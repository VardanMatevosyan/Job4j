package ru.matevosyan.controllers.crud;

import ru.matevosyan.controllers.json.ConverterJsonSignUpToJava;

import ru.matevosyan.persistens.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddSignUpUser add user to database.
 */
public class AddSignUpUser extends HttpServlet {
    private static final UserRepository USER_REPOSITORY = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USER_REPOSITORY.add(new ConverterJsonSignUpToJava().getUser(req));
    }
}
