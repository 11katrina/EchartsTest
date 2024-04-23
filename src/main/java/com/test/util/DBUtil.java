package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class DBUtil {
    private static final Properties PROPERTIES = new Properties();//存储配置文件的map
    static {
        InputStream is = DBUtil.class.getResourceAsStream("/db.properties");
        try {
            PROPERTIES.load(is);//通过流，将配置文件内容加载到properties集合
            Class.forName(PROPERTIES.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取数据库的连接
    public static Connection getConnection(){
        Connection collection = null;
        try {
            collection =  DriverManager.getConnection(PROPERTIES.getProperty("url"),PROPERTIES.getProperty("username"),PROPERTIES.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }
    //编写查询公共方法
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] objects){
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < objects.length; i++) {
            try {
                preparedStatement.setObject(i+1,objects[i]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
    //编写增删改的公共方法
    public static int execute(Connection connection, PreparedStatement preparedStatement,String sql,Object[] objects){
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < objects.length; i++) {
            try {
                preparedStatement.setObject(i+1,objects[i]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        int upDateRows = 0;
        try {
            upDateRows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return upDateRows;
    }
    //关闭数据库的连接
    public static boolean closeAll(Connection connection, Statement statement, ResultSet resultSet){
        boolean flag = true;
        if(resultSet!=null){
            try {
                resultSet.close();
                //GC回收
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(statement!=null){
            try {
                statement.close();
                //GC回收
                statement = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(connection!=null){
            try {
                connection.close();
                //GC回收
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
