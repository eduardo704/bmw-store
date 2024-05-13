package com.eduardo.bmwstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.bmwstore.model.User;
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
    // Optional<User> findByEmail(String email);
    User findByEmail(String email);

    
}
