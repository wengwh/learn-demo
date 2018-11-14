package com.plumdo.jdbc.common.param;


/**
 * 
 * @author wengwenhui
 *
 */
public class PageOutputParmImpl extends BaseOutputParmImpl implements BaseOutputParm {
	private Object data = null;
	private Object total = null;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getTotal() {
		return total;
	}

	public void setTotal(Object total) {
		this.total = total;
	}

	public void success(Object data, Object total) {
		this.data = data;
		this.total = total;
	}


	@Override
	public Object toJson() {
		logger.info("接口返回信息： {}",this);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				"ret=" + getRet() +
				", msg='" + getMsg() + '\'' +
				", data=" + data +
				", total=" + total +
				'}';
	}
}
