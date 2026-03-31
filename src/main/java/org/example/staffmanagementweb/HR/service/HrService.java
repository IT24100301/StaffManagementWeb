package org.example.staffmanagementweb.HR.service;

import org.example.staffmanagementweb.HR.entity.Department;
import org.example.staffmanagementweb.HR.entity.Employee;
import org.example.staffmanagementweb.HR.entity.Role;
import org.example.staffmanagementweb.HR.entity.User;
import org.example.staffmanagementweb.HR.observer.EmployeeEventPublisher;
import org.example.staffmanagementweb.HR.repository.DepartmentRepository;
import org.example.staffmanagementweb.HR.repository.EmployeeRepository;
import org.example.staffmanagementweb.HR.repository.RoleRepository;
import org.example.staffmanagementweb.HR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HrService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeEventPublisher eventPublisher;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .filter(emp -> emp != null && (emp.getIsActive() == null || emp.getIsActive()))
                .collect(Collectors.toList());
    }


    // Save (insert or update) employee
    public void saveEmployee(Employee employee) {
        boolean isNewEmployee = employee.getEmployeeId() == null;
        employeeRepository.save(employee);
        
        // Notify observers based on operation type
        if (isNewEmployee) {
            eventPublisher.notifyEmployeeCreated(employee);
        } else {
            eventPublisher.notifyEmployeeUpdated(employee);
        }
    }

    // Get employee by ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete employee by ID
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a user with defaults and return it
    public User createDefaultUserForEmployee(Employee employee) {
        User user = new User();

        // Username from email if present, else firstName.lastName lowercased
        String username;
        if (employee.getEmail() != null && !employee.getEmail().isEmpty()) {
            username = employee.getEmail().split("@")[0];
        } else {
            String first = employee.getFirstName() != null ? employee.getFirstName() : "user";
            String last = employee.getLastName() != null ? employee.getLastName() : "";
            username = (first + "." + last).toLowerCase().replaceAll("\\s+", "");
        }
        user.setUsername(username);

        // WARNING: In production, hash the password. For now, simple default.
        user.setPassword("ChangeMe123!");

        // Default role: try 'Staff', else first available
        Role defaultRole = null;
        try {
            defaultRole = roleRepository.findByRoleName("Staff");
        } catch (Exception ignored) { }
        if (defaultRole == null) {
            List<Role> roles = roleRepository.findAll();
            if (!roles.isEmpty()) {
                defaultRole = roles.get(0);
            }
        }
        if (defaultRole != null) {
            user.setRole(defaultRole);
        }

        return userRepository.save(user);
    }



    // Dashboard stats
    public long getStaffCount() {
        return employeeRepository.count();
    }

    public long getPendingLeavesCount() {
        return employeeRepository.count(); // TODO: Replace with Leave entity logic
    }

    public long getAttendanceOverview() {
        return employeeRepository.count(); // TODO: Replace with Attendance entity logic
    }

    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // HrService.java
    public void softDeleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setIsActive(false);  // mark inactive instead of delete
        employeeRepository.save(employee);
        
        // Notify observers about employee deletion
        eventPublisher.notifyEmployeeDeleted(employee);
    }






}
