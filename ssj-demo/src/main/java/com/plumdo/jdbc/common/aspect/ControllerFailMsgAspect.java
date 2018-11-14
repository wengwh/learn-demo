package com.plumdo.jdbc.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plumdo.jdbc.common.exception.BaseException;

/**
 * 接口异常打印名称
 * @author wengwenhui
 *
 */
@Component
@Aspect
public class ControllerFailMsgAspect {

	@Pointcut("execution(* com.plumdo.jdbc.controller.*.*.*(..))")
	public void myAspect() {
	}

	@Around("myAspect()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object result;
		try {
			result = pjp.proceed();
		}catch(Throwable a){
			if(!(a instanceof BaseException)){
				RequestMapping requestMapping = getRequestMapping(pjp);
				if(requestMapping != null){
					throw new BaseException(requestMapping.name()+"失败", a);
				}
			}
			throw a;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private RequestMapping getRequestMapping(ProceedingJoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                if (clazzs.length == arguments.length) {    
            		 return method.getAnnotation(RequestMapping.class);    
                }  
            }    
         }    
         return null;    
    } 

}
