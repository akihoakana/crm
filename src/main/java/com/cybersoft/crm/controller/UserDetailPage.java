package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "userdetail",urlPatterns = "/user-detail")
public class UserDetailPage extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") != null && !req.getParameter("id").equals("")){
            id = Integer.parseInt(req.getParameter("id"));
            System.out.println("1");
        }else {

            HttpSession session = req.getSession();
            id= (int) session.getAttribute("id");
            System.out.println("id = " + id);
        }
        req.setAttribute("profilecount1",userService.getQuantityUsersById(id,1));
        req.setAttribute("profilecount2",userService.getQuantityUsersById(id,2));
        req.setAttribute("profilecount3",userService.getQuantityUsersById(id,3));
        req.setAttribute("fullname",userService.getFullnameEmailByIdService(id).getUsersfullname());
        req.setAttribute("email",userService.getFullnameEmailByIdService(id).getUsersemail());
        req.setAttribute("status1",userService.getTaskUsersByIdAndStatusService(id,1));
        req.setAttribute("status2",userService.getTaskUsersByIdAndStatusService(id,2));
        req.setAttribute("status3",userService.getTaskUsersByIdAndStatusService(id,3));
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}