package com.lpmas.framework.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StringKit;

public class ParamKit {
	public static String[] getArrayParameter(HttpServletRequest request, String paramName) {
		return request.getParameterValues(paramName);
	}

	public static String getAttribute(HttpServletRequest request, String paramName) {
		return getAttribute(request, paramName, true);
	}

	public static String getAttribute(HttpServletRequest request, String paramName, boolean allowNull) {
		String value = (String) request.getAttribute(paramName);
		if (null != value) {
			if ((value.equals("")) && allowNull) {
				return null;
			}
			return value;
		}

		return null;
	}

	public static boolean getBooleanAttribute(HttpServletRequest request, String paramName) {
		String value = (String) request.getAttribute(paramName);
		return ((null != value) && (value.equalsIgnoreCase("true")));
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String paramName) {
		return getBooleanParameter(request, paramName, true);
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String paramName, boolean defaultValue) {
		String value = request.getParameter(paramName);
		if ((null != value) && (!(value.equals("")))) {
			value = value.toLowerCase();
			if (("true".equals(value)) || ("on".equals(value))
					|| (String.valueOf(Constants.STATUS_VALID).equals(value))) {
				return true;
			} else if (("false".equals(value)) || ("off".equals(value))
					|| (String.valueOf(Constants.STATUS_NOT_VALID).equals(value))) {
				return false;
			}
		}
		return defaultValue;
	}

	public static double getDoubleParameter(HttpServletRequest request, String paramName, double defaultValue) {
		String value = request.getParameter(paramName);
		if (StringKit.isValid(value)) {
			double result = defaultValue;
			try {
				result = Double.parseDouble(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return defaultValue;
	}

	public static int getIntAttribute(HttpServletRequest request, String paramName, int defaultValue) {
		String value = (String) request.getAttribute(paramName);
		if (StringKit.isValid(value)) {
			int result = defaultValue;
			try {
				result = Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return defaultValue;
	}

	public static int getIntParameter(HttpServletRequest request, String paramName, int defaultValue) {
		String value = request.getParameter(paramName);
		if (StringKit.isValid(value)) {
			int result = defaultValue;
			try {
				result = Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return defaultValue;
	}

	public static int[] getIntParameters(HttpServletRequest request, String paramName, int defaultValue) {
		String[] values = request.getParameterValues(paramName);
		if (values == null) {
			return null;
		}
		if (values.length < 1) {
			return new int[0];
		}
		int[] result = new int[values.length];
		for (int i = 0; i < values.length; ++i) {
			try {
				result[i] = Integer.parseInt(values[i]);
			} catch (Exception e) {
				result[i] = defaultValue;
			}
		}
		return result;
	}

	public static long getLongAttribute(HttpServletRequest request, String paramName, long defaultValue) {
		String value = (String) request.getAttribute(paramName);
		if (StringKit.isValid(value)) {
			long result = defaultValue;
			try {
				result = Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return defaultValue;
	}

	public static long getLongParameter(HttpServletRequest request, String paramName, long defaultValue) {
		String value = request.getParameter(paramName);
		if (StringKit.isValid(value)) {
			long result = defaultValue;
			try {
				result = Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return defaultValue;
	}

	public static long[] getLongParameters(HttpServletRequest request, String paramName, long defaultValue) {
		String[] values = request.getParameterValues(paramName);
		if (values == null) {
			return null;
		}
		if (values.length < 1) {
			return new long[0];
		}
		long[] result = new long[values.length];
		for (int i = 0; i < values.length; ++i) {
			try {
				result[i] = Long.parseLong(values[i]);
			} catch (Exception e) {
				result[i] = defaultValue;
			}
		}
		return result;
	}

	public static String getParameter(HttpServletRequest request, String paramName) {
		return getParameter(request, paramName, false);
	}

	public static String getParameter(HttpServletRequest request, String paramName, boolean allowNull) {
		String value = request.getParameter(paramName);
		if (value != null) {
			if ((value.equals("")) && allowNull) {
				return null;
			}
			return value;
		}

		return null;
	}

	public static String getParameter(HttpServletRequest request, String paramName, String defaultValue) {
		String value = request.getParameter(paramName);
		if (value != null) {
			return value;
		}
		return defaultValue;
	}

	public static HashMap<String, String> getParameterMap(HttpServletRequest request, String paramNames) {
		return getParameterMap(request, paramNames, false, null);
	}

	public static HashMap<String, String> getParameterMap(HttpServletRequest request, String paramNames,
			String defaultValue) {
		return getParameterMap(request, paramNames, true, defaultValue);
	}

	public static HashMap<String, String> getParameterMap(HttpServletRequest request, String paramNames,
			boolean allowNull, String defaultValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		List<String> list = ListKit.string2List(paramNames, ",");
		for (String paramName : list) {
			String value = request.getParameter(paramName);
			if (value != null) {
				map.put(paramName, value);
			} else {
				if (allowNull) {
					map.put(paramName, defaultValue);
				}
			}
		}
		return map;
	}

	public static boolean isValidRequest(HttpServletRequest request, String regex) {
		return isValidUrl(request, regex) && isValidParam(request, regex);
	}

	public static boolean isValidUrl(HttpServletRequest request, String regex) {
		StringBuffer url = request.getRequestURL();
		return StringKit.isMatch(url.toString(), regex);
	}

	public static boolean isValidParam(HttpServletRequest request, String regex) {
		Enumeration<String> names = request.getParameterNames();
		List<String> list = new ArrayList<String>();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = getParameter(request, name);
			list.add(value);
		}
		return StringKit.isMatch(list, regex);
	}

	public static String getInputStream(HttpServletRequest request) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}