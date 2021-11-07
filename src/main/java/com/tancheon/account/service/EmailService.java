package com.tancheon.account.service;

public interface EmailService {

    void sendSignupConfirmationEmail(String to, String language, int verifyCode);
}
