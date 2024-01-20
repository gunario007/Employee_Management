package com.Employee.EmployeeManagement.controller;

import com.Employee.EmployeeManagement.dto.EmployeeDto;
import com.Employee.EmployeeManagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD API's for Employee Resource"

)

@CrossOrigin(origins = {"http://localhost:3001"})
@RestController
@AllArgsConstructor
@RequestMapping({"/api"})
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(
            summary = "Create Employee Resource"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping({"/employee/create"})
    public ResponseEntity<EmployeeDto> createdEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        EmployeeDto createEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);

    }


    @Operation(
            summary = "Get Employee Resource"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )

    @GetMapping({"/employee/{employeeId}"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId){
        EmployeeDto employee=employeeService.getEmployeeById(employeeId);


        return new ResponseEntity<>(employee,HttpStatus.OK);

    }

    @Operation(
            summary = "Get All Employee Resources"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 201 OK"
    )
    @GetMapping({"/employees"})
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> users=employeeService.getAllEmployees();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }



    @Operation(
            summary = "Update an Employee Resource"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping({"/employee/{employeeId}"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("employeeId") Long employeeId,
                                              @RequestBody @Valid EmployeeDto employeeDto){
        employeeDto.setEmployeeId(employeeId);
        EmployeeDto updatedEmployee=employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);

    }


    @Operation(
            summary = "Delete an Employee Resource"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP Status 204 No Content"
    )
    @DeleteMapping({"/employee/{employeeId}"})
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
    }







}
