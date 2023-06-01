package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.UserInform;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserInfromDao {
    /**
     * 通知
     * @return
     * @throws SQLException
     */
    public List<UserInform> getAll() throws SQLException;

    /**
     * 查询通知有多少个
     * @return
     * @throws SQLException
     */
    public int getCount() throws SQLException;

    public UserInform getinformId(Integer informId) throws SQLException;
    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    public List<UserInform> getUIPage(Page<UserInform> page) throws SQLException;

    /**
     * 删除通知
     * @param informId
     * @return
     * @throws SQLException
     */
    public int delete(Integer informId) throws SQLException;


    /**
     * 增加通知
     * @param userInform
     * @return
     * @throws SQLException
     */
    public int add(UserInform userInform) throws SQLException;

    /**
     * 修改通知
     * @param userInform
     * @return
     * @throws SQLException
     */
    public int update(UserInform userInform) throws SQLException;
    public List<UserInform> getTopUI(Integer informId,int limit) throws SQLException;
    public int getCount(Integer informId) throws SQLException;
    public UserInform getArticleCarouselbyId(Integer informId) throws SQLException;
    public List<UserInform> getUIByPage(Page<UserInform> page,Integer informId) throws SQLException;
    List<UserInform> getUserInform(int uid) throws SQLException;
}
