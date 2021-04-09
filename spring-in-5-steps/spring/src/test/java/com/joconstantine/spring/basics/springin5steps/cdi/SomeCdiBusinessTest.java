package com.joconstantine.spring.basics.springin5steps.cdi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//Load the context
@ExtendWith(MockitoExtension.class)
class SomeCdiBusinessTest {
	
	//Inject mock
	@InjectMocks
	SomeCdiBusiness business;
	
	//Create mock
	@Mock
	SomeCdiDao daoMock;
	
	@Test
	void testBasicScenario() {
		
		//when daoMock.getData() method is called, return int[] {2, 4}
		Mockito.when(daoMock.getData()).thenReturn(new int[] {2, 4});
		
		//call method on binarySearch
		int actualResult = business.findGreatest();
		
		//check if the value is correct
		assertEquals(4, actualResult);		
	}
	
	@Test
	void testBasicScenario2() {
		
		Mockito.when(daoMock.getData()).thenReturn(new int[] {});
		
		//call method on binarySearch
		int actualResult = business.findGreatest();
		
		//check if the value is correct
		assertEquals(Integer.MIN_VALUE, actualResult);		
	}
	
	@Test
	void testBasicScenario3() {
		
		Mockito.when(daoMock.getData()).thenReturn(new int[] {2, 2});
		
		//call method on binarySearch
		int actualResult = business.findGreatest();
		
		//check if the value is correct
		assertEquals(2, actualResult);		
	}

}
