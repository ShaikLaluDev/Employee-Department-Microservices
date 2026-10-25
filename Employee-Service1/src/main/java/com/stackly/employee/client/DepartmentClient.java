
package com.stackly.employee.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stackly.employee.responsedto.DepartmentResponseDto;

@FeignClient(name = "Department-Service")
public interface DepartmentClient {

    @GetMapping("/api/departments/internal/{id}")
    DepartmentResponseDto getDepartmentById(
            @PathVariable("id") Integer id);
}
