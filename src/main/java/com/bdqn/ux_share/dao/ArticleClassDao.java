package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleClassDao {
    /**
     *  查询所有分类
     * @return
     * @throws SQLException
     */
    public List<ArticleClass> getAll() throws SQLException;
    /**
     * 模糊查询
     */
    public List<ArticleClass> getAllByName(String tname) throws SQLException;

    /**
     * 删除文章类型
     * @return
     */
    public int delArticleClass(Long aid) throws SQLException;

    /**
     * 添加文章类型
     * @param articleClass
     * @return
     * @throws SQLException
     */
    public int addArticleClass(ArticleClass articleClass) throws SQLException;

    /**
     * 修改文章类型
     * @param articleClass
     * @return
     * @throws SQLException
     */
    public int updateArticleClass(ArticleClass articleClass) throws  SQLException;

    /**
     * 类型总条数
     * @return
     */
    public int getCount() throws  SQLException;

    /**
     * 分页查询
     * @param page
     * @return
     * @throws SQLException
     */
    public List<ArticleClass> getArticleTagByPage(Page<ArticleClass> page) throws  SQLException;
    public int getMohu(String tname) throws SQLException;
}
