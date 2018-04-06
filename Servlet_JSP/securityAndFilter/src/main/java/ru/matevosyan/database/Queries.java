package ru.matevosyan.database;

/**
 * Queries class contains sql queries for manipulate database.
 * Created on 13.03.2018.
 * @author Matevosyan Vardan
 * @version 1.0
 */
public enum Queries {
    CREATE_DATABASE("CREATE database userstore;"),
    CREATE_TABLE("CREATE table if not exists users ("
            + "id serial PRIMARY KEY NOT NULL, "
            + "name character varying (50) NOT NULL, "
            + "login character varying (50) NOT NULL, "
            + "email character varying (50) NOT NULL, "
            + "password character varying (20) NOT NULL, "
            + "createDate timestamp NOT NULL, "
            + "fk_role integer references roles(id), "
            + "UNIQUE(name), UNIQUE(login));"),
    CREATE_TABLE_ROLE("CREATE table if not exists roles ("
            + "id integer PRIMARY KEY NOT NULL, "
            + "name character varying (50) NOT NULL);"),
    INSERT_ROLE("INSERT INTO roles (id, name) SELECT ?, ? WHERE NOT EXISTS (SELECT id FROM roles WHERE id=?);"),
    INSERT("INSERT INTO users (name, login, email, password, createDate, fk_role) VALUES (?, ?, ?, ?, ?, ?);"),
    INSERT_ROOT("INSERT INTO users (name, login, email, password, createDate, fk_role) "
            + "SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT name FROM users WHERE name='root');"),
    UPDATE("UPDATE users SET name=?, login=?, fk_role=(SELECT id FROM roles WHERE name=?) WHERE name=? and login=?;"),
    DELETE("DELETE FROM users WHERE name=? AND login=?;"),
    SELECT("SELECT u.name, u.login, u.createDate, u.password, u.email, r.id, r.name FROM users AS u INNER JOIN roles AS r ON (u.fk_role = r.id) ORDER BY createDate DESC;"),
    SELECT_ONE_USER("SELECT login, password FROM users WHERE login=? AND password=?"),
    GET_ROLE_ID("SELECT id FROM roles WHERE name=?"),
    SELECT_ROLES("SELECT * FROM roles;");

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
