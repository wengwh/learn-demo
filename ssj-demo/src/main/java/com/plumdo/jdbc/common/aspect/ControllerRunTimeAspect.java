package com.plumdo.jdbc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wengwenhui
 *
 */
@Component
@Aspect
public class ControllerRunTimeAspect {
	private static final Logger logger = LoggerFactory.getLogger(ControllerRunTimeAspect.class);

	@Pointcut("execution(* com.plumdo.jdbc.controller.*.*.*(..))")
	public void myAspect() {
	}

	@Around("myAspect()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result;
		Long start = System.currentTimeMillis();
		try {
			result = pjp.proceed();
		} finally {
			Long end = System.currentTimeMillis();
			logger.info("{}>>>>>run time:{}", pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "()", (end - start));
		}
		return result;
	}

}
