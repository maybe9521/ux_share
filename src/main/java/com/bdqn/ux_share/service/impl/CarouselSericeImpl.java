package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.CarouselDao;
import com.bdqn.ux_share.dao.impl.CarouselDaoImpl;
import com.bdqn.ux_share.pojo.ArticleCarousel;
import com.bdqn.ux_share.service.CarouselService;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarouselSericeImpl implements CarouselService {

    private CarouselDao carouselDao = new CarouselDaoImpl();

    /**
     * 查询所有新闻
     * @return
     * @throws SQLException
     */
    @Override
    public List<ArticleCarousel> getAll() throws SQLException {
        return carouselDao.getAll();
    }

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public Page<ArticleCarousel> getByPage(Page<ArticleCarousel> page) throws SQLException {
        page.setTotal(carouselDao.getCount());
        page.setData(carouselDao.getACPage(page));
        return page;
    }

    @Override
    public boolean delete(Integer carouselId) throws SQLException {
        boolean flah = false;
        Connection conn = null;
        try{
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            if (1 == carouselDao.delete(carouselId)){
                flah = true;
                conn.commit();
            }
        }catch (Exception e){
            try{
                e.printStackTrace();
                conn.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return flah;
    }

    /**
     *
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    @Override
    public boolean add(ArticleCarousel articleCarousel) throws SQLException {
        boolean flag = false;
        Connection conn = null;
        try{
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            if(1 == articleCarousel.getCarouselId()){
                flag = true;
                conn.commit();
            }
        }catch (Exception e){
            try {
                e.printStackTrace();
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return flag;
    }

    @Override
    public boolean update(ArticleCarousel articleCarousel) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            // 添加修改时间
            articleCarousel.setCarouselImg(articleCarousel.getCarouselImg());
            if(1 == carouselDao.update(articleCarousel)){
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
    public ArticleCarousel getArticleCarouselbyid(Integer carouselId) throws SQLException {
        return carouselDao.getArticleCarouselbyId(carouselId);
    }

    @Override
    public List<ArticleCarousel> getTopAC(Integer acticleId, int limit) throws SQLException {
        return carouselDao.getTopAC(acticleId,limit);
    }

    @Override
    public Page<ArticleCarousel> getByPage(Page<ArticleCarousel> page, Integer carouselId) throws SQLException {
        page.setTotal(carouselDao.getCount(carouselId));
        page.setData(carouselDao.getACByPage(page,carouselId));
        return page;
    }

    @Override
    public List<ArticleCarousel> getAllCarousel(){
        try {
            return carouselDao.getAllCarousel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
