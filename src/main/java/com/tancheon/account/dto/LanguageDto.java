package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LanguageDto {

	private String id;

	@NotBlank
	private String name;

}
