package com.bdqn.ux_share.dao;

import com.bdqn.ux_share.pojo.ArticleCarousel;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * 轮播图
 */
public interface CarouselDao {
    /**
     * 查询所有轮播图
     * @return
     * @throws SQLException
     */
    public List<ArticleCarousel> getAll() throws SQLException;

    /**
     * 查询轮播图有多少个
     * @return
     * @throws SQLException
     */
    public int getCount() throws SQLException;

    public ArticleCarousel getCarouselbyId(Integer carouselid) throws SQLException;
    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    public List<ArticleCarousel> getACPage(Page<ArticleCarousel> page) throws SQLException;

    /**
     * 删除轮播图
     * @param carouselId
     * @return
     * @throws SQLException
     */
    public int delete(Integer carouselId) throws SQLException;


    /**
     * 增加轮播图
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    public int add(ArticleCarousel articleCarousel) throws SQLException;

    /**
     * 修改轮播图
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    public int update(ArticleCarousel articleCarousel) throws SQLException;
    public List<ArticleCarousel> getTopAC(Integer carousel_id,int limit) throws SQLException;
    public int getCount(Integer carouselId) throws SQLException;
    public ArticleCarousel getArticleCarouselbyId(Integer carousel_id) throws SQLException;
    public List<ArticleCarousel> getACByPage(Page<ArticleCarousel> page,Integer carouselId) throws SQLException;


    /**
     * 查询所有轮播数据
     * @return
     */
    List<ArticleCarousel> getAllCarousel() throws SQLException;
//
//    /**
//     * 查询所有轮播图
//     * @return
//     * @throws SQLException
//     */
//    public List<ArticleCarousel> getAll() throws SQLException;
//
//    /**
//     * 查询轮播图有多少个
//     * @return
//     * @throws SQLException
//     */
//    public int getCount() throws SQLException;
//
//
//    /**
//     * 分页
//     * @param page
//     * @return
//     * @throws SQLException
//     */
//    public List<ArticleCarousel> getACPage(Page<ArticleCarousel> page) throws SQLException;
//
//    /**
//     * 删除轮播图
//     * @param carouselId
//     * @return
//     * @throws SQLException
//     */
//    public int delete(Integer carouselId) throws SQLException;
//
//
//    /**
//     * 增加轮播图
//     * @param articleCarousel
//     * @return
//     * @throws SQLException
//     */
//    public int add(ArticleCarousel articleCarousel) throws SQLException;
//
//    /**
//     * 修改轮播图
//     * @param articleCarousel
//     * @return
//     * @throws SQLException
//     */
//    public int update(ArticleCarousel articleCarousel) throws SQLException;
//
//    public ArticleCarousel getArticleCarouselbyId(Integer carousel_id) throws SQLException;
}
