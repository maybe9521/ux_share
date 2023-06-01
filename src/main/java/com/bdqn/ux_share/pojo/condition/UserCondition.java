package com.bdqn.ux_share.pojo.condition;

import com.bdqn.ux_share.pojo.*;

/**
 * 查询实体
 */
public class UserCondition {
    private UserMsg userMsg;    //用户实体
    private Article article;    //文章实体
    private ArticleComment articleComment;  //评论实体
    private ArticleClass articleClass;  //文章分类
    private ArticleTag articleTag;  //文章标签

    private int choose;// 分类选择

    public UserCondition() {
        userMsg = new UserMsg();
        article = new Article();
        articleComment = new ArticleComment();
        articleClass = new ArticleClass();
        articleTag = new ArticleTag();
    }

    public UserMsg getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(UserMsg userMsg) {
        this.userMsg = userMsg;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public ArticleComment getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(ArticleComment articleComment) {
        this.articleComment = articleComment;
    }

    public ArticleClass getArticleClass() {
        return articleClass;
    }

    public void setArticleClass(ArticleClass articleClass) {
        this.articleClass = articleClass;
    }

    public ArticleTag getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(ArticleTag articleTag) {
        this.articleTag = articleTag;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }
}
