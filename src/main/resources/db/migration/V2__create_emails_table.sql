DROP TABLE IF EXISTS emails;

CREATE SEQUENCE IF NOT EXISTS email_id_sq;

CREATE TABLE emails (
	id BIGINT NOT NULL DEFAULT nextval('email_id_sq'::regclass),
	email varchar(255) NOT NULL,
	customer_id BIGINT NOT NULL,
	CONSTRAINT email_pkey PRIMARY KEY (id),
    CONSTRAINT customer_fk FOREIGN KEY(customer_id) REFERENCES customers(id)
);