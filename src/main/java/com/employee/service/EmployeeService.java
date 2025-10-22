package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    // Get all employees
    public List<Employee> getAllEmployees();

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id);

    // Save a new employee
    public Employee saveEmployee(Employee employee);

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee);

    // Delete employee by ID
    public void deleteEmployeeById(Long id);

    // Delete all employees
    public void deleteAllEmployees();
}

