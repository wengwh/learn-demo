package com.plumdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApplicationB {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplicationB.class, args);
	}
}