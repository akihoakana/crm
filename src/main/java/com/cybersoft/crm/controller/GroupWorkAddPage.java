package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@WebServlet(name = "groupworkadd",urlPatterns = "/groupwork-add")
public class GroupWorkAddPage extends HttpServlet {
    private JobService jobService=new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/groupwork-add.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name")!=null
                && req.getParameter("start_date")!=null
                && req.getParameter("end_date")!=null
                && !req.getParameter("name").equals("")
                && !req.getParameter("start_date").equals("")
                && !req.getParameter("end_date").equals("")){
            String name= req.getParameter("name");
            String start_date= req.getParameter("start_date");
            String end_date= req.getParameter("end_date");
            LocalDate start =LocalDate.parse(start_date);
            LocalDate end =LocalDate.parse(end_date);
            boolean isInsertJobSuccess=jobService.insertJobService(name,start_date,end_date);
            if (isInsertJobSuccess && end.isAfter(start)){
                resp.sendRedirect(req.getContextPath()+"/groupwork");
            }
            else{
                req.getRequestDispatcher("/groupwork-add.html").forward(req,resp);
            }
        }else{
            req.getRequestDispatcher("/groupwork-add.html").forward(req,resp);
        }
    }

}