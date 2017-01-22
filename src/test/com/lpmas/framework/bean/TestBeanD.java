package test.com.lpmas.framework.bean;

public class  TestBeanD<E> {
	private int orderId = 0;
	private String orderNumber = "";
	private E data = null;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}
