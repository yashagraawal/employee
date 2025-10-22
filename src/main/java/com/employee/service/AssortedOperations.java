package com.employee.service;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AssortedOperations {
	
	public String generateEmpNumber() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toUpperCase();
	}
}
