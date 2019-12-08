package com.example.could.contorller;

import com.example.could.Service.GoodsService;
import com.example.could.model.T_item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String enterHomePage(Model model, HttpSession session){
        log.info(session.getAttribute("account").toString());
        List<T_item> itemList = goodsService.getGood(session.getAttribute("account").toString());
        model.addAttribute("account", session.getAttribute("account").toString());
        model.addAttribute("itemList", itemList);
        return "store_homepage";
    }

    @GetMapping("/user")
    public String enterUserHomePage(Model model, HttpSession session){
        log.info(session.getAttribute("account").toString());
        List<T_item> itemList = goodsService.getGood(null);
        model.addAttribute("account", session.getAttribute("account").toString());
        model.addAttribute("itemList", itemList);
        return "user_homepage";
    }
}
