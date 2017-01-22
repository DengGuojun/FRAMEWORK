package test.com.lpmas.framework.mq.activemq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lpmas.framework.mq.MQFactory;
import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.util.DateKit;

public class TestActiveMQSender {

	@Test
	public void test() {
		//String brokerId = "Test";
		String queueName = "FirstQueue";
		String message = "中文[" + DateKit.getCurrentDateTime() + "]";

		List<String> list = new ArrayList<String>();
		list.add(1 + message);
		//list.add(2 + message);

		MQFactory factory = new ActiveMQFactory();
		MQSender sender = factory.getMQSender();
		try {
			sender.send(queueName, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
