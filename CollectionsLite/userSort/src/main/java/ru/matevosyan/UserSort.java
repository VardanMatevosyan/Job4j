package ru.matevosyan;

import java.util.*;

public class UserSort {


    public Set<User> sort(List<User> userList) {

        Iterator<User> userIterator = userList.listIterator();
        TreeSet<User> userTreeSet = new TreeSet<>();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            userTreeSet.add(user);
        }
        return userTreeSet;
    }

}
