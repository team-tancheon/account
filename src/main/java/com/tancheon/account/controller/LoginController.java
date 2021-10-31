package com.tancheon.account.controller;

import com.tancheon.account.api.JwtProperties;
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
public class LoginController extends BaseController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestHeader("User-Agent") String userAgent,
                                          @RequestBody @Valid AccountDto accountDto) {

        TokenDto tokens = loginService.login(userAgent, accountDto);
        return responseOk(tokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("User-Agent") String userAgent,
                                         @RequestHeader(JwtProperties.HEADER_STRING) String headerString) {

        loginService.logout(userAgent, headerString);

        return responseOk("");
    }


}

