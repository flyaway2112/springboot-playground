package com.github.flyaway2112.springboot.playground.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public Optional<String> getUsername(String userId) {
    return Optional.of("Username"); // TODO: implement it
  }

  @Override
  public Optional<String> getEmployeeNumber(String userId) {
    return Optional.of("Employee Number"); // TODO: implement it
  }
}
