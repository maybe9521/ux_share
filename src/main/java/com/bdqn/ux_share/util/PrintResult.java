package com.bdqn.ux_share.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintResult {

    public static void print(HttpServletResponse resp,Object data){
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
           out = resp.getWriter();
           out.print(JSON.toJSONString(data));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 信息弹框打印
     * @param resp
     * @param message
     */
    public static void printInfo(HttpServletResponse resp,String  message){
        PrintResult.print(resp,Common.failed(message));
    }

    /**
     * 信息弹框打印并跳转
     * @param resp
     * @param message 提示信息
     * @Param path 跳转路径
     */
    public static void printInfo(HttpServletResponse resp,String  message,String path){

    }
}
