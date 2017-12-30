/* Create a table called Product */ 

CREATE TABLE ProductType(
	type_id serial PRIMARY KEY,
	name character varying (30)
); 

CREATE TABLE Product(
	pk_id serial PRIMARY KEY,
	name character varying (30),
	fk_type_id integer references ProductType(type_id),
	expired_date date,
	price integer
);