package com.plumdo.test;


import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plumdo.jdbc.common.http.HttpRequest;
import com.plumdo.jdbc.common.http.HttpTemplate;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})  
public class HttpTest {
	 public static String URL = "http://localhost:8080/plumdo-jdbc/";



	public static HttpTemplate httpTemplate = new HttpTemplate();
	static {
		httpTemplate.init();
	}
	@Test  
	public void get() throws IOException{
		HttpRequest request = httpTemplate.getHttpRequest("");
		request.setMethod("GET");
		request.setUrl(URL + "sys/app/list");
		request.addParameter("company_id", "19");
		
		httpTemplate.execute(request);
	}
}
