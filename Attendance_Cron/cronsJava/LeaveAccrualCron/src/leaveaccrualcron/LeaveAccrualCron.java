/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveaccrualcron;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class LeaveAccrualCron {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    static String location = "";
    static String year = "";
    static String current_month = "";
    static String previous_month = "";
    static String previous_month_start_date = "";
    static String previous_month_end_date = "";
    static String no_of_days_in_month = "";
    static String from_date = "";
    static String to_date = "";
    static String no_of_leave_days = "";
    static double leave_accrued = 0.00;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {
        
        String configFile = new File("LeaveAccrual.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        location = properties.getProperty("LOCATION");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        objTargetConnection.setAutoCommit(true);
        
        Date today = new Date();
        today.setMonth(1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.MONTH, -1);
        year = Integer.toString(cal.get(Calendar.YEAR));
        previous_month = Integer.toString(cal.get(Calendar.MONTH)+1);
        current_month = Integer.toString(cal.get(Calendar.MONTH)+2);
        while(previous_month.length()<2){
            previous_month = "0" + previous_month;
        }
        no_of_days_in_month = Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        previous_month_start_date = year+"-"+previous_month+"-01";
        previous_month_end_date =  year+"-"+previous_month+"-"+no_of_days_in_month;
        if(cal.get(Calendar.MONTH)>=0){
            String employeeListQuery = "SELECT emp.id, emp.employment_status, emp.band_id, emp.joined_date, emp.released_date, ROUND((lq.no_of_days/12),2) AS no_of_days, "
                                        +"CASE WHEN emp.joined_date >= '"+previous_month_start_date+"' THEN '1' ELSE '0' END AS joined, "
                                        +"CASE WHEN emp.released_date <= '"+previous_month_end_date+"' THEN '1' ELSE '0' END AS released "
                                        +"FROM employees AS emp "
                                        +"LEFT JOIN leave_quotas AS lq ON emp.band_id = lq.band_id AND lq.leave_category_id = 4 "
                                        +"WHERE (emp.released_date IS NULL OR emp.released_date>='"+previous_month_start_date+"') "
                                        +"AND emp.company_location_id IN("+location+") AND emp.band_id NOT IN(33,34)";
            System.out.println(employeeListQuery);
            PreparedStatement employeeQuery = objTargetConnection.prepareStatement(employeeListQuery);
            ResultSet employeeList = employeeQuery.executeQuery();
            while (employeeList.next()) {
                int flag=0;
                String empInfoQuery = "SELECT employee_info_histories.employee_id, DATE_FORMAT(FROM_UNIXTIME(employee_info_histories.modified_time),'%Y-%m-%d') AS transferred_date,changed_from,changed_to "
                                        +"FROM employee_info_histories "
                                        +"WHERE employee_info_histories.employee_id = "+employeeList.getString(1)+" AND DATE_FORMAT(FROM_UNIXTIME(employee_info_histories.modified_time),'%Y-%m-%d') >= '"+previous_month_start_date+"' "
                                        +"AND field_changed = 'employment_status' AND ((changed_from = 'p' AND changed_to = 'e') OR (changed_from = 'e' AND changed_to = 'p'))";
                PreparedStatement infoQuery = objTargetConnection.prepareStatement(empInfoQuery);
                ResultSet employeeInfoList = infoQuery.executeQuery();
                if(employeeList.getString(2).equals("e")){
                    flag=0;
                }else{
                    flag=1;
                }
                while(employeeInfoList.next()){
                    System.out.println("inside info history");
                    flag=1;
                }
                if(employeeList.getString(7).equals("1")){
                    from_date = employeeList.getString(4).split("-")[2];
                }else{
                    from_date = "1";
                }
                if(employeeList.getString(8).equals("1")){
                    to_date = employeeList.getString(5).split("-")[2];
                }else{
                    to_date = no_of_days_in_month;
                }
                if(flag==1){
                    leave_accrued = calculateLeave(from_date, to_date, employeeList.getString(6), employeeList.getString(1));
                    String monthlyUpdate = "UPDATE employee_leave_details_monthly SET no_of_days_allotted = "+leave_accrued+" "
                                            + "WHERE employee_id = "+Integer.parseInt(employeeList.getString(1))+" AND month = "+Integer.parseInt(previous_month)+" AND leave_category_id = 4 AND year = "+Integer.parseInt(year)+" ";
                    PreparedStatement monthlyQuery = objTargetConnection.prepareStatement(monthlyUpdate);
//                    monthlyQuery.setDouble(1, leave_accrued);
//                    monthlyQuery.setInt(2,Integer.parseInt(employeeList.getString(1)));
//                    monthlyQuery.setInt(3, Integer.parseInt(previous_month));
//                    monthlyQuery.setInt(4, Integer.parseInt(year));
                    int result = 0;
                    try{
                        //result = monthlyQuery.executeUpdate();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    System.out.println("rsult "+result +" "+monthlyUpdate);
                    
                    String monthlyUpdate_new = "Insert into employee_leave_details_monthly set total_no_of_days_allotted = ? "
                                            + ",employee_id = ? , month = 2 , leave_category_id = 4 , year = ?";
                    PreparedStatement monthlyQuery_new = objTargetConnection.prepareStatement(monthlyUpdate_new);
                    System.out.println("rsult "+monthlyUpdate_new);
                    monthlyQuery_new.setDouble(1, leave_accrued);
                    monthlyQuery_new.setInt(2,Integer.parseInt(employeeList.getString(1)));
                    monthlyQuery_new.setInt(3, Integer.parseInt(year));
                    
                    //monthlyQuery_new.execute();
                    //monthlyQuery.close();   
                }
            }
        }
    }
    
    public static double calculateLeave(String from_date, String to_date, String no_of_days, String employee_id){
        double leave = 0;
        double total_days = Double.parseDouble(to_date) - Double.parseDouble(from_date) + 1;
        double leave_per_day = Double.parseDouble(no_of_days)/Double.parseDouble(no_of_days_in_month);
        leave = total_days * leave_per_day;
        return Math.round(leave* 100D) / 100D;
    }
}
