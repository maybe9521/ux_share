package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.pojo.UserMsgDto;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserMsgService {

    public List<UserMsg> getAll() throws SQLException;
    public UserMsg getByNameAndPwd(String uname,String pwd);

    public boolean UserRegister(UserMsg userMsg) throws SQLException;

    public UserMsg login(UserMsg userMsg) throws SQLException;

    public boolean updateState(Integer userid,Integer state) throws SQLException;

    public int getCount() throws SQLException;

    // 根据用户账户和密码或id取得用户详细信息
    public UserMsgDto getUserMsgInfo(UserMsgDto userMsgDto)throws SQLException;


    public Page<UserMsg> getBypage(Page<UserMsg> page)throws SQLException;


    public Page<UserMsg> getLikeUser(String like,Page<UserMsg> page)throws SQLException;
}
