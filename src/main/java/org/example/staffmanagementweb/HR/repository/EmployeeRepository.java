package org.example.staffmanagementweb.HR.repository;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.isActive = true")
    List<Employee> findAllActiveEmployees();


}