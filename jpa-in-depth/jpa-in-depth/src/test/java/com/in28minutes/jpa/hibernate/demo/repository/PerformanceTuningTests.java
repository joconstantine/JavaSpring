package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class PerformanceTuningTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	void creatingNPlusOneProblem() {
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses", Course.class)
				.getResultList();
		
		for(Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	void solvingNPlusOneProblem_EntityGraph() {
		
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);		
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
		
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		
		for(Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
				.getResultList();
		
		for(Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}

}


