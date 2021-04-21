package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
class CourseSpringDataRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		logger.info("{}", courseOptional.isPresent());
	}
	
	void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		logger.info("{}", courseOptional.isPresent());
	}
	
	public void playingAroundWithSpringDataRepository() {
		Course course = new Course("Microservices in 100 Steps");
		repository.save(course);
		course.setName("Microservices in 100 Steps - Updated");
		repository.save(course);
		
		logger.info("Courses -> {}", repository.findAll());
		logger.info("Courses -> {}", repository.count());
	
	}
	
	public void sort() {
		Course course = new Course("Microservices in 100 Steps");
		repository.save(course);
		course.setName("Microservices in 100 Steps - Updated");
		
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		logger.info("Courses -> {}", repository.findAll(sort));
	
	}
	
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("Courses -> {}", firstPage);
		logger.info("First Page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		
		logger.info("Second Page -> {}", secondPage.getContent());
	}
	
	@Test
	public void findUsingName() {		
		logger.info("FindByName -> {}", repository.findByName("JPA in 50 Steps"));
		logger.info("CountByName -> {}", repository.countByName("JPA in 50 Steps"));
		logger.info("FindByNameAndId -> {}", repository.findByNameAndId("JPA in 50 Steps", 10001L));
	}
}
