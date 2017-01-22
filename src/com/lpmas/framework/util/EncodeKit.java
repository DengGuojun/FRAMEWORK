package com.lpmas.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;

public class EncodeKit {
	private static Logger log = LoggerFactory.getLogger(EncodeKit.class);

	/**
	 * 转换字符串为ISO
	 * 
	 * @param str
	 * @return
	 */
	public static String toIso(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(), Constants.ENCODING_ISO);
		} catch (Exception e) {
			log.error("字符串转换ISO出错：", e);
		}
		return str;
	}

	/**
	 * 转换字符串为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String toGbk(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(), Constants.ENCODING_GBK);
		} catch (Exception e) {
			log.error("字符串转换GBK出错：", e);
		}
		return str;
	}

	/**
	 * 转换字符串为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(), Constants.ENCODING_UNICODE);
		} catch (Exception e) {
			log.error("字符串转换UTF8出错：", e);
		}
		return str;
	}

	public static String toSystemCode(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		if (Constants.SYSTEM_ENCODING.equals(Constants.ENCODING_ISO)) {
			return toIso(str);
		} else if (Constants.SYSTEM_ENCODING.equals(Constants.ENCODING_UNICODE)) {
			return toUnicode(str);
		} else {
			return toGbk(str);
		}
	}

	/**
	 * 将ISO字符串转换为UTF
	 * 
	 * @param str
	 * @return
	 */
	public static String iso2Unicode(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_ISO), Constants.ENCODING_UNICODE);
		} catch (Exception e) {
			log.error("ISO转换UTF8出错：", e);
		}
		return str;
	}

	/**
	 * 将UTF字符串转换为ISO
	 * 
	 * @param str
	 * @return
	 */
	public static String unicode2Iso(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_UNICODE), Constants.ENCODING_ISO);
		} catch (Exception e) {
			log.error("UTF8转换ISO出错：", e);
		}

		return str;
	}

	/**
	 * 将ISO字符串转换为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String iso2Gbk(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_ISO), Constants.ENCODING_GBK);
		} catch (Exception e) {
			log.error("ISO转换GBK出错：", e);
		}
		return str;
	}

	/**
	 * 将GBK字符串转换为ISO
	 * 
	 * @param str
	 * @return
	 */
	public static String gbk2Iso(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_GBK), Constants.ENCODING_ISO);
		} catch (Exception e) {
			log.error("GBK转换ISO出错：", e);
		}

		return str;
	}

	/**
	 * 将UTF字符串转换为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String unicode2Gbk(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_UNICODE), Constants.ENCODING_GBK);
		} catch (Exception e) {
			log.error("UTF8转换GBK出错：", e);
		}
		return str;
	}

	/**
	 * 将GBK字符串转换为UTF
	 * 
	 * @param str
	 * @return
	 */
	public static String gbk2Unicode(String str) {
		if (!StringKit.isValid(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(Constants.ENCODING_GBK), Constants.ENCODING_UNICODE);
		} catch (Exception e) {
			log.error("GBK转换UTF8出错：", e);
		}

		return str;
	}
}
