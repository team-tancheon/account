package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AccountDto {

	private String id;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

	@NotBlank
	private String state;

	private long createDate;

	private long changeDate;

	private long passwordChangeDate;

	private boolean isReceiveMarketingMail;

	private String alertEmailFrequency;

	private boolean isSuggestActivation;

	private boolean isColorblindFriendly;

	private boolean isTwoFactorAuthentication;

	private TimeZoneDto timeZoneId;

	private LanguageDto language;

}
