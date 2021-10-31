package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifyInfoDto {

	String id;

	AccountDto account;

	String verifyCode;

	long createDate;

	long expireDate;

}
