package com.plumdo.jdbc.dao.system.impl;

import com.plumdo.jdbc.common.dao.BaseDaoImpl;
import com.plumdo.jdbc.dao.system.SysAppDao;

import org.springframework.stereotype.Component;

/**
 * swt_sys_app 表数据库控制层实现类
 * 
 * @author wengwh
 * @Date 2016-11-25
 * 
 */
@Component
public class SysAppDaoImpl extends BaseDaoImpl implements SysAppDao {

	public SysAppDaoImpl(){
		super.setTable("swt_sys_app");
		super.setPkey("sysapp_id");
	}
}