package org.example.staffmanagementweb.HR.strategy;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ManagerApprovalStrategy implements LeaveApprovalStrategy {
    
    @Override
    public boolean canApprove(LeaveRequest leaveRequest, User approver) {
        // Manager can approve leaves for employees in their department
        // For now, we'll implement a simple rule: Manager role can approve
        return approver.getRole() != null && 
               "Manager".equalsIgnoreCase(approver.getRole().getRoleName());
    }
    
    @Override
    public String getApprovalMessage(LeaveRequest leaveRequest, User approver) {
        if (canApprove(leaveRequest, approver)) {
            return "Leave approved by Manager: " + approver.getUsername();
        }
        return "Leave cannot be approved by this user";
    }
}
