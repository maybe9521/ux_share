package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.ArticleTagDao;
import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleTagDaoImpl implements ArticleTagDao {

    @Override
    public List<ArticleTag> getAll() throws SQLException {
        String sql = "SELECT tag_id,tag_name FROM article_tag; ";
        ResultSet rs =  JdbcUtil.executeQuery(sql);
        List<ArticleTag> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new ArticleTag(rs.getInt("tag_id"),rs.getString("tag_name")));
            }
        }
        return list;
    }

    @Override
    public int delArticleTag(Long aid) throws SQLException {
        String sql = "DELETE FROM article_tag WHERE tag_id = ?;";
        return JdbcUtil.executeUpdate(sql,aid);
    }

    @Override
    public int addArticleTag(ArticleTag article) throws SQLException {
        String sql = "INSERT INTO article_tag(tag_name) VALUE (?);";
        return JdbcUtil.executeUpdate(sql,article.getTagName());
    }

    @Override
    public int updateArticleTag(ArticleTag article) throws SQLException {
        String sql = "UPDATE article_tag SET tag_name = ? WHERE tag_id = ?;";
        return JdbcUtil.executeUpdate(sql,article.getTagName(),article.getTagId());
    }

    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "select count(1) as count from article_tag;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public int getCount(String tname) throws SQLException {
        int count = 0;
//        List<Object> list = new ArrayList<>();
//        String sql = "SELECT COUNT(1) FROM article_tag WHERE tag_name LIKE '%?%'";
//        System.out.println(sql+","+tname);
//        ResultSet rs = JdbcUtil.executeQuery(sql,list.toArray());
//        if(rs != null){
//            while(rs.next()){
//                count = rs.getInt("count");
//            }
//        }
        return 3;
    }

    @Override
    public List<ArticleTag> getArticleTagByPage(Page<ArticleTag> page) throws SQLException {
        String sql = "SELECT tag_id,tag_name FROM article_tag order by tag_id asc limit ?,?;;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<ArticleTag> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleTag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                ));
            }
        }
        return list;
    }

//    @Override
//    public int getCount(String tname) throws SQLException {
//        String count = null;
//        List<Object> list = new ArrayList<>();
//        String sql = "select count(1) as count from article_tag";
//        if(tname != null){
//            sql += " where ntid = ?;";
//            list.add(ntid);
//        }
//        System.out.println(sql+","+ntid);
//
//        ResultSet rs = JdbcUtil.executeQuery(sql,list.toArray());
//        if(rs != null){
//            while(rs.next()){
//                count = rs.getInt("count");
//            }
//        }
//        return count;
//    }

    @Override
    public List<ArticleTag> getArticleTagByPageByTj(Page<ArticleTag> page, String fname) throws SQLException {
        String sql = "SELECT tag_id,tag_name FROM article_tag WHERE tag_name LIKE CONCAT('%',?,'%') limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,fname,(page.getPage()-1)*page.getSize(),page.getSize());
        List<ArticleTag> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleTag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                ));
            }
        }
        return list;
    }

    @Override
    public List<ArticleTag> getArticleTag(int aid) throws SQLException {
        String sql = " SELECT article_tag.tag_name,article_tag.tag_id FROM article ,article_tag ,article_rele_tag WHERE article_rele_tag.article_id = article.article_id AND article_rele_tag.tag_id = article_tag.tag_id AND article.article_id = ? ";
        ResultSet rs =  JdbcUtil.executeQuery(sql,aid);
        List<ArticleTag> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new ArticleTag(rs.getInt("tag_id"),rs.getString("tag_name")));
            }
        }
        return list;
    }

    /**
     * 查询标签分页
     * @return
     * @throws SQLException
     */
//    @Override
//    public int getCount(Long ntid) throws SQLException {
//        int count = 0;
//        List<Object> list = new ArrayList<>();
//        String sql = "select count(1) as count from article_tag;";
//        if(ntid != null && ntid != 0){
//            sql += " where tag_id = ?;";
//            list.add(ntid);
//        }
//        System.out.println(sql+","+ntid);
//
//        ResultSet rs = JdbcUtil.executeQuery(sql,list.toArray());
//        if(rs != null){
//            while(rs.next()){
//                count = rs.getInt("count");
//            }
//        }
//        return count;
//    }

//    @Override
//    public List<ArticleTag> getArticleTagByPage(Page<ArticleTag> page, Long ntid) throws SQLException {
//        String sql = "SELECT tag_id,tag_name FROM article_tag;";
//        List<Object> params = new ArrayList<>();
//        if(ntid != null && ntid != 0){
//            sql += " where tag_id = ? ";
//            params.add(ntid);
//        }
//        sql += "order by tag_id asc limit ?,?;";
//        params.add((page.getPage()-1)*page.getSize());
//        params.add(page.getSize());
//        ResultSet rs = JdbcUtil.executeQuery(sql,params.toArray());
//        System.out.println(sql+","+ntid);
//        List<ArticleTag> list = new ArrayList<>();
//        if(rs != null){
//            while(rs.next()){
//                ArticleTag articleTag = new ArticleTag();
//                articleTag.setTagId(rs.getInt("tag_id"));
//                articleTag.setTagName(rs.getString("tag_name"));
//                list.add(articleTag);
//            }
//        }
//        return list;
//    }

}
