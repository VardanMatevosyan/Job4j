package ru.matevosyan;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Was created to test User class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class UserTest {
    @Test
    public void whenCreateTwoSameObjectThanCheckTheResultOfComparing() {
        Calendar c = new GregorianCalendar(2017, Calendar.JUNE, 8);
        User first = new User("first", 3, c);
        User second = new User("first", 3, c);

        assertThat(first, is(second));
    }
}