package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.UserFeedback;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserFeedbackDao {
    /**
     * 查询所有用户反馈
     * @return
     * @throws SQLException
     */
    List<UserFeedback> getAllFeedback(Page<UserFeedback> page) throws SQLException;

    /**
     * 处理反馈
     * @param userFeedback
     * @return
     */
    int updateFeedback(UserFeedback userFeedback);

    /**
     * 新增反馈
     * @param userFeedback
     * @return
     */
    int insertFeedback(UserFeedback userFeedback);

    int getFeedbackCount() throws SQLException;
}
