package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.ReviewRating;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class CourseRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}
	
	@Transactional
	void findById_basic_firstLevelCacheDemo() {
		Course course = repository.findById(10001L);
		logger.info("First Course Retrieve -> {}", course);
		
		assertEquals("JPA in 50 steps", course.getName());
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieve -> {}", course1);
	}
	
	@Test
	void deleteById_basic() {
		Course course = repository.findById(10002L);
		assertEquals("Spring in 50 Steps", course.getName());
		repository.deleteById(10002L);
		course = repository.findById(10002L);
		assertNull(course);
	}
	
	void save_basic() {
		repository.save(new Course("Microservices in 100 steps"));
		Course course = repository.findById(1L);
		assertEquals("Microservices in 100 steps", course.getName());
	}
	
	void playWithEntityManager() {
		repository.playWithEntityManager();
	}
	
	void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}", course.getReviews());
	}
	
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}
	
	@Test
	@Transactional
	void addReviewToCourse() {
		Course course = repository.findById(10002L);
		assertEquals("Spring in 50 Steps", course.getName());
		Review review = new Review(ReviewRating.THREE, "This is so so");
		course.addReview(review);
		review.setCourse(course);
		em.persist(course);		
		em.persist(review);
		em.flush();
	}

}

