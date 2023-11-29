package com.nicholaspilotto.authenticationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateBuilderConfiguration {
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
