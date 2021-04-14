package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
class NativeQueriesTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	void native_queries_basic() {
		Query createQuery = em.createNativeQuery("Select * From Course", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select * From Course -> {}", resultList);
	}
	
	void native_queries_with_parameter() {
		Query createQuery = em.createNativeQuery("Select * From Course where id=?", Course.class);
		createQuery.setParameter(1, 10001L);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select * From Course where id=? -> {}", resultList);
	}
	
	@Test
	void native_queries_with_named_parameter() {
		Query createQuery = em.createNativeQuery("Select * From Course where id=:id", Course.class);
		createQuery.setParameter("id", 10001L);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select * From Course where id=? -> {}", resultList);
	}
	
	@Test
	@Transactional
	void native_queries_to_update() {
		Query createQuery = em.createNativeQuery("Update Course set last_updated_date=systimestamp()", Course.class);
		int noOfRowsUpdated = createQuery.executeUpdate();
		logger.info("Update Course where set last_updated_date=systimestamp() -> {}", noOfRowsUpdated);
	}
}
