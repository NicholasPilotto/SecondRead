package com.nicholaspilotto.userservice.controllers.circuitbreaker;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("/sample-api")
  @Bulkhead(name = "sample-api")
  public String sampleApi() {
    logger.info("Sample API call received");
    ResponseEntity<String> forEntity = new RestTemplate()
      .getForEntity("http://localhost:8080/some-dummy-url", String.class);
    return  forEntity.getBody();
  }
}
