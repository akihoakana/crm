package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "profile",urlPatterns = "/profile")
public class ProfilePage extends HttpServlet {

    private UserService userService=new UserService();
    private TasksModel tasksModel=new TasksModel();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        req.setAttribute("profiles",userService.getUserAllTasksById(id));
        req.setAttribute("profilename",tasksModel.getUsersfullname());
        req.setAttribute("profileemail",tasksModel.getUsersemail());
        req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }
}
