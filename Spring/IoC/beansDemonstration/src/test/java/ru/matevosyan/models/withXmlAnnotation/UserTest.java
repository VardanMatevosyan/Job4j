package ru.matevosyan.models.withXmlAnnotation;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.matevosyan.models.withAnnotation.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserTest is for testing bean returned from context that configured by Annotation and XML file.
 */
public class UserTest {

    /**
     * Get bean from context invoke initData method and check if id is 2 and name is IvanWithXML.
     */
    @Test
    public void whenGetUserBeanWithAnnotationInvokeInitMethodThenResultShouldBeTheSameAsExpected() {
        ApplicationContext context = new ClassPathXmlApplicationContext("withXMLAndAnnotation/spring-context.xml");
        User user = (User) context.getBean("user");
        user.initData();
        int expectedId = 2;
        String expectedName = "IvanWithXML";

        assertThat(expectedId, is(user.getId()));
        assertThat(expectedName, is(user.getName()));
    }
}