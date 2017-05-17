package ru.matevosyan;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test MajorStore class.
 * Created on 16.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class MajorStoreTest {

    /**
     * whenAddBaseAndGetObjectThanCheckAddedObject() method created to test {@link MajorStore#add(Base)} method.
     */

    @Test
    public void whenAddBaseAndGetObjectThanCheckAddedObject() {
        Base user = new User("Vova", 12, "1");
        Base role = new Role("2");

        MajorStore majorStore = new MajorStore(2);

        majorStore.add(user);
        majorStore.add(role);

        assertThat(majorStore.get("1"), is(user));
    }

    /**
     * whenAddBaseAndUpdateAndGetObjectThanCheckUpdatedObject() method created to test.
     * {@link MajorStore#update(String, Base)} )} method.
     */

    @Test
    public void whenAddBaseAndUpdateAndGetObjectThanCheckUpdatedObject() {
        Base user = new User("Vova", 12, "1");
        Base role = new Role("2");
        Base userToUpdate = new User("Ivan", 22, "1");

        MajorStore majorStore = new MajorStore(2);

        majorStore.add(user);
        majorStore.add(role);

        majorStore.update("1", userToUpdate);

        assertThat(majorStore.get("1"), is(userToUpdate));
    }

    /**
     * whenAddBaseAndDeleteObjectThanCheckBaseObject() method created to test.
     * {@link MajorStore#delete(String)} )} method.
     */

    @Test
    public void whenAddBaseAndDeleteObjectThanCheckBaseObject() {
        Throwable e = null;
        User user = new User("Vova", 12, "1");
        User user2 = new User("Tom", 12, "2");

        SimpleArray<Base> simpleArray = new SimpleArray<>(2);
        UserStore userStore = new UserStore(simpleArray);

        try {
            userStore.add(user);
            userStore.add(user2);
            userStore.delete("4");
        } catch (Throwable exp) {
            e = exp;
        }

        assertTrue(e instanceof NoSuchElementException);

    }

    /**
     * whenAddBaseAndGetObjectThanCheckGetObject() method created to test.
     * {@link MajorStore#get(String)} )} method.
     */

    @Test
    public void whenAddBaseAndGetObjectThanCheckGetObject() {
        Base user = new User("Vova", 12, "1");
        Base role = new Role("2");

        MajorStore majorStore = new MajorStore(2);

        majorStore.add(user);
        majorStore.add(role);

        assertThat(majorStore.get("2"), is(role));
        assertFalse(majorStore.get("2").equals(user));
    }

}