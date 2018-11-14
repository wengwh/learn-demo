package com.plumdo.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 
 * @author wengwenhui
 *
 */
@RefreshScope
@RestController
public class SimpleController {
 
	@Value("${from}")
    private String from;
	
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
    
    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public String hello(){
		throw new RuntimeException();
    }
   
}