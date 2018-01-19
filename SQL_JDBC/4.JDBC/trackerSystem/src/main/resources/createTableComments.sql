CREATE table if not exists comments (
    pk_id serial PRIMARY KEY,
    comment character varying (100),
    fk_id_Item integer references Items(pk_id)
);
