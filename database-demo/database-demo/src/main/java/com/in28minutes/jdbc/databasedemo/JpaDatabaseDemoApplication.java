package com.in28minutes.jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jdbc.databasedemo.entity.Person;
import com.in28minutes.jdbc.databasedemo.jpa.PersonJpaRepository;

//@SpringBootApplication
public class JpaDatabaseDemoApplication implements CommandLineRunner {

	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJpaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		LOGGER.info("All users -> {}", repository.findAll());	
		LOGGER.info("User id 10001 -> {}", repository.findById(1));	
		
//		LOGGER.info("Birth Year 2021 -> {}", repository.findByBirthYear(2021));
//		LOGGER.info("Delete user id 10002 -> {}", repository.deleteById(10002));
//		LOGGER.info("All users -> {}", repository.findAll());
		LOGGER.info("Inserting 10004 -> {}", 
				repository.insert(new Person("Tara", "Berlin", new Date())));
//		LOGGER.info("All users -> {}", repository.findAll());
		LOGGER.info("Inserting 10003 -> {}", 
				repository.update(new Person(1, "Pieter", "Utrecht", new Date())));
		LOGGER.info("User Id 1 -> {}", 
				repository.findById(1));
//		repository.deleteById(1);
//		LOGGER.info("User Id 1 -> {}", 
//				repository.findById(1));
		LOGGER.info("All users -> {}", repository.findAll());
		LOGGER.info("User named Pieter -> {}", repository.findByName("Pieter"));
		
	}
	

}
