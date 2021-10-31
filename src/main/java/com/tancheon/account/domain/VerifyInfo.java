package com.tancheon.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="verify_info")
@Getter @Setter
public class VerifyInfo {

	@Id
	@Column(name = "id", length=32)
	private String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@NotBlank
	@Column(name = "verify_code", length=6)
	private String verifyCode;

	@Column(name = "create_date")
	private long createDate;

	@Column(name = "expire_date")
	private long expireDate;

}
