package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.UserInfromDao;
import com.bdqn.ux_share.pojo.UserInform;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfromDaoImpl implements UserInfromDao {

    @Override
    public List<UserInform> getAll() throws SQLException {
        String sql = "select inform_id,inform_title,inform_content,inform_date,user_id,inform_state from user_inform;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<UserInform> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new UserInform(rs.getInt("inform_id"),
                        rs.getString("inform_title"),
                        rs.getString("inform_content"),
                        rs.getDate("inform_date"),
                        rs.getInt("user_id"),
                        rs.getInt("inform_state")));
            }
        }
        return list;
    }

    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "select count(1) as count from user_inform;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public UserInform getinformId(Integer informId) throws SQLException {
        return null;
    }

    @Override
    public List<UserInform> getUIPage(Page<UserInform> page) throws SQLException {
        String sql = "select inform_id,inform_title,inform_content,inform_date,user_id,inform_state from user_inform order by inform_date desc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<UserInform> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new UserInform(rs.getInt("inform_id"),
                        rs.getString("inform_title"),
                        rs.getString("inform_content"),
                        rs.getDate("inform_date"),
                        rs.getInt("user_id"),
                        rs.getInt("inform_state")));
            }
        }
        return list;
    }

    @Override
    public int delete(Integer informId) throws SQLException {
        String sql = "delete from user_inform where inform_id = ?;";
        System.out.println(informId);
        return JdbcUtil.executeUpdate(sql,informId);
    }


    @Override
    public int update(UserInform userInform) throws SQLException {
        String sql = "update user_inform set inform_id = ?,inform_title=?,inform_content=?,inform_date=?,user_id=?,inform_state=? where inform_id=?";
        return JdbcUtil.executeUpdate(sql,userInform.getInformId(),userInform.getInformTitle(),userInform.getInformContent(),
                userInform.getInformDate(),userInform.getUserId());
    }

    @Override
    public List<UserInform> getTopUI(Integer informId, int limit) throws SQLException {
        String sql = "select inform_id,inform_title,inform_content,inform_date,user_id,inform_state from user_inform where inform_id = ? order by inform_date desc limit ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,informId,limit);
        List<UserInform> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                UserInform userInform = new UserInform();
                userInform.setInformId(rs.getInt("inform_id"));
                userInform.setInformTitle(rs.getString("inform_title"));
                userInform.setInformContent(rs.getString("inform_content"));
                userInform.setInformDate(rs.getDate("inform_date"));
                userInform.setUserId(rs.getInt("user_id"));
                userInform.setInformState(rs.getInt("inform_state"));
                list.add(userInform);
            }
        }
        return list;
    }

    @Override
    public int getCount(Integer informId) throws SQLException {
        int count = 0;
        List<Object> list = new ArrayList<>();
        String sql = "select count(1) as count from user_inform ";
        if(informId != null && informId != 0){
            sql += " where inform_id = ?;";
            list.add(informId);
        }
        System.out.println(sql+","+informId);

        ResultSet rs = JdbcUtil.executeQuery(sql,list.toArray());
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public UserInform getArticleCarouselbyId(Integer informId) throws SQLException {
        String sql = "select inform_id,inform_title,inform_content,inform_date,user_id,inform_state from user_inform where inform_id = ?";
        ResultSet rs = JdbcUtil.executeQuery(sql,informId);
        UserInform userInform = new UserInform();
        if(rs != null){
            while(rs.next()){
                userInform.setInformId(rs.getInt("inform_id"));
                userInform.setInformTitle(rs.getString("inform_title"));
                userInform.setInformContent(rs.getString("inform_content"));
                userInform.setInformDate(rs.getDate("inform_date"));
                userInform.setUserId(rs.getInt("user_id"));
                userInform.setInformState(rs.getInt("inform_state"));
            }
        }
        return userInform;
    }

    @Override
    public List<UserInform> getUIByPage(Page<UserInform> page, Integer informId) throws SQLException {
        String sql = "select inform_id,inform_title,inform_content,inform_date,user_id,inform_state from user_inform ";// ";
        List<Object> params = new ArrayList<>();
        if(informId != null && informId != 0){
            sql += " where inform_id = ? ";
            params.add(informId);
        }
        sql += "order by inform_date desc limit ?,?;";
        params.add((page.getPage()-1)*page.getSize());
        params.add(page.getSize());
        ResultSet rs = JdbcUtil.executeQuery(sql,params.toArray());
        System.out.println(sql+","+informId);
        List<UserInform> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                UserInform userInform = new UserInform();
                userInform.setInformId(rs.getInt("inform_id"));
                userInform.setInformTitle(rs.getString("inform_title"));
                userInform.setInformContent(rs.getString("inform_content"));
                userInform.setInformDate(rs.getDate("inform_date"));
                userInform.setUserId(rs.getInt("user_id"));
                userInform.setInformState(rs.getInt("inform_state"));
                list.add(userInform);
            }
        }
        return list;
    }
    @Override
    public int add(UserInform userInform) throws SQLException {
        String sql = " INSERT INTO `user_inform`(`inform_title`, `inform_content`, `inform_date`, `user_id`, `inform_state`) values(?,?,?,?,?) ";
        return JdbcUtil.executeUpdate(sql,userInform.getInformTitle(),userInform.getInformContent(),
                userInform.getInformDate(),userInform.getUserId(),userInform.getInformState());
    }

    @Override
    public List<UserInform> getUserInform(int uid) throws SQLException {
        String sql = " select `inform_id`,`inform_title`,`inform_content`,`inform_date`,`user_id`,`inform_state` from `uxshare`.`user_inform` where user_id = ? ";
        List<UserInform> list = null;
        UserInform userInform = null;
        ResultSet rs = JdbcUtil.executeQuery(sql, uid);
        if (rs!=null){
            list = new ArrayList<>();
            while (rs.next()){
                userInform = new UserInform();
                userInform.setInformId(rs.getInt("inform_id"));
                userInform.setInformTitle(rs.getString("inform_title"));
                userInform.setInformContent(rs.getString("inform_content"));
                userInform.setInformDate(rs.getTimestamp("inform_date"));
                userInform.setUserId(rs.getInt("user_id"));
                userInform.setInformState(rs.getInt("inform_state"));
                list.add(userInform);
            }
        }
        return list;
    }
}
