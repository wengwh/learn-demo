package com.plumdo.study.aop;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {  
    private Object target;    
      
    public Object getProxyInstance(Object target) {    
        this.target = target;  
        Enhancer enhancer = new Enhancer();    
        enhancer.setSuperclass(this.target.getClass());    
        enhancer.setCallback(this);  // call back method  
        return enhancer.create();  // create proxy instance  
    }    
      
    @Override  
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {  
        System.out.println("before target method...");  
        Object result = proxy.invokeSuper(target, args);  
        System.out.println("after target method...");  
        return result;  
    }  
}  