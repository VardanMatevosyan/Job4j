package ru.matevosyan;

import java.util.*;

public class UserConverter {


    public HashMap<Integer, User> process(List<User> list) {

        Iterator<User> userIterator = list.listIterator();
        HashMap<Integer, User> userMap = new HashMap<>();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            userMap.put(user.getId(), user);
        }
        return userMap;
    }

}
