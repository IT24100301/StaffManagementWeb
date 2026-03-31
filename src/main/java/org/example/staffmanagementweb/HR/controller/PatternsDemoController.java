package org.example.staffmanagementweb.HR.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatternsDemoController {

    @GetMapping("/patterns-demo")
    public String showPatternsDemo() {
        return "HR/patterns-demo";
    }
}
