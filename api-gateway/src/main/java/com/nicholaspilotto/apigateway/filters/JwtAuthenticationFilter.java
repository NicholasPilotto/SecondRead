package com.nicholaspilotto.apigateway.filters;

import com.nicholaspilotto.apigateway.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserDetailsService userDetailsService;

  private final TokenService tokenService;

  public JwtAuthenticationFilter(UserDetailsService userDetailsService, TokenService tokenService) {
    this.userDetailsService = userDetailsService;
    this.tokenService = tokenService;
  }

  /**
   * Manages the filters chain, decoding JWT from the request.
   *
   * @param request intercepted incoming request.
   * @param response intercepted out coming response.
   * @param filterChain list of filters we need to execute.
   * @throws ServletException exception.
   * @throws IOException exception.
   */
  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String bearerSuffix = "Bearer ";

    final String userEmail;

    if (authHeader == null || authHeader.startsWith(bearerSuffix)) {
      filterChain.doFilter(request, response);
      return;
    }

    // get jwt from Bearer authorization header
    jwt = authHeader.substring(bearerSuffix.length());
    userEmail = tokenService.extractUserEmail(jwt);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

      if (tokenService.isTokenValid(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );

        authToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}