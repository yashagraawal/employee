package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;


    // Get all employees
    public List<Employee> getAllEmployees() {
    	return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
    	return employeeRepository.findById(id);
    }

    // Save a new employee
    public Employee saveEmployee(Employee employee) {
    	return employeeRepository.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

    	if (employeeRepository.existsById(id)) {
            return employeeRepository.save(updatedEmployee);
        }
    	return null;
    }

    // Delete employee by ID
    public void deleteEmployeeById(Long id) {
    	employeeRepository.deleteById(id);
    }

    // Delete all employees
    public void deleteAllEmployees() {
    	employeeRepository.deleteAll();
    }
}
