DROP TABLE IF EXISTS language CASCADE;

CREATE TABLE language
(
	id		VARCHAR(32)	NOT NULL,
	name	VARCHAR(20)	NOT NULL	UNIQUE,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS time_zone CASCADE;

CREATE TABLE time_zone
(
	id		VARCHAR(32)	NOT NULL,
	name	VARCHAR(50)	NOT NULL	UNIQUE,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE account
(
	id								VARCHAR(32)		NOT NULL,
	email 							VARCHAR(256) 	NOT NULL	UNIQUE,
	password 						VARCHAR(256) 	NOT NULL,
	name 							VARCHAR(256) 	NOT NULL,
	state 							VARCHAR(16)		NOT NULL,
	create_date						BIGINT,
	change_date 					BIGINT,
	password_change_date 			BIGINT,
	is_receive_marketing_mail 		BOOLEAN 		DEFAULT false,
	alert_email_frequency 			VARCHAR(16),
	is_suggest_activation			BOOLEAN 		DEFAULT false,
	is_colorblind_friendly 			BOOLEAN 		DEFAULT false,
	is_two_factory_authentication 	BOOLEAN 		DEFAULT false,
	time_zone_id 					VARCHAR(32) 	NOT NULL,
	language_id 					VARCHAR(32) 	NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (time_zone_id) 	REFERENCES 	time_zone	(id),
	FOREIGN KEY (language_id) 	REFERENCES	language	(id)
);

DROP TABLE IF EXISTS login_history CASCADE;

CREATE TABLE login_history
(
	id			VARCHAR(32)	NOT NULL,
	account_id	VARCHAR(32)	NOT NULL,
	login_date	BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS verify_info CASCADE;

CREATE TABLE verify_info
(
	id			VARCHAR(32)	NOT NULL,
	account_id	VARCHAR(32)	NOT NULL,
	verify_code	VARCHAR(6)	NOT NULL,
	create_date	BIGINT,
	expire_date	BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS session CASCADE;

CREATE TABLE session
(
	id					VARCHAR(32)		NOT NULL,
	account_id			VARCHAR(32)		NOT NULL,
	device_name			VARCHAR(256)	NULL,
	browser_name		VARCHAR(256)	NULL,
	last_access_date	BIGINT			NULL,
	token				VARCHAR(256)	NOT NULL	UNIQUE,
	PRIMARY KEY (id),
	FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);