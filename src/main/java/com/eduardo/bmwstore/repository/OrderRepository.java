package com.eduardo.bmwstore.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.bmwstore.model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{
       List<Order> findByUserEmail(String email);
    
}
