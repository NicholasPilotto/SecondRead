package com.nicholaspilotto.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
  @GetMapping("/user/test")
  public Map<String, String> UserTest() {
    HashMap<String, String> map = new HashMap<>();
    map.put("Test", "This is a test call.");
    map.put("Service", "User");
    return map;
  }
}
