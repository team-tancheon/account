package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifyInfoDto {

	private String id;

	private AccountDto account;

	private String verifyCode;

	private long createDate;

	private long expireDate;

}
