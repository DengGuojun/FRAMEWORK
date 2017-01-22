package com.lpmas.framework.db;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lpmas.framework.util.DateKit;

public class SqlKit {
	private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DateKit.DEFAULT_DATE_TIME_FORMAT);

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String getQueryStmt(String value) {
		if (null == value) {
			return "''";
		}
		String v = value.trim();
		int vs = v.length();
		StringBuilder sb = new StringBuilder();
		char c = 0;
		sb.append('\'');
		for (int i = 0; i < vs; i++) {
			c = v.charAt(i);
			// 防止sql注入处理，替换'为''，替换\为\\
			if ('\'' == c) {
				sb.append('\'');
				sb.append('\'');
			} else if ('\\' == c) {
				sb.append('\\');
				sb.append('\\');
			} else {
				sb.append(c);
			}
		}
		sb.append('\'');
		return sb.toString();
	}

	/**
	 * 拼接SQL语法的字段字符串值，默认日期格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String getQueryStmt(Date value) {
		return "'" + SIMPLE_DATE_FORMAT.format(value) + "'";
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串
	 */
	private static String getQueryStmt(Timestamp value) {
		return "'" + value + "'";
	}

	/**
	 * 拼接SQL语法的字段字符串值
	 * 
	 * @param value
	 *            -- 数据
	 * @return -- SQL片段字符串，如果value为null，返回字符串：''
	 */
	public static String getQueryStmt(Object value) {
		if (null == value) {
			return "''";
		} else if (value instanceof String) {
			return getQueryStmt((String) value);
		} else if (value instanceof Date) {
			return getQueryStmt((Date) value);
		} else if (value instanceof Timestamp) {
			return getQueryStmt((Timestamp) value);
		} else if (value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Float
				|| value instanceof Double) {
			// 基本数字类型
			return getPrimitiveQueryStmt(value);
		} else if (value instanceof List) {
			return getQueryStmt(((List<?>) value).toArray());
		} else if (value instanceof Set) {
			return getQueryStmt(((Set<?>) value).toArray());
		} else if (value.getClass().isArray()) {
			// 数组类型
			Class<?> clazz = value.getClass().getComponentType();
			if (clazz == String.class) {
				return getQueryStmt(String[].class.cast(value));
			} else if (clazz == int.class) {
				return getQueryStmt(getQueryStmt((int[]) value));
			} else if (clazz == long.class) {
				return getQueryStmt(getQueryStmt((long[]) value));
			} else if (clazz == short.class) {
				return getQueryStmt(getQueryStmt((short[]) value));
			} else if (clazz == float.class) {
				return getQueryStmt(getQueryStmt((float[]) value));
			} else if (clazz == double.class) {
				return getQueryStmt(getQueryStmt((double[]) value));
			}
			// 默认,转成Object对象数组
			return getQueryStmt((Object[]) value);
		} else {
			return "'" + value.toString() + "'";
		}
	}

	/**
	 * 拼接SQL语法的字段字符串值，适用于基本数据类型
	 * 
	 * @param value
	 * @return
	 */
	private static <T> String getPrimitiveQueryStmt(T value) {
		return value.toString();
	}

	/**
	 * 拼接SQL语法的字段字符串值，适用于数组类型
	 * 
	 * @param value
	 * @return
	 */
	private static <T> String getQueryStmt(T[] value) {
		if (null == value) {
			return "''";
		}
		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < value.length; i++) {
			sql.append(getQueryStmt(value[i]));
			if (i < value.length - 1) {
				sql.append(",");
			}
		}
		return sql.toString();
	}

	/**
	 * 将int数组转换成Integer数组
	 * 
	 * @param array
	 * @return
	 */
	private static Integer[] getQueryStmt(int[] array) {
		Integer[] result = new Integer[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	/**
	 * 将short数组转换成Short数组
	 * 
	 * @param array
	 * @return
	 */
	private static Short[] getQueryStmt(short[] array) {
		Short[] result = new Short[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	/**
	 * 将long数组转换成Long数组
	 * 
	 * @param array
	 * @return
	 */
	private static Long[] getQueryStmt(long[] array) {
		Long[] result = new Long[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	/**
	 * 将float数组转换成Float数组
	 * 
	 * @param array
	 * @return
	 */
	private static Float[] getQueryStmt(float[] array) {
		Float[] result = new Float[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	/**
	 * 将double数组转换成Double数组
	 * 
	 * @param array
	 * @return
	 */
	private static Double[] getQueryStmt(double[] array) {
		Double[] result = new Double[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = array[i];
		return result;
	}

	public static String getCountSql(String sql) {
		return "select count(1) from (" + sql + ") t";
	}

	public static String condList2QueryStmt(List<String> condList, boolean needWhereStmt) {
		StringBuilder sb = new StringBuilder();
		if (condList.size() > 0) {
			if (needWhereStmt) {
				sb.append(" where ");
			}
			for (int i = 0; i < condList.size(); i++) {
				String cond = condList.get(i);
				if (i > 0) {
					sb.append(" and ");
				}
				sb.append(cond);
			}
		}
		return sb.toString();
	}

	public static String getInQueryPreparedStmt(int count) throws SQLException {
		StringBuilder sb = new StringBuilder();
		if (count <= 1000) {
			for (int i = 0; i < count; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append("?");
			}
		} else {
			throw new SQLException();
		}
		return sb.toString();
	}
}
