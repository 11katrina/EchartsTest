package com.test.dao;

import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//删除病虫害
public class DeleteDao {
    public int DeleteDisease(String Date[],String number){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int row = 0;
        int rows = 0;
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            for(String s:Date) {
                StringBuffer sql = new StringBuffer("UPDATE disease SET ");
                if (s.length() != 0) {
                    sql.append(s +" = null WHERE SoilNumber = ?");
                }
                if (number.length() != 0) {
                    parms.add(number);
                }
                row = DBUtil.execute(connection,preparedStatement, sql.toString(), parms.toArray());
                rows +=row;
            }
        }
        DBUtil.closeAll(connection,preparedStatement,resultSet);
        return rows;
    }
}
