package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.UserInfromDao;
import com.bdqn.ux_share.dao.impl.UserInfromDaoImpl;
import com.bdqn.ux_share.pojo.UserInform;
import com.bdqn.ux_share.service.UserInformService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserInformServiceImpl implements UserInformService {
    private UserInfromDao userInfromDao = new UserInfromDaoImpl();
    @Override
    public List<UserInform> getAll() throws SQLException {
        return userInfromDao.getAll();
    }

    @Override
    public Page<UserInform> getByPage(Page<UserInform> page) throws SQLException {
        page.setTotal(userInfromDao.getCount());
        page.setData(userInfromDao.getUIPage(page));
        return page;
    }

    @Override
    public boolean delete(Integer informId) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if(1 == userInfromDao.delete(informId)){
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
    public boolean add(UserInform userInform) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加时间
            userInform.setInformDate(new Date());
            userInform.setInformState(2);
            if(1 == userInfromDao.add(userInform)){
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
    public boolean update(UserInform userInform) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加修改时间
            userInform.setInformDate(new Date());
            if(1 == userInfromDao.update(userInform)){
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
    public UserInform getUIbyid(Integer informId) throws SQLException {
        return userInfromDao.getinformId(informId);
    }

    @Override
    public List<UserInform> getTopAC(Integer informId, int limit) throws SQLException {
        return userInfromDao.getTopUI(informId,limit);
    }

    @Override
    public Page<UserInform> getByPage(Page<UserInform> page, Integer informId) throws SQLException {
        page.setTotal(userInfromDao.getCount(informId));
        page.setData(userInfromDao.getUIByPage(page,informId));
        return page;
    }

    @Override
    public List<UserInform> getUserInform(int uid) throws SQLException {
        return userInfromDao.getUserInform(uid);
    }
}
