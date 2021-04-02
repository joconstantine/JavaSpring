package com.joconstantine.spring.basics.springin5steps.basic;

import org.springframework.stereotype.Service;

@Service
public interface SortAlgorithm {
	public int[] sort(int[] numbers);
}
