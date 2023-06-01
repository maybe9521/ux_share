package com.bdqn.ux_share.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserMsgDto extends UserMsg implements Serializable {

    public UserMsgDto() {
        super();
    }

    public UserMsgDto(String userUname, String userPwd) {
        super(userUname, userPwd);
    }


    public UserMsgDto(String userUname, String userPwd, Date userCreateDate, String userPhone) {
        super(userUname, userPwd, userCreateDate, userPhone);
    }

    public UserMsgDto(Integer userId, String userNickname, String userUname, String userPwd, Integer userRole, Integer userState, Date userCreateDate, String userIcon, String userPhone, Integer userPoint, Integer userLevel, Integer userRootId, Integer userEx) {
        super(userId, userNickname, userUname, userPwd, userRole, userState, userCreateDate, userIcon, userPhone, userPoint, userLevel, userRootId, userEx);
    }

    public UserMsgDto(UserMsg loginUser) {
        this.setUserId(loginUser.getUserId());
        this.setUserNickname(loginUser.getUserNickname());
        this.setUserUname(loginUser.getUserUname());
        this.setUserPwd(loginUser.getUserPwd());
        this.setUserRole(loginUser.getUserRole());
        this.setUserState(loginUser.getUserState());
        this.setUserCreateDate(loginUser.getUserCreateDate());
        this.setUserIcon(loginUser.getUserIcon());
        this.setUserPhone(loginUser.getUserPhone());
        this.setUserPoint(loginUser.getUserPoint());
        this.setUserLevel(loginUser.getUserLevel());
        this.setUserRootId(loginUser.getUserRootId());
        this.setUserEx(loginUser.getUserEx());
    }

    @Override
    public Integer getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(Integer value) {
        super.setUserId(value);
    }

    @Override
    public String getUserNickname() {
        return super.getUserNickname();
    }

    @Override
    public void setUserNickname(String value) {
        super.setUserNickname(value);
    }

    @Override
    public String getUserUname() {
        return super.getUserUname();
    }

    @Override
    public void setUserUname(String value) {
        super.setUserUname(value);
    }

    @Override
    public String getUserPwd() {
        return super.getUserPwd();
    }

    @Override
    public void setUserPwd(String value) {
        super.setUserPwd(value);
    }

    @Override
    public Integer getUserRole() {
        return super.getUserRole();
    }

    @Override
    public void setUserRole(Integer value) {
        super.setUserRole(value);
    }

    @Override
    public Integer getUserState() {
        return super.getUserState();
    }

    @Override
    public void setUserState(Integer value) {
        super.setUserState(value);
    }

    @Override
    public Date getUserCreateDate() {
        return super.getUserCreateDate();
    }

    @Override
    public void setUserCreateDate(Date value) {
        super.setUserCreateDate(value);
    }

    @Override
    public String getUserIcon() {
        return super.getUserIcon();
    }

    @Override
    public void setUserIcon(String value) {
        super.setUserIcon(value);
    }

    @Override
    public String getUserPhone() {
        return super.getUserPhone();
    }

    @Override
    public void setUserPhone(String value) {
        super.setUserPhone(value);
    }

    @Override
    public Integer getUserPoint() {
        return super.getUserPoint();
    }

    @Override
    public void setUserPoint(Integer value) {
        super.setUserPoint(value);
    }

    @Override
    public Integer getUserLevel() {
        return super.getUserLevel();
    }

    @Override
    public void setUserLevel(Integer value) {
        super.setUserLevel(value);
    }

    @Override
    public Integer getUserRootId() {
        return super.getUserRootId();
    }

    @Override
    public void setUserRootId(Integer value) {
        super.setUserRootId(value);
    }

    @Override
    public Integer getUserEx() {
        return super.getUserEx();
    }

    @Override
    public void setUserEx(Integer value) {
        super.setUserEx(value);
    }

    @Override
    public String toString() {
        return "UserMsgDto{"+super.toString()+"}";
    }
}
