package org.example.staffmanagementweb.HR.dto;

public class DashboardDTO {
    private long staffCount;
    private long pendingLeavesCount;
    private long attendanceOverview;
    private long activeEmployeesCount;
    private long inactiveEmployeesCount;

    // Default constructor
    public DashboardDTO() {}

    // Constructor with parameters
    public DashboardDTO(long staffCount, long pendingLeavesCount, long attendanceOverview, 
                       long activeEmployeesCount, long inactiveEmployeesCount) {
        this.staffCount = staffCount;
        this.pendingLeavesCount = pendingLeavesCount;
        this.attendanceOverview = attendanceOverview;
        this.activeEmployeesCount = activeEmployeesCount;
        this.inactiveEmployeesCount = inactiveEmployeesCount;
    }

    // Getters and Setters
    public long getStaffCount() { return staffCount; }
    public void setStaffCount(long staffCount) { this.staffCount = staffCount; }

    public long getPendingLeavesCount() { return pendingLeavesCount; }
    public void setPendingLeavesCount(long pendingLeavesCount) { this.pendingLeavesCount = pendingLeavesCount; }

    public long getAttendanceOverview() { return attendanceOverview; }
    public void setAttendanceOverview(long attendanceOverview) { this.attendanceOverview = attendanceOverview; }

    public long getActiveEmployeesCount() { return activeEmployeesCount; }
    public void setActiveEmployeesCount(long activeEmployeesCount) { this.activeEmployeesCount = activeEmployeesCount; }

    public long getInactiveEmployeesCount() { return inactiveEmployeesCount; }
    public void setInactiveEmployeesCount(long inactiveEmployeesCount) { this.inactiveEmployeesCount = inactiveEmployeesCount; }

    @Override
    public String toString() {
        return "DashboardDTO{" +
                "staffCount=" + staffCount +
                ", pendingLeavesCount=" + pendingLeavesCount +
                ", attendanceOverview=" + attendanceOverview +
                ", activeEmployeesCount=" + activeEmployeesCount +
                ", inactiveEmployeesCount=" + inactiveEmployeesCount +
                '}';
    }
}
