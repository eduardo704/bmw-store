package com.eduardo.bmwstore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.bmwstore.model.Order;
import com.eduardo.bmwstore.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findAll();
    }
    public Order saveOrder(Order order) {
        log.debug("testeeee", order);
        return orderRepository.save(order);
    }
}
