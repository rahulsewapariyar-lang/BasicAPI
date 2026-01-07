package com.jugger.BasicAPI.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jugger.BasicAPI.model.Employee;
import com.jugger.BasicAPI.service.EmploymentService;

@RestController
public class EmployeeController {

     EmploymentService empServ;

    @GetMapping("/employees")
    private ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> e = empServ.getEmployees();
        if(e.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(e);
    }

}
