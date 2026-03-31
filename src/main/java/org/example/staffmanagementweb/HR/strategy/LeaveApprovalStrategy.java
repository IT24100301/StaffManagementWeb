package org.example.staffmanagementweb.HR.strategy;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;

public interface LeaveApprovalStrategy {
    boolean canApprove(LeaveRequest leaveRequest, User approver);
    String getApprovalMessage(LeaveRequest leaveRequest, User approver);
}
