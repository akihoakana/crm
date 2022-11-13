package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "usersDeleteApi",urlPatterns = "/api/user-delete")
public class UserDeleteApi extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        int id= Integer.parseInt(req.getParameter("id"));
        boolean isSuccess=userService.deleteUsersServiceById(id);
        ResponseData responseData=new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess?"Xoá user thành công":"Xoá user thất bại");
        Gson gson=new Gson();
        String json= gson.toJson(responseData);
        out.print(json);
        out.flush();
    }
}