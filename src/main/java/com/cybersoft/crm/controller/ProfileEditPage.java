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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "profileedit",urlPatterns = "/profile-edit")
public class ProfileEditPage extends HttpServlet {
    private JobService jobService=new JobService();
    private UserService userService=new UserService();
    private TaskService taskService=new TaskService();
    private StatusService statusService=new StatusService();
    private TasksModel tasksModel=new TasksModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setAttribute("tasks", taskService.getAllTasks());
            req.setAttribute("jobs", jobService.getAllJobs());
            req.setAttribute("status", statusService.getStatusService());
            req.getRequestDispatcher("/profile-edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id= (int) session.getAttribute("id");
        int task_id=0;
        if (req.getParameter("task_id")!=null && !req.getParameter("task_id").equals("")){
            task_id= Integer.parseInt(req.getParameter("task_id"));
            session.setAttribute("task_id",task_id);
        }
        if (req.getParameter("name")!=null
                && req.getParameter("start_date")!=null
                && req.getParameter("end_date")!=null
                && req.getParameter("job_id")!=null
                && req.getParameter("status_id")!=null
                && !req.getParameter("name").equals("")
                && !req.getParameter("start_date").equals("")
                && !req.getParameter("end_date").equals("")
                && !req.getParameter("job_id").equals("")
                && !req.getParameter("status_id").equals("")
        )
        {
            tasksModel.setName(req.getParameter("name"));
            tasksModel.setStart_date(req.getParameter("start_date"));
            tasksModel.setEnd_date(req.getParameter("end_date"));
            tasksModel.setJob_id(Integer.parseInt(req.getParameter("job_id")));
            tasksModel.setStatus_id(Integer.parseInt(req.getParameter("status_id")));
            tasksModel.setId((Integer) session.getAttribute("task_id"));
            tasksModel.setUser_id(id);
            LocalDate start =LocalDate.parse(req.getParameter("start_date"));
            LocalDate end =LocalDate.parse(req.getParameter("end_date"));
            boolean isupdateUsersServiceByTaskSuccess=userService.updateUsersServiceByTask(tasksModel);
            if (isupdateUsersServiceByTaskSuccess  && end.isAfter(start)){
                resp.sendRedirect(req.getContextPath()+"/profile");
            }
            else{
                req.setAttribute("tasks", taskService.getAllTasks());
                req.setAttribute("jobs", jobService.getAllJobs());
                req.setAttribute("status", statusService.getStatusService());
                req.getRequestDispatcher("/profile-edit.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("tasks", taskService.getAllTasks());
            req.setAttribute("jobs", jobService.getAllJobs());
            req.setAttribute("status", statusService.getStatusService());
            req.getRequestDispatcher("/profile-edit.jsp").forward(req,resp);
        }
    }
}