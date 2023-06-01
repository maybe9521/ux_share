package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.NoticeDao;
import com.bdqn.ux_share.dao.impl.NoticeDaoImpl;
import com.bdqn.ux_share.pojo.Notice;
import com.bdqn.ux_share.service.NoticeService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao = new NoticeDaoImpl();

    @Override
    public List<Notice> getAll() throws SQLException {
        return noticeDao.gatAll();
    }




    @Override
    public Page<Notice> getNcByPage(Page<Notice> page) throws SQLException {
        page.setTotal(noticeDao.getCount());
        page.setData(noticeDao.getNcByPage(page));
        return page;
    }


    @Override
    public boolean delete(Integer noticeId) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            if (1 == noticeDao.delete(noticeId)) {
                flag = true;
                connection.commit();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean add(Notice notice) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加时间
            Date date = new Date();
            notice.setNoticeDate(date);
            notice.setNoticeDate(date);
            if (1 == noticeDao.add(notice)) {
                flag = true;
                connection.commit();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean update(Notice notice) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加修改时间
            notice.setNoticeDate(new Date());
            if (1 == noticeDao.updata(notice)) {
                flag = true;
                connection.commit();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(connection, null, null);
        }
        return flag;
    }

    @Override
    public Notice getNoticbyid(Integer noticeId) throws SQLException {
        return noticeDao.getNoiceByid(noticeId);
    }

    @Override
    public List<Notice> getNcByPage(Integer noticeId, int limit) throws SQLException {
        return noticeDao.getTopNotice(noticeId,limit);
    }
}
