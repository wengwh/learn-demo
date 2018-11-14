package com.plumdo.jdbc.common.service;

import java.util.Map;

import com.plumdo.jdbc.common.param.PageParam;


/**
 * 
 * @author wengwenhui
 *
 */
public interface BaseService {
	
	Map<String, String> create(Map<String, Object> entityMap);

	Map<String, String> update(Map<String, Object> entityMap, Map<String, Object> paramMap);

	Map<String, Object> listPage(PageParam pageParam, Map<String, Object> params);

	Map<String, String> deleteByPkey(Object id);

	Map<String, Object> listBy(Map<String, Object> paramMap);

	Map<String, Object> getBy(Map<String, Object> paramMap);

	Map<String, String> delete(Map<String, Object> paramMap);
}
