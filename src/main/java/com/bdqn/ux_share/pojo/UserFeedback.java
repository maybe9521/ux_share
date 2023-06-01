package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户反馈 用户反馈
 */
public class UserFeedback implements Serializable
{
    private Integer feedbackId;
    private Integer userId;
    private Date feedbackDate;
    private String feedbackContent;
    private Integer feedbackState;
    private Integer feedbackContact;
    private String feedbackRemsg;
    private Date remsgDate;
    private UserMsg userMsg;
    public UserMsg getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }

    public UserFeedback()
    {
        this.userMsg = new UserMsg();
    }

    /**
     * Get 反馈编号 反馈id
     */
    public Integer getFeedbackId()
    {
        return feedbackId;
    }
    /**
     * Set 反馈编号 反馈id
     */
    public void setFeedbackId(Integer value)
    {
        this.feedbackId = value;
    }
    /**
     * Get 用户编号 用户编号
     */
    public Integer getUserId()
    {
        return userId;
    }
    /**
     * Set 用户编号 用户编号
     */
    public void setUserId(Integer value)
    {
        this.userId = value;
    }
    /**
     * Get 反馈时间 提交反馈的时间
     */
    public Date getFeedbackDate()
    {
        return feedbackDate;
    }
    /**
     * Set 反馈时间 提交反馈的时间
     */
    public void setFeedbackDate(Date value)
    {
        this.feedbackDate = value;
    }
    /**
     * Get 反馈内容 用户反馈的内容
     */
    public String getFeedbackContent()
    {
        return feedbackContent;
    }
    /**
     * Set 反馈内容 用户反馈的内容
     */
    public void setFeedbackContent(String value)
    {
        this.feedbackContent = value;
    }
    /**
     * Get 处理状态 是否已处理1/2
     */
    public Integer getFeedbackState()
    {
        return feedbackState;
    }
    /**
     * Set 处理状态 是否已处理1/2
     */
    public void setFeedbackState(Integer value)
    {
        this.feedbackState = value;
    }
    /**
     * Get 联系方式 回馈信息返回的地方
     */
    public Integer getFeedbackContact()
    {
        return feedbackContact;
    }
    /**
     * Set 联系方式 回馈信息返回的地方
     */
    public void setFeedbackContact(Integer value)
    {
        this.feedbackContact = value;
    }
    /**
     * Get 回馈内容 回馈信息
     */
    public String getFeedbackRemsg()
    {
        return feedbackRemsg;
    }
    /**
     * Set 回馈内容 回馈信息
     */
    public void setFeedbackRemsg(String value)
    {
        this.feedbackRemsg = value;
    }
    /**
     * Get 回馈时间 回馈时间
     */
    public Date getRemsgDate()
    {
        return remsgDate;
    }
    /**
     * Set 回馈时间 回馈时间
     */
    public void setRemsgDate(Date value)
    {
        this.remsgDate = value;
    }

}

