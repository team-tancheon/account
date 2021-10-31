package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginHistoryDto {

	String id;

	AccountDto account;

	long loginDate;

}
