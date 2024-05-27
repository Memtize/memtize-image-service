package com.memtize.image.service.util;

import java.io.Serial;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  @Serial private static final long serialVersionUID = -7782365519284093962L;

  private final ErrorType error;

  public ApplicationException(ErrorType error, String message) {
    super(message);
    this.error = error;
  }

  public ApplicationException(ErrorType error, Throwable cause, String message) {
    super(message, cause);
    this.error = error;
  }
}
