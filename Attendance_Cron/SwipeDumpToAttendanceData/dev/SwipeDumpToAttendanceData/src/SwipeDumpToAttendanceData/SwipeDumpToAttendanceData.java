/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SwipeDumpToAttendanceData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
/**
 *
 * @author 16047
 */
public class SwipeDumpToAttendanceData {

    /**
     * @param args the command line arguments
     */
    static String strUserName ="ideal2";
    static String strPassword ="passw0rd@123";
   static String strDatabase="jdbc:mysql://10.18.1.54:3306/ideal_test";
  //  static String strDatabase="jdbc:mysql://192.168.1.219:3306/ideal2";

    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.gjt.mm.mysql.Driver");
        Connection objTargetConnection = null;        
        //SetConfigValues();        
        //
        if(!strDatabase.equals("") && !strUserName.equals("") && !strPassword.equals(""))
        {
            try{
                objTargetConnection = (Connection) DriverManager.getConnection(strDatabase,strUserName,strPassword);
                objTargetConnection.setAutoCommit(false);

                if(objTargetConnection != null)
                {
                    System.out.println("connected");
                    List<String> lstEmpNumbers = null;
                    Statement stmtGetEmpNumbers = objTargetConnection.createStatement();
//                    String empNumberQry = "SELECT EMP.employee_number " +
//                                            " FROM employees EMP " +
//                                            " INNER JOIN employee_work_location EMP_WRK_LOC " +
//                                            " ON EMP.id = EMP_WRK_LOC.employee_id " + 
//                                            " INNER JOIN company_locations COMP_LOC " + 
//                                            " ON EMP_WRK_LOC.work_location_id = COMP_LOC.id " +
//                                            " INNER JOIN countries CNTRY " + 
//                                            " ON COMP_LOC.country_id = CNTRY.id " +
//                                            " WHERE CNTRY.country = 'India'" + 
//                                            " AND EMP_WRK_LOC.location_table = 'company_locations'" +
//                                            " UNION " +
//                                            " SELECT EMP.employee_number " +
//                                            " FROM employees EMP " + 
//                                            " INNER JOIN employee_work_location EMP_WRK_LOC " +
//                                            " ON EMP.id = EMP_WRK_LOC.employee_id " +
//                                            " WHERE EMP_WRK_LOC.work_location_id = 0 ";
                    String empNumberQry = "select distinct Emp_id from attendance_temp "+
                                            " where attendance_date = DATE_ADD(CURDATE(), INTERVAL -1 day) ";
                    
                    stmtGetEmpNumbers.execute(empNumberQry);
                    ResultSet rsEmpNumbers = stmtGetEmpNumbers.getResultSet();
                    if(rsEmpNumbers != null)
                    {
                        lstEmpNumbers = new ArrayList<String>();
                        while(rsEmpNumbers.next())
                        {
                            lstEmpNumbers.add(rsEmpNumbers.getString(1));
                        }
                    }
                    
                    if(lstEmpNumbers != null && lstEmpNumbers.size() > 0)
                    {  
                        for(int i = 0; i < lstEmpNumbers.size(); i++)
                        {
                            List<AttendanceData> lstAttendance = null;
                            Statement stmtEmpAttendance = objTargetConnection.createStatement();
                            String empAttendanceQry = "SELECT EMP.id, ATT_TMP.attendance_date,ATT_TMP.check_in_time, " + 
                                                    " ATT_TMP.in_out,ATT_TMP.door,ATT_TMP.remarks,ATT_TMP.source,ATT_TMP.location " +
                                                    " FROM employees EMP " +
                                                    " INNER JOIN attendance_temp ATT_TMP " +
                                                    " ON ATT_TMP.emp_id = EMP.employee_number " +
                                                    " WHERE EMP.employee_number = '" + lstEmpNumbers.get(i) + "'" +
                                                    " AND ATT_TMP.attendance_date = DATE_ADD(CURDATE(), INTERVAL -1 day)" +
                                                    " ORDER BY EMP.id, ATT_TMP.attendance_date,ATT_TMP.id ";
                            stmtEmpAttendance.execute(empAttendanceQry);
                            ResultSet rsEmpAttendance = stmtEmpAttendance.getResultSet();
                            if(rsEmpAttendance != null)
                            {
                                lstAttendance = new ArrayList<AttendanceData>();
                                while(rsEmpAttendance.next())
                                {
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
                            if(lstAttendance != null && lstAttendance.size() > 0)
                            {
                                List<AttendanceData> lstEmployeeAttendance = lstAttendance;
                                        
                                String first_in = "";
                                String last_out = "";
                                String last_out_yesterday = "";
                                String carry_fwd_out = "";

                                if(lstEmployeeAttendance.get(0).getInOut() == 0)
                                {
                                    Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
                                    @Override
                                    public int compare(AttendanceData att2, AttendanceData att1)
                                    {

                                        return  att2.getCheckinTime().compareTo(att1.getCheckinTime());
                                    }
                                    });

                                    first_in = lstAttendance.get(0).getCheckinTime();
                                    last_out = lstAttendance.get(lstAttendance.size() - 1).getCheckinTime();
                                }
                                else
                                {
                                    first_in = GetInOutTime(lstEmployeeAttendance, 1);
                                    last_out = GetInOutTime(lstEmployeeAttendance, 2);
                                    carry_fwd_out = GetCarryForwardTime(lstEmployeeAttendance);
                                }
                                //

                                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");                                 
                                //
                                Statement stmtExists = objTargetConnection.createStatement();  
                                ResultSet rsEmpAttendanceExists = null;
                                long lnAttendanceId = 0;

                                String existsQry = "";

                                if(!carry_fwd_out.equals(""))
                                {
                                    existsQry = "SELECT id FROM employee_attendance WHERE emp_id = '" + lstEmployeeAttendance.get(0).getEmpId() +
                                                   "' AND attendance_date = DATE_ADD('" + lstEmployeeAttendance.get(0).getAttendanceDate()  + "', INTERVAL -1 day)";

                                    stmtExists.execute(existsQry);
                                    rsEmpAttendanceExists = stmtExists.getResultSet();
                                    if(rsEmpAttendanceExists != null && rsEmpAttendanceExists.next())
                                    {
                                        lnAttendanceId = rsEmpAttendanceExists.getLong(1);
                                    }

                                    if(lnAttendanceId > 0)
                                    {                                           
                                        String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate() + " 00:00";                                         
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(formatter.parse(tempDate));
                                        c.add(Calendar.DATE, -1);
                                        c.set(Calendar.HOUR_OF_DAY, 23);
                                        c.set(Calendar.MINUTE, 59);
                                        Date dtTempDate = c.getTime();
                                        last_out_yesterday =   formatter.format(dtTempDate);
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
                                        if(parts != null && parts.length == 2)
                                        {
                                            String[] timeparts = parts[1].split(":");
                                            c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                            c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                        }
                                        dtTempDate = c.getTime();                                        
                                        carry_fwd_out = formatter.format(dtTempDate);

                                        Statement stmtUpdate = objTargetConnection.createStatement(); 
                                        String updateQry = "UPDATE employee_attendance SET carry_fwd_out = '" + carry_fwd_out + 
                                                            "', carry_fwd_in = '" + carry_fwd_in + "'" +
                                                            ", last_out = '" + last_out_yesterday + "' WHERE id = '" + lnAttendanceId + "'";
                                        stmtUpdate.execute(updateQry);
                                    }
                                }

                                existsQry = "SELECT emp_id FROM employee_attendance WHERE emp_id = '" + lstEmployeeAttendance.get(0).getEmpId() +
                                                   "' AND attendance_date = '" + lstEmployeeAttendance.get(0).getAttendanceDate() + "'";                                                           

                                stmtExists.execute(existsQry);
                                
                                if(ValidDateForAttendanceEntry(lstAttendance))
                                {
                                    rsEmpAttendanceExists = stmtExists.getResultSet();
                                    long lnEmpId = 0;
                                    if(rsEmpAttendanceExists != null && rsEmpAttendanceExists.next())
                                    {
                                        lnEmpId = rsEmpAttendanceExists.getLong(1);
                                    }

                                    if(lnEmpId == 0)
                                    {                                    
                                        Statement stmtInsert = objTargetConnection.createStatement();
                                        String insertQry = "INSERT INTO employee_attendance (emp_id,attendance_date,first_in,last_out,location,source)" + 
                                                           " VALUES(" + lstEmployeeAttendance.get(0).getEmpId() + 
                                                           ",' " + lstEmployeeAttendance.get(0).getAttendanceDate() + "'";
                                        if(!first_in.equals("")) {
                                            //
                                            String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate()+ " 00:00";                                               
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(formatter.parse(tempDate));
                                            //
                                            String[] parts = first_in.split(" ");
                                            if(parts != null && parts.length == 2)
                                            {
                                                String[] timeparts = parts[1].split(":");
                                                c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                                c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                            }
                                            Date dtTempDate = c.getTime();
                                            first_in =   formatter.format(dtTempDate);

                                            insertQry += ",'" + first_in + "'";
                                        }
                                        else {
                                            insertQry += ",NULL";
                                        }
                                         if(!last_out.equals("")) {
                                              //
                                            String tempDate = lstEmployeeAttendance.get(0).getAttendanceDate()+ " 00:00";                                               
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(formatter.parse(tempDate));
                                            //
                                            String[] parts = last_out.split(" ");
                                            if(parts != null && parts.length == 2)
                                            {
                                                String[] timeparts = parts[1].split(":");
                                                c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timeparts[0]));
                                                c.add(Calendar.MINUTE, Integer.parseInt(timeparts[1]));
                                            }
                                            Date dtTempDate = c.getTime();
                                            last_out =   formatter.format(dtTempDate);
                                            insertQry += ",'" + last_out + "'";
                                        }
                                        else {
                                            insertQry += ",NULL";
                                        }

                                        insertQry += ",'" + lstEmployeeAttendance.get(0).getLocation() + "'";
                                        insertQry += ",'" + lstEmployeeAttendance.get(0).getSource() + "')";

                                        stmtInsert.execute(insertQry);    
                                    }
                                }
                            }
                        }
                    }
                }
                objTargetConnection.commit();
            }
            catch(Exception e){
                System.out.print(e);
                objTargetConnection.rollback();   
            }finally
            {
                objTargetConnection.close();
            }
        }
        else
        {
            System.out.print("Unable to read configuration values");
        }
    }
    
    private static List<List<AttendanceData>> GroupAttendanceByEmployeeNumber(List<AttendanceData> lstAttendance)
    {
        List<List<AttendanceData>> lstEmployeeAttendance = null;

        Map<String, List<AttendanceData>> groupedAttendance = new HashMap<String, List<AttendanceData>>();
        for (AttendanceData attendance: lstAttendance) {
            String key = attendance.getEmployeeNumber();
            if (groupedAttendance.get(key) == null) {
                groupedAttendance.put(key, new ArrayList<AttendanceData>());
            }
            groupedAttendance.get(key).add(attendance);
        }
        if(groupedAttendance != null && groupedAttendance.size() > 0)
        {
            lstEmployeeAttendance = new ArrayList<List<AttendanceData>>();
            for(List<AttendanceData> lstAttendanceGrp : groupedAttendance.values())
            {
                lstEmployeeAttendance.add(lstAttendanceGrp);
            }
        }
        return lstEmployeeAttendance;
    }
    
    private static String GetInOutTime(List<AttendanceData> lstAttendance, Integer InOut)
    {
        String InOutTime = "";
       
        if(lstAttendance != null && lstAttendance.size() > 0)
        {            
            Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
            @Override
            public int compare(AttendanceData att2, AttendanceData att1)
            {

                return  att2.getCheckinTime().compareTo(att1.getCheckinTime());
            }
            });
            
            if(InOut == 1)
            {
                if(lstAttendance.get(0).getInOut() == 1)
                {       
                    InOutTime = lstAttendance.get(0).getCheckinTime();
                }
                else if(lstAttendance.get(0).getInOut() == 2)
                {
                    int nIndex = -1;
                    for(int i = 0; i < lstAttendance.size(); i++)
                    {
                        if(lstAttendance.get(i).getInOut() == 1)
                        {
                            nIndex = i;
                            break;
                        }
                    }
                    if(nIndex > -1) {
                        InOutTime = lstAttendance.get(nIndex).getCheckinTime();
                    }
                }
            }
            if(InOut == 2)
            {
                if(lstAttendance.get(lstAttendance.size() - 1).getInOut() == 2)
                {
                    InOutTime = lstAttendance.get(lstAttendance.size() - 1).getCheckinTime();
                }
                else if(lstAttendance.get(lstAttendance.size() - 1).getInOut() == 1)
                {
                     int nIndex = -1;
                    for(int i = lstAttendance.size() - 1; i >= 0; i--)
                    {
                        if(lstAttendance.get(i).getInOut() == 2)
                        {
                            nIndex = i;
                            break;
                        }
                    }
                    if(nIndex > -1) {
                        InOutTime = lstAttendance.get(nIndex).getCheckinTime();
                    }
                }
            }
        }
        return InOutTime;
    }    
    
    private static String GetCarryForwardTime(List<AttendanceData> lstAttendance)
    {
        String Carry_Fwd_Time = "";
        
        if(lstAttendance != null && lstAttendance.size() > 0)
        {            
            Collections.sort(lstAttendance, new Comparator<AttendanceData>() {
            @Override
            public int compare(AttendanceData att2, AttendanceData att1)
            {

                return  att2.getCheckinTime().compareTo(att1.getCheckinTime());
            }
            });
            
            if(lstAttendance.get(0).getInOut() == 2)
            {
                Carry_Fwd_Time = lstAttendance.get(0).getCheckinTime();
            }
        }
        return Carry_Fwd_Time;
    }
    
    private static boolean ValidDateForAttendanceEntry(List<AttendanceData> lstAttendance)
    {
        boolean bValid = false;
        
        if(lstAttendance.get(0).getLocation() == 2)
            bValid = true;
        else
        {
            for(int i = 0; i < lstAttendance.size(); i++)
            {
                if(lstAttendance.get(i).getInOut() == 1)
                {
                    bValid = true;
                    break;
                }
            }
        }
        
        return  bValid;
    }    
    
    private static void SetConfigValues()
    {
        // Set Configuration values
        File configFile = new File("./src/SwipeDumpToAttendanceData/SwipeConfiguration.properties");
 
        try {
            FileReader fileInput = new FileReader(configFile);
            Properties props = new Properties();
            props.load(fileInput);
            fileInput.close();
            
            strDatabase = "jdbc:mysql://" + props.getProperty("server") + ":" + props.getProperty("port") + "/" + props.getProperty("database");
            strUserName = props.getProperty("user");
            strPassword = props.getProperty("password");

        } catch (FileNotFoundException ex) {
            System.out.print("exception " +ex.getMessage());
            // file does not exist
        } catch (IOException ex) {
            System.out.print("exception " +ex.getMessage());
            // I/O error
        }catch(Exception ex){
            System.out.print("exception " +ex.getMessage());
        }
    }
}

