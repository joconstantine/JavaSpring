package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String number;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
	private Student student;
	
	protected Passport() {}
	
	public Passport(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("Passport [%s]", number);
	}
}
