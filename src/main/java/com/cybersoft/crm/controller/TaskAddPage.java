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

@WebServlet(name = "taskadd",urlPatterns = "/task-add")
public class TaskAddPage extends HttpServlet {
    private JobService jobService=new JobService();
    private UserService userService=new UserService();
    private TaskService taskService=new TaskService();
    private StatusService statusService=new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null
                && req.getParameter("start_date") != null
                && req.getParameter("end_date") != null
                && req.getParameter("user_id") != null
                && req.getParameter("job_id") != null
                && req.getParameter("status_id") != null
                && !req.getParameter("name").equals("")
                && !req.getParameter("start_date").equals("")
                && !req.getParameter("end_date").equals("")
                && !req.getParameter("user_id").equals("")
                && !req.getParameter("job_id").equals("")
                && !req.getParameter("status_id").equals(""))
        {
            String name = req.getParameter("name");
            String start_date = req.getParameter("start_date");
            String end_date = req.getParameter("end_date");
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            int job_id = Integer.parseInt(req.getParameter("job_id"));
            int status_id = Integer.parseInt(req.getParameter("status_id"));
            boolean isSuccess = taskService.insertTaskService(name, start_date, end_date, user_id, job_id, status_id);
            if (isSuccess) {
                resp.sendRedirect(req.getContextPath() + "/task");
            }
        } else {
            req.setAttribute("jobs", jobService.getAllJobs());
            req.setAttribute("users", userService.getAllUsers());
            req.setAttribute("status", statusService.getStatusService());
            req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
        }
    }
}