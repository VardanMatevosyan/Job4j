package ru.matevosyan.models.javaSpecific;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.matevosyan.AppConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserTest is for testing bean returned from context that configured by java config class.
 */
public class UserTest {

    /**
     * Get bean from context invoke initData method and check if id is 3 and name is javaSpecific.
     */
    @Test
    public void whenGetUserBeanWithXmlInvokeInitMethodThenResultShouldBeTheSameAsExpected() {
        int expectedId = 3;
        String expectedName = "javaSpecific";

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean(User.class);
        user.initData();

        assertThat(expectedId, is(user.getId()));
        assertThat(expectedName, is(user.getName()));
    }
}