package com.bdqn.ux_share.servlet;

import com.alibaba.fastjson.JSON;
import com.bdqn.ux_share.dao.ArticleDao;
import com.bdqn.ux_share.dao.impl.ArticleDaoImpl;
import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.ArticleSelectDto;
import com.bdqn.ux_share.pojo.condition.UserCondition;
import com.bdqn.ux_share.service.ArticleCommentService;
import com.bdqn.ux_share.service.ArticleService;
import com.bdqn.ux_share.service.ArticleTagService;
import com.bdqn.ux_share.service.impl.ArticleCommentServiceImpl;
import com.bdqn.ux_share.service.impl.ArticleServiceImpl;
import com.bdqn.ux_share.service.impl.ArticleTagServiceImpl;
import com.bdqn.ux_share.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/article")
public class ArticleController extends HttpServlet {

    ArticleDao articleDao = new ArticleDaoImpl();
    ArticleService as = new ArticleServiceImpl();
    private ArticleTagService ats = new ArticleTagServiceImpl();
    private ArticleCommentService acs = new ArticleCommentServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
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
                case "getAllDto": {
                    getAllDto(req, resp);
                    break;
                }
                case "getByPage": {
                    getByPage(req, resp);
                    break;
                }
                case "getUserPage":{
                    getUserPage(req,resp);
                }
                case "getAllArticle": {
                    getAllArticle(req, resp);
                    break;
                }
                case "delArticleByIds": {
                    delArticleByIds(req, resp);
                    break;
                }
                case "getHotArticle":{
                    getHotArticle(req,resp);
                    break;
                }
                case "getArticleDetails":{
                    getArticleDetails(req,resp);
                    break;
                }
                case "getCollectNum":{
                    getCollectNum(req,resp);
                    break;
                }
                case "getArticleTag":{
                    getArticleTag(req,resp);
                    break;
                }
                case "addArticlePv":{
                    addArticlePv(req,resp);
                    break;
                }
                case "addArticleComment":{
                    addArticleComment(req,resp);
                    break;
                }
                case "updateState":{
                    updateState(req,resp);
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

    private void getByUserPage(HttpServletRequest req, HttpServletResponse resp) {
// 请求分页参数
        String page = req.getParameter("page");
        System.out.println(ConsoleColors.GREEN + page);
        String size = req.getParameter("size");
        System.out.println(ConsoleColors.GREEN + size);
        Page<ArticleDto> pageBean = new Page<ArticleDto>(page, size);
        BufferedReader br = null;
        try {
            br = req.getReader();
            String params = br.readLine();//json字符串
            //转为 ArticleSelectDto 文章查询条件实体
            ArticleSelectDto articleSelectDto = JSON.parseObject(params, ArticleSelectDto.class);
            pageBean = as.selectByPageAndCondition(pageBean, articleSelectDto.getUsername(), articleSelectDto.getStartDate(), articleSelectDto.getEndDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PrintResult.print(resp, Common.success(pageBean));
    }

    private void updateState(HttpServletRequest req, HttpServletResponse resp) {
            int id = Integer.parseInt(req.getParameter("id"));
            int state = Integer.parseInt(req.getParameter("state"));
            if(articleDao.updateArticleState(id,state)){
                PrintResult.print(resp,Common.success(true));
            }else {
                PrintResult.print(resp,Common.failed("更改状态失败"));
            }

    }

    public void getCollectNum(HttpServletRequest req, HttpServletResponse resp){
        String aid = req.getParameter("aid");
        PrintResult.print(resp, Common.success(as.getCollectCount(Integer.parseInt(aid))));
    }
    public void getArticleTag(HttpServletRequest req, HttpServletResponse resp){
        String aid = req.getParameter("aid");
        PrintResult.print(resp, Common.success(ats.getArticleTag(Integer.parseInt(aid))));
    }
    public void getHotArticle(HttpServletRequest req, HttpServletResponse resp){
        PrintResult.print(resp, Common.success(as.getHotArticle()));
    }

    public void addArticlePv(HttpServletRequest req, HttpServletResponse resp){
        int aid = Integer.valueOf(req.getParameter("aid"));
        PrintResult.print(resp, Common.success(as.addArticlePv(aid)));
    }

    // 查询所有文章列表
    private void getAll(HttpServletRequest req, HttpServletResponse resp) {
        List<Article> articleList = null;
        try {
            articleList = as.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintResult.print(resp, Common.success(articleList));
    }

    /**
     * 根据请求的条件参数,查询结果
     *
     * @param req
     * @param resp
     */
    private void getAllDto(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");
        List<ArticleDto> articleDtoList = new ArrayList<>();
        try {
            articleDtoList = as.selectByPageBeanAndCondition(username, startDateString, endDateString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintResult.print(resp, Common.success(articleDtoList));
    }


    /**
     * 分页文章条件查询
     * @param req
     * @param resp
     */
    private void getByPage(HttpServletRequest req, HttpServletResponse resp) {
        // 请求分页参数
        String page = req.getParameter("page");
        System.out.println(ConsoleColors.GREEN + page);
        String size = req.getParameter("size");
        System.out.println(ConsoleColors.GREEN + size);
        Page<ArticleDto> pageBean = new Page<ArticleDto>(page, size);
        BufferedReader br = null;
        try {
            br = req.getReader();
            String params = br.readLine();//json字符串
            //转为 ArticleSelectDto 文章查询条件实体
            ArticleSelectDto articleSelectDto = JSON.parseObject(params, ArticleSelectDto.class);
            pageBean = as.selectByPageAndCondition(pageBean, articleSelectDto.getUsername(), articleSelectDto.getStartDate(), articleSelectDto.getEndDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PrintResult.print(resp, Common.success(pageBean));
    }


    /**
     * 根据文章编号删除文章信息和有关标签
     *
     * @param req
     * @param resp
     */
    private void delArticleByIds(HttpServletRequest req, HttpServletResponse resp) {
        // 获取参数
        String tagId = req.getParameter("selectedIds");
        ArticleDto articleDto = new ArticleDto();
        tagId = "{" + tagId + "}";
        int[] ids = ByteArrayConvert.convertIntToArray(tagId);
        articleDto.setTagIds(ids);
        try {
            if (as.delArticleById(articleDto)) {
                PrintResult.print(resp, Common.success("删除成功"));
            } else {
                PrintResult.print(resp, Common.failed("删除失败"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 分页查询文章列表根据参数分类
     * @param req
     * @param resp
     */
    public void getAllArticle(HttpServletRequest req, HttpServletResponse resp){
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        // 调用service
        String acn = req.getParameter("artClassName");
        String sortBy = req.getParameter("sortBy");
        UserCondition userCondition = new UserCondition();
        userCondition.getArticleClass().setClassName(acn);
        Page<UserCondition> pageData = as.getAllArticle(new Page<UserCondition>(page,size),userCondition,sortBy);
        PrintResult.print(resp, Common.success(pageData));
    }

    /**
     * 获取文章详情
     * @param req
     * @param resp
     */
    public void getArticleDetails(HttpServletRequest req, HttpServletResponse resp){
        String aid = req.getParameter("aid");
        System.out.println(aid);
        PrintResult.print(resp, Common.success(as.getArticleDetails(Integer.parseInt(aid))));
    }
    public void getLikeNum(HttpServletRequest req, HttpServletResponse resp){
        String aid = req.getParameter("aid");
        PrintResult.print(resp, Common.success(as.getLikeCount(Integer.parseInt(aid))));
    }

    public void addArticleComment(HttpServletRequest req, HttpServletResponse resp){
        int aid = Integer.valueOf(req.getParameter("aid"));
        int uid = Integer.valueOf(req.getParameter("uid"));
        String commentText = req.getParameter("commentText");
        ArticleComment ac = new ArticleComment(aid,uid,commentText);
        PrintResult.print(resp, Common.success(acs.addComment(ac)));
    }

    private void getUserPage(HttpServletRequest req, HttpServletResponse resp) {
        //请求分页参数
        String page = req.getParameter("page");
        System.out.println(ConsoleColors.GREEN + page);
        String size = req.getParameter("size");
        System.out.println(ConsoleColors.GREEN + size);
        String temp =req.getParameter("choose");
        String temp1 = req.getParameter("userId");
        Integer choose = 0;
        Integer userId =0 ;
        System.out.println("测试"+temp+"/"+temp1);
        try {
            choose = Integer.parseInt(req.getParameter("choose"));
        } catch (NumberFormatException e) {
            System.out.println("输入错误：" + e.getMessage());
        }
        try {
            userId = Integer.parseInt(req.getParameter("userId"));
        } catch (NumberFormatException e) {
            System.out.println("输入错误：" + e.getMessage());
        }
        System.out.println(ConsoleColors.GREEN+choose);
        System.out.println(ConsoleColors.GREEN+userId);
        Page<ArticleDto> pageBean = new Page<ArticleDto>(page, size);
        ArticleDto articleDto = new ArticleDto();
        articleDto.setUserId(Integer.valueOf(userId));
        articleDto.setChoose(choose);
        try {
            pageBean = as.selectByPageAndConditionUser(pageBean,articleDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PrintResult.print(resp, Common.success(pageBean));
    }

}
