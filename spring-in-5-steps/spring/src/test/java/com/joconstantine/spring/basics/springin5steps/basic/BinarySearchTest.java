package com.joconstantine.spring.basics.springin5steps.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joconstantine.spring.basics.springin5steps.SpringIn5StepsBasicApplication;

//Load the context
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=SpringIn5StepsBasicApplication.class)
class BinarySearchTest {
	
	//Get this bean from the context
	@Autowired
	BinarySearchImpl binarySearch;

	@Test
	void testBasicScenario() {
		
		//call method on binarySearch
		int actualResult = binarySearch.binarySearch(new int[] {}, 5);
		
		//check if the value is correct
		assertEquals(3, actualResult);		
	}

}
