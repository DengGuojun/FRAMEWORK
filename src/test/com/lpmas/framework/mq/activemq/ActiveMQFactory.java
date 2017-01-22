package test.com.lpmas.framework.mq.activemq;

import com.lpmas.framework.mq.MQFactory;
import com.lpmas.framework.mq.MQObject;
import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.mq.activemq.ActiveMQSender;

public class ActiveMQFactory extends MQFactory {
	@Override
	public MQObject getMQObject() {		
		return null ;
	}

	@Override
	public MQSender getMQSender() {
		return new ActiveMQSender("Test");
	}

}
