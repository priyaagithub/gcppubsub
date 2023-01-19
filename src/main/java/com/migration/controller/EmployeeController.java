package com.migration.controller;

import com.migration.exception.ResourceNotFoundException;
import com.migration.model.Employee;
import com.migration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("employees")
    public List<Employee> getEmployee(){
        return this.employeeRepository.findAll();
    }
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found for id :: " + id));

        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee){
        System.out.println("Employee-" + employee.toString());
        return this.employeeRepository.save(employee);
    }
}
