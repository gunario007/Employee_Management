package com.Employee.EmployeeManagement.service;

import com.Employee.EmployeeManagement.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee (EmployeeDto employeeDto);
    EmployeeDto updateEmployee (EmployeeDto employeeDto);
    void deleteEmployee (Long employeeId);
    EmployeeDto getEmployeeById (Long employeeId);
    List<EmployeeDto> getAllEmployees ();


}
