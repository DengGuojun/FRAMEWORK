package test.com.lpmas.framework.util;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lpmas.framework.util.JsonKit;

import test.com.lpmas.framework.bean.CRMCouponQueryResponsetBean;
import test.com.lpmas.framework.bean.CRMCouponReponseBean;
import test.com.lpmas.framework.bean.TestBean;
import test.com.lpmas.framework.bean.TestBeanB;
import test.com.lpmas.framework.bean.TestBeanC;
import test.com.lpmas.framework.bean.TestBeanD;

public class TestJsonKit {
	
	@Test
	public void test(){
		TestBean beanA = new TestBean();		
		beanA.setUserId(100);
		beanA.setUserName("abc");
		
		TestBean beanB = new TestBean();		
		beanB.setUserId(200);
		beanB.setUserName("def");
		
		List<TestBean> list = new ArrayList<TestBean>();
		list.add(beanA);
		list.add(beanB);
		
		String result = JsonKit.toJson(list);
		System.out.println(result);
		
		List<TestBean> listA = JsonKit.toList(result, TestBean.class);
		System.out.println(listA.get(0).getUserName());
	}

	//@Test
	public void testA(){
		TestBean beanA = new TestBean();		
		beanA.setUserId(100);
		beanA.setUserName("abc");
		
		TestBeanB beanB = new TestBeanB();
		beanB.setProductId(0);
		beanB.setProductNumber("pn");
		beanB.setItem(beanA);
		
		String json = JsonKit.toJson(beanB);
		System.out.println(json);
		
		TestBeanB beanR = JsonKit.toBean(json, TestBeanB.class);
		System.out.println(JsonKit.toJson(beanR));
	}
	
	//@Test
	public void testB(){
		TestBeanB beanB = new TestBeanB();
		beanB.setProductId(2);
		
		TestBean beanA = new TestBean();		
		beanA.setUserId(100);
		beanA.setUserName("abc");
		beanB.setItem(beanA);
		
		List<TestBeanB> item = new ArrayList<TestBeanB>();
		item.add(beanB);
		
		TestBeanC beanC = new TestBeanC();
		beanC.setProductId(0);
		beanC.setProductNumber("pn");
		beanC.setItem(item);
		
		String json = JsonKit.toJson(beanC);
		System.out.println(json);
		
		TestBeanC beanR = JsonKit.toBean(json, TestBeanC.class);
		System.out.println(JsonKit.toJson(beanR));
	}
	
	//@Test
	public void testC() throws JsonParseException, JsonMappingException, IOException{
		TestBeanB beanB = new TestBeanB();
		beanB.setProductId(500);
		List<TestBeanB> list = new ArrayList<TestBeanB>();
		list.add(beanB);
		
		TestBeanC beanC = new TestBeanC();
		beanC.setItem(list);
		
		TestBeanD<TestBeanC> beanD = new TestBeanD<TestBeanC>();
		beanD.setOrderId(200);
		beanD.setData(beanC);
		
		String json = JsonKit.toJson(beanD);
		System.out.println(json);
		
		TypeReference<TestBeanD<TestBeanC>> type = new TypeReference<TestBeanD<TestBeanC>>(){};
		TestBeanD<TestBeanC> beanR = JsonKit.getObjectMapper().readValue(json, type);
		TestBeanC c = beanR.getData();
		System.out.println(JsonKit.toJson(c));
	}
	
	//@Test
	public void testD(){
//		CRMCouponInfoBean couponBean = new CRMCouponInfoBean();
//		couponBean.setCouponId(100);
//		List<CRMCouponInfoBean> list = new ArrayList<CRMCouponInfoBean>();
//		list.add(couponBean);
//		
//		CRMCouponQueryResponsetBean cqrBean = new CRMCouponQueryResponsetBean();
//		cqrBean.setItems(list);
//		
//		CRMCouponReponseBean crBean = new CRMCouponReponseBean();
//		crBean.setCode("ok");
//		crBean.setContent(cqrBean);
//		
//		String json = JsonKit.toJson(crBean);
//		System.out.println(json);
		
		String json = "{\"code\":\"ok\",\"message\":\"\",\"content\":{\"pageNum\":1,\"pageSize\":20,\"totalCount\":0,\"items\":[{\"couponId\":100,\"couponNo\":\"\",\"couponPassword\":\"\",\"couponTemplateId\":0,\"isUsed\":0,\"startTime\":0,\"endTime\":0,\"userId\":0,\"bindTime\":0}]}}";
		
		try {
			TypeReference<CRMCouponReponseBean<CRMCouponQueryResponsetBean>> type = new TypeReference<CRMCouponReponseBean<CRMCouponQueryResponsetBean>>(){};
			CRMCouponReponseBean<CRMCouponQueryResponsetBean> bean = JsonKit.getObjectMapper().readValue(json, type);
			CRMCouponQueryResponsetBean aBean = (CRMCouponQueryResponsetBean)bean.getContent();
			System.out.println(JsonKit.toJson(aBean));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		CRMCouponReponseBean beanR = JsonKit.toBean(json, CRMCouponReponseBean.class);
//		Map<String,Object> map = (Map<String,Object>)beanR.getContent();
//		System.out.println(JsonKit.toJson(map));
	}
	
	@Test
	public void testToBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testToList() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJsonObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJsonListOfObject() {
		fail("Not yet implemented");
	}

}
