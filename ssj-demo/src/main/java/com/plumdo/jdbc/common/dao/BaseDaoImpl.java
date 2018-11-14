package com.plumdo.jdbc.common.dao;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.plumdo.jdbc.common.http.HttpTemplate;
import com.plumdo.jdbc.common.param.PageParam;
import com.plumdo.jdbc.common.query.QueryBulider;
import com.plumdo.jdbc.common.query.SimpleJdbcTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wengwenhui
 * 
 */
public abstract class BaseDaoImpl implements BaseDao {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpTemplate httpTemplate;
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;

	private String table;
	private String pkey;

	protected String getTable() {
		return table;
	}

	protected void setTable(String table) {
		this.table = table;
	}

	protected String getPkey() {
		return pkey;
	}

	protected void setPkey(String pkey) {
		this.pkey = pkey;
	}

	protected SimpleJdbcTemplate getJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	protected HttpTemplate getHttpTemplate() {
		return httpTemplate;
	}

	@Override
	public Long insert(Map<String, Object> entityMap) {
		return insert(entityMap, true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Long insert(Map<String, Object> entityMap, boolean isAutoKey) {
		List<Object> values = new ArrayList<Object>();
		List<String> keys = new ArrayList<String>();
		Iterator iterator = entityMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			if (entry.getValue()!=null && StringUtils.isNotEmpty(entry.getValue().toString())) {
				values.add(entry.getValue());
				keys.add(entry.getKey());
			}
		}
		
		String sql = getBaseInsertSql(keys.toArray());
		if (isAutoKey) {
			return simpleJdbcTemplate.insert(sql, this.pkey, values.toArray());
		} else {
			simpleJdbcTemplate.update(sql, values.toArray());
			return Long.valueOf(entityMap.get(this.pkey).toString());
		}
	}

	@Override
	public int[] batchInsert(List<Map<String, Object>> entityList) {
		if (entityList == null || entityList.size() == 0) {
			return null;
		}
		Object[] keys = entityList.get(0).keySet().toArray();
		String sql = getBaseInsertSql(keys);
		return simpleJdbcTemplate.batchUpdate(sql, keys, entityList);
	}

	@Override
	public int updateByPkey(String column, Object columnVal, Object keyVal) {
		return updateByKey(column, columnVal, getPkey(), keyVal);
	}
	
	@Override
	public int updateByKey(String column, Object columnVal, String key, Object keyVal) {
		return update(column, columnVal, new QueryBulider().eq(key, keyVal, true));
	}
	
	@Override
	public int update(String column, Object columnVal, Map<String, Object> paramMap) {
		return update(column, columnVal, new QueryBulider().allEq(paramMap, true));
	}

	@Override
	public int update(String column, Object columnVal, QueryBulider queryBulider) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(table).append(" SET `").append(column).append("`= ? ,`update_datetime`=now() WHERE 1=1");
		values.add(columnVal);
		return simpleJdbcTemplate.update(sql.toString(), values, queryBulider);
	}
	
	@Override
	public int updateByPkey(Map<String, Object> entityMap, Object val) {
		return updateByKey(entityMap, getPkey(), val);
	}
	
	@Override
	public int updateByKey(Map<String, Object> entityMap, String key, Object val) {
		return update(entityMap, new QueryBulider().eq(key, val, true));
	}
	
