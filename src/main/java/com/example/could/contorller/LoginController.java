package com.example.could.contorller;

import com.example.could.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/user")
    public String userLogin(){
        return "forward:/user_login.html";
    }

    @GetMapping("/store")
    public String storeLogin(){
        return "forward:/store_login.html";
    }
    @PostMapping("/store/check")
    public String storeLoginCheck(@RequestParam("account") String account,
                                  @RequestParam("psw") String psw, HttpSession session){
        if (loginService.StoreLoginCheck(account, psw, session)){

            return "redirect:/home/store";
        }else {
            return "redirect:/store";
        }
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "forward:/admin_login.html";
    }
}
