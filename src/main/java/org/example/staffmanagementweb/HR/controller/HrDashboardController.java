package org.example.staffmanagementweb.HR.controller;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.example.staffmanagementweb.HR.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hr-dashboard")
public class HrDashboardController {

    @Autowired
    private HrService hrService;

    @GetMapping("")
    public String showDashboard(Model model) {
        // Fetch dashboard stats
        long staffCount = hrService.getStaffCount();
        long pendingLeavesCount = hrService.getPendingLeavesCount();
        long attendanceOverview = hrService.getAttendanceOverview();

        // Fetch only active employees for staff list
        List<Employee> staffList = hrService.getAllEmployees()
                .stream()
                .filter(emp -> emp.getIsActive() == null || emp.getIsActive()) // only active
                .collect(Collectors.toList());

        // Send data to Thymeleaf
        model.addAttribute("staffCount", staffCount);
        model.addAttribute("pendingLeavesCount", pendingLeavesCount);
        model.addAttribute("attendanceOverview", attendanceOverview);
        model.addAttribute("staffList", staffList);

        return "HR/hr-dashboard"; // Must match templates/HR/hr-dashboard.html
    }


}
