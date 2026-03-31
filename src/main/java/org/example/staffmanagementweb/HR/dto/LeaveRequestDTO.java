package org.example.staffmanagementweb.HR.dto;

public class LeaveRequestDTO {
    private Long leaveId;
    private Integer employeeId;
    private String employeeName;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;
    private String appliedDate;

    // Default constructor
    public LeaveRequestDTO() {}

    // Constructor with parameters
    public LeaveRequestDTO(Long leaveId, Integer employeeId, String employeeName, 
                          String leaveType, String startDate, String endDate, 
                          String reason, String status, String appliedDate) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    // Getters and Setters
    public Long getLeaveId() { return leaveId; }
    public void setLeaveId(Long leaveId) { this.leaveId = leaveId; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAppliedDate() { return appliedDate; }
    public void setAppliedDate(String appliedDate) { this.appliedDate = appliedDate; }

    @Override
    public String toString() {
        return "LeaveRequestDTO{" +
                "leaveId=" + leaveId +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
