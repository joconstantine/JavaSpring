package com.in28minutes.springboot.basics.springbootin10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbotIn10StepsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringbotIn10StepsApplication.class, args);
	
		for (String name: applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}
