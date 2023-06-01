package com.bdqn.ux_share.dao.impl;


import com.bdqn.ux_share.dao.RootDao;
import com.bdqn.ux_share.pojo.Root;
import com.bdqn.ux_share.util.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RootDaoImpl implements RootDao {
    @Override
    public List<Root> getAll() {
        String sql = "SELECT root_id,root_level,root_desc FROM root";
        ResultSet rs =JdbcUtil.executeQuery(sql);
        List<Root> rootList = new ArrayList<Root>();
        Root root = null;
        try{
            while (rs.next()){
                root = new Root();
                root.setRootId(rs.getInt("root_id"));
                root.setRootLevel(rs.getInt("root_level"));
                root.setRootDesc(rs.getString("root_desc"));
                rootList.add(root);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rootList;
    }
}
