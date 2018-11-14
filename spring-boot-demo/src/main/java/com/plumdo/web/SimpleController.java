package com.plumdo.web;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 
 * @author wengwenhui
 *
 */
@RestController
public class SimpleController {
 
    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public String hello(){
		throw new RuntimeException();
    }
   
}