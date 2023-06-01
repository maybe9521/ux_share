package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleTagDao {

    /**
     *  查询所有分类
     * @return
     * @throws SQLException
     */
    public List<ArticleTag> getAll() throws SQLException;
    /**
     * 删除标签
     * @return
     */
    public int delArticleTag(Long aid) throws SQLException;

    /**
     * 添加标签
     * @param article
     * @return
     * @throws SQLException
     */
    public int addArticleTag(ArticleTag article) throws SQLException;

    /**
     * 修改标签
     * @param article
     * @return
     * @throws SQLException
     */
    public int updateArticleTag(ArticleTag article) throws  SQLException;

    /**
     * 标签总条数
     * @return
     */
    public int getCount() throws  SQLException;
    public int getCount(String tname) throws  SQLException;

    public List<ArticleTag> getArticleTagByPage(Page<ArticleTag> page) throws  SQLException;

//    public int getCount(String tname) throws  SQLException;
    public List<ArticleTag> getArticleTagByPageByTj(Page<ArticleTag> page,String fname) throws  SQLException;


    /**
     *  查询所有标签
     * @return
     * @throws SQLException
     */
    public List<ArticleTag> getArticleTag(int aid) throws SQLException;
}
