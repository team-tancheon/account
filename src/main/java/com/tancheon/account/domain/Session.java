package com.tancheon.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="session")
@Getter @Setter
public class Session {

	@Id
	@Column(name = "id", length=32)
	private String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "device_name", length=256)
	private String deviceName;

	@Column(name = "browser_name", length=256)
	private String browserName;

	@Column(name = "last_access_date")
	long lastAccessDate;

	@NotBlank
	@Column(name = "token", length=256)
	private String token;

}
