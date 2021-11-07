DROP TABLE IF EXISTS users;

CREATE SEQUENCE IF NOT EXISTS user_id_sq;

CREATE TABLE users (
	id BIGINT NOT NULL DEFAULT nextval('user_id_sq'::regclass),
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);