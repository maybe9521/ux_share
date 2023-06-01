package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.dao.UserMsgDao;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMsgDaoImpl implements UserMsgDao {


    @Override
    public UserMsg getUserBynameAndPwd(String name, String pwd) throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS name,user_role AS role,user_state AS state FROM user_msg WHERE user_uname = ? AND user_pwd = ?";
        ResultSet rs = null;
        UserMsg userMsg = null;
        try{
            rs = JdbcUtil.executeQuery(sql,name,pwd);
            if(rs != null){
                while(rs.next()){
                    userMsg = new UserMsg(rs.getInt("id"),rs.getString("name"),rs.getInt("role"),rs.getInt("state"));
                    break;
                }
            }
        }catch (Exception e){
            throw new SQLException("用户登录连接异常！");
        }
        return userMsg;
    }

    @Override
    public List<UserMsg> getAll() throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS nickname,user_uname AS uname,user_pwd\n" +
                "AS pwd,user_role AS role,user_state AS state,user_createDate AS createDate,user_icon AS icon,user_phone AS phone,user_point AS point,user_level AS `level`,user_root_id AS rootid,user_ex AS ex FROM user_msg;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<UserMsg> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new UserMsg(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("uname"),
                        rs.getString("pwd"),
                        rs.getInt("role"),
                        rs.getInt("state"),
                        rs.getDate("createDate"),
                        rs.getString("icon"),
                        rs.getString("phone"),
                        rs.getInt("point"),
                        rs.getInt("level"),
                        rs.getInt("rootid"),
                        rs.getInt("ex")
                ));
            }
        }
        return list;
    }

    @Override
    public UserMsg getUser(String name, String pwd) throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS name  ,user_uname,user_pwd FROM user_msg WHERE user_uname = ? AND user_pwd = ?";
        ResultSet rs = null;
        UserMsg userMsg = null;
        try{
            rs = JdbcUtil.executeQuery(sql,name,pwd);
            if(rs != null){
                while(rs.next()){
                    userMsg = new UserMsg(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("user_uname"),
                            rs.getString("user_pwd"));
                    break;
                }
            }
        }catch (Exception e){
            throw new SQLException("用户登录连接异常！");
        }
        return userMsg;
    }

    @Override
    public int UserRegister(UserMsg userMsg) throws SQLException {
        String sql = "INSERT INTO `user_msg`(user_nickname,user_uname,user_pwd,user_role,user_state,user_createDate,user_icon,user_phone,user_point,user_level,user_root_id,user_ex)\n" +
                "VALUES('user20', ?, ?, 1, 1, ?, '/upload/icon/admin/01.jpg', ?, 0, 0, 0, 10)";
        return JdbcUtil.executeUpdate(sql,userMsg.getUserUname(),userMsg.getUserPwd(),userMsg.getUserCreateDate(),userMsg.getUserPhone());
    }

    @Override
    public int updateState(Integer userid,Integer state) throws SQLException {
        String sql = "UPDATE user_msg SET user_state = ? WHERE user_id = ?;";
        return JdbcUtil.executeUpdate(sql,state,userid);
    }

    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(1) AS count FROM user_msg;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public int getCareCount(int uid) throws SQLException {
        String sql = "SELECT count(1) FROM user_att WHERE user_id=?";
        ResultSet rs = JdbcUtil.executeQuery(sql,uid);
        int row =0;
        while (rs.next()){
            row = rs.getInt(1);
        }
        return row;
    }

    @Override
    public List<UserMsg> getUserByPage(Page<UserMsg> page) throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS nickname,user_uname AS uname,user_pwd\n" +
                "AS pwd,user_role AS role,user_state AS state,user_createDate AS createDate,user_icon AS icon,user_phone AS phone,user_point AS point,user_level AS `level`,user_root_id AS rootid,user_ex AS ex FROM user_msg limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<UserMsg> list = new ArrayList<>();
        if(rs != null){
            while (rs.next()){
                list.add(new UserMsg(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("uname"),
                        rs.getString("pwd"),
                        rs.getInt("role"),
                        rs.getInt("state"),
                        rs.getDate("createDate"),
                        rs.getString("icon"),
                        rs.getString("phone"),
                        rs.getInt("point"),
                        rs.getInt("level"),
                        rs.getInt("rootid"),
                        rs.getInt("ex")
                ));
            }
        }
        return list;
    }

    @Override
    public UserMsg getLoginUserInfo(int uid) throws SQLException {
        String sql = "SELECT user_id,user_nickname,user_uname,user_pwd,user_role,user_state,user_createDate,user_icon,user_phone," +
                "user_point,user_level,user_root_id,user_ex FROM user_msg WHERE user_id=?";
        ResultSet rs = JdbcUtil.executeQuery(sql,uid);
        UserMsg userMsg = null;
        while (rs.next()){
            userMsg = new UserMsg(
                    rs.getInt("user_id"),
                    rs.getString("user_nickname"),
                    rs.getString("user_uname"),
                    rs.getString("user_pwd"),
                    rs.getInt("user_role"),
                    rs.getInt("user_state"),
                    rs.getTimestamp("user_createDate"),
                    rs.getString("user_icon"),
                    rs.getString("user_phone"),
                    rs.getInt("user_point"),
                    rs.getInt("user_level"),
                    rs.getInt("user_root_id"),
                    rs.getInt("user_ex"));
        }
        return userMsg;
    }


    @Override
    public int getUserCollectSum(int uid) throws SQLException {
        String sql = "SELECT DISTINCT count(1) as 'countNum' FROM article AS a LEFT JOIN user_collect AS att ON a.article_id=att.article_id " +
                "WHERE att.user_id=?";
        ResultSet rs = JdbcUtil.executeQuery(sql,uid);
        int row = 0;
        if(rs.next()){
            row = rs.getInt("countNum");
        }
        return row;
    }
    @Override
    public List<UserMsg> searchLikeUser(String like,Page<UserMsg> page) throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS nickname,user_uname AS uname,user_pwd\n" +
                "AS pwd,user_role AS role,user_state AS state,user_createDate AS createDate,user_icon AS icon,user_phone AS phone,user_point AS point,user_level AS `level`,user_root_id AS rootid,user_ex AS ex FROM user_msg WHERE user_nickname LIKE CONCAT('%',?,'%') LIMIT ?,?";
        ResultSet rs = JdbcUtil.executeQuery(sql, like, (page.getPage() - 1) * page.getSize(), page.getSize());
        List<UserMsg> list = new ArrayList<>();
        if (rs != null) {
            while (rs.next()) {
                list.add(new UserMsg(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("uname"),
                        rs.getString("pwd"),
                        rs.getInt("role"),
                        rs.getInt("state"),
                        rs.getDate("createDate"),
                        rs.getString("icon"),
                        rs.getString("phone"),
                        rs.getInt("point"),
                        rs.getInt("level"),
                        rs.getInt("rootid"),
                        rs.getInt("ex")
                ));
            }
        }
        return list;
    }
}
