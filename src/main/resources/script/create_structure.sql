--------------------------------------------------------
--  DDL for Table COMMODITY
--------------------------------------------------------
CREATE SCHEMA test

create table test.audit_log
(
	ID_AUDIT_LOG int auto_increment
		primary key,
	IP varchar(200) null,
	REQUEST varchar(200) null,
	STATUS varchar(200) null,
	USER_AGENT varchar(200) null,
	DATE timestamp null,
	constraint AUDIT_LOG_ID_AUDIT_LOG_uindex
		unique (ID_AUDIT_LOG)
);