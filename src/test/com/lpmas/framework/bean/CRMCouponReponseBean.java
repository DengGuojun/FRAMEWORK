package test.com.lpmas.framework.bean;

public class CRMCouponReponseBean<E> {

	private String code = "";
	private String message = "";
	private E content = null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public E getContent() {
		return content;
	}
	public void setContent(E content) {
		this.content = content;
	}
	
	
}
