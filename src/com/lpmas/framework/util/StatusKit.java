package com.lpmas.framework.util;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;

public class StatusKit {
	/**
	 * 将状态列表转换成map
	 * 
	 * @param list
	 * @return
	 */
	public static <E,T> HashMap<E,T> toMap(List<StatusBean<E,T>> list) {
		HashMap<E,T> map = new HashMap<E,T>();
		if (list.size() > 0) {
			for (StatusBean<E,T> bean : list) {
				map.put(bean.getStatus(), bean.getValue());
			}
		}
		return map;
	}
}