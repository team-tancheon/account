package com.tancheon.account.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tancheon.account.api.ApiConstant;
import com.tancheon.account.config.JwtTokenProvider;
import com.tancheon.account.domain.Account;
import com.tancheon.account.repository.AccountRepository;
import com.tancheon.account.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthorizationInterceptor extends HandlerInterceptorAdapter {

	private final JwtTokenProvider jwtTokenProvider;
	private final SessionRepository sessionRepository;
	private final AccountRepository accountRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String header = request.getHeader(ApiConstant.AUTHORIZATION);

		if (!StringUtils.hasText(header))
			return false;

		String[] tokens = header.split(ApiConstant.TOKEN_PREFIX);

		if (tokens.length < ApiConstant.TOKEN_LENGTH)
			return false;

		String jwt = tokens[1];

		/*
			TODO: access token 만료 시, refresh 토큰 확인하여 access token 발급하는 과정 필요
		 */

		// 1. 유효한 토큰인지 검증
		jwtTokenProvider.verifyToken(jwt);

		// 2. 사용자 정보 토대로 유효성 검증
		DecodedJWT decodedJWT = JWT.decode(jwt);
		String jwtKey = decodedJWT.getClaim("JWT_KEY").asString();

		Account account = findAccountById(jwtKey)
				.orElseThrow(() -> new IllegalArgumentException("not fount account"));
		checkAccountSatus(account);

		// 3. 만료된 토큰인지 체크
		Date nowDate = new Date(System.currentTimeMillis());
		if(nowDate.after(decodedJWT.getExpiresAt())){
			throw new JWTVerificationException("expired access token");
		}

		return super.preHandle(request, response, handler);
	}

	private void checkAccountSatus(Account account) throws IllegalAccessException {

		String status = account.getState();

		// 비활성화 상태의 사용자인 경우 인가 제한
		if("INACTIVE".equals(status)){
			throw new IllegalAccessException("inactive user");
		}

		//TODO: 인가 허용 범위 지정 필요
	}

	private Optional<Account> findAccountById(String accountId) {
		return accountRepository.findById(accountId);
	}
}