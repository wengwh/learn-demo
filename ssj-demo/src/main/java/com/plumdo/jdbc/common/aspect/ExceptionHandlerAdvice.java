package com.plumdo.jdbc.common.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plumdo.jdbc.common.exception.BaseException;
import com.plumdo.jdbc.common.exception.ConvertException;
import com.plumdo.jdbc.common.exception.ForbiddenException;
import com.plumdo.jdbc.common.exception.HttpFailException;
import com.plumdo.jdbc.common.exception.InputParameterException;
import com.plumdo.jdbc.common.exception.ObjectNotFoundException;
import com.plumdo.jdbc.common.param.BaseOutputParmImpl;

/**
 * 异常全局捕获
 * 
 * @author wengwh
 * 
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(ForbiddenException.class)
	@ResponseBody
	public Object handleForbidden(ForbiddenException e) {
		LOGGER.error("Operate is forbidden", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}

	@ExceptionHandler(InputParameterException.class)
	@ResponseBody
	public Object InputParameter(InputParameterException e) {
		LOGGER.error("input parameter is error", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseBody
	public Object handleObjectNotFound(ObjectNotFoundException e) {
		LOGGER.error("object is not found", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}

	@ExceptionHandler(ConvertException.class)
	@ResponseBody
	public Object handleConvert(ConvertException e) {
		LOGGER.error("convert fail ", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}

	@ExceptionHandler(HttpFailException.class)
	@ResponseBody
	public Object handleHttpFail(HttpFailException e) {
		LOGGER.error("send http fail", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}
	
	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public Object handleBaseException(BaseException e) {
		LOGGER.error("base exception", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail(e.getMessage());
		return returnParm.toJson();
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleOtherException(Exception e) {
		LOGGER.error("Unhandled exception", e);
		BaseOutputParmImpl returnParm = new BaseOutputParmImpl();
		returnParm.fail("内部异常");
		return returnParm.toJson();
	}

}
