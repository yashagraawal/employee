package com.employee;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceFailureTest {


    private EmployeeService employeeService;

    @Mock
    private DepartmentRepository departmentRepository;

    private Department department;
    
    private Employee employee;
    
    Employee res;
    
    @BeforeEach
    void setUp() {
    	
    	department = new Department();
    	department.setId(5L);
    	department.setName("Finance");
    	
        employee = new Employee();
        employee.setName("John Doe");
        employee.setJobTitle("Software Engineer");
        employee.setSalary(80000.0);
        employee.setDepartment(department);
    }

    @Test
    void testSaveEmployeeFailureDepartmentNotFound() {
    	
        when(departmentRepository.findByName("HR")).thenReturn(null);
        
        System.out.println(employee.toString());

        // Trying to sav an employee with non-existent department and getting null pointer exception (Trying to Resolve)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        assertEquals("Department not found", exception.getMessage());
    }
}
