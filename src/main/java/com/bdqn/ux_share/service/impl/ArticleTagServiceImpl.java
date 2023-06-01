package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.ArticleTagDao;
import com.bdqn.ux_share.dao.impl.ArticleTagDaoImpl;
import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.service.ArticleTagService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArticleTagServiceImpl implements ArticleTagService {
    private ArticleTagDao tagDao = new ArticleTagDaoImpl();
    /**
     * 获取全部
     * @return
     * @throws SQLException
     */
    @Override
    public List<ArticleTag> getAll() throws SQLException {
        return tagDao.getAll();
    }

    /**
     * 获取标签名
     * @param aid
     * @return
     */
    @Override
    public List<ArticleTag> getArticleTag(int aid) {
        try {
            return tagDao.getArticleTag(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delArticleTag(Long aid) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(tagDao.delArticleTag(aid) == 1){
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
    public boolean addArticleTag(ArticleTag articleTag) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(1 == tagDao.addArticleTag(articleTag)){
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
     * 分页
     * @param articleTag
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateArticleTag(ArticleTag articleTag) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加修改时间
            if(tagDao.updateArticleTag(articleTag) == 1){
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
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public Page<ArticleTag> getByPage(Page<ArticleTag> page) throws SQLException {
        page.setTotal(tagDao.getCount());
        page.setData(tagDao.getArticleTagByPage(page));
        return page;
    }

    @Override
    public Page<ArticleTag> getByPageByFname(Page<ArticleTag> page, String fname) throws SQLException {
        try{
            page.setTotal(tagDao.getCount());
            page.setData(tagDao.getArticleTagByPageByTj(page,fname));
            return page;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
