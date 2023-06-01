package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知 对用户个人的通知
 */
public class UserInform implements Serializable
{
    private Integer informId;
    private String informTitle;
    private String informContent;
    private Date informDate;
    private Integer userId;
    private Integer informState;

    public UserInform()
    {
    }


    public UserInform(Integer informId, String informTitle, String informContent, Date informDate, Integer userId, Integer informState) {
        this.informId = informId;
        this.informTitle = informTitle;
        this.informContent = informContent;
        this.informDate = informDate;
        this.userId = userId;
        this.informState = informState;
    }

    /**
     * Get 通知编号
     */
    public Integer getInformId()
    {
        return informId;
    }
    /**
     * Set 通知编号
     */
    public void setInformId(Integer value)
    {
        this.informId = value;
    }
    /**
     * Get 通知标题
     */
    public String getInformTitle()
    {
        return informTitle;
    }
    /**
     * Set 通知标题
     */
    public void setInformTitle(String value)
    {
        this.informTitle = value;
    }
    /**
     * Get 通知内容
     */
    public String getInformContent()
    {
        return informContent;
    }
    /**
     * Set 通知内容
     */
    public void setInformContent(String value)
    {
        this.informContent = value;
    }
    /**
     * Get 通知时间
     */
    public Date getInformDate()
    {
        return informDate;
    }
    /**
     * Set 通知时间
     */
    public void setInformDate(Date value)
    {
        this.informDate = value;
    }
    /**
     * Get 通知用户
     */
    public Integer getUserId()
    {
        return userId;
    }
    /**
     * Set 通知用户
     */
    public void setUserId(Integer value)
    {
        this.userId = value;
    }
    /**
     * Get 已读状态 (已读：1/未读：2)
     */
    public Integer getInformState()
    {
        return informState;
    }
    /**
     * Set 已读状态 (已读：1/未读：2)
     */
    public void setInformState(Integer value)
    {
        this.informState = value;
    }

    @Override
    public String toString() {
        return "UserInform{" +
                "informId=" + informId +
                ", informTitle='" + informTitle + '\'' +
                ", informContent='" + informContent + '\'' +
                ", informDate=" + informDate +
                ", userId=" + userId +
                ", informState=" + informState +
                '}';
    }
}

