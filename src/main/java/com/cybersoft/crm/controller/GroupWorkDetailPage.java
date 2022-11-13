package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "groupworkdetail",urlPatterns = "/groupwork-detail")
public class GroupWorkDetailPage extends HttpServlet {
    private JobService jobService = new JobService();

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
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("groupworkdetail", jobService.getTaskServiceDetailById(id));
            req.getRequestDispatcher("/groupwork-detail.jsp").forward(req, resp);
    }
}