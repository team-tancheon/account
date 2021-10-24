package com.tancheon.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="account")
@Getter @Setter
public class Account {
	
	@Id
	@Column(name = "id")
	long id;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "state")
	String state;
	
	@Column(name = "create_date")
	long createDate;
	
	@Column(name = "change_date")
	long changeDate;
	
	@Column(name = "password_change_date")
	long passwordChangeDate;
	
	@Column(name = "is_receive_marketing_mail")
	boolean isReceiveMarketingMail;
	
	@Column(name = "alert_email_frequency")
	String alertEmailFrequency;
	
	@Column(name = "is_suggest_activation")
	boolean isSuggestActivation;
	
	@Column(name = "is_colorblind_friendly")
	boolean isColorblindFriendly;
	
	@Column(name = "is_twoFactor_authentication")
	boolean isTwoFactorAuthentication;
	
	@Column(name = "time_zone_id")
	long timeZoneId;
	
	@Column(name = "language_id")
	long languageId;

}
