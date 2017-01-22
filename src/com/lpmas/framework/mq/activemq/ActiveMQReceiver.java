package com.lpmas.framework.mq.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.mq.MQReceiver;

public abstract class ActiveMQReceiver extends MQReceiver {
	private static Logger log = LoggerFactory.getLogger(ActiveMQReceiver.class);

	public ActiveMQReceiver(String brokerId) {
		this.mqObject = new ActiveMQObject(brokerId);
	}

	@Override
	public abstract void process(String message);

}
