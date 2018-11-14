package com.plumdo.jdbc.common.param;


/**
 * 
 * @author wengwenhui
 *
 */
public class DataOutputParmImpl extends BaseOutputParmImpl implements BaseOutputParm {
	private Object data = null;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void success(Object data) {
		this.data = data;
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
				'}';
	}
}
