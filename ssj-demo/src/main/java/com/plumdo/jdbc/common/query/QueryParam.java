package com.plumdo.jdbc.common.query;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author wengwenhui
 * 
 */
public class QueryParam {
	private String column;
	private Object value;
	private QueryType type;
	private boolean isNeed;

	public QueryParam() {
	}

	public QueryParam(String column, Object value) {
		this.column = column;
		this.value = value;
		this.type = QueryType.EQ;
		this.isNeed = false;
	}

	public QueryParam(String column, QueryType type) {
		this.column = column;
		this.type = type;
		this.isNeed = false;
	}

	public QueryParam(String column, Object value, QueryType type) {
		this.column = column;
		this.value = value;
		this.type = type;
		this.isNeed = false;
	}
	public QueryParam(String column, Object value, QueryType type,boolean isNeed) {
		this.column = column;
		this.value = value;
		this.type = type;
		this.isNeed = isNeed;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}

	public boolean isNeed() {
		return isNeed;
	}

	public void setNeed(boolean isNeed) {
		this.isNeed = isNeed;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isNeedContains(){
		if (!this.isNeed() && type != QueryType.NULL && type != QueryType.NOT_NULL) {
			if (value instanceof Collection) {
				List<Object> list = (List<Object>) value;
				if (list == null || list.size() == 0) {
					return false;
				}
			} else {
				if (value==null || StringUtils.isEmpty(value.toString())) {
					return false;
				}
			}
		}
		return true;

	}
	

}