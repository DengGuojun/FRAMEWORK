package com.lpmas.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.lpmas.framework.web.ParamKit;

public class BeanKit {
	private static Map<String, List<Field>> fieldListMap = new HashMap<String, List<Field>>();

	static {
		DateConverter date = new DateConverter(null);
		ConvertUtils.register(date, java.util.Date.class);

		SqlDateConverter sqlDate = new SqlDateConverter(null);
		ConvertUtils.register(sqlDate, java.sql.Date.class);

		SqlTimestampConverter timestamp = new SqlTimestampConverter(null);
		ConvertUtils.register(timestamp, java.sql.Timestamp.class);
	}

	/**
	 * 将request里面的对象转换成bean
	 * 
	 * @param request
	 * @param clazz
	 *            转换成的class
	 * @return
	 * @throws Exception
	 */
	public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) throws ServletException {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Enumeration<String> namesEnum = request.getParameterNames();
			while (namesEnum.hasMoreElements()) {
				String name = (String) namesEnum.nextElement();
				String value = ParamKit.getParameter(request, name);
				if (StringKit.isValid(value)) {
					map.put(name, value);
				}
			}
			return map2Bean(map, clazz);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 转换字段名称，将驼峰式字符串转换成数据库中带“_”字段
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String convertFieldName(String fieldName) {
		StringBuilder result = new StringBuilder();
		char[] charArray = fieldName.toCharArray();
		boolean isLastNumber = false;
		for (char ch : charArray) {
			boolean isNumber = Character.isDigit(ch);
			boolean isLowerCase = Character.isLowerCase(ch);
			if ((!isNumber && isLowerCase) || (isNumber && isLastNumber)) {// 非数字的小写字母
				result.append(ch);
			} else {
				result.append("_").append(Character.toLowerCase(ch));
			}

			isLastNumber = isNumber;
		}
		return result.toString();
	}

	/**
	 * 将bean里面的String属性null值转为空字符串
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static <T> T convertStringFieldValueNull2Blank(T bean) throws Exception {
		List<Field> fieldList = getDeclaredFieldList(bean.getClass());
		for (Field field : fieldList) {
			String fieldName = field.getName();
			if (field.getType().equals(String.class)) {
				String value = BeanUtils.getProperty(bean, fieldName);
				if (StringKit.isNull(value)) {
					BeanUtils.setProperty(bean, fieldName, "");
				}
			}
		}
		return bean;
	}

	/**
	 * 将ResultSet内容转换成bean
	 * 
	 * @param <T>
	 * @param rs
	 * @param clazz
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws Exception
	 */
	public static <T> T resultSet2Bean(ResultSet rs, Class<T> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Field> fieldList = getDeclaredFieldList(clazz);
		for (Field field : fieldList) {
			if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {// 如果字段是final或者static修饰符，不处理
				continue;
			}
			Object value = null;
			String fieldName = convertFieldName(field.getName());
			try {
				value = rs.getObject(fieldName);
			} catch (SQLException sqle) {
			}
			if (value != null) {
				map.put(field.getName(), value);
			}
		}

		return map2Bean(map, clazz);
	}

	/**
	 * 获取类中声明的字段
	 * 
	 * @param clazz
	 * @return 字段Field列表
	 */
	public static <T> List<Field> getDeclaredFieldList(Class<T> clazz) {
		// 判断是否已经在fieldListMap里面有此字段列表
		String className = clazz.getName();
		if (fieldListMap.containsKey(className)) {
			return fieldListMap.get(className);
		}

		List<Field> fieldList = new ArrayList<Field>();
		Class<?> superClazz = clazz.getSuperclass();
		if (!superClazz.getName().equals("java.lang.Object")) {
			fieldList.addAll(getDeclaredFieldList(superClazz));
		}
		fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
		fieldListMap.put(className, fieldList);// 将字段列表增加到fieldListMap中
		return fieldList;
	}

	/**
	 * 获取类中声明的字段
	 * 
	 * @param bean
	 * @return 字段Field列表
	 */
	public static <T> List<Field> getDeclaredFieldList(T bean) {
		return getDeclaredFieldList(bean.getClass());
	}

	/**
	 * 将HashMap内容转换成bean
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		T bean = clazz.newInstance();
		BeanUtils.populate(bean, map);
		return bean;
	}

	/**
	 * 复制bean
	 * 
	 * @param targetBean
	 * @param sourceBean
	 * @throws Exception
	 */
	public static <E, T> void copyBean(E targetBean, T sourceBean)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(targetBean, sourceBean);
	}
}
