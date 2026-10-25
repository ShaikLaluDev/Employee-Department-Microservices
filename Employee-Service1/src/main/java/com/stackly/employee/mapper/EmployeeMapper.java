package com.stackly.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.stackly.employee.entity.Employee;
import com.stackly.employee.requestdto.EmployeeRequestDto;
import com.stackly.employee.responsedto.EmployeeResponseDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	@Mapping(target="id",ignore = true)
	@Mapping(target="empId",ignore=true)
	Employee toEntity(EmployeeRequestDto requestDto);
	
	EmployeeResponseDto toResponseDto(Employee employee);
	
	@Mapping(target="id",ignore = true)
	@Mapping(target = "empId", ignore = true)
	void updateEntity(
			EmployeeRequestDto requestDto,
			@MappingTarget Employee employee
			);
}
