package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import com.works.services.SQLDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final AdminService adminService;
    final SQLDB sqldb;

    @GetMapping("/")
    public String login() {
        //adminService.regiser();
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Valid Admin admin, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            List<FieldError> ls = result.getFieldErrors();
            model.addAttribute("errors", ls);
            return "login";
        }else {
            model.addAttribute("password", admin.getPassword() );
            boolean loginStatus = adminService.login(admin);
            //boolean loginStatus = sqldb.login(admin.getEmail(), admin.getPassword());
            if (loginStatus) {
                return "redirect:/dashboard";
            }
            return "login";
        }
    }

}
