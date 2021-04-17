package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;	
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public List<Student> findAllStudents() {
	    return em.createQuery("SELECT a FROM Student a", Student.class).getResultList();      
	}
	
	public Student save(Student student) {
		//insert or update
		if (student.getId() == null) {
			//insert
			em.persist(student);
		} else {
			//update
			em.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someDummyOperation() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		
		//Database Operation 2 - Retrieve student
		Passport passport = student.getPassport();
		
		//Database Operation 3 - Retrieve student
		passport.setNumber("E123457");
		
		//Database Operation 4 - Retrieve student
		student.setName("Ranga - updated");
	}
	
	public void insertStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		em.persist(student);
	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		em.persist(student);
	}
}
