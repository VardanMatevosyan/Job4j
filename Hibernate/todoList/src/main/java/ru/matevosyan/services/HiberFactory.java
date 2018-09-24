package ru.matevosyan.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate factory enum to get factory object.
 */
public enum HiberFactory {
    FACTORY;
    private final SessionFactory factory;

    /**
     * HiberFactory constructor with init and configure factory object.
     */
    HiberFactory() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    /**
     * Getter for session factory.
     * @return  SessionFactory object.
     */
    public SessionFactory getFactory() {
        return factory;
    }
}
