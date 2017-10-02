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
     * Constructor for User and amount is 0.
     * @param id user identification.
     */

    public User(final int id) {
        this.id = id;
        this.amount = 0;;
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

    /**
     * Override method equals for user.
     * @param o object to check if it is the same object with this.o.
     * @return true if object is the same and false ig not.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User thatUser = (User) o;

        return this.id == thatUser.id && this.amount == thatUser.amount;
    }

    /**
     * Override hashCode to return object hash code representation.
     * @return int value which represent object hash code.
     */

    @Override
    public int hashCode() {
        int code = 31;
        code = 17 * code + id;
        code = 17 * code + amount;
        return code;
    }
}