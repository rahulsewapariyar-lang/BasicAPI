package com.jugger.BasicAPI.service;

import java.util.List;

// import org.springframework.http.ResponseEntity;
import com.jugger.BasicAPI.exception.*;
import com.jugger.BasicAPI.model.Department;
import com.jugger.BasicAPI.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import com.jugger.BasicAPI.model.Employee;
import com.jugger.BasicAPI.repository.EmployeeRepository;

@Service
public class EmploymentService {
    

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmploymentService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    //get all employees
    public List<Employee> getEmployees(){
       List<Employee> employee = employeeRepository.findAll();
        if (employee.isEmpty()){
            throw new NoEmployeesFoundException("Empty List is Empty");
        }
        return employeeRepository.findAll();
    }
    //adding multiple employees
    public List<Employee> addEmployees(List<Employee> employees){
        if (employees == null) {
            return List.of();
        }
        return employeeRepository.saveAll(employees);
    }
    //add a single employee
    public Employee add(Employee employee){

        if (employee.getName() ==null || employee.getName().trim().isEmpty() ||
                employee.getEmail() ==null || employee.getEmail().trim().isEmpty()){
            throw new EmployeeNullException("Name or email cannot be null");
        } else if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException("Employee with this email already exists");
        }
        if(employee.getDepartment() != null && employee.getDepartment().getId()!=null){
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(()-> new DepartmentNotFoundException("Department not found with id " + employee.getDepartment().getId()));
            employee.setDepartment(dept);
        }
            return employeeRepository.save(employee);
    }
    //get employee by id
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee of id " +id +" not found"));
    }
    //update employee
    public Employee updateEmployee(Long id ,Employee employeedetails){
        Employee exists = employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee of id " +employeedetails.getId()+" not found"));
        if (employeedetails.getName() != null && !employeedetails.getName().trim().isEmpty()) {
            exists.setName(employeedetails.getName());
        }
        if(employeedetails.getEmail() != null && !employeedetails.getEmail().trim().isEmpty()) {
            String newEmail = employeedetails.getEmail().trim();
            if (!newEmail.equals((exists.getEmail()))) {
                boolean isDuplicate = employeeRepository.existsByEmail(newEmail);
                if (isDuplicate) {
                    throw new DuplicateEmailException("Employee with this email already exists");
                }
            }
            exists.setEmail(newEmail);
        }
        if (employeedetails.getSalary() != null) {
            exists.setSalary(employeedetails.getSalary());
        }
        if (employeedetails.getDepartment() != null) {
            exists.setDepartment(employeedetails.getDepartment());
        }
        exists.setEmail(employeedetails.getEmail());

        return employeeRepository.save(exists);
    }

    public void deleteEmployee(Long id) {
        Employee e = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee of id " +id +" not found"));
        employeeRepository.delete(e);
    } 
}
