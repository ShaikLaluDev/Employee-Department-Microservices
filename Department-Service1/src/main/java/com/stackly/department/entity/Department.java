package com.stackly.department.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dept_id", unique = true)
    private String deptId;

    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @Column(name = "dept_description")
    private String deptDescription;
}
