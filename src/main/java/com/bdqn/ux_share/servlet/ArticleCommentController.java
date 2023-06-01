package com.bdqn.ux_share.servlet;

import com.alibaba.fastjson.JSON;
import com.bdqn.ux_share.dao.ArticleCommentDao;
import com.bdqn.ux_share.dao.impl.ArticleCommentDaoImpl;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.service.ArticleCommentService;
import com.bdqn.ux_share.service.impl.ArticleCommentServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.Page;
import com.bdqn.ux_share.util.PrintResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/articlecomment")
public class ArticleCommentController extends HttpServlet {
    private ArticleCommentService articleCommentService = new ArticleCommentServiceImpl();
    private ArticleCommentDao articleCommentDao = new ArticleCommentDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 2.设置响应格式
        resp.setContentType("text/html;charset=UTF-8");
        // 3.获取请求参数
        try {
            String opr = req.getParameter("opr");
            switch (opr) {
                case "getAll": {
                    getAll(req, resp);
                    break;
                }
                case "delete": {
                    delete(req, resp);
                    break;
                }
                case "getcount": {
                    getcount(req, resp);
                    break;
                }
                case "getByPage": {
                    getByPage(req, resp);
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

    private void getByPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        // 调用service
        Page<ArticleComment> pageData = articleCommentService.getByPage(new Page<ArticleComment>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSONString(articleCommentService.getAll()));
        out.flush();
        out.close();
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            String commentid = req.getParameter("commentid");
            if(articleCommentService.delete(Integer.valueOf(commentid))){
                PrintResult.print(resp, Common.success());
            }else{
                PrintResult.print(resp, Common.failed());
            }
        } catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }
    public void getcount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSONString(articleCommentService.getCount()));
        out.flush();
        out.close();
    }
}
