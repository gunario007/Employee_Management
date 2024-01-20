package com.Employee.EmployeeManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Employee DTO Model Info"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    @Schema(
            description = "Employee Firstname"
    )

    private String firstName;
    @Schema(
            description = "Employee Lastname"
    )

    private String LastName;
    @Schema(
            description = "Employee Email"
    )

    private String email;


}
