package com.cybersoft.crm.api;

import com.cybersoft.crm.PayLoad.ResponseData;
import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.service.RoleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "roleUpdateApi",urlPatterns = "/api/role-update")
public class RoleUpdateApi extends HttpServlet {
    private RoleService roleService=new RoleService();
    private RoleModel roleModel=new RoleModel();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        roleModel.setId(Integer.parseInt(req.getParameter("id")));
        roleModel.setName(req.getParameter("name"));
        roleModel.setDescription(req.getParameter("description"));
        boolean isSuccess=roleService.UpdateRoleServiceById(roleModel);
        ResponseData responseData=new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess?"Update Role thành công":"Update Role thất bại");
        Gson gson=new Gson();
        String json= gson.toJson(responseData);
        out.print(json);
        out.flush();
    }

}