package test.com.lpmas.framework.mq.activemq;

import com.lpmas.framework.mq.activemq.ActiveMQReceiver;

public class TestActiveMQReceiver1 extends ActiveMQReceiver {

	public TestActiveMQReceiver1(String brokerId) {
		super(brokerId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(String message) {
		System.out.println(message);

	}

}
