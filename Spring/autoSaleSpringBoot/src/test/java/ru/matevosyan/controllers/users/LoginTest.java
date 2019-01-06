package ru.matevosyan.controllers.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-schema.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/delete-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class LoginTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserController controller;

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

    @Test
    public void whenCorrectUserAndPasswordDataThenLogin() throws Exception {
        this.mvc.perform(formLogin().user("root").password("root").loginProcessingUrl("http://localhost/signIn"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void whenUserAndPasswordDataIsNotCorrectThenRedirectToLoginPage() throws Exception {
        this.mvc.perform(formLogin().user("NotCorrect").password("NotCorrect").loginProcessingUrl("http://localhost/signIn"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signIn?error"));
    }

}