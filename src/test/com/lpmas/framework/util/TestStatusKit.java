package test.com.lpmas.framework.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.MapKit;

public class TestStatusKit {

	@Test
	public void test() {
		System.out.println(MapKit.getValueFromMap(1, Constants.SELECT_MAP));
		System.out.println(MapKit.getValueFromMap(1, Constants.STATUS_MAP));
	}

}
