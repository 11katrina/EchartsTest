package com.test.dao;

import com.test.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class testDao {
    public String test(String[] date, String number) {
        String message = null;
        ArrayList<String> list = search(date,number);
        if (!areAllFalse(list)) {
            message = "false";
            return message;
        } else if(!areAnyNotNull(list)){
            message = "null";
            return message;
        }else if(!areAllOther(list)){
            message = "other";
            return message;
        }else {
            int row;
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            ArrayList<Object> parms = new ArrayList<>();
            connection = DBUtil.getConnection();
            if (connection != null) {
                //判断是否插入病害信息
                if(!list.get(0).equals("0")){
                    if(list.get(0).equals("disease")){
                        StringBuffer sql = new StringBuffer("UPDATE disease SET disease = ? WHERE SoilNumber = ?");
                        parms.add(date[0]);
                        if(number.length()!=0){
                            parms.add(number);
                        }
                        row = DBUtil.execute(connection,preparedStatement, sql.toString(), parms.toArray());
                    }else {
                        StringBuffer sql = new StringBuffer("UPDATE disease SET other = ? WHERE SoilNumber = ?");
                        parms.add(date[1]);
                        if (number.length() != 0) {
                            parms.add(number);
                        }
                        row = DBUtil.execute(connection, preparedStatement, sql.toString(), parms.toArray());
                    }
                }
                if(!list.get(1).equals("0")){
                    if(list.get(1).equals("Pest")){
                        StringBuffer sql = new StringBuffer("UPDATE disease SET Pest = ? WHERE SoilNumber = ?");
                        parms.add(date[1]);
                        if(number.length()!=0){
                            parms.add(number);
                        }
                        row = DBUtil.execute(connection,preparedStatement, sql.toString(), parms.toArray());
                    }else {
                        StringBuffer sql = new StringBuffer("UPDATE disease SET other = ? WHERE SoilNumber = ?");
                        parms.add(date[1]);
                        if (number.length() != 0) {
                            parms.add(number);
                        }
                        row = DBUtil.execute(connection, preparedStatement, sql.toString(), parms.toArray());
                    }
                }
                message = "success";
            }
        }
        return message;
    }


    public ArrayList<String> search(String[] date,String number) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Flag = null;
        ArrayList<String> t = new ArrayList<>();
        ArrayList<Object> parms = new ArrayList<>();
        connection = DBUtil.getConnection();
        if (connection != null) {
            if (!date[0].equals("0")) {
                String s = "SELECT disease FROM `disease` WHERE SoilNumber = ?";
                parms.add(number);
                resultSet = DBUtil.execute(connection, preparedStatement, resultSet, s, parms.toArray());
                try {
                    if (resultSet.next()) {
                        String disease = resultSet.getString("disease");
                        if (disease.equals(date[0])) {
                            Flag = "false";//与原数据重复
                        } else {
                            Flag = "other";//有数据但不相同,可插入other
                        }
                    }//else代表原来的没数据,可插入disease
                    else {
                        Flag = "disease";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Flag = "null";
            }
            t.add(Flag);
            if (!date[1].equals("0")) {
                String s = "SELECT Pest FROM `disease` WHERE SoilNumber = ?";
                parms.add(number);
                resultSet = DBUtil.execute(connection, preparedStatement, resultSet, s, parms.toArray());
                try {
                    if (resultSet.next()) {
                        String Pest = resultSet.getString("Pest");
                        if (Pest.equals(date[1])) {
                            Flag = "false";//与原数据重复
                        } else {
                            Flag = "other";//有数据但不相同,可插入other
                        }
                    }
                    //else代表原来的没数据,可插入
                    else {
                        Flag = "Pest";
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Flag = "null";
            }
            t.add(Flag);
        }
        DBUtil.closeAll(connection, preparedStatement, resultSet);
        return t;
    }

    public static boolean areAllFalse(ArrayList<String> list) {
        for (String b : list) {
            if (b.equals("false")) {  // 如果有一个元素为 false，就返回 false
                return false;
            }
        }
        return true;
    }

    public static boolean areAnyNotNull(ArrayList<String> list) {
        for (String s : list) {
            if (!"null".equals(s)) {  // 如果有一个元素不等于 "null"，就返回 true
                return true;
            }
        }
        return false;  // 所有元素均等于 "null"
    }

    public static boolean areAllOther(ArrayList<String> list) {
        for (String s : list) {
            if (!"other".equals(s)) {  // 如果有一个元素不等于 "null"，就返回 true
                return true;
            }
        }
        return false;  // 所有元素均等于 "null"
    }

}
