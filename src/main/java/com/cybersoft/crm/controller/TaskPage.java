package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "task",urlPatterns = "/task")
public class TaskPage extends HttpServlet {
    private JobService jobService=new JobService();
    private UserService userService=new UserService();
    private TaskService taskService=new TaskService();
    private StatusService statusService=new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks", taskService.getAllTasks());
        req.setAttribute("jobs", jobService.getAllJobs());
        req.setAttribute("users", userService.getAllUsers());
        req.setAttribute("status", statusService.getStatusService());
        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }
}
