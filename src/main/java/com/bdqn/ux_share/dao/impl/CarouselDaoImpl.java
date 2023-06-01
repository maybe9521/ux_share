package com.bdqn.ux_share.dao.impl;

import com.bdqn.ux_share.dao.CarouselDao;
import com.bdqn.ux_share.pojo.ArticleCarousel;
import com.bdqn.ux_share.util.JdbcUtil;
import com.bdqn.ux_share.util.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 */
public class CarouselDaoImpl implements CarouselDao {
    @Override
    public List<ArticleCarousel> getAll() throws SQLException {
        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        List<ArticleCarousel> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleCarousel(rs.getInt("carousel_id"),
                        rs.getString("carousel_desc"),rs.getString("carousel_img"),
                        rs.getString("carousel_src"),rs.getInt("carousel_state")));
            }
        }
        return list;
    }

    @Override
    public int getCount() throws SQLException {
        int count = 0;
        String sql = "select count(1) as count from article_carousel;";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        if (rs != null){
            while (rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    @Override
    public ArticleCarousel getCarouselbyId(Integer carouselid) throws SQLException {
        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel where carousel = ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,carouselid);
        ArticleCarousel ac = new ArticleCarousel();
        if(rs != null){
            while(rs.next()){
                ac.setCarouselId(rs.getInt("carousel_id"));
                ac.setCarouselDesc(rs.getString("carousel_desc"));
                ac.setCarouselImg(rs.getString("carousel_img"));
                ac.setCarouselSrc(rs.getString("carousel_src"));
                ac.setCarouselState(rs.getInt("carousel_state"));
            }
        }
        return ac;
    }

    /**
     * 分页
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public List<ArticleCarousel> getACPage(Page<ArticleCarousel> page) throws SQLException {
        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel order by carousel_id asc limit ?,?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
        List<ArticleCarousel> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleCarousel(rs.getInt("carousel_id"),
                        rs.getString("carousel_desc"),rs.getString("carousel_img"),
                        rs.getString("carousel_src"), rs.getInt("carousel_state")));
            }
        }
        return list;
    }



    /**
     * 删除
     * @param carouselId
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(Integer carouselId) throws SQLException {
        String sql = "delete from article_carousel where carousel_id = ?;";
        return JdbcUtil.executeUpdate(sql,carouselId);
    }



    /**
     * 添加
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    @Override
    public int add(ArticleCarousel articleCarousel) throws SQLException {
        String sql="INSERT INTO article_carousel(`carousel_id`, `carousel_desc`, `carousel_img`, `carousel_src`, `carousel_state`) values(?,?,?,?,?);";
        return JdbcUtil.executeUpdate(sql,
                articleCarousel.getCarouselId(),
                articleCarousel.getCarouselDesc(),
                articleCarousel.getCarouselImg(),
                articleCarousel.getCarouselSrc(),
                articleCarousel.getCarouselState());
    }

    /**
     * 修改
     * @param articleCarousel
     * @return
     * @throws SQLException
     */
    @Override
    public int update(ArticleCarousel articleCarousel) throws SQLException {
        String sql ="update article_carousel set carousel_desc= ?,carousel_img=?,carousel_src=?,carousel_state=? where carousel_id=?;";
        return JdbcUtil.executeUpdate(sql,articleCarousel.getCarouselDesc(),
                articleCarousel.getCarouselImg(),articleCarousel.getCarouselSrc(),
                articleCarousel.getCarouselState());
    }

    @Override
    public List<ArticleCarousel> getTopAC(Integer carousel_id, int limit) throws SQLException {
        String sql = "select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel where carousel_id = ? order by carousel_id desc limit ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,carousel_id,limit);
        List<ArticleCarousel> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                ArticleCarousel ac = new ArticleCarousel();
                ac.setCarouselId(rs.getInt("carousel_id"));
                ac.setCarouselDesc(rs.getString("carousel_desc"));
                ac.setCarouselImg(rs.getString("carousel_img"));
                ac.setCarouselSrc(rs.getString("carousel_src"));
                ac.setCarouselState(rs.getInt("carousel_state"));
                list.add(ac);
            }
        }
        return list;
    }

    @Override
    public int getCount(Integer carouselId) throws SQLException {
        int count = 0;
        List<Object> list = new ArrayList<>();
        String sql = "select count(1) as count from article_carousel; ";
        if(carouselId != null && carouselId != 0){
            sql += " where ntid = ?;";
            list.add(carouselId);
        }
        System.out.println(sql+","+carouselId);

        ResultSet rs = JdbcUtil.executeQuery(sql,list.toArray());
        if(rs != null){
            while(rs.next()){
                count = rs.getInt("count");
            }
        }
        return count;
    }

    /**
     * 查询单个
     * @return
     * @throws SQLException
     */
    @Override
    public ArticleCarousel getArticleCarouselbyId(Integer carousel_id) throws SQLException {
        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel where carousel_id = ?;";
        ResultSet rs = JdbcUtil.executeQuery(sql,carousel_id);
        ArticleCarousel articleCarousel = new ArticleCarousel();
        if (rs!=null){
            while (rs.next()){
                articleCarousel.setCarouselId(rs.getInt("carousel_id"));
                articleCarousel.setCarouselDesc(rs.getString("carousel_desc"));
                articleCarousel.setCarouselImg(rs.getString("carousel_img"));
                articleCarousel.setCarouselSrc(rs.getString("carousel_src"));
                articleCarousel.setCarouselState(rs.getInt("carousel_state"));
            }
        }
        return articleCarousel;
    }

    @Override
    public List<ArticleCarousel> getACByPage(Page<ArticleCarousel> page, Integer carouselId) throws SQLException {
        String sql = "select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel;";// ";
        List<Object> params = new ArrayList<>();
        if(carouselId != null && carouselId != 0){
            sql += " where carousel_id = ? ";
            params.add(carouselId);
        }
        sql += "order by carousel_id asc limit ?,?";
        params.add((page.getPage()-1)*page.getSize());
        params.add(page.getSize());
        ResultSet rs = JdbcUtil.executeQuery(sql,params.toArray());
        System.out.println(sql+","+carouselId);
        List<ArticleCarousel> list = new ArrayList<>();
        if(rs != null){
            while(rs.next()){
                list.add(new ArticleCarousel(rs.getInt("carousel_id"),
                        rs.getString("carousel_desc"),rs.getString("carousel_img"),
                        rs.getString("carousel_src"), rs.getInt("carousel_state")));
            }
        }
        return list;
    }


    @Override
    public List<ArticleCarousel> getAllCarousel() throws SQLException {
        String sql = " SELECT `carousel_id`,`carousel_desc`,`carousel_img`,`carousel_src`,`carousel_state` FROM `uxshare`.`article_carousel` ";
        ResultSet rs = JdbcUtil.executeQuery(sql);
        ArticleCarousel articleCarousel = null;
        List<ArticleCarousel> list = null;
        if(rs!=null){
            list = new ArrayList<>();
            while (rs.next()){
                articleCarousel = new ArticleCarousel();
                articleCarousel.setCarouselId(rs.getInt("carousel_id"));
                articleCarousel.setCarouselDesc(rs.getString("carousel_desc"));
                articleCarousel.setCarouselImg(rs.getString("carousel_img"));
                articleCarousel.setCarouselSrc(rs.getString("carousel_src"));
                articleCarousel.setCarouselState(rs.getInt("carousel_state"));
                list.add(articleCarousel);
            }
        }
        return list;
    }
