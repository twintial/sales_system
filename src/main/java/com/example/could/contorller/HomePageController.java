package com.example.could.contorller;

import com.example.could.Service.GoodsService;
import com.example.could.model.T_item;
import lombok.extern.slf4j.Slf4j;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    GoodsService goodsService;
    // 进入主页(商品页面)
    @GetMapping("/store")
    public String enterStoreHomePage(Model model, HttpSession session){
        String account = session.getAttribute("account").toString();
        List<T_item> itemList = goodsService.getGoods(Integer.parseInt(session.getAttribute("id").toString()));
        int itemAmount = itemList.size();
        model.addAttribute("account", account);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        return "store_homepage";
    }

    @GetMapping("/store/all")
    public String searchGoods(String itemName, Model model, HttpSession session){
        log.info("搜索了" + itemName);
        String account = session.getAttribute("account").toString();
        List<T_item> itemList = goodsService.searchGoods(Integer.parseInt(session.getAttribute("id").toString()), itemName);
        int itemAmount = itemList.size();
        model.addAttribute("account", account);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchName", itemName);
        return "store_homepage";
    }

    @ResponseBody
    @GetMapping("/goods/delete/{itemID}")
    public String deleteGoods(@PathVariable int itemID){
        log.info("删除了" + itemID);
        return goodsService.deleteGoods(itemID) ? "success" : "fail";
    }

    @ResponseBody
    @GetMapping("/stock/revise/{itemID}/{stock}")
    public String reviseStock(@PathVariable int itemID, @PathVariable int stock){
        log.info("修改了" + itemID + "为" + stock);
        return goodsService.reviseStock(itemID, stock) ? "success" : "fail";
    }
}
