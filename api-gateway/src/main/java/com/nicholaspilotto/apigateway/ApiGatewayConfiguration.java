package com.nicholaspilotto.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(predicateSpec -> {
        return predicateSpec.path("/test")
          .uri("https://httpbin.org/ip");
      })
      .build();
  }
}
