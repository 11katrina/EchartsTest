package com.test.dao;

import com.test.pojo.*;
import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EchartsDao {
    //病虫害echarts
    public List<Disease> DiseaseEcharts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Disease> diseases  = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            String sql = "SELECT * FROM disease";
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql,parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    Disease disease = new Disease();
                    disease.setSoilNumber(resultSet.getString("SoilNumber"));
                    disease.setDisease(resultSet.getString("disease"));
                    disease.setPest(resultSet.getString("Pest"));
                    disease.setOther(resultSet.getString("other"));
                    diseases.add(disease);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        return diseases;
    }

    //土壤健康echarts
    public List<HeavyMetalContent> ContentsEcharts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<HeavyMetalContent> contents  = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            String sql = "SELECT * FROM heavy_metal_content";
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql,parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    HeavyMetalContent heavyMetalContent = new HeavyMetalContent();
                    heavyMetalContent.setSoilNumber(resultSet.getLong("SoilNumber"));
                    heavyMetalContent.setAsC(resultSet.getDouble("As_C"));
                    heavyMetalContent.setCdC(resultSet.getDouble("Cd_C"));
                    heavyMetalContent.setHgC(resultSet.getDouble("Hg_C"));
                    heavyMetalContent.setZnC(resultSet.getDouble("Zn_C"));
                    heavyMetalContent.setCuC(resultSet.getDouble("Cu_C"));
                    contents.add(heavyMetalContent);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        return contents;
    }

    //土壤水分echarts
    public List<Soilmoisture> soilmoistureEcharts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Soilmoisture> soilmoisture  = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            String sql = "SELECT * FROM soilmoisture";
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql,parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    Soilmoisture soilmoisture1 = new Soilmoisture();
                    soilmoisture1.setSoilNumber(resultSet.getString("SoilNumber"));
                    soilmoisture1.setSoilMoisture(resultSet.getString("SoilMoisture"));
                    soilmoisture.add(soilmoisture1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        return soilmoisture;
    }

    public List<Climate> climateEcharts(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Climate> climates  = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if(connection!=null){
            String sql = "SELECT * FROM climate";
            resultSet = DBUtil.execute(connection, preparedStatement, resultSet, sql,parms.toArray());
            try {
                while (resultSet.next()) {
                    //查询到所有数据
                    Climate climate = new Climate();
                    climate.setMonth(resultSet.getLong("month"));
                    climate.setTemperature(resultSet.getDouble("temperature"));
                    climate.setHumidity(resultSet.getLong("humidity"));
                    climate.setPrecipitation(resultSet.getDouble("precipitation"));
                    climates.add(climate);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeAll(connection, preparedStatement, resultSet);
            }
        }
        return climates;
    }

}
