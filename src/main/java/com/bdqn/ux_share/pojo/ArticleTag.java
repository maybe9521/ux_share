package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 文章标签表 文章标签
 */
public class ArticleTag implements Serializable
{
    private Integer tagId;
    private String tagName;

    public ArticleTag()
    {
    }

    public ArticleTag(String tagName) {
        this.tagName = tagName;
    }

    public ArticleTag(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    /**
     * Get 文章标签 文章标签
     */
    public Integer getTagId()
    {
        return tagId;
    }
    /**
     * Set 文章标签 文章标签
     */
    public void setTagId(Integer value)
    {
        this.tagId = value;
    }
    /**
     * Get 标签名 标签名称
     */
    public String getTagName()
    {
        return tagName;
    }
    /**
     * Set 标签名 标签名称
     */
    public void setTagName(String value)
    {
        this.tagName = value;
    }

}
