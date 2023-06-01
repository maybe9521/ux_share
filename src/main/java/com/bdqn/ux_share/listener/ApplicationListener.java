package com.bdqn.ux_share.listener;

import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.service.ArticleClassService;
import com.bdqn.ux_share.service.ArticleTagService;
import com.bdqn.ux_share.service.NoticeService;
import com.bdqn.ux_share.service.impl.ArticleClassServiceImpl;
import com.bdqn.ux_share.service.impl.ArticleTagServiceImpl;
import com.bdqn.ux_share.service.impl.NoticeServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 全局上下文监听器
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    ArticleClassService articleClassService = new ArticleClassServiceImpl();
    ArticleTagService articleTagService = new ArticleTagServiceImpl();
    NoticeService noticeService = new NoticeServiceImpl();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("上下文被创建。。。");
        ServletContext application = sce.getServletContext();
        try{// 加载公共列表
            application.setAttribute("noticeList",noticeService.getAll());
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

        try {// 加载文章分类属性
            application.setAttribute("articleClassList", articleClassService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {// 加载文章标签属性
            application.setAttribute("tagList", articleTagService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            HashMap<String, UserMsg> map = new HashMap<String, UserMsg>();
            application.setAttribute("onlineUsers", map);
            System.out.println("系统在线人数：" + map.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("加载脏文过滤器。。。");
            HashMap<String, String> map = new HashMap<>();
            map.put("&", "&amp;");
            map.put("bbb", "*");
            map.put("你", "*");
            application.setAttribute("errorName", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("上下文被销毁");
    }
}
