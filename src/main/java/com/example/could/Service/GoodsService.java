package com.example.could.Service;

import com.example.could.model.T_item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    // 获取物品
    public List<T_item> getGoods(Integer storeID){
        List<T_item> itemList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            itemList.add(new T_item(i, 1000, "goods" + i,
                    i, 100.12f, 150, i));
        }
        if (storeID != null){
            // 根据storeAccount查询
            return itemList;
        } else {
            // 全部查
            return itemList;
        }
    }

    public List<T_item> searchGoods(Integer storeID, String name){
        List<T_item> itemList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            itemList.add(new T_item(i, 1000, "goods" + i,
                    i, 100.12f, 150, i));
        }
        if (storeID != null){
            // 根据storeAccount查询
            return itemList;
        } else {
            // 全部查
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
