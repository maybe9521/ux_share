package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.ArticleClassDao;
import com.bdqn.ux_share.dao.impl.ArticleClassDaoImpl;
import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.service.ArticleClassService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArticleClassServiceImpl implements ArticleClassService {
    private ArticleClassDao articleClassDao = new ArticleClassDaoImpl();

    @Override
    public List<ArticleClass> getAll() throws SQLException {
        return articleClassDao.getAll();
    }

    @Override
    public List<ArticleClass> getAllByName(String name) throws SQLException {
        return articleClassDao.getAllByName(name);
    }

    /**
     * 删除文章分类
     * @param aid
     * @return
     * @throws SQLException
     */
    @Override
    public boolean delArticleClass(Long aid) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(articleClassDao.delArticleClass(aid) == 1){
                flag = true;
                connection.commit();
            }
        }catch (Exception e){
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(connection,null,null);
        }
        return flag;
    }

    /**
     * 添加标题分类
     * @param articleClass
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addArticleClass(ArticleClass articleClass) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(articleClassDao.addArticleClass(articleClass) == 1){
                flag = true;
                connection.commit();
            }
        }catch (Exception e){
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(connection,null,null);
        }
        return flag;
    }

    /**
     * 修改文章分类
     * @param articleClass
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateArticleClass(ArticleClass articleClass) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加修改时间
            if(articleClassDao.updateArticleClass(articleClass) == 1){
                flag = true;
                connection.commit();
            }
        }catch (Exception e){
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(connection,null,null);
        }
        return flag;
    }

    @Override
    public Page<ArticleClass> getByPage(Page<ArticleClass> page) throws SQLException {
        page.setTotal(articleClassDao.getCount());
        page.setData(articleClassDao.getArticleTagByPage(page));
        return page;
    }
}
