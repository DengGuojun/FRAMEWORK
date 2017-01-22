package com.lpmas.framework.mq;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

public abstract class MQObjectFactory extends BasePooledObjectFactory<MQObject> {
	protected String brokerId;
	
	
}
