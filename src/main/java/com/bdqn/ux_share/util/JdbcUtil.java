package com.bdqn.ux_share.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC的工具类：
 *      1、连接
 *      2、关闭
 */
public class JdbcUtil {
    public static final String DATA_FILE = "database.properties";
    public static String DRIVER_NAME;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static ThreadLocal<Connection> conn = new ThreadLocal<Connection>();

    static{
        init();
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        try {
            // 创建属性集对象
            Properties properties = new Properties();
            // 加载文件内容
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream(DATA_FILE));
            // 读取内容
            DRIVER_NAME = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection(){
        try {
            if(conn.get() == null){
                conn.set(DriverManager.getConnection(URL,USERNAME,PASSWORD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn.get();
    }

    /**
     * 关闭连接
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs){
        try {
            if(rs != null)rs.close();
            if(stmt != null)stmt.close();
            if(conn != null){conn.close();JdbcUtil.conn.remove();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql,Object... params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if(params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1,params[i]);
                }
            }
            result = pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改
     * @param sql
     * @param params
     * @return
     */
    public static ResultSet executeQuery(String sql, Object... params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if(params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1,params[i]);
                }
            }
            rs = pstmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static SqlSession getSqlsession() {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 取得Sqlsession对象
        return sqlSessionFactory.openSession();
    }
}
