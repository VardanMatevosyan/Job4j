package ru.matevosyan;


import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

/**
 * Created UserConverter to convert list to map.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserConverter {

    /**
     * process method was created to convert array list to map.
     * @param list list of users.
     * @return user map.
     */

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
