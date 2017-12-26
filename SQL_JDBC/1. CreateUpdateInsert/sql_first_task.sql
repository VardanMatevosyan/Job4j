
create table roleRights (
	pk_id serial primary key,
	rightsDescription character varying (50),
	adding boolean,
	commenting boolean,
	attachingFiles boolean,
	stateVeiw boolean,
	updateOrdter boolean,
	deleteOrder boolean
);


create table roleInSystem (
	pk_id serial primary key,
	fk_id_roleRights integer references roleRights(pk_id),
	typeOfRole character varying (30)
);

create table category (
	pk_id serial primary key,
	categoryName character varying (20)
);

create table state (
	pk_id serial primary key,
	stateName character varying (20)
);

create table orders (
	pk_id serial primary key,
	fk_id_state integer references state(pk_id),
	fk_id_category integer references category(pk_id),
	orderName character varying (50),
	description character varying (200),
	created_date timestamp
);


create table users (
	pk_id serial primary key,
	fk_id_roleInSystem integer references roleInSystem(pk_id),
	fk_id_orders integer references orders(pk_id),
	lastName character varying (30),
	phoneNumber character varying (15),
	userLogin character varying (30),
	userPassword character varying (30),
	userName character varying (30),
	email character varying (50)
);




create table attachedFiles (
	pk_id serial primary key,
	fk_id_orders integer references orders(pk_id),
	filesPath text
);

create table comments (
	pk_id serial primary key,
	fk_id_orders integer references orders(pk_id),
	summary character varying (50),
	description text
);






