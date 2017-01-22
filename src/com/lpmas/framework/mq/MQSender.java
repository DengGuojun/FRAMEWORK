package com.lpmas.framework.mq;

import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MQSender {
	private static Logger log = LoggerFactory.getLogger(MQSender.class);

	protected GenericObjectPool<MQObject> mqObjectPool;

	// protected MQObject mqObject = null;

	/**
	 * 发送消息到指定队列中
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public void send(String queueName, String message) throws Exception {
		MQObject mqObject = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			mqObject = mqObjectPool.borrowObject();
			// Connection ：客户端到JMS Provider 的连接
			Connection connection = mqObject.getConnection();
			// 启动
			// connection.start();

			MQSenderConfigBean senderConfigBean = mqObject.configBean.getSenderConfig();
			// Session： 一个发送或接收消息的线程
			session = connection.createSession(senderConfigBean.isTransacted(), senderConfigBean.getAcknowledgeMode());
			// 消息的目的地
			Destination destination = session.createQueue(queueName);
			// 消息发送者
			producer = session.createProducer(destination);
			// 设置是否持久化
			producer.setDeliveryMode(senderConfigBean.getDeliveryMode());

			TextMessage textMessage = session.createTextMessage(message);
			producer.send(textMessage);
			log.info("向队列[{}]发送消息[{}]", queueName, message);
			if (senderConfigBean.isTransacted()) {
				session.commit();
			}
		} catch (Exception e) {
			log.error("发送消息到MQ[{}]失败：", queueName, e);
			throw e;
		} finally {
			try {
				if (null != producer) {
					producer.close();
				}
			} catch (Exception e) {
				log.error("MQ队列关闭producer错误", e);
			}
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception e) {
				log.error("MQ队列关闭session错误", e);
			}
			try {
				mqObjectPool.returnObject(mqObject);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 发送多个消息到指定队列中
	 * 
	 * @param queueName
	 *            队列名称
	 * @param messageList
	 *            消息内容列表
	 */
	public void send(String queueName, List<String> messageList) throws Exception {
		MQObject mqObject = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			mqObject = mqObjectPool.borrowObject();
			// Connection ：客户端到JMS Provider 的连接
			Connection connection = mqObject.getConnection();

			MQSenderConfigBean senderConfigBean = mqObject.configBean.getSenderConfig();
			// Session： 一个发送或接收消息的线程
			session = connection.createSession(senderConfigBean.isTransacted(), senderConfigBean.getAcknowledgeMode());
			// 消息的目的地
			Destination destination = session.createQueue(queueName);
			// 消息发送者
			producer = session.createProducer(destination);
			// 设置是否持久化
			producer.setDeliveryMode(senderConfigBean.getDeliveryMode());

			for (String message : messageList) {
				TextMessage textMessage = session.createTextMessage(message);
				producer.send(textMessage);
				log.info("向队列[{}]发送消息[{}]", queueName, message);
			}
			if (senderConfigBean.isTransacted()) {
				session.commit();
			}
		} catch (Exception e) {
			log.error("发送消息到MQ[{}]失败：", queueName, e);
			throw e;
		} finally {
			try {
				if (null != producer) {
					producer.close();
				}
			} catch (Exception e) {
				log.error("MQ队列关闭producer错误", e);
			}
			try {
				if (null != session) {
					session.close();
				}
			} catch (Exception e) {
				log.error("MQ队列关闭session错误", e);
			}
			try {
				mqObjectPool.returnObject(mqObject);
			} catch (Exception e) {
			}
		}
	}
}
