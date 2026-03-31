package org.example.staffmanagementweb.HR.strategy;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApprovalContext {
    
    @Autowired
    private List<LeaveApprovalStrategy> approvalStrategies;
    
    public boolean approveLeave(LeaveRequest leaveRequest, User approver) {
        for (LeaveApprovalStrategy strategy : approvalStrategies) {
            if (strategy.canApprove(leaveRequest, approver)) {
                // Log the approval message
                System.out.println(strategy.getApprovalMessage(leaveRequest, approver));
                return true;
            }
        }
        System.out.println("No approval strategy found for user: " + approver.getUsername());
        return false;
    }
    
    public String getApprovalMessage(LeaveRequest leaveRequest, User approver) {
        for (LeaveApprovalStrategy strategy : approvalStrategies) {
            if (strategy.canApprove(leaveRequest, approver)) {
                return strategy.getApprovalMessage(leaveRequest, approver);
            }
        }
        return "Leave cannot be approved by this user";
    }
}
