package com.bdqn.ux_share.mapper;

import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.ArticleReleTag;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ArticleReleTagMapper {


    /**
     * 查询文章所关联的标签数量
     * @param
     * @return
     * @throws SQLException
     */
    int getAllByTagIdInt(ArticleDto articleDto)throws SQLException;

    /**
     * 绑定文章关联标签
     * @param list
     * @return
     * @throws SQLException
     */
    int addArticleRelationTagIds(@Param("list") List<ArticleReleTag> list)throws SQLException;

    /**
     * 删除文章关联标签
     * @param
     * @return
     * @throws SQLException
     */
    int delArticleRelationTagIds(ArticleDto articleDto)throws SQLException;


}
