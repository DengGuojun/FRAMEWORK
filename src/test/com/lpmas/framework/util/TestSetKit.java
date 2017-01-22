package test.com.lpmas.framework.util;

import java.util.Set;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.SetKit;

public class TestSetKit {

	@Test
	public void test() {
		String str = "1,2,3";
		String regex = ",";
		//Set<Integer> intSet = SetKit.string2Set(str, regex,Integer.class);
		//System.out.println(JsonKit.toJson(intSet));
		
		Set<String> strSet = SetKit.string2Set(str, regex);
		System.out.println(JsonKit.toJson(strSet));
		
		//Set<String> strSet = SetKit.string2Set(str, regex,String.class);
		//System.out.println(JsonKit.toJson(strSet));
	}

}
