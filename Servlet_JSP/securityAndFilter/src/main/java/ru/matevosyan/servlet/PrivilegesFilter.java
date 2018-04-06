package ru.matevosyan.servlet;

import ru.matevosyan.model.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * PrivilegesFilter for check user privileges.
 */
public class PrivilegesFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        boolean clickedUpdate = false;
        boolean clickedDelete = false;
        boolean clickedCreate = false;
        HttpServletRequest request = ((HttpServletRequest) req);
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(request, response);
        } else {
            synchronized (session) {
                String roleName = ((UserRole) session.getAttribute("userRole")).getName();
                if (roleName.equalsIgnoreCase("admin")) {
                    chain.doFilter(request, response);
                } else if (roleName.equalsIgnoreCase("user")) {
                    String login = (String) session.getAttribute("login");
                    String name = (String) session.getAttribute("name");

                    Enumeration<String> parameterNames = request.getParameterNames();
                    while (parameterNames.hasMoreElements()) {
                        String element = parameterNames.nextElement();
                        if (element.equals("method")) {
                            String method = request.getParameter("method");
                            if (method.equals("update")) {
                                clickedUpdate = true;
                            } else if (method.equals("delete")) {
                                clickedDelete = true;
                            } else if (method.equals("create")) {
                                clickedCreate = true;
                            }
                            break;
                        }
                    }
                    if (request.getRequestURI().contains("/update") && clickedUpdate) {
                        if (request.getParameter("login").equals(login)
                                && request.getParameter("newUserRole").equals(roleName)
                                && request.getParameter("user").equals(name)) {
                            chain.doFilter(request, response);

                        } else {
                            request.setAttribute("error", "You can update only your profile!");
                            request.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(request, response);
                        }
                    } else if (request.getRequestURI().contains("/delete") && clickedDelete) {
                        request.setAttribute("error", "You can delete only your profile!");
                        request.getRequestDispatcher("/WEB-INF/views/delete.jsp").forward(request, response);
                    } else if (request.getRequestURI().contains("/insert") && clickedCreate) {
                        request.setAttribute("error", "You can create only your profile!");
                        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
                    }
                }
            }
            if (!(clickedUpdate || clickedCreate || clickedDelete)) {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
