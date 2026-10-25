package com.stackly.employee.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name ="Employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="emp_id",unique = true)
	private String empId;
	
	@Column(nullable = false,name="emp_name")
	private String empName;
	
	@Column(nullable = false,name="emp_email",unique = true)
	private String empEmail;
	
	@Column(nullable = false,name="emp_contact",unique = true,length = 10)
	private String empContact;
	
	@Column(nullable = false,name="emp_salary",precision = 12, scale = 2)
	private BigDecimal empSalary;
	
	@Column(nullable = false,name="department_id")
	private Integer departmentId;

}
