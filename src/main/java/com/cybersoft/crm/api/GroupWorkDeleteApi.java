package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.RoleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "groupWorkDeleteApi",urlPatterns = "/api/groupwork-delete")
public class GroupWorkDeleteApi extends HttpServlet {
    private JobService jobService=new JobService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        int id= Integer.parseInt(req.getParameter("id"));
        boolean isSuccess=jobService.deleteJobsServiceById(id);
        ResponseData responseData=new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess?"Xoá groupWork thành công":"Xoá groupWork thất bại");
        Gson gson=new Gson();
        String json= gson.toJson(responseData);
        out.print(json);
        out.flush();
    }
}