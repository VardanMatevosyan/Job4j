package ru.matevosyan.controllers.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.matevosyan.controllers.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * LogOutTest for testing log out user that was successfully logIn before.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")

public class LogOutTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserController controller;

    /**
     * When send "GET" request to "/signOt" url then log out then log out user from the system.
     * and get the "signIn" page.
     * @throws Exception object.
     */
    @Test
    @WithMockUser(value = "admin")
    public void whenLogOutThenGetLogInPage() throws Exception {
        this.mvc.perform(get("/signOut")
                        .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signIn"));
    }
}
