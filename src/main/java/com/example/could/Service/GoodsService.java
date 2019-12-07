package com.example.could.Service;

import com.example.could.model.T_item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    public List<T_item> getGood(String storeAccount){
        List<T_item> itemList = new ArrayList<>();
        if (storeAccount != null){
            // 根据storeAccount查询
            T_item a = new T_item();
            a.setCost_price(12.1f);
            a.setItem_id(12345);
            a.setItem_name("aaa");
            a.setSales(111);
            a.setStock(123333);
            a.setUnit_price(15.0f);
            itemList.add(a);
            return itemList;
        } else {
            // 直接查
            T_item a = new T_item();
            a.setCost_price(12.1f);
            a.setItem_id(12345);
            a.setItem_name("aaa");
            a.setSales(111);
            a.setStock(123333);
            a.setUnit_price(15.0f);
            itemList.add(a);
            return itemList;
        }
    }
}
