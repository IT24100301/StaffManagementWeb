package org.example.staffmanagementweb.HR.controller;

import org.example.staffmanagementweb.HR.entity.LeaveRequest;
import org.example.staffmanagementweb.HR.entity.User;
import org.example.staffmanagementweb.HR.service.LeaveService;
import org.example.staffmanagementweb.HR.strategy.LeaveApprovalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hr-dashboard/strategy-demo")
public class StrategyDemoController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveApprovalContext approvalContext;

    @GetMapping("/approval-test")
    public String testApprovalStrategies(Model model) {
        // Get pending leaves
        List<LeaveRequest> pendingLeaves = leaveService.getPendingLeaves();
        
        // Create test users with different roles
        User managerUser = createTestUser("Manager", "manager1");
        User hrUser = createTestUser("HR", "hr1");
        User adminUser = createTestUser("Admin", "admin1");
        User staffUser = createTestUser("Staff", "staff1");

        model.addAttribute("pendingLeaves", pendingLeaves);
        model.addAttribute("managerUser", managerUser);
        model.addAttribute("hrUser", hrUser);
        model.addAttribute("adminUser", adminUser);
        model.addAttribute("staffUser", staffUser);

        return "HR/strategy-demo";
    }

    private User createTestUser(String roleName, String username) {
        User user = new User();
        user.setUsername(username);
        
        // Create a simple role for testing
        org.example.staffmanagementweb.HR.entity.Role role = new org.example.staffmanagementweb.HR.entity.Role();
        role.setRoleName(roleName);
        user.setRole(role);
        
        return user;
    }
}
