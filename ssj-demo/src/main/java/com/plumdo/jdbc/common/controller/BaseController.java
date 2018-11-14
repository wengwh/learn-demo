package com.plumdo.jdbc.common.controller;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.plumdo.jdbc.common.param.BaseOutputParmImpl;
import com.plumdo.jdbc.common.param.DataOutputParmImpl;
import com.plumdo.jdbc.common.param.PageParam;
import com.plumdo.jdbc.common.param.PageOutputParmImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author wengwenhui
 *
 */
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpSession session;

	protected Object getJsonResult() {
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		return returnParm.toJson();
	}

	protected Object getJsonResult(Object data) {
		DataOutputParmImpl returnParm = new DataOutputParmImpl();
		returnParm.success(data);
		return returnParm.toJson();
	}

	protected Object getJsonResult(Object data, Object total) {
		PageOutputParmImpl returnParm = new PageOutputParmImpl();
		returnParm.success(data, total);
		return returnParm.toJson();
	}
	
	protected PageParam getPageParam() {
		PageParam pageParam = new PageParam();

		if (StringUtils.isNotEmpty(request.getParameter("page"))) {
			pageParam.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (StringUtils.isNotEmpty(request.getParameter("size"))) {
			pageParam.setSize(Integer.parseInt(request.getParameter("size")));
		}
		if (StringUtils.isNotEmpty(request.getParameter("sort"))) {
			pageParam.setSort(Arrays.asList(request.getParameterValues("sort")));
		}
		if (StringUtils.isNotEmpty(request.getParameter("order"))) {
			pageParam.setOrder(Arrays.asList(request.getParameterValues("order")));
		}
		return pageParam;
	}

}
