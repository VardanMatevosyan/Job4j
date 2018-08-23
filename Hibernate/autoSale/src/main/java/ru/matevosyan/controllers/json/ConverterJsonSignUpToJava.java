package ru.matevosyan.controllers.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * ConverterJsonSignUpToJava class convert json from signUp page to java obj.
 */
public class ConverterJsonSignUpToJava {
    private static final Logger LOG = LoggerFactory.getLogger(ConverterJsonSignUpToJava.class.getName());

    /**
     * convert json to java obj.
     * @param req user.
     * @return User obj.
     * @throws JsonConvertException json exception.
     */
    private User convert(HttpServletRequest req) throws JsonConvertException {
        BufferedReader reader = null;
        String json = "";
        try {
            reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
            json =  reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        User user = new User();
        try {
            JSONObject userObj = (JSONObject) parser.parse(json);
            Optional<JSONObject> userJsonObj = Optional.ofNullable(userObj);

            if (userJsonObj.isPresent()) {
                System.out.println("Present");
                String name = (String) userJsonObj.get().get("name");
                String password = (String) userJsonObj.get().get("password");
                String phoneNumber = (String) userJsonObj.get().get("phoneNumber");
                String city = (String) userJsonObj.get().get("city");
                System.out.println(name + " - " + password + "  - " + city);
                Role role = new Role();
                role.setId(1);
                role.setName("user");

                user.setName(name);
                user.setRole(role);
                user.setPassword(password);
                user.setCity(city);
                user.setPhoneNumber(phoneNumber);
            } else {
                LOG.error("json is empty");
                throw new JsonConvertException("json is empty");
            }

        } catch (ParseException parseExp) {
            LOG.error("Problem with parsing user object in ConvertJsonSignUpToUser {}", parseExp);
        }
        return user;
    }

    /**
     * gerter for User obj.
     * @param req user request.
     * @return User object.
     */
    public User getUser(HttpServletRequest req) {
        return this.convert(req);
    }


}
