package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created UserConverterTest to test UserConverter class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserConverterTest {

    /**
     * whenPassArrayThanCheckList was created to test converting method which return map.
     * and check with passing list to the {@link UserConverter#process(List)} method.
     */

    @Test
    public void whenPassArrayThanCheckList() {

        //assign
        User admin = new User(1, "Tom", "LA");
        User firsUser = new User(2, "Vova", "Moscow");
        User secondUser = new User(3, "Vardan", "Dzhanfida");

        List<User> userList = new ArrayList<>();
        HashMap<Integer, User> actualUserMap;

        UserConverter convertList = new UserConverter();

        //act
        userList.add(admin);
        userList.add(firsUser);
        userList.add(secondUser);

        actualUserMap = convertList.process(userList);

        Iterator<Map.Entry<Integer, User>> hashMap = actualUserMap.entrySet().iterator();
        Iterator<User> userValues = userList.iterator();

        //assert
        while (hashMap.hasNext() && userValues.hasNext()) {

            Map.Entry<Integer, User> userEntry = hashMap.next();
            User userArrayList = userValues.next();

            assertThat(userEntry.getKey(), is(userArrayList.getId()));
            assertThat(userEntry.getValue().getName(), is(userArrayList.getName()));

        }

    }



}