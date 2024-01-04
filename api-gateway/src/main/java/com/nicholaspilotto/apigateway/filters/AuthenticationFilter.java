package com.nicholaspilotto.apigateway.filters;

import com.nicholaspilotto.apigateway.configuration.RouteValidator;
import com.nicholaspilotto.apigateway.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Represents a filter applied to the request to validate and authenticate it.
 */
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
  private final RouteValidator validator;
  private final JwtService jwtService;

  /**
   * Creates a new instance of {@link AuthenticationFilter} object.
   *
   * @param validator {@link RouteValidator} injected reference.
   * @param jwtService {@link JwtService} injected reference.
   */
  @Autowired
  public AuthenticationFilter(RouteValidator validator, JwtService jwtService) {
    this.validator = validator;
    this.jwtService = jwtService;
  }

  /**
   * Implementation of {@link GatewayFilter#filter(ServerWebExchange, GatewayFilterChain)}.
   *
   * @param exchange {@link ServerWebExchange} object.
   * @param chain {@link GatewayFilterChain} object.
   *
   * @return {@link Mono} task.
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();

    if (validator.isSecured.test(request)) {
      if (authMissing(request)) {
        return onError(exchange);
      }

      final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

      if (jwtService.isExpired(token)) {
        return onError(exchange);
      }
    }

    return chain.filter(exchange);
  }

  /**
   * Error response.
   *
   * @param exchange {@link ServerWebExchange} object.
   *
   * @return {@link Mono} task.
   */
  private Mono<Void> onError(ServerWebExchange exchange) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(HttpStatus.UNAUTHORIZED);
    return response.setComplete();
  }

  /**
   * Check if {@code Authorization} header is missing.
   *
   * @param request current {@link org.springframework.http.server.ServerHttpRequest} object.
   *
   * @return {@code true} if {@code Authorization} header is missing, otherwise, {@code false}.
   */
  private boolean authMissing(ServerHttpRequest request) {
    return !request.getHeaders().containsKey("Authorization");
  }
}
