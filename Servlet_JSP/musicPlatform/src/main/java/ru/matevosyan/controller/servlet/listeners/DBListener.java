package ru.matevosyan.controller.servlet.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener to create database and tables before user can sign in.
 */
public class DBListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ServletContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        UserStore store = UserStore.STORE;
        sc.setAttribute("store", store);
        LOG.debug("Init userStore class");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        event.getServletContext().removeAttribute("store");
        DBConnection.getInstance().closePool();
        LOG.debug("close connection pool");
    }
}
