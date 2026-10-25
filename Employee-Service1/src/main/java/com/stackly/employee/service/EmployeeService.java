package com.stackly.employee.service;

import java.util.List;

import com.stackly.employee.requestdto.EmployeeRequestDto;
import com.stackly.employee.responsedto.EmployeeResponseDto;

public interface EmployeeService {

	EmployeeResponseDto saveOrUpdateEmployee(
			Integer id,
			EmployeeRequestDto requestDto
			);
	
	String deleteEmployee(String empId);

	List<EmployeeResponseDto> readEmployees();
}
