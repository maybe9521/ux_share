package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.NoticeDao;
import com.bdqn.ux_share.pojo.Notice;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    /**
     * 查询全部
     * @return
     * @throws SQLException
     */
    @Override
    public List<Notice> gatAll() throws SQLException {
        String sql = "select notice_id,notice_title,notice_content,notice_date from notice;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<Notice> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new Notice(
                        rs.getInt("notice_id"),
                        rs.getString("notice_title"),
                        rs.getString("notice_content"),
                        rs.getDate("notice_date")));

            }
        }
        return list;
    }

    /**
     * 查询数量
     * @return
     * @throws SQLException
     */
    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "select count(1) as count from notice;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if (rs != null){
            while (rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    /**
     * 删除
     * @param noticeId
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(Integer noticeId) throws SQLException {
        String sql="delete from notice where notice_id = ?;";
        return JdbcUtil.executeUpdate(sql,noticeId);
    }




    /**
     * 修改1
     * @param notice
     * @return
     * @throws SQLException
     */
    @Override
    public int updata(Notice notice) throws SQLException {
        String sql ="update notice set notice_id= ?,notice_title=?,notice_content=?,notice_date=? where notice_id=?;";
        return JdbcUtil.executeUpdate(sql,
                notice.getNoticeId(),
                notice.getNoticeTitle(),
                notice.getNoticeContent(),
                notice.getNoticeDate());
    }

    /**
     * 增加
     * @param notice
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Notice notice) throws SQLException {
        String sql = "INSERT INTO notice VALUES (null,?,?,NOW())";
        return JdbcUtil.executeUpdate(sql,notice.getNoticeTitle(),notice.getNoticeContent());
    }

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public List<Notice> getByPage(Page<Notice> page, Integer noticeId) throws SQLException {
        String sql = "SELECT notice_id,notice_title,notice_content,notice_date FROM notice order by notice_id asc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<Notice> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new Notice(rs.getInt("notice_id"),
                        rs.getString("notice_title"),rs.getString("notice_content"),
                        rs.getDate("notice_date")));
            }
        }
        return list;

    }

    @Override
    public List<Notice> getNcByPage(Page<Notice> page) throws SQLException {
        String sql = "SELECT notice_id,notice_title,notice_content,notice_date FROM notice order by notice_id asc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<Notice> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new Notice(rs.getInt("notice_id"),
                        rs.getString("notice_title"),rs.getString("notice_content"),
                        rs.getDate("notice_date")));
            }
        }
        return list;
    }

    @Override
    public Notice getNoiceByid(Integer noticeId) throws SQLException {
        String sql = "select notice_id,notice_title,notice_content,notice_date from notice where notice_id = ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,noticeId);
        Notice notice = new Notice();
        if(rs != null){
            while(rs.next()){
                notice.setNoticeId(rs.getInt("notice_id"));
                notice.setNoticeTitle(rs.getString("notice_title"));
                notice.setNoticeContent(rs.getString("notice_content"));
                notice.setNoticeDate(rs.getDate("notice_date"));
            }
        }
        return notice;
    }

    @Override
    public List<Notice> getTopNotice(Integer noticeId, int limit) throws SQLException {
        String sql = "select notice_id,notice_title,notice_content,notice_date from notice where notice_id = ? order by notice_date desc limit ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,noticeId,limit);
        List<Notice> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                Notice notice = new Notice();
                notice.setNoticeId(rs.getInt("noctice_id"));
                notice.setNoticeTitle(rs.getString("noctice_title"));
                notice.setNoticeContent(rs.getString("noctice_content"));
                notice.setNoticeDate(rs.getDate("noctice_date"));
                list.add(notice);
            }
        }
        return list;
    }
}
