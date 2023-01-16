package com.cybersoft.crm.controller;

import com.cybersoft.crm.DTO.GroupWorkDetailsDTO;
import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "groupworkdetail",urlPatterns = "/groupwork-details")
public class GroupWorkDetailPage extends HttpServlet {
    private JobService jobService = new JobService();
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int job_id= Integer.parseInt(req.getParameter("job_id"));
        String employeeJsonString = this.gson.toJson(taskService.getGroupWorkDetailsByJobId(job_id));
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        int job_id=0;
//        if (req.getParameter("job_id")!=null && !req.getParameter("job_id").equals("")){
//            job_id= Integer.parseInt(req.getParameter("job_id"));
//            session.setAttribute("job_id",job_id);
//        }
        int job_id= Integer.parseInt(req.getParameter("job_id"));
        req.setAttribute("profilecount1",jobService.getQuantityJobsById(job_id,1));
        req.setAttribute("profilecount2",jobService.getQuantityJobsById(job_id,2));
        req.setAttribute("profilecount3",jobService.getQuantityJobsById(job_id,3));
        req.setAttribute("groupworkdetail",taskService.getGroupWorkDetailsByJobId(job_id));
        taskService.getGroupWorkDetailsByJobId(job_id).forEach(groupWorkDetailsDTO->{
            System.out.println("GroupWorkDetailsDTO.getId() = " + groupWorkDetailsDTO.getId());
            System.out.println("GroupWorkDetailsDTO.getId() = " + groupWorkDetailsDTO.getFullname());
            groupWorkDetailsDTO.getStatusGroupWorkDetailsModelModels().forEach(statusGroupWorkDetailsModelModel -> {
                System.out.println("statusGroupWorkDetailsModelModel.getId() = " + statusGroupWorkDetailsModelModel.getId());
                System.out.println("statusGroupWorkDetailsModelModel.getNameStatus() = " + statusGroupWorkDetailsModelModel.getNameStatus());
                statusGroupWorkDetailsModelModel.getTaskGroupWorkDetailsModels().forEach(taskGroupWorkDetailsModel -> {
                    System.out.println("taskGroupWorkDetailsModel.getId() = " + taskGroupWorkDetailsModel.getId());
                    System.out.println("taskGroupWorkDetailsModel.getNameTask() = " + taskGroupWorkDetailsModel.getNameTask());
                    System.out.println("taskGroupWorkDetailsModel.getStart_date() = " + taskGroupWorkDetailsModel.getStart_date());
                    System.out.println("taskGroupWorkDetailsModel.getEnd_date() = " + taskGroupWorkDetailsModel.getEnd_date());
                });
            });
        });
        req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
    }
}