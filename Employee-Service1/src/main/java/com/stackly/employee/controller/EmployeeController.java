
package com.stackly.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.stackly.employee.requestdto.EmployeeRequestDto;
import com.stackly.employee.responsedto.EmployeeResponseDto;
import com.stackly.employee.serviceimpl.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/employees")
@Tag(
    name = "Employee Management",
    description = "APIs for creating, updating, deleting and retrieving employees"
)
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    // CREATE EMPLOYEE

    @Operation(
        summary = "Create Employee",
        description = "Creates a new employee and generates an employee code"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201",
            description = "Employee created successfully"),
        @ApiResponse(responseCode = "400",
            description = "Invalid employee details"),
        @ApiResponse(responseCode = "409",
            description = "Email or contact number already exists")
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        EmployeeResponseDto response =
                employeeServiceImpl.saveOrUpdateEmployee(null, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE EMPLOYEE

    @Operation(
        summary = "Update Employee",
        description = "Updates an existing employee using the numeric ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200",
            description = "Employee updated successfully"),
        @ApiResponse(responseCode = "400",
            description = "Invalid employee details"),
        @ApiResponse(responseCode = "404",
            description = "Employee not found"),
        @ApiResponse(responseCode = "409",
            description = "Duplicate email or contact number")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @Parameter(description = "Numeric employee ID", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        EmployeeResponseDto response =
                employeeServiceImpl.saveOrUpdateEmployee(id, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // DELETE EMPLOYEE

    @Operation(
        summary = "Delete Employee",
        description = "Deletes an employee using the generated employee code"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200",
            description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "404",
            description = "Employee not found")
    })
    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(
            @Parameter(description = "Employee code", example = "STK-0001")
            @PathVariable String empId) {

        String message = employeeServiceImpl.deleteEmployee(empId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(message);
    }

    // GET ALL EMPLOYEES

    @Operation(
        summary = "Get All Employees",
        description = "Retrieves all employee records"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Employees retrieved successfully"
    )
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDto>> readAllEmployees() {

        List<EmployeeResponseDto> response =
                employeeServiceImpl.readEmployees();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
