package com.nicholaspilotto.userservice.interceptors;

import com.nicholaspilotto.userservice.annotations.AuthorizedRoles;
import com.nicholaspilotto.userservice.constants.ProjectConstants;
import com.nicholaspilotto.userservice.models.entities.Role;
import com.nicholaspilotto.userservice.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor class used to filter request based on {@code User} roles.
 */
@Component
public class RoleInterceptor implements HandlerInterceptor {
  private final JwtService jwtService;

  /**
   * Initializes a new instance of {@code RoleInterceptor} object.
   *
   * @param jwtService {@code JwtService} reference injection.
   */
  @Autowired
  public RoleInterceptor(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  /**
   * Check if the method is protected with {@code Role}.s
   *
   * @param request current request object.
   * @param response response object.
   * @param handler request handler.
   *
   * @return {@code true} if the request can be executed, otherwise, {@code false}.
   *
   * @throws Exception when something went wrong.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod handlerMethod) {
      AuthorizedRoles authorizedRoles = handlerMethod.getMethodAnnotation(AuthorizedRoles.class);

      if (authorizedRoles == null) {
        return true;
      }

      String token = request
        .getHeader(ProjectConstants.HttpHeaders.AUTHORIZATION)
        .replace(ProjectConstants.HttpHeaders.BEARER, ProjectConstants.StringConstants.EMPTY);

      if (token.isBlank()) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("User not authenticated or not allowed to access to this resource.");
        return false;
      }

      boolean isExpired = jwtService.isTokenExpired(token);
      Role role = Role.values()[Integer.parseInt(
        jwtService
          .getClaims(token)
          .get(ProjectConstants.Claims.ROLE, String.class)
      )];

      boolean match = role == Role.ADMIN || Arrays.stream(authorizedRoles.authorized()).anyMatch(r -> r == role);

      if (token.isBlank() || isExpired || !match) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter()
                .write("User not authenticated or not allowed to access to this resource.");
        return false;
      }
    }

    return true;
  }
}
