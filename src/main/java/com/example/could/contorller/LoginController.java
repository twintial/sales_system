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


    @GetMapping("/store")
    public String storeLogin(){
        return "forward:/store_login.html";
    }
    @PostMapping("/store/check")
    public String storeLoginCheck(@RequestParam("account") String account,
                                  @RequestParam("psw") String psw, HttpSession session){
        if (loginService.storeLoginCheck(account, psw, session)){
            return "redirect:/home/store";
        }else {
            return "redirect:/login/store";
        }
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "forward:/admin_login.html";
    }
    @PostMapping("/admin/check")
    public String adminLoginCheck(@RequestParam("account") String account,
                                  @RequestParam("psw") String psw, HttpSession session){
        if (loginService.adminLoginCheck(account, psw, session)){
            return "redirect:/home/admin";
        }else {
            return "redirect:/login/admin";
        }
    }

    @GetMapping("/user")
    public String userLogin(){
        return "forward:/user_login.html";
    }
    @PostMapping("/user/check")
    public String userLoginCheck(@RequestParam("account") String account,
                                  @RequestParam("psw") String psw, HttpSession session){
        if (loginService.userLoginCheck(account, psw, session)){
            return "redirect:/home/user";
        }else {
            return "redirect:/login/user";
        }
    }
}
