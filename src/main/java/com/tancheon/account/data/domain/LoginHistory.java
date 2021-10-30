package com.tancheon.account.data.domain;

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
	String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;

	@Column(name = "login_date")
	long loginDate;

}
