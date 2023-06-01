package com.bdqn.ux_share.service;


import com.bdqn.ux_share.pojo.UserInform;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserInformService {
    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    public List<UserInform> getAll() throws SQLException;

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    public Page<UserInform> getByPage(Page<UserInform> page) throws SQLException;

    /**
     * 删除
     * @param informId
     * @return
     * @throws SQLException
     */
    public boolean delete(Integer informId) throws SQLException;

    /**
     * 新增用户通知
     * @param userInform
     * @return
     * @throws SQLException
     */
    public boolean add(UserInform userInform) throws SQLException;

    /**
     * 修改新闻
     * @param userInform
     * @return
     * @throws SQLException
     */
    public boolean update(UserInform userInform) throws SQLException;


    public UserInform getUIbyid(Integer informId) throws SQLException;

    /**
     * 分页
     * @param informId
     * @param limit
     * @return
     * @throws SQLException
     */
    public List<UserInform> getTopAC(Integer informId,int limit)throws SQLException;
    /**
     * 主题分页
     * @param page
     * @param informId
     * @return
     * @throws SQLException
     */
    public Page<UserInform> getByPage(Page<UserInform> page,Integer informId)throws SQLException;
    List<UserInform> getUserInform(int uid) throws SQLException;
}
