package com.example.could.Service;

import com.example.could.model.T_item;
import com.example.could.model.T_store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GoodsService {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    // 获取物品
    public List<T_item> getGoods(Integer storeID, HttpSession session){
        if (storeID != null){
            // 根据storeAccount查询
            StringBuffer sql = new StringBuffer("select * from t_item_p where store_id = ? order by item_id asc");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class), storeID);
            session.setAttribute("biggest", itemList.get(itemList.size() - 1).getItem_id());
            return itemList;
        } else {
            // 全部查
            StringBuffer sql = new StringBuffer("select * from t_item_p order by item_id asc");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class));
            return itemList;
        }
    }

    public List<T_item> searchGoods(Integer storeID, String name){
        if (storeID != null){
            // 根据storeAccount和name查询
            StringBuffer sql = new StringBuffer("select * from t_item_p " +
                    "where store_id = ? and item_name like ? order by item_id asc");
            Object[] params = {storeID, "%" + name + "%"};
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(T_item.class), params);
            return itemList;
        } else {
            // 全部查
            StringBuffer sql = new StringBuffer("select * from t_item_p where item_name like ? order by item_id asc");
            List<T_item> itemList = hiveDruidTemplate.query(sql.toString(),
                    new BeanPropertyRowMapper<>(T_item.class), "%" + name + "%");
            return itemList;
        }
    }

    public boolean deleteGoods(int itemID){
        // 在数据库中删除
        StringBuilder sql = new StringBuilder("alter table t_item_p drop if exists partition (item_id = ?)");
        hiveDruidTemplate.update(sql.toString(), itemID);
        return true;
    }

    public boolean reviseStock(int itemID, int stock){
        String sql = "select * from t_item_p where item_id = ?";
        T_item item = hiveDruidTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(T_item.class), itemID);
        if (item == null){
            return false;
        }
        sql = "alter table t_item_p drop if exists partition (item_id = ?)";
        hiveDruidTemplate.update(sql, itemID);
        sql = "insert into table t_item_p values (?,?,?,?,?,?,?)";
        item.setStock(stock);
        Object[] param = {item.getStore_id(), item.getItem_name(), item.getSales(), item.getCost_price(), item.getUnit_price(),
                item.getStock(), item.getItem_id()};
        hiveDruidTemplate.update(sql, param);
        // 在数据库中更改
        return true;
    }

    public boolean addItem(T_item item, int storeID, HttpSession session){
        item.setSales(0);
        item.setStore_id(storeID);
        item.setItem_id(Integer.parseInt(session.getAttribute("biggest").toString()) + 1);
        session.setAttribute("biggest", item.getItem_id() + 1);
        String sql = "insert into table t_item_p values (?,?,?,?,?,?,?)";
        Object[] param = {item.getStore_id(), item.getItem_name(), item.getSales(), item.getCost_price(), item.getUnit_price(),
                item.getStock(), item.getItem_id()};
        int row = hiveDruidTemplate.update(sql, param);
        return true;
    }
}
