package com.employee.controller;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Get all employees
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {

        List<Employee> lst = employeeService.getAllEmployees();

        if(!lst.isEmpty()){
            return new ResponseEntity<>(lst , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        if(employee!=null){
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {

        // Get the department by name
        Optional<Department> department = departmentRepository.findByName(employee.getDepartment().getName());
        
        employee.setDepartment(department.get());
        
        if(department.isPresent()){
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Department Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Department department = departmentRepository.findByName(employee.getDepartment().getName())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(id);
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setDepartment(department);

        Employee res = employeeService.updateEmployee(id, updatedEmployee);
        return res != null ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete all employees
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
