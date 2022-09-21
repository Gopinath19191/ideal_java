/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SwipeToAttendance;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author 16363
 */
public class AttendanceMaitenance {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    //
    static String sLogFile = "";
    static Logger logger = Logger.getLogger("MyLog");
    static FileHandler fh;
    static SimpleFormatter formatter = new SimpleFormatter();

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException {

        try {
            String configFile = new File("AttendanceDataConfiguration.properties").getAbsolutePath();

            FileReader fileInput = new FileReader(configFile);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            //
            strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
            strUserName = properties.getProperty("USER");
            strPassword = properties.getProperty("PASSWORD");
            sLogFile = properties.getProperty("LOGFILE").replace("\\", "/");
            //
            // This block configure the logger with handler and formatter  
            fh = new FileHandler(sLogFile, true);
            logger.addHandler(fh);
            fh.setFormatter(formatter);
            //
            logger.info(configFile);
            logger.info("Configurations data read successful....");
            //
            Integer nDays = 1;
            Integer nLocation = 1;

            if (args != null && args.length > 0) {
                logger.info("JAR file execution with parameter starts...");
                if (args.length == 2) {
                    nDays = Integer.parseInt(args[0]) > 0 ? Integer.parseInt(args[0]) : 1;
                    if (nDays > 1) {
                        logger.info("Number of days to be pre-phoned " + nDays);
                    }
                    nLocation = Integer.parseInt(args[1]);
                    if (nLocation > 0) {
                        logger.info("Location to be processed " + nLocation);
                    }
                } else if (args.length == 1) {
                    nDays = Integer.parseInt(args[0]) > 0 ? Integer.parseInt(args[0]) : 1;
                    if (nDays > 1) {
                        logger.info("Number of days to be pre-phoned " + nDays);
                    }
                }
            } else {
                logger.info("JAR file execution without parameter starts...");
            }
            //
            logger.info("Source to Attendance temp starts...");
            //
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //
            Class.forName("org.gjt.mm.mysql.Driver");
            //
            Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);

            objTargetConnection.setAutoCommit(false);
            Connection objSourceConnection = null;

            if (nLocation == 0 || nLocation == 1) {
                objSourceConnection = DriverManager.getConnection(properties.getProperty("SRC_CONN_CHENNAI"));

                try {

                    if (objSourceConnection == null) {
                        logger.info("Connection Failure to Chennai");
                    } else {
                        logger.info("Successfully Connected to Chennai");                        
                        //
                        String sQuery = "INSERT INTO attendance_temp (emp_id,attendance_date,check_in_time,in_out,door,remarks,source,location)"
                                        +" SELECT * FROM (SELECT ? AS emp_id,? AS attendance_date ,?,? as IO,?,?,'CRON','1' as chennai ) AS tmp "
                                        + " WHERE NOT EXISTS (SELECT emp_id FROM attendance_temp WHERE emp_id = ? AND attendance_date = ? "
                                        + " AND check_in_time = ? AND in_out = ? AND door = ? AND remarks = ? "
                                        + " AND source = 'CRON' AND location='1') LIMIT 1" ;
                        
                        PreparedStatement prepInsertStmt = objTargetConnection.prepareStatement(sQuery); 
                        
                        String sEmpQry = "SELECT  EmpID, Date, Time, InOut, GateDesc, Remark "
                                + " FROM Daily_Data1 where Date = DATEADD(day,datediff(day, ? ,GETDATE()),0) AND EmpID IS NOT NULL";
                        //
                        PreparedStatement psEmpQry = objSourceConnection.prepareStatement(sEmpQry);
                        psEmpQry.setInt(1, nDays);
                        //
                        ResultSet rs = psEmpQry.executeQuery();
                        
                        String empId = null;
                        String attendanceDate = null;
                        String checkInTime = null;
                        String inOut = null;
                        String door = null;
                        String remark = null;

                        while ((rs != null) && (rs.next())) {                            
                            empId = Integer.toString(Integer.parseInt(rs.getString(1)));
                            attendanceDate = rs.getString(2);
                            checkInTime = rs.getString(3);
                            inOut = rs.getString(4);
                            door = rs.getString(5);
                            remark = rs.getString(6);
                            //
                            prepInsertStmt.setString(1,empId);
                            prepInsertStmt.setString(2,attendanceDate);
                            prepInsertStmt.setString(3,checkInTime);
                            prepInsertStmt.setString(4,inOut);
                            prepInsertStmt.setString(5,door);
                            prepInsertStmt.setString(6,remark);
                            prepInsertStmt.setString(7,empId);
                            prepInsertStmt.setString(8,attendanceDate);
                            prepInsertStmt.setString(9,checkInTime);
                            prepInsertStmt.setString(10,inOut);
                            prepInsertStmt.setString(11,door);
                            prepInsertStmt.setString(12,remark);
                            //
                            System.out.println("  "+prepInsertStmt);
                            prepInsertStmt.execute();
                            //
                            //System.out.println("testing "+empId+" "+attendanceDate);
                            prepInsertStmt.clearParameters();
                        }
                        prepInsertStmt.close();
                        psEmpQry.close();
                    }
                    //
                    objTargetConnection.commit();

                } catch (Exception e) {
                    objTargetConnection.rollback();
                    System.out.println("error "+e);
                    logger.info("Error at Chennai Source " + e);

                } finally {
                    objTargetConnection.close();
                    objSourceConnection.close();
                }
            }

            if (nLocation == 0 || nLocation == 2) {
                //
                objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
                objTargetConnection.setAutoCommit(false);
                //
                objSourceConnection = DriverManager.getConnection(properties.getProperty("SRC_CONN_PUNE"));
                //
                try {
                    if(objSourceConnection == null)
                        logger.info("Connection Failure to Pune");
                    else
                    {
                        logger.info("Successfully Connected to Pune");                                            
                        //
                        String sQuery = "INSERT INTO attendance_temp (emp_id,attendance_date,check_in_time,in_out,door,source,location)"
                                        +" SELECT * FROM (SELECT ? AS emp_id,? AS attendance_date ,?,? as IO,?,'CRON','2' as pune ) AS tmp "
                                        + " WHERE NOT EXISTS (SELECT emp_id FROM attendance_temp WHERE emp_id = ? AND attendance_date = ? "
                                        + " AND check_in_time = ? AND in_out = ? AND door = ?"
                                        + " AND source = 'CRON' AND location='2') LIMIT 1" ;
                        
                        PreparedStatement prepInsertStmt = objTargetConnection.prepareStatement(sQuery); 
                        // New query for pune scess card config
                        String empQry = "SELECT Empid , convert(varchar,attendate, 23), chkTime, "
                                        +"door_name, "
                                        +"Inout FROM dbo.ATTENDEMPDET "
                                        +"WHERE attendate = dateadd(day,datediff(day,?,GETDATE()),0) AND Empid IS NOT NULL";
                        //System.out.println("prepEmpStmt "+empQry);
                        //System.out.println("prepEmpStmt "+sQuery);
                        //String empQry = "SELECT Emp_code, Log_Date, (convert(varchar,Log_Date, 23)+' '+convert(varchar, Log_Time, 120)) as dateTim,"
                        //                +" Device_name, case when Direction = 'out' then '2' else '1' end as InOut FROM AttendancePunch WHERE"
                        //                +" Log_Date = dateadd(day,datediff(day,?,GETDATE()),0) AND Emp_code IS NOT NULL";
                        
                        PreparedStatement prepEmpStmt = objSourceConnection.prepareStatement(empQry); 
                        prepEmpStmt.setInt(1, nDays);
                        //
                        //System.out.println("prepEmpStmt "+empQry);
                        ResultSet rs = prepEmpStmt.executeQuery();
                        //
                        String employeeNumber = null;
                        String attendate = null;
                        String chkTime = null;
                        String door_name = null;
                        String Inout = null;
                        //
                        while ((rs != null) && (rs.next())) 
                        {
                            employeeNumber = rs.getString(1).trim();
                            attendate = rs.getString(2);
                            chkTime = rs.getString(3);
                            door_name = rs.getString(4);
                            Inout = rs.getString(5);
                            //
                            if(Inout.equals("O"))
                                Inout = "2";
                            else if(Inout.equals("I"))
                               Inout = "1";                            
                            //
                            prepInsertStmt.setString(1,employeeNumber);
                            prepInsertStmt.setString(2,attendate);
                            prepInsertStmt.setString(3,chkTime);
                            prepInsertStmt.setString(4,Inout);
                            prepInsertStmt.setString(5,door_name);
                            prepInsertStmt.setString(6,employeeNumber);
                            prepInsertStmt.setString(7,attendate);
                            prepInsertStmt.setString(8,chkTime);
                            prepInsertStmt.setString(9,Inout);
                            prepInsertStmt.setString(10,door_name);
                            //
                            prepInsertStmt.execute();
                            //
                            prepInsertStmt.clearParameters();
                        }   
                        prepInsertStmt.close();
                        prepEmpStmt.close();
                    } 
                    //
                    objTargetConnection.commit();      
                } catch (Exception e) {
                    objTargetConnection.rollback();
                    logger.info("Error at Pune Source " + e.getMessage());
                } finally {
                    objTargetConnection.close();
                    objSourceConnection.close();
                }
            }
            //   
            logger.info("Source to Attendance temp ends.");
            //
            logger.info("Attendance temp to Employee Attendance starts..");
            objTargetConnection = null;
            //
            try {
                objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
                objTargetConnection.setAutoCommit(false);

                if (objTargetConnection != null) {
                    logger.info("Successfully Connected to Target Database");
                    List<String> lstEmpNumbers = null;
                    
                    String empNumberQry = "SELECT DISTINCT Emp_id FROM attendance_temp "
                            + " WHERE attendance_date = DATE_ADD(CURDATE(), INTERVAL -? day) ";
                    
                    PreparedStatement psGetEmpNumbers = objTargetConnection.prepareStatement(empNumberQry);
                    psGetEmpNumbers.setInt(1, nDays);
                    //
                    ResultSet rsEmpNumbers = psGetEmpNumbers.executeQuery();
                    if (rsEmpNumbers != null) {
                        lstEmpNumbers = new ArrayList<String>();
                        while (rsEmpNumbers.next()) {
                            lstEmpNumbers.add(rsEmpNumbers.getString(1));
                        }
                    }

                    if (lstEmpNumbers != null && lstEmpNumbers.size() > 0) {
                        for (int i = 0; i < lstEmpNumbers.size(); i++) {
                            List<AttendanceData> lstAttendance = null;
                            //
                            String empAttendanceQry = "SELECT EMP.id, ATT_TMP.attendance_date,ATT_TMP.check_in_time, "
                                    + " ATT_TMP.in_out,ATT_TMP.door,ATT_TMP.remarks,ATT_TMP.source,ATT_TMP.location "
                                    + " FROM employees EMP "
                                    + " INNER JOIN attendance_temp ATT_TMP "
                                    + " ON ATT_TMP.emp_id = EMP.employee_number "
                                    + " WHERE EMP.employee_number = ?"
                                    + " AND ATT_TMP.attendance_date = DATE_ADD(CURDATE(), INTERVAL -? day)"
                                    + " ORDER BY EMP.id, ATT_TMP.attendance_date,ATT_TMP.id ";
                            //
                            PreparedStatement psEmpAttendance = objTargetConnection.prepareStatement(empAttendanceQry);
                            psEmpAttendance.setString(1, lstEmpNumbers.get(i));
                            psEmpAttendance.setInt(2, nDays);
                            //
                            ResultSet rsEmpAttendance = psEmpAttendance.executeQuery();
                            if (rsEmpAttendance != null) {
                                lstAttendance = new ArrayList<AttendanceData>();
                                while (rsEmpAttendance.next()) {
                                    AttendanceData oAttendanceData = new AttendanceData();
                                    oAttendanceData.setEmpId(rsEmpAttendance.getLong(1));
                                    oAttendanceData.setEmployeeNumber(lstEmpNumbers.get(i));
                                    oAttendanceData.setAttendanceDate(rsEmpAttendance.getString(2));
                                    oAttendanceData.setCheckinTime(rsEmpAttendance.getString(3));
                                    oAttendanceData.setInOut(rsEmpAttendance.getInt(4));
                                    oAttendanceData.setDoor(rsEmpAttendance.getString(5));
                                    oAttendanceData.setRemarks(rsEmpAttendance.getString(6));
                                    oAttendanceData.setSource(rsEmpAttendance.getString(7));
                                    oAttendanceData.setLocation(rsEmpAttendance.getInt(8));
                                    lstAttendance.add(oAttendanceData);
                                }
                            }
                            if (lstAttendance != null && lstAttendance.size() > 0) {
                                List<AttendanceData> lstEmployeeAttendance = lstAttendance;

                                String first_in = "";
                                String last_out = "";
                                String last_out_yesterday = "";
                                String carry_fwd_out = "";

                                if (lstEmployeeAttendance.get(0).getInOut() == 0) {
                                    Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
                                        @Override
                                        public int compare(AttendanceData att2, AttendanceData att1) {

                                            return att2.getCheckinTime().compareTo(att1.getCheckinTime());
                                        }
                                    });

                                    first_in = lstAttendance.get(0).getCheckinTime();
                                    last_out = lstAttendance.get(lstAttendance.size() - 1).getCheckinTime();
                                } else {
                                    first_in = GetInOutTime(lstEmployeeAttendance, 1);
                                    last_out = GetInOutTime(lstEmployeeAttendance, 2);
                                    carry_fwd_out = GetCarryForwardTime(lstEmployeeAttendance);
                                }
                                //
                                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                //
                                ResultSet rsEmpAttendanceExists = null;
                                long lnAttendanceId = 0;

                                String existsQry = "";

                                if (!carry_fwd_out.equals("")) {                                    
                                    existsQry = "SELECT id FROM employee_attendance WHERE emp_id = ? "
                                            + " AND attendance_date = DATE_ADD(?, INTERVAL -? day)";
                                    
                                    PreparedStatement psExistsQry = objTargetConnection.prepareStatement(existsQry);
                                    psExistsQry.setLong(1, lstEmployeeAttendance.get(0).getEmpId());
                                    psExistsQry.setString(2, lstEmployeeAttendance.get(0).getAttendanceDate());
                                    psExistsQry.setInt(3, nDays);

                                    rsEmpAttendanceExists = psExistsQry.executeQuery();
                                    if (rsEmpAttendanceExists != null && rsEmpAttendanceExists.next()) {
                                        lnAttendanceId = rsEmpAttendanceExists.getLong(1);
                                    }
                                    if (lnAttendanceId > 0) {
                                        String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate() + " 00:00";
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(formatter.parse(tempDate));
                                        c.add(Calendar.DATE, -1);
                                        c.set(Calendar.HOUR_OF_DAY, 23);
                                        c.set(Calendar.MINUTE, 59);
                                        Date dtTempDate = c.getTime();
                                        last_out_yesterday = formatter.format(dtTempDate);
                                        //
                                        c = Calendar.getInstance();
                                        c.setTime(formatter.parse(tempDate));
                                        c.set(Calendar.HOUR_OF_DAY, 0);
                                        c.set(Calendar.MINUTE, 0);
                                        dtTempDate = c.getTime();
                                        String carry_fwd_in = formatter.format(dtTempDate);

                                        c = Calendar.getInstance();
                                        c.setTime(formatter.parse(tempDate));
                                        c.set(Calendar.HOUR_OF_DAY, 0);
                                        c.set(Calendar.MINUTE, 0);
                                        //
                                        String[] parts = carry_fwd_out.split(" ");
                                        if (parts != null && parts.length == 2) {
                                            String[] timeparts = parts[1].split(":");
                                            c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                            c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                        }
                                        dtTempDate = c.getTime();
                                        carry_fwd_out = formatter.format(dtTempDate);
                                        
                                        String updateQry = "UPDATE employee_attendance SET carry_fwd_out = ?"
                                                + ", carry_fwd_in = ?"
                                                + ", last_out = ? WHERE id = ?";
                                        PreparedStatement psUpdateStmt = objTargetConnection.prepareStatement(updateQry);
                                        //
                                        psUpdateStmt.setString(1, carry_fwd_out);
                                        psUpdateStmt.setString(2, carry_fwd_in);
                                        psUpdateStmt.setString(3, last_out_yesterday);
                                        psUpdateStmt.setLong(4, lnAttendanceId);
                                        //
                                        psUpdateStmt.execute();
                                        psUpdateStmt.close();
                                    }
                                    psExistsQry.close();
                                }
                                
                                existsQry = "SELECT emp_id FROM employee_attendance WHERE emp_id = ? "
                                             + " AND attendance_date = ?";
                                
                                PreparedStatement psExistsQry = objTargetConnection.prepareStatement(existsQry);
                                psExistsQry.setLong(1, lstEmployeeAttendance.get(0).getEmpId());
                                psExistsQry.setString(2, lstEmployeeAttendance.get(0).getAttendanceDate());

                                if (ValidDateForAttendanceEntry(lstAttendance)) {
                                    rsEmpAttendanceExists = psExistsQry.executeQuery();
                                    long lnEmpId = 0;
                                    if (rsEmpAttendanceExists != null && rsEmpAttendanceExists.next()) {
                                        lnEmpId = rsEmpAttendanceExists.getLong(1);
                                    }

                                    if (lnEmpId == 0) {
                                        
                                        String insertQry = "INSERT INTO employee_attendance (emp_id,attendance_date,first_in,last_out,location,source)"
                                                + " VALUES(?,?,?,?,?,?)";
                                        
                                        PreparedStatement psInsertStmt = objTargetConnection.prepareStatement(insertQry);
                                        psInsertStmt.setLong(1, lstEmployeeAttendance.get(0).getEmpId());
                                        psInsertStmt.setString(2, lstEmployeeAttendance.get(0).getAttendanceDate());
                                        
                                        if (!first_in.equals("")) {
                                            //
                                            String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate() + " 00:00";
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(formatter.parse(tempDate));
                                            //
                                            String[] parts = first_in.split(" ");
                                            if (parts != null && parts.length == 2) {
                                                String[] timeparts = parts[1].split(":");
                                                c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                                c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                            }
                                            Date dtTempDate = c.getTime();
                                            first_in = formatter.format(dtTempDate);

                                            //insertQry += ",'" + first_in + "'";
                                             psInsertStmt.setString(3,first_in);
                                        } else {
                                            //insertQry += ",NULL";
                                            psInsertStmt.setString(3, null);
                                        }
                                        if (!last_out.equals("")) {
                                            //
                                            String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate() + " 00:00";
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(formatter.parse(tempDate));
                                            //
                                            String[] parts = last_out.split(" ");
                                            if (parts != null && parts.length == 2) {
                                                String[] timeparts = parts[1].split(":");
                                                c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                                c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                            }
                                            Date dtTempDate = c.getTime();
                                            last_out = formatter.format(dtTempDate);
                                            
                                            //insertQry += ",'" + last_out + "'";
                                            psInsertStmt.setString(4, last_out);
                                        } else {
                                            //insertQry += ",NULL";
                                            psInsertStmt.setString(4, null);
                                        }

                                        //insertQry += ",'" + lstEmployeeAttendance.get(0).getLocation() + "'";
                                        //insertQry += ",'" + lstEmployeeAttendance.get(0).getSource() + "')";
                                        psInsertStmt.setInt(5, lstEmployeeAttendance.get(0).getLocation());
                                        psInsertStmt.setString(6, lstEmployeeAttendance.get(0).getSource());

                                        //stmtInsert.execute(insertQry);
                                        psInsertStmt.execute();
                                        psInsertStmt.close();
                                    }
                                }
                                psExistsQry.close();
                            }
                        }
                    }
                }
                objTargetConnection.commit();
            } catch (Exception e) {
                logger.info(e.getMessage());
                objTargetConnection.rollback();
            } finally {
                objTargetConnection.close();
            }
            logger.info("Attendance temp to Employee attendance ends.");
            //
            logger.info("JAR file execution ends...");
            //
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private static List<List<AttendanceData>> GroupAttendanceByEmployeeNumber(List<AttendanceData> lstAttendance) {
        List<List<AttendanceData>> lstEmployeeAttendance = null;

        Map<String, List<AttendanceData>> groupedAttendance = new HashMap<String, List<AttendanceData>>();
        for (AttendanceData attendance : lstAttendance) {
            String key = attendance.getEmployeeNumber();
            if (groupedAttendance.get(key) == null) {
                groupedAttendance.put(key, new ArrayList<AttendanceData>());
            }
            groupedAttendance.get(key).add(attendance);
        }
        if (groupedAttendance != null && groupedAttendance.size() > 0) {
            lstEmployeeAttendance = new ArrayList<List<AttendanceData>>();
            for (List<AttendanceData> lstAttendanceGrp : groupedAttendance.values()) {
                lstEmployeeAttendance.add(lstAttendanceGrp);
            }
        }
        return lstEmployeeAttendance;
    }

    private static String GetInOutTime(List<AttendanceData> lstAttendance, Integer InOut) {
        String InOutTime = "";

        if (lstAttendance != null && lstAttendance.size() > 0) {
            Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
                @Override
                public int compare(AttendanceData att2, AttendanceData att1) {

                    return att2.getCheckinTime().compareTo(att1.getCheckinTime());
                }
            });

            if (InOut == 1) {
                if (lstAttendance.get(0).getInOut() == 1) {
                    InOutTime = lstAttendance.get(0).getCheckinTime();
                } else if (lstAttendance.get(0).getInOut() == 2) {
                    int nIndex = -1;
                    for (int i = 0; i < lstAttendance.size(); i++) {
                        if (lstAttendance.get(i).getInOut() == 1) {
                            nIndex = i;
                            break;
                        }
                    }
                    if (nIndex > -1) {
                        InOutTime = lstAttendance.get(nIndex).getCheckinTime();
                    }
                }
            }
            if (InOut == 2) {
                if (lstAttendance.get(lstAttendance.size() - 1).getInOut() == 2) {
                    InOutTime = lstAttendance.get(lstAttendance.size() - 1).getCheckinTime();
                } else if (lstAttendance.get(lstAttendance.size() - 1).getInOut() == 1) {
                    int nIndex = -1;
                    for (int i = lstAttendance.size() - 1; i >= 0; i--) {
                        if (lstAttendance.get(i).getInOut() == 2) {
                            nIndex = i;
                            break;
                        }
                    }
                    if (nIndex > -1) {
                        InOutTime = lstAttendance.get(nIndex).getCheckinTime();
                    }
                }
            }
        }
        return InOutTime;
    }

    private static String GetCarryForwardTime(List<AttendanceData> lstAttendance) {
        String Carry_Fwd_Time = "";

        if (lstAttendance != null && lstAttendance.size() > 0) {
            Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
                @Override
                public int compare(AttendanceData att2, AttendanceData att1) {

                    return att2.getCheckinTime().compareTo(att1.getCheckinTime());
                }
            });

            if (lstAttendance.get(0).getInOut() == 2) {
                Carry_Fwd_Time = lstAttendance.get(0).getCheckinTime();
            }
        }
        return Carry_Fwd_Time;
    }

    private static boolean ValidDateForAttendanceEntry(List<AttendanceData> lstAttendance) {
        boolean bValid = false;

        if (lstAttendance.get(0).getLocation() == 2) {
            bValid = true;
        } else {
            for (int i = 0; i < lstAttendance.size(); i++) {
                if (lstAttendance.get(i).getInOut() == 1) {
                    bValid = true;
                    break;
                }
            }
        }

        return bValid;
    }
}
