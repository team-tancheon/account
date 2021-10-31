package com.tancheon.account.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tancheon.account.api.JwtProperties;
import com.tancheon.account.config.JwtTokenProvider;
import com.tancheon.account.repository.AccountRepository;
import com.tancheon.account.repository.SessionRepository;
import com.tancheon.account.domain.Account;
import com.tancheon.account.domain.Session;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.dto.TokenDto;
import com.tancheon.account.utils.KeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TokenDto login(String userAgent, AccountDto accountDto) {

        Account account = accountRepository.findByEmail(accountDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("login fail"));

        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("login fail");
        }

        String accessToken = jwtTokenProvider.createAccessToken(accountDto);
        String refreshToken = jwtTokenProvider.createRefreshToken(accountDto);

        Session session = new Session();
        session.setId(KeyProvider.createKey());
        session.setLastAccessDate(System.currentTimeMillis());
        session.setAccount(account);
        session.setToken(refreshToken);

        sessionRepository.save(session);

        // accessToken과 refreshToken 반환
        TokenDto tokens = new TokenDto();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);

        return tokens;

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void logout(String userAgent, String token) {

        if (token == null || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return;
        }
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY.getBytes())).build()
                .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""));
        String jwtKey = decodedJWT.getClaim("JWT_KEY").asString();

        Session session = sessionRepository.findById(jwtKey)
                .orElseThrow(() -> new IllegalArgumentException("session not found"));
        sessionRepository.delete(session);

    }
}
