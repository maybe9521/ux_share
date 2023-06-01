package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.UserDao;
import com.bdqn.ux_share.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public int addUserAtt(int uid, int auid) {
        String sql = " insert into `uxshare`.`user_att` (`user_id`, `att_id`) values (?, ?) ";
        return JdbcUtil.executeUpdate(sql,uid,auid);
    }

    @Override
    public int exitUserAtt(int uid, int auid) throws SQLException {
        String sql = "select count(1) from user_att where user_id = ? and att_id = ? ";
        ResultSet rs = JdbcUtil.executeQuery(sql, uid,auid);
        int count=0;
        if (rs!=null) {
            if (rs.next()){
                count = rs.getInt(1);
            }
        }
        return count;
    }

    @Override
    public int addCollect(int uid, int aid) throws SQLException {
        String sql = " insert into `uxshare`.`user_collect` (`user_id`, `article_id`) values (?, ?) ";
        return JdbcUtil.executeUpdate(sql,uid,aid);
    }

    @Override
    public int exitUserCollect(int uid, int aid) throws SQLException {
        String sql = "select count(1) from user_collect where user_id = ? and article_id = ? ";
        ResultSet rs = JdbcUtil.executeQuery(sql, uid,aid);
        int count=0;
        if (rs!=null) {
            if (rs.next()){
                count = rs.getInt(1);
            }
        }
        return count;
    }

    @Override
    public int addLike(int aid) throws SQLException {
        String sql = "UPDATE `uxshare`.`article` SET `data_like` = data_like+1 WHERE `article_id` = ? ";
        return JdbcUtil.executeUpdate(sql, aid);
    }
}
