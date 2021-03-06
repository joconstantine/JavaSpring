package com.in28minutes.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
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
import javax.persistence.PreRemove;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(
		value = {
				@NamedQuery(name="query_get_all_courses", query="select c From Course c"),
				@NamedQuery(name="query_get_100_Step_courses",
					query="Select c From Course c where name like '%100 steps'"),
				@NamedQuery(name="query_get_all_courses_join_fetch", 
					query="select c From Course c LEFT JOIN FETCH c.students s"),
		})
@Cacheable
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<Student>();
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}
	
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
