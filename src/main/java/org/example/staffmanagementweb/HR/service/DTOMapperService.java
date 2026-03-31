package org.example.staffmanagementweb.HR.service;

import org.example.staffmanagementweb.HR.dto.DashboardDTO;
import org.example.staffmanagementweb.HR.dto.EmployeeDTO;
import org.example.staffmanagementweb.HR.dto.LeaveRequestDTO;
import org.example.staffmanagementweb.HR.entity.Department;
import org.example.staffmanagementweb.HR.entity.Employee;
import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.Role;
import org.example.staffmanagementweb.HR.entity.User;
import org.example.staffmanagementweb.HR.observer.EmployeeEventPublisher;
import org.example.staffmanagementweb.HR.repository.DepartmentRepository;
import org.example.staffmanagementweb.HR.repository.EmployeeRepository;
import org.example.staffmanagementweb.HR.repository.LeaveRequestRepository;
import org.example.staffmanagementweb.HR.repository.RoleRepository;
import org.example.staffmanagementweb.HR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOMapperService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    // Convert Employee entity to EmployeeDTO
    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setHireDate(employee.getHireDate());
        dto.setJobRole(employee.getJobRole());
        dto.setIsActive(employee.getIsActive());
        
        // Set department information
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getDepartmentId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        
        // Set user information
        if (employee.getUser() != null) {
            dto.setUserId(employee.getUser().getUserId());
            dto.setUsername(employee.getUser().getUsername());
            if (employee.getUser().getRole() != null) {
                dto.setRoleName(employee.getUser().getRole().getRoleName());
            }
        }
        
        return dto;
    }
    
    // Convert EmployeeDTO to Employee entity
    public Employee convertToEmployee(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setHireDate(dto.getHireDate());
        employee.setJobRole(dto.getJobRole());
        employee.setIsActive(dto.getIsActive());
        
        return employee;
    }
    
    // Convert LeaveRequest entity to LeaveRequestDTO
    public LeaveRequestDTO convertToLeaveRequestDTO(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }
        
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setLeaveId(leaveRequest.getLeaveId());
        dto.setLeaveType(leaveRequest.getLeaveType());
        dto.setStartDate(leaveRequest.getStartDate() != null ? leaveRequest.getStartDate().toString() : null);
        dto.setEndDate(leaveRequest.getEndDate() != null ? leaveRequest.getEndDate().toString() : null);
        dto.setReason(leaveRequest.getReason());
        dto.setStatus(leaveRequest.getStatus());
        dto.setAppliedDate(leaveRequest.getAppliedDate() != null ? leaveRequest.getAppliedDate().toString() : null);
        
        // Set employee information
        if (leaveRequest.getEmployee() != null) {
            dto.setEmployeeId(leaveRequest.getEmployee().getEmployeeId());
            dto.setEmployeeName(leaveRequest.getEmployee().getFirstName() + " " + 
                              leaveRequest.getEmployee().getLastName());
        }
        
        return dto;
    }
    
    // Create DashboardDTO from statistics
    public DashboardDTO createDashboardDTO(long staffCount, long pendingLeavesCount, 
                                         long attendanceOverview, long activeEmployeesCount, 
                                         long inactiveEmployeesCount) {
        return new DashboardDTO(staffCount, pendingLeavesCount, attendanceOverview, 
                               activeEmployeesCount, inactiveEmployeesCount);
    }
    
    // Convert list of employees to DTOs
    public List<EmployeeDTO> convertToEmployeeDTOList(List<Employee> employees) {
        return employees.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    // Convert list of leave requests to DTOs
    public List<LeaveRequestDTO> convertToLeaveRequestDTOList(List<LeaveRequest> leaveRequests) {
        return leaveRequests.stream()
                .map(this::convertToLeaveRequestDTO)
                .collect(Collectors.toList());
    }
}