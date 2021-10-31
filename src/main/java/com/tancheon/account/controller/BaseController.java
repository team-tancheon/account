package com.tancheon.account.controller;

import com.tancheon.account.api.ErrorCode;
import com.tancheon.account.api.response.ErrorResponse;
import com.tancheon.account.api.response.ErrorResponseMessage;
import com.tancheon.account.api.response.ResponseMessage;
import com.tancheon.account.api.response.WrapperResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<T> {

    protected ResponseEntity<ResponseMessage> responseOk() {
        ResponseMessage message = WrapperResponseMessage.<T>builder().build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseMessage> responseOk(T data) {
        ResponseMessage message = WrapperResponseMessage.<T>builder().data(data).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseMessage> responseOk(List<T> list) {
        ResponseMessage message = WrapperResponseMessage.<List<T>>builder().data(list).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseMessage> responseError(ErrorCode errorCode) {
        ErrorResponse error = ErrorResponse.of(errorCode);
        ResponseMessage message = ErrorResponseMessage.<ErrorResponse>builder().error(error).build();
        return new ResponseEntity<>(message, HttpStatus.valueOf(errorCode.getHttpStatus()));
    }

    protected ResponseEntity<ResponseMessage> responseError(ErrorCode errorCode, ErrorResponse.FieldError fieldError) {
        ErrorResponse error = ErrorResponse.of(errorCode, fieldError);
        ResponseMessage message = ErrorResponseMessage.<ErrorResponse>builder().error(error).build();
        return new ResponseEntity<>(message, HttpStatus.valueOf(errorCode.getHttpStatus()));
    }
}