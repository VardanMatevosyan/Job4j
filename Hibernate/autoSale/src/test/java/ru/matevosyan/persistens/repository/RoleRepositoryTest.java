package ru.matevosyan.persistens.repository;


import org.hibernate.query.Query;
import org.junit.Test;
import ru.matevosyan.entity.Role;
import ru.matevosyan.servise.SessionManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Role repository test for testing RoleRepository class.
 */
public class RoleRepositoryTest {
    private static final RoleRepository ROLE_REPOSITORY = new RoleRepository();
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;

    /**
     * Add role invoking add() method from RoleRepository.
     * Then get role from the database and check if it is the same value.
     */
    @Test
    public void whenAddRoleToTheDBCheckIfItIsInTheSystem() {
        String expectedRoleAdmin = "admin";
        ROLE_REPOSITORY.add();

        Role admin = this.getRole(expectedRoleAdmin);

        assertThat(expectedRoleAdmin, is(admin.getName()));
    }

    /**
     * Helper method getRole from the database.
     * @param role in the system.
     * @return role.
     */
    private Role getRole(String role) {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("from Role as r WHERE r.name=:name");
            query.setParameter("name", role);
            return (Role) query.uniqueResult();
        });

    }
}