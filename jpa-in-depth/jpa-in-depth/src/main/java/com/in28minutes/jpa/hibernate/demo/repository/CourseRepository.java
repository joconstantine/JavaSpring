package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
public class CourseRepository {

	@Autowired
	EntityManager em;	
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public List<Course> findAllCourses() {
	    return em.createQuery("SELECT a FROM Course a", Course.class).getResultList();      
	}
	
	//public Course save(Course course) -> insert or update
	
	//public void deleteById(Long id) 
}
