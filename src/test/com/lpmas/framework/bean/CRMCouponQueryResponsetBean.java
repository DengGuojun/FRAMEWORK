package test.com.lpmas.framework.bean;

import java.util.ArrayList;
import java.util.List;

public class CRMCouponQueryResponsetBean {

	private int pageNum = 1;
	private int pageSize = 20;
	private int totalCount = 0;
	private List<CRMCouponInfoBean> items = new ArrayList<CRMCouponInfoBean>();

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<CRMCouponInfoBean> getItems() {
		return items;
	}

	public void setItems(List<CRMCouponInfoBean> items) {
		this.items = items;
	}
	
	
}
