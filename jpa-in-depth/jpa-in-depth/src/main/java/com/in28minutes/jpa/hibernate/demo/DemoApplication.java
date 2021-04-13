package com.in28minutes.jpa.hibernate.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	CourseRepository reposity;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reposity.findById(10001L);
	}

}
