package com.example.could.Service;

import com.example.could.model.SalesLogViewModel;
import com.example.could.model.T_item;
import com.example.could.model.T_sales_log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesLogService {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    public List<SalesLogViewModel> getSalesLog(Integer storeID){
        if (storeID != null){
            StringBuffer sql = new StringBuffer(
                    "select item_name, real_name, age, purchase_time, purchase_volume, unit_price, purchase_volume*unit_price total_price " +
                    "from t_user natural join t_item natural join t_sales_log " +
                    "where store_id = ?");
            List<SalesLogViewModel> salesLogList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(SalesLogViewModel.class), storeID);
            return salesLogList;
        }else {
            StringBuffer sql = new StringBuffer(
                    "select item_name, real_name, age, purchase_time, purchase_volume, unit_price, purchase_volume*unit_price total_price " +
                            "from t_user natural join t_item natural join t_sales_log");
            List<SalesLogViewModel> salesLogList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(SalesLogViewModel.class));
            return salesLogList;
        }
    }
    // 搜索
    public List<SalesLogViewModel> searchSalesLog(Integer storeID, String name){
        if (storeID != null){
            StringBuffer sql = new StringBuffer(
                    "select item_name, real_name, age, purchase_time, purchase_volume, unit_price, purchase_volume*unit_price total_price " +
                            "from t_user natural join t_item natural join t_sales_log " +
                            "where store_id = ? and item_name like ?");
            Object[] params = {storeID, "%" + name + "%"};
            List<SalesLogViewModel> salesLogList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(SalesLogViewModel.class), params);
            return salesLogList;
        }else {
            StringBuffer sql = new StringBuffer(
                    "select item_name, real_name, age, purchase_time, purchase_volume, unit_price, purchase_volume*unit_price total_price " +
                            "from t_user natural join t_item natural join t_sales_log " +
                            "where item_name like ?");
            List<SalesLogViewModel> salesLogList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(SalesLogViewModel.class), "%" + name + "%");
            return salesLogList;
        }
    }
}
