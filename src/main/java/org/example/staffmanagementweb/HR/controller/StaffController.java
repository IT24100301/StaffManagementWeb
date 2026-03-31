package org.example.staffmanagementweb.HR.controller;

import org.example.staffmanagementweb.HR.entity.Employee;
import org.example.staffmanagementweb.HR.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr-dashboard/staff")
public class StaffController {

    @Autowired
    private HrService hrService;

    // Show add staff form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", hrService.getAllDepartments());
        model.addAttribute("users", hrService.getAllUsers());
        return "HR/add-staff";
    }

    // Handle add staff (Spring will bind department.departmentId & user.userId automatically)
//    @PostMapping("/add")
//    public String addStaff(@ModelAttribute Employee employee, Model model) {
//        try {
//            hrService.saveEmployee(employee);
//            model.addAttribute("message", "Staff added successfully!");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error adding staff: " + e.getMessage());
//            return "HR/add-staff";
//        }
//        return "redirect:/hr-dashboard";
//    }
    @PostMapping("/add")
    public String addStaff(@ModelAttribute Employee employee,
                           @RequestParam(required = false) Integer departmentId,
                           @RequestParam(required = false) Integer userId,
                           Model model) {
        try {
            if (departmentId != null) {
                employee.setDepartment(hrService.getDepartmentById(departmentId));
            } else {
                employee.setDepartment(null);
            }

            if (userId != null) {
                employee.setUser(hrService.getUserById(userId));
            } else {
                // Auto-create a User if none was provided
                employee.setUser(hrService.createDefaultUserForEmployee(employee));
            }

            hrService.saveEmployee(employee);
            return "redirect:/hr-dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", hrService.getAllDepartments());
            model.addAttribute("users", hrService.getAllUsers());
            model.addAttribute("errorMessage", e.getMessage());
            return "HR/add-staff";
        }
    }



    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Employee employee = hrService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", hrService.getAllDepartments());
        model.addAttribute("users", hrService.getAllUsers());
        return "HR/edit-staff";
    }

    // Handle update
//    @PostMapping("/update")
//    public String updateStaff(@ModelAttribute Employee employee, Model model) {
//        try {
//            hrService.saveEmployee(employee); // update happens since ID exists
//        } catch (Exception e) {
//            model.addAttribute("error", "Error updating staff: " + e.getMessage());
//            return "HR/edit-staff";
//        }
//        return "redirect:/hr-dashboard";
//    }
    @PostMapping("/update")
    public String updateStaff(@ModelAttribute Employee employee,
                              @RequestParam(required = false) Integer departmentId,
                              @RequestParam(required = false) Integer userId,
                              Model model) {
        try {
            if (departmentId != null) {
                employee.setDepartment(hrService.getDepartmentById(departmentId));
            } else {
                employee.setDepartment(null); // ensures NULL in DB → show "N/A" in UI
            }

            if (userId != null) {
                employee.setUser(hrService.getUserById(userId));
            } else {
                employee.setUser(null);
            }

            hrService.saveEmployee(employee);
            return "redirect:/hr-dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", hrService.getAllDepartments());
            model.addAttribute("users", hrService.getAllUsers());
            model.addAttribute("errorMessage", e.getMessage());
            return "HR/edit-staff";
        }
    }


    // Soft delete
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Integer id) {
        hrService.softDeleteEmployee(id);
        return "redirect:/hr-dashboard";
    }
}
