package com.lpmas.framework.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.framework.util.StatusKit;

public class Constants {
	// 配置文件存放目录
	public static final String PROP_FILE_PATH = "config";

	// 系统配置文件路径
	public static final String PROP_FILE_NAME = PROP_FILE_PATH + "/framework_config";

	// 编码
	public static final String ENCODING_ISO = "ISO-8859-1";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_UNICODE = "UTF-8";

	// 通用状态
	public static final int STATUS_NOT_VALID = 0;// 无效
	public static final int STATUS_VALID = 1;// 有效

	public static List<StatusBean<Integer,String>> STATUS_LIST = new ArrayList<StatusBean<Integer,String>>();
	public static Map<Integer, String> STATUS_MAP = new HashMap<Integer, String>();

	// 选择
	public static final int SELECT_FALSE = 0;// 否
	public static final int SELECT_TRUE = 1;// 是

	public static List<StatusBean<Integer,String>> SELECT_LIST = new ArrayList<StatusBean<Integer,String>>();
	public static Map<Integer, String> SELECT_MAP = new HashMap<Integer, String>();

	// 缓存时间
	public static final int CACHE_TIME_1_MIN = 1 * 60;
	public static final int CACHE_TIME_5_MIN = 5 * 60;
	public static final int CACHE_TIME_10_MIN = 10 * 60;
	public static final int CACHE_TIME_15_MIN = 15 * 60;
	public static final int CACHE_TIME_20_MIN = 20 * 60;
	public static final int CACHE_TIME_30_MIN = 30 * 60;
	public static final int CACHE_TIME_1_HOUR = 60 * 60;
	public static final int CACHE_TIME_2_HOUR = 2 * 60 * 60;
	public static final int CACHE_TIME_4_HOUR = 4 * 60 * 60;
	public static final int CACHE_TIME_8_HOUR = 8 * 60 * 60;
	public static final int CACHE_TIME_12_HOUR = 12 * 60 * 60;
	public static final int CACHE_TIME_1_DAY = 24 * 60 * 60;

	// 系统信息
	public static final String SYSTEM_ENCODING = PropertiesKit.getBundleProperties(PROP_FILE_NAME, "SYSTEM_ENCODING");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");// 路径分隔符

	// 页面路径
	public static final String PAGE_PATH = "/WEB-INF/pages/";

	static {
		init();
	}

	private static void init() {
		initStatusList();
		initStatusMap();

		initSelectList();
		initSelectMap();
	}

	private static void initStatusList() {
		STATUS_LIST = new ArrayList<StatusBean<Integer,String>>();
		STATUS_LIST.add(new StatusBean<Integer,String>(STATUS_NOT_VALID, "无效"));
		STATUS_LIST.add(new StatusBean<Integer,String>(STATUS_VALID, "有效"));
	}

	private static void initStatusMap() {
		STATUS_MAP = StatusKit.toMap(STATUS_LIST);
	}

	private static void initSelectList() {
		SELECT_LIST = new ArrayList<StatusBean<Integer,String>>();
		SELECT_LIST.add(new StatusBean<Integer,String>(SELECT_TRUE, "是"));
		SELECT_LIST.add(new StatusBean<Integer,String>(SELECT_FALSE, "否"));
	}

	private static void initSelectMap() {
		SELECT_MAP = StatusKit.toMap(SELECT_LIST);
	}
}
