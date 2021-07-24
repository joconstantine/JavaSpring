package com.luv2code.springdemo;

public class MyApp {

	public static void main(String[] args) {
		// create the project
		Coach theCoach = new TrackCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());
	}

}
