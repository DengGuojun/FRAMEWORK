package com.lpmas.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileKit {
	private final static String mobileNumberRule = "1(3\\d|5[0-3,5-9]|8[0-3,5-9]|4[57]|7[0,6-8])\\d{8}";

	private static Pattern mobileNumberPattern = Pattern.compile("^" + mobileNumberRule + "$");

	/**
	 * 检查是否合法的电话号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isValidMobileNumber(String mobile) {
		Matcher matcher = mobileNumberPattern.matcher(mobile);
		return matcher.matches();
	}
}
