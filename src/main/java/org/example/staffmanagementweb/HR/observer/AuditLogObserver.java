package org.example.staffmanagementweb.HR.observer;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class AuditLogObserver implements EmployeeObserver {
    
    @Override
    public void onEmployeeCreated(Employee employee) {
        System.out.println("Audit Log: Employee created - ID: " + employee.getEmployeeId() + 
                          ", Name: " + employee.getFirstName() + " " + employee.getLastName() + 
                          ", Department: " + (employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : "N/A"));
        // In a real application, you would log to database or file here
    }
    
    @Override
    public void onEmployeeUpdated(Employee employee) {
        System.out.println("Audit Log: Employee updated - ID: " + employee.getEmployeeId() + 
                          ", Name: " + employee.getFirstName() + " " + employee.getLastName());
        // In a real application, you would log to database or file here
    }
    
    @Override
    public void onEmployeeDeleted(Employee employee) {
        System.out.println("Audit Log: Employee deactivated - ID: " + employee.getEmployeeId() + 
                          ", Name: " + employee.getFirstName() + " " + employee.getLastName());
        // In a real application, you would log to database or file here
    }
}
