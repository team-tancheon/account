package com.tancheon.account.controller;

import com.tancheon.account.api.ApiConstant;
import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.dto.TokenDto;
import com.tancheon.account.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/account/v1")
@RestController
public class LoginController extends BaseController {   //TODO: BaseController 타입 지정 필요

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestHeader("User-Agent") String userAgent,
                                          @RequestBody @Valid AccountDto accountDto) {

        TokenDto tokens = loginService.login(userAgent, accountDto);
        return responseOk(tokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("User-Agent") String userAgent,
                                         @RequestHeader(ApiConstant.AUTHORIZATION) String token) {

        loginService.logout(userAgent, token);
        return responseOk("");
    }
}

