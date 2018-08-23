package ru.matevosyan.servise;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate session factory enum.
 * for holding session factory object.
 */
public enum HiberFactory {
    HIBERNT;
    private static final SessionFactory FACTORY;

    /**
     *  for HiberFactory to configure and assign session factory object.
     */
    static  {
        FACTORY = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Getter for session factory object.
     * @return factory - session factory object.
     */
    public SessionFactory getFactory() {
        return FACTORY;
    }
}
