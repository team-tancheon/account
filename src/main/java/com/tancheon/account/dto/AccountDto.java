package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AccountDto {
	
	String id;

	@NotBlank
	String email;

	@NotBlank
	String password;

	@NotBlank
	String name;

	@NotBlank
	String state;
	
	long createDate;
	
	long changeDate;
	
	long passwordChangeDate;
	
	boolean isReceiveMarketingMail;
	
	String alertEmailFrequency;
	
	boolean isSuggestActivation;
	
	boolean isColorblindFriendly;
	
	boolean isTwoFactorAuthentication;

	TimeZoneDto timeZoneId;

	LanguageDto language;

}
