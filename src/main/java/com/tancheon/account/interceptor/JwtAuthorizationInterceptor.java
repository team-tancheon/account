package com.tancheon.account.interceptor;

import com.tancheon.account.api.ApiConstant;
import com.tancheon.account.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtAuthorizationInterceptor extends HandlerInterceptorAdapter {

	private SessionRepository sessionRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String header = request.getHeader(ApiConstant.AUTHORIZATION);

		/*
			TODO: 토큰에 대한 인증 처리 필요
			인증 로직을 인터셉터에서 처리하면 뒤에서는 필요한 정보만 JWT에서 뽑아서 처리하면 되니 코드가 단순해질 듯
		 */

		return true;
	}
}