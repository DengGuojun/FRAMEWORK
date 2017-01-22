package com.lpmas.framework.page;

public class PageBean {
	// 当前页数，第n页
	private int currentPageNumber = 0;

	// 每页的记录条数
	private int pageSize = 20;

	// 总页数
	private int totalPageNumber = 0;

	// 上一页
	private int prePageNumber = 0;

	// 下一页
	private int nextPageNumber = 0;

	// 起始记录条数
	private int startRecordNumber = 0;

	// 终止记录条数
	private int endRecordNumber = 0;

	// 总记录条数
	private int totalRecordNumber = 0;

	public PageBean() {
	}

	public PageBean(int currentPageNumber, int pageSize) {
		init(currentPageNumber, pageSize);
	}

	public void init(int currentPageNumber, int pageSize) {
		this.setCurrentPageNumber(currentPageNumber);
		this.setPageSize(pageSize);

		// 计算并设置起始记录条数和终止记录条数
		int startRecordNumber = pageSize * (currentPageNumber - 1);
		int endRecordNumber = startRecordNumber + pageSize;

		this.setStartRecordNumber(startRecordNumber);
		this.setEndRecordNumber(endRecordNumber);
	}

	public void init(int currentPageNumber, int pageSize, int totalRecordNumber) {
		init(currentPageNumber, pageSize);
		this.setTotalRecordNumber(totalRecordNumber);

		// 计算并设置总页数
		int totalPageNumber = totalRecordNumber / pageSize;
		if ((totalRecordNumber % pageSize) != 0) {
			totalPageNumber = totalPageNumber + 1;
		}
		this.setTotalPageNumber(totalPageNumber);

		// 计算并设置上一页的页数
		int prePageNumber = 0;
		if (currentPageNumber > 1) {
			prePageNumber = currentPageNumber - 1;
		}
		this.setPrePageNumber(prePageNumber);

		// 计算并设置下一页的页数
		int nextPageNumber = 0;
		if (currentPageNumber < totalPageNumber) {
			nextPageNumber = currentPageNumber + 1;
		}
		this.setNextPageNumber(nextPageNumber);
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	public int getPrePageNumber() {
		return prePageNumber;
	}

	public void setPrePageNumber(int prePageNumber) {
		this.prePageNumber = prePageNumber;
	}

	public int getNextPageNumber() {
		return nextPageNumber;
	}

	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}

	public int getStartRecordNumber() {
		return startRecordNumber;
	}

	public void setStartRecordNumber(int startRecordNumber) {
		this.startRecordNumber = startRecordNumber;
	}

	public int getEndRecordNumber() {
		return endRecordNumber;
	}

	public void setEndRecordNumber(int endRecordNumber) {
		this.endRecordNumber = endRecordNumber;
	}

	public int getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(int totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}

}
