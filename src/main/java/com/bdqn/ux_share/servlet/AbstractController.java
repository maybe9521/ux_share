package com.bdqn.ux_share.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 公共的Servlet抽象类
 * Created by bdqn on 2016/4/21.
 */
public abstract class AbstractController extends HttpServlet {


    public abstract Class getServletClass();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        //method名称
    	String actionIndicator = req.getParameter("opr");
        Method method = null;
        Object result = null;
        try {
            if (actionIndicator == null) {//没有opr参数
                result = execute(req, resp);
            } else {
            	//获取opr的参数，调用对应类的方法
                method = getServletClass().getDeclaredMethod(actionIndicator, HttpServletRequest.class, HttpServletResponse.class);
                result = method.invoke(this, req, resp);
            }
            //对方法的返回值进行判断，进行路由分配
            toView(req, resp, result);
        } catch (NoSuchMethodException e) {
            String viewName = "/404.html";
            req.getRequestDispatcher(viewName).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String viewName = "/500.html";
            req.getRequestDispatcher(viewName).forward(req, resp);
        }
    }

    protected void toView(HttpServletRequest req, HttpServletResponse resp, Object result) throws IOException, ServletException {
        if (result!=null) {
            if (result instanceof String) {
            	String key = "redirect:";
            	int index = ((String) result).indexOf("redirect:");
            	if(index != -1){//重定向
            		String viewName = result.toString().substring(index+key.length()) + ".jsp";
            		resp.sendRedirect(viewName);
            	}else{//转发
            		String viewName = result.toString() + ".jsp";
                    req.getRequestDispatcher(viewName).forward(req, resp);
            	}
            } else {//json
        		write(result,resp);
            }
        }
    }
    
    //回到首页
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        return "pre/index";
    }
    //响应内容
    private void print(String msg,HttpServletResponse response){
        PrintWriter writer=null;
		try {
            if(null != response){
                writer=response.getWriter();
                writer.print(msg);
                writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            writer.close();
        }
    }
    /**
     * 输出json格式
     * @param obj
     * @param response
     */
    public void write(Object obj,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		String json = JSONObject.toJSONString(obj);
		print(json,response);
	}
}
