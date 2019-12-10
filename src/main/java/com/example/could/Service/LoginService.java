package com.example.could.Service;

import com.example.could.model.T_admin;
import com.example.could.model.T_store;
import com.example.could.model.T_user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class LoginService {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    public boolean storeLoginCheck(String account, String psw, HttpSession session){
        StringBuffer sql = new StringBuffer("select store_id, store_password, store_name from t_store where store_account = ?");
        T_store store;
        try {
            store = hiveDruidTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(T_store.class), account);
        } catch (Exception e){
            return false;
        }
        if (store == null){
            return false;
        }
        if (psw.equals(store.getStore_password())){
            session.setAttribute("account", account);
            session.setAttribute("store_name", store.getStore_name());
            // 改成查询出的id
            session.setAttribute("id", store.getStore_id());
            return true;
        } else {
            return false;
        }
    }

    public boolean adminLoginCheck(String account, String psw, HttpSession session){
        StringBuffer sql = new StringBuffer("select admin_password from t_admin where admin_account = ?");
        T_admin admin;
        try {
            admin = hiveDruidTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(T_admin.class), account);
        } catch (Exception e){
            return false;
        }
        if (admin == null){
            return false;
        }
        if (psw.equals(admin.getAdmin_password())){
            session.setAttribute("account", account);
            return true;
        } else {
            return false;
        }
    }

    public boolean userLoginCheck(String account, String psw, HttpSession session){
        StringBuffer sql = new StringBuffer("select user_id, user_password, real_name from t_user where user_account = ?");
        T_user user;
        try {
            user = hiveDruidTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(T_user.class), account);
        } catch (Exception e){
            return false;
        }
        if (user == null){
            return false;
        }
        if (psw.equals(user.getUser_password())){

            session.setAttribute("account", account);
            session.setAttribute("user_name", user.getReal_name());
            // 改成查询出的id
            session.setAttribute("id", user.getUser_id());
            return true;
        } else {
            return false;
        }
    }
}
