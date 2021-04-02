package com.joconstantine.spring.basics.springin5steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.joconstantine.spring.basics.componentscan.ComponentDAO;

@Configuration
@ComponentScan("com.joconstantine.spring.basics.componentscan")
public class SpringIn5StepsComponentScanApplication {
	
	private static Logger LOGGER = 
			LoggerFactory.getLogger(SpringIn5StepsComponentScanApplication.class);

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(SpringIn5StepsComponentScanApplication.class);
		
		ComponentDAO componentDao = 
				applicationContext.getBean(ComponentDAO.class);
		
		
		applicationContext.close();
		
		LOGGER.info("{}", componentDao);
		
	}

}
