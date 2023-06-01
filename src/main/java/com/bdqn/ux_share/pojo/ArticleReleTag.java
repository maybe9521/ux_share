package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 文章关联标签表 文章对应标签
 */
public class ArticleReleTag implements Serializable
{
    private Integer articleId;
    private Integer tagId;

    public ArticleReleTag()
    {
    }

    public ArticleReleTag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    /**
     * Get 文章编号
     */
    public Integer getArticleId()
    {
        return articleId;
    }
    /**
     * Set 文章编号
     */
    public void setArticleId(int value)
    {
        this.articleId = value;
    }
    /**
     * Get 标签编号
     */
    public Integer getTagId()
    {
        return tagId;
    }
    /**
     * Set 标签编号
     */
    public void setTagId(Integer value)
    {
        this.tagId = value;
    }


    @Override
    public String toString() {
        return "ArticleReleTag{" +
                "articleId=" + articleId +
                ", tagId=" + tagId +
                '}';
    }
}

