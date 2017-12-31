create table engine (
	pk_id serial PRIMARY KEY,
	name character varying (50),
	brand character varying (50)
);

create table transmission (
	pk_id serial PRIMARY KEY,
	transmission_type character varying (50)
);

create table geatBox (
	pk_id serial PRIMARY KEY,
	name character varying (50),
	speed_steps integer 
);

create table car (
	pk_id serial PRIMARY KEY,
	brand character varying (30),
	name character varying (50),
	fk_engine_id integer references engine(pk_id),
	fk_transmission_id integer references transmission(pk_id),
	fk_geatBox_id integer references geatBox(pk_id)
	
);