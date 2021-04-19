package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
class CriteriaQueryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	void jpql_basic() {
		//"Select c From Course c"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> createQuery = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("TypedQuery -> {}", resultList);
	}
	
	void all_courses_having_100Steps() {
		//"Select c From Course c"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		//criteria (like)
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
		cq.where(like100Steps);
		
		TypedQuery<Course> createQuery = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("TypedQuery -> {}", resultList);
	}
	
	void all_courses_without_students() {
		//"Select c From Course c"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		//criteria (like)
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		cq.where(studentsIsEmpty);
		
		TypedQuery<Course> createQuery = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("TypedQuery -> {}", resultList);
	}
	
	@Test
	void join() {
		//"Select c, s From Course c join c.students s"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		
		Join<Object, Object> join = courseRoot.join("students");
		
		TypedQuery<Course> createQuery = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("TypedQuery -> {}", resultList);
	}
	
	@Test
	void left_join() {
		//"Select c, s From Course c left join c.students s"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		TypedQuery<Course> createQuery = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = createQuery.getResultList();
		logger.info("TypedQuery -> {}", resultList);
	}
}
