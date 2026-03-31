package org.example.staffmanagementweb.HR.dto;

import java.sql.Date;

public class EmployeeDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private String departmentName;
    private Integer departmentId;
    private String jobRole;
    private String username;
    private Integer userId;
    private String roleName;
    private Boolean isActive;

    // Default constructor
    public EmployeeDTO() {}

    // Constructor with parameters
    public EmployeeDTO(Integer employeeId, String firstName, String lastName, String email, 
                      String phone, Date hireDate, String departmentName, Integer departmentId, 
                      String jobRole, String username, Integer userId, String roleName, Boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.jobRole = jobRole;
        this.username = username;
        this.userId = userId;
        this.roleName = roleName;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
