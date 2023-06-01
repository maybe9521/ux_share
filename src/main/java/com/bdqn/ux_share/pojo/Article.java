package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章数据表 文章数据
 */
public class Article implements Serializable
{
    private Integer articleId;
    private Integer userId;
    private String articleTitle;
    private Integer classId;
    private String articleContent;
    private String articleSummary;
    private String articleCover;
    private Integer dataLike;
    private Integer dataPv;
    private Integer articleState;
    private Date articleDate;

    private List<ArticleComment> commentList;     //文章对应评论

    public List<ArticleComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ArticleComment> commentList) {
        this.commentList = commentList;
    }


    public Article()
    {
        commentList = new ArrayList<>();
    }



    /**
     * Get 文章编号 文章编号
     */
    public Integer getArticleId()
    {
        return articleId;
    }
    /**
     * Set 文章编号 文章编号
     */
    public void setArticleId(Integer value)
    {
        this.articleId = value;
    }
    /**
     * Get 发布者编号 发布者编号
     */
    public Integer getUserId()
    {
        return userId;
    }
    /**
     * Set 发布者编号 发布者编号
     */
    public void setUserId(Integer value)
    {
        this.userId = value;
    }
    /**
     * Get 文章标题 文章标题
     */
    public String getArticleTitle()
    {
        return articleTitle;
    }
    /**
     * Set 文章标题 文章标题
     */
    public void setArticleTitle(String value)
    {
        this.articleTitle = value;
    }
    /**
     * Get 分类编号 分类编号
     */
    public Integer getClassId()
    {
        return classId;
    }
    /**
     * Set 分类编号 分类编号
     */
    public void setClassId(Integer value)
    {
        this.classId = value;
    }
    /**
     * Get 文章内容 文章内容
     */
    public String getArticleContent()
    {
        return articleContent;
    }
    /**
     * Set 文章内容 文章内容
     */
    public void setArticleContent(String value)
    {
        this.articleContent = value;
    }
    /**
     * Get 文章概要 文章概要
     */
    public String getArticleSummary()
    {
        return articleSummary;
    }
    /**
     * Set 文章概要 文章概要
     */
    public void setArticleSummary(String value)
    {
        this.articleSummary = value;
    }
    /**
     * Get 封面图片 封面图片
     */
    public String getArticleCover()
    {
        return articleCover;
    }
    /**
     * Set 封面图片 封面图片
     */
    public void setArticleCover(String value)
    {
        this.articleCover = value;
    }
    /**
     * Get 点赞数 文章对应点赞数
     */
    public Integer getDataLike()
    {
        return dataLike;
    }
    /**
     * Set 点赞数 文章对应点赞数
     */
    public void setDataLike(Integer value)
    {
        this.dataLike = value;
    }
    /**
     * Get 浏览量 文章对应浏览量
     */
    public Integer getDataPv()
    {
        return dataPv;
    }
    /**
     * Set 浏览量 文章对应浏览量
     */
    public void setDataPv(Integer value)
    {
        this.dataPv = value;
    }
    /**
     * Get 文章启用状态 用户可以删除自己的文章，数据库不删除（1:启用/2:禁用）
     */
    public Integer getArticleState()
    {
        return articleState;
    }
    /**
     * Set 文章启用状态 用户可以删除自己的文章，数据库不删除（1:启用/2:禁用）
     */
    public void setArticleState(Integer value)
    {
        this.articleState = value;
    }
    /**
     * Get 文章发布时间
     */
    public Date getArticleDate()
    {
        return articleDate;
    }
    /**
     * Set 文章发布时间
     */
    public void setArticleDate(Date value)
    {
        this.articleDate = value;
    }


    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", articleTitle='" + articleTitle + '\'' +
                ", classId=" + classId +
                ", articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleCover='" + articleCover + '\'' +
                ", dataLike=" + dataLike +
                ", dataPv=" + dataPv +
                ", articleState=" + articleState +
                ", articleDate=" + articleDate +
                '}';
    }
}

