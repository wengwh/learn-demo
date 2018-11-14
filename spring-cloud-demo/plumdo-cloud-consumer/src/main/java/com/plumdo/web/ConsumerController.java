package com.plumdo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.plumdo.service.ComputeClient;

@RestController
public class ConsumerController {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String add() {
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/users?a=10&b=20", String.class).getBody();
	}

	public String addServiceFallback() {
		return "error222";
	}

	@Autowired
	ComputeClient computeClient;

	@RequestMapping(value = "/users-feign", method = RequestMethod.GET)
	public String users() {
		return computeClient.hello().toString();
	}
}