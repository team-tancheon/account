package com.tancheon.account.data.domain;

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
	String id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;

	@NotBlank
	@Column(name = "verify_code", length=6)
	String verifyCode;

	@Column(name = "create_date")
	long createDate;

	@Column(name = "expire_date")
	long expireDate;

}
