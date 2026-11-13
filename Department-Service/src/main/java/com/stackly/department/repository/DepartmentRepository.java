
package com.stackly.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackly.department.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, Integer> {

    // CHECK DUPLICATE DEPARTMENT NAME
    boolean existsByDeptNameIgnoreCase(String deptName);

    // CHECK DUPLICATE NAME WHILE UPDATING
    boolean existsByDeptNameIgnoreCaseAndIdNot(
            String deptName,
            Integer id
    );

    // FIND DEPARTMENT USING GENERATED DEPARTMENT CODE
    Optional<Department> findByDeptId(String deptId);
}
