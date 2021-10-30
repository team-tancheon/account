package com.tancheon.account.data.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="account")
@Getter @Setter
public class Account {
	
	@Id
	@Column(name = "id", length=32)
	String id;

	@NotBlank
	@Column(name = "email", length=256)
	String email;

	@NotBlank
	@Column(name = "password", length=256)
	String password;

	@NotBlank
	@Column(name = "name", length=256)
	String name;

	@NotBlank
	@Column(name = "state", length=16)
	String state;
	
	@Column(name = "create_date")
	long createDate;
	
	@Column(name = "change_date")
	long changeDate;
	
	@Column(name = "password_change_date")
	long passwordChangeDate;
	
	@Column(name = "is_receive_marketing_mail")
	boolean isReceiveMarketingMail;
	
	@Column(name = "alert_email_frequency", length=16)
	String alertEmailFrequency;
	
	@Column(name = "is_suggest_activation")
	boolean isSuggestActivation;
	
	@Column(name = "is_colorblind_friendly")
	boolean isColorblindFriendly;
	
	@Column(name = "is_twoFactor_authentication")
	boolean isTwoFactorAuthentication;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "time_zone_id")
	TimeZone timeZoneId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "language_id")
	Language language;

}
