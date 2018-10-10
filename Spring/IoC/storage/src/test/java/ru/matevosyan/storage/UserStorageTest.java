package ru.matevosyan.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.matevosyan.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserStorageTest for testing Storages class.
 */
public class UserStorageTest {

    /**
     * Test when store User with id = 1 and name = User1 to the InMemory storage then get bean.
     * And then check the result should be the same.
     */
    @Test
    public void whenAddUserToTheInMemoryStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        User paramUser = new User(1);
        paramUser.setName("User1");
        int expectedId = 1;

        User user = userStorage.add(paramUser);

        assertThat(expectedId, is(user.getId()));
    }
}