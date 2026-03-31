package org.example.staffmanagementweb.HR.strategy;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AdminApprovalStrategy implements LeaveApprovalStrategy {
    
    @Override
    public boolean canApprove(LeaveRequest leaveRequest, User approver) {
        // Admin can approve any leave request
        return approver.getRole() != null && 
               "Admin".equalsIgnoreCase(approver.getRole().getRoleName());
    }
    
    @Override
    public String getApprovalMessage(LeaveRequest leaveRequest, User approver) {
        if (canApprove(leaveRequest, approver)) {
            return "Leave approved by Admin: " + approver.getUsername();
        }
        return "Leave cannot be approved by this user";
    }
}
