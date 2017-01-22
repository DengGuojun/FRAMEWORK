package com.lpmas.framework.bean;

public class StatusBean<E, T> {
	private E status = null;
	private T value = null;

	public StatusBean(E status, T value) {
		this.setStatus(status);
		this.setValue(value);
	}

	public E getStatus() {
		return status;
	}

	public void setStatus(E status) {
		this.status = status;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
