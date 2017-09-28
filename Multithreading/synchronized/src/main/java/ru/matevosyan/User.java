package ru.matevosyan;

/**
 * Class User tjo use in UserStorage.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 28.09.2017
 */

public final class User {
    private final int id;
    private final int amount;

    /**
     * Constructor for User.
     * @param id user identification.
     * @param amount user amount.
     */

    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;;
    }

    /**
     * Getter for user id.
     * @return user id.
     */

    public int getId() {
        return id;
    }

    /**
     * Getter for user amount.
     * @return user amount.
     */

    public int getAmount() {
        return amount;
    }
}
