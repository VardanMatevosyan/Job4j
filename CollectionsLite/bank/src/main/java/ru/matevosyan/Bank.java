package ru.matevosyan;

import java.util.*;

/**
 * Created by Пользователь on 27.04.2017.
 */
public class Bank {
    private Map<User, List<Account>> accountHolder = new HashMap<>();

    public void addUser(User user) {
        this.accountHolder.put(user, new ArrayList<Account>());
    }

    public void deleteUser(User user) {
        this.accountHolder.remove(user);
    }

    public void addAccountToUser(User user, Account account) {
        this.accountHolder.get(user).add(account);
    }

    public void deleteAccountFromUser(User user, Account account) {
        this.accountHolder.get(user).remove(account);
    }

    public List<Account> getUserAccounts (User user) {
//        Iterator<Map.Entry<User, List<Account>>> accountHolderIterator = accountHolder.entrySet().iterator();
        return  this.accountHolder.get(user);
    }

    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount){

        return false;
    }

}