//    @Override
//    public List<ArticleCarousel> getAll() throws SQLException {
//        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel;";
//        ResultSet rs = JdbcUtil.executeQuery(sql);
//        List<ArticleCarousel> list = new ArrayList<>();
//        if(rs != null){
//            while(rs.next()){
//                list.add(new ArticleCarousel(rs.getInt("carousel_id"),
//                        rs.getString("carousel_desc"),rs.getString("carousel_img"),
//                        rs.getString("carousel_src"),rs.getInt("carousel_state")));
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public int getCount() throws SQLException {
//        int count = 0;
//        String sql = "select count(1) as count from article_carousel;";
//        ResultSet rs = JdbcUtil.executeQuery(sql);
//        if (rs != null){
//            while (rs.next()){
//                count = rs.getInt("count");
//            }
//        }
//        return count;
//    }
//
//    /**
//     * 分页
//     * @param page
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public List<ArticleCarousel> getACPage(Page<ArticleCarousel> page) throws SQLException {
//        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel order by carousel_id desc limit ?,?;";
//        ResultSet rs = JdbcUtil.executeQuery(sql,(page.getPage()-1)*page.getSize(),page.getSize());
//        List<ArticleCarousel> list = new ArrayList<>();
//        if(rs != null){
//            while(rs.next()){
//                list.add(new ArticleCarousel(rs.getInt("carousel_id"),
//                        rs.getString("carousel_desc"),rs.getString("carousel_img"),
//                        rs.getString("carousel_src"), rs.getInt("carousel_state")));
//            }
//        }
//        return list;
//    }
//
//
//
//    /**
//     * 删除
//     * @param carouselId
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public int delete(Integer carouselId) throws SQLException {
//        String sql = "delete from article_carousel where carousel_id = ?;";
//        return JdbcUtil.executeUpdate(sql,carouselId);
//    }
//
//
//
//    /**
//     * 添加
//     * @param articleCarousel
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public int add(ArticleCarousel articleCarousel) throws SQLException {
//        String sql="INSERT INTO article_carousel(`carousel_id`, `carousel_desc`, `carousel_img`, `carousel_src`, `carousel_state`) values(?,?,?,?,?);";
//        return JdbcUtil.executeUpdate(sql,
//                articleCarousel.getCarouselId(),
//                articleCarousel.getCarouselDesc(),
//                articleCarousel.getCarouselImg(),
//                articleCarousel.getCarouselSrc(),
//                articleCarousel.getCarouselState());
//    }
//
//    /**
//     * 修改
//     * @param articleCarousel
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public int update(ArticleCarousel articleCarousel) throws SQLException {
//        String sql ="update article_carousel set carousel_desc= ?,carousel_img=?,carousel_src=?,carousel_state=? where carousel_id=?;";
//        return JdbcUtil.executeUpdate(sql,articleCarousel.getCarouselDesc(),
//                articleCarousel.getCarouselImg(),articleCarousel.getCarouselSrc(),
//                articleCarousel.getCarouselState());
//    }
//
//    /**
//     * 查询单个
//     * @return
//     * @throws SQLException
//     */
//    @Override
//    public ArticleCarousel getArticleCarouselbyId(Integer carousel_id) throws SQLException {
//        String sql="select carousel_id,carousel_desc,carousel_img,carousel_src,carousel_state from article_carousel where carousel_id = ?;";
//        ResultSet rs = JdbcUtil.executeQuery(sql,carousel_id);
//        ArticleCarousel articleCarousel = new ArticleCarousel();
//        if (rs!=null){
//            while (rs.next()){
//                articleCarousel.setCarouselId(rs.getInt("carousel_id"));
//                articleCarousel.setCarouselDesc(rs.getString("carousel_desc"));
//                articleCarousel.setCarouselImg(rs.getString("carousel_img"));
//                articleCarousel.setCarouselSrc(rs.getString("carousel_src"));
//                articleCarousel.setCarouselState(rs.getInt("carousel_state"));
//            }
//        }
//        return articleCarousel;
//    }
}
