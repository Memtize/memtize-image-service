package com.memtize.image.service.api.dto;

import com.memtize.image.service.util.DateTimeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorForm extends Form {

  @Schema(description = "error date")
  private LocalDateTime timestamp;

  @Schema(description = "http error status")
  private int status;

  @Schema(description = "http error code")
  private String error;

  @Schema(description = "error message")
  private String message;

  @Schema(description = "requested endpoint")
  private String path;

  public String getTimestamp() {
    return this.timestamp != null ? this.timestamp.format(DateTimeUtil.DEFAULT_DT_PATTERN) : null;
  }
}
