package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.Notice;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface NoticeService {
    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    public List<Notice> getAll() throws SQLException;



    Page<Notice> getNcByPage(Page<Notice> page) throws SQLException;

    /**
     * 删除
     * @param noticeId
     * @return
     * @throws SQLException
     */
    public boolean delete(Integer noticeId) throws SQLException;

    /**
     * 新增
     * @param notice
     * @return
     * @throws SQLException
     */
    public boolean add(Notice notice) throws SQLException;

    /**
     * 修改
     * @param notice
     * @return
     * @throws SQLException
     */
    public boolean update(Notice notice) throws SQLException;


    public Notice getNoticbyid(Integer noticeId) throws SQLException;

    /**
     * 分页
     * @param noticeId
     * @param limit
     * @return
     * @throws SQLException
     */
    public List<Notice> getNcByPage(Integer noticeId,int limit)throws SQLException;

}
