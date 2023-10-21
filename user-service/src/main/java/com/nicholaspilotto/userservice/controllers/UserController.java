package com.nicholaspilotto.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @GetMapping("/user/test")
  public String UserTest() {
    return "Test";
  }
}
