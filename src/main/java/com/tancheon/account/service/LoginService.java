package com.tancheon.account.service;

import com.tancheon.account.dto.AccountDto;
import com.tancheon.account.dto.TokenDto;

public interface LoginService {

    TokenDto login(String userAgent, AccountDto accountDto);

    void logout(String userAgent, String token);
}
