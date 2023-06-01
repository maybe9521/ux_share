package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface UserMsgDao {


    public UserMsg getUserBynameAndPwd(String name,String pwd) throws SQLException;

    /**
     * 查询所有用户信息
     * @return
     * @throws SQLException
     */
    public List<UserMsg> getAll() throws SQLException;
    public UserMsg getUser(String name,String pwd) throws SQLException;

    public int UserRegister(UserMsg userMsg) throws SQLException;

    /**
     * 更改用户状态
     *
     * @return
     * @throws SQLException
     */
    public int updateState(Integer userid,Integer state) throws SQLException;

    /**
     * 统计数据数量
     * @return
     * @throws SQLException
     */
    public int getCount() throws  SQLException;


    int getCareCount(int uid) throws SQLException;



    public List<UserMsg> getUserByPage(Page<UserMsg> page) throws SQLException;


    /**
     * 根据用户id得到详情信息
     * @param uid
     * @return
     */
    UserMsg getLoginUserInfo(int uid)throws SQLException;



    /**
     * 获取用户收藏文章的数量
     * @param uid
     * @return
     * @throws SQLException
     */
    int getUserCollectSum(int uid)throws SQLException;

    public List<UserMsg> searchLikeUser(String like,Page<UserMsg> page) throws SQLException;
}
