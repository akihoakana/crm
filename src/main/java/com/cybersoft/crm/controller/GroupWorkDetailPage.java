package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "groupworkdetail",urlPatterns = "/groupwork-details")
public class GroupWorkDetailPage extends HttpServlet {
    private JobService jobService = new JobService();
    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        int job_id=0;
//        if (req.getParameter("job_id")!=null && !req.getParameter("job_id").equals("")){
//            job_id= Integer.parseInt(req.getParameter("job_id"));
//            session.setAttribute("job_id",job_id);
//        }
        int job_id= Integer.parseInt(req.getParameter("job_id"));
//            req.setAttribute("groupworkdetail", jobService.getTaskServiceDetailById(id));
        req.setAttribute("profilecount1",jobService.getQuantityJobsById(job_id,1));
        req.setAttribute("profilecount2",jobService.getQuantityJobsById(job_id,2));
        req.setAttribute("profilecount3",jobService.getQuantityJobsById(job_id,3));
//        req.setAttribute("name",jobService.getNameByIdJobService(job_id));
//        req.setAttribute("status1",userService.getTaskUsersByIdAndStatusService(job_id,1));
//        req.setAttribute("status2",userService.getTaskUsersByIdAndStatusService(job_id,2));
//        req.setAttribute("status3",userService.getTaskUsersByIdAndStatusService(job_id,3));
        req.setAttribute("name",jobService.getByIdJobService(job_id));

        req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
    }
}