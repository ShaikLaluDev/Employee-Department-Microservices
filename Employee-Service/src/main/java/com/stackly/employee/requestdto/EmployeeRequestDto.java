
package com.stackly.employee.requestdto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "EmployeeRequest",
    description = "Request details for creating or updating an employee"
)
public class EmployeeRequestDto {

    // EMPLOYEE NAME

    @NotBlank(message = "Employee name is required")
    @Size(min = 2, max = 100,
          message = "Employee name must contain 2 to 100 characters")
    @Pattern(
        regexp = "^[\\p{L}][\\p{L} .'-]*$",
        message = "Employee name contains invalid characters"
    )
    @Schema(
        description = "Full name of the employee",
        example = "Rahul Sharma",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String empName;

    // EMPLOYEE EMAIL

    @NotBlank(message = "Employee email is required")
    @Email(message = "Please enter a valid email address")
    @Size(max = 254,
          message = "Email must not exceed 254 characters")
    @Schema(
        description = "Unique email address of the employee",
        example = "rahul@example.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String empEmail;

    // EMPLOYEE SALARY

    @NotNull(message = "Employee salary is required")
    @DecimalMin(
        value = "15000.00",
        message = "Salary must be at least 15000"
    )
    @Digits(
        integer = 10,
        fraction = 2,
        message = "Salary can have up to 10 digits and 2 decimal places"
    )
    @Schema(
        description = "Employee salary (minimum 15000)",
        example = "35000.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal empSalary;

    // EMPLOYEE CONTACT NUMBER

    @NotBlank(message = "Employee contact number is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Contact number must be a valid 10-digit Indian mobile number"
    )
    @Schema(
        description = "Unique 10-digit Indian mobile number",
        example = "9876543210",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String empContact;

    // DEPARTMENT ID

    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be greater than zero")
    @Schema(
        description = "Numeric ID of the department",
        example = "1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer departmentId;
}
