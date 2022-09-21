/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author 14053
 */
public class Config {

    private static Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("ideal");
//        DataSource dataSource = (DataSource) context.lookup("java:comp/env/ideal");
//         DataSource dataSource = (DataSource) context.lookup("ideal_keyedin");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int getParentId(String parentName) {
        int id = 0;
        Connection connection = getConnection();
        if (connection != null) {
            String sql = "SELECT id FROM configuration_values WHERE configuration_key = '" + parentName + "'";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public static LinkedHashMap<String, String> getChildElements(int parentId) {
        LinkedHashMap<String, String> children = new LinkedHashMap<String, String>();
        String key, value;
        Connection connection = getConnection();
        if (connection != null) {
            String sql = "SELECT configuration_key AS k,configuration_value AS v FROM configuration_values WHERE parent_id = '" + parentId + "'";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    key = rs.getString("k");
                    value = rs.getString("v");
                    children.put(key, value);
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return children;
    }


    public static String getChildElement(int parentId,String childKey) {
        String child = null;
        Connection connection = getConnection();
        if (connection != null) {
            String sql = "SELECT configuration_value AS v FROM configuration_values WHERE parent_id = '"+parentId+"' AND configuration_key = '"+childKey+"'";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    child = rs.getString("v");
                }   
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return child;
    }

}
