package com.in28minutes.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class SomeBusinessMockTest {

	@Test
	void testFindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		//dataServiceMock.retrieveAllData() => new int[] {24, 15, 3};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 15, 3});
		
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	@Test
	void testFindTheGreatestFromAllData_ForOneValue() {
		DataService dataServiceMock = mock(DataService.class);
		//dataServiceMock.retrieveAllData() => new int[] {24, 15, 3};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {15});
		
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(15, result);
	}

}
