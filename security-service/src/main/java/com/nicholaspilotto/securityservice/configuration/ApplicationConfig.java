package com.nicholaspilotto.securityservice.configuration;

import com.nicholaspilotto.securityservice.services.UserDetailsServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  private final UserDetailsServiceFeign userDetailsServiceFeign;

  @Autowired
  public ApplicationConfig(UserDetailsServiceFeign userDetailsServiceFeign) {
    this.userDetailsServiceFeign = userDetailsServiceFeign;
  }

  @Bean
  public UserDetailsService userDetailsService() throws UsernameNotFoundException {
    return userDetailsServiceFeign;
  }

  @Bean
  public AuthenticationProvider authenticationProvider () {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}