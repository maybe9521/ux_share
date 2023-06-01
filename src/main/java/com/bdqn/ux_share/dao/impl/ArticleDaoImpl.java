package com.bdqn.ux_share.dao.impl;


import com.bdqn.ux_share.mapper.ArticleMapper;
import com.bdqn.ux_share.pojo.UserMsg;
import com.bdqn.ux_share.pojo.condition.UserCondition;
import com.bdqn.ux_share.dao.ArticleCommentDao;
import com.bdqn.ux_share.dao.ArticleDao;
import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleComment;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.util.FilePath;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;
import jdk.nashorn.internal.scripts.JD;
import org.apache.ibatis.session.SqlSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    private ArticleCommentDao acd = new ArticleCommentDaoImpl();


    @Override
    public int selectByCondition(ArticleDto articleDto) {
        int choose = articleDto.getChoose();// 选择分支
        int userId = articleDto.getUserId();// 用户id
        String sql;
        ResultSet rs = null;
        int row = 0;
        if (choose == 1) {
            sql = "SELECT count(1) FROM article AS a,user_collect AS att WHERE a.article_id=att.article_id AND att.user_id=?";
            rs = JdbcUtil.executeQuery(sql, userId);
        } else if (choose == 2) {
            sql = "SELECT count(1) FROM article WHERE user_id=?";
            rs = JdbcUtil.executeQuery(sql, userId);
        } else if (choose == 3) {
            sql = "SELECT count(1) FROM article AS a WHERE a.user_id IN (SELECT uat.att_id FROM user_att AS uat WHERE uat.user_id=?)";
            rs = JdbcUtil.executeQuery(sql, userId);
        }
        try {
            while (rs.next()){
                row = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<ArticleDto> selectByConditionUser(Page<ArticleDto> page, ArticleDto articleDto) {
        int choose = articleDto.getChoose();// 选择分支
        int userId = articleDto.getUserId();// 用户id
        int pages =page.getPage();
        int sizes = page.getSize();
        int pageIndex =  (pages-1)*sizes;
        int sizeIndex = sizes;
        String sql;
        List<ArticleDto> list = new ArrayList<ArticleDto>();
        ResultSet rs = null;
        if (choose == 1) {
            sql = "SELECT DISTINCT a.article_id,a.user_id,a.article_title,a.class_id,a.article_content," +
                    "a.article_summary,a.article_cover,a.data_like,a.data_pv,a.article_date,a.article_state," +
                    "us.user_nickname,us.user_icon,us.user_uname FROM article AS a LEFT JOIN user_collect AS " +
                    "uc ON a.article_id=uc.article_id LEFT JOIN user_msg AS us ON " +
                    "us.user_id=a.user_id WHERE uc.user_id=? ORDER BY a.article_date DESC LIMIT ?,?";
            rs = JdbcUtil.executeQuery(sql, userId,pageIndex,sizeIndex);
        } else if (choose == 2) {
            sql = "SELECT a.article_id,a.user_id,a.article_title,a.class_id,a.article_content," +
                    "a.article_summary,a.article_cover,a.data_like,a.data_pv,a.article_date,a.article_state," +
                    "us.user_nickname,us.user_icon,us.user_uname FROM article AS a LEFT JOIN" +
                    " user_msg AS us ON a.user_id=us.user_id WHERE us.user_id=? ORDER BY a.article_date DESC LIMIT ?,?";
            rs = JdbcUtil.executeQuery(sql, userId,pageIndex,sizeIndex);
        } else if (choose == 3) {
            sql = "SELECT DISTINCT a.article_id,a.user_id,a.article_title,a.class_id," +
                    "a.article_content,a.article_summary,a.article_cover,a.data_like," +
                    "a.data_pv,a.article_date,a.article_state,us.user_nickname,us.user_icon," +
                    "us.user_uname FROM article AS a LEFT JOIN user_att AS uat ON a.user_id=uat.user_id" +
                    " LEFT JOIN article_comment AS comm ON a.article_id=comm.article_id LEFT JOIN user_msg AS us ON us.user_id=uat.user_id WHERE uat.user_id !=?  ORDER BY a.article_date DESC LIMIT ?,?";
            rs = JdbcUtil.executeQuery(sql, userId,pageIndex,sizeIndex);
        }
        try {
            while (rs.next()) {
                list.add(
                        new ArticleDto(
                                rs.getInt("a.article_id"),
                                rs.getInt("a.user_id"),
                                rs.getString("a.article_title"),
                                rs.getInt("a.class_id"),
                                rs.getString("a.article_content"),
                                rs.getString("a.article_summary"),
                                rs.getString("a.article_cover"),
                                rs.getInt("a.data_like"),
                                rs.getInt("a.data_pv"),
                                rs.getInt("a.article_state"),
                                rs.getTimestamp("a.article_date"),
                                new UserMsg(
                                        rs.getString("us.user_nickname"),
                                        rs.getString("us.user_icon"),
                                        rs.getString("us.user_uname")
                                ))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            flag=list.get(i).getArticleCover().startsWith("/");
            if(flag==false){
                String path = list.get(i).getArticleCover();
                list.get(i).setArticleCover(FilePath.findFileSavePathByFileName(path,FilePath.UPLOAD)+"/"+path);
            }
            // 取得Sqlsession对象
            SqlSession sqlSession = JdbcUtil.getSqlsession();
            // 取得UserMsgMapper代理对象
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            // 执行SQL语句
            try {
                list.get(i).setCommentLength(articleMapper.selectCommentLengthInt(list.get(i).getArticleId()));
                sqlSession.commit();// 提交事务
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sqlSession.close();
        }
        return list;
    }



    @Override
    public List<UserCondition> getAllArticle(Page<UserCondition> page, UserCondition condition, String sortBy) throws SQLException {
        List<UserCondition> list = null;
        List<Object> params = new ArrayList<>();
        String sql = " SELECT article_id,article_title,class_name,article_summary,article_cover,data_like,data_pv,article_state,article_date,user_nickname,user_icon FROM user_article_class where 1=1 ";
        UserCondition userCondition = null;
        if (condition != null) {
            //标题模糊查询
            if (condition.getArticle().getArticleTitle() != null) {
                sql += "and article_title like CONCAT('%',?,'%') ";
                params.add(condition.getArticle().getArticleTitle());
            }
            //查询用户收藏
            if (condition.getUserMsg().getUserId() != null) {
                sql += " and article_id IN (SELECT article_id FROM user_collect WHERE user_id = ? ) ";
                params.add(condition.getUserMsg().getUserId());
            }
            if (sortBy != null) {
                switch (sortBy) {
                    case "pv": {     //浏览量排序
                        sql += " ORDER BY data_pv DESC ";
                        break;
                    }
                    case "like": {   //点赞量排序
                        sql += " ORDER BY data_like DESC ";
                        break;
                    }
                    case "date": {   //日期排序
                        sql += " ORDER BY article_date DESC ";
                        break;
                    }
                }
            }
        }
        sql += "limit ?,? ";
        params.add((page.getPage() - 1) * page.getSize());
        params.add(page.getSize());
        ResultSet rs = JdbcUtil.executeQuery(sql, params.toArray());
        if (rs != null) {
            list = new ArrayList<>();
            while (rs.next()) {
                userCondition = new UserCondition();
                userCondition.getArticle().setArticleId(rs.getInt("article_id"));
                userCondition.getArticle().setArticleTitle(rs.getString("article_title"));
                userCondition.getArticleClass().setClassName(rs.getString("class_name"));
                userCondition.getArticle().setArticleSummary(rs.getString("article_summary"));
                userCondition.getArticle().setArticleCover(rs.getString("article_cover"));
                userCondition.getArticle().setDataLike(rs.getInt("data_like"));
                userCondition.getArticle().setDataPv(rs.getInt("data_pv"));
                userCondition.getArticle().setArticleState(rs.getInt("article_state"));
                userCondition.getArticle().setArticleDate(rs.getTimestamp("article_date"));
                userCondition.getUserMsg().setUserNickname(rs.getString("user_nickname"));
                userCondition.getUserMsg().setUserIcon(rs.getString("user_icon"));
                for (ArticleComment c : acd.getAllComment(userCondition.getArticle().getArticleId())) {
                    userCondition.getArticle().getCommentList().add(c);
                }
                list.add(userCondition);
            }
        }
        boolean flag = false;
        for(int i=0;i<list.size();i++){
            flag = list.get(i).getArticle().getArticleCover().startsWith("/");
            if(flag==false){
                String path = list.get(i).getArticle().getArticleCover();
                list.get(i).getArticle().setArticleCover(FilePath.findFileSavePathByFileName(path,FilePath.UPLOAD)+"/"+path);
            }
        }
        return list;
    }


    @Override
    public int getCount(UserCondition condition) throws SQLException {
        String sql = " SELECT COUNT(1) FROM article ";
        List<Object> params = new ArrayList<>();
        if (condition != null) {
            //标题模糊查询
            if (condition.getArticle().getArticleTitle() != null) {
                sql += "and article_title like CONCAT('%',?,'%') ";
                params.add(condition.getArticle().getArticleTitle());
            }
            //查询用户收藏
            if (condition.getUserMsg().getUserId() != null) {
                sql += " and article_id IN (SELECT article_id FROM user_collect WHERE user_id = ? ) ";
                params.add(condition.getUserMsg().getUserId());
            }
        }
        ResultSet rs = JdbcUtil.executeQuery(sql, params.toArray());
        int count = 0;
        if (rs != null) {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }





    @Override
    public int getCountUser(UserCondition condition) throws SQLException {
        int choose = condition.getChoose();
        String sql;
        List<UserCondition> list = null;
        List<Object> params = new ArrayList<>();
        if (choose == 1) {

        } else if (choose == 2) {

        } else if (choose == 3) {

        }
        return 0;
    }

    @Override
    public int getAllInts() throws SQLException {
        String sql = "SELECT count(1) FROM `article`";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        int count = 0;
        if (rs != null) {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    @Override
    public int updateArticle(Article article) {
        List<Object> params = new ArrayList<>();
        String sql = "update `uxshare`.`article` set ";
        if (article != null) {
            if (article.getDataLike() != null) {
                sql += " `data_like` = ? ";
                params.add(article.getDataLike());
            }
            if (article.getDataPv() != null) {
                sql += " `data_pv` = ? ";
                params.add(article.getDataPv());
            }
        }
        sql += " where `article_id` = ? ";
        params.add(article.getArticleId());
        return JdbcUtil.executeUpdate(sql, params.toArray());
    }

    @Override
    public int getArticleLike(int aid) throws SQLException {
        String sql = " SELECT count(1) FROM user_collect WHERE article_id = ? ";
        int likeNum = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, aid);
        if (rs != null) {
            if (rs.next()) {
                likeNum = rs.getInt(1);
            }
        }
        return likeNum;
    }

    @Override
    public int getArticleSumLike(int uid) throws SQLException {
        String sql = " select sum(data_like) FROM article WHERE user_id = ? ";
        int sum = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, uid);
        if (rs != null) {
            if (rs.next()) {
                sum = rs.getInt(1);
            }
        }
        return sum;
    }

    @Override
    public int getArticleSum(int uid) throws SQLException {
        String sql = " select count(1) FROM article WHERE user_id = ? ";
        int sum = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, uid);
        if (rs != null) {
            if (rs.next()) {
                sum = rs.getInt(1);
            }
        }
        return sum;
    }




    private List<Article> executeSql(String sql, Object... params) throws SQLException {
        List<Article> list = null;
        Article article = null;
        ResultSet rs = JdbcUtil.executeQuery(sql, params);
        if (rs != null) {
            list = new ArrayList<>();
            while (rs.next()) {
                article = new Article();
                article.setArticleId(rs.getInt("article_id"));
                article.setUserId(rs.getInt("user_id"));
                article.setArticleTitle(rs.getString("article_title"));
                article.setClassId(rs.getInt("class_id"));
                article.setArticleContent(rs.getString("article_content"));
                article.setArticleSummary(rs.getString("article_summary"));
                article.setArticleCover(rs.getString("article_cover"));
                article.setDataLike(rs.getInt("data_like"));
                article.setDataPv(rs.getInt("data_pv"));
                article.setArticleState(rs.getInt("article_state"));
                article.setArticleDate(rs.getTimestamp("article_date"));
                list.add(article);
            }
        }
        return list;
    }

    @Override
    public List<Article> getAll() {
        String sql = "SELECT article_id,user_id,article_title,class_id,article_content,article_summary,article_cover," +
                "data_like,data_pv,article_state,article_date FROM article";
        List<Article> articleList = new ArrayList<Article>();
        Article article = null;
        ResultSet rs = JdbcUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                article = new Article();
                article.setArticleId(rs.getInt("article_id"));
                article.setUserId(rs.getInt("user_id"));
                article.setArticleTitle(rs.getString("article_title"));
                article.setClassId(rs.getInt("class_id"));
                article.setArticleContent(rs.getString("article_content"));
                article.setArticleSummary(rs.getString("article_summary"));
                article.setArticleCover(rs.getString("article_cover"));
                article.setDataLike(rs.getInt("data_like"));
                article.setDataPv(rs.getInt("data_pv"));
                article.setArticleState(rs.getInt("article_state"));
                article.setArticleDate(rs.getDate("article_date"));
                articleList.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag =false;
        for(int i=0;i<articleList.size();i++){
            flag=articleList.get(i).getArticleCover().startsWith("/");
            if(flag==false){
                String path = articleList.get(i).getArticleCover();
                articleList.get(i).setArticleCover(FilePath.findFileSavePathByFileName(path,FilePath.UPLOAD)+"/"+path);
            }

        }
        return articleList;
    }

    @Override
    public List<ArticleDto> getArticleDtoAll() {
        String sql = "SELECT a.article_id,a.user_id,a.article_title,a.class_id,a.article_content,a.article_summary,a.article_cover,a.data_like,a.data_pv," +
                "a.article_state,a.article_date,u.user_uname FROM article AS a,user_msg AS u WHERE a.user_id=u.user_id";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<ArticleDto> articleDtoList = new ArrayList<>();
        ArticleDto articleDto = null;
        try {
            while (rs.next()) {
                articleDto = new ArticleDto();
                articleDto.setArticleId(rs.getInt("a.article_id"));
                articleDto.setUserId(rs.getInt("a.user_id"));
                articleDto.setArticleTitle(rs.getString("a.article_title"));
                articleDto.setClassId(rs.getInt("a.class_id"));
                articleDto.setArticleContent(rs.getString("a.article_content"));
                articleDto.setArticleSummary(rs.getString("a.article_summary"));
                articleDto.setArticleCover(rs.getString("a.article_cover"));
                articleDto.setDataLike(rs.getInt("a.data_like"));
                articleDto.setDataPv(rs.getInt("a.data_pv"));
                articleDto.setArticleState(rs.getInt("a.article_state"));
                articleDto.setArticleDate(rs.getDate("a.article_date"));
                articleDto.setUserName(rs.getString("u.user_uname"));
                articleDtoList.add(articleDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtoList;
    }

    @Override
    public UserCondition getArticleDetails(int aid) throws SQLException {
        String sql = " SELECT article_id,article_title,class_name,data_like,data_pv,article_date,article_content,user_nickname,user_icon,user_id,user_level FROM user_article_class where article_id = ?";
        UserCondition userCondition = null;
        ResultSet rs = JdbcUtil.executeQuery(sql,aid);
        if (rs!=null){
            userCondition = new UserCondition();
            if (rs.next()){
                userCondition.getArticle().setArticleId(rs.getInt("article_id"));
                userCondition.getArticle().setArticleTitle(rs.getString("article_title"));
                userCondition.getArticleClass().setClassName(rs.getString("class_name"));
                userCondition.getArticle().setDataLike(rs.getInt("data_like"));
                userCondition.getArticle().setDataPv(rs.getInt("data_pv"));
                userCondition.getArticle().setArticleDate(rs.getTimestamp("article_date"));
                userCondition.getArticle().setArticleContent(rs.getString("article_content"));
                userCondition.getUserMsg().setUserIcon(rs.getString("user_icon"));
                userCondition.getUserMsg().setUserNickname(rs.getString("user_nickname"));
                userCondition.getUserMsg().setUserId(rs.getInt("user_id"));
                userCondition.getUserMsg().setUserLevel(rs.getInt("user_level"));
                userCondition.getUserMsg().setSumLike(this.getArticleSumLike(userCondition.getUserMsg().getUserId()));
                userCondition.getUserMsg().setSumArticle(this.getArticleSum(userCondition.getUserMsg().getUserId()));
                userCondition.getUserMsg().setSumAtt(this.getUserAttSum(userCondition.getUserMsg().getUserId()));
                for (ArticleComment c:acd.getAllComment(userCondition.getArticle().getArticleId())) {
                    userCondition.getArticle().getCommentList().add(c);
                }
            }
        }
        return userCondition;
    }


    @Override
    public List<UserMsg> getUserByPage(Page<UserMsg> page) throws SQLException {
        String sql = "SELECT user_id AS id,user_nickname AS nickname,user_uname AS uname,user_pwd\n" +
                "AS pwd,user_role AS role,user_state AS state,user_createDate AS createDate,user_icon AS icon,user_phone AS phone,user_point AS point,user_level AS `level`,user_root_id AS rootid,user_ex AS ex FROM user_msg limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql, (page.getPage() - 1) * page.getSize(), page.getSize());
        List<UserMsg> list = new ArrayList<>();
        if (rs != null) {
            while (rs.next()) {
                list.add(new UserMsg(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("uname"),
                        rs.getString("pwd"),
                        rs.getInt("role"),
                        rs.getInt("state"),
                        rs.getDate("createDate"),
                        rs.getString("icon"),
                        rs.getString("phone"),
                        rs.getInt("point"),
                        rs.getInt("level"),
                        rs.getInt("rootid"),
                        rs.getInt("ex")
                ));
            }
        }
        return list;
    }

    @Override
    public int visitTotal() throws SQLException {
        String sql = "SELECT sum(article.data_pv) as total FROM article";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        int row = 0;
        if (rs.next()) {
            row = rs.getInt("total");
        }
        return row;
    }

    @Override
    public int getArticleCollect(int aid) throws SQLException {
        String sql = " SELECT count(1) FROM user_collect WHERE article_id = ? ";
        int likeNum = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, aid);
        if (rs != null) {
            if (rs.next()) {
                likeNum = rs.getInt(1);
            }
        }
        return likeNum;
    }

    @Override
    public int getUserAttSum(int uid) throws SQLException {
        String sql = " SELECT count(1) FROM user_att WHERE user_id = ? ";
        int sum = 0;
        ResultSet rs = JdbcUtil.executeQuery(sql, uid);
        if (rs != null) {
            if (rs.next()) {
                sum = rs.getInt(1);
            }
        }
        return sum;
    }

    @Override
    public List<Article> getHotArticle() throws SQLException {
        List<Article> list = null;
        Article article = null;
        String sql = "SELECT article.article_id,article.article_title,article.article_cover,article.article_date FROM article ORDER BY article.data_pv DESC LIMIT 1,6 ";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if (rs != null) {
            list = new ArrayList<>();
            while (rs.next()) {
                article = new Article();
                article.setArticleId(rs.getInt("article_id"));
                article.setArticleTitle(rs.getString("article_title"));
                article.setArticleCover(rs.getString("article_cover"));
                article.setArticleDate(rs.getTimestamp("article_date"));
                list.add(article);
            }
        }
        return list;
    }

    @Override
    public int updateArticlePv(int aid) {
        String sql = "UPDATE `uxshare`.`article` SET `data_pv` = data_pv+1 WHERE `article_id` = ? ";
        return JdbcUtil.executeUpdate(sql, aid);
    }

    @Override
    public boolean updateArticleState(int aid, int stateId) {
        String sql;
        if(stateId==1){
            sql ="UPDATE article SET article.article_state = 2 WHERE article.article_id=?";
        }else {
            sql ="UPDATE article SET article.article_state = 1 WHERE article.article_id=?";
        }
        int row = JdbcUtil.executeUpdate(sql,aid);
        if(row!=0){
            return true;
        }
        return false;
    }


    @Override
    public int selectByCondition(Page<UserCondition> page, UserCondition condition) {
        int choose = condition.getChoose();
        String sql;
        List<UserCondition> list = null;
        List<Object> params = new ArrayList<>();
        if (choose == 1) {

        } else if (choose == 2) {

        } else if (choose == 3) {

        }
        return 0;
    }

    @Override
    public List<Article> selectByConditionUser(Page<UserCondition> page, UserCondition condition) {
        int choose = condition.getChoose();
        String sql;
        List<UserCondition> list = null;
        List<Object> params = new ArrayList<>();
        if (choose == 1) {

        } else if (choose == 2) {

        } else if (choose == 3) {

        }
        return null;
    }




}
