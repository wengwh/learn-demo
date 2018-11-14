package com.plumdo.jdbc.common.param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wengwenhui
 *
 */
public class BaseOutputParmImpl implements BaseOutputParm {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private int ret = 0;
	private String msg = "ok";

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void fail(String msg) {
		this.ret = -1;
		this.msg = msg;
	}

	@Override
	public Object toJson() {
		logger.info("接口返回信息： {}", this);
		return this;
	}

	@Override
	public String toString() {
		return "{" + "ret=" + getRet() + ", msg='" + getMsg() + '\'' + '}';
	}

}
