package com.bdqn.ux_share.pojo;

import java.io.Serializable;

/**
 * 轮播图表 轮播
 */
public class ArticleCarousel implements Serializable
{
    private Integer carouselId;
    private String carouselDesc;
    private String carouselImg;
    private String carouselSrc;
    private Integer carouselState;

    public ArticleCarousel()
    {
    }

    public ArticleCarousel(Integer carouselId, String carouselDesc, String carouselImg, String carouselSrc, Integer carouselState) {
        this.carouselId = carouselId;
        this.carouselDesc = carouselDesc;
        this.carouselImg = carouselImg;
        this.carouselSrc = carouselSrc;
        this.carouselState = carouselState;
    }



    /**
     * Get 轮播编号 轮播编号
     */
    public Integer getCarouselId()
    {
        return carouselId;
    }
    /**
     * Set 轮播编号 轮播编号
     */
    public void setCarouselId(Integer value)
    {
        this.carouselId = value;
    }
    /**
     * Get 轮播描述 轮播图描述
     */
    public String getCarouselDesc()
    {
        return carouselDesc;
    }
    /**
     * Set 轮播描述 轮播图描述
     */
    public void setCarouselDesc(String value)
    {
        this.carouselDesc = value;
    }
    /**
     * Get 图片路径 轮播图片路径
     */
    public String getCarouselImg()
    {
        return carouselImg;
    }
    /**
     * Set 图片路径 轮播图片路径
     */
    public void setCarouselImg(String value)
    {
        this.carouselImg = value;
    }
    /**
     * Get 跳转路径 轮播跳转路径
     */
    public String getCarouselSrc()
    {
        return carouselSrc;
    }
    /**
     * Set 跳转路径 轮播跳转路径
     */
    public void setCarouselSrc(String value)
    {
        this.carouselSrc = value;
    }
    /**
     * Get 轮播状态 启用或未启用，1/2，默认为1
     */
    public Integer getCarouselState()
    {
        return carouselState;
    }
    /**
     * Set 轮播状态 启用或未启用，1/2，默认为1
     */
    public void setCarouselState(Integer value)
    {
        this.carouselState = value;
    }


    @Override
    public String toString() {
        return "ArticleCarousel{" +
                "carouselId=" + carouselId +
                ", carouselDesc='" + carouselDesc + '\'' +
                ", carouselImg='" + carouselImg + '\'' +
                ", carouselSrc='" + carouselSrc + '\'' +
                ", carouselState=" + carouselState +
                '}';
    }
}

