package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.UserFeedbackDao;
import com.bdqn.ux_share.dao.impl.UserFeedbackDaoImpl;
import com.bdqn.ux_share.pojo.UserFeedback;
import com.bdqn.ux_share.service.UserFeedbackService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserFeedbackServiceImpl implements UserFeedbackService {
    private UserFeedbackDao ufd = new UserFeedbackDaoImpl();
    @Override
    public Page<UserFeedback> getAllFeedback(Page<UserFeedback> page) {
        try {
            page.setData(ufd.getAllFeedback(page));
            page.setTotal(ufd.getFeedbackCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public boolean updateFeedback(UserFeedback userFeedback) {
        userFeedback.setRemsgDate(new Date());
        userFeedback.setFeedbackState(2);
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(1 == ufd.updateFeedback(userFeedback)){
                flag = true;
                connection.commit();
            }
        }catch (Exception e){
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(connection,null,null);
        }
        return flag;
    }

    @Override
    public boolean insertFeedback(UserFeedback userFeedback) {
        userFeedback.setFeedbackDate(new Date());
        userFeedback.setFeedbackState(1);
        return ufd.insertFeedback(userFeedback)>0;
    }
}
