package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.UserModel;
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

@WebServlet(name = "userUpdateApi",urlPatterns = "/api/user-update")
public class UserUpdateApi extends HttpServlet {
    private UserService userService = new UserService();
    private UserModel userModel = new UserModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        userModel.setId(Integer.parseInt(req.getParameter("id")));
        userModel.setRole_id(Integer.parseInt(req.getParameter("role_id")));
        userModel.setEmail(req.getParameter("email"));
        userModel.setPassword(req.getParameter("Password"));
        userModel.setFullname(req.getParameter("fullname"));
        userModel.setFirstname(req.getParameter("firstname"));
        userModel.setLastname(req.getParameter("lastname"));
        userModel.setUsername(req.getParameter("username"));
        boolean isSuccess = userService.updateUsersServiceByClass(userModel);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Update user thành công" : "Update user thất bại");
        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        out.print(json);
        out.flush();
    }
}