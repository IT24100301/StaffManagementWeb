package org.example.staffmanagementweb.HR.observer;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeEventPublisher {
    
    @Autowired
    private List<EmployeeObserver> observers;
    
    public void notifyEmployeeCreated(Employee employee) {
        System.out.println("Publishing employee created event...");
        for (EmployeeObserver observer : observers) {
            observer.onEmployeeCreated(employee);
        }
    }
    
    public void notifyEmployeeUpdated(Employee employee) {
        System.out.println("Publishing employee updated event...");
        for (EmployeeObserver observer : observers) {
            observer.onEmployeeUpdated(employee);
        }
    }
    
    public void notifyEmployeeDeleted(Employee employee) {
        System.out.println("Publishing employee deleted event...");
        for (EmployeeObserver observer : observers) {
            observer.onEmployeeDeleted(employee);
        }
    }
}
