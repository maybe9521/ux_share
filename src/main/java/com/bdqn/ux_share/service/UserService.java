package com.bdqn.ux_share.service;

public interface UserService {
    boolean addUserAtt(int uid,int auid);
    boolean exitUserAtt(int uid,int auid);
    boolean exitUserCollect(int aid,int uid);
    boolean addUserCollect(int uid,int aid);
    boolean addUserLike(int aid);
}
