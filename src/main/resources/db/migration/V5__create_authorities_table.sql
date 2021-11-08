DROP TABLE IF EXISTS authorities;

CREATE SEQUENCE IF NOT EXISTS authorities_id_sq;

CREATE TABLE authorities (
	id BIGINT NOT NULL DEFAULT nextval('authorities_id_sq'::regclass),
	authority VARCHAR(11) NOT NULL,
	CONSTRAINT authority_pkey PRIMARY KEY (id)
);