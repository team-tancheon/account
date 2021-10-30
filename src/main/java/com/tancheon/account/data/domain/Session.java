package com.tancheon.account.data.domain;

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
	String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;

	@Column(name = "device_name", length=256)
	String deviceName;

	@Column(name = "browser_name", length=256)
	String browserName;

	@Column(name = "last_access_date")
	long lastAccessDate;

	@NotBlank
	@Column(name = "token", length=256)
	String token;

}
