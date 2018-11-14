package com.plumdo.jdbc.common.http;

import org.apache.commons.httpclient.Header;

import com.plumdo.jdbc.common.exception.ConvertException;
import com.plumdo.jdbc.common.util.JacksonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 类名：HttpResponse 功能：Http返回对象的封装 详细：封装Http返回信息
 */

public class HttpResponse {

	/**
	 * 返回中的Header信息
	 */
	private Header[] responseHeaders;

	private int statusCode;

	/**
	 * String类型的result
	 */
	private String stringResult;

	/**
	 * btye类型的result
	 */
	private byte[] byteResult;

	private String charst;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Header[] getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Header[] responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public byte[] getByteResult() {
		if (byteResult != null) {
			return byteResult;
		}
		if (stringResult != null) {
			return stringResult.getBytes();
		}
		return null;
	}

	public void setByteResult(byte[] byteResult, String charst) {
		this.byteResult = byteResult;
		this.charst = charst;
	}

	public String getStringResult() {
		if (stringResult != null) {
			return stringResult;
		}
		if (byteResult != null) {
			try {
				return new String(byteResult, charst);
			} catch (UnsupportedEncodingException e) {
				throw new ConvertException("http返回结果转换异常", e);
			}
		}
		return null;
	}

	public Map<String, Object> getMapResult() {
		return JacksonUtil.json2Map(getStringResult());
	}

	public String getFailMsg() {
		if (!String.valueOf(statusCode).startsWith("2")) {
			return "请求url返回错误状态码：" + statusCode;
		}
		if (getStringResult() != null && getStringResult().contains("ret") && "-1".equals(getMapResult().get("ret").toString())) {
			return getMapResult().get("msg").toString();
		}
		return null;
	}

	public void setStringResult(String stringResult) {
		this.stringResult = stringResult;
	}

}
