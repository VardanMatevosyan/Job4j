package ru.matevosyan.controllers.offers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.matevosyan.entity.Car;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;
import ru.matevosyan.services.OfferService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * AddOfferTest for testing adding offer to the database from request to save to database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "/delete-schema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class AddOfferTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private OfferController controller;

    @Autowired
    private OfferService<Offer> service;

    /**
     * create request to /ROLE_ADMIN/offer and check if user sending the request is authenticated.
     * and check if we get body with the same value in the offer object which means that we add offer to the database.
     * @throws Exception object.
     */
    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenUserAddOfferThenCheckIfUserIsAuthenticatedAndReturnedBody() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Car car = new Car();
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

        this.mvc.perform(post("/ROLE_ADMIN/offer")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(params)))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("abcdefgh")));

    }
}
