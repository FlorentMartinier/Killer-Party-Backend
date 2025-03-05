CREATE TABLE if not exists "session" (
	id varchar NOT NULL,
	user_can_join boolean NOT NULL,
	CONSTRAINT session_pk PRIMARY KEY (id)
);

CREATE TABLE if not exists "user" (
	id varchar NOT NULL,
	session_id varchar NOT NULL,
	phone_number varchar NOT NULL,
	associated_ip_address varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);