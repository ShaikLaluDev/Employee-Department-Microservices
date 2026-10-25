
package com.stackly.department.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    name = "DepartmentResponse",
    description = "Response containing department information"
)
public class DepartmentResponseDto {

    // DATABASE PRIMARY KEY

    @Schema(
        description = "Numeric primary key of the department",
        example = "1"
    )
    private Integer id;

    // GENERATED DEPARTMENT CODE

    @Schema(
        description = "Automatically generated department code",
        example = "DEPT-0001"
    )
    private String deptId;

    // DEPARTMENT NAME

    @Schema(
        description = "Name of the department",
        example = "Information Technology"
    )
    private String deptName;

    // DEPARTMENT DESCRIPTION

    @Schema(
        description = "Description of the department",
        example = "Responsible for software development and IT support"
    )
    private String deptDescription;
}
