package ru.matevosyan.controllers.offers;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.matevosyan.controllers.CommonTestConfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing ReadOffersTest to test getting data from the database.
 * and how the server respond to us and what exactly we received.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "root", roles = {"ADMIN"})
public class ReadOffersTest extends CommonTestConfiguration {
    @Autowired
    private MockMvc mvc;

    /**
     * When create and send GET request with "/ROLE_ADMIN/allOffers" url to the server.
     * then check that we get two offer with the different tittles and with status "OK".
     * @throws Exception object.
     */
    @Test
    @Ignore
    public void whenRequestedGetAllOffersThenCheckThatOffersShouldBeTwoAndTheyValueShouldEndWithFirstAndSecondValue() throws Exception {
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

    /**
     * When create and send GET request with "/ROLE_ADMIN/lastAddedOffers" url to the server.
     * then check that we get one offer with the tittle "tittleFirst" and with status OK.
     * @throws Exception object.
     */
    @Test
    @Ignore
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

    /**
     * When create and send GET request with "/ROLE_ADMIN/withPhoto" url to the server.
     * then check that we get one offer with photo and with status OK.
     * @throws Exception object.
     */
    @Test
    @Ignore
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

    /**
     * When create and send GET request with "/ROLE_ADMIN/withBrands" url.
     * and param as brand with BMW and Lexus values to the server.
     * then check that we get two offers with brands BMW and Lexus and with status OK.
     * @throws Exception object.
     */
    @Test
    @Ignore
    public void whenRequestedGetOffersWithBrandsThenCheckIfGetTwo() throws Exception {
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
