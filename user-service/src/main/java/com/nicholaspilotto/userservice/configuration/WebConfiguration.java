package com.nicholaspilotto.userservice.configuration;

import com.nicholaspilotto.userservice.interceptors.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
