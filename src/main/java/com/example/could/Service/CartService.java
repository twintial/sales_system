package com.example.could.Service;

import com.example.could.model.T_item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartService {
    public List<T_item> getGood(String userAccount){
        //根据用户账号，查找用户id；根据用户id查商品id，再根据商品id返回商品信息
        List<T_item> itemList = new ArrayList<>();
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
