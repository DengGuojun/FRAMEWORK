package com.lpmas.framework.transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;

public class HttpClientKit {
	private static Logger log = LoggerFactory.getLogger(HttpClientKit.class);

	private static final String DEFAULT_CHARSET = Constants.ENCODING_UNICODE;// 默认编码
	private static final int DEFAULT_CONNECT_TIMEOUT = 5000;// 默认链接超时时间，5s
	private static final int DEFAULT_SOCKET_TIMEOUT = 10000;// 默认socket超时时间，10s

	private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
	private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
	private int connectionRequestTimeout = DEFAULT_SOCKET_TIMEOUT;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public HttpClientResultBean getContent(String url, Map<String, String> paramMap) {
		return getContent(url, paramMap, DEFAULT_CHARSET);
	}

	public HttpClientResultBean getContent(String url, Map<String, String> paramMap, String charset) {
		Set<String> keySet = paramMap.keySet();
		Iterator<String> it = keySet.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (sb.length() > 0) {
				sb.append("&");
			} else {
				sb.append("?");
			}
			sb.append(key);
			sb.append("=");
			sb.append(paramMap.get(key));
		}
		return getContent(url, charset);
	}

	public HttpClientResultBean getContent(String url, String charset) {
		HttpClientResultBean result = new HttpClientResultBean();

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = null;
		try {
			httpGet.setConfig(getRequestConfig());// 处理配置信息
			httpResponse = httpClient.execute(httpGet);

			processHttpResponse(httpResponse, result, charset);
		} catch (Exception e) {
			result.setResult(false);
			result.setResultContent(e.toString());
			log.error("通过get方法获取Http内容错误：", e);
		} finally {
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	public HttpClientResultBean postContent(String url, Map<String, String> paramMap) {
		return postContent(url, paramMap, DEFAULT_CHARSET);
	}

	public <K, V> HttpClientResultBean postContent(String url, Map<K, V> paramMap, String charset) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		for (Map.Entry<K, V> entry : paramMap.entrySet()) {
			paramList.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
		}
		return postContent(url, paramList, charset);
	}

	public HttpClientResultBean postContent(String url, List<NameValuePair> paramList, String charset) {
		HttpClientResultBean result = new HttpClientResultBean();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(paramList));
			httpPost.setConfig(getRequestConfig());// 处理配置信息
			httpResponse = httpClient.execute(httpPost);

			processHttpResponse(httpResponse, result, charset);
		} catch (IOException e) {
			result.setResult(false);
			result.setResultContent(e.toString());
			log.error("通过post方法获取Http内容错误[IOException]：", e);
		} finally {
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	public HttpClientResultBean postContent(String url, String data, String charset) {
		HttpClientResultBean result = new HttpClientResultBean();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = null;
		try {
			StringEntity entity = new StringEntity(data, charset);
			httpPost.setEntity(entity);
			httpPost.setConfig(getRequestConfig());// 处理配置信息
			httpResponse = httpClient.execute(httpPost);

			processHttpResponse(httpResponse, result, charset);
		} catch (IOException e) {
			result.setResult(false);
			result.setResultContent(e.toString());
			log.error("通过post方法获取Http内容错误[IOException]：", e);
		} finally {
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	private RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(this.getSocketTimeout())
				.setConnectTimeout(this.getConnectTimeout())
				.setConnectionRequestTimeout(this.getConnectionRequestTimeout()).build();
		return requestConfig;
	}

	private void processHttpResponse(HttpResponse httpResponse, HttpClientResultBean bean, String charset)
			throws IOException {
		HttpEntity httpEntity = httpResponse.getEntity();

		int statusCode = httpResponse.getStatusLine().getStatusCode();// 获取状态码
		if (statusCode == HttpStatus.SC_OK) {
			bean.setResult(true);
			bean.setResultByteContent(EntityUtils.toByteArray(httpEntity));
			bean.setResultContent(new String(bean.getResultByteContent(), charset));
		} else {
			bean.setResult(false);
			bean.setResultContent(EntityUtils.toString(httpEntity, charset));
		}
	}
}
