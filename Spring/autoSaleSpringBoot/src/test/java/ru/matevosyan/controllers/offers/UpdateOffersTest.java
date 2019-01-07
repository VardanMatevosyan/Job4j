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
import java.util.Map;
import java.util.Optional;
import java.util.LinkedHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UpdateOffersTest for testing update offer in the database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class UpdateOffersTest extends CommonTestConfiguration {
    @Autowired
    private MockMvc mvc;

    /**
     * When send "PUT" request with "/ROLE_ADMIN/offerSellStatusValue" url.
     * then check that soldStare was changed from false to true or from true to false if there was true value.
     * @throws Exception object.
     */
    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenSendRequestToChangeTheSellState() throws Exception {
        Integer id = 1;
        Optional<Offer> beforeUpdate = super.getRepository().findById(id);
        Offer offer = beforeUpdate.orElse(new Offer());
        Boolean beforeUpdatedState = offer.getSoldState();

        String json = "{\"soldState\": true, \"id\": " + id + "}";
        MockHttpServletRequestBuilder request = put("/ROLE_ADMIN/offerSellStatusValue")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());

        Optional<Offer> updated = super.getRepository().findById(id);
        Offer updatedOffer = updated.orElse(new Offer());
        Boolean afterUpdatedState = updatedOffer.getSoldState();

        assertThat(beforeUpdatedState, is(not(afterUpdatedState)));

    }

    /**
     * when send "PUT" request with "/ROLE_ADMIN//update/{id}" url then check.
     * that offer was updated and status is "OK" and we get updated JSON offer object.
     * @throws Exception object.
     */
    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenSendRequestToUpdateOffer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Car car = new Car();
        car.setId(1);
        car.setBrand("brand");
        car.setEngineCapacity(2.5F);
        car.setGearBox("auto");
        car.setBodyType("carBody");
        car.setYearOfManufacture(new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime()));
        car.setModelVehicle("vehicle");

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

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("tittle", "abcdefgh");
        params.put("description", "description");
        params.put("picture", "default.jpeg");
        params.put("price", "890934");
        params.put("address", "address dd 4");
        params.put("car", car);
        params.put("user", user);

        Offer forJson = new Offer();
        forJson.setUser(user);
        forJson.setCar(car);
        forJson.setId(1);
        forJson.setTittle("abcdefgh");
        forJson.setDescription("description");
        forJson.setPicture("images\\default.jpeg");
        forJson.setPrice(890934);
        forJson.setAddress("address dd 4");
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
        Optional<Offer> beforeUpdate = super.getRepository().findById(id);
        Offer offer = beforeUpdate.orElse(new Offer());
        String tittleBefore = offer.getTittle();

        String json = mapper.writeValueAsString(params);
        MockHttpServletRequestBuilder request = put("/ROLE_ADMIN//update/" + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(responseJson));

        Optional<Offer> updated = super.getRepository().findById(id);
        Offer updatedOffer = updated.orElse(new Offer());
        String updatedOfferTittle = updatedOffer.getTittle();

        assertThat(tittleBefore, is(not(updatedOfferTittle)));
        assertThat(offer, is(not(equalTo(updatedOffer))));
    }

}
