package com.learning.accounts.configuration;

import com.learning.accounts.dto.ErrorResponseDto;
import com.learning.accounts.exception.ResourceNotFoundException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Validation failed",
                details
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Validation failed",
                details
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Malformed request body",
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.notFound(
                request.getRequestURI(),
                ex.getMessage(),
                List.of("The requested resource was not found")
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElement(
            NoSuchElementException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.notFound(
                request.getRequestURI(),
                "Resource not found",
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponseDto> handleFeignException(
            FeignException ex,
            HttpServletRequest request) {

        if (ex.status() == HttpStatus.NOT_FOUND.value()) {
            return ErrorResponseDto.notFound(
                    request.getRequestURI(),
                    "Resource not found in downstream service",
                    List.of("The requested resource was not found in the cards service")
            );
        }

        return ErrorResponseDto.internalError(
                request.getRequestURI(),
                "Downstream service error",
                List.of("The cards service could not be reached. Please try again later.")
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Data integrity violation",
                List.of("The operation could not be completed due to a data conflict. Please check your input and try again.")
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Invalid argument",
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingParam(
            MissingServletRequestParameterException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Missing request parameter",
                List.of(ex.getParameterName() + " parameter is required")
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {

        String supportedMethods = ex.getSupportedMethods() != null
                ? String.join(", ", ex.getSupportedMethods())
                : "N/A";

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "HTTP method not supported",
                List.of("Supported methods: " + supportedMethods)
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpServletRequest request) {

        return ErrorResponseDto.badRequest(
                request.getRequestURI(),
                "Media type not supported",
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        String message = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";

        return ErrorResponseDto.internalError(
                request.getRequestURI(),
                "An unexpected error occurred",
                List.of(message)
        );
    }
}
