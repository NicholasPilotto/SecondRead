package com.nicholaspilotto.apigateway.configuration;

import com.nicholaspilotto.apigateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that redirect requests to the correct service.
 */
@Configuration
@EnableHystrix
public class GatewayConfiguration {
  private final AuthenticationFilter filter;

  /**
   * Creates a new instance of {@link GatewayConfiguration}.
   *
   * @param filter {@link AuthenticationFilter} injected reference.
   */
  @Autowired
  public GatewayConfiguration(AuthenticationFilter filter) {
    this.filter = filter;
  }

  /**
   * Locate requests to its service.
   *
   * @param builder {@link RouteLocatorBuilder} builder object.
   *
   * @return {@link RouteLocator} object.
   */
  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
      .route("user-service", r -> r.path("/user/**")
        .filters(f -> f.filter(filter))
        .uri("lb://user-service")
      )
      .route("auth-service", r -> r.path("/auth/**")
        .filters(f -> f.filter(filter))
          .uri("lb://auth-service")
      )
      .route("book-service", r -> r.path("/book/**")
        .filters(f -> f.filter(filter))
          .uri("lb://book-service")
      )
      .build();
  }
}
