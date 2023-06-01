package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.ArticleCommentDao;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleCommentDaoImpl implements ArticleCommentDao {


    @Override
    public List<ArticleComment> getAll() throws SQLException {
        String sql = "SELECT comment_id AS commentid,user_nickname AS usernickname,comment_text AS commenttext,article_id AS articleid,comment_date AS commentdate\n" +
                "FROM comment_view;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<ArticleComment> list = new ArrayList<>();
        if (rs != null) {
            while (rs.next()) {
                list.add(new ArticleComment(
                        rs.getInt("commentid"),
                        rs.getString("usernickname"),
                        rs.getString("commenttext"),
                        rs.getInt("articleid"),
                        rs.getTimestamp("commentdate")));
            }
        }
        return list;
    }

    @Override
    public int delete(Integer commentId) throws SQLException {
        String sql = "DELETE FROM article_comment WHERE comment_id = ?";
        return JdbcUtil.executeUpdate(sql, commentId);
    }

    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(1) AS count FROM article_comment";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public List<ArticleComment> getArticleCommentByPage(Page<ArticleComment> page) throws SQLException {
        String sql = "SELECT comment_id AS commentid,user_nickname AS usernickname,comment_text AS commenttext,article_id AS articleid,comment_date AS commentdate\n" +
                "from comment_view order by comment_date desc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql, (page.getPage() - 1) * page.getSize(), page.getSize());
        List<ArticleComment> list = new ArrayList<>();
        if (rs != null) {
            while (rs.next()) {
                list.add(new ArticleComment(
                        rs.getInt("commentid"),
                        rs.getString("usernickname"),
                        rs.getString("commenttext"),
                        rs.getInt("articleid"),
                        rs.getTimestamp("commentdate")));
            }
        }
        return list;
    }

    @Override
    public int getCommentCount(int articleId) throws SQLException {
        String sql = " SELECT COUNT(article_id) FROM article_comment WHERE  article_id = ? ";
        int count = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, articleId);
        if (rs != null) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }


    @Override
    public List<ArticleComment> getAllComment(int articleId) throws SQLException {
        String sql = "SELECT comment_id,article_id,article_comment.user_id,comment_date,comment_text,user_icon,user_nickname FROM uxshare.`article_comment`,`user_msg` where article_comment.user_id = user_msg.user_id  and article_id = ? ";
        List<ArticleComment> list = new ArrayList<ArticleComment>();
        ArticleComment articleComment = null;
        ResultSet rs = JdbcUtil.executeQuery(sql, articleId);
        while (rs.next()) {
            articleComment = new ArticleComment();
            articleComment.setCommentId(rs.getInt("comment_id"));
            articleComment.setArticleId(rs.getInt("article_id"));
            articleComment.setUserId(rs.getInt("user_id"));
            articleComment.setCommentDate(rs.getTimestamp("comment_date"));
            articleComment.setCommentText(rs.getString("comment_text"));
            articleComment.getUserMsg().setUserIcon(rs.getString("user_icon"));
            articleComment.getUserMsg().setUserNickname(rs.getString("user_nickname"));
            list.add(articleComment);
        }
        return list;
    }

    @Override
    public int addComment(ArticleComment articleComment) {
        String sql = " insert into `uxshare`.`article_comment` (`article_id`,`user_id`,`comment_date`,`comment_text`)values (?,?,?,?) ";
        return JdbcUtil.executeUpdate(sql, articleComment.getArticleId(), articleComment.getUserId(), articleComment.getCommentDate(), articleComment.getCommentText());
    }
}
