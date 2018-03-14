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
            + "createDate timestamp NOT NULL, UNIQUE(name), UNIQUE(login));"),
    INSERT("INSERT INTO users (name, login, email, password, createDate) VALUES (?, ?, ?, ?, ?);"),
    UPDATE("UPDATE users SET name=?, login=? WHERE name=? and login=?;"),
    DELETE("DELETE FROM users WHERE name=? AND login=?;"),
    SELECT("SELECT * FROM users ORDER BY createDate DESC;");

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
