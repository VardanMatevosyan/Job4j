package ru.matevosyan;

import java.util.*;

/**
 * Created user account to be able to transfer amount ich other.
 * Created on 27.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Bank {

    private Map<User, List<Account>> accountHolder = new HashMap<>();

    /**
     * Created addUser(User user), to be able to add user to a Bank.
     * @param user is user that will become a Bank user {@link User}.
     */

    public void addUser(User user) {
        this.accountHolder.put(user, new ArrayList<>());
    }

    /**
     * Created deleteUser(User user), to delete bank user from users base.
     * @param user is user that would be deleted {@link User}.
     */

    public void deleteUser(User user) {
        this.accountHolder.remove(user);
    }

    /**
     * Created addAccountToUser(User user, Account account), to add account to user.
     * The same user can have a multiple accounts.
     * @param user is user who takes accounts {@link User}.
     * @param account is an account that will belong to the user {@link Account}.
     */

    public void addAccountToUser(User user, Account account) {
        this.accountHolder.get(user).add(account);
    }

    /**
     * Created deleteAccountFromUser(User user, Account account), to delete an account from user.
     * @param user is passing to delete own user account {@link User}.
     * @param account that would be deleted from user passing to the {@link Bank#deleteAccountFromUser(User, Account)}.
     */

    public void deleteAccountFromUser(User user, Account account) {
        this.accountHolder.get(user).remove(account);
    }

    /**
     * Created getUserAccounts (User user), to received a list of an account from user.
     * @param user passing to get a list of an account {@link User}.
     * @return list of an account.
     */

    public List<Account> getUserAccounts(User user) {
        return  this.accountHolder.get(user);
    }

    /**
     * Created transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount).
     * to be able to transfer amount between two users from their accounts.
     * User can transfer amount only from one account to another one.
     * @param srcUser is user to be able to transfer his amount from his own account {@link User}.
     * @param srcAccount is an srcUser account.
     * @param dstUser is user whose account will be refilled.
     * @param dstAccount dstAccount that will be refilled.
     * @param amount how much money have been transfer from srcUser srcAccount to dstUser dstAccount.
     * @return true if transfer is passed.
     */

    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {

        boolean isisTransfer = true;

            if ((this.accountHolder.containsKey(dstUser) && getUserAccounts(dstUser).contains(dstAccount)) &&
                    (this.accountHolder.containsKey(srcUser) && getUserAccounts(srcUser).contains(srcAccount))) {

                if (srcAccount.getValue() > amount) {

                    srcAccount.setValue(srcAccount.getValue() - amount);
                    dstAccount.setValue(dstAccount.getValue() + amount);

                } else {
                    isisTransfer = false;
                }

            }
        return isisTransfer;
        }

}

