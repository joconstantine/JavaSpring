package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class JPQLTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	void query_basic() {
		Query createQuery = em.createQuery("Select c From Course c");
		List resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void query_typed() {
		TypedQuery<Course> createQuery = 
				em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void jpql_where() {
		TypedQuery<Course> createQuery = 
				em.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
}
