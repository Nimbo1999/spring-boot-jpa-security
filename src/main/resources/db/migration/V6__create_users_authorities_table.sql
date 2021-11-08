DROP TABLE IF EXISTS users_authorities;

CREATE SEQUENCE IF NOT EXISTS users_authorities_id_sq;

CREATE TABLE users_authorities (
	id BIGINT NOT NULL DEFAULT nextval('users_authorities_id_sq'::regclass),
	user_id BIGINT NOT NULL,
	authority_id BIGINT NOT NULL,
	CONSTRAINT users_authorities_pkey PRIMARY KEY (id),
    CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT authority_fk FOREIGN KEY(authority_id) REFERENCES authorities(id)
);