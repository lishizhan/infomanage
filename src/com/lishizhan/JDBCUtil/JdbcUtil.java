package com.lishizhan.JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC 工具类
 *
 * @Author : 黎仕展
 * @Description : 对jdbc注册,关闭资源等操作的封装
 * @Date : 2020/5/11/0011
 */
public class JdbcUtil {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /**
     * 读取一次配置文件，获取值
     */
    static {
        try {
            //1.创建Properties集合类获取配置文件中的数据
            Properties properties = new Properties();

            //获取src路径的文件
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            URL url1 = classLoader.getResource("com/lishizhan/JDBCUtil/jdbcdata.properties");
            String path = url1.getPath();//返回路径字符串
            //路径编码设置
            path = java.net.URLDecoder.decode(path, "utf-8");
            //2.加载文件
            properties.load(new FileReader(path));

            //3.获取值，赋值
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接对象：使用一种配置文件的方式。
     *
     * @return Connection；
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     *
     * @param connection：连接对象
     * @param statement：数据库操作对象
     * @param resultSet：查询结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
