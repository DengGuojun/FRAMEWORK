package test.com.lpmas.framework.bean;

import java.util.List;

public class TestBeanC {
	private int productId = 0;
	private String productNumber = "";
	private List<TestBeanB> item = null;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public List<TestBeanB> getItem() {
		return item;
	}

	public void setItem(List<TestBeanB> item) {
		this.item = item;
	}
}
