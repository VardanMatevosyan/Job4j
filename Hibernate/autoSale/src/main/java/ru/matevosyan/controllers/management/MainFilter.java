package ru.matevosyan.controllers.management;


import ru.matevosyan.entity.User;
import ru.matevosyan.persistens.repository.OfferRepository;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * MainFilter is filter to figure to which resources forward the client request.
 */
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) req);
        HttpServletResponse response = (HttpServletResponse) resp;
        OfferRepository offerRepository = new OfferRepository();

        if (request.getRequestURI().contains("/signIn") || request.getRequestURI().contains("/signOut")) {
            chain.doFilter(req, resp);
        } else if (request.getRequestURI().contains(request.getContextPath())
                && request.getRequestURI().endsWith("/")
                && request.getSession().getAttribute("currentUser") == null) {
            req.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req, resp);
            return;
        }  else if (request.getSession().getAttribute("currentUser") != null) {
            User currentUser = (User) request.getSession().getAttribute("currentUser");

            //change everething to JSON
//            request.getSession().setAttribute("userOffers", currentUser.getOffers());
//            request.getSession().setAttribute("offers", offerRepository.getOffers());
            if (currentUser.getRole().getName().equals("user")) {
                request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
            } else if (currentUser.getRole().getName().equals("admin")) {
                request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
