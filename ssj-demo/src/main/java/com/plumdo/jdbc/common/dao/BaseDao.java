package com.plumdo.jdbc.common.dao;


import java.util.List;
import java.util.Map;

import com.plumdo.jdbc.common.param.PageParam;
import com.plumdo.jdbc.common.query.QueryBulider;

/**
 * 
 * @author wengwenhui
 * 
 */
public interface BaseDao {

	/**
	 * 插入数据
	 * 
	 * @param entityMap
	 *            添加内容
	 * @return 主键值
	 */
	Long insert(Map<String, Object> entityMap);

	/**
	 * 插入数据
	 * 
	 * @param entityMap
	 *            添加内容
	 * @param isAutoKey
	 *            是否自增主键
	 * @return
	 */
	Long insert(Map<String, Object> entityMap, boolean isAutoKey);

	/**
	 * 批量插入数据
	 * 
	 * @param entityList
	 * @return
	 */
	int[] batchInsert(List<Map<String, Object>> entityList);

	/**
	 * 更新记录
	 * 
	 * @param column
	 *            修改内容字段名
	 * @param columnVal
	 *            修改内容字段值
	 * @param keyVal
	 *            更新条件字段值
	 * @return
	 */
	int updateByPkey(String column, Object columnVal, Object keyVal);

	/**
	 * 更新记录
	 * 
	 * @param column
	 *            修改内容字段名
	 * @param columnVal
	 *            修改内容字段值
	 * @param key
	 *            更新条件字段名
	 * @param keyVal
	 *            更新条件字段值
	 * @return
	 */
	int updateByKey(String column, Object columnVal, String key, Object keyVal);

	/**
	 * 更新记录
	 * 
	 * @param column
	 *            修改内容字段名
	 * @param columnVal
	 *            修改内容字段值
	 * @param paramMap
	 *            更新条件
	 * @return 更新记录数
	 */
	int update(String column, Object columnVal, Map<String, Object> paramMap);

	/**
	 * 更新记录
	 * 
	 * @param column
	 *            修改内容字段名
	 * @param columnVal
	 *            修改内容字段值
	 * @param queryBulider
	 *            更新条件
	 * @return 更新记录数
	 */
	int update(String column, Object columnVal, QueryBulider queryBulider);

	/**
	 * 更新记录
	 * 
	 * @param entityMap
	 *            修改内容
	 * @param val
	 *            更新条件字段值
	 * @return 更新记录数
	 */
	int updateByPkey(Map<String, Object> entityMap, Object val);

	/**
	 * 更新记录
	 * 
	 * @param entityMap
	 *            修改内容
	 * @param key
	 *            更新条件字段名
	 * @param val
	 *            更新条件字段值
	 * @return 更新记录数
	 */
	int updateByKey(Map<String, Object> entityMap, String key, Object val);

	/**
	 * 更新记录
	 * 
	 * @param entityMap
	 *            修改内容
	 * @param paramMap
	 *            更新条件
	 * @return 更新记录数
	 */
	int update(Map<String, Object> entityMap, Map<String, Object> paramMap);

	/**
	 * 更新记录
	 * 
	 * @param entityMap
	 *            修改内容
	 * @param queryBulider
	 *            更新条件
	 * @return 更新记录数
	 */
	int update(Map<String, Object> entityMap, QueryBulider queryBulider);

	/**
	 * 删除记录
	 * 
	 * @param val
	 *            删除字段值
	 * @return 删除记录数
	 */
	int deleteByPkey(Object val);

	/**
	 * 删除记录(单个条件)
	 * 
	 * @param key
	 *            删除字段名
	 * @param val
	 *            删除字段值
	 * @return 删除记录数
	 */
	int deleteByKey(String key, Object val);

	/**
	 * 删除记录
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 删除记录数
	 */
	int delete(Map<String, Object> paramMap);

	/**
	 * 删除记录
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @return 删除记录数
	 */
	int delete(QueryBulider queryBulider);

	/**
	 * 分页查询
	 * 
	 * @param pageParam
	 *            分页参数
	 * @param paramMap
	 *            查询条件
	 * @return 记录列表
	 */
	List<Map<String, Object>> listPage(PageParam pageParam, Map<String, Object> paramMap);

