package com.sanish.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the error occurred")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error occurred")
    private String errorMessage;

    @Schema(description = "Time when error occurred")
    private LocalDateTime errorTime;
}
