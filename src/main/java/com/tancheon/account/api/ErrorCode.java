package com.tancheon.account.api;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_REQUEST_PARAM("","Invalid request parameter", 400),
    METHOD_NOT_ALLOWED("", "Method not allowed", 405),
    INTERNAL_ERROR("", "Internal error", 500);

    private final String code;
    private final String message;
    private final int httpStatus;

    ErrorCode(String code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
