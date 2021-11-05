DROP TABLE IF EXISTS phone_numbers;

CREATE SEQUENCE IF NOT EXISTS phone_number_id_sq;

CREATE TABLE phone_numbers (
	id BIGINT NOT NULL DEFAULT nextval('phone_number_id_sq'::regclass),
	number varchar(11) NOT NULL,
	type varchar(11) NOT NULL,
	customer_id BIGINT NOT NULL,
	CONSTRAINT phone_number_pkey PRIMARY KEY (id),
    CONSTRAINT customer_fk FOREIGN KEY(customer_id) REFERENCES customers(id)
);