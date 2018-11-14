package com.plumdo.jdbc.common.param;

import java.util.List;

/**
 * 
 * @author wengwenhui
 * 
 */
public class PageParam {

	private int page = 1; // 当前页数
	private int size = 10; // 每页记录数
	private List<String> sort;
	private List<String> order;
	private Long total; // 总记录数

	public PageParam() {
	}

	public PageParam(int page) {
		this.page = page;
	}

	public List<String> getSort() {
		return sort;
	}

	public String getFirstSort(){
		if(sort !=null && sort.size()>0){
			return sort.get(0);
		}
		return null;
	}
	
	public void setSort(List<String> sort) {
		this.sort = sort;
	}
	
	public String getFirstOrder(){
		if(order !=null && order.size()>0){
			return order.get(0);
		}
		return null;
	}
	
	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSkipNum() {
		return (page - 1) * size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getOrderBySql(){
		if(this.sort!=null && this.sort.size()>0 
				&& this.order!=null && this.order.size()>0
				&& this.sort.size() == this.order.size()){
			StringBuilder orderBy = new StringBuilder();
			for(int i=0; i<sort.size();i++){
				orderBy.append(sort.get(i)).append(" ").append(order.get(i)).append(",");
			}
			orderBy.delete(orderBy.length()-1, orderBy.length());
			return orderBy.toString();
		}
		return null;
	}
}