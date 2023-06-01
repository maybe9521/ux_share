package com.bdqn.ux_share.service;

import com.bdqn.ux_share.pojo.ArticleDto;

import java.sql.SQLException;

public interface ArticleReleTagService {
    /**
     * 新增有关文章关联标签
     * @param articleDto
     * @return
     * @throws SQLException
     */
    int addArticleRelationTagIds(ArticleDto articleDto)throws SQLException;
}
