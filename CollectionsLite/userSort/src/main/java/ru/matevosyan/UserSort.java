package ru.matevosyan;


import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collections;

/**
 * UserSort was created to sort list and set.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserSort {

    /**
     * sort method sorted convert list to treeSet and sort treeSet with comparable class.
     * @param userList list of users.
     * @return sorted treeSet by age.
     */

    public Set<User> sort(List<User> userList) {

        Iterator<User> userIterator = userList.listIterator();
        TreeSet<User> userTreeSet = new TreeSet<>();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            userTreeSet.add(user);
        }
        return userTreeSet;
    }

    /**
     * sortHash use to sort list with comparator by hash code.
     * @param users list od users.
     * @return sorted list of users.
     */

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

    /**
     * sortLength use to sort list with comparator by length name.
     * @param users list od users.
     * @return sorted list of users.
     */

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
