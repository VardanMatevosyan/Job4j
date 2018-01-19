CREATE table if not exists items (
    pk_id serial PRIMARY KEY,
    name character varying (30),
    description character varying (100),
    created_date timestamp
);