	/**
	 * 分页查询
	 * 
	 * @param pageParam
	 *            分页参数
	 * @param paramMap
	 *            查询条件
	 * @param fields
	 *            返回字段
	 * @return 记录列表
	 */
	List<Map<String, Object>> listPage(PageParam pageParam, Map<String, Object> paramMap, String fields);

	/**
	 * 分页查询
	 * 
	 * @param pageParam
	 *            分页参数
	 * @param queryBulider
	 *            查询条件
	 * @return 记录列表
	 */
	List<Map<String, Object>> listPage(PageParam pageParam, QueryBulider queryBulider);

	/**
	 * 分页查询
	 * 
	 * @param pageParam
	 *            分页参数
	 * @param paramMap
	 *            查询条件
	 * @param fields
	 *            返回字段
	 * @return 记录列表
	 */
	List<Map<String, Object>> listPage(PageParam pageParam, QueryBulider queryBulider, String fields);

	/**
	 * 查询 不分页
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	List<Map<String, Object>> listByKey(String key, Object val);

	/**
	 * 查询 不分页
	 * 
	 * @param key
	 * @param val
	 * @param fields
	 * @return
	 */
	List<Map<String, Object>> listByKey(String key, Object val, String fields);

	/**
	 * 查询 不分页
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 记录列表
	 */
	List<Map<String, Object>> listBy(Map<String, Object> paramMap);

	/**
	 * 查询 不分页
	 * 
	 * @param paramMap
	 *            查询条件
	 * @param fields
	 * @return 记录列表
	 */
	List<Map<String, Object>> listBy(Map<String, Object> paramMap, String fields);

	/**
	 * 查询 不分页
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @return 记录列表
	 */
	List<Map<String, Object>> listBy(QueryBulider queryBulider);

	/**
	 * 查询 不分页
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @param fields
	 *            返回字段
	 * @return 记录列表
	 */
	List<Map<String, Object>> listBy(QueryBulider queryBulider, String fields);

	/**
	 * 查询单条记录(单个条件)
	 * 
	 * @param key
	 *            查询字段名
	 * @param val
	 *            查询字段值
	 * @return 单条记录
	 */
	Map<String, Object> getByKey(String key, Object val);

	/**
	 * 查询单条记录(单个条件)
	 * 
	 * @param key
	 *            查询字段名
	 * @param val
	 *            查询字段值
	 * @param fields
	 *            返回字段
	 * @return 单条记录
	 */
	Map<String, Object> getByKey(String key, Object val, String fields);

	/**
	 * 查询单条记录
	 * 
	 * @param val
	 *            查询字段值
	 * @return 单条记录
	 */
	Map<String, Object> getByPkey(Object val);

	/**
	 * 查询单条记录
	 * 
	 * @param val
	 *            查询字段值
	 * @param fields
	 *            返回字段
	 * @return 单条记录
	 */
	Map<String, Object> getByPkey(Object val, String fields);

	/**
	 * 查询单条记录
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 单条记录
	 */
	Map<String, Object> getBy(Map<String, Object> paramMap);

	/**
	 * 查询单条记录
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 单条记录
	 */
	Map<String, Object> getBy(Map<String, Object> paramMap, String fields);

	/**
	 * 查询单条记录
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @return 单条记录
	 */
	Map<String, Object> getBy(QueryBulider queryBulider);

	/**
	 * 查询单条记录
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @param fields
	 *            返回字段
	 * @return 单条记录
	 */
	Map<String, Object> getBy(QueryBulider queryBulider, String fields);

	/**
	 * 获取记录数(单个条件)
	 * 
	 * @param key
	 *            查询字段名
	 * @param val
	 *            查询字段值
	 * @return 记录数
	 */
	Long countByKey(String key, Object val);

	/**
	 * 获取记录数
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 记录数
	 */
	Long count(Map<String, Object> paramMap);

	/**
	 * 获取记录数
	 * 
	 * @param queryBulider
	 *            查询条件
	 * @return 记录数
	 */
	Long count(QueryBulider queryBulider);

}