
package com.stackly.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.stackly.department.entity.Department;
import com.stackly.department.requestdto.DepartmentRequestDto;
import com.stackly.department.responsedto.DepartmentResponseDto;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deptId", ignore = true)
    Department toEntity(DepartmentRequestDto requestDto);

    DepartmentResponseDto toResponseDto(Department department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deptId", ignore = true)
    void updateEntity(
            DepartmentRequestDto requestDto,
            @MappingTarget Department department
    );
}
