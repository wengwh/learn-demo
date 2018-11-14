package com.plumdo.jdbc.common.http;

import org.apache.log4j.Logger;

/**
 *类名：HttpTemplate
 */

public class HttpTemplate {

	protected Logger logger = Logger.getRootLogger();

	protected HttpProtocolHandler protocolHandler = null;

	public void init() {
		protocolHandler = HttpProtocolHandler.getInstance();
	}

	public void destroy() {
		protocolHandler.destroy();
	}

	public HttpResponse execute(HttpRequest request) {
		return protocolHandler.execute(request);
	}
	
	public HttpRequest getHttpRequest(String url) {
		HttpRequest request = new HttpRequest(HttpResultType.STRING);
		request.setUrl(url);
		request.setSign(true);
		request.addHeader("User-Agent", "Mozilla/4.0");
		return request;
	}
}
