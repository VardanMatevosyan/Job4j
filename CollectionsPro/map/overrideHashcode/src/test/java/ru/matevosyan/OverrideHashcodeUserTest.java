package ru.matevosyan;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Was created to test OverrideHashcodeUserTest class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */


public class OverrideHashcodeUserTest {

    /**
     * when crate two same objects then check the result is false and they hashcode are the same.
     */
    @Test
    public void whenCreateTwoSameObjectThanCheckTheResultOfComparing() {
        Calendar c = new GregorianCalendar(2017, Calendar.JUNE, 8);
        OverrideHashcodeUser first = new OverrideHashcodeUser("first", 3, c);
        OverrideHashcodeUser second = new OverrideHashcodeUser("first", 3, c);
        Map<OverrideHashcodeUser, Object> map = new HashMap<>();

        map.put(first, "User");
        map.put(second, "User");

        System.out.println(map);
        assertThat(first.equals(second), is(false));
        assertThat(first.hashCode() == second.hashCode(), is(true));
    }
}