package com.learning.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {

    private String statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ResponseEntity<ResponseDto<T>> success(T data) {
        return ResponseEntity.ok(
                ResponseDto.<T>builder()
                        .statusCode(String.valueOf(HttpStatus.OK.value()))
                        .message("Success")
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> success(T data, String message) {
        return ResponseEntity.ok(
                ResponseDto.<T>builder()
                        .statusCode(String.valueOf(HttpStatus.OK.value()))
                        .message(message)
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseDto.<T>builder()
                        .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                        .message("Created")
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> created(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseDto.<T>builder()
                        .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                        .message(message)
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> of(T data, HttpStatus status, String message) {
        return ResponseEntity.status(status).body(
                ResponseDto.<T>builder()
                        .statusCode(String.valueOf(status.value()))
                        .message(message)
                        .data(data)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
