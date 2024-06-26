package com.eduardo.bmwstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eduardo.bmwstore.model.Order;
import com.eduardo.bmwstore.model.User;
import com.eduardo.bmwstore.repository.OrderRepository;
import com.eduardo.bmwstore.repository.UserRepository;

@Service
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

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (!existingUser.isPresent()) {
            User newUser = userRepository.save(order.getUser());
            existingUser = Optional.of(newUser);
        }

        order.setUser(existingUser.get());
        return orderRepository.save(order);
    }
}
