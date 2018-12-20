CREATE TABLE roles
(
  id   SERIAL NOT NULL,
  name CHARACTER VARYING(255),
  CONSTRAINT roles_pkey PRIMARY KEY (id),
  CONSTRAINT uk_ofx66keruapi6vyqpv6f2or37 UNIQUE (name)
);

CREATE TABLE users
(
  id serial NOT NULL,
  city character varying(255),
  enabled boolean NOT NULL,
  name character varying(255),
  password character varying(255),
  phone_number character varying(255),
  fk_role_id integer,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT fk217q85dy9gx4q1rn3ncj4l94o FOREIGN KEY (fk_role_id)
  REFERENCES roles (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_3g1j96g94xpk3lpxl2qbl985x UNIQUE (name),
  CONSTRAINT uk_r53o2ojjw4fikudfnsuuga336 UNIQUE (password)
);

CREATE TABLE cars
(
  id serial NOT NULL,
  body_type character varying(255),
  brand character varying(255),
  engine_capacity real,
  gear_box character varying(255),
  model_vehicle character varying(255),
  year_of_manufacture timestamp without time zone,
  CONSTRAINT cars_pkey PRIMARY KEY (id)
);

CREATE TABLE offers
(
  id serial NOT NULL,
  address character varying(255),
  description character varying(255),
  picture character varying(255),
  posting_date timestamp without time zone,
  price integer,
  sold_state boolean,
  tittle character varying(255),
  fk_car_id integer,
  fk_user_id integer,
  CONSTRAINT offers_pkey PRIMARY KEY (id),
  CONSTRAINT fk1my4igq6q2e3j3p11faf8qxo5 FOREIGN KEY (fk_car_id)
  REFERENCES cars (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkjufrkitloem8dy8p20y5flu42 FOREIGN KEY (fk_user_id)
  REFERENCES users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ukghqxr37q5ww28oemn3cqharfg UNIQUE (tittle, sold_state)
);
