package com.nicholaspilotto.userservice.interceptors;

import com.nicholaspilotto.userservice.annotations.RoleAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod handlerMethod) {
      RoleAdmin roleAdmin = handlerMethod.getMethodAnnotation(RoleAdmin.class);

      if (roleAdmin == null) {
        roleAdmin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RoleAdmin.class);
      }
    }

    return true;
  }
}
