package com.github.flyaway2112.springboot.playground.controller;

import com.github.flyaway2112.springboot.playground.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private final UserService userService;

  @Autowired
  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/users/{userId}/username", method = RequestMethod.GET)
  public @ResponseBody
  ResponseEntity<String> getUsername(@PathVariable String userId) {
    return userService.getUsername(userId)
        .map(userName -> ResponseEntity.status(HttpStatus.OK).body(userName))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }
}
