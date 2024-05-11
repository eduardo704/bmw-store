package com.eduardo.bmwstore.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.bmwstore.model.Order;
import com.eduardo.bmwstore.services.OrderService;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private OrderService orderService;
       public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getMethodName() {
        return this.orderService.getOrdersByUserId("");
    }
    
    @PostMapping("")
    public Order postMethodName(@RequestBody Order order) {
        //TODO: process POST request
        // log.debug("testeeee", order);
        // System.out.println(order.getUserId());

        return this.orderService.saveOrder(order);

        //
    }
    
}
