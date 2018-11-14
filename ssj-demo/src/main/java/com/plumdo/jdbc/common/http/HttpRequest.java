package com.plumdo.jdbc.common.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.RequestEntity;

import com.plumdo.jdbc.common.util.EncryptUtil;



/**
 *类名：HttpRequest 功能：Http请求对象的封装 详细：封装Http请求
 */

public class HttpRequest {

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_PUT = "PUT";
	public static final String METHOD_DELETE = "DELETE";

	private String url = null;

	private String method = METHOD_POST;

	private String contentType = null;

	private boolean sign = false;

	private int timeout = 0;

	private int connectionTimeout = 0;

	/**
	 * 请求时组装好的参数值对
	 */
	private List<NameValuePair> parameters = null;

	/**
	 * 请求时组装好的参数值对
	 */
	private List<Header> headers = null;

	/**
	 * 请求时对应的流方式的
	 */
	private RequestEntity requestEntity = null;

	/**
	 * 默认的请求编码方式
	 */
	private String charset = "UTF-8";

	/**
	 * 请求返回的方式
	 */
	private HttpResultType resultType = HttpResultType.BYTES;

	public HttpRequest(HttpResultType resultType) {
		super();
		this.resultType = resultType;
	}

	public NameValuePair[] getParameters() {
		if (this.parameters != null) {
			return this.parameters.toArray(new NameValuePair[this.parameters.size()]);
		} else {
			return null;
		}
	}

	public void addParameter(String key, Object value) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<NameValuePair>();
		}
		NameValuePair nameValuePair = new NameValuePair(key, String.valueOf(value));
		this.parameters.add(nameValuePair);
	}

	public void addParameters(NameValuePair[] valuePairs) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<NameValuePair>();
		}
		if (valuePairs != null) {
			for (int i = 0; i < valuePairs.length; i++) {
				this.parameters.add(valuePairs[i]);
			}
		}
	}

	public void addHeader(String key, String value) {
		if (this.headers == null) {
			this.headers = new ArrayList<Header>();
		}
		Header header = new Header(key, value);
		this.headers.add(header);
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public RequestEntity getRequestEntity() {
		return requestEntity;
	}

	public void setRequestEntity(RequestEntity requestEntity) {
		this.requestEntity = requestEntity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public HttpResultType getResultType() {
		return resultType;
	}

	public void setResultType(HttpResultType resultType) {
		this.resultType = resultType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}

	public String getSign() {
		Map<String, String> newMap = new HashMap<String, String>();
		if (parameters != null) {
			for (NameValuePair entry : parameters) {
				if (newMap.containsKey(entry.getName())) {
					newMap.put(entry.getName(), newMap.get(entry.getName()) + entry.getValue());
				} else {
					newMap.put(entry.getName(), entry.getValue());
				}
			}
		}
		return EncryptUtil.sign(newMap);
	}

}
