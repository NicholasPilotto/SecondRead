package com.nicholaspilotto.bookservice.configuration;

import com.nicholaspilotto.bookservice.interceptors.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Implementation of {@link WebMvcConfigurer} to add {@link RoleInterceptor}.
 */
@Component
public class WebConfiguration implements WebMvcConfigurer {
  private final RoleInterceptor roleInterceptor;

  @Autowired
  public WebConfiguration(RoleInterceptor roleInterceptor) {
    this.roleInterceptor = roleInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(roleInterceptor).addPathPatterns("/**");
  }
}
