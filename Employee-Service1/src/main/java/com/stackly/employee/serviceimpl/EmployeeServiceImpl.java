
package com.stackly.employee.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackly.employee.client.DepartmentClient;
import com.stackly.employee.entity.Employee;
import com.stackly.employee.exceptionhandling.DepartmentNotFoundException;
import com.stackly.employee.exceptionhandling.DuplicateEmployeeException;
import com.stackly.employee.exceptionhandling.EmployeeNotFoundException;
import com.stackly.employee.mapper.EmployeeMapper;
import com.stackly.employee.repository.EmployeeRepository;
import com.stackly.employee.requestdto.EmployeeRequestDto;
import com.stackly.employee.responsedto.EmployeeResponseDto;
import com.stackly.employee.service.EmployeeService;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final DepartmentClient departmentClient;

    // CREATE OR UPDATE EMPLOYEE

    @Override
    @Transactional
    public EmployeeResponseDto saveOrUpdateEmployee(
            Integer id,
            EmployeeRequestDto requestDto) {

        Employee employee;

        // CREATE EMPLOYEE
        if (id == null) {

            // CHECK DUPLICATE EMAIL
            if (employeeRepository.existsByEmpEmail(
                    requestDto.getEmpEmail())) {

                throw new DuplicateEmployeeException(
                        "Email ID already exists");
            }

            // CHECK DUPLICATE CONTACT
            if (employeeRepository.existsByEmpContact(
                    requestDto.getEmpContact())) {

                throw new DuplicateEmployeeException(
                        "Contact number already exists");
            }

            // CONVERT REQUEST DTO TO ENTITY
            employee = employeeMapper.toEntity(requestDto);

        } else {

            // UPDATE EMPLOYEE

            // CHECK WHETHER EMPLOYEE EXISTS
            employee = employeeRepository.findById(id)
                    .orElseThrow(() ->
                            new EmployeeNotFoundException(
                                    "Employee not found with ID: " + id));

            // CHECK DUPLICATE EMAIL EXCLUDING CURRENT EMPLOYEE
            if (employeeRepository.existsByEmpEmailAndIdNot(
                    requestDto.getEmpEmail(), id)) {

                throw new DuplicateEmployeeException(
                        "Email ID belongs to another employee");
            }

            // CHECK DUPLICATE CONTACT EXCLUDING CURRENT EMPLOYEE
            if (employeeRepository.existsByEmpContactAndIdNot(
                    requestDto.getEmpContact(), id)) {

                throw new DuplicateEmployeeException(
                        "Contact number belongs to another employee");
            }

            // UPDATE EXISTING ENTITY
            employeeMapper.updateEntity(requestDto, employee);
        }

        // VALIDATE DEPARTMENT USING OPENFEIGN
        try {

            departmentClient.getDepartmentById(
                    requestDto.getDepartmentId());

        } catch (FeignException.NotFound ex) {

            throw new DepartmentNotFoundException(
                    "Department not found with ID: "
                            + requestDto.getDepartmentId());
        }

        // GENERATE EMPLOYEE CODE FOR NEW EMPLOYEE
        if (id == null) {

            employee = employeeRepository.saveAndFlush(employee);

            String generatedEmpId =
                    String.format("STK-%04d", employee.getId());

            employee.setEmpId(generatedEmpId);
        }

        // SAVE EMPLOYEE
        Employee savedEmployee =
                employeeRepository.saveAndFlush(employee);

        // CONVERT ENTITY TO RESPONSE DTO
        return employeeMapper.toResponseDto(savedEmployee);
    }

    // DELETE EMPLOYEE

    @Override
    @Transactional
    public String deleteEmployee(String empId) {

        Employee employee = employeeRepository.findByEmpId(empId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + empId));

        employeeRepository.delete(employee);

        return "Employee with ID " + empId
                + " deleted successfully";
    }

    // GET ALL EMPLOYEES

    @Override
    public List<EmployeeResponseDto> readEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDto)
                .toList();
    }
}
