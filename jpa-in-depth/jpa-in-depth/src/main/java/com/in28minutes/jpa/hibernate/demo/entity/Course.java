package com.in28minutes.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(
		value = {
				@NamedQuery(name="query_get_all_courses", query="select c From Course c"),
				@NamedQuery(name="query_get_100_Step_courses",
				query="Select c From Course c where name like '%100 steps'")
		})
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course", fetch=FetchType.EAGER)
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<Student>();
		
	protected Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Course [%s]", name);
	}
}
