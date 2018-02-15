CREATE table if not exists vacancy (
    pk_id serial NOT NULL,
    tittle character varying (100) NOT NULL,
    author character varying (50),
    create_date timestamp,
    CONSTRAINT idWithTitle PRIMARY KEY (pk_id)
);
