package com.tancheon.account.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="language")
@Getter @Setter
public class Language {

	@Id
	@Column(name = "id", length=32)
	String id;

	@NotBlank
	@Column(name = "name", length=20)
	String name;

}