package com.plumdo.jdbc.controller.system;

import com.plumdo.jdbc.common.controller.BaseController;
import com.plumdo.jdbc.common.exception.InputParameterException;
import com.plumdo.jdbc.param.system.SysAppInputParam;
import com.plumdo.jdbc.service.system.SysAppService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * swt_sys_app 表控制层实现类
 * 
 * @author wengwh
 * @Date 2016-11-25
 * 
 */
@RestController
@RequestMapping("/sys/app")
public class SysAppController extends BaseController {

	@Autowired
	private SysAppService sysAppService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, name = "应用创建")
	public Object create() {
		SysAppInputParam inputParam = new SysAppInputParam(request);
		inputParam.validateCreate();
		sysAppService.create(inputParam.getCreateEntityMap());
		return getJsonResult();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, name = "应用修改")
	public Object update() {
		SysAppInputParam inputParam = new SysAppInputParam(request);
		inputParam.validateUpdate();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysapp_id", request.getParameter("sysapp_id"));
		sysAppService.update(inputParam.getUpdateEntityMap(), paramMap);
		return getJsonResult();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, name = "获取应用列表")
	public Object list() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", request.getParameter("type"));
		Map<String, Object> ret = sysAppService.listPage(getPageParam(), paramMap);
		return getJsonResult(ret.get("data"), ret.get("total"));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, name = "获取应用详情")
	public Object get() {
		if (StringUtils.isEmpty(request.getParameter("sysapp_id"))) {
			throw new InputParameterException("sysapp_id 不能为空");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysapp_id", request.getParameter("sysapp_id"));
		Map<String, Object> ret = sysAppService.getBy(paramMap);
		return getJsonResult(ret.get("data"));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, name = "应用删除")
	public Object delete() {
		if (StringUtils.isEmpty(request.getParameter("sysapp_id"))) {
			throw new InputParameterException("sysapp_id 不能为空");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysapp_id", request.getParameter("sysapp_id"));
		sysAppService.delete(paramMap);
		return getJsonResult();
	}
}