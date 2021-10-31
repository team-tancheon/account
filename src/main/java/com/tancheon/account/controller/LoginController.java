package com.tancheon.account.controller;

import com.tancheon.account.api.JwtProperties;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.dto.TokenDto;
import com.tancheon.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RequestMapping("/account/v1")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestHeader("User-Agent") String userAgent
            , @RequestBody @Valid AccountDto request
            , HttpServletResponse response) {

        TokenDto tokens = loginService.login(userAgent, request, response);
       return responseOk( tokens );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> login(@RequestHeader("User-Agent") String userAgent, HttpServletRequest request) {

        loginService.logout(userAgent, request.getHeader(JwtProperties.HEADER_STRING));

        return responseOk("");
    }


}

