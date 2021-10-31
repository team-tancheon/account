package com.tancheon.account.controller;

import com.tancheon.account.api.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController<T> {

    public ResponseEntity responseOk() {
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity responseOk(T data) {
        return new ResponseEntity(data, HttpStatus.OK);
    }

    public ResponseEntity responseError(ErrorCode errorCode) {
        return new ResponseEntity<>(errorCode, HttpStatus.valueOf(errorCode.getHttpStatus()));
    }
}