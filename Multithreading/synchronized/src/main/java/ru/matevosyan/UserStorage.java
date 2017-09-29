package ru.matevosyan;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.matevosyan.exception.NotEnoughMoney;
import ru.matevosyan.exception.UserDoesNotExist;

import java.util.HashMap;


/**
 * UserStorage class for storage users account.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 28.09.2017
 */

@ThreadSafe
public final class UserStorage {

    @GuardedBy("this")
    private final HashMap<Integer, User> userList;

    /**
     * UserStorage constructor.
     */

    public UserStorage() {
        this.userList = new HashMap<>();
    }

    /**
     * Get user list.
     * @return userList.
     */

    public synchronized HashMap<Integer, User> getUserList() {
        return userList;
    }

    /**
     * Add user to storage.
     *
     * @param user user.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     */

    public synchronized void add(User user) throws UserDoesNotExist {
        if (!checkUserPresent(user, user.getAmount())) {
            this.userList.put(user.getId(), user);
        } else {
            throw new UserDoesNotExist("User with " + user.getId() + " doesn't exist");
        }
    }

    /**
     * delete user from storage.
     *
     * @param user pass to delete from storage.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     */

    public synchronized void delete(User user) throws UserDoesNotExist {
        if (checkUserPresent(user, user.getAmount())) {
            this.userList.remove(user.getId());
        } else {
        throw new UserDoesNotExist("User with " + user.getId() + " doesn't exist");
        }
    }

    /**
     * Update user in the storage.
     *
     * @param user user.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     */

    public synchronized void update(User user, int amount) throws UserDoesNotExist {
        if (checkUserPresent(user, amount)) {
            this.userList.put(user.getId(), user);
        } else {
        throw new UserDoesNotExist("User with " + user.getId() + " doesn't exist");
        }
    }

    /**
     * Transfer amount from user with fromId to user with toId.
     *
     * @param fromId user id.
     * @param toId user id.
     * @param amount user amount.
     * @throws NotEnoughMoney if users account balance is negative.
     */

    public void transfer(int fromId, int toId, int amount) throws NotEnoughMoney {
        int userFromIdAmount = this.userList.get(fromId).getAmount();
        int userToIdAmount = this.userList.get(toId).getAmount();

        int userFromId = userFromIdAmount - amount;
        int userToId = userToIdAmount + amount;

        User userToSubtractAmount = new User(fromId, userFromId);
        User userToIncreaseAmount = new User(toId, userToId);

        synchronized (this) {
            if (positiveBalance(fromId, amount)) {
                this.update(userToSubtractAmount, this.userList.get(fromId).getAmount());
                this.update(userToIncreaseAmount, this.userList.get(toId).getAmount());
            } else {
                throw new NotEnoughMoney("Not enough money");
            }
        }
    }


    /**
     * Check if user is present in the storage.
     *
     * @param user user.
     * @return true if user present, else false.
     */

    public boolean checkUserPresent(User user, int amountUser) {
        return this.userList.containsKey(user.getId()) && this.userList.get(user.getId()).getAmount() == amountUser;
    }

    /**
     * Check if user balance is positive.
     *
     * @param userId user with id "userId".
     * @param amount user amount.
     * @return true if balance is positive, else false.
     */

    public boolean positiveBalance(int userId, int amount) {
        return this.userList.get(userId).getAmount() >= amount;
    }

    /**
     * Get total amount.
     * @return sum of all user amounts.
     */

    public synchronized int getTotalAmount() {
        return userList.values().stream().mapToInt(User::getAmount).sum();
    }
}
