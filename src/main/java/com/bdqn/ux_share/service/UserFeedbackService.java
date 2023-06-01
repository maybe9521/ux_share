package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.UserFeedback;
import com.bdqn.ux_share.util.Page;

import java.util.List;

public interface UserFeedbackService {
    Page<UserFeedback> getAllFeedback(Page<UserFeedback> page);
    boolean updateFeedback(UserFeedback userFeedback);
    boolean insertFeedback(UserFeedback userFeedback);
}
