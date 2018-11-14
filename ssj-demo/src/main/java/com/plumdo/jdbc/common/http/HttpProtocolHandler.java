package com.plumdo.jdbc.common.http;

import org.apache.commons.httpclient.HttpException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plumdo.jdbc.common.exception.HttpFailException;

/**
 * 类名：HttpProtocolHandler 功能：HttpClient方式访问 详细：获取远程HTTP数据
 */

public class HttpProtocolHandler {

	private static Logger logger = LoggerFactory.getLogger(HttpProtocolHandler.class);

	private static String DEFAULT_CHARSET = "UTF-8";

	/** 连接超时时间，由bean factory设置，缺省为8秒钟 */
	private int defaultConnectionTimeout = 8000;

	/** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
	private int defaultSoTimeout = 30000;

	/** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
	private int defaultIdleConnTimeout = 60000;

	private int defaultMaxConnPerHost = 150;

	private int defaultMaxTotalConn = 300;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：3秒 */
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private HttpConnectionManager connectionManager;

	private IdleConnectionTimeoutThread ict;

	private static class SingleTonBuilder {
		private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();
	}

	/**
	 * 工厂方法
	 * 
	 * @return
	 */
	public static HttpProtocolHandler getInstance() {
		return SingleTonBuilder.httpProtocolHandler;
	}

	public void destroy() {
		MultiThreadedHttpConnectionManager.shutdownAll();
		ict.shutdown();
	}

	/**
	 * 私有的构造方法
	 */
	private HttpProtocolHandler() {
		// 创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

		ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(defaultIdleConnTimeout);

		ict.start();
	}

	/**
	 * 执行Http请求
	 * 
	 * @param request
	 *            请求数据
	 * @return
	 * @throws HttpException
	 *             , IOException
	 */
	public HttpResponse execute(HttpRequest request) {
		HttpClient httpclient = new HttpClient(connectionManager);
		List<Header> headers = new ArrayList<Header>();
		httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);

		if (request.isSign()) {
			request.addParameter("sign", request.getSign());
		}

		// 设置连接超时
		int connectionTimeout = defaultConnectionTimeout;
		if (request.getConnectionTimeout() > 0) {
			connectionTimeout = request.getConnectionTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

		// 设置回应超时
		int soTimeout = defaultSoTimeout;
		if (request.getTimeout() > 0) {
			soTimeout = request.getTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

		// 设置等待ConnectionManager释放connection的时间
		httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

		String charset = request.getCharset();
		charset = charset == null ? DEFAULT_CHARSET : charset;

		HttpMethod method = null;

		HttpResponse response = new HttpResponse();
		try {
			if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
				method = new GetMethod(request.getUrl());
				if (request.getParameters() != null)
					((GetMethod) method).setQueryString(request.getParameters());
			} else if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
				method = new PostMethod(request.getUrl());
				if (request.getParameters() != null){
					if (request.getRequestEntity() != null){
						((PostMethod) method).setQueryString(request.getParameters());
					}else{
						((PostMethod) method).setRequestBody(request.getParameters());
					}
				}
				if (request.getRequestEntity() != null)
					((PostMethod) method).setRequestEntity(request.getRequestEntity());
			} else if (request.getMethod().equals(HttpRequest.METHOD_PUT)) {
				method = new PutMethod(request.getUrl());
				if (request.getParameters() != null)
					((PutMethod) method).setQueryString(request.getParameters());
				if (request.getRequestEntity() != null)
					((PutMethod) method).setRequestEntity(request.getRequestEntity());
			} else if (request.getMethod().equals(HttpRequest.METHOD_DELETE)) {
				method = new DeleteMethod(request.getUrl());
				if (request.getParameters() != null)
					((DeleteMethod) method).setQueryString(request.getParameters());
			}
			if (request.getHeaders() != null) {
				for (Header header : request.getHeaders()) {
					method.addRequestHeader(header);
				}
			}
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset); 

			String url = method.getURI().toString();
			if(!url.contains("?") && request.getParameters()!=null){
				url = url + "?" + EncodingUtil.formUrlEncode(request.getParameters(),"UTF-8");
			}
			
			logger.info("调用请求method：{}，url：{}",method.getName(),url);

			if (request.getRequestEntity() instanceof StringRequestEntity) {
				logger.info("调用请求体：" + ((StringRequestEntity) request.getRequestEntity()).getContent());
			}

			httpclient.executeMethod(method);
			response.setStatusCode(method.getStatusCode());
			logger.info("请求返回的结果码:" + response.getStatusCode());
			response.setResponseHeaders(method.getResponseHeaders());
			if (method.getResponseBodyAsStream() != null) {
				if (request.getResultType().equals(HttpResultType.STRING)) {
					response.setStringResult(IOUtils.toString(method.getResponseBodyAsStream(), charset));
					logger.info("请求返回数据:" + response.getStringResult());
				} else if (request.getResultType().equals(HttpResultType.BYTES)) {
					response.setByteResult(IOUtils.toByteArray(method.getResponseBodyAsStream()), charset);
				}
			}

			String failMsg = response.getFailMsg();
			if (failMsg != null) {
				throw new HttpFailException(failMsg);
			}

		} catch (Exception ex) {
			logger.error("调用Rest接口异常", ex);
			throw new HttpFailException("调用Rest接口异常");
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return response;
	}

}
