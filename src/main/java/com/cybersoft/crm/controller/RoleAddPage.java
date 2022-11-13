package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "roleadd",urlPatterns = "/role-add")
public class RoleAddPage extends HttpServlet {
    private RoleService roleService=new RoleService();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/role-add.html").forward(req,resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name")!=null && req.getParameter("description")!=null
                && !req.getParameter("name").equals("") && !req.getParameter("description").equals("")){
            String name= req.getParameter("name");
            String description= req.getParameter("description");
            boolean isInsertRoleSuccess=roleService.insertRoleService(name,description);
            System.out.println("isInsertRoleSuccess "+isInsertRoleSuccess);
            if (isInsertRoleSuccess){
                resp.sendRedirect(req.getContextPath()+"/role");
            }
        }else
            req.getRequestDispatcher("/role-add.html").forward(req,resp);

//        req.setAttribute("roles",roleService.getAllRoles());
    }
}
