package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.service.ArticleTagService;
import com.bdqn.ux_share.service.impl.ArticleTagServiceImpl;
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

@WebServlet("/articleTag")
public class ArticleTagController extends HttpServlet {
    private ArticleTagService articleTagService = new ArticleTagServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            String opr = req.getParameter("opr");
            switch (opr) {
                case "getAll": {
                    getAll(req,resp);
                    break;
                }
                case "getAllAdd":{
                    getAllAdd(req,resp);
                }
                case "addArticleTag": {
                    addArticleTag(req,resp);
                    break;
                }
                case "delArticleTag": {
                    delArticleTag(req,resp);
                    break;
                }
                case "updateArticleTag": {
                    updateArticleTag(req,resp);
                    break;
                }
                case "getByPage": {
                    getByPage(req,resp);
                    break;
                }
                case "getByPageForName": {
                    getByPageForName(req,resp);
                    break;
                }
                default: {
                    resp.sendRedirect("/404.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/500.jsp");
        }
    }

    private void getAllAdd(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext application = req.getServletContext();
        List<ArticleTag> articleTags = null;
        if(application.getAttribute("articleTagsList") != null){
            articleTags = (List<ArticleTag>)application.getAttribute("articleTagsList");
        }else{
            try {
                articleTags = articleTagService.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        PrintResult.print(resp, Common.success(articleTags));
    }

    /**
     * 条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String page = "1";
        String size = "4";
        String tname = req.getParameter("fname");
        System.out.println(page);
        System.out.println(size);
        System.out.println(tname);
        // 调用service
        Page<ArticleTag> pageData = articleTagService.getByPageByFname(new Page<ArticleTag>(page,size),tname);
        PrintResult.print(resp,Common.success(pageData));
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
        Page<ArticleTag> pageData = articleTagService.getByPage(new Page<ArticleTag>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }

    public void getByPageForName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        resp.setContentType("application/json;charset=UTF-8");
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        String uname = req.getParameter("uname");
        System.out.println(page);
        System.out.println(size);
        System.out.println(uname);
        System.out.println("我被调用了");
        Page<ArticleTag> pageData = articleTagService.getByPageByFname(new Page<ArticleTag>(page,size),uname);
        PrintResult.print(resp,Common.success(pageData));
    }

    /**
     * 删除文章分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void delArticleTag(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String aid = req.getParameter("aid");
        System.out.println(aid);
        System.out.println("我被调用了");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(articleTagService.delArticleTag(Long.valueOf(aid))){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }

    /**
     * 添加数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void addArticleTag(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String tname = req.getParameter("tname");
        System.out.println("添加哦");
        System.out.println(tname);
        ArticleTag articleTag = new ArticleTag();
        articleTag.setTagName(tname);
        try{
            if(articleTagService.addArticleTag(articleTag)){
                PrintResult.print(resp, Common.success());
            }else{
                PrintResult.print(resp,Common.failed());
            }
        } catch (Exception e){
            e.printStackTrace();
            PrintResult.print(resp,Common.failed(e.getMessage()));
        }
    }

    /**
     * 修改分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void updateArticleTag(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("我是update，我被调用了");
        Integer integer = Integer.valueOf(req.getParameter("tid"));
        System.out.println(integer);
        String tname = req.getParameter("tname");
        System.out.println("获取的参数为："+tname);
        System.out.println("获取的Id为："+22);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(articleTagService.updateArticleTag(new ArticleTag(integer,tname))){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }
}
