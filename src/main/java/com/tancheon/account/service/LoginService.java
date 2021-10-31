package com.tancheon.account.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tancheon.account.api.JwtProperties;
import com.tancheon.account.config.JwtTokenProvider;
import com.tancheon.account.dao.repository.AccountRepository;
import com.tancheon.account.dao.repository.SessionRepository;
import com.tancheon.account.domain.Account;
import com.tancheon.account.domain.Session;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.utils.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    private AccountRepository accountRepo;
    private SessionRepository sessionRepo;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void login(String userAgent, AccountDto req, HttpServletResponse response) {

        Account account = accountRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("login fail"));

        if (!passwordEncoder.matches(req.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("login fail");
        }

        String accessToken = jwtTokenProvider.createAccessToken(req);
        String refreshToken = jwtTokenProvider.createRefreshToken(req);

        Session session = new Session();
        session.setId(KeyProvider.createKey());
        session.setDeviceName("");
        session.setBrowserName("");
        session.setLastAccessDate(System.currentTimeMillis());
        session.setAccount(account);
        session.setToken(refreshToken);

        sessionRepo.save(session);

        //TODO: reponse Cookie에 accessToken과 refreshToken을 담아야 함.
        response.addCookie(new Cookie("accessToken", accessToken));
        response.addCookie(new Cookie("refreshToken", accessToken));

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void logout(String userAgent, String token) {

        if (token == null || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return;
        }
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY.getBytes())).build()
                .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""));
        String jwtKey = decodedJWT.getClaim("JWT_KEY").asString();

        Session session = sessionRepo.findByKey(jwtKey)
                .orElseThrow(() -> new IllegalArgumentException("session not found"));
        sessionRepo.delete(session);

        //TODO: Cookie에서 accessToken clear 해야함.

    }
}
