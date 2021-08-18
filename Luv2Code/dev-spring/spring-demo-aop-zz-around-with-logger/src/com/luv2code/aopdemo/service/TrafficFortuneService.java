package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortuneService {

	public String getFortune(boolean toThrow) throws RuntimeException {

		// simulate a delay

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (toThrow) {
			throw new RuntimeException("Exception for u....");
		}

		// return a fortune
		return "Expect heavy traffic this morning";
	}

}
