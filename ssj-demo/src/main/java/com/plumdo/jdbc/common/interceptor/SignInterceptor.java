package com.plumdo.jdbc.common.interceptor;

import com.plumdo.jdbc.common.exception.ForbiddenException;
import com.plumdo.jdbc.common.util.EncryptUtil;
import com.plumdo.jdbc.common.util.JacksonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author wengwenhui
 *
 */
public class SignInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
    
    @SuppressWarnings("rawtypes")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	logger.info("接口URL： {}",request.getRequestURI());
    	logger.info("接口参数： {}",JacksonUtil.object2Json(request.getParameterMap()));
    	logger.info("接口实现类： {}",handler);

        Map<String, String> map = new HashMap<String, String>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (paramName.equals("sign")) {
                continue;
            }
            String[] paramValues = request.getParameterValues(paramName);
            StringBuilder paramValue = new StringBuilder();
            for (String s : paramValues) {
                paramValue.append(s);
            }
            map.put(paramName, paramValue.toString());
        }
        String sign = EncryptUtil.sign(map);
         if (!sign.equals(request.getParameter("sign"))) {
        	 throw new ForbiddenException("接口验证出错sign："+sign);
        }
        return true;
    }


}
