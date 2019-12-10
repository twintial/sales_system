package com.example.could.contorller;

import com.example.could.Service.GoodsService;
import com.example.could.model.T_item;
import com.example.could.model.T_userOrder;
import lombok.extern.slf4j.Slf4j;
import org.datanucleus.store.rdbms.adapter.SybaseAdapter;
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
        String name = session.getAttribute("store_name").toString();
        List<T_item> itemList = goodsService.getGoods(Integer.parseInt(session.getAttribute("id").toString()), session);
        int itemAmount = itemList.size();
        model.addAttribute("store_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        return "store_homepage";
    }
    @GetMapping("/user")
    public String enterUserHomePage(Model model, HttpSession session){
        String name = session.getAttribute("user_name").toString();
        List<T_item> itemList = goodsService.getGoods(null, session);
        int itemAmount = itemList.size();
        model.addAttribute("user_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);

        return "user_homepage";
    }

    @GetMapping("/user/order")
    public String enterUserOrder(Model model, HttpSession session){
        String name = session.getAttribute("user_name").toString();
        List<T_userOrder> itemList = goodsService.order((Integer)session.getAttribute("id"));
        int itemAmount = itemList.size();
        int cost = 0;
        for (T_userOrder order: itemList){
            cost += order.getUnit_price() * order.getPurchase_volume();
        }
        model.addAttribute("user_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("cost", cost);
        return "user_order";
    }

    @ResponseBody
    @GetMapping("/buy/{a}")
    public boolean enterUserHomePageAfterBuy(@PathVariable List<Integer> a, HttpSession session){
       return goodsService.deleteAllCart((Integer)session.getAttribute("id"),a);
    }

    @GetMapping("/store/all")
    public String searchGoods(String itemName, Model model, HttpSession session){
        log.info("搜索了" + itemName);
        String name = session.getAttribute("store_name").toString();
        List<T_item> itemList = goodsService.searchGoods(Integer.parseInt(session.getAttribute("id").toString()), itemName);
        int itemAmount = itemList.size();
        model.addAttribute("store_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchName", itemName);
        return "store_homepage";
    }

    @GetMapping("/user/order/all")
    public String enterUserOrder(Model model, HttpSession session,String itemName){
        String name = session.getAttribute("user_name").toString();
        List<T_userOrder> itemList = goodsService.searchOrder((Integer)session.getAttribute("id"),itemName);
        int itemAmount = itemList.size();
        model.addAttribute("user_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        return "user_order";
    }

    @GetMapping("/user/all")
    public String SearchGoods(String itemName2, Model model, HttpSession session){
        log.info("搜索了" + itemName2);
        String name = session.getAttribute("user_name").toString();
        List<T_item> itemList = goodsService.searchGoods(null, itemName2);
        int itemAmount = itemList.size();
        model.addAttribute("user_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchName", itemName2);
        return "user_homepage";
    }

    @GetMapping("/admin")
    public String enterAdminHomePage(Model model, HttpSession session){
        String account = session.getAttribute("account").toString();
        List<T_item> itemList = goodsService.getGoods(null, session);
        int itemAmount = itemList.size();
        model.addAttribute("account", account);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        return "admin_homepage";
    }

    @GetMapping("/admin/all")
    public String searchInAllGoods(String itemName, Model model, HttpSession session){
        log.info("搜索了" + itemName);
        String account = session.getAttribute("account").toString();
        List<T_item> itemList = goodsService.searchGoods(null, itemName);
        int itemAmount = itemList.size();
        model.addAttribute("account", account);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchName", itemName);
        return "admin_homepage";
    }



    @ResponseBody
    @GetMapping("/goods/delete/{itemID}")
    public String deleteGoods(@PathVariable int itemID){
        log.info("删除了" + itemID);
        return goodsService.deleteGoods(itemID) ? "success" : "fail";
    }

    @ResponseBody
    @GetMapping("/goods/addCart/{itemID}")
    public String addCart(@PathVariable int itemID,HttpSession session){
        log.info("进入了购物车" + itemID);
        return goodsService.addCart(itemID,(Integer)session.getAttribute("id")) ? "success" : "fail";
    }

    @ResponseBody
    @GetMapping("/goods/deleteCart/{itemID}")
    public String deleteGoods(@PathVariable int itemID,HttpSession session){
        log.info("从购物车中删除了" + itemID);
        return goodsService.deleteCart(itemID,(Integer)session.getAttribute("id")) ? "success" : "fail";
    }

    @ResponseBody
    @GetMapping("/stock/revise/{itemID}/{stock}")
    public String reviseStock(@PathVariable int itemID, @PathVariable int stock){
        log.info("修改了" + itemID + "为" + stock);
        return goodsService.reviseStock(itemID, stock) ? "success" : "fail";
    }

    @ResponseBody
    @PostMapping("/stock/add")
    public String addStock(@RequestBody T_item item, HttpSession session){
        System.out.println(item.getCost_price() + "|" +item.getStock()+"|"+item.getCost_price());
        return goodsService.addItem(item, Integer.parseInt(session.getAttribute("id").toString()), session) ? "success" : "fail";
//        log.info(s);
//        return "s";
    }
}
