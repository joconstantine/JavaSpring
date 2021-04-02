package com.joconstantine.spring.basics.springin5steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.joconstantine.spring.basics.springin5steps.xml.XmlPersonDAO;

public class SpringIn5StepsXmlContextApplication {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SpringIn5StepsXmlContextApplication.class);

	public static void main(String[] args) {
		//BinarySearchImpl binarySearch = 
		//	new BinarySearchImpl(new QuickSortAlgorithm());

		try (ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml")){
			//SpringApplication.run(SpringIn5StepsBasicApplication.class, args);
			
			LOGGER.info("Beans loaded => {}", (Object) applicationContext.getBeanDefinitionNames());
			
			XmlPersonDAO personDao = 
					applicationContext.getBean(XmlPersonDAO.class);

			LOGGER.info("{} {}", personDao, personDao.getXmlJdbcConnection());
		}
	}

}
