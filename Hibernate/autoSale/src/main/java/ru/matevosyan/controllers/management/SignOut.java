package ru.matevosyan.controllers.management;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SignOut class for sign out the user.
 */
public class SignOut extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session) {
            session.removeAttribute("offers");
            session.removeAttribute("currentUser");
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
        }

    }
}
