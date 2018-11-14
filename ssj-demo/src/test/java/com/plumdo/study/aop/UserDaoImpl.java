package com.plumdo.study.aop;
public class UserDaoImpl implements UserDao {  
    @Override  
    public boolean login(String username, String password) {  
        String user = "("+username+","+password+")";  
        System.out.println(this.getClass().getName()+"-> processing login:"+user);  
        return true;  
    }  
} 