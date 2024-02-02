package com.nicholaspilotto.apigateway.configuration;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

/**
 * Represents the validator class for the open route.
 */
@Service
public class RouteValidator {
  /**
   * List of all open endpoints of {@code Authentication service}.
   */
  private static final List<String> authenticationOpenEndpoints = List.of(
    "auth/register",
    "auth/login",
    "/ping"
  );

  /**
   * Contains all routes that do not need token authentication.
   */
  public static final List<String> openEndpoints = Stream.of(authenticationOpenEndpoints)
    .flatMap(Collection::stream)
    .collect(Collectors.toList());

  /**
   * Check if a request is an open URL or not.
   */
  public Predicate<ServerHttpRequest> isSecured = request ->
    openEndpoints.stream()
                 .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
