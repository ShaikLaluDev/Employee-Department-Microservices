package com.stackly.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackly.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	
	boolean existsByEmpEmail(String empEmail);
	
	boolean existsByEmpContact(String empContact);
	
	boolean existsByEmpEmailAndIdNot(String empEmail,Integer Id);
	
	boolean existsByEmpContactAndIdNot(String empContact,Integer Id);
	
	boolean existsByEmpId(String empId);
	
	Optional<Employee> findByEmpId(String empId);
}
