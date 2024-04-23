package com.test.dao;

import com.test.pojo.Disease1;
import com.test.pojo.InsectPest;
import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {
    public List<Object> SearchInsect(String number){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Object> measure = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        //查病害
        if(connection!=null){
            StringBuffer sql = new StringBuffer("SELECT measure from disease1 WHERE disease_Name = (SELECT disease FROM disease WHERE SoilNumber =  ");
            if (number.length() != 0) {
                sql.append(number+")");
            }
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql.toString(), parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    Disease1 disease1 = new Disease1();
                    disease1.setMeasure(resultSet.getString("measure"));
                    measure.add(disease1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        connection = DBUtil.getConnection();
        //查虫害
        if(connection!=null){
            StringBuffer sql = new StringBuffer("SELECT measure from insect_pest WHERE Pest_Name = (SELECT Pest FROM disease WHERE SoilNumber =  ");
            if (number.length() != 0) {
                sql.append(number+")");
            }
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql.toString(), parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    InsectPest insectPest = new InsectPest();
                    insectPest.setMeasure(resultSet.getString("measure"));
                    measure.add(insectPest);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        return measure;
    }
}
