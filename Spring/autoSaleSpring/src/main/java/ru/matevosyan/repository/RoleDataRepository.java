package ru.matevosyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matevosyan.entity.Role;

/**
 * Repository for Role entity.
 * @param <T> role and child.
 */
@Repository
public interface RoleDataRepository<T extends Role> extends JpaRepository<Role, Integer> {
}
