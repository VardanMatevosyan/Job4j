package ru.matevosyan;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

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
    private HashMap<Integer, User> userList;

    public UserStorage() {
        this.userList = new HashMap<>();
    }

    public synchronized HashMap<Integer, User> getUserList() {
        return userList;
    }

    public synchronized void add(User user) {
        this.userList.put(user.getId(), user);
    }

    public synchronized void delete(User user) {
        this.userList.remove(user.getId());
    }

    public synchronized void update(User user) {
        this.userList.put(user.getId(), user);
    }

    public void transfer(int fromId, int toId, int amount) throws RuntimeException {
        synchronized (this) {
            if (this.userList.get(fromId).getAmount() >= amount) {
                int userFromId = this.userList.get(fromId).getAmount() - amount;
                int userToId = this.userList.get(toId).getAmount() + amount;

                User userToSubtractAmount = new User(fromId, userFromId);
                User userToIncreaseAmount = new User(toId, userToId);

                update(userToSubtractAmount);
                update(userToIncreaseAmount);
            } else {
                throw new RuntimeException("Not enough money");
            }
        }
    }

    public synchronized int getTotalAmount() {
        return userList.values().stream().mapToInt(User::getAmount).sum();
    }
}
