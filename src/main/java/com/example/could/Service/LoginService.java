package com.example.could.Service;

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

    public boolean StoreLoginCheck(String account, String psw, HttpSession session){
        StringBuffer sql = new StringBuffer("select store_id, store_password, store_name from t_store where store_account = ?");
        T_store store = hiveDruidTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(T_store.class), account);
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
}
