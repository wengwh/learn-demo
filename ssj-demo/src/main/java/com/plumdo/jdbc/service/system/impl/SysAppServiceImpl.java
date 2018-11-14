package com.plumdo.jdbc.service.system.impl;

import com.plumdo.jdbc.common.service.BaseServiceImpl;
import com.plumdo.jdbc.dao.system.SysAppDao;
import com.plumdo.jdbc.service.system.SysAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * swt_sys_app 表数据服务层接口实现类
 * 
 * @author wengwh
 * @Date 2016-11-25
 * 
 */
@Component
@Transactional
public class SysAppServiceImpl extends BaseServiceImpl implements SysAppService {

	@Autowired
	private SysAppDao sysAppDao;

	@PostConstruct
	public void initBaseDao() {
		super.setBaseDao(sysAppDao);
	}

}