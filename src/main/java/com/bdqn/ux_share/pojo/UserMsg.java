package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 */
public class UserMsg implements Serializable {
    private Integer userId;
    private String userNickname;
    private String userUname;
    private String userPwd;
    private Integer userRole;
    private Integer userState;
    private Date userCreateDate;
    private String userIcon;
    private String userPhone;
    private Integer userPoint;
    private Integer userLevel;
    private Integer userRootId;
    private Integer userEx;

    private Integer sumArticle; //用户所发文章总数
    private Integer sumLike;    //用户所发文章共获赞
    private Integer sumAtt;    //用户关注总数时

    private Integer collectAtt;// 用户收藏数量


    private boolean loginType = true;


    public UserMsg(Integer userId, String userUname, String userPwd, Integer userRole, Integer userState) {
        this.userId = userId;
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userState = userState;
    }

    public UserMsg(int userId, String name, String userUname, String userPwd, int userRole, int userState) {
        this.userId = userId;
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userState = userState;
    }

    public UserMsg(int id, String name, int role,int state) {
        this.userId =id;
        this.userUname =name;
        this.userRole = role;
        this.userState = state;
    }


    public UserMsg(String userNickname, String userIcon, String userUname) {
        this.userNickname = userNickname;
        this.userIcon = userIcon;
        this.userUname = userUname;
    }

    public Integer getCollectAtt() {
        return collectAtt;
    }

    public void setCollectAtt(Integer collectAtt) {
        this.collectAtt = collectAtt;
    }

    public boolean isLoginType() {
        return loginType;
    }

    public void setLoginType(boolean loginType) {
        this.loginType = loginType;
    }


    public Integer getSumAtt() {
        return sumAtt;
    }

    public void setSumAtt(Integer sumAtt) {
        this.sumAtt = sumAtt;
    }

    public Integer getSumArticle() {
        return sumArticle;
    }

    public void setSumArticle(Integer sumArticle) {
        this.sumArticle = sumArticle;
    }

    public Integer getSumLike() {
        return sumLike;
    }

    public void setSumLike(Integer sumLike) {
        this.sumLike = sumLike;
    }

    public UserMsg() {
    }

    public UserMsg(String userUname, String userPwd) {
        this.userUname = userUname;
        this.userPwd = userPwd;
    }

    public UserMsg(String userPwd, String userPhone, boolean loginType) {
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.loginType = loginType;
    }

    public UserMsg(String userUname, String userPwd, Integer userRole) {
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
    }

    public UserMsg(String userUname, String userPwd, Date userCreateDate, String userPhone) {
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userCreateDate = userCreateDate;
        this.userPhone = userPhone;
    }

    public UserMsg(Integer userId, String userNickname, String userUname, String userPwd, Integer userRole, Integer userState, Date userCreateDate, String userIcon, String userPhone, Integer userPoint, Integer userLevel, Integer userRootId, Integer userEx) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userState = userState;
        this.userCreateDate = userCreateDate;
        this.userIcon = userIcon;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.userLevel = userLevel;
        this.userRootId = userRootId;
        this.userEx = userEx;
    }



    public UserMsg(Integer userId, String userNickname, String userUname, String userPwd, Integer userRole, Integer userState, Date userCreateDate, String userIcon, String userPhone, Integer userPoint, Integer userLevel, Integer userRootId) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userState = userState;
        this.userCreateDate = userCreateDate;
        this.userIcon = userIcon;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.userLevel = userLevel;
        this.userRootId = userRootId;
    }

    public UserMsg(String userNickname, String userUname, String userPwd, Integer userRole, Integer userState, Date userCreateDate, String userIcon, String userPhone, Integer userPoint, Integer userLevel, Integer userRootId, Integer userEx) {
        this.userNickname = userNickname;
        this.userUname = userUname;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userState = userState;
        this.userCreateDate = userCreateDate;
        this.userIcon = userIcon;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.userLevel = userLevel;
        this.userRootId = userRootId;
        this.userEx = userEx;
    }




    public UserMsg(int userId, String userNickname, String userUname, String userPwd) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userUname = userUname;
        this.userPwd = userPwd;
    }

    public UserMsg(Integer userId, String userNickname) {
        this.userId = userId;
        this.userNickname = userNickname;
    }

    /**
     * Get 用户编号 用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Set 用户编号 用户编号
     */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    /**
     * Get 用户昵称 用户昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * Set 用户昵称 用户昵称
     */
    public void setUserNickname(String value) {
        this.userNickname = value;
    }

    /**
     * Get 用户账号 用户账号
     */
    public String getUserUname() {
        return userUname;
    }

    /**
     * Set 用户账号 用户账号
     */
    public void setUserUname(String value) {
        this.userUname = value;
    }

    /**
     * Get 用户密码 用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * Set 用户密码 用户密码
     */
    public void setUserPwd(String value) {
        this.userPwd = value;
    }

    /**
     * Get 用户类别 用户类别(1:普通用户,2:管理员)
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * Set 用户类别 用户类别(1:普通用户,2:管理员)
     */
    public void setUserRole(Integer value) {
        this.userRole = value;
    }

    /**
     * Get 用户状态 用户状态(1:可用,2:禁用);
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * Set 用户状态 用户状态(1:可用,2:禁用);
     */
    public void setUserState(Integer value) {
        this.userState = value;
    }

    /**
     * Get 注册时间 用户创建时间
     */
    public Date getUserCreateDate() {
        return userCreateDate;
    }

    /**
     * Set 注册时间 用户创建时间
     */
    public void setUserCreateDate(Date value) {
        this.userCreateDate = value;
    }

    /**
     * Get 用户头像 用户上传或者默认头像
     */
    public String getUserIcon() {
        return userIcon;
    }

    /**
     * Set 用户头像 用户上传或者默认头像
     */
    public void setUserIcon(String value) {
        this.userIcon = value;
    }

    /**
     * Get 用户电话 用户电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * Set 用户电话 用户电话
     */
    public void setUserPhone(String value) {
        this.userPhone = value;
    }

    /**
     * Get 用户积分 积分
     */
    public Integer getUserPoint() {
        return userPoint;
    }

    /**
     * Set 用户积分 积分
     */
    public void setUserPoint(Integer value) {
        this.userPoint = value;
    }

    /**
     * Get 用户等级 用户等级
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * Set 用户等级 用户等级
     */
    public void setUserLevel(Integer value) {
        this.userLevel = value;
    }

    /**
     * Get 用户权限 权限id
     */
    public Integer getUserRootId() {
        return userRootId;
    }

    /**
     * Set 用户权限 权限id
     */
    public void setUserRootId(Integer value) {
        this.userRootId = value;
    }

    /**
     * Get 等级经验 等级经验
     */
    public Integer getUserEx() {
        return userEx;
    }

    /**
     * Set 等级经验 等级经验
     */
    public void setUserEx(Integer value) {
        this.userEx = value;
    }

    @Override
    public String toString() {
        return "UserMsg{" +
                "userId=" + userId +
                ", userNickname='" + userNickname + '\'' +
                ", userUname='" + userUname + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userRole=" + userRole +
                ", userState=" + userState +
                ", userCreateDate=" + userCreateDate +
                ", userIcon='" + userIcon + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPoint=" + userPoint +
                ", userLevel=" + userLevel +
                ", userRootId=" + userRootId +
                ", userEx=" + userEx +
                '}';
    }
}
