package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.TasksModel;
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

@WebServlet(name = "profileedit",urlPatterns = "/profile-edit")
public class ProfileEditPage extends HttpServlet {
    private JobService jobService=new JobService();
    private UserService userService=new UserService();
    private TaskService taskService=new TaskService();
    private StatusService statusService=new StatusService();
    private TasksModel tasksModel=new TasksModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name")!=null
                && req.getParameter("start_date")!=null
                && req.getParameter("end_date")!=null
                && req.getParameter("job_id")!=null
                && req.getParameter("status_id")!=null
                && req.getParameter("tasks_id")!=null
                && req.getParameter("user_id")!=null
                && !req.getParameter("name").equals("")
                && !req.getParameter("start_date").equals("")
                && !req.getParameter("end_date").equals("")
                && !req.getParameter("job_id").equals("")
                && !req.getParameter("status_id").equals("")
                && !req.getParameter("tasks_id").equals("")
                && !req.getParameter("user_id").equals(""))
        {
            tasksModel.setName(req.getParameter("name"));
            tasksModel.setStart_date(req.getParameter("start_date"));
            tasksModel.setEnd_date(req.getParameter("end_date"));
            tasksModel.setJob_id(Integer.parseInt(req.getParameter("job_id")));
            tasksModel.setStatus_id(Integer.parseInt(req.getParameter("status_id")));
            tasksModel.setId(Integer.parseInt(req.getParameter("tasks_id")));
            tasksModel.setUser_id(Integer.parseInt(req.getParameter("user_id")));

            boolean isupdateUsersServiceByTaskSuccess=userService.updateUsersServiceByTask(tasksModel);
            if (isupdateUsersServiceByTaskSuccess){
                resp.sendRedirect(req.getContextPath()+"/role");
            }
        }else{
            req.setAttribute("tasks", taskService.getAllTasks());
            req.setAttribute("jobs", jobService.getAllJobs());
            req.setAttribute("status", statusService.getStatusService());
            req.getRequestDispatcher("/profile-edit.jsp").forward(req,resp);
        }


    }
}