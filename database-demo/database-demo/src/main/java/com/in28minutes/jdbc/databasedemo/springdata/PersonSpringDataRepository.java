package com.in28minutes.jdbc.databasedemo.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.jdbc.databasedemo.entity.Person;

public interface PersonSpringDataRepository 
	extends JpaRepository<Person, Integer>{

}
