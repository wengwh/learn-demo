package com.plumdo.jdbc.common.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.plumdo.jdbc.common.param.PageParam;

/**
 * 
 * @author wengwenhui
 * 
 */
@Component
public class SimpleJdbcTemplate {

	private Logger logger = LoggerFactory.getLogger(SimpleJdbcTemplate.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int update(String sql, QueryBulider queryBulider) {
		return update(getContactSql(sql, queryBulider), queryBulider.getArgsToArray());
	}
	
	public int update(String sql, List<Object> values, QueryBulider queryBulider) {
		values.addAll(queryBulider.getArgs());
		return update(getContactSql(sql, queryBulider), values.toArray());
	}

	public int update(String sql, Object... args) {
		logger.info("SQL语句： {}", sql);
		logger.info("参数： {}", ArrayUtils.toString(args));
		return jdbcTemplate.update(sql, args);
	}

	public Long insert(final String sql, final String pkey, final Object... args) {
		logger.info("SQL语句： {}", sql);
		logger.info("参数： {}", ArrayUtils.toString(args));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { pkey });
				int i = 1;
				for (Object o : args) {
					ps.setObject(i++, o);
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	public int[] batchUpdate(String sql, final Object[] keys, final List<Map<String, Object>> entityList) {
		logger.info("批量操作，SQL语句： {}", sql);
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				Map<String, Object> map = entityList.get(i);
				for (int j = 0; j < keys.length; j++) {
					preparedStatement.setObject(j + 1, map.get(keys[j]));
				}
				logger.info("批量操作，参数： {}", map.values());
			}

			@Override
			public int getBatchSize() {
				return entityList.size();
			}
		});
	}

	public void execute(String sql) {
		logger.info("SQL语句： {}", sql);
		jdbcTemplate.execute(sql);
	}

	public Map<String, Object> queryForMap(String sql, QueryBulider queryBulider) {
		return queryForMap(sql, queryBulider, "");
	}

	public Map<String, Object> queryForMap(String startSql, QueryBulider queryBulider, String endSql) {
		return queryForMap(getContactSql(startSql, queryBulider, endSql), queryBulider.getArgsToArray());
	}

	public Map<String, Object> queryForMap(String sql, Object... args) {
		logger.info("SQL语句： {}", sql);
		logger.info("参数： {}", ArrayUtils.toString(args));
		return jdbcTemplate.queryForMap(sql, args);
	}

	public List<Map<String, Object>> queryForList(String sql, QueryBulider queryBulider) {
		return queryForList(sql, queryBulider, "");
	}

	public List<Map<String, Object>> queryForList(String startSql, QueryBulider queryBulider, String endSql) {
		return queryForList(getContactSql(startSql, queryBulider, endSql), queryBulider.getArgsToArray());
	}

	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		logger.info("SQL语句： {}", sql);
		logger.info("参数： {}", ArrayUtils.toString(args));
		return jdbcTemplate.queryForList(sql, args);
	}

	public Long queryForCount(String sql, QueryBulider queryBulider) {
		return queryForCount(sql, queryBulider, "");
	}

	public Long queryForCount(String startSql, QueryBulider queryBulider, String endSql) {
		return queryForCount(getContactSql(startSql, queryBulider, endSql), queryBulider.getArgsToArray());
	}
	
	public Long queryForCount(String sql, Object... args) {
		return queryForObject(sql, Long.class, args);
	}

	public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
		logger.info("SQL语句： {}", sql.toString());
		logger.info("参数： {}", ArrayUtils.toString(args));
		return jdbcTemplate.queryForObject(sql.toString(), requiredType, args);
	}

	public List<Map<String, Object>> queryForPage(String sql, PageParam pageParam, QueryBulider queryBulider) {
		return queryForPage(sql, pageParam, queryBulider, "");
	}

	public List<Map<String, Object>> queryForPage(String startSql, PageParam pageParam, QueryBulider queryBulider, String endSql) {
		return queryForPage(getContactSql(startSql, queryBulider, endSql), pageParam, queryBulider.getArgsToArray());
	}
	
	public List<Map<String, Object>> queryForPage(String sql, PageParam pageParam, Object... args) {
		StringBuilder countSql = new StringBuilder();
		countSql.append("SELECT COUNT(1) AS TOTAL FROM (").append(sql).append(") temp ");

		pageParam.setTotal(queryForCount(countSql.toString(), args));

		if (pageParam.getTotal() <= 0) {
			return Collections.emptyList();
		} else {
			StringBuilder pageSql = new StringBuilder();
			pageSql.append("SELECT temp.* FROM (").append(sql).append(") temp ");

			if (pageParam.getOrderBySql() != null) {
				pageSql.append(" ORDER BY ").append(pageParam.getOrderBySql());
			}
			if (pageParam.getPage() > 0 && pageParam.getSize() > 0) {
				pageSql.append(" LIMIT ").append(pageParam.getSkipNum()).append(",").append(pageParam.getSize());
			}
			return queryForList(pageSql.toString(), args);
		}
	}
	
	private String getContactSql(String startSql, QueryBulider queryBulider){
		return getContactSql(startSql, queryBulider, "");
	}
	
	private String getContactSql(String startSql, QueryBulider queryBulider, String endSql){
		StringBuilder sql = new StringBuilder();
		sql.append(startSql).append(queryBulider.getSql()).append(endSql);
		return sql.toString();
	}
}