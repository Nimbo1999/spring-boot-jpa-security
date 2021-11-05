DROP TABLE IF EXISTS addresses;

CREATE SEQUENCE IF NOT EXISTS addresses_id_sq;

CREATE TABLE addresses (
	id BIGINT NOT NULL DEFAULT nextval('addresses_id_sq'::regclass),
	postal_code VARCHAR(8) NOT NULL,
	public_place VARCHAR(255) NOT NULL,
	neighborhood VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	federative_unit VARCHAR(2) NOT NULL,
	complement VARCHAR(255) NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);