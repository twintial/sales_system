package com.example.could.Service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    public boolean StoreLoginCheck(String account, String psw, HttpSession session){
        // 改成从数据库中查询
        if (account.equals("550847434@qq.com") && psw.equals("abcd1234")){
            session.setAttribute("account", account);
            return true;
        } else {
            return false;
        }
    }
    public boolean UserLoginCheck(String account, String psw, HttpSession session){
        // 改成从数据库中查询
        if (account.equals("1@q.q") && psw.equals("1")){
            session.setAttribute("account", account);
            return true;
        } else {
            return false;
        }
    }
}
