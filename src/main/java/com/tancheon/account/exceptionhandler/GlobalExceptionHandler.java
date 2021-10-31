package com.tancheon.account.exceptionhandler;

import com.tancheon.account.api.ErrorCode;
import com.tancheon.account.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleContraintViolationException(ConstraintViolationException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM, toFieldErrors(e.getConstraintViolations()));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM, toFieldErrors(e.getBindingResult()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM, toFieldErrors(e.getBindingResult()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM,
                ErrorResponse.FieldError.of(e.getParameterName(), null, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM,
                ErrorResponse.FieldError.of(e.getName(), e.getValue().toString(), e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ErrorResponse.of(ErrorCode.INVALID_REQUEST_PARAM);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknownException(Throwable t) {
        log.error("unknown error : " + t.getMessage());
        return ErrorResponse.of(ErrorCode.INTERNAL_ERROR);
    }

    private List<ErrorResponse.FieldError> toFieldErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> ErrorResponse.FieldError.of(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private List<ErrorResponse.FieldError> toFieldErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> ErrorResponse.FieldError.of(
                        violation.getPropertyPath().toString(),
                        violation.getInvalidValue().toString(),
                        violation.getMessage()))
                .collect(Collectors.toList());
    }
}
