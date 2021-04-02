package com.in28minutes.junit;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyMathTest {
	MyMath myMath = new MyMath();
	
	@BeforeAll
	public static void before() {
		System.out.println("Before");
	}
	
	@AfterAll
	public static void after() {
		System.out.println("After");
	}
	
	@BeforeEach
	public void beforeClass() {
		System.out.println("BeforeClass");
	}
	
	@AfterEach
	public void afterClass() {
		System.out.println("AfterClass");
	}

	@Test
	void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.sum(new int[] {1, 2, 3}));
	}
	
	@Test
	void sum_with1number1() {
		System.out.println("Test1");
		assertEquals(1, myMath.sum(new int[] {1}));
	}

}
