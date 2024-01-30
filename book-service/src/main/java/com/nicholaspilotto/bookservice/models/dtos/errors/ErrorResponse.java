package com.nicholaspilotto.bookservice.models.dtos.errors;

import org.springframework.http.HttpStatus;

/**
 * Represents an error response model.
 */
public class ErrorResponse {
  private HttpStatus status;
  private int code;
  private String message;

  /**
   * Creates a new instance of {@link ErrorResponse} object.
   *
   * @param status {@link HttpStatus} of the response.
   * @param message response message.
   */
  public ErrorResponse(HttpStatus status, String message) {
    this.status = status;
    this.code = status.value();
    this.message = message;
  }

  /**
   * Gets the current status.
   *
   * @return the {@link HttpStatus} of the current response.
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * Sets the status to the current response.
   *
   * @param status {@link HttpStatus} of the current response.
   */
  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  /**
   * Gets ths code of the current response.
   *
   * @return code of the current response.
   */
  public int getCode() {
    return code;
  }

  /**
   * Sets the code of the current response.
   *
   * @param code status code if the current response.
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets the message of the current response.
   *
   * @return the message string of the current response.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message of the current response.
   *
   * @param message message string of the current response.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Get the current response as {@link String} object.
   *
   * @return {@link String} representing the current object.
   */
  @Override
  public String toString() {
    return "ErrorResponse: {"
      + "status=" + status
      + ", code=" + code
      + ", message='" + message + '\''
      + '}';
  }
}
