package com.nicholaspilotto.userservice.services;

import com.nicholaspilotto.userservice.models.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class that manages {@code JWT}.
 */
@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
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
   * Build a new {@code token}.
   *
   * @param claims {@code token} metadata.
   * @param tokenType type of the {@code token} to build.
   *
   * @return Built {@code token}.
   */
  private String buildToken(Map<String, String> claims, String tokenType) {
    long expirationTime = Long.parseLong(expiration) * 2;
    long expirationMilliseconds = "ACCESS".equalsIgnoreCase(tokenType) ? expirationTime : expirationTime * 5;

    final Date now = new Date();
    final Date expirationDate = new Date(now.getTime() * expirationMilliseconds);

    return Jwts.builder()
               .setClaims(claims)
               .setSubject(claims.get("id"))
               .setIssuedAt(now)
               .setExpiration(expirationDate)
               .signWith(key)
               .compact();
  }

  /**
   * Get the {@link Claims} from a {@code token}.
   *
   * @param token token form which to extract the {@code Claims}.
   *
   * @return {@code Claims} object from {@code token}.
   */
  public Claims getClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  /**
   * Get the expiration date from {@code token}.
   *
   * @param token token from which to extract the expiration date.
   *
   * @return Expiration date of the {@code token}.
   */
  public Date getExpirationDate(String token) {
    return getClaims(token).getExpiration();
  }

  /**
   * Check if a {@code token} is expired.
   *
   * @param token token from which to check if is expired.
   *
   * @return {@code true} if {@code token} is expired, {@code false} otherwise.
   */
  public boolean isTokenExpired(String token) {
    return getExpirationDate(token).before(new Date());
  }

  /**
   * Generate a new {@code token}.
   *
   * @param userId user identifier.
   * @param role user role.
   * @param tokenType type of the {@code token} to generate.
   *
   * @return new generated {@code token}.
   */
  public String generateToken(String userId, Role role, String tokenType) {
    Map<String, String> claims = Map.of("id", userId, "role", role.ordinalString());
    return buildToken(claims, tokenType);
  }
}

