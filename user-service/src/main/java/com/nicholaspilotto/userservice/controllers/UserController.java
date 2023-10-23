package com.nicholaspilotto.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
  @Autowired
  private RestTemplate restTemplate;
  
  @GetMapping("/user/test")
  public Map<String, String> UserTest() {
    HashMap<String, String> map = new HashMap<>();
    map.put("Test", "This is a test call.");
    map.put("Service", "User");
    return map;
  }
}
