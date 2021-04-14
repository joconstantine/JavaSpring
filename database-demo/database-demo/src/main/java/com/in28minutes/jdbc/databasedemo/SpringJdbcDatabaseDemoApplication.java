package com.in28minutes.jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jdbc.databasedemo.entity.Person;
import com.in28minutes.jdbc.databasedemo.jdbc.PersonJbdcDao;

//@SpringBootApplication
public class SpringJdbcDatabaseDemoApplication implements CommandLineRunner {

	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJbdcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("All users -> {}", dao.findAll());	
		LOGGER.info("User id 10001 -> {}", dao.findById(10001));	
		LOGGER.info("Birth Year 2021 -> {}", dao.findByBirthYear(2021));
		LOGGER.info("Delete user id 10002 -> {}", dao.deleteById(10002));
		LOGGER.info("All users -> {}", dao.findAll());
		LOGGER.info("Inserting 10004 -> {}", 
				dao.insert(new Person(10004, "Tara", "Berlin", new Date())));
		LOGGER.info("All users -> {}", dao.findAll());
		LOGGER.info("Inserting 10003 -> {}", 
				dao.update(new Person(10003, "Pieter", "Utrecht", new Date())));
		LOGGER.info("All users -> {}", dao.findAll());
		
	}
	

}
