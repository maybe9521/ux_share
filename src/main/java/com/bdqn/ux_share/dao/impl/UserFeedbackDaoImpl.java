package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.UserFeedbackDao;
import com.bdqn.ux_share.pojo.UserFeedback;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFeedbackDaoImpl implements UserFeedbackDao {
    @Override
    public List<UserFeedback> getAllFeedback(Page<UserFeedback> page) throws SQLException {
        StringBuilder sb = new StringBuilder();
        List<UserFeedback> list = null;
        UserFeedback userFeedback = null;
        sb.append(" SELECT user_msg.user_nickname,user_msg.user_uname,user_msg.user_phone, ");
        sb.append(" user_msg.user_root_id, ");
        sb.append(" user_feedback.feedback_id,user_feedback.feedback_date, ");
        sb.append(" user_feedback.feedback_content,user_feedback.feedback_state,user_feedback.feedback_contact, ");
        sb.append(" user_feedback.feedback_remsg,user_feedback.remsg_date,user_msg.user_id ");
        sb.append(" FROM user_msg ,user_feedback where user_msg.user_id = user_feedback.user_id");
        sb.append(" Limit ?,? ");
        ResultSet rs = JdbcUtil.executeQuery(sb.toString(),(page.getPage()-1)*page.getSize(),page.getSize());
        if (rs!=null){
            list = new ArrayList<>();
            while (rs.next()){
                userFeedback = new UserFeedback();
                userFeedback.getUserMsg().setUserId(rs.getInt("user_id"));
                userFeedback.getUserMsg().setUserNickname(rs.getString("user_nickname"));
                userFeedback.getUserMsg().setUserUname(rs.getString("user_uname"));
                userFeedback.getUserMsg().setUserPhone(rs.getString("user_phone"));
                userFeedback.getUserMsg().setUserRootId(rs.getInt("user_root_id"));
                userFeedback.setFeedbackId(rs.getInt("feedback_id"));
                userFeedback.setRemsgDate(rs.getTimestamp("remsg_date"));
                userFeedback.setFeedbackContent(rs.getString("feedback_content"));
                userFeedback.setFeedbackState(rs.getInt("feedback_state"));
                userFeedback.setFeedbackContact(rs.getInt("feedback_contact"));
                userFeedback.setFeedbackRemsg(rs.getString("feedback_remsg"));
                userFeedback.setFeedbackDate(rs.getTimestamp("feedback_date"));
                list.add(userFeedback);
            }
        }
        return list;
    }

    @Override
    public int updateFeedback(UserFeedback feedback) {
        String sql = "UPDATE `uxshare`.`user_feedback` SET `feedback_state` = ?,`feedback_remsg` = ?, `remsg_date` = ? WHERE `feedback_id` = ? ";
        return JdbcUtil.executeUpdate(sql,feedback.getFeedbackState(),feedback.getFeedbackRemsg(),feedback.getRemsgDate(),feedback.getFeedbackId());
    }

    @Override
    public int insertFeedback(UserFeedback userFeedback) {
        String sql = "INSERT INTO `uxshare`.`user_feedback` (`user_id`,`feedback_date`,`feedback_content`,`feedback_state`,`feedback_contact`) VALUES(?,?,?,?,? ) ";
        return JdbcUtil.executeUpdate(sql,userFeedback.getUserId(),userFeedback.getFeedbackDate(),userFeedback.getFeedbackContent(),userFeedback.getFeedbackState(),userFeedback.getFeedbackContact());
    }

    @Override
    public int getFeedbackCount() throws SQLException {
        String sql = "SELECT COUNT(1) FROM `user_feedback` ";
        ResultSet rs = JdbcUtil.executeQuery(sql );
        int count = 0;
        if (rs!=null){
            if(rs.next()){
                count = rs.getInt(1);
            }
        }
        return count;
    }

}
