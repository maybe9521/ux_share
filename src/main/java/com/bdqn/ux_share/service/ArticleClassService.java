package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleClassService {
    /**
     * 查询所有文章类型
     * @return
     * @throws SQLException
     */
    public List<ArticleClass> getAll() throws SQLException;

    /**
     * 模糊查询
     * @param name
     * @return
     * @throws SQLException
     */
    public List<ArticleClass> getAllByName(String name) throws SQLException;
    /**
     * 删除标签类型
     * @param aid
     * @return
     * @throws SQLException
     */
    public boolean delArticleClass(Long aid) throws SQLException;

    /**
     * 添加标签类别
     * @param articleClass
     * @return
     * @throws SQLException
     */
    public boolean addArticleClass(ArticleClass articleClass) throws SQLException;

    /**
     * 修改标签类别
     * @param articleClass
     * @return
     * @throws SQLException
     */
    public boolean updateArticleClass(ArticleClass articleClass) throws SQLException;

    /**
     * 分页查询
     * @param page
     * @return
     * @throws SQLException
     */
    public Page<ArticleClass> getByPage(Page<ArticleClass> page) throws SQLException;
}
