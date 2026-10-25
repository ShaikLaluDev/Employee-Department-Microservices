package com.stackly.employee.exceptionhandling;

public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
