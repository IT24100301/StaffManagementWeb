package org.example.staffmanagementweb.HR.repository;

import org.example.staffmanagementweb.HR.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
