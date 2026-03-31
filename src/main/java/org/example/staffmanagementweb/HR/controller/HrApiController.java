package org.example.staffmanagementweb.HR.controller;

import org.example.staffmanagementweb.HR.config.ApplicationConfig;
import org.example.staffmanagementweb.HR.dto.DashboardDTO;
import org.example.staffmanagementweb.HR.dto.EmployeeDTO;
import org.example.staffmanagementweb.HR.entity.Employee;
import org.example.staffmanagementweb.HR.service.DTOMapperService;
import org.example.staffmanagementweb.HR.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/hr-dashboard/api")
public class HrApiController {

    @Autowired
    private HrService hrService;

    @Autowired
    private DTOMapperService dtoMapperService;

    @Autowired
    private ApplicationConfig applicationConfig;

    @GetMapping("/dashboard-data")
    public String getDashboardData(Model model) {
        // Get raw data
        long staffCount = hrService.getStaffCount();
        long pendingLeavesCount = hrService.getPendingLeavesCount();
        long attendanceOverview = hrService.getAttendanceOverview();
        
        // Calculate additional statistics
        List<Employee> allEmployees = hrService.getAllEmployees();
        long activeEmployeesCount = allEmployees.stream()
                .filter(emp -> emp.getIsActive() == null || emp.getIsActive())
                .count();
        long inactiveEmployeesCount = allEmployees.stream()
                .filter(emp -> emp.getIsActive() != null && !emp.getIsActive())
                .count();

        // Create DTO using mapper service
        DashboardDTO dashboardDTO = dtoMapperService.createDashboardDTO(
                staffCount, pendingLeavesCount, attendanceOverview, 
                activeEmployeesCount, inactiveEmployeesCount);

        // Add configuration information from singleton
        model.addAttribute("dashboardData", dashboardDTO);
        model.addAttribute("appConfig", applicationConfig);
        model.addAttribute("appName", applicationConfig.getApplicationName());
        model.addAttribute("appVersion", applicationConfig.getVersion());

        return "HR/dashboard-api";
    }

    @GetMapping("/employees-dto")
    public String getEmployeesAsDTO(Model model) {
        // Get all employees
        List<Employee> employees = hrService.getAllEmployees();
        
        // Convert to DTOs using mapper service
        List<EmployeeDTO> employeeDTOs = dtoMapperService.convertToEmployeeDTOList(employees);
        
        model.addAttribute("employees", employeeDTOs);
        model.addAttribute("totalCount", employeeDTOs.size());
        
        return "HR/employees-dto";
    }

    @GetMapping("/config-info")
    public String getConfigInfo(Model model) {
        // Demonstrate singleton pattern usage
        ApplicationConfig config = ApplicationConfig.getInstance();
        
        model.addAttribute("config", config);
        model.addAttribute("isDebugMode", config.isDebugMode());
        model.addAttribute("maxLoginAttempts", config.getMaxLoginAttempts());
        model.addAttribute("sessionTimeout", config.getSessionTimeoutMinutes());
        
        return "HR/config-info";
    }
}
