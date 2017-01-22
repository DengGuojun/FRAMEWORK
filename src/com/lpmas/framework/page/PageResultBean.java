package com.lpmas.framework.page;

import java.util.List;

public class PageResultBean<T> {
	private int totalRecordNumber = 0;

	private List<T> recordList = null;

	public PageResultBean() {
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(int totalRecordNum) {
		this.totalRecordNumber = totalRecordNum;
	}
}
