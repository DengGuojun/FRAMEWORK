package test.com.lpmas.framework.transfer;

import java.util.HashMap;

import org.junit.Test;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.transfer.HttpClientKit;
import com.lpmas.framework.transfer.HttpClientResultBean;
import com.lpmas.framework.util.JsonKit;

public class TestHttpClientKit {

	@Test
	public void test() {
		String url = "http://www.fiveplus.com";
		// try {
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpGet httpGet = new HttpGet("http://www.ochirly.com.cn");
		// CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
		//
		// HttpEntity httpEntity = httpResponse.getEntity();
		// int statusCode = httpResponse.getStatusLine().getStatusCode();
		// System.out.println(statusCode);
		// System.out.println(new
		// String(IOUtils.toByteArray(httpEntity.getContent()),"utf-8"));
		// EntityUtils.consume(httpEntity);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		HashMap<String, String> map = new HashMap<String, String>();

		HttpClientKit httpClientKit = new HttpClientKit();
		//HttpClientResultBean bean = httpClientKit.postContent(url, map, Constants.CODE_UNICODE);
		HttpClientResultBean bean = httpClientKit.getContent(url, Constants.ENCODING_UNICODE);
		System.out.println(JsonKit.toJson(bean));
	}

}
