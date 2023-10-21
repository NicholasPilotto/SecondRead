package com.nicholaspilotto.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApiGatewayConfiguration {
  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(predicateSpec -> {
        return predicateSpec.path("/get")
          .filters(f -> f
            .addRequestHeader("MyHeader", "MyUri")
            .addRequestParameter("MyParam", "MyValue")
          )
          .uri("http://httpbin.org/get");
      })
      .route(predicateSpec -> {
        return predicateSpec.path("/post")
          .filters(f -> f
            .addRequestHeader("MyHeader", "MyUri")
            .addRequestParameter("MyParam", "MyValue")
          )
          .uri("http://httpbin.org/post");
      })
      .route(predicateSpec -> {
        return predicateSpec.path("/user/**")
          .uri("lb://user-service");
      })
      .build();
  }
}
