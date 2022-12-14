package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null && !session.getAttribute("login").equals("")) {
            boolean isLoginSession = (boolean) session.getAttribute("login");
                if (isLoginSession){
                    if (request.getServletPath().equals("/login")){
                        response.sendRedirect(request.getContextPath());
                    }
                    else{
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
                else{
                    response.sendRedirect(request.getContextPath() + "/login");
                }
        }
        else{
            if (request.getServletPath().equals("/login")){
                filterChain.doFilter(servletRequest, servletResponse);

            }
            else{
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}




