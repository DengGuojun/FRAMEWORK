package com.lpmas.framework.mq.activemq;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.lpmas.framework.mq.MQObject;
import com.lpmas.framework.mq.MQObjectFactory;

public class ActiveMQObjectFactory extends MQObjectFactory {

	public ActiveMQObjectFactory(String brokerId) {
		this.brokerId = brokerId;
	}

	@Override
	public MQObject create() throws Exception {
		return new ActiveMQObject(brokerId);
	}

	@Override
	public PooledObject<MQObject> wrap(MQObject obj) {
		return new DefaultPooledObject<MQObject>((ActiveMQObject) obj);
	}
	
	@Override
	public void destroyObject(PooledObject<MQObject> p) throws Exception {
		ActiveMQObject activeMQObject = (ActiveMQObject) p;
		activeMQObject.close();
	}
}
