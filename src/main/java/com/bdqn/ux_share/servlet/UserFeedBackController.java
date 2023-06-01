package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.UserFeedback;
import com.bdqn.ux_share.service.UserFeedbackService;
import com.bdqn.ux_share.service.impl.UserFeedbackServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.Page;
import com.bdqn.ux_share.util.PrintResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback")
public class UserFeedBackController extends HttpServlet {
    private UserFeedbackService ufs = new UserFeedbackServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String opr = req.getParameter("opr");
        switch (opr){
            case "getAllFeedback":{
                getAllFeedback(req,resp);
                break;
            }
            case "updateFeedback":{
                updateFeedback(req,resp);
                break;
            }
            case "insertFeedback":{
                insertFeedback(req,resp);
                break;
            }
        }
    }

    public void getAllFeedback(HttpServletRequest req, HttpServletResponse resp){
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        Page<UserFeedback> pageData = ufs.getAllFeedback(new Page<UserFeedback>(page,size));
        PrintResult.print(resp, Common.success(ufs.getAllFeedback(pageData)));
    }
    public void updateFeedback(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("update");
        int fid = Integer.valueOf(req.getParameter("fid"));
        String reMsg = req.getParameter("reMsg");
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setFeedbackId(fid);
        userFeedback.setFeedbackRemsg(reMsg);
        PrintResult.print(resp, Common.success(ufs.updateFeedback(userFeedback)));
    }
    public void insertFeedback(HttpServletRequest req, HttpServletResponse resp){
        UserFeedback userFeedback = new UserFeedback();
        PrintResult.print(resp, Common.success(ufs.insertFeedback(userFeedback)));
    }
}
