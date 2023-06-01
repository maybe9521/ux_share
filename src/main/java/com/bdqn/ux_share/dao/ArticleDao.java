package com.bdqn.ux_share.dao;


import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.pojo.condition.UserCondition;
import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.util.Page;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {



    int getAllInts() throws SQLException;

    /**
     * 修改文章数据
     * @param article
     * @return
     */
    int updateArticle(Article article);




    /**
     * 查询文章被收藏数
     * @param aid
     * @return
     */
    int getArticleLike(int aid) throws SQLException;

    /**
     * 查询用户获赞总数
     * @param uid
     * @return
     * @throws SQLException
     */
    int getArticleSumLike(int uid) throws SQLException;

    // 统计所有文章的访问量
    int visitTotal()throws SQLException;

    /**
     * 查询文章所有列表
     * @return
     * @throws SQLException
     */
    public List<Article> getAll()  throws SQLException;

    /**
     * 查询文章所有列表以及作者账户
     * @return
     * @throws SQLException
     */
    public List<ArticleDto> getArticleDtoAll()  throws SQLException;



    /**
     * 查询文章条数
     * @param condition
     * @return
     * @throws SQLException
     */
    int getCount(UserCondition condition) throws SQLException;



    /**
     * 查询文章条数
     * @param condition
     * @return
     * @throws SQLException
     */
    int getCountUser(UserCondition condition) throws SQLException;



    /**
     * 文章详情
     * @param aid
     * @return
     * @throws SQLException
     */
    UserCondition getArticleDetails(int aid) throws SQLException;








    /**
     * 查询文章被收藏数
     * @param aid
     * @return
     */
    int getArticleCollect(int aid) throws SQLException;



    /**
     * 查询用户关注数
     * @param uid
     * @return
     * @throws SQLException
     */
    int getUserAttSum(int uid) throws SQLException;

    /**
     * 查询热门文章
     * @return
     * @throws SQLException
     */
    List<Article> getHotArticle() throws SQLException;


    //修改文章浏览量
    int updateArticlePv(int aid);

    /**
     * 根据文章编号,和文章当前状态更改文章状态
     * @param aid
     * @param stateId
     * @return
     */
    boolean updateArticleState(int aid,int stateId);


    /**
     * 查询所有文章
     * @param page
     * @return
     * @throws SQLException
     */
    public List<UserMsg> getUserByPage(Page<UserMsg> page) throws SQLException;

    /**
     * 查询所有文章
     * @param condition
     * @return
     * @throws SQLException
     */
    List<UserCondition> getAllArticle(Page<UserCondition> page, UserCondition condition,String sortBy) throws SQLException;





    /**
     * 查询用户所发文章总数
     * @param uid
     * @return
     * @throws SQLException
     */
    int getArticleSum(int uid) throws SQLException;



   int selectByCondition(Page<UserCondition> page, UserCondition condition);


    /**
     * 根据用户id查询关注用户的文章列表,收藏列表，用户的作品文章列表
     * @param page
     * @param condition
     * @return
     */
    List<Article> selectByConditionUser(Page<UserCondition> page, UserCondition condition);


    /**
     * 根据选择参数,条件查询文章数量
     * @param articleDto
     * @return
     */
    int selectByCondition( ArticleDto articleDto);


    /**
     * 根据用户id查询关注用户的文章列表,收藏列表，用户的作品文章列表
     * @param page
     * @param articleDto
     * @return
     */
    List<ArticleDto> selectByConditionUser(Page<ArticleDto> page, ArticleDto articleDto);

}
