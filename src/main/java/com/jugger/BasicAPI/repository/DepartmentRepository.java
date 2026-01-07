package com.jugger.BasicAPI.repository;

import com.jugger.BasicAPI.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
