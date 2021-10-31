package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class SessionDto {

	String id;

	AccountDto account;

	String deviceName;

	String browserName;

	long lastAccessDate;

	@NotBlank
	String token;

}
