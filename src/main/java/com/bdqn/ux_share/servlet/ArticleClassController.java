package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.service.ArticleClassService;
import com.bdqn.ux_share.service.impl.ArticleClassServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.Page;
import com.bdqn.ux_share.util.PrintResult;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/articleClass")
public class ArticleClassController extends HttpServlet {
    private ArticleClassService articleClassService = new ArticleClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try{
            String opr = req.getParameter("opr");
            switch (opr){
                case "getAll": {
                    getAll(req,resp);
                    break;
                }
                case "getAllAdd":{
                    getAllAdd(req,resp);
                }
                case "getAllByName":{
                    getAllByName(req,resp);
                    break;
                }
                case "delArticleClass":{
                    delArticleClass(req,resp);
                    break;
                }
                case "addArticleClass":{
                    addArticleClass(req,resp);
                    break;
                }
                case "updateArticleClass":{
                    updateArticleClass(req,resp);
                    break;
                }
                case "getByPage":{
                    getByPage(req,resp);
                    break;
                }
                default:{
                    resp.sendRedirect("/404.html");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("/500.html");
        }
    }

    private void getAllAdd(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext application = req.getServletContext();
        List<ArticleClass> articleClasses = null;
        if(application.getAttribute("articleClassList") != null){
            articleClasses = (List<ArticleClass>)application.getAttribute("articleClassList");
        }else{
            try {
                articleClasses = articleClassService.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        PrintResult.print(resp, Common.success(articleClasses));
    }

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("我被调用lo");
        String tname = req.getParameter("tname");
        System.out.println(tname);
        System.out.println("进入空的！");
        ServletContext application = req.getServletContext();
        List<ArticleClass> articleClasses = null;
        if(application.getAttribute("articleClassList") != null){
            articleClasses = (List<ArticleClass>)application.getAttribute("articleClassList");
        }else{
            articleClasses = articleClassService.getAll();
        }
        PrintResult.print(resp, Common.success(articleClasses));
    }

    /**
     * 查询所有通过名字
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getAllByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("我被调用了");
        String tname = req.getParameter("tname");
        System.out.println(tname);
        ServletContext application = req.getServletContext();
        List<ArticleClass> articleClasses = null;
        if(application.getAttribute("articleClassList") != null){
            articleClasses = (List<ArticleClass>)application.getAttribute("articleClassList");
        }else{
            articleClasses = articleClassService.getAllByName(tname);
        }
        PrintResult.print(resp, Common.success(articleClasses));
    }

    /**
     * 删除文章分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void delArticleClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String aid = req.getParameter("aid");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(articleClassService.delArticleClass(Long.valueOf(aid))){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        System.out.println(page);
        System.out.println(size);
        // 调用service
        Page<ArticleClass> pageData = articleClassService.getByPage(new Page<ArticleClass>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }

    /**
     * 添加
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void addArticleClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String tname = req.getParameter("tname");
        System.out.println(tname);
        ArticleClass articleClass = new ArticleClass();
        articleClass.setClassName(tname);
        try{
            if(articleClassService.addArticleClass(articleClass)){
                PrintResult.print(resp, Common.success());
            }else{
                PrintResult.print(resp,Common.failed());
            }
        } catch (Exception e){
            e.printStackTrace();
            PrintResult.print(resp,Common.failed(e.getMessage()));
        }
    }

    //更新
    private void updateArticleClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Integer tid = Integer.parseInt(req.getParameter("tid"));
        String tname = req.getParameter("tname");
        System.out.println(tid);
        System.out.println(tname);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(articleClassService.updateArticleClass(new ArticleClass(tid,tname))){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }
}
