package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.ArticleClass;
import com.bdqn.ux_share.pojo.Notice;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {

    /**
     * 查询公告
     * @return
     * @throws SQLException
     */
    public List<Notice> gatAll() throws SQLException;


    /**
     * 查询多少公告数量
     * @return
     * @throws SQLException
     */
    public int getCount() throws SQLException;

    /**
     * 删除
     * @param noticeId
     * @return
     * @throws SQLException
     */
    public int delete(Integer noticeId) throws SQLException;

    /**
     * 修改公告
     * @param notice
     * @return
     * @throws SQLException
     */
    public int updata(Notice notice) throws SQLException;


    /**
     * 修改公告
     * @param notice
     * @return
     * @throws SQLException
     */
    public int add(Notice notice) throws SQLException;


    List<Notice> getByPage(Page<Notice> page, Integer noticeId) throws SQLException;

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Notice> getNcByPage(Page<Notice> page) throws  SQLException;
    public Notice getNoiceByid(Integer noticeId)throws SQLException;
    public List<Notice> getTopNotice(Integer noticeId,int limit) throws SQLException;

}
