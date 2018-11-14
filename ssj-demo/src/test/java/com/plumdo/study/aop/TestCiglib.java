package com.plumdo.study.aop;
public class TestCiglib {  
    public static void main(String[] args) {  
        CglibProxy proxy = new CglibProxy();  
        Hello hello = (Hello) proxy.getProxyInstance(new HelloImpl());  
        System.out.println(hello.sayHello("Leon"));  

        PrintClass.printClassDefinition(hello.getClass());
        
        UserDaoImpl userDao = (UserDaoImpl) proxy.getProxyInstance(new UserDaoImpl());  
        userDao.login("Leon", "1234");  
        System.out.println(userDao.getClass().getSuperclass());//看动态代理实例的父类  
    }  
} 