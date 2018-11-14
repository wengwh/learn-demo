package com.plumdo.jdbc.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * 
 * @author wengwenhui
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomObjectMapper(){
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//设置有属性不能映射成PO时不报错
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
}
