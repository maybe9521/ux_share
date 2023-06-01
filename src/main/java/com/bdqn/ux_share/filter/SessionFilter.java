package com.bdqn.ux_share.filter;

import com.bdqn.ux_share.pojo.UserMsg;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 会话过滤器
 */
@WebFilter("/backendewe/*")
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("会话权限过滤器开启...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("安全过滤");
        //1. 会话判断
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("loginUser") !=null) {
            Object obj = session.getAttribute("loginUser");
            if (obj instanceof UserMsg) {
                UserMsg loginUser = (UserMsg) obj;
                if (loginUser != null) {
                    //2. 权限判断
                    if (loginUser.getUserRole() == 2) {// 如果 user_role =2 ，则是管理员账户进行放行操作
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("没有权限,请登录");
                        response.sendRedirect("/front/index.html");
                    }
                } else {
                    response.sendRedirect("/front/index.html");
                }
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("会话权限过滤器销毁...");
    }
}
