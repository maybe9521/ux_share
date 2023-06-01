package com.bdqn.ux_share.pojo;

import com.bdqn.ux_share.util.ConsoleColors;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *  文章条件实体
 */
public class ArticleDto extends Article{

    private String userName;// 用户账户

    private Timestamp createDate;// 创造日期

    public int[] tagIds;// 文章关联标签数组

    private int choose;// 分类选择


    private UserMsg userMsg;// 条件用户信息

    private int commentLength;// 评论数量


    public ArticleDto(int articleId, int userId, String articleTitle, int classId, String articleContent, String articleSummary, String articleCover,
                      int dataLike, int dataPv, int articleState, Timestamp articleDate,UserMsg userMsg) {
        this.setArticleId(articleId);
        this.setUserId(userId);
        this.setArticleTitle(articleTitle);
        this.setClassId(classId);
        this.setArticleContent(articleContent);
        this.setArticleSummary(articleSummary);
        this.setArticleCover(articleCover);
        this.setDataLike(dataLike);
        this.setDataPv(dataPv);
        this.setArticleState(articleState);
        this.setArticleDate(articleDate);
        this.userMsg =userMsg;
    }


    public int getCommentLength() {
        return commentLength;
    }

    public void setCommentLength(int commentLength) {
        this.commentLength = commentLength;
    }

    public UserMsg getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }

    public ArticleDto() {
    }


    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public int[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(int[] tagIds) {
        this.tagIds =tagIds;//转换为int数组
    }

    @Override
    public Integer getArticleId() {
        return super.getArticleId();
    }

    @Override
    public void setArticleId(Integer value) {
        super.setArticleId(value);
    }

    @Override
    public Integer getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(Integer value) {
        super.setUserId(value);
    }

    @Override
    public String getArticleTitle() {
        return super.getArticleTitle();
    }

    @Override
    public void setArticleTitle(String value) {
        super.setArticleTitle(value);
    }

    @Override
    public Integer getClassId() {
        return super.getClassId();
    }

    @Override
    public void setClassId(Integer value) {
        super.setClassId(value);
    }

    @Override
    public String getArticleContent() {
        return super.getArticleContent();
    }

    @Override
    public void setArticleContent(String value) {
        super.setArticleContent(value);
    }

    @Override
    public String getArticleSummary() {
        return super.getArticleSummary();
    }

    @Override
    public void setArticleSummary(String value) {
        super.setArticleSummary(value);
    }

    @Override
    public String getArticleCover() {
        return super.getArticleCover();
    }

    @Override
    public void setArticleCover(String value) {
        System.out.println(ConsoleColors.BLUE_BOLD+value);
        super.setArticleCover(value);
    }

    @Override
    public Integer getDataLike() {
        return super.getDataLike();
    }

    @Override
    public void setDataLike(Integer value) {
        super.setDataLike(value);
    }

    @Override
    public Integer getDataPv() {
        return super.getDataPv();
    }

    @Override
    public void setDataPv(Integer value) {
        super.setDataPv(value);
    }

    @Override
    public Integer getArticleState() {
        return super.getArticleState();
    }

    @Override
    public void setArticleState(Integer value) {
        super.setArticleState(value);
    }

    @Override
    public Date getArticleDate() {
        return super.getArticleDate();
    }

    @Override
    public void setArticleDate(Date value) {
        super.setArticleDate(value);
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return super.toString()+"username:"+this.userName+this.createDate;
    }
}
