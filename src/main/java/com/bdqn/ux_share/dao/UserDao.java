package com.bdqn.ux_share.dao;

import java.sql.SQLException;

public interface UserDao {
    //添加用户关注
    int addUserAtt(int uid,int auid);
    //判断用户是否关注
    int exitUserAtt(int uid,int auid) throws SQLException;
    //添加用户收藏
    int addCollect(int uid,int aid) throws SQLException;
    //判断用户是否收藏
    int exitUserCollect(int aid,int uid) throws SQLException;
    //添加点赞
    int addLike(int aid) throws SQLException;
}
