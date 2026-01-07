package com.jugger.BasicAPI.service;

import java.util.List;

// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jugger.BasicAPI.model.Employee;
import com.jugger.BasicAPI.repository.EmployeeRepository;

@Service
public class EmploymentService {
    
     EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
}
