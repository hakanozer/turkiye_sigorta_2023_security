package com.works.controllers;

import com.works.entities.Admin;
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
public class LoginController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@Valid Admin admin, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            List<FieldError> ls = result.getFieldErrors();
            for( FieldError error : ls ) {
                System.out.println( error.getField() + " - " + error.getDefaultMessage() );
            }
            model.addAttribute("errors", ls);
            return "login";
        }else {
            System.out.println( admin.getEmail() + " -VT- " + admin.getPassword() );
            model.addAttribute("password", admin.getPassword() );
            return "login";
            //return "redirect:/";
        }
    }

}