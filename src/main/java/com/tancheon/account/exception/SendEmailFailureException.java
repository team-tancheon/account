package com.tancheon.account.exception;

public class SendEmailFailureException extends RuntimeException {
    public SendEmailFailureException(String message) {
        super(message);
    }

    public SendEmailFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendEmailFailureException(Throwable cause) {
        super(cause);
    }
}
