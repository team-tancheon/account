package com.tancheon.account.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tancheon.account.api.ApiConstant;
import com.tancheon.account.domain.Session;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.utils.KeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final String secretKey = Base64.getEncoder()
            .encodeToString(ApiConstant.SECRET_KEY.getBytes());

    public String createAccessToken(AccountDto account) {

        Session session = new Session();
        session.setId(KeyProvider.createKey());

        // access token 생성
        String accessToken = JWT.create()
                .withSubject(account.getId())
                .withClaim("JWT_KEY", session.getId())
                .withClaim("ROLE", account.getState()) //TODO: token 생성 시, ROLE 필요 -> 임시로 State 값으로 넣어두었으나, 별도 필드값 필요해 보임.
                .withExpiresAt(new Date(System.currentTimeMillis() + ApiConstant.ACCESS_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secretKey));

        return ApiConstant.TOKEN_PREFIX + accessToken;
    }

    public String createRefreshToken(AccountDto account) {

        Session session = new Session();
        session.setId(KeyProvider.createKey());

        // access token 생성
        String refreshToken = JWT.create()
                .withSubject(account.getId())
                .withClaim("JWT_KEY", session.getId())
                .withClaim("ROLE", account.getState()) //TODO: token 생성 시, ROLE 필요 -> 임시로 State 값으로 넣어두었으나, 별도 필드값 필요해 보임.
                .withExpiresAt(new Date(System.currentTimeMillis() + ApiConstant.REFRESH_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secretKey));

        return ApiConstant.TOKEN_PREFIX + refreshToken;
    }


    public boolean validateToken(String userAgent, String token ) {

        //TODO: 토큰 유효성 검증 및 decode

        return true;
    }

    public void removeToken(String userAgent, String token) {
        //TODO: 토큰 유효하지 않게 처리

        //TODO: 로그 저장 (로그아웃 로그)
    }
}
