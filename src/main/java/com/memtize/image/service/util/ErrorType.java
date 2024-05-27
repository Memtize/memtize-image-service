package com.memtize.image.service.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {
  ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND),
  MANDATORY_PARAMETER(HttpStatus.BAD_REQUEST),
  CURRENTLY_IN_USE(HttpStatus.CONFLICT),
  ALREADY_EXISTS(HttpStatus.CONFLICT),
  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
  FORBIDDEN(HttpStatus.FORBIDDEN),
  UNSUPPORTED_TYPE(HttpStatus.BAD_REQUEST),
  ENTITY_STATE_CHANGED(HttpStatus.CONFLICT),
  INCORRECT_VALUE_TYPE(HttpStatus.BAD_REQUEST);

  private final HttpStatus httpStatus;
}
