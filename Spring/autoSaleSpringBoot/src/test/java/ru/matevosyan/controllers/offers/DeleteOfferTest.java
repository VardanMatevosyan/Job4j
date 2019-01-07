package ru.matevosyan.controllers.offers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.matevosyan.controllers.CommonTestConfiguration;

import ru.matevosyan.entity.Car;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * DeleteOfferTest for testing deleting offer from the database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class DeleteOfferTest extends CommonTestConfiguration {
    @Autowired
    private MockMvc mvc;

    /**
     * Create and send DELETE request to the server and check if we delete the offer from DB.
     * and get the deleted offer from the server in response body as JSON string.
     * @throws Exception object.
     */
    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenPerformDeleteRequestThenCheckOfferExist() throws  Exception {
        ObjectMapper mapper = new ObjectMapper();

        Car car = new Car();
        car.setId(1);
        car.setBrand("BMW");
        car.setEngineCapacity(4.4F);
        car.setGearBox("auto");
        car.setBodyType("bodyType");
        Timestamp yearOfManufacture = Timestamp.valueOf(LocalDateTime.now()
                .withYear(1989)
                .withMonth(11)
                .withDayOfMonth(11)
                .withHour(LocalTime.MIDNIGHT.getHour())
                .withMinute(LocalTime.MIDNIGHT.getMinute())
                .withSecond(LocalTime.MIDNIGHT.getSecond())
                .withNano(LocalTime.MIDNIGHT.getNano()));
        car.setYearOfManufacture(yearOfManufacture);
        car.setModelVehicle("sedan");

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_ADMIN");

        User user = new User();
        user.setId(1);
        user.setName("root");
        user.setPassword("root");
        user.setCity("Poltava");
        user.setPhoneNumber("89261234567");
        user.setRole(role);
        Offer forJson = new Offer();
        forJson.setUser(user);
        forJson.setCar(car);
        forJson.setId(1);
        forJson.setTittle("tittleSecond");
        forJson.setDescription("descriptionSecond");
        forJson.setPicture("images/BMWsedan/photo.jpeg");
        forJson.setPrice(123123);
        forJson.setAddress("addressSecond");
        forJson.setSoldState(false);
        Timestamp date = Timestamp.valueOf(LocalDateTime.now()
                .withYear(2018)
                .withMonth(11)
                .withDayOfMonth(11)
                .withHour(LocalTime.MIDNIGHT.getHour())
                .withMinute(LocalTime.MIDNIGHT.getMinute())
                .withSecond(LocalTime.MIDNIGHT.getSecond())
                .withNano(LocalTime.MIDNIGHT.getNano()));

        forJson.setPostingDate(date);

        String responseJson = mapper.writeValueAsString(forJson);

        Integer id = 1;
        Optional<Offer> optional = super.getRepository().findById(id);
        boolean presentBefore = optional.isPresent();

        MockHttpServletRequestBuilder request = delete("/ROLE_ADMIN/delete/" + id)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        this.mvc.perform(request).andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().string(responseJson));

        Optional<Offer> optionalAfterDeleting = super.getRepository().findById(id);
        boolean presentAfter = optionalAfterDeleting.isPresent();

        assertThat(presentBefore, is(not(presentAfter)));
    }
}
