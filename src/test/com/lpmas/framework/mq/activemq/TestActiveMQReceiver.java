package test.com.lpmas.framework.mq.activemq;

import org.junit.Test;

import com.lpmas.framework.mq.activemq.ActiveMQReceiver;

public class TestActiveMQReceiver {

	@Test
	public void test() {
		String brokerId = "Test";
		String queueName = "FirstQueue";

		ActiveMQReceiver receiver = new TestActiveMQReceiver1(brokerId);
		receiver.receive(queueName);
	}

}
