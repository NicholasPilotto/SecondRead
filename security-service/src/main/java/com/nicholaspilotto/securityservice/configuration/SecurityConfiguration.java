package com.nicholaspilotto.securityservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Autowired
public SecurityConfiguration(
  JwtAuthenticationFilter jwtAuthFilter,
  AuthenticationProvider authenticationProvider
  ) {
  this.jwtAuthFilter = jwtAuthFilter;
  this.authenticationProvider = authenticationProvider;
  }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
  httpSecurity.csrf(AbstractHttpConfigurer::disable)
  .authorizeHttpRequests(
  requests -> requests
  .requestMatchers("localhost:8765/user/count").permitAll()
  .requestMatchers("/auth/register").permitAll()
  )
  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  .authenticationProvider(authenticationProvider)
  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

  return httpSecurity.build();
  }
  }