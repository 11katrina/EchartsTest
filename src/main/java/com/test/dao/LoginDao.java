package com.test.dao;

import com.test.pojo.User;
import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginDao {
    public User checkUser(String username,String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        Object[] objects = new Object[10];
        connection = DBUtil.getConnection();
        if(connection!=null){
            StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE username = ? and password = ?");
            if(username.length()!=0){
               objects[0] = username;
            }if(password.length()!=0){
                objects[1] = password;
            }
            resultSet = DBUtil.execute(connection,preparedStatement,resultSet,sql.toString(),objects);
           try{
               while (resultSet.next()){
                   user = new User();
                   user.setUsername(resultSet.getString("username"));
                   user.setPassword(resultSet.getString("password"));
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }finally {
               DBUtil.closeAll(connection,preparedStatement,resultSet);
           }
        }
        return user;
    }
}