	@Override
	public int update(Map<String, Object> entityMap, Map<String, Object> paramMap) {
		return update(entityMap, new QueryBulider().allEq(paramMap, true));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int update(Map<String, Object> entityMap, QueryBulider queryBulider) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(table).append(" SET ");
		Iterator iterator = entityMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			if (entry.getValue() != null) {
				sql.append("`").append(entry.getKey()).append("`= ? ,");
				values.add(entry.getValue());
			}
		}
		sql.append("`update_datetime`=now() WHERE 1=1 ");
		return simpleJdbcTemplate.update(sql.toString(), values, queryBulider);
	}

	@Override
	public int deleteByPkey(Object val) {
		return deleteByKey(getPkey(), val);
	}

	@Override
	public int deleteByKey(String key, Object val) {
		return delete(new QueryBulider().eq(key, val, true));
	}

	@Override
	public int delete(Map<String, Object> paramMap) {
		return delete(new QueryBulider().allEq(paramMap, true));
	}

	@Override
	public int delete(QueryBulider queryBulider) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(table).append(" WHERE 1=1 ");
		return simpleJdbcTemplate.update(sql.toString(), queryBulider);
	}

	@Override
	public List<Map<String, Object>> listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return listPage(pageParam, paramMap, null);
	}

	@Override
	public List<Map<String, Object>> listPage(PageParam pageParam, Map<String, Object> paramMap, String fields) {
		return listPage(pageParam, new QueryBulider().allEq(paramMap), fields);
	}

	@Override
	public List<Map<String, Object>> listPage(PageParam pageParam, QueryBulider queryBulider) {
		return listPage(pageParam, queryBulider, null);
	}

	@Override
	public List<Map<String, Object>> listPage(PageParam pageParam, QueryBulider queryBulider, String fields) {
		return simpleJdbcTemplate.queryForPage(getBaseSelectSql(fields), pageParam, queryBulider);
	}

	@Override
	public List<Map<String, Object>> listByKey(String key, Object val) {
		return listByKey(key, val, null);
	}

	@Override
	public List<Map<String, Object>> listByKey(String key, Object val, String fields) {
		return listBy(new QueryBulider().eq(key, val, true), fields);
	}

	@Override
	public List<Map<String, Object>> listBy(Map<String, Object> paramMap) {
		return listBy(paramMap, null);
	}

	@Override
	public List<Map<String, Object>> listBy(Map<String, Object> paramMap, String fields) {
		return listBy(new QueryBulider().allEq(paramMap), fields);
	}

	@Override
	public List<Map<String, Object>> listBy(QueryBulider queryBulider) {
		return listBy(queryBulider, null);
	}

	@Override
	public List<Map<String, Object>> listBy(QueryBulider queryBulider, String fields) {
		return simpleJdbcTemplate.queryForList(getBaseSelectSql(fields), queryBulider);
	}

	@Override
	public Map<String, Object> getByPkey(Object val) {
		return getByPkey(val, null);
	}

	@Override
	public Map<String, Object> getByPkey(Object val, String fields) {
		return getByKey(getPkey(), val, fields);
	}

	@Override
	public Map<String, Object> getByKey(String key, Object val) {
		return getByKey(key, val, null);
	}

	@Override
	public Map<String, Object> getByKey(String key, Object val, String fields) {
		return getBy(new QueryBulider().eq(key, val, true), fields);
	}

	@Override
	public Map<String, Object> getBy(Map<String, Object> paramMap) {
		return getBy(paramMap, null);
	}

	@Override
	public Map<String, Object> getBy(Map<String, Object> paramMap, String fields) {
		return getBy(new QueryBulider().allEq(paramMap), fields);
	}

	@Override
	public Map<String, Object> getBy(QueryBulider queryBulider) {
		return getBy(queryBulider, null);
	}

	@Override
	public Map<String, Object> getBy(QueryBulider queryBulider, String fields) {
		return simpleJdbcTemplate.queryForMap(getBaseSelectSql(fields), queryBulider);
	}

	@Override
	public Long countByKey(String key, Object val) {
		return count(new QueryBulider().eq(key, val, true));
	}

	@Override
	public Long count(Map<String, Object> paramMap) {
		return count(new QueryBulider().allEq(paramMap));
	}

	@Override
	public Long count(QueryBulider queryBulider) {
		return simpleJdbcTemplate.queryForCount(getBaseSelectSql("count(1)"), queryBulider);
	}

	protected String getBaseSelectSql(String fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		if (StringUtils.isNotEmpty(fields)) {
			sql.append(fields);
		} else {
			sql.append("*");
		}
		sql.append(" FROM ").append(table).append(" WHERE 1=1 ");
		return sql.toString();
	}

	protected String getBaseInsertSql(Object[] keys){
		StringBuilder sql = new StringBuilder();
		StringBuilder paramKeys = new StringBuilder();
		StringBuilder paramVals = new StringBuilder();
		sql.append("INSERT INTO ").append(table);
		for (Object key : keys) {
			paramKeys.append("`").append(key).append("`,");
			paramVals.append("? ,");
		}
		paramKeys.append("`create_datetime`,").append("`update_datetime`");
		paramVals.append("now(),now()");
		sql.append("(").append(paramKeys).append(") VALUES(").append(paramVals).append(")");
		return sql.toString();
	}
}