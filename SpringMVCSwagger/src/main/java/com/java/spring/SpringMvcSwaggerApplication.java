package com.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class SpringMvcSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcSwaggerApplication.class, args);
	}
}
