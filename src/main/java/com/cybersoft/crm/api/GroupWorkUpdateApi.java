package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.RoleModel;
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

@WebServlet(name = "groupWorkUpdateApi",urlPatterns = "/api/groupwork-update")
public class GroupWorkUpdateApi extends HttpServlet {
    private JobService jobService = new JobService();
    private JobsModel jobsModel = new JobsModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        jobsModel.setId(Integer.parseInt(req.getParameter("id")));
        jobsModel.setName(req.getParameter("name"));
        jobsModel.setStart_date(req.getParameter("start_date"));
        jobsModel.setEnd_date(req.getParameter("end_date"));
        boolean isSuccess = jobService.updateJobsServiceByClass(jobsModel);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Update groupwork thành công" : "Update groupwork thất bại");
        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        out.print(json);
        out.flush();
    }
}