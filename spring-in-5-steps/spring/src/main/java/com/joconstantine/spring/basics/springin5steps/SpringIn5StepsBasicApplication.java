package com.joconstantine.spring.basics.springin5steps;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.joconstantine.spring.basics.springin5steps.basic.BinarySearchImpl;

@Configuration
public class SpringIn5StepsBasicApplication {

	public static void main(String[] args) {
		//BinarySearchImpl binarySearch = 
		//	new BinarySearchImpl(new QuickSortAlgorithm());
		
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(SpringIn5StepsBasicApplication.class);
				//SpringApplication.run(SpringIn5StepsBasicApplication.class, args);
		
		BinarySearchImpl binarySearch = 
				applicationContext.getBean(BinarySearchImpl.class);
		
		BinarySearchImpl binarySearch1 = 
				applicationContext.getBean(BinarySearchImpl.class);
		
		System.out.println(binarySearch);
		System.out.println(binarySearch1);
			
		int result = binarySearch.binarySearch(new int[] {124,  6}, 3);
		System.out.println(result);
	}

}
