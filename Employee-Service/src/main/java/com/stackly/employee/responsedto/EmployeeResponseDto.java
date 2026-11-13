
package com.stackly.employee.responsedto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "EmployeeResponse",
    description = "Response containing employee information"
)
public class EmployeeResponseDto {

    @Schema(
        description = "Numeric primary key",
        example = "1"
    )
    private Integer id;

    @Schema(
        description = "Automatically generated employee code",
        example = "STK-0001"
    )
    private String empId;

    @Schema(
        description = "Employee full name",
        example = "Rahul Sharma"
    )
    private String empName;

    @Schema(
        description = "Employee email address",
        example = "rahul@example.com"
    )
    private String empEmail;

    @Schema(
        description = "Employee contact number",
        example = "9876543210"
    )
    private String empContact;

    @Schema(
        description = "Employee salary",
        example = "35000.00"
    )
    private BigDecimal empSalary;

    @Schema(
        description = "Numeric department ID",
        example = "1"
    )
    private Integer departmentId;
}
