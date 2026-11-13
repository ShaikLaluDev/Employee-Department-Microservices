
package com.stackly.department.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackly.department.entity.Department;
import com.stackly.department.exceptionhandling.DepartmentNotFoundException;
import com.stackly.department.exceptionhandling.DuplicateDepartmentException;
import com.stackly.department.mapper.DepartmentMapper;
import com.stackly.department.repository.DepartmentRepository;
import com.stackly.department.requestdto.DepartmentRequestDto;
import com.stackly.department.responsedto.DepartmentResponseDto;
import com.stackly.department.service.DepartmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    // CREATE OR UPDATE DEPARTMENT

    @Override
    @Transactional
    public DepartmentResponseDto saveOrUpdateDepartment(
            Integer id,
            DepartmentRequestDto requestDto) {

        Department department;

        String name = requestDto.getDeptName().trim();

        // CREATE NEW DEPARTMENT

        if (id == null) {

            if (departmentRepository.existsByDeptNameIgnoreCase(name)) {
                throw new DuplicateDepartmentException(
                        "Department name already exists");
            }

            department = departmentMapper.toEntity(requestDto);

            department.setDeptName(name);

            department = departmentRepository.saveAndFlush(department);

            String generatedDeptId =
                    String.format("DEPT-%04d", department.getId());

            department.setDeptId(generatedDeptId);

        } else {

            // UPDATE EXISTING DEPARTMENT

            department = departmentRepository.findById(id)
                    .orElseThrow(() ->
                            new DepartmentNotFoundException(
                                    "Department not found with ID: " + id));

            if (departmentRepository
                    .existsByDeptNameIgnoreCaseAndIdNot(name, id)) {

                throw new DuplicateDepartmentException(
                        "Department name belongs to another department");
            }

            departmentMapper.updateEntity(requestDto, department);

            department.setDeptName(name);
        }

        // SAVE DEPARTMENT

        Department savedDepartment =
                departmentRepository.saveAndFlush(department);

        return departmentMapper.toResponseDto(savedDepartment);
    }

    // DELETE DEPARTMENT

    @Override
    @Transactional
    public String deleteDepartment(String deptId) {

        Department department = departmentRepository.findByDeptId(deptId)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with ID: " + deptId));

        departmentRepository.delete(department);

        return "Department with ID " + deptId
                + " deleted successfully";
    }

    // GET ALL DEPARTMENTS

    @Override
    public List<DepartmentResponseDto> readDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();
    }

    // INTERNAL DEPARTMENT LOOKUP FOR OPENFEIGN

    @Override
    @Transactional
    public DepartmentResponseDto getDepartmentByNumericId(Integer id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with ID: " + id));

        return departmentMapper.toResponseDto(department);
    }
}
