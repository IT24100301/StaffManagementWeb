package org.example.staffmanagementweb.HR.observer;

import org.example.staffmanagementweb.HR.entity.Employee;

public interface EmployeeObserver {
    void onEmployeeCreated(Employee employee);
    void onEmployeeUpdated(Employee employee);
    void onEmployeeDeleted(Employee employee);
}
