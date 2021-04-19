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
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class JPQLTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	void query_basic() {
		Query createQuery = em.createQuery("Select c From Course c");
		List resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void query_typed() {
		TypedQuery<Course> createQuery = 
				em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void jpql_where() {
		TypedQuery<Course> createQuery = 
				em.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void jpql_courses_without_students() {
		TypedQuery<Course> createQuery = 
				em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> createQuery = 
				em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void jpql_courses_ordered_by_students() {
		TypedQuery<Course> createQuery = 
				em.createQuery("Select c from Course c order by size(c.students)", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> createQuery = 
				em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = createQuery.getResultList();
		logger.info("Select s from Students s -> {}", resultList);
	}
	
	void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size -> {}", resultList.size());
		for (Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	void left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size -> {}", resultList.size());
		for (Object[] result: resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}
	
	@Test
	void cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size -> {}", resultList.size());
		for (Object[] result: resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}
}
