
package com.stackly.department.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "DepartmentRequest",
    description = "Request details for creating or updating a department"
)
public class DepartmentRequestDto {

    // DEPARTMENT NAME

    @NotBlank(message = "Department name is required")
    @Size(
        min = 2,
        max = 100,
        message = "Department name must contain 2 to 100 characters"
    )
    @Schema(
        description = "Name of the department",
        example = "Information Technology",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String deptName;

    // DEPARTMENT DESCRIPTION

    @NotBlank(message = "Department description is required")
    @Size(
        max = 500,
        message = "Department description cannot exceed 500 characters"
    )
    @Schema(
        description = "Description of the department",
        example = "Responsible for software development and IT support",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String deptDescription;
}
