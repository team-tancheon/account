package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginHistoryDto {

	private String id;

	private AccountDto account;

	private long loginDate;

}
