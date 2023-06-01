package com.bdqn.ux_share.mapper;

import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.pojo.UserMsgDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface UserMsgMapper {
    /**
     * 根据真实用户信息查询用户详细信息
     *
     * @param userMsgDto
     * @return
     * @throws SQLException
     */
    public UserMsgDto getUserMsgInfo(UserMsgDto userMsgDto) throws SQLException;

    /**
     * 统计用户收藏数量
     */
    int getUserCollect(ArticleDto articleDto)throws SQLException;
}
