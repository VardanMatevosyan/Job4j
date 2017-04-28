package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

/**
 * UserSortTest was created to test sorting list and set.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserSortTest {

    /**
     * whenPassArrayThanCheckList created to test {@link UserSort#sort(List)} method.
     * passing list, return sorted list by age and check with expected list.
     */

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

    /**
     * whenPassArrayListThanReturnSortHashCodeList created to test {@link UserSort#sortHash(List)}.
     * passing list, return sorted list by hash code and check with expected list.
     */

    @Test
    public void whenPassArrayListThanReturnSortHashCodeList() {

        //assign
        User firsUser = new User("Vanya", 30);
        User secondUser = new User("Igor", 13);
        User thirdUser = new User("Vova", 26);

        List<User> userList = new ArrayList<>();

        ArrayList<User> expected = new ArrayList<>();

        UserSort userSort = new UserSort();
        //act
        userList.add(firsUser);
        userList.add(secondUser);
        userList.add(thirdUser);

        expected.add(firsUser);
        expected.add(secondUser);
        expected.add(thirdUser);

        userList = userSort.sortHash(userList);

        Iterator<User> userListValues = userList.iterator();
        Iterator<User> expectedValue = expected.iterator();

        //assert

        while (expectedValue.hasNext() || userListValues.hasNext()) {
            assertFalse(userListValues.next().equals(expectedValue.next()));
        }
    }

    /**
     * whenPassArrayListThanReturnSortLengthList created to test {@link UserSort#sortLength(List)}}.
     * passing list, return sorted list by length and check with expected list.
     */

    @Test
    public void whenPassArrayListThanReturnSortLengthList() {

        //assign
        User firsUser = new User("VarrrryyLooonnnnggName", 30);
        User secondUser = new User("Aagor", 13);
        User thirdUser = new User("Zova", 26);

        List<User> userList = new ArrayList<>();
        List<User> userList2Save;

        ArrayList<User> expected = new ArrayList<>();

        UserSort userSort = new UserSort();
        //act
        userList.add(firsUser);
        userList.add(secondUser);
        userList.add(thirdUser);

        expected.add(thirdUser);
        expected.add(secondUser);
        expected.add(firsUser);

        userList2Save = userSort.sortLength(userList);

        Iterator<User> userListValues = userList2Save.iterator();
        Iterator<User> expectedValue = expected.iterator();

        //assert

        while (expectedValue.hasNext() || userListValues.hasNext()) {
            assertThat(userListValues.next().getName().length(), is(expectedValue.next().getName().length()));
        }
    }

}