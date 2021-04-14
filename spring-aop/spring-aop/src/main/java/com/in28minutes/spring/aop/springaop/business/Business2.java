package com.in28minutes.spring.aop.springaop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.spring.aop.springaop.aspect.TrackTime;
import com.in28minutes.spring.aop.springaop.data.Dao2;

@Service
public class Business2 {
	
	@Autowired
	private Dao2 dao2;
	
	@TrackTime
	public String calculateSomething() {
		//Business
		return dao2.retrieveSomething();
	}
}
