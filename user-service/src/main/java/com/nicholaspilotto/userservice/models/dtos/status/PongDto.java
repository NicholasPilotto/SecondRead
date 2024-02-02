package com.nicholaspilotto.userservice.models.dtos.status;

import java.time.Instant;

/**
 * Represents the response model for ping-pong requests.
 */
public class PongDto {
  private static final String ping = "pong";
  private static final long timestamp = Instant.now().getEpochSecond();

  public PongDto() { }

  public String getPing() {
    return ping;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
