DROP TABLE IF EXISTS users_roles;

CREATE SEQUENCE IF NOT EXISTS users_roles_id_sq;

CREATE TABLE users_roles (
	id BIGINT NOT NULL DEFAULT nextval('users_roles_id_sq'::regclass),
	user_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL,
	CONSTRAINT users_roles_pkey PRIMARY KEY (id),
    CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT role_fk FOREIGN KEY(role_id) REFERENCES roles(id)
);