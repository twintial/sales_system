package com.example.could.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/user")
    public String userLogin(){
        return "forward:/user_login.html";
    }
    @GetMapping("/store")
    public String storeLogin(){
        return "forward:/store_login.html";
    }
    @GetMapping("/admin")
    public String adminLogin(){
        return "forward:/admin_login.html";
    }
}
