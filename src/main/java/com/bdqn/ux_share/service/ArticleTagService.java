package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleTagService {
    /**
     * 查询所有文章类型
     * @return
     * @throws SQLException
     */
    public List<ArticleTag> getAll() throws SQLException;

    List<ArticleTag> getArticleTag(int aid);

    /**
     * 删除标签类型
     * @param aid
     * @return
     * @throws SQLException
     */
    public boolean delArticleTag(Long aid) throws SQLException;

    /**
     * 添加标签类别
     * @param articleTag
     * @return
     * @throws SQLException
     */
    public boolean addArticleTag(ArticleTag articleTag) throws SQLException;

    /**
     * 修改标签类别
     * @param articleTag
     * @return
     * @throws SQLException
     */
    public boolean updateArticleTag(ArticleTag articleTag) throws SQLException;

    /**
     * 分页查询
     * @param page
     * @return
     * @throws SQLException
     */
    public Page<ArticleTag> getByPage(Page<ArticleTag> page) throws SQLException;
    public Page<ArticleTag> getByPageByFname(Page<ArticleTag> page,String fname) throws SQLException;
}
