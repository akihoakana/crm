package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.StatusModel;
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
import java.util.List;

@WebServlet(name = "profile",urlPatterns = "/profile")
public class ProfilePage extends HttpServlet {

    private UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id= (int) session.getAttribute("id");
        req.setAttribute("profiles",userService.getUserAllTasksById(id));//check
        req.setAttribute("fullname",userService.getFullnameEmailByIdService(id).getUsersfullname());
        req.setAttribute("email",userService.getFullnameEmailByIdService(id).getUsersemail());
        req.setAttribute("profilecount1",userService.getQuantityUsersById(id,1));
        req.setAttribute("profilecount2",userService.getQuantityUsersById(id,2));
        req.setAttribute("profilecount3",userService.getQuantityUsersById(id,3));
        req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }
}
