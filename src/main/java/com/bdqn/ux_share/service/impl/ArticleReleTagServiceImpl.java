package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.mapper.ArticleReleTagMapper;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.ArticleReleTag;
import com.bdqn.ux_share.service.ArticleReleTagService;
import com.bdqn.ux_share.util.JdbcUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleReleTagServiceImpl implements ArticleReleTagService {
    @Override
    public int addArticleRelationTagIds(ArticleDto articleDto) throws SQLException {
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        ArticleReleTagMapper userMsgMapper = sqlSession.getMapper(ArticleReleTagMapper.class);
        // 执行SQL语句
        List<ArticleReleTag> articleReleTagList = new ArrayList<ArticleReleTag>();
        int length = articleDto.getTagIds().length;// 获取文章关联标签数组
        for(int i=0;i<articleDto.getTagIds().length;i++){
            articleReleTagList.add(new ArticleReleTag(articleDto.getArticleId(),articleDto.getTagIds()[i]));
        }
        int row = userMsgMapper.addArticleRelationTagIds(articleReleTagList);// 新增行数
        if(row == length){
            sqlSession.commit();// 执行SQL长度等于集合长度
        }
        // 释放资源
        sqlSession.close();
        return row;
    }
}
