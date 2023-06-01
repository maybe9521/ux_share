package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 权限表 用户对应权限，高等级默认拥有低等级权限
 */
public class Root implements Serializable
{
    private Integer rootId;
    private Integer rootLevel;
    private String rootDesc;

    public Root()
    {
    }

    /**
     * Get 权限编号
     */
    public Integer getRootId()
    {
        return rootId;
    }
    /**
     * Set 权限编号
     */
    public void setRootId(Integer value)
    {
        this.rootId = value;
    }
    /**
     * Get 权限等级
     */
    public Integer getRootLevel()
    {
        return rootLevel;
    }
    /**
     * Set 权限等级
     */
    public void setRootLevel(Integer value)
    {
        this.rootLevel = value;
    }
    /**
     * Get 权限描述
     */
    public String getRootDesc()
    {
        return rootDesc;
    }
    /**
     * Set 权限描述
     */
    public void setRootDesc(String value)
    {
        this.rootDesc = value;
    }


    @Override
    public String toString() {
        return "Root{" +
                "rootId=" + rootId +
                ", rootLevel=" + rootLevel +
                ", rootDesc='" + rootDesc  +
                "}";
    }
}

