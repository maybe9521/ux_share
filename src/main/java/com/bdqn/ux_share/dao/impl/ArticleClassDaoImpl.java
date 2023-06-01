package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.ArticleClassDao;
import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleClassDaoImpl implements ArticleClassDao {

    @Override
    public List<ArticleClass> getAll() throws SQLException {
        String sql = "SELECT class_id,class_name FROM article_class; ";
        ResultSet rs =  JdbcUtil.executeQuery(sql);
        List<ArticleClass> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new ArticleClass(rs.getInt("class_id"),rs.getString("class_name")));
            }
        }
        return list;
    }

    @Override
    public List<ArticleClass> getAllByName(String tname) throws SQLException {
        String sql = "SELECT * FROM article_class WHERE class_name LIKE concat('%',?,'%');";
        ResultSet rs =  JdbcUtil.executeQuery(sql,tname);
        List<ArticleClass> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new ArticleClass(rs.getInt("class_id"),rs.getString("class_name")));
            }
        }
        return list;
    }

    @Override
    public int delArticleClass(Long aid) throws SQLException {
        String sql = "DELETE FROM article_class WHERE class_id = ?;";
        return JdbcUtil.executeUpdate(sql,aid);
    }

    @Override
    public int addArticleClass(ArticleClass articleClass) throws SQLException {
        String sql = "INSERT INTO article_class(class_name) VALUE (?);";
        return JdbcUtil.executeUpdate(sql,articleClass.getClassName());
    }

    @Override
    public int updateArticleClass(ArticleClass articleClass) throws SQLException {
        String sql = "UPDATE article_class SET class_name = ? WHERE class_id = ?;";
        return JdbcUtil.executeUpdate(sql,articleClass.getClassName(),articleClass.getClassId());
    }

    /**
     * 获取总数
     * @return
     * @throws SQLException
     */
    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "select count(1) as count from article_class;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public List<ArticleClass> getArticleTagByPage(Page<ArticleClass> page) throws SQLException {
        String sql = "SELECT class_id,class_name FROM article_class order by class_id asc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<ArticleClass> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleClass(
                        rs.getInt("class_id"),
                        rs.getString("class_name")
                ));
            }
        }
        return list;
    }

    @Override
    public int getMohu(String tname) throws SQLException {
        return 0;
    }
}
