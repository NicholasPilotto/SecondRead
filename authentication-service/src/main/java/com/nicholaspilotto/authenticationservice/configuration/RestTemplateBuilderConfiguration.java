package com.nicholaspilotto.authenticationservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Builder class for {@link RestTemplate} objects.
 */
@Configuration
public class RestTemplateBuilderConfiguration {
  @LoadBalanced
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
