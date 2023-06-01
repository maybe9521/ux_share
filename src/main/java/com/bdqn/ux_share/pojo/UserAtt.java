package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 用户关注 用户与用户的关注关系
 */
public class UserAtt implements Serializable
{
    private Integer userId;
    private Integer attId;

    public UserAtt()
    {
    }

    /**
     * Get 用户编号
     */
    public Integer getUserId()
    {
        return userId;
    }
    /**
     * Set 用户编号
     */
    public void setUserId(Integer value)
    {
        this.userId = value;
    }
    /**
     * Get 用户关注编号
     */
    public Integer getAttId()
    {
        return attId;
    }
    /**
     * Set 用户关注编号
     */
    public void setAttId(Integer value)
    {
        this.attId = value;
    }

    @Override
    public String toString() {
        return "UserAtt{" +
                "userId=" + userId +
                ", attId=" + attId +
                '}';
    }
}

