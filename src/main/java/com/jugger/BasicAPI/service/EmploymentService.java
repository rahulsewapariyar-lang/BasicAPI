package com.jugger.BasicAPI.service;

import java.util.List;

// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jugger.BasicAPI.model.Employee;
import com.jugger.BasicAPI.repository.EmployeeRepository;

@Service
public class EmploymentService {
    

    private final EmployeeRepository employeeRepository;

    public EmploymentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    //get all employees
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    //adding multiple employees
    public List<Employee> addEmployees(List<Employee> employees){
        if (employees == null) {
            return List.of();
        }
        return employeeRepository.saveAll(employees);
    }
    //add single employee
    public Employee add(Employee employee){
        if (employee == null){
            throw new IllegalArgumentException("Employee must not be null"); 
        }
        return employeeRepository.save(employee);
    }
    //get employee by id
    public Employee getEmployeeById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID must not be null");
        }
        return employeeRepository.findById(id).orElse(null);
    }
}
