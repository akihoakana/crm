package com.cybersoft.crm.controller;

import com.cybersoft.crm.model.UserModel;
import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "useradd",urlPatterns = "/user-add")
public class UserAddPage extends HttpServlet {
    private UserService userService=new UserService();
    private UserModel userModel=new UserModel();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("fullname") != null
                && req.getParameter("email") != null
                && req.getParameter("password") != null
                && req.getParameter("phone") != null
                && req.getParameter("country") != null
                && !req.getParameter("fullname").equals("")
                && !req.getParameter("email").equals("")
                && !req.getParameter("password").equals("")
                && !req.getParameter("phone").equals("")
                && !req.getParameter("country").equals(""))
        {
            userModel.setFullname(req.getParameter("fullname"));
            userModel.setPassword(req.getParameter("password"));
            userModel.setEmail(req.getParameter("email"));
            userModel.setPhone(req.getParameter("phone"));
            userModel.setCountry(req.getParameter("country"));
            boolean isSuccess = userService.insertUsersService(userModel);
            if (isSuccess) {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        } else {
            req.getRequestDispatcher("/user-add.html").forward(req, resp);
        }
    }
}
