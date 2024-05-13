package com.eduardo.bmwstore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.bmwstore.model.Order;
import com.eduardo.bmwstore.model.User;
import com.eduardo.bmwstore.repository.OrderRepository;
import com.eduardo.bmwstore.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getOrdersByUserEmail(String email) {
        return orderRepository.findByUserEmail(email);
    }

    public Order saveOrder(Order order) {
        String email = order.getUser().getEmail();
        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            existingUser = userRepository.save(order.getUser());
        }

        order.setUser(existingUser);
        log.debug("testeeee", order);
        return orderRepository.save(order);
    }
}
