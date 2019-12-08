package com.example.could.Service;

import com.example.could.model.T_item;
import com.example.could.model.T_store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    // 获取物品
    public List<T_item> getGoods(Integer storeID){
        if (storeID != null){
            // 根据storeAccount查询
            StringBuffer sql = new StringBuffer("select * from t_item where store_id = ?");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class), storeID);
            return itemList;
        } else {
            // 全部查
            StringBuffer sql = new StringBuffer("select * from t_item");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class));
            return itemList;
        }
    }

    public List<T_item> searchGoods(Integer storeID, String name){
        if (storeID != null){
            // 根据storeAccount和name查询
            StringBuffer sql = new StringBuffer("select * from t_item " +
                    "where store_id = ? and item_name like ?");
            Object[] params = {storeID, "%" + name + "%"};
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class), params);
            return itemList;
        } else {
            // 全部查
            StringBuffer sql = new StringBuffer("select * from t_item where item_name like ?");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(T_item.class), "%" + name + "%");
            return itemList;
        }
    }

    public boolean deleteGoods(int itemID){
        // 在数据库中删除
        return true;
    }

    public boolean reviseStock(int itemID, int stock){
        // 在数据库中更改
        return true;
    }
}
