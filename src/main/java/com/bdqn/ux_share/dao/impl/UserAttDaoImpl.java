package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.UserAttDao;
import com.bdqn.ux_share.pojo.UserAtt;
import com.bdqn.ux_share.util.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserAttDaoImpl implements UserAttDao {
    @Override
    public List<UserAtt> getAll() {
        String sql = "SELECT user_id,att_id FROM user_att";
        List<UserAtt> userAttList = new ArrayList<>();
        UserAtt userAtt = null;
        ResultSet rs = JdbcUtil.executeQuery(sql);
        try{
            while (rs.next()){
                userAtt = new UserAtt();
                userAtt.setUserId(rs.getInt("user_id"));
                userAtt.setAttId(rs.getInt("att_id"));
                userAttList.add(userAtt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userAttList;
    }
}
