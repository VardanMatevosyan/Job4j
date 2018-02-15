package ru.matevosyan.model;

import java.sql.Timestamp;

/**
 * Vacancy class to describe offers.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class Vacancy {
    private final String tittle;
    private final String author;
    private final Timestamp create_date;

    /**
     * Constructor vacancy object.
     * @param tittle vacancy tittle.
     * @param author vacancy author.
     * @param create_date vacancy date.
     */

    public Vacancy(String tittle, String author, Timestamp create_date) {
        this.tittle = tittle;
        this.author = author;
        this.create_date = create_date;
    }

    public String getTittle() {
        return tittle;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        return this.tittle.equals(vacancy.tittle) &&
                this.author.equals(vacancy.author) &&
                this.create_date.equals(vacancy.create_date);

    }

    @Override
    public int hashCode() {
        int result =  this.tittle.hashCode();
        result = 31 * result +  this.author.hashCode();
        result = 31 * result +  this.create_date.hashCode();
        return result;
    }
}
