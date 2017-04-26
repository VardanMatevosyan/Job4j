package ru.matevosyan;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserSortTest {

    @Test
    public void whenPassArrayThanCheckList() {

        //assign
        User firsUser = new User("Vanya", 30);
        User secondUser = new User("Igor", 13);
        User thirdUser = new User("Vova", 26);
        User foursUser = new User("Vova", 26);

        List<User> userList = new ArrayList<>();
        Set<User> treeSet;

        ArrayList<Integer> expectedAge = new ArrayList<>();

        UserSort userSort = new UserSort();
        //act
        userList.add(firsUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        userList.add(foursUser);

        expectedAge.add(13);
        expectedAge.add(26);
        expectedAge.add(30);

        treeSet = userSort.sort(userList);

        Iterator<User> userTreeValues = treeSet.iterator();
        Iterator<Integer> userAge = expectedAge.iterator();

        //assert

        while (userAge.hasNext() || userTreeValues.hasNext()) {
            assertThat(userTreeValues.next().getAge(), is(userAge.next()));
        }


    }



}