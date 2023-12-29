package com.nicholaspilotto.userservice.controllers.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * Circuit breaker controller class.
 */
@RestController
public class CircuitBreakerController {
  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
}
