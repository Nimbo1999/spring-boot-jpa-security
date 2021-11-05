DROP TABLE IF EXISTS customers;

CREATE SEQUENCE IF NOT EXISTS customer_id_sq;

CREATE TABLE customers (
	id BIGINT NOT NULL DEFAULT nextval('customer_id_sq'::regclass),
	name VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	address_id BIGINT NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT address_fk FOREIGN KEY(address_id) REFERENCES addresses(id)
);