package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "index",urlPatterns ="")
public class IndexPage extends HttpServlet {

    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id= (int) session.getAttribute("id");
        req.setAttribute("profilecount1",userService.getQuantityUsersById(id,1));
        req.setAttribute("profilecount2",userService.getQuantityUsersById(id,2));
        req.setAttribute("profilecount3",userService.getQuantityUsersById(id,3));
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}