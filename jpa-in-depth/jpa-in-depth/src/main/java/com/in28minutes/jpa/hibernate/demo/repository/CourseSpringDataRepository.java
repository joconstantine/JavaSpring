package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
	public List<Course> findByName(String name);
	
	public int countByName(String name);
	
	public List<Course> findByNameAndId(String name, long id);
	
	public List<Course> findByNameOrderByIdDesc(String name);
	
	@Query("Select c From Course c where name like '%100 steps'")
	List<Course> courseWith100StepsInName();
	
	@Query(value="Select * From Course c where name like '%100 steps'", 
			nativeQuery=true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();
	
	@Query(name="query_get_100_Step_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
	
}
