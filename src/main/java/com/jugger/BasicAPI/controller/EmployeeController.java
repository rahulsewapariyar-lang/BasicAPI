package com.jugger.BasicAPI.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jugger.BasicAPI.model.Employee;
import com.jugger.BasicAPI.service.EmploymentService;

@RestController
public class EmployeeController {

     private final EmploymentService empServ;
        public EmployeeController(EmploymentService empServ) {
            this.empServ = empServ;
        }
     
     @GetMapping("/employees")
     private ResponseEntity<List<Employee>> getEmployees(){
         List<Employee> e = empServ.getEmployees();
         if(e.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(e);
    }
    //adding multiple employees
    @PostMapping("/employees")
    private ResponseEntity<List<Employee>> addEmployees(@RequestBody List<Employee> employees){
       if (employees == null || employees.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        empServ.addEmployees(employees);
        return ResponseEntity.ok(employees);
    }
    //add single employee
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        empServ.add(employee);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee>getEmployeeById(@PathVariable Long id){
         Employee e = empServ.getEmployeeById(id);
        if (e == null){
            throw new IllegalArgumentException("ID must not be null");
        }
        return ResponseEntity.ok().body(e);
    }
    @PatchMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails){
        Employee exists = empServ.getEmployeeById(id);
        if (exists == null){
            return ResponseEntity.notFound().build();
        }  else {
            exists.setName(empDetails.getName());
            exists.setEmail(empDetails.getEmail());
            exists.setSalary(empDetails.getSalary());
            exists.setDepartment(empDetails.getDepartment());
            Employee updatedEmployee = empServ.add(exists);
            return ResponseEntity.ok(updatedEmployee);
        }
    }
    //delete method 
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void>deleteEmployee(@PathVariable Long id){
        try{
             empServ.deleteEmployee(id);
             return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
