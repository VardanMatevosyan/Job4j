package ru.matevosyan.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthAccessDeniedHandler for handle the deny requests.
 */
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Redirect request to the root page.
     * @param req user.
     * @param resp user.
     * @param e AccessDeniedException.
     * @throws IOException exception for I/O.
     * @throws ServletException exception for exception deal with servlet.
     */
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        if (req.getSession().getAttribute("currentUser") != null) {
            resp.sendRedirect("/");
        }
    }
}
