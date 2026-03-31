package org.example.staffmanagementweb.HR.strategy;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HRApprovalStrategy implements LeaveApprovalStrategy {
    
    @Override
    public boolean canApprove(LeaveRequest leaveRequest, User approver) {
        // HR can approve any leave request
        return approver.getRole() != null && 
               "HR".equalsIgnoreCase(approver.getRole().getRoleName());
    }
    
    @Override
    public String getApprovalMessage(LeaveRequest leaveRequest, User approver) {
        if (canApprove(leaveRequest, approver)) {
            return "Leave approved by HR: " + approver.getUsername();
        }
        return "Leave cannot be approved by this user";
    }
}
