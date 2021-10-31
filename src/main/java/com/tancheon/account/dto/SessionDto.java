package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class SessionDto {

	private String id;

	private AccountDto account;

	private String deviceName;

	private String browserName;

	private long lastAccessDate;

	@NotBlank
	private String token;

}
