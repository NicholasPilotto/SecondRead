package com.nicholaspilotto.apigateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Manage all the actions to build, decode and validate a {@code JWT}.
 */
@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String secret;

  private Key key;

  @PostConstruct
  public void initKey() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  /**
   * Check if a token has expired.
   *
   * @param token token to check.
   *
   * @return {@code true} if token has expired, otherwise, {@code false}.
   */
  public boolean isExpired(String token) {
    try {
      return getClaims(token).getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }
}
