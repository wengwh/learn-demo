package com.plumdo.jdbc.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 
 * @author wengwenhui
 *
 */
@Component
public class Constant {
	public static String SIGN_SECRET;
	
	@Value("${conf.sign_secret}")  
	public void setSignSecret(String signSecret) {
		SIGN_SECRET = signSecret;
	}

}
