package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleCommentService {
    public List<ArticleComment> getAll() throws SQLException;

    public boolean delete(Integer commentid) throws SQLException;

    public int getCount() throws SQLException;

    public Page<ArticleComment> getByPage(Page<ArticleComment> page)throws SQLException;


    /**
     * 新闻对应评论数
     * @param articleId
     * @return
     * @throws SQLException
     */
    int getCommentCount(int articleId) throws SQLException;

    /**
     * 获取新闻对应评论
     * @param articleId
     * @return
     * @throws SQLException
     */
    List<ArticleComment> getAllComment(int articleId) throws SQLException;

    /**
     * 新增评论
     * @param articleComment
     * @return
     */
    boolean addComment(ArticleComment articleComment);
}
