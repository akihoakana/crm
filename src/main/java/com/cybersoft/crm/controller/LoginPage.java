package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.LoginService;

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
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username=req.getParameter("email");
//        String password=req.getParameter("password");
//        boolean isLogin = loginService.checkLogin(username,password);
//        System.out.println("isLogin "+ isLogin);
//        if (isLogin){
//            HttpSession session=req.getSession();
//            session.setAttribute("login", true  );
//            session.getAttribute("login");
//            session.setMaxInactiveInterval(60*60) ;
//        }
//
//        req.getRequestDispatcher("/login.html").forward(req,resp);
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.getRequestDispatcher("/login.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        boolean isLogin = loginService.checkLogin(email,password);
        if (isLogin){
            HttpSession session=req.getSession();
            session.setAttribute("login", true  );
            session.getAttribute("login");
            session.setMaxInactiveInterval(10*60) ;
            System.out.println(req.getContextPath());
            resp.sendRedirect(req.getContextPath());
        }
    }

}

