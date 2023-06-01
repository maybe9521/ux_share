package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.service.UserService;
import com.bdqn.ux_share.service.impl.UserServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.PrintResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService us = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opr = req.getParameter("opr");
        switch (opr){
            case "userAtt":{
                userAtt(req,resp);
                break;
            }case "userCollect":{
                userCollect(req,resp);
                break;
            }case "userLike":{
                userLike(req,resp);
                break;
            }
            case "exitUserAtt":{
                exitUserAtt(req,resp);
                break;
            }case "exitUserCollect":{
                exitUserCollect(req,resp);
                break;
            }case "getLoginUserMsg":{
                getLoginUserMsg(req,resp);
                break;
            }
            default:{
                resp.sendRedirect("404.jsp");
                break;
            }
        }
    }

    /**
     * 用户关注
     * @param req
     * @param resp
     */
    public void userAtt(HttpServletRequest req, HttpServletResponse resp){
        //获得请求参数
        int uid = Integer.valueOf(req.getParameter("uid"));
        int auid =Integer.valueOf( req.getParameter("auid"));
        PrintResult.print(resp, Common.success(us.addUserAtt(uid,auid)));
    }

    /**
     * 用户收藏
     * @param req
     * @param resp
     */
    public void userCollect(HttpServletRequest req, HttpServletResponse resp){
        //获得请求参数
        int uid = Integer.valueOf(req.getParameter("uid"));
        int aid =Integer.valueOf( req.getParameter("aid"));
        PrintResult.print(resp, Common.success(us.addUserCollect(uid,aid)));
    }
    public void userLike(HttpServletRequest req, HttpServletResponse resp){
        //获得请求参数
        int aid =Integer.valueOf( req.getParameter("aid"));
        PrintResult.print(resp, Common.success(us.addUserLike(aid)));
    }

    /**
     * 判断是否关注
     * @param req
     * @param resp
     */
    public void exitUserAtt(HttpServletRequest req, HttpServletResponse resp){
        //获得请求参数
        int uid = Integer.valueOf(req.getParameter("uid"));
        int auid =Integer.valueOf( req.getParameter("auid"));
        System.out.println(uid+"abc"+auid);
        PrintResult.print(resp, Common.success(us.exitUserAtt(uid,auid)));
    }

    /**
     * 判断是否收藏
     * @param req
     * @param resp
     */
    public void exitUserCollect(HttpServletRequest req, HttpServletResponse resp){
        //获得请求参数
        int aid = Integer.valueOf(req.getParameter("aid"));
        int uid =Integer.valueOf( req.getParameter("uid"));
        PrintResult.print(resp, Common.success(us.exitUserCollect(uid,aid)));
    }
    /**
     * 返回用户详细信息
     *
     * @param req  用户账户和用户密码 或者用户id
     * @param resp 用户详细信息
     */
    private void getLoginUserMsg(HttpServletRequest req, HttpServletResponse resp) {
//        UserMsg userMsg = new UserMsg();
//        userMsg.setUserId(2);
//        userMsg.setUserRootId(4);
//        userMsg.setUserIcon("/upload/icon/user666/01.jpg");
//        req.getSession().setAttribute("loginUser",userMsg);
        UserMsg loginUser = (UserMsg) req.getSession().getAttribute("loginUser");
        PrintResult.print(resp, Common.success(loginUser));
    }
}
