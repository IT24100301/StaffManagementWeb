package org.example.staffmanagementweb.HR.observer;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver implements EmployeeObserver {
    
    @Override
    public void onEmployeeCreated(Employee employee) {
        System.out.println("Email Notification: New employee " + 
                          employee.getFirstName() + " " + employee.getLastName() + 
                          " has been created. Email: " + employee.getEmail());
        // In a real application, you would send actual email here
    }
    
    @Override
    public void onEmployeeUpdated(Employee employee) {
        System.out.println("Email Notification: Employee " + 
                          employee.getFirstName() + " " + employee.getLastName() + 
                          " information has been updated.");
        // In a real application, you would send actual email here
    }
    
    @Override
    public void onEmployeeDeleted(Employee employee) {
        System.out.println("Email Notification: Employee " + 
                          employee.getFirstName() + " " + employee.getLastName() + 
                          " has been deactivated.");
        // In a real application, you would send actual email here
    }
}
