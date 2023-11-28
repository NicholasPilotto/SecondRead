package com.nicholaspilotto.authenticationservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
  @Value("jwt.secret")
  private String secret;

  @Value("jtw.expiration")
  private String expiration;

  private Key key;

  /**
   * Initialize the key object.
   */
  @PostConstruct
  private void initKey() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  /**
   * Get the {@link Claims} from a {@code token}.
   * @param token token form which to extract the {@code Claims}.
   * @return {@code Claims} object from {@code token}.
   */
  public Claims getClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJwt(token)
      .getBody();
  }

  /**
   * Get the expiration date from {@code token}.
   * @param token token from which to extract the expiration date.
   * @return Expiration date of the {@code token}.
   */
  public Date getExpirationDate(String token) {
    return getClaims(token).getExpiration();
  }
}
