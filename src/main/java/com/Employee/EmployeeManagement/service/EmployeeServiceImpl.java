package com.Employee.EmployeeManagement.service;

import com.Employee.EmployeeManagement.dto.EmployeeDto;
import com.Employee.EmployeeManagement.exception.EmailAlreadyExistsException;
import com.Employee.EmployeeManagement.exception.ResourceNotFoundException;
import com.Employee.EmployeeManagement.model.Employee;
import com.Employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService{

    private ModelMapper modelMapper;

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employee.getEmail());

        if(optionalEmployee.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists");
        }
        Employee newEmployee = employeeRepository.save(employee);
        return modelMapper.map(newEmployee,EmployeeDto.class);


    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        Employee existingEmployee = employeeRepository.findById(employee.getEmployeeId()).
                orElseThrow(()->new ResourceNotFoundException("user","id",employee.getEmployeeId()));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());


        Employee newEmployee = employeeRepository.save(existingEmployee);
        return modelMapper.map(newEmployee,EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId).
                orElseThrow(()->new ResourceNotFoundException("user","id",employeeId));

        employeeRepository.deleteById(existingEmployee.getEmployeeId());

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",employeeId));
        return modelMapper.map(employee,EmployeeDto.class);

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeDto> employeeDtos= employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDtos;



    }
}
