package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章对应评论表 文章内的评论
 */
public class ArticleComment implements Serializable
{
    private Integer commentId;
    private Integer articleId;
    private Integer userId;
    private Date commentDate;
    private String commentText;
    private String usernickname;

    private UserMsg userMsg;

    public ArticleComment()
    {
        this.userMsg = new UserMsg();
    }

    public ArticleComment(Integer commentId, Integer articleId, Integer userId, Date commentDate, String commentText) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.commentDate = commentDate;
        this.commentText = commentText;
    }

    public ArticleComment(Integer commentId,String usernickname,String commentText,Integer articleId, Date commentDate) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.commentDate = commentDate;
        this.commentText = commentText;
        this.usernickname = usernickname;
    }

    public UserMsg getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }

    /**
     * Get 评论id 评论id
     */
    public Integer getCommentId()
    {
        return commentId;
    }
    /**
     * Set 评论id 评论id
     */
    public void setCommentId(Integer value)
    {
        this.commentId = value;
    }
    /**
     * Get 文章id 对应文章id
     */
    public Integer getArticleId()
    {
        return articleId;
    }
    /**
     * Set 文章id 对应文章id
     */
    public void setArticleId(Integer value)
    {
        this.articleId = value;
    }
    /**
     * Get 用户id 发评人
     */
    public Integer getUserId()
    {
        return userId;
    }
    /**
     * Set 用户id 发评人
     */
    public void setUserId(Integer value)
    {
        this.userId = value;
    }
    /**
     * Get 评论时间 评论时间
     */
    public Date getCommentDate()
    {
        return commentDate;
    }
    /**
     * Set 评论时间 评论时间
     */
    public void setCommentDate(Date value)
    {
        this.commentDate = value;
    }
    /**
     * Get 评论内容 评论内容
     */
    public String getCommentText()
    {
        return commentText;
    }
    /**
     * Set 评论内容 评论内容
     */
    public void setCommentText(String value)
    {
        this.commentText = value;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public ArticleComment(Integer articleId, Integer userId, String commentText) {
        this.articleId = articleId;
        this.userId = userId;
        this.commentText = commentText;
    }

}

