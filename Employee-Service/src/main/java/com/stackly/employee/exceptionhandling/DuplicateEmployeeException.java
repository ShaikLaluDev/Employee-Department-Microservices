package com.stackly.employee.exceptionhandling;

public class DuplicateEmployeeException extends RuntimeException{

	public DuplicateEmployeeException(String message) {
		super(message);
	}
}
