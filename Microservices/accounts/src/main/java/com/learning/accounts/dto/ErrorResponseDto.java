package com.learning.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {

    private String apiPath;
    private int statusCode;
    private String errorMessage;
    private List<String> errorDetails;
    private LocalDateTime timestamp;

    public static ResponseEntity<ErrorResponseDto> badRequest(String apiPath, String errorMessage, List<String> errorDetails) {
        return build(apiPath, HttpStatus.BAD_REQUEST, errorMessage, errorDetails);
    }

    public static ResponseEntity<ErrorResponseDto> notFound(String apiPath, String errorMessage, List<String> errorDetails) {
        return build(apiPath, HttpStatus.NOT_FOUND, errorMessage, errorDetails);
    }

    public static ResponseEntity<ErrorResponseDto> internalError(String apiPath, String errorMessage, List<String> errorDetails) {
        return build(apiPath, HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, errorDetails);
    }

    public static ResponseEntity<ErrorResponseDto> build(String apiPath, HttpStatus status, String errorMessage, List<String> errorDetails) {
        return ResponseEntity.status(status).body(
                ErrorResponseDto.builder()
                        .apiPath(apiPath)
                        .statusCode(status.value())
                        .errorMessage(errorMessage)
                        .errorDetails(errorDetails)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
