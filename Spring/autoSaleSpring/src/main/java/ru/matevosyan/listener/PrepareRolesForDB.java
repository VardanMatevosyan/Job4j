package ru.matevosyan.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.persistens.repository.RoleRepository;
import ru.matevosyan.persistens.repository.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * PrepareRolesForDB class for listening initialization of the context.
 */
public class PrepareRolesForDB implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ServletContextListener.class.getName());
    private static final UserRepository USER_REPOSITORY = new UserRepository();
    private static final RoleRepository ROLE_REPOSITORY = new RoleRepository();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ROLE_REPOSITORY.add();
        USER_REPOSITORY.addRoot();
        LOG.info("add roles to db");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOG.debug("close connection pool");
    }
}
