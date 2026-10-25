
package com.stackly.department.service;

import java.util.List;

import com.stackly.department.requestdto.DepartmentRequestDto;
import com.stackly.department.responsedto.DepartmentResponseDto;

public interface DepartmentService {

    // CREATE OR UPDATE DEPARTMENT
    DepartmentResponseDto saveOrUpdateDepartment(
            Integer id,
            DepartmentRequestDto requestDto
    );

    String deleteDepartment(String deptId);

  
    List<DepartmentResponseDto> readDepartments();

    DepartmentResponseDto getDepartmentByNumericId(Integer id);
}
