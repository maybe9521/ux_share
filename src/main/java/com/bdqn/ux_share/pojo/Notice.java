package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表 系统公告
 */
public class Notice implements Serializable
{
    private Integer noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeDate;

    public Notice()
    {
    }

    public Notice(Integer noticeId, String noticeTitle, String noticeContent, Date noticeDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeDate = noticeDate;
    }

    /**
     * Get 公告编号
     */
    public Integer getNoticeId()
    {
        return noticeId;
    }
    /**
     * Set 公告编号
     */
    public void setNoticeId(Integer value)
    {
        this.noticeId = value;
    }
    /**
     * Get 公告标题
     */
    public String getNoticeTitle()
    {
        return noticeTitle;
    }
    /**
     * Set 公告标题
     */
    public void setNoticeTitle(String value)
    {
        this.noticeTitle = value;
    }
    /**
     * Get 公告内容
     */
    public String getNoticeContent()
    {
        return noticeContent;
    }
    /**
     * Set 公告内容
     */
    public void setNoticeContent(String value)
    {
        this.noticeContent = value;
    }
    /**
     * Get 发布时间
     */
    public Date getNoticeDate()
    {
        return noticeDate;
    }
    /**
     * Set 发布时间
     */
    public void setNoticeDate(Date value)
    {
        this.noticeDate = value;
    }

}

