package ru.matevosyan.database;
/**
 * Queries class contains sql queries for manipulate database.
 * Created on 13.03.2018.
 * @author Matevosyan Vardan
 * @version 1.0
 */
public enum Queries {
        CREATE_DATABASE("CREATE database musicPlatform WITH owner=postgres ENCODING = 'UTF-8';"),
        CREATE_TABLE("CREATE table if not exists users ("
                + "id serial PRIMARY KEY NOT NULL, "
                + "name character varying (50) NOT NULL, "
                + "login character varying (50) NOT NULL, "
                + "password character varying (20) NOT NULL, "
                + "email character varying (20) NOT NULL, "
                + "createDate timestamp NOT NULL, "
                + "fk_role integer references roles(id), "
                + "fk_address integer references address(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                + "UNIQUE(name), UNIQUE(login), UNIQUE(fk_address));"),
        CREATE_TABLE_AND_INSERT_COUNTRY("DROP TABLE IF EXISTS country CASCADE;"
                + "  CREATE TABLE country ("
                + "  country_id_pk integer NOT NULL,"
                + "  name character varying (128) NOT NULL default '',"
                + "  CONSTRAINT country_pk PRIMARY KEY (country_id_pk)"
                + ");"
                + "INSERT INTO country VALUES"
                + "(4, 'Россия'),"
                + "(5, 'Белорусь'),"
                + "(6, 'Армения'),"
                + "(7, 'США'),"
                + "(8, 'Казакстан');"),
        CREATE_TABLE_AND_INSERT_CITY("DROP TABLE IF EXISTS city CASCADE;"
                + "  CREATE TABLE city ("
                + "  city_id_pk integer NOT NULL,"
                + "  country_id integer REFERENCES country(country_id_pk) NOT NULL default '0',"
                + "  name character varying (128) NOT NULL default '',"
                + "  CONSTRAINT city_pk PRIMARY KEY (city_id_pk)"
                + ");"
                + "INSERT INTO city VALUES"
                + "(6, 4, 'Москва'),"
                + "(7, 4, 'Ростов'),"
                + "(8, 5, 'Минск'),"
                + "(9, 6, 'Ереван'),"
                + "(10, 6, 'Джанфида'),"
                + "(11, 7, 'Санта-Круз'),"
                + "(12, 8, 'Алматы');"),
        CREATE_TABLE_AND_INSERT_ROLE("CREATE table if not exists roles ("
                + "id integer NOT NULL, "
                + "name character varying (50) NOT NULL, "
                + "CONSTRAINT role_pk PRIMARY KEY (id));"
                + "INSERT INTO roles VALUES"
                + "(1, 'user'), "
                + "(2, 'moderator'), "
                + "(3, 'admin');"),
        CREATE_TABLE_AND_INSERT_MUSICTYPE("CREATE table if not exists musicType ("
                + "id integer PRIMARY KEY NOT NULL, "
                + "name character varying (50) NOT NULL);"
                + "INSERT INTO musicType VALUES"
                + "(1, 'rap'), "
                + "(2, 'rock'), "
                + "(3, 'jazz');"),
        CREATE_TABLE_MUSIC_PREPARE("CREATE table if not exists musicPrepare ( "
                + "id serial PRIMARY KEY NOT NULL, "
                + "user_id integer references users(id) ON DELETE CASCADE ON UPDATE CASCADE, "
                + "music_id integer references musicType(id) NOT NULL);"),
        CREATE_TABLE_ADDRESS("CREATE table if not exists address ("
                + "id serial NOT NULL, "
                + "country character varying (50) NOT NULL, "
                + "city character varying (50) NOT NULL, "
                + "street character varying (50) NOT NULL, "
                + "number integer NOT NULL, "
                + "CONSTRAINT address_pk PRIMARY KEY (id),"
                + "UNIQUE (street, number)); "),
        INSERT_ADDRESS("INSERT INTO address (country, city, street, number)"
                + "VALUES (?, ?, ?, ?) ;"),
        INSERT("INSERT INTO users (name, login, password, email, createDate, fk_role, fk_address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);"),
        INSERT_ROOT("INSERT INTO address (country, city, street, number) VALUES (?, ?, ?, ?);"
                + "INSERT INTO users (name, login, password, email, createDate, fk_role, fk_address) "
                + "SELECT ?, ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT name FROM users WHERE name='root');"),
        INSERT_ROLE("INSERT INTO roles (name) VALUES(?);"),
        INSERT_MUSIC_PREPARE("INSERT INTO musicPrepare (user_id, music_id) VALUES(?, ?);"),
        INSERT_MUSIC_TYPE("INSERT INTO musicType(name) VALUES(?);"),
        UPDATE("UPDATE users SET name=?, login=?, fk_role=(SELECT id FROM roles WHERE name=?) WHERE id=?;"),
        UPDATE_MUSIC_TYPE("UPDATE musicType SET name=? WHERE id=?;"),
        UPDATE_ROLE("UPDATE roles SET name=? WHERE id=?;"),
        UPDATE_ADDRESS("UPDATE address SET country=?, city=?, street=?, number=? WHERE id=?;"),
        DELETE_ROLE("DELETE FROM roles WHERE id=?;"),
        DELETE("DELETE FROM users WHERE name=? AND login=?;"),
        DELETE_MUSIC_TYPE("DELETE FROM musicType WHERE id=?;"),
        DELETE_MUSIC_PREPARE("DELETE FROM musicPrepare WHERE user_id=?;"),
        DELETE_ADDRESS("DELETE FROM address WHERE id=?;"),
        SELECT("SELECT u.id, u.name, u.login, u.password, u.createDate, u.email, r.id, r.name, "
                + "a.id, a.country, a.city, a.street, a.number "
                + "FROM users AS u "
                + "INNER JOIN roles AS r "
                + "ON (u.fk_role = r.id) "
                + "INNER JOIN address AS a "
                + "ON (u.fk_address = a.id) "
                + "ORDER BY createDate DESC;"),
        SELECT_USER_MUSIC_PREPARE("SELECT mt.name FROM musicType AS mt "
                + "INNER JOIN musicPrepare AS mp "
                + "ON (mt.id = mp.music_id) "
                + "WHERE mp.user_id = ?;"),
        SELECT_ONE_USER("SELECT login, password FROM users WHERE login=? AND password=?;"),
        GET_ONE_USER_BY_NAME_LOGIN("SELECT name, login FROM users WHERE name=? AND login=?;"),
        GET_USER_ID("SELECT id FROM users WHERE login=? AND password=?;"),
        GET_ROLE_ID("SELECT id FROM roles WHERE name=?;"),
        SELECT_ROLES("SELECT * FROM roles;"),
        GET_CITIES("SELECT * FROM city WHERE country_id = ?;"),
        GET_COUNTIES("SELECT * FROM country;"),
        CHECK_EMAIL("SELECT email FROM users WHERE email=?;"),
        CHECK_LOGIN_OR_NAME_IS_IN_DB("SELECT * FROM users WHERE login=? AND name=?;"),
        GET_USER_BY_ID("SELECT u.id, u.name, u.login, u.password, u.createDate, u.email, r.id, r.name,"
                + "a.id, a.country, a.city, a.street, a.number, m.id, t.name "
                + "FROM users AS u "
                + "INNER JOIN roles AS r "
                + "ON (u.fk_role = r.id) "
                + "INNER JOIN address as a "
                + "ON (u.fk_address = a.id) "
                + "RIGHT JOIN musicprepare as m "
                + "ON (m.user_id = u.id) "
                + "INNER JOIN musictype as t "
                + "ON (t.id = m.music_id) "
                + "WHERE u.id = ?;"),
        GET_ROLE_BY_ID("SELECT r.name FROM roles AS r WHERE r.id=?;"),
        GET_MUSICTYPE("SELECT * FROM musicType;"),
        GET_MUSIC_TYPE_BY_ID("SELECT mt.name FROM musicType AS mt WHERE id=?;"),
        GET_ADDRESS("SELECT * FROM address;"),
        GET_ADDRESS_BY_ID("SELECT * FROM ADDRESS WHERE id=?;"),
        GET_ADDRESS_ID("SELECT id FROM address WHERE city=? AND street=? AND number=?;"),
        GET_USERS_BY_ADDRESS("SELECT u.id, u.name, u.login, u.password, u.createDate, u.email,"
                + "a.id, a.country, a.city, a.street, a.number, r.id, r.name "
                + "FROM users AS u "
                + "INNER JOIN roles AS r "
                + "ON (u.fk_role = r.id) "
                + "INNER JOIN address AS a "
                + "ON (u.fk_address = a.id) "
                + "WHERE u.fk_address = ?;"),
        GET_USERS_BY_ROLE("SELECT u.id, u.name, u.login, u.password, u.createDate, u.email, "
                + "a.id, a.country, a.city, a.street, a.number, r.id, r.name "
                + "FROM users AS u "
                + "INNER JOIN address AS a "
                + "ON (u.fk_address = a.id) "
                + "INNER JOIN roles AS r "
                + "ON (u.fk_role = r.id) "
                + "WHERE u.fk_role = ?"),
        GET_USERS_BY_MUSIC_PREPARE("SELECT u.id, u.name, u.login, u.password, u.createDate, u.email, "
                + "a.id, a.country, a.city, a.street, a.number, r.id, r.name "
                + "FROM users AS u "
                + "INNER JOIN address AS a "
                + "ON (u.fk_address = a.id) "
                + "INNER JOIN roles AS r "
                + "ON (u.fk_role = r.id) "
                + "RIGHT JOIN musicprepare as m "
                + "ON (m.user_id = u.id) "
                + "INNER JOIN musictype as t "
                + "ON (t.id = m.music_id) "
                + "WHERE t.id = ? ");

        private String query;

        /**
         * Getter for query.
         * @return query.
         */
        public String getQuery() {
            return query;
        }

        /**
         * Constructor for assign value to user variable from define constant.
         * @param query sql queries.
         */
        Queries(String query) {
            this.query = query;
        }
    }

