package com.tancheon.account.api;

public interface ApiConstant {

    // JWT Token을 hash 할 때 사용할 secret key
    String SECRET_KEY = "asdf1234!";

    // JWT Token의 validation 기간
    long REFRESH_EXPIRATION_TIME = 60 * 60 * 24 * 7 * 1000L;    // 1 week
    long ACCESS_EXPIRATION_TIME = 30 * 60 * 1000L;               // 30 minute

    // JWT Token의 prefix는 Bearer
    String TOKEN_PREFIX = "Bearer ";
    int TOKEN_LENGTH = 2;

    // JWT Token은 Authorization header로 전달됩니다.
    String AUTHORIZATION = "Authorization";

    String ISSUER = "TANCHEON";
}
