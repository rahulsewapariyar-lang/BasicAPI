package com.jugger.BasicAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jugger.BasicAPI.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    boolean existsByEmail(String email);
}
