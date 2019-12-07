package com.example.could.Service;

import com.example.could.model.SalesLogViewModel;
import com.example.could.model.T_item;
import com.example.could.model.T_sales_log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesLogService {
    public List<SalesLogViewModel> getSalesLog(Integer storeID){
        List<SalesLogViewModel> salesLogList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            salesLogList.add(new SalesLogViewModel("goods" + i, "user" + i, 18, new Date(),
                    i, 18f, 18 * i));
        }
        if (storeID != null){
            return salesLogList;
        }else {
            return salesLogList;
        }
    }
    // 搜索
    public List<SalesLogViewModel> searchSalesLog(Integer storeID, String name){
        List<SalesLogViewModel> salesLogList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            salesLogList.add(new SalesLogViewModel("goods" + i, "user" + i, 18, new Date(),
                    i, 18f, 18 * i));
        }
        if (storeID != null){
            return salesLogList;
        }else {
            return salesLogList;
        }
    }
}
