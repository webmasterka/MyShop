package com.webmasterka.myshop.controller;

import com.webmasterka.myshop.model.Item;
import com.webmasterka.myshop.model.order.Order;
import com.webmasterka.myshop.repository.ItemRepository;
import com.webmasterka.myshop.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    private String adminPage() {
        return "adminview/addItem";
    }

    @PostMapping
    private String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}

