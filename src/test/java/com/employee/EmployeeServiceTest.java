package com.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {
	
    private Department department;
    
    private Employee employee;
    
    @Autowired
    private EmployeeService employeeService;
    
    Employee res;
    
    @BeforeEach
    void setUp() {
    	department = new Department();
        department.setId(1L);
        department.setName("IT");

        employee = new Employee();
        employee.setName("Yash Agrawal");
        employee.setJobTitle("Software Engineer");
        employee.setSalary(80000.0);
        employee.setDepartment(department);
        res = employeeService.saveEmployee(employee);
    }
    
    @Test
    void testSaveEmployeeSuccess() {
        // expected saved employee values
    	assertEquals("IT", department.getName());

    	Employee DbSaved = employeeService.getEmployeeById(res.getId()).get();
    	
        // expected saved employee values
        assertNotNull(DbSaved.getId());
        assertEquals("Yash Agrawal", DbSaved.getName());
        assertEquals("Software Engineer", DbSaved.getJobTitle());
        assertEquals(80000.0, DbSaved.getSalary());
        assertEquals("IT", DbSaved.getDepartment().getName());
    }
}
