package ru.matevosyan.model;

/**
 * UserRole for user privileges.
 */
public class UserRole {
    private final int id;
    private final String role;

    /**
     * Constructor.
     * @param id role.
     * @param role name.
     */
    public UserRole(final int id, final String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Getter for role name.
     * @return role name.
     */
    public String getName() {
        return role;
    }

    /**
     * Getter for role id.
     * @return role id.
     */
    public int getId() {
        return this.id;
    }
}
