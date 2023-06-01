package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.ArticleCommentDao;
import com.bdqn.ux_share.dao.UserMsgDao;
import com.bdqn.ux_share.dao.impl.ArticleCommentDaoImpl;
import com.bdqn.ux_share.dao.impl.UserMsgDaoImpl;
import com.bdqn.ux_share.mapper.UserMsgMapper;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.pojo.UserMsgDto;
import com.bdqn.ux_share.service.UserMsgService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserMsgServiceImpl implements UserMsgService {
    private UserMsgDao userMsgDao = new UserMsgDaoImpl();
    ArticleCommentDao articleCommentDao = new ArticleCommentDaoImpl();

    @Override
    public List<UserMsg> getAll() throws SQLException {
        return userMsgDao.getAll();
    }

    @Override
    public UserMsg getByNameAndPwd(String uname, String pwd) {
        return null;
    }

    @Override
    public boolean UserRegister(UserMsg userMsg) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            Date date = new Date();
            userMsg.setUserCreateDate(date);
            if(1 == userMsgDao.UserRegister(userMsg)){
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
    public UserMsg login(UserMsg userMsg) throws SQLException {
        try {
            return userMsgDao.getUserBynameAndPwd(userMsg.getUserUname(),userMsg.getUserPwd());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateState(Integer userid,Integer state) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(1 == userMsgDao.updateState(userid, state)){
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
    public int getCount() throws SQLException {
        return userMsgDao.getCount();
    }

    @Override
    public UserMsgDto getUserMsgInfo(UserMsgDto userMsgDto) throws SQLException {
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        UserMsgMapper userMsgMapper = sqlSession.getMapper(UserMsgMapper.class);
        // 执行SQL语句
        UserMsgDto userMsg = userMsgMapper.getUserMsgInfo(userMsgDto);
      //  UserMsgDto userMsgDto1 = new UserMsgDto();
        // 释放资源
        sqlSession.close();
        return userMsg;
    }


    @Override
    public Page<UserMsg> getBypage(Page<UserMsg> page) throws SQLException {
        page.setTotal(userMsgDao.getCount());
        page.setData(userMsgDao.getUserByPage(page));
        return page;
    }
    @Override
    public Page<UserMsg> getLikeUser(String like,Page<UserMsg> page) throws SQLException {
        try {
            page.setData(userMsgDao.searchLikeUser(like,page));
            page.setTotal(userMsgDao.getCount());
            return page;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
