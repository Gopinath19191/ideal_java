/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.utils;

import com.defiance.ideal.exitMgmt.login.dto.ConnectionBeanTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 14026
 */
public class JdbcConnectionPool {

    private Connection connection = null;
    public Connection srcConnection = null;
    public Connection dstnConnection = null;
    public ConnectionBeanTO connectionBeanTO = null;
//    public JdbcConnectionPool(ConnectionBeanTO srcBean,ConnectionBeanTO dstnBean) {
//        try {
//            srcConnection=getJdbcConnection(srcBean);
//            dstnConnection=getJdbcConnection(dstnBean);
//        } catch (Exception e) {
//        }        
//    }

    /**
     * 
     * @param driverName
     * @param url
     * @param userName
     * @param password
     * @return 
     */
    public Connection getJdbcConnection(ConnectionBeanTO srcBean) throws Exception {
        try {
            connectionBeanTO = srcBean;
            Class.forName(srcBean.getDriverName()).newInstance();
            System.out.println("URL::" + srcBean.getUrl() + " , " + srcBean.getUserName() + " ," + srcBean.getPassword());
            connection = DriverManager.getConnection(srcBean.getUrl(), srcBean.getUserName(), srcBean.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
        return connection;
    }

    /**
     * 
     * @param srcTables
     * @param dstnTables
     * @return 
     */
    public List dataTransfer(List srcTables, List dstnTables)throws Exception {

        List<String> msg = new ArrayList<String>();
        try {
            int c = 0;
            int tableCount = 0;            
            
            for (Object tableName : srcTables) {
                String[] databaseName = srcConnection.getMetaData().getURL().split("//");
                String dbName = databaseName[1].split("/")[1];
                System.out.println("DBNAME" + dbName);
                PreparedStatement pstmt = srcConnection.prepareStatement("SELECT * FROM information_schema.`TABLES` T where table_schema='" + dbName + "' and table_name='" + tableName + "'");                
                ResultSet resultSet=pstmt.executeQuery();
                //System.out.println("QUERY:::"+resultSet.next());
                String result = tableName.toString();
                if (resultSet.next()) {
                    final PreparedStatement ps = srcConnection.prepareStatement("SELECT * FROM " + tableName);
                    //ps.setFetchSize(FETCH_SIZE);
                    final ResultSet rs = ps.executeQuery();
                    final ResultSetMetaData metaData = rs.getMetaData();
                    final int columnCount = metaData.getColumnCount();
                    databaseName = dstnConnection.getMetaData().getURL().split("//");
                    dbName = databaseName[1].split("/")[1];
                    System.out.println("DB::"+dbName);
                    pstmt = dstnConnection.prepareStatement("SELECT * FROM information_schema.`TABLES` T where table_schema='" + dbName + "' and table_name='" + dstnTables.get(tableCount) + "'");                    
                    //System.out.println("pstmt:::"+pstmt.toString());
                    resultSet=pstmt.executeQuery();
                    if (resultSet.next()) {
                        String insertSQL = "INSERT INTO " + dstnTables.get(tableCount) + " ";
                        while (rs.next()) {
                            StringBuffer columns = new StringBuffer("(");
                            StringBuffer params = new StringBuffer("(");
                            String updateSql = " on duplicate key update ";
                            for (int i = 0; i < columnCount; i++) {
                                columns.append(metaData.getColumnName(i + 1));
                                updateSql = updateSql + metaData.getColumnName(i + 1) + "=?";
                                params.append("?");
                                if ((i + 1) != columnCount) {
                                    columns.append(",");
                                    params.append(",");
                                    updateSql = updateSql + ",";
                                }
                            }
                            columns.append(")");
                            params.append(")");

                            final String sql = insertSQL + columns.toString() + " VALUES " + params.toString() + " " + updateSql;
                            //System.out.println("SQL:::" + sql);                                                

//        if (c >  0 && c % FETCH_SIZE == 0) {
//          System.out.println("Transfered " + c + " records.");
//        }

                            final PreparedStatement insertStatement = dstnConnection.prepareStatement(sql);
                            for (int i = 0; i < columnCount; i++) {
                                insertStatement.setObject(i + 1, rs.getObject(i + 1));
                                insertStatement.setObject(i + 1 + columnCount, rs.getObject(i + 1));
                            }
                            
                            insertStatement.execute();
                            //System.out.println("Query:::"+insertStatement.toString());
                            c++;
                            if (dstnConnection.getAutoCommit() == false) {
                                dstnConnection.commit();                                
                            }
                            insertStatement.close();
                        }
                        rs.close();
                        ps.close();                        
//                        result = result + " ";
                        result = c + " Records Transfered Successfully ";
                        msg.add(result);
                    }
                    else{
                        msg.add(dstnTables.get(tableCount) +" Table is Not Found in " + dbName);
                    }
                } else {
                    msg.add(tableName+" Table is Not Found in " + dbName);
                }
                tableCount += 1;
                c=0;

            }
            //System.out.println("Total transfered " + c + " records.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
        return msg;
    }

    public void closeConnection() throws Exception{
        try {
            srcConnection.close();
            dstnConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }

    }

    public void setConnection(Connection src, Connection dstn) throws Exception{
        try {
            this.srcConnection = src;
            this.dstnConnection = dstn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
    }

//    public static void main(String[] args) {
//        try {
//            JdbcConnectionPool connectionPool = new JdbcConnectionPool();
//            ConnectionBeanTO connectionBeanTO = new ConnectionBeanTO("com.mysql.jdbc.Driver", "jdbc:mysql://220.226.3.70:3306/finance", "panch", "panch123");
//            connectionPool.srcConnection = connectionPool.getJdbcConnection(connectionBeanTO);
//            String[] urls = connectionBeanTO.getUrl().split("//");
//            //System.out.println("URLS;"+urls[1].split("/")[1] );
//            connectionBeanTO = new ConnectionBeanTO("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/sample", "root", "root");
//            connectionPool.dstnConnection = connectionPool.getJdbcConnection(connectionBeanTO);
//
//            List src = new ArrayList();
//            List dst = new ArrayList();
//            src.add("dtl_subcon_costs");
//            src.add("Unit_direct_costs");
//
//            dst.add("dtl_subcon_costs");
//            dst.add("Unit_direct_costs");
//            List msg = connectionPool.dataTransfer(src, dst);
//            for (Object object : msg) {
//                System.out.println(object);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
