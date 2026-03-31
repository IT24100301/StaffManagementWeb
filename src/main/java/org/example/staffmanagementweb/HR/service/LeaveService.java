package org.example.staffmanagementweb.HR.service;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.example.staffmanagementweb.HR.repository.LeaveRequestRepository;
import org.example.staffmanagementweb.HR.strategy.LeaveApprovalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveApprovalContext approvalContext;
    private User approver;

    public List<LeaveRequest> getPendingLeaves() {
        return leaveRequestRepository.findByStatus("PENDING");
    }

    public Optional<LeaveRequest> getLeaveById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    public LeaveRequest approveLeave(Long id) {
        Optional<LeaveRequest> leave = getLeaveById(id);
        if (leave.isPresent()) {
            // Use strategy pattern to check if user can approve
            if (approvalContext.approveLeave(leave.get(), approver)) {
                leave.get().setStatus("APPROVED");
                return leaveRequestRepository.save(leave.get());
            } else {
                throw new RuntimeException("User " + approver.getUsername() + " cannot approve this leave request");
            }
        }
        return null;
    }

    public LeaveRequest rejectLeave(Long id) {
        Optional<LeaveRequest> leave = getLeaveById(id);
        if (leave.isPresent()) {
            // Use strategy pattern to check if user can approve (same logic for rejection)
            if (approvalContext.approveLeave(leave.get(), approver)) {
                leave.get().setStatus("REJECTED");
                return leaveRequestRepository.save(leave.get());
            } else {
                throw new RuntimeException("User " + approver.getUsername() + " cannot reject this leave request");
            }
        }
        return null;
    }
}
