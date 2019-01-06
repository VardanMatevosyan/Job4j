package ru.matevosyan.controllers.offers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.matevosyan.controllers.OfferController;
import ru.matevosyan.entity.Car;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;
import ru.matevosyan.services.OfferService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        WithSecurityContextTestExecutionListener.class})

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


    @Test
    @WithMockUser(username="root",roles={"ADMIN"})
    public void whenUserAddOfferCheckIfUserIsAuthenticated() throws Exception {

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

        this.mvc.perform(post("http://localhost:8080/ROLE_ADMIN/offer")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(params)))
                .andExpect(authenticated())
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("abcdefgh")));

    }
}
