package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.condition.UserCondition;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ArticleService {

    public List<Article> getAll() throws SQLException;

    public int selectCountBySelect(String username, String startDate, String endDate) throws SQLException;

    public List<ArticleDto> selectByPageBeanAndCondition(String username, String startDate, String endDate) throws SQLException;

    /**
     * 分页查询实体
     * @param page
     * @param size
     * @param username
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    public  List<ArticleDto> selectByPageBean(int page, int size, String username, String startDate, String endDate) throws SQLException;


    /**
     * 分页查询实体
     *
     * @param pageBean
     * @param username
     * @param startDate
     * @param endDate
     * @return
     */
    Page<ArticleDto> selectByPageAndCondition(Page<ArticleDto> pageBean, String username, String startDate, String endDate);// 分页查询实体

    /**
     *  新增文章
     * @param articleDto
     * @return
     * @throws SQLException
     */
    public boolean addArticle(ArticleDto articleDto)throws SQLException;

    /**
     * 分页查询实体
     * @param page
     * @param condition
     * @param sortBy
     * @return
     */
    Page<UserCondition> getAllArticle(Page<UserCondition> page, UserCondition condition, String sortBy);


    boolean delArticleById(ArticleDto articleDto)throws SQLException;

    UserCondition getArticleDetails(int aid);

    int getLikeCount(int aid);

    boolean addArticlePv(int aid);

    int getCollectCount(int aid);
    List<Article> getHotArticle();


    /**
     *  分页查询实体
     * @param pageBean 分页实体带page,size
     * @param articleDto 分页查询指定参数
     * @return
     */
    Page<ArticleDto> selectByPageAndConditionUser(Page<ArticleDto> pageBean, ArticleDto articleDto);// 分页查询实体
}
