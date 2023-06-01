package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.RootDao;
import com.bdqn.ux_share.dao.impl.RootDaoImpl;
import com.bdqn.ux_share.pojo.Root;
import com.bdqn.ux_share.service.RootService;

import java.util.List;

public class RootServiceImpl implements RootService {
    RootDao rootDao = new RootDaoImpl();

    @Override
    public List<Root> getAll() {
        return rootDao.getAll();
    }
}
