package ru.matevosyan.models.withXML;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserTest is for testing bean returned from context that configured by XML file.
 */
public class UserTest {

    /**
     * Get bean from context method and check if id is 1 and name is withXML.
     */
    @Test
    public void whenGetUserBeanWithXmlInvokeInitMethodThenResultShouldBeTheSameAsExpected() {
        int expectedId = 1;
        String expectedName = "withXML";

        ApplicationContext context = new ClassPathXmlApplicationContext("withXML/spring-context.xml");
        User user = (User) context.getBean("user");

        assertThat(expectedId, is(user.getId()));
        assertThat(expectedName, is(user.getName()));
    }
}