package com.plumdo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApplicationA {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ServiceApplicationA.class).web(true).run(args);
	}
}