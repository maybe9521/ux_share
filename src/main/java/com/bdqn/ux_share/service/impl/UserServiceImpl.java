package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.UserDao;
import com.bdqn.ux_share.dao.impl.UserDaoImpl;
import com.bdqn.ux_share.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();
    @Override
    public boolean addUserAtt(int uid, int auid) {
        System.out.println(1);
        int a = ud.addUserAtt(uid, auid);
        System.out.println(a);
        return a>0;
    }

    @Override
    public boolean exitUserAtt(int uid, int auid) {
        try {
            return ud.exitUserAtt(uid, auid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exitUserCollect(int uid, int aid) {
        try {
            return ud.exitUserCollect(uid, aid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserCollect(int uid, int aid) {
        try {
            return ud.addCollect(uid, aid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserLike(int aid) {
        try {
            return ud.addLike(aid)>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
