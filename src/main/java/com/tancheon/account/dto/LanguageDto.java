package com.tancheon.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="language")
@Getter @Setter
public class LanguageDto {

	String id;

	@NotBlank
	String name;

}
