package ru.matevosyan;

import java.util.*;

public class Account {

    private final int value;
    private final String requisites;

    public int getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    private Map<User, List<Account>> accountHolder = new HashMap<>();
    Iterator<Map.Entry<User, List<Account>>> accountHolderIterator = accountHolder.entrySet().iterator();

    public void addUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public void addAccountToUser(User user, Account account) {

    }

    public void deleteAccountFromUser(User user, Account account) {

    }

    public List<Account> getUserAccounts (User user) {

    }

    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount){
        return false;
    }

}
