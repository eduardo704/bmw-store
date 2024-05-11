package com.eduardo.bmwstore.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardo.bmwstore.dtos.SignUpRequest;
import com.eduardo.bmwstore.exceptions.DuplicateException;
import com.eduardo.bmwstore.model.User;
import com.eduardo.bmwstore.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public void signup(SignUpRequest request) {
    String email = request.email();
    Optional<User> existingUser = repository.findByEmail(email);
    if (existingUser.isPresent()) {
      throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
    }

    String hashedPassword = passwordEncoder.encode(request.password());
    User user = new User();
    user.setEmail(email);
    user.setName(request.name());
    user.setPassword(hashedPassword);
    repository.save(user);
  }

}