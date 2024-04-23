package com.test.dao;

import com.test.pojo.Disease;
import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
    public List<Disease> QueryNumber(String number){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Disease> diseases = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            String sql = "SELECT * FROM `disease` WHERE SoilNumber = ?";
            if(number.length()!=0){
                parms.add(number);
            }
            resultSet = DBUtil.execute(connection,preparedStatement,resultSet,sql,parms.toArray());
            try{
            while (resultSet.next()) {
                Disease disease = new Disease();
                disease.setSoilNumber(resultSet.getString("SoilNumber"));
                disease.setDisease(resultSet.getString("disease"));
                disease.setPest(resultSet.getString("Pest"));
                disease.setOther(resultSet.getString("other"));
                diseases.add(disease);
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                DBUtil.closeAll(connection,preparedStatement,resultSet);
            }
        }
        return diseases;
    }
}
