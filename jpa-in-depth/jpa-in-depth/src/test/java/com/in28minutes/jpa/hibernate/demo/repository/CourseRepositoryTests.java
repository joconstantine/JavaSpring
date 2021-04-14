package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class CourseRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;

	void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
	}

	@DirtiesContext
	void deleteById_basic() {
		Course course = repository.findById(10002L);
		assertEquals("Spring in 50 steps", course.getName());
		repository.deleteById(10002L);
		course = repository.findById(10002L);
		assertNull(course);
	}
	
	@DirtiesContext
	void save_basic() {
		repository.save(new Course("Microservices in 100 steps"));
		Course course = repository.findById(1L);
		assertEquals("Microservices in 100 steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}
