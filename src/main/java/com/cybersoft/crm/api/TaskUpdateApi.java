package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "taskUpdateApi",urlPatterns = "/api/task-update")
public class TaskUpdateApi extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        TasksModel tasksModel = new TasksModel();
        tasksModel.setId(Integer.parseInt(req.getParameter("id")));
        tasksModel.setName(req.getParameter("name"));
        tasksModel.setStart_date(req.getParameter("start_date"));
        tasksModel.setEnd_date(req.getParameter("end_date"));
        tasksModel.setUser_id(Integer.parseInt(req.getParameter("user_id")));
        tasksModel.setJob_id(Integer.parseInt(req.getParameter("job_id")));
        tasksModel.setStatus_id(Integer.parseInt(req.getParameter("status_id")));
        boolean isSuccess = taskService.updateTasksServiceByClass(tasksModel);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Update task thành công" : "Update task thất bại");
        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        out.print(json);
        out.flush();
    }
}