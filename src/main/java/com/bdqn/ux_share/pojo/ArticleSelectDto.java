package com.bdqn.ux_share.pojo;

import java.util.Date;

/**
 * 文章条件实体
 */
public class ArticleSelectDto {
    private String username;// 用户账户

    private  String startDate;// 开始日期

    private String endDate;// 结束日期

    public ArticleSelectDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ArticleSelectDto{" +
                "username='" + username + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
