package com.joconstantine.spring.basics.springin5steps.xml;

import org.springframework.stereotype.Component;

@Component
public class XmlJdbcConnection {
	public XmlJdbcConnection() {
		System.out.println("JDBC Connection");
	}
}
