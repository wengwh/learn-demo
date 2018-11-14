package com.plumdo.jdbc.common.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.plumdo.jdbc.common.exception.ForbiddenException;


/**
 * 查询条件构造器
 * 
 * @author wengwenhui
 * 
 */
public class QueryBulider {
	private static final String AND = "AND";
	private static final String OR = "OR";
	private static final String AND_NEW = "AND (";
	private static final String END = ") ";

	private List<QueryParams> allQueryObjects = new ArrayList<QueryParams>();
	private QueryParams currentQueryObject = null;

	private String sql = null;
	private List<Object> args = null;
	private boolean isBuild = false;

	public QueryBulider() {
		currentQueryObject = new QueryParams(this);
		allQueryObjects.add(currentQueryObject);
	}

	public QueryBulider or() {
		if (currentQueryObject.inOrStatement) {
			throw new ForbiddenException("the query is already in an or statement");
		}
		currentQueryObject = new QueryParams(this,true);
		allQueryObjects.add(currentQueryObject);
		return this;
	}

	public QueryBulider endOr() {
		if (!currentQueryObject.inOrStatement) {
			throw new ForbiddenException("endOr() can only be called after calling or()");
		}
		currentQueryObject = new QueryParams(this);
		allQueryObjects.add(currentQueryObject);
		return this;
	}
	
	public QueryBulider remove(){
		currentQueryObject.remove(currentQueryObject.queryParams.size()-1);
		return this;
	}
	
	public QueryBulider where(QueryParam queryParam) {
		currentQueryObject.add(queryParam);
		return this;
	}
	
	public QueryBulider where(String column, Object value, QueryType queryType,boolean isNeed) {
		currentQueryObject.add(new QueryParam(column, value, queryType, isNeed));
		return this;
	}
	
