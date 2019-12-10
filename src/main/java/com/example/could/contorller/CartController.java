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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    GoodsService goodService;

    @GetMapping("/item")
    public String enterCart(Model model, HttpSession session){
        log.info(session.getAttribute("id").toString());
        String name = session.getAttribute("user_name").toString();
        List<T_item> itemList = goodService.cartGood((Integer)session.getAttribute("id"));
        List<Integer>num =null;
        int itemAmount = itemList.size();
        model.addAttribute("user_name", name);
        model.addAttribute("itemAmount", itemAmount);
        model.addAttribute("itemList", itemList);
        model.addAttribute("num", num);
        return "my_cart";
    }
}
