package ru.matevosyan.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Session manager is to avoid duplicate code and use functional interface in lambdas.
 */
public enum SessionManager {
    TRANSACTION;
    private static final SessionFactory SESSION_FACTORY = HiberFactory.HIBERNT.getFactory();
    private static final Logger LOG = LoggerFactory.getLogger(SessionFactory.class.getName());

    /**
     * useAndReturn command to do some work defined in the command and return result of it.
     * @param command to apply on the session object. In another word it is a query acting on session.
     * @param <T> defined in command.
     * @return result of applying the command on a session object.
     */
    public <T> T useAndReturn(final Function<Session, T> command) {
        final Session session = SESSION_FACTORY.openSession();
        final Transaction tx = session.beginTransaction();

        try {
            return command.apply(session);
        } catch (final Exception e) {
            tx.rollback();
            LOG.error("Rollback transaction and catch exception like {}", e);
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

}