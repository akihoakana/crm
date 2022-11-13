package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "userdetail",urlPatterns = "/user-detail")
public class UserDetailPage extends HttpServlet {
    private JobService jobService = new JobService();
    private TaskService taskService = new TaskService();

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getParameter("id")!=null && !req.getParameter("id").equals("")){
//            int id = Integer.parseInt(req.getParameter("id"));
//            req.setAttribute("groupworkdetail", jobService.getTaskServiceDetailById(id));
//            req.getRequestDispatcher("/groupwork-detail.jsp").forward(req, resp);
//        }
//        else
//            req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("detail", taskService.getAllTasks());
        req.getRequestDispatcher("/user-detail.jsp").forward(req, resp);
    }
}