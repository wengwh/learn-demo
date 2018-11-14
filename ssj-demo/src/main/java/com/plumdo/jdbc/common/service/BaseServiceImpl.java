package com.plumdo.jdbc.common.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.plumdo.jdbc.common.dao.BaseDao;
import com.plumdo.jdbc.common.param.PageParam;

/**
 * 
 * @author wengwenhui
 *
 */
@Transactional
public class BaseServiceImpl implements BaseService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Map<String, String> deleteByPkey(Object id) {
		Map<String, String> ret = new HashMap<String, String>();
		baseDao.deleteByPkey(id);
		return ret;
	}

	@Override
	public Map<String, Object> getBy(Map<String, Object> paramMap) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("data", baseDao.getBy(paramMap));
		return ret;
	}

	@Override
	public Map<String, Object> listPage(PageParam pageParam, Map<String, Object> paramMap) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("data", baseDao.listPage(pageParam, paramMap));
		ret.put("total", pageParam.getTotal());
		return ret;
	}

	@Override
	public Map<String, Object> listBy(Map<String, Object> paramMap) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("data", baseDao.listBy(paramMap));
		return ret;
	}

	@Override
	public Map<String, String> update(Map<String, Object> entityMap, Map<String, Object> paramMap) {
		Map<String, String> ret = new HashMap<String, String>();
		baseDao.update(entityMap, paramMap);
		return ret;
	}

	@Override
	public Map<String, String> create(Map<String, Object> entityMap) {
		Map<String, String> ret = new HashMap<String, String>();
		baseDao.insert(entityMap);
		return ret;
	}

	@Override
	public Map<String, String> delete(Map<String, Object> paramMap) {
		Map<String, String> ret = new HashMap<String, String>();
		baseDao.delete(paramMap);
		return ret;
	}
}
