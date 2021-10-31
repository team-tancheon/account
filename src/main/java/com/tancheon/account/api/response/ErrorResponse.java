package com.tancheon.account.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tancheon.account.api.ErrorCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class ErrorResponse {

    @JsonIgnore
    private int httpStatus;
    private final String code;
    private final String message;
    private final List<FieldError> errors;

    private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.errors = errors;
        this.code = errorCode.getCode();
    }

    private ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, FieldError error) {
        return new ErrorResponse(code, Arrays.asList(error));
    }

    public static ErrorResponse of(ErrorCode code, List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Getter
    public static class FieldError {
        private final String field;
        private final String value;
        private final String message;

        private FieldError(String field, String value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        public static FieldError of(String field, String value, String message) {
            return new FieldError(field, value, message);
        }
    }
}