	/**
	 * <p>
	 * 等同于SQL的"field=value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider eq(String column, Object value) {
		return eq(column, value, false);
	}
	
	public QueryBulider eq(String column, Object value, boolean isNeed) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.EQ,isNeed));
		return this;
	}

	/**
	 * <p>
	 * 等同于SQL的"field=value"表达式
	 * </p>
	 * 
	 * @param params
	 * @return
	 */
	public QueryBulider allEq(Map<String, Object> values) {
		return allEq(values, false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QueryBulider allEq(Map<String, Object> values, boolean isNeed) {
		if (values != null) {
			Iterator iterator = values.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
				currentQueryObject.add(new QueryParam(entry.getKey(), entry.getValue(), QueryType.EQ, isNeed));
			}

		}
		return this;
	}
	
	/**
	 * <p>
	 * 等同于SQL的"field=value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider notEq(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.NOT_EQ));
		return this;
	}

	/**
	 * <p>
	 * 等同于SQL的"field>value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider gt(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.GT));
		return this;
	}

	/**
	 * <p>
	 * 等同于SQL的"field>=value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider ge(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.GE));
		return this;
	}

	/**
	 * <p>
	 * 等同于SQL的"field<value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider lt(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.LT));
		return this;
	}

	/**
	 * <p>
	 * 等同于SQL的"field<=value"表达式
	 * </p>
	 * 
	 * @param column
	 * @param params
	 * @return
	 */
	public QueryBulider le(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.LE));
		return this;
	}

	/**
	 * LIKE条件语句，value中前后%
	 * 
	 * @param column
	 *            字段名称
	 * @param value
	 *            匹配值
	 * @return this
	 */
	public QueryBulider like(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.LIKE));
		return this;
	}

	/**
	 * LIKE条件语句，value中前%
	 * 
	 * @param column
	 *            字段名称
	 * @param value
	 *            匹配值
	 * @param type
	 * @return this
	 */
	public QueryBulider likeLeft(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.LIKE_LEFT));
		return this;
	}

	/**
	 * LIKE条件语句，value中后%
	 * 
	 * @param column
	 *            字段名称
	 * @param value
	 *            匹配值
	 * @return this
	 */
	public QueryBulider likeRight(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.LIKE_RIGHT));
		return this;
	}

	/**
	 * is not null 条件
	 * 
	 * @param columns
	 *            字段名称。多个字段以逗号分隔。
	 * @return this
	 */
	public QueryBulider isNotNull(String column) {
		currentQueryObject.add(new QueryParam(column, QueryType.NOT_NULL));
		return this;
	}

	/**
	 * is null 条件
	 * 
	 * @param columns
	 *            字段名称。多个字段以逗号分隔。
	 * @return this
	 */
	public QueryBulider isNull(String column) {
		currentQueryObject.add(new QueryParam(column, QueryType.NULL));
		return this;
	}

	/**
	 * IN 条件语句
	 * 
	 * @param column
	 *            字段名称
	 * @param value
	 *            逗号拼接的字符串
	 * @return this
	 */
	public QueryBulider in(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.IN));
		return this;
	}

	/**
	 * NOT IN条件语句
	 * 
	 * @param column
	 *            字段名称
	 * @param value
	 *            逗号拼接的字符串
	 * @return this
	 */
	public QueryBulider notIn(String column, Object value) {
		currentQueryObject.add(new QueryParam(column, value, QueryType.NOT_IN));
		return this;
	}

	/**
	 * betwwee 条件语句
	 * 
	 * @param column
	 *            字段名称
	 * @param val1
	 * @param val2
	 * @return this
	 */
	public QueryBulider between(String column, String val1, String val2) {
		List<String> value = new ArrayList<String>();
		value.add(val1);
		value.add(val2);
		currentQueryObject.add(new QueryParam(column, value, QueryType.BETWEEN));
		return this;
	}
	
	public String getSql(){
		if(!isBuild){
			buildQueryBuilder();
		}
		return sql;
	}
	
	public List<Object> getArgs(){
		if(!isBuild){
			buildQueryBuilder();
		}
		return args;
	}
	
	public Object[] getArgsToArray(){
		return getArgs().toArray();
	}
	
	public void refresh(){
		this.sql = null;
		this.args = null;
		this.isBuild = false;
	}

	private void buildQueryBuilder() {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sbSql = new StringBuilder();
		String join = null;
		for (QueryParams params : allQueryObjects) {
			join = params.inOrStatement ? AND_NEW : AND;
			for (QueryParam queryParam : params.queryParams) {
				if (queryParam.isNeedContains()) {
					sqlArgsFill(sbSql, values, queryParam, join);
					if (params.inOrStatement) {
						join = OR;
					}
				}
			}
			if (params.inOrStatement && !AND_NEW.equals(join)) {
				sbSql.append(END);
			}
		}
		
		this.sql = sbSql.toString();
		this.args = values;
		this.isBuild = true;
	}

	@SuppressWarnings("unchecked")
	private void sqlArgsFill(StringBuilder sql, List<Object> values, QueryParam queryParam, String join) {
		Object column = queryParam.getColumn();
		Object value = queryParam.getValue();
		QueryType queryType = queryParam.getType();
		switch (queryType) {
		case EQ:
		case NOT_EQ:
		case GT:
		case LT:
		case GE:
		case LE:
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" ? ");
			values.add(value);
			break;
		case LIKE:
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" ? ");
			values.add("%" + value + "%");
			break;
		case LIKE_LEFT:
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" ? ");
			values.add("%" + value);
			break;
		case LIKE_RIGHT:
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" ? ");
			values.add(value + "%");
			break;
		case NULL:
		case NOT_NULL:
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey());
			break;
		case IN:
		case NOT_IN:
			if (value instanceof Collection) {
				List<Object> list = (List<Object>) value;
				sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" ( ");
				for (Object item : list) {
					sql.append(item + ",");
				}
				sql.replace(sql.length() - 1, sql.length(), ") ");
			} else {
				sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(" (").append(value).append(") ");
			}
			break;
		case BETWEEN:
			List<Object> list = (List<Object>) value;
			sql.append(join).append(" ").append(column).append(" ").append(queryType.getKey()).append(list.get(0)).append(" AND ").append(list.get(1)).append(" ");
			break;
		default:
			break;
		}
	}

	private class QueryParams {
		private List<QueryParam> queryParams = new ArrayList<QueryParam>();
		private boolean inOrStatement = false;
		private QueryBulider queryBulider = null;

		public QueryParams(QueryBulider queryBulider) {
			this.queryBulider = queryBulider;
		}

		public QueryParams(QueryBulider queryBulider,boolean inOrStatement) {
			this.queryBulider = queryBulider;
			this.inOrStatement = inOrStatement;
		}

		public void add(QueryParam queryParam) {
			queryParams.add(queryParam);
			queryBulider.refresh();
		}
		
		public void remove(int index) {
			queryParams.remove(index);
			queryBulider.refresh();
		}

	}
}