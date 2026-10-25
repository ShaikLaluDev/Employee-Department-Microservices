package com.stackly.employee.responsedto;

import lombok.Data;

@Data
public class DepartmentResponseDto {

    private Integer id;
    private String deptId;
    private String deptName;
    private String deptDescription;
}
