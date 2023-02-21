package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        boolean loginStatus = request.getSession().getAttribute("admin") == null;
        if (loginStatus) {
            return "redirect:/";
        }
        return "dashboard";
    }

}
