package com.tancheon.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="login_history")
@Getter @Setter
public class LoginHistory {

	@Id
	@Column(name = "id", length=32)
	private String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "login_date")
	private long loginDate;

}
