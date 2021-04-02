package com.joconstantine.spring.basics.springin5steps;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.joconstantine.spring.basics.springin5steps.properties.SomeExternalService;

@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
public class SpringIn5StepsPropertiesApplication {

	public static void main(String[] args) {
		//BinarySearchImpl binarySearch = 
		//	new BinarySearchImpl(new QuickSortAlgorithm());

		try (AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(SpringIn5StepsBasicApplication.class)){
			//SpringApplication.run(SpringIn5StepsBasicApplication.class, args);

			SomeExternalService service = 
					applicationContext.getBean(SomeExternalService.class);

			System.out.println(service);
			System.out.println(service.returnServiceURL());
		}
	}

}
