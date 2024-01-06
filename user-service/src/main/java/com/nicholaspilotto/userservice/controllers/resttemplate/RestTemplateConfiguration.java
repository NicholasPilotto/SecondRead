package com.nicholaspilotto.userservice.controllers.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for {@link RestTemplate}.
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {
  /**
   * Build a new instance of {@link RestTemplate} object.
   *
   * @param builder {@link RestTemplateBuilder} object reference.
   *
   * @return new instance of {@link RestTemplate}.
   */
  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
}
