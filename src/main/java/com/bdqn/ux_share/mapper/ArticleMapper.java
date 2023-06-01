package com.bdqn.ux_share.mapper;

import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleDto;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ArticleMapper {

    /**
     * 新增文章列表
     * @param articleDto
     * @return
     * @throws SQLException
     */
    public int addArticle(ArticleDto articleDto)throws SQLException;


    /**
     * 删除文章
     * @param
     * @return
     * @throws SQLException
     */
    int delArticle(ArticleDto articleDto) throws  SQLException;


    /**
     * 查询文章所有列表
     *
     * @return
     * @throws SQLException
     */
    public List<Article> getAll() throws SQLException;

    /**
     * 查询文章所有列表以及作者账户
     *
     * @return
     * @throws SQLException
     */
    public List<ArticleDto> getArticleDtoAll(@Param("username") String username,
                                             @Param("startDate") String startDate,
                                             @Param("endDate") String endDate) throws SQLException;


    /**
     * 查询文章数量
     * @return
     * @throws SQLException
     */
    public int getArticleDtoCount() throws SQLException;


    /**
     * 查询文章数量
     *
     * @param username
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    public int getArticleDtoCounts(@Param("username") String username,
                                   @Param("startDate") Timestamp startDate,
                                   @Param("endDate") Timestamp endDate) throws SQLException;

    public int getArticleAll() throws SQLException;

    List<ArticleDto> selectByPageBeanAll(@Param("page") int page, @Param("size") int size) throws SQLException;

    List<ArticleDto> selectByPageBean(@Param("page") int page, @Param("size") int size, @Param("username") String username,
                                      @Param("startDate") String startDate,
                                      @Param("endDate") String endDate) throws SQLException;

    List<ArticleDto> selectByPageBeans(@Param("page") int page, @Param("size") int size, @Param("username") String username,
                                       @Param("startDate") Timestamp startDate,
                                       @Param("endDate") Timestamp endDate) throws SQLException;



    /**
     * 查询文章评论数量
     * @param num
     * @return
     * @throws SQLException
     */
    int selectCommentLengthInt(@Param("num") int num)throws SQLException;
}
