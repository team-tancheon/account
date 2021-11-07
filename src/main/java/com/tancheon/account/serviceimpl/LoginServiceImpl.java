package com.tancheon.account.serviceimpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tancheon.account.api.ApiConstant;
import com.tancheon.account.config.JwtTokenProvider;
import com.tancheon.account.domain.Account;
import com.tancheon.account.domain.Session;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.dto.TokenDto;
import com.tancheon.account.repository.AccountRepository;
import com.tancheon.account.repository.SessionRepository;
import com.tancheon.account.service.EmailService;
import com.tancheon.account.service.LoginService;
import com.tancheon.account.utils.KeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final EmailService emailService;
    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public TokenDto login(String userAgent, AccountDto accountDto) {

        Account account = accountRepository.findByEmail(accountDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("login fail"));

        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("login fail");
        }

        String accessToken = jwtTokenProvider.createAccessToken(accountDto);
        String refreshToken = jwtTokenProvider.createRefreshToken(accountDto);

        // browserName setting
        String browserName = getBrowserName(userAgent);
        // deviceName setting
        String deviceName = getDeviceName(userAgent);
        // now date
        long nowDate = System.currentTimeMillis();

        Session session = Session.builder()
                .id(KeyProvider.createKey())
                .account(account)
                .browserName(browserName)
                .deviceName(deviceName)
                .lastAccessDate(nowDate)
                .token(refreshToken)
                .build();

        sessionRepository.save(session); // refresh token db에 저장

        // accessToken과 refreshToken 반환
        TokenDto tokens = new TokenDto();
        tokens.setAccessToken(accessToken);

        return tokens;

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void logout(String userAgent, String token) {

        if (token == null || !token.startsWith(ApiConstant.TOKEN_PREFIX)) {
            return;
        }
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ApiConstant.SECRET_KEY.getBytes())).build()
                .verify(token.replace(ApiConstant.TOKEN_PREFIX, ""));
        String jwtKey = decodedJWT.getClaim("JWT_KEY").asString();

        Session session = sessionRepository.findById(jwtKey)
                .orElseThrow(() -> new IllegalArgumentException("session not found"));
        sessionRepository.delete(session);

    }

    private String getBrowserName(String userAgent) {
        String browserName = "";

        if(userAgent.contains("Chrome")){
            browserName = "Chrome";
        }else if(userAgent.contains("Trident")){
            browserName = "IE";
        }else if(userAgent.contains("Edge")){
            browserName = "Edge";
        }else if(userAgent.contains("Firefox")){
            browserName = "Firefox";
        }else if(userAgent.contains("Opera")){
            browserName = "Opera";
        }else if(userAgent.contains("Whale")){
            browserName = "Whale";
        }

        return browserName;
    }

    private String getDeviceName(String userAgent) {
        String deviceName = "";

        if(userAgent.contains("Windows")) {
            deviceName = "Windows";
        }else if(userAgent.contains("Macintosh")){
            deviceName = "Macintosh";
        }else if(userAgent.contains("Linux")){
            deviceName = "Linux";
        }

        return deviceName;
    }
}
