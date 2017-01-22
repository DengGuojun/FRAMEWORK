package test.com.lpmas.framework.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j2 {
	private static Logger log = LoggerFactory.getLogger(TestLog4j2.class);
	
	@Test
	public void test() {
		log.error("hello world");
		log.info("hello world");
		log.debug("hello world!!!!");
	}

}
