package org.example.staffmanagementweb.admin.controller;


import org.example.staffmanagementweb.admin.entity.User;
import org.example.staffmanagementweb.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {

        User existingUser = userService.findByUsername(user.getUsername());
        // If same username exists
        if (existingUser != null && (user.getUserId() == null || !existingUser.getUserId().equals(user.getUserId()))) {
            model.addAttribute("error", "Username already exists. Please choose a different one.");
            model.addAttribute("user", user);
            return "admin/user-form";
        }
        // Check username length
        if (user.getUsername() == null || user.getUsername().length()<3 || user.getUsername().length()>50) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username must be in between 3 - 50 characters");
            return "admin/user-form";
        }

        // Check password length for new user
        if (user.getPassword() == null || user.getPassword().length() <= 6) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Password must be at least 6 characters long");
            return "admin/user-form";
        }

        userService.createUser(user);
        return "redirect:/admin/users?success";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "admin/user-form";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users?deleted";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, Model model) {
        // Only validate password if user entered a new one
        if (user.getPassword() != null && !user.getPassword().isEmpty() && user.getPassword().length() < 6) {
            model.addAttribute("user", user);
            model.addAttribute("error", "New password must be at least 6 characters long");
            return "admin/user-form";
        }

        userService.updateUser(id, user);
        return "redirect:/admin/users?success";
    }
}
