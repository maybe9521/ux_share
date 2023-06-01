package com.bdqn.ux_share.service.impl;

import com.bdqn.ux_share.dao.ArticleDao;
import com.bdqn.ux_share.dao.impl.ArticleDaoImpl;
import com.bdqn.ux_share.mapper.ArticleMapper;
import com.bdqn.ux_share.mapper.ArticleReleTagMapper;
import com.bdqn.ux_share.pojo.Article;
import com.bdqn.ux_share.pojo.ArticleDto;
import com.bdqn.ux_share.pojo.ArticleReleTag;
import com.bdqn.ux_share.pojo.condition.UserCondition;
import com.bdqn.ux_share.service.ArticleService;
import com.bdqn.ux_share.util.*;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDao ad = new ArticleDaoImpl();

    @Override
    public int getCollectCount(int aid) {
        try {
            return ad.getArticleCollect(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Article> getHotArticle() {
        try {
            return ad.getHotArticle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserCondition getArticleDetails(int aid) {
        try {
            return ad.getArticleDetails(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLikeCount(int aid) {
        try {
            return ad.getArticleLike(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Page<UserCondition> getAllArticle(Page<UserCondition> page, UserCondition condition, String sortBy) {
        try {
            page.setTotal(ad.getCount(condition));
            page.setData(ad.getAllArticle(page, condition, sortBy));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }


    @Override
    public boolean delArticleById(ArticleDto articleDto) throws SQLException {
        // 执行SQL语句
        int tagIdRow = articleDto.getTagIds().length;
        for (int i = 0; i < tagIdRow; i++) {
            ArticleDto articleDto1 = new ArticleDto();
            // 取得Sqlsession对象
            SqlSession sqlSession = JdbcUtil.getSqlsession();
            // 取得UserMsgMapper代理对象
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            ArticleReleTagMapper articleReleTagMapper = sqlSession.getMapper(ArticleReleTagMapper.class);
            int indexTag = articleDto.getTagIds()[i];
            articleDto1.setArticleId(indexTag);
            int delTagIdRow = articleReleTagMapper.delArticleRelationTagIds(articleDto1);
            int row = 0;
            boolean flag = false;
            if (tagIdRow == 1) {
                int delArticle = articleMapper.delArticle(articleDto1);
                if (delArticle != 0) {
                    sqlSession.commit();
                    flag = true;
                }
            }
            // 释放资源
            sqlSession.close();
            //sqlSession.commit(); 是事务提交,mybatis默认是不自动提交,先判断执行结果正确，再进行 commit提交
        }
        return false;
    }


    ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    public List<Article> getAll() throws SQLException {
        return articleDao.getAll();
    }


    // 集合 实体
    @Override
    public List<ArticleDto> selectByPageBeanAndCondition(String username, String startDate, String endDate) throws SQLException {
        String name;
        String startDateString = startDate;
        String endDateString = endDate;
        Date startsDate = new Date();
        Date endsDate = new Date();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        if (username == null || username.equals("")) {
            name = "%";
        } else {
            name = "%" + username + "%";
        }
        if (startDateString == null) {
            startDateString = "1901-01-01 00:00:00";
            startsDate = SimpleForamtCommon.stringToDate("1901-01-01 00:00:00");
        }
        if (endDateString == null) {
            endDateString = "2099-01-01 00:00:00";
            endsDate = new Date(System.currentTimeMillis());
        }
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        // 执行SQL语句
        articleDtoList = articleMapper.getArticleDtoAll(name, startDateString, endDateString);
        System.out.println(articleDtoList);
        boolean flag = false;
        for (int i = 0; i < articleDtoList.size(); i++) {
            flag = articleDtoList.get(i).getArticleCover().startsWith("/");
            if (flag == false) {
                String path = articleDtoList.get(i).getArticleCover();
                articleDtoList.get(i).setArticleCover(FilePath.findFileSavePathByFileName(path,FilePath.UPLOAD)+"/"+path);
            }

        }
        // 释放资源
        sqlSession.close();
        //sqlSession.commit(); 是事务提交,mybatis默认是不自动提交,先判断执行结果正确，再进行 commit提交
        return articleDtoList;
    }


    // 集合条数
    @Override
    public int selectCountBySelect(String username, String startDate, String endDate) throws SQLException {
        String name;
        String startDateString = startDate;
        String endDateString = endDate;
        Date startsDate = new Date();
        Date endsDate = new Date();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        if (username == null || username.equals("")) {
            name = "%";
        } else {
            name = "%" + username + "%";
        }
        if (startDateString == null || startDateString.equals("")) {
            startDateString = "1901-01-01 00:00:00";
        }
        if (endDateString == null || endDateString.equals("")) {
            endDateString = "2099-12-12 00:00:00";
        }
        Timestamp start = Timestamp.valueOf(startDateString);
        Timestamp end = Timestamp.valueOf(endDateString);
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        // 执行SQL语句
        int row = articleMapper.getArticleDtoCounts(name, start, end);
        //int row = articleMapper.getArticleAll();
        System.out.println("row:" + row);
        // 释放资源
        sqlSession.close();
        return row;
    }


    // 分页集合实体
    @Override
    public List<ArticleDto> selectByPageBean(int page, int size, String username, String startDate, String endDate) throws SQLException {
        String name;
        String startDateString = startDate;
        String endDateString = endDate;
        Date startsDate = new Date();
        Date endsDate = new Date();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        if (username == null || username.equals("")) {
            name = "%";
        } else {
            name = "%" + username + "%";
        }
        if (startDateString == null || startDateString.equals("")) {
            startDateString = "1901-01-01 00:00:00";
        }
        if (endDateString == null || endDateString.equals("")) {
            endDateString = "2099-12-12 00:00:00";
        }
        Timestamp start = Timestamp.valueOf(startDateString);
        Timestamp end = Timestamp.valueOf(endDateString);
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        // 执行SQL语句
        //articleDtoList = articleMapper.getArticleDtoAll(name, startDateString, endDateString);
        articleDtoList = articleMapper.selectByPageBeans((page - 1) * size, size, name, start, end);
        int index = (page - 1) - size;
        //articleDtoList = articleMapper.selectByPageBeanAll(page,size);
        boolean flag = false;
        for (int i = 0; i < articleDtoList.size(); i++) {
            flag = articleDtoList.get(i).getArticleCover().startsWith("/");
            if (flag == false) {
                String path = articleDtoList.get(i).getArticleCover();
                articleDtoList.get(i).setArticleCover(FilePath.findFileSavePathByFileName(path,FilePath.UPLOAD)+"/"+path);
            }

        }
        // 释放资源
        sqlSession.close();
        //sqlSession.commit(); 是事务提交,mybatis默认是不自动提交,先判断执行结果正确，再进行 commit提交
        return articleDtoList;
    }


    @Override
    public Page<ArticleDto> selectByPageAndCondition(Page<ArticleDto> pageBean, String username, String startDate, String endDate) {
        try {
            pageBean.setTotal(selectCountBySelect(username, startDate, endDate));
            pageBean.setData(selectByPageBean(pageBean.getPage(), pageBean.getSize(), username, startDate, endDate));
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "当前页面:" + pageBean.getPage() +
                    "每页条数:" + pageBean.getSize() + "数据集合长度:" + pageBean.getData().size() + "总数量:" + pageBean.getTotal() + "总页码:" + pageBean.getPageCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pageBean;
    }


    @Override
    public boolean addArticle(ArticleDto articleDto) throws SQLException {
        // 执行结果
        boolean falg = false;
        // 取得Sqlsession对象
        SqlSession sqlSession = JdbcUtil.getSqlsession();
        // 取得UserMsgMapper代理对象
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        ArticleReleTagMapper articleReleTagMapper = sqlSession.getMapper(ArticleReleTagMapper.class);
        // 执行SQL语句
        int rowArticle = articleMapper.addArticle(articleDto);
        System.out.println(ConsoleColors.RED + rowArticle);
        List<ArticleReleTag> articleReleTagList = new ArrayList<ArticleReleTag>();
        int length = articleDto.getTagIds().length;// 获取文章关联标签数组长度
        int articleListRow = articleMapper.getArticleDtoCount() + 1;
        System.out.println(articleListRow);
        for (int i = 0; i < length; i++) {
            articleReleTagList.add(new ArticleReleTag(articleListRow, articleDto.getTagIds()[i]));
        }
        int rowArticleTag = articleReleTagMapper.addArticleRelationTagIds(articleReleTagList);
        System.out.println(ConsoleColors.RED + rowArticleTag);
        if (rowArticle != 0) {
            if (rowArticleTag == length) {
                sqlSession.commit();// 提交事务
                falg = true;
            }
        }
        // 释放资源
        sqlSession.close();
        //sqlSession.commit(); 是事务提交,mybatis默认是不自动提交,先判断执行结果正确，再进行 commit提交
        return falg;
    }


    @Override
    public Page<ArticleDto> selectByPageAndConditionUser(Page<ArticleDto> pageBean, ArticleDto articleDto) {
        System.out.println(ConsoleColors.CYAN_BOLD + articleDto);
        pageBean.setTotal(articleDao.selectByCondition(articleDto));// 总数据量,总页码
        System.out.println(ConsoleColors.RED + pageBean.getTotal());
        pageBean.setData(articleDao.selectByConditionUser(pageBean, articleDto));// 数据集合
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "当前页面:" + pageBean.getPage() +
                "每页条数:" + pageBean.getSize() + "数据集合长度:" + pageBean.getData().size() + "总数量:" + pageBean.getTotal() + "总页码:" + pageBean.getPageCount());
        return pageBean;
    }

    @Override
    public boolean addArticlePv(int aid) {
        return ad.updateArticlePv(aid) > 0;
    }
}
