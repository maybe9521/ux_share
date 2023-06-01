package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.ArticleCommentDao;
import com.bdqn.ux_share.dao.impl.ArticleCommentDaoImpl;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.service.ArticleCommentService;
import com.bdqn.ux_share.util.ConsoleColors;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ArticleCommentServiceImpl implements ArticleCommentService {
    ArticleCommentDao articleCommentDao = new ArticleCommentDaoImpl();
    @Override
    public List<ArticleComment> getAll() throws SQLException {
        return articleCommentDao.getAll();
    }

    @Override
    public boolean delete(Integer commentid) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(1 == articleCommentDao.delete(commentid)){
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
    public int getCount() throws SQLException {
        return articleCommentDao.getCount();
    }

    @Override
    public Page<ArticleComment> getByPage(Page<ArticleComment> page) throws SQLException {
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+page);
        page.setTotal(articleCommentDao.getCount());
        page.setData(articleCommentDao.getArticleCommentByPage(page));
        System.out.println(ConsoleColors.BLUE+page.getData());
        return page;
    }

    @Override
    public int getCommentCount(int articleId) throws SQLException {
        return articleCommentDao.getCommentCount(articleId);
    }

    @Override
    public List<ArticleComment> getAllComment(int articleId) throws SQLException {
        return articleCommentDao.getAllComment(articleId);
    }

    @Override
    public boolean addComment(ArticleComment articleComment) {
        articleComment.setCommentDate(new Date());
        return articleCommentDao.addComment(articleComment)>0;
    }
}
