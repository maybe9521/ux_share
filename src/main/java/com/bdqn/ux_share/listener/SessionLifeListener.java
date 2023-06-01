package com.bdqn.ux_share.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionLifeListener implements HttpSessionListener,HttpSessionAttributeListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话创建："+se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("会话销毁："+se.getSession().getId());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("会话新增属性："+se.getSession().getId());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("会话删除属性："+se.getSession().getId());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("会话替换属性："+se.getSession().getId());
    }
}
