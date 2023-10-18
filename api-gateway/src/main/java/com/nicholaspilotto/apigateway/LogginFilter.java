package com.nicholaspilotto.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class LogginFilter implements GlobalFilter {
  private  final Logger logger = LoggerFactory.getLogger(LogginFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    logger.info("Request path -> {}", exchange.getRequest().getPath());
    return chain.filter(exchange);
  }
}
