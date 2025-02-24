package com.tary.ServiceFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tary.ServiceFlow")
public class ServiceFlowApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceFlowApplication.class, args);
	}
}
