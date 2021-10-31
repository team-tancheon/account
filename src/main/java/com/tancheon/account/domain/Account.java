package com.tancheon.account.domain;

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
	private String id;

	@NotBlank
	@Column(name = "email", length=256)
	private String email;

	@NotBlank
	@Column(name = "password", length=256)
	private String password;

	@NotBlank
	@Column(name = "name", length=256)
	private String name;

	@NotBlank
	@Column(name = "state", length=16)
	private String state;
	
	@Column(name = "create_date")
	private long createDate;
	
	@Column(name = "change_date")
	private long changeDate;
	
	@Column(name = "password_change_date")
	private long passwordChangeDate;
	
	@Column(name = "is_receive_marketing_mail")
	private boolean isReceiveMarketingMail;
	
	@Column(name = "alert_email_frequency", length=16)
	private String alertEmailFrequency;
	
	@Column(name = "is_suggest_activation")
	private boolean isSuggestActivation;
	
	@Column(name = "is_colorblind_friendly")
	private boolean isColorblindFriendly;
	
	@Column(name = "is_twoFactor_authentication")
	private boolean isTwoFactorAuthentication;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "time_zone_id")
	private TimeZone timeZoneId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "language_id")
	Language language;

}
