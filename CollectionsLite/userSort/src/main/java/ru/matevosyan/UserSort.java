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

    public List<User> sortHash (List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
        public int compare(User user1, User user2) {
                Integer user1Hash = user1.hashCode();
                Integer user2Hash = user2.hashCode();

                return user1Hash.compareTo(user2Hash);
            }
    });
        return users;
    }

    public List<User> sortLength (List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer user1Length = o1.getName().length();
                Integer user2Length = o2.getName().length();

                return user1Length.compareTo(user2Length);
            }
        });
        return users;
    }

}
