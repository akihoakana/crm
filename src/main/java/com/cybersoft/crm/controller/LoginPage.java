package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.LoginService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginPage extends HttpServlet {
    private LoginService loginService=new LoginService();
    private UserService userService=new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("email")!=null && req.getParameter("password")!=null
                && !req.getParameter("email").equals("") && !req.getParameter("password").equals("")) {
            String email=req.getParameter("email");
            String password=req.getParameter("password");
            boolean isLogin = loginService.checkLogin(email, password);
            if (isLogin) {
                HttpSession session = req.getSession(true);
                session.setAttribute("login", true);
                session.setAttribute("id", userService.getIdByEmailService(email));
                session.setMaxInactiveInterval(10 * 60);
                resp.sendRedirect(req.getContextPath());
            }
            else {
                req.getRequestDispatcher("/login.html").forward(req,resp);
            }
        }
        else {
            req.getRequestDispatcher("/login.html").forward(req,resp);
        }
    }

}

