DROP TABLE IF EXISTS roles;

CREATE SEQUENCE IF NOT EXISTS roles_id_sq;

CREATE TABLE roles (
	id BIGINT NOT NULL DEFAULT nextval('roles_id_sq'::regclass),
	role VARCHAR(11) NOT NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);