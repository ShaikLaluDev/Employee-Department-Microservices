package com.stackly.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackly.department.requestdto.DepartmentRequestDto;
import com.stackly.department.responsedto.DepartmentResponseDto;
import com.stackly.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(
    name = "Department Management",
    description = "Department Microservice REST APIs"
)
public class DepartmentController {

    private final DepartmentService departmentService;

    // CREATE OR UPDATE DEPARTMENT

    @Operation(
        summary = "Create or Update Department",
        description = "Creates a department without an ID, or updates it when an ID is provided"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201",
                description = "Department created successfully"),
        @ApiResponse(responseCode = "200",
                description = "Department updated successfully"),
        @ApiResponse(responseCode = "400",
                description = "Invalid department details"),
        @ApiResponse(responseCode = "404",
                description = "Department not found"),
        @ApiResponse(responseCode = "409",
                description = "Duplicate department name")
    })
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> saveOrUpdateDepartment(
            @RequestParam(required = false) Integer id,
            @Valid @RequestBody DepartmentRequestDto requestDto) {

        DepartmentResponseDto response =
                departmentService.saveOrUpdateDepartment(id, requestDto);

        return ResponseEntity
                .status(id == null ? HttpStatus.CREATED : HttpStatus.OK)
                .body(response);
    }

    // DELETE DEPARTMENT

    @Operation(
        summary = "Delete Department",
        description = "Deletes a department using its generated department code"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Department deleted successfully"),
        @ApiResponse(responseCode = "404",
                description = "Department not found")
    })
    @DeleteMapping("/{deptId}")
    public ResponseEntity<String> deleteDepartment(
            @Parameter(
                description = "Generated department code",
                example = "DEPT-0001"
            )
            @PathVariable String deptId) {

        String message = departmentService.deleteDepartment(deptId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(message);
    }

    // GET ALL DEPARTMENTS

    @Operation(
        summary = "Get All Departments",
        description = "Retrieves all department records"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Departments retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> readDepartments() {

        List<DepartmentResponseDto> response =
                departmentService.readDepartments();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // INTERNAL API FOR EMPLOYEE SERVICE

    @Operation(
        summary = "Get Department by Numeric ID",
        description = "Internal endpoint used by Employee Service through OpenFeign"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Department found"),
        @ApiResponse(responseCode = "404",
                description = "Department not found")
    })
    @GetMapping("/internal/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentByNumericId(
            @Parameter(
                description = "Numeric department ID",
                example = "1"
            )
            @PathVariable Integer id) {

        DepartmentResponseDto response =
                departmentService.getDepartmentByNumericId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
