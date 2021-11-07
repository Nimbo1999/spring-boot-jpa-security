DROP TABLE IF EXISTS user_roles;

CREATE SEQUENCE IF NOT EXISTS user_roles_id_sq;

CREATE TABLE user_roles (
	id BIGINT NOT NULL DEFAULT nextval('user_roles_id_sq'::regclass),
	role VARCHAR(11) NOT NULL,
	user_id BIGINT NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (id),
    CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES users(id)
);