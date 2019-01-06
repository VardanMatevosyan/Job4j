package ru.matevosyan.controllers.offers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.matevosyan.controllers.OfferController;
import ru.matevosyan.controllers.UserController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/fill-offers-before-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "/delete-schema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class ReadOffersTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OfferController offerController;
    @Autowired
    private UserController userController;

    @Test
    @WithMockUser(username="root",roles={"ADMIN"})
    public void whenRequestedGetAllOffersThenCheckCountOfAllOffersShouldBeTwoAndTheyValueShouldEndWithFirstAndSecondValue() throws Exception {
        this.mvc.perform(
                get("/ROLE_ADMIN/allOffers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tittleFirst")))
                .andExpect(content().string(containsString("tittleSecond")));
    }

    @Test
    @WithMockUser(username="root",roles={"ADMIN"})
    public void whenRequestedGetLastAddedOffersThenCheckIfGetOne() throws Exception {
        String date = Timestamp.valueOf(LocalDateTime.now()).toString().substring(0, 10);

        this.mvc.perform(
                get("/ROLE_ADMIN/lastAddedOffers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tittleFirst")))
                .andExpect(content().string(containsString(date)));
    }

    @Test
    @WithMockUser(username="root",roles={"ADMIN"})
    public void whenRequestedGetOffersWithPhotoThenCheckIfGetOne() throws Exception {
       String photo = "images/BMWsedan/photo.jpeg";
        this.mvc.perform(
                get("/ROLE_ADMIN/withPhoto")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(photo)));
    }

    @Test
    @WithMockUser(username="root",roles={"ADMIN"})
    public void whenRequestedGetOffersWithBrandsThenCheckIfGetOne() throws Exception {
        String bmw = "BMW";
        String lexus = "Lexus";
        this.mvc.perform(
                get("/ROLE_ADMIN/withBrands")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("brand", "BMW").param("brand", "Lexus")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bmw)))
                .andExpect(content().string(containsString(lexus)));
    }

}
