package ru.matevosyan.controllers.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.matevosyan.controllers.UserController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * LoginTest testing log in user to the application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-schema.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-all-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/delete-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class LoginTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserController controller;

    /**
     * When send request to the server to the "SignIn page" to log in then return view named "signIn" and status "OK".
     * @throws Exception object.
     */
    @Test
    public void whenRequestGetSignInThenGetReturnedView() throws Exception {
        this.mvc.perform(
                get("/signIn").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("signIn")
        );
    }

    /**
     * When send correct username and password to "/signIn" url then redirect to root url the controller.
     * to check which direction you should to go to send the page if log in.
     * @throws Exception object
     */
    @Test
    public void whenCorrectUserAndPasswordDataThenLogin() throws Exception {
        this.mvc.perform(formLogin().user("root").password("root").loginProcessingUrl("/signIn"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    /**
     * when send username and password to "/signIn" url and it is not correct then redirect to login error page "signIn?error" with "302" code.
     * @throws Exception object
     */
    @Test
    public void whenUserAndPasswordDataIsNotCorrectThenRedirectToLoginPage() throws Exception {
        this.mvc.perform(formLogin().user("NotCorrect").password("NotCorrect").loginProcessingUrl("/signIn"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signIn?error"));
    }

}