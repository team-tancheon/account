package com.tancheon.account.filter;

import com.tancheon.account.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	static final String[] EXCLUDE_URL = { "/assest/", "/account/v1/api-docs/",
			"/configuration/ui", "/swagger-resources/", "/configuration/", "/swagger-resources", "/swagger-ui.html",
			"/webjars/**" };

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	private SessionRepository sessionRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		


		// Continue filter execution
		chain.doFilter(request, response);
	}

}