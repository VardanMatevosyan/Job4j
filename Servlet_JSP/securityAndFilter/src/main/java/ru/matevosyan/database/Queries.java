package ru.matevosyan.database;

/**
 * Queries class contains sql queries for manipulate database.
 * Created on 13.03.2018.
 * @author Matevosyan Vardan
 * @version 1.0
 */
public enum Queries {
    CREATE_DATABASE("CREATE database userstore WITH owner=postgres ENCODING = 'UTF-8';"),
    CREATE_TABLE("CREATE table if not exists users ("
            + "id serial PRIMARY KEY NOT NULL, "
            + "name character varying (50) NOT NULL, "
            + "login character varying (50) NOT NULL, "
            + "email character varying (50) NOT NULL, "
            + "password character varying (20) NOT NULL, "
            + "createDate timestamp NOT NULL, "
            + "fk_role integer references roles(id), "
            + "fk_country integer references country(country_id_pk) DEFAULT '5', "
            + "fk_city integer references city(city_id_pk) DEFAULT '8', "
            + "UNIQUE(name), UNIQUE(login));"),
    CREATE_TABLE_ROLE("CREATE table if not exists roles ("
            + "id integer PRIMARY KEY NOT NULL, "
            + "name character varying (50) NOT NULL);"),
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
    INSERT_ROLE("INSERT INTO roles (id, name) SELECT ?, ? WHERE NOT EXISTS (SELECT id FROM roles WHERE id=?);"),
    INSERT("INSERT INTO users (name, login, email, password, createDate, fk_role, fk_country, fk_city) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
    INSERT_ROOT("INSERT INTO users (name, login, email, password, createDate, fk_role) "
            + "SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT name FROM users WHERE name='root');"),
    UPDATE("UPDATE users SET name=?, login=?, fk_role=(SELECT id FROM roles WHERE name=?) WHERE name=? and login=?;"),
    DELETE("DELETE FROM users WHERE name=? AND login=?;"),
    SELECT("SELECT u.name, u.login, u.createDate, u.password, u.email, r.id, r.name, c.name, cnt.name "
            + "FROM users AS u "
            + "INNER JOIN roles AS r "
            + "ON (u.fk_role = r.id) "
            + "INNER JOIN city as c "
            + "ON (u.fk_city = c.city_id_pk) "
            + "INNER JOIN country as cnt "
            + "ON (u.fk_country = cnt.country_id_pk) "
            + "ORDER BY createDate DESC;"),
    SELECT_ONE_USER("SELECT login, password FROM users WHERE login=? AND password=?"),
    GET_ROLE_ID("SELECT id FROM roles WHERE name=?"),
    SELECT_ROLES("SELECT * FROM roles;"),
    GET_CITIES("SELECT * FROM city WHERE country_id = ?"),
    GET_COUNTIES("SELECT * FROM country"),
    CHECK_EMAIL("SELECT email FROM users WHERE email=?"),
    CHECK_LOGIN_OR_NAME_IS_IN_DB("SELECT * FROM users WHERE login=? OR name=?");

    private String query;


    /**
     * Getter for query.
     * @return query.
     */
    public String getGuery() {
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
