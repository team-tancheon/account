package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TokenDto {

    String accessToken;

    String refreshToken;

}
