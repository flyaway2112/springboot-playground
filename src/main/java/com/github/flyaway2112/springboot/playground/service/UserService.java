package com.github.flyaway2112.springboot.playground.service;

import java.util.Optional;

public interface UserService {

  Optional<String> getUsername(String userId);

  Optional<String> getEmployeeNumber(String userId);
}
