package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 文章分类表 文章分类
 */
public class ArticleClass implements Serializable
{
    private Integer classId;
    private String className;

    public ArticleClass()
    {
    }

    public ArticleClass(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
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
     * Get 分类名称 分类名称
     */
    public String getClassName()
    {
        return className;
    }
    /**
     * Set 分类名称 分类名称
     */
    public void setClassName(String value)
    {
        this.className = value;
    }

}
