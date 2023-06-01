package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.Root;
import com.bdqn.ux_share.service.RootService;
import com.bdqn.ux_share.service.impl.RootServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.PrintResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/root")
public class RootController extends HttpServlet {

    RootService rootService = new RootServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String opr = req.getParameter("opr");
            switch (opr) {
                case "getAll": {
                    getAll(req, resp);
                    break;
                }
                default: {
                    resp.sendRedirect("/404.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/500.html");
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) {
        List<Root> rootList = rootService.getAll();
        PrintResult.print(resp, Common.success(rootList));
    }
}
