package ru.matevosyan.controller.servlet.filters;

import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * AuthFilter is for first of all handle all the request that come in.
 * And check if the user is in the database.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) req);
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(req, resp);
        } else {
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    response.sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                }
            }

            chain.doFilter(req, resp);
        }
        boolean logOut = request.getParameter("method").equalsIgnoreCase("logout");
        if (logOut) {
            System.out.println(request.getParameter("method"));
            response.sendRedirect(String.format("%s/signout", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {
    }
}
