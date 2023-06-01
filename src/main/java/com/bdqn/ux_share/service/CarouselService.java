package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.ArticleCarousel;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface CarouselService {
    /**
     * 查询所有轮播图
     * @return
     * @throws SQLException
     */
    public List<ArticleCarousel> getAll() throws SQLException;

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    public Page<ArticleCarousel>getByPage(Page<ArticleCarousel> page) throws SQLException;

    /**
     * 删除轮播图
     * @param carouselId
     * @return
     * @throws SQLException
     */
    public boolean delete(Integer carouselId) throws SQLException;

    /**
     * 新增轮播图
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    public boolean add(ArticleCarousel articleCarousel) throws SQLException;

    /**
     * 修改新闻
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    public boolean update(ArticleCarousel articleCarousel) throws SQLException;


    public ArticleCarousel getArticleCarouselbyid(Integer carouselId) throws SQLException;

    /**
     * 分页
     * @param acticleId
     * @param limit
     * @return
     * @throws SQLException
     */
    public List<ArticleCarousel> getTopAC(Integer acticleId,int limit)throws SQLException;
    /**
     * 主题分页
     * @param page
     * @param carouselId
     * @return
     * @throws SQLException
     */
    public Page<ArticleCarousel> getByPage(Page<ArticleCarousel> page,Integer carouselId)throws SQLException;

    /**
     * 查询所有轮播数据
     * @return
     */
    List<ArticleCarousel> getAllCarousel();
}
