package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleCommentDao {

    /**
     * 查询全部评论
     * @return
     * @throws SQLException
     */
    public List<ArticleComment> getAll() throws SQLException;

    /**
     * 删除新闻，根据id
     * @param commentId
     * @return
     * @throws SQLException
     */
    public int delete(Integer commentId) throws SQLException;

    /**
     * 统计评论数量
     * @return
     * @throws SQLException
     */
    public int getCount() throws SQLException;

    public List<ArticleComment> getArticleCommentByPage(Page<ArticleComment> page) throws SQLException;

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
    int addComment(ArticleComment articleComment);



}
