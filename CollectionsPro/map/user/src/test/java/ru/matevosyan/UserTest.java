package ru.matevosyan;

import org.junit.Test;

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
        User first = new User("first", 3, );
        User second = new User("second", 2, );

        assertThat(first.equals(second), is(false));
    }
}