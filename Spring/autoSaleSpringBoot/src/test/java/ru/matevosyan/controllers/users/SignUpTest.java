package ru.matevosyan.controllers.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.matevosyan.controllers.CommonTestConfiguration;
import ru.matevosyan.entity.Role;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * SignUpTest testing sign up user case.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class SignUpTest extends CommonTestConfiguration {
    @Autowired
    private MockMvc mvc;

    /**
     * When send "POST" request to "/signUp" url with user fills field.
     * then check if the user was sign up to the database and then response the "signIn" view to signIn.
     * @throws Exception object.
     */
    @Test
    @Ignore
    public void whenRequestGetSignInWithParamsThenGetReturnedView() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Role role = new Role();
        role.setId(2);
        role.setName("user");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "newUpeor");
        params.put("password", "1234598N");
        params.put("city", "Poltava");
        params.put("phoneNumber", "89097895676");
        params.put("role", role);

        this.mvc.perform(
                post("/signUp")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(params))
                        .accept(MediaType.TEXT_HTML_VALUE))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("signIn")
        );
    }
}
