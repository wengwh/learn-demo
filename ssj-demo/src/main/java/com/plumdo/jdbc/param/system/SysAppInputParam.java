package com.plumdo.jdbc.param.system;

import com.plumdo.jdbc.common.exception.InputParameterException;
import com.plumdo.jdbc.common.param.BaseInputParam;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * swt_sys_app表输入参数实体类
 * 
 * @author wengwh
 * @Date 2016-11-25
 * 
 */
public class SysAppInputParam implements BaseInputParam {

	private final HttpServletRequest request;

	public SysAppInputParam(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void validateCreate() {
		if (StringUtils.isEmpty(request.getParameter("type"))) {
			throw new InputParameterException("type 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("name"))) {
			throw new InputParameterException("name 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("description"))) {
			throw new InputParameterException("description 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("report_location_flag"))) {
			throw new InputParameterException("report_location_flag 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("isreportuser"))) {
			throw new InputParameterException("isreportuser 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("isreportenter"))) {
			throw new InputParameterException("isreportenter 不能为空");
		}
	}

	@Override
	public void validateUpdate() {
		if (StringUtils.isEmpty(request.getParameter("sysapp_id"))) {
			throw new InputParameterException("sysapp_id 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("type"))) {
			throw new InputParameterException("type 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("name"))) {
			throw new InputParameterException("name 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("description"))) {
			throw new InputParameterException("description 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("report_location_flag"))) {
			throw new InputParameterException("report_location_flag 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("isreportuser"))) {
			throw new InputParameterException("isreportuser 不能为空");
		}
		if (StringUtils.isEmpty(request.getParameter("isreportenter"))) {
			throw new InputParameterException("isreportenter 不能为空");
		}
	}

	public Map<String, Object> getCreateEntityMap() {
		Map<String, Object> entityMap = new HashMap<String, Object>();
		entityMap.put("type", request.getParameter("type"));
		entityMap.put("name", request.getParameter("name"));
		entityMap.put("description", request.getParameter("description"));
		entityMap.put("home_url", request.getParameter("home_url"));
		entityMap.put("logo_url", request.getParameter("logo_url"));
		entityMap.put("report_location_flag", request.getParameter("report_location_flag"));
		entityMap.put("isreportuser", request.getParameter("isreportuser"));
		entityMap.put("isreportenter", request.getParameter("isreportenter"));
		return entityMap;
	}

	public Map<String, Object> getUpdateEntityMap() {
		Map<String, Object> entityMap = new HashMap<String, Object>();
		entityMap.put("type", request.getParameter("type"));
		entityMap.put("name", request.getParameter("name"));
		entityMap.put("description", request.getParameter("description"));
		entityMap.put("home_url", request.getParameter("home_url"));
		entityMap.put("logo_url", request.getParameter("logo_url"));
		entityMap.put("report_location_flag", request.getParameter("report_location_flag"));
		entityMap.put("isreportuser", request.getParameter("isreportuser"));
		entityMap.put("isreportenter", request.getParameter("isreportenter"));
		return entityMap;
	}
}
