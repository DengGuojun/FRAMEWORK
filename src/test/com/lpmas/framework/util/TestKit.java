package test.com.lpmas.framework.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestKit {
	private static int count = 10000000;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("before");
	}

	@Test
	public void testString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");

		long startTime = new Date().getTime();
		for (int i = 0; i < count; i++) {
			String a = map.get("a");
			String b = map.get("b");
			String c = map.get("c");
		}
		long endTime = new Date().getTime();
		System.out.println("string : " + (endTime - startTime));
	}

	@Test
	public void testObject() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", "2");
		map.put("c", "3");

		long startTime = new Date().getTime();
		for (int i = 0; i < count; i++) {
			Object a = map.get("a");
			Object b = map.get("b");
			Object c = map.get("c");
			Integer s1 = (Integer) a;
			String s2 = (String) b;
			String s3 = (String) c;
		}
		long endTime = new Date().getTime();
		System.out.println("object : " + (endTime - startTime));
	}

}
