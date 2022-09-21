/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bench_allocation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class Bench_allocation {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    static String fromAddress ;
    static String mailPassword;
    static String mailHost;
    static String mailPort;
    static String mailTo;
    static String mailCc;
    static String mailSubject = "";
    static String mailMessage = "";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {

        String configFile = new File("bench_allocation.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        objTargetConnection.setAutoCommit(false);
        
        String mailPasswordQuery = "SELECT configuration_value FROM configuration_values WHERE parent_id = 10";
        PreparedStatement mailStatement = objTargetConnection.prepareStatement(mailPasswordQuery);
        ResultSet mailResult = mailStatement.executeQuery();
        if(mailResult != null){
            while(mailResult.next()){
                if(mailResult.getRow()==1)
                    fromAddress = mailResult.getString(1);
                if(mailResult.getRow()==2)
                    mailPassword = mailResult.getString(1);
                if(mailResult.getRow()==3)
                    mailHost = mailResult.getString(1);
                if(mailResult.getRow()==4)
                    mailPort = mailResult.getString(1);
            }
        }
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(fromAddress,mailPassword,mailHost,mailPort);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curdate = new Date();
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end_date = startEnd.getTime();
        
        String full_bench = "SELECT emp.id AS employee_id, emp.employee_number, "
                            +"CONCAT(emp.first_name,' ', emp.last_name), emp.structure_name, "
                            +"emp.structure_name_subgroup, emp.structure_name_subpractice, "
                            +"emp.work_email_address, emp.current_location_id "
                            +"FROM employees AS emp "
                            +"LEFT JOIN project_team_allocations AS pta ON emp.id = pta.employee_id AND "
                            +"pta.end_date >= now() AND pta.deleted=0 AND pta.status='a'"
                            +"LEFT JOIN projects AS prj ON pta.project_id = prj.id AND prj.project_status='e'"
                            +"WHERE (released_date IS NULL OR released_date>CURDATE())"
                            +"AND emp.billable = 'b' AND prj.id IS NULL";
        
        PreparedStatement bench_query = objTargetConnection.prepareStatement(full_bench);
        ResultSet resultSet_full_bench = bench_query.executeQuery();
                
        int counnt=0;
        if(resultSet_full_bench != null){
            while(resultSet_full_bench.next()){
                counnt++;
                String getBench_id = "";
                if(resultSet_full_bench.getString(4).equals("2")){
                    getBench_id = "SELECT pbp.project_id, pbp.role_id "
                        + "FROM practice_bench_project_mappings AS pbp "
                        + "WHERE pbp.sub_practice_id = "+resultSet_full_bench.getString(6);
                }else if(resultSet_full_bench.getString(4).equals("5")){
                    getBench_id = "SELECT pbp.project_id, pbp.role_id "
                        + "FROM practice_bench_project_mappings AS pbp "
                        + "WHERE pbp.sub_practice_id = "+resultSet_full_bench.getString(5);
                }
                PreparedStatement bench_project_query = objTargetConnection.prepareStatement(getBench_id);
                ResultSet rs_projecct_id = bench_project_query.executeQuery();
                if(rs_projecct_id != null){
                    while(rs_projecct_id.next()){
                        String insert_query = "INSERT INTO project_team_allocations(project_id, employee_id, role_id, work_location_id, "
                                                + "location_table, start_date, end_date, status, billable_effort,updated_on) "
                                                + "VALUES(?,?,?,?,'company_locations',?,?,'a',0.00, now())";

                        try{
                            PreparedStatement insert_pta = objTargetConnection.prepareStatement(insert_query);
                            insert_pta.setInt(1, Integer.parseInt(rs_projecct_id.getString(1)));
                            insert_pta.setInt(2, Integer.parseInt(resultSet_full_bench.getString(1)));
                            insert_pta.setInt(3, Integer.parseInt(rs_projecct_id.getString(2)));
                            insert_pta.setInt(4, Integer.parseInt(resultSet_full_bench.getString(8)));
                            insert_pta.setString(5, sdf.format(curdate).toString());
                            insert_pta.setString(6, sdf.format(end_date).toString());
                            insert_pta.execute();
                            insert_pta.clearParameters();
                            insert_pta.close();
                            objTargetConnection.commit();
                        }catch(SQLException e){
                            System.out.println("  "+e);
                        }
                        
                    }
                }
            }
        }
        
        String allocated_resource = "SELECT pta.employee_id, pta.project_id, pta.role_id, "
                + "pta.start_date, pta.end_date, pta.id "
                + "FROM project_team_allocations AS pta "
                + "WHERE pta.end_date>=CURDATE() "
                + "AND pta.project_id IN(SELECT project_id FROM practice_bench_project_mappings)";
        
        PreparedStatement allocated_query = objTargetConnection.prepareStatement(allocated_resource);
        ResultSet resultSet_allocated_query = allocated_query.executeQuery();
                
        //int counnt=0;
        if(resultSet_allocated_query != null){
            while(resultSet_allocated_query.next()){
                String project_details = "SELECT pta.project_id, pta.start_date, pta.end_date "
                        + "FROM project_team_allocations AS pta "
                        + "left join projects as prj on pta.project_id = prj.id "
                        + "WHERE pta.employee_id = '"+resultSet_allocated_query.getString(1) +"' "
                        + "AND pta.project_id <> '"+resultSet_allocated_query.getString(2) +"' "
                        + "AND pta.end_date > CURDATE() "
                        + "AND pta.status = 'a' AND prj.project_status ='e'";
                PreparedStatement project_query = objTargetConnection.prepareStatement(project_details);
                ResultSet resultSet_project_query = project_query.executeQuery();
                
                if(resultSet_project_query != null){
                    while(resultSet_project_query.next()){
                        String new_start_date="";
                        String new_end_date="";
                        Date project_start = sdf.parse(resultSet_project_query.getString(2));
                        Date bench_start = sdf.parse(resultSet_allocated_query.getString(4));
                        Date bench_end = sdf.parse(resultSet_allocated_query.getString(5));
                        if(project_start.compareTo(bench_end) < 0){
                            Calendar bench_new_end = Calendar.getInstance();
                            bench_new_end.setTime(project_start);
                            bench_new_end.add(Calendar.DATE,-1);
                            Date dd = bench_new_end.getTime();
                            new_end_date = sdf.format(dd);
                            if(bench_start.compareTo(dd) > 0){
                                try{
                                    String delete_bench_allocation = "DELETE FROM project_team_allocations WHERE id = ? AND project_id = ?";
                                    PreparedStatement delete_ps = objTargetConnection.prepareStatement(delete_bench_allocation);
                                    delete_ps.setInt(1, Integer.parseInt(resultSet_allocated_query.getString(6)));
                                    delete_ps.setString(2, resultSet_allocated_query.getString(2));
                                    int delete = delete_ps.executeUpdate();
                                    objTargetConnection.commit();
                                }catch(Exception e){
                                
                                }
                            }else if(bench_start.compareTo(dd) < 0){
                                try{
                                    String update_end = "UPDATE project_team_allocations SET end_date = '"+sdf.format(dd) +"', updated_on = NOW() "
                                            + "WHERE project_id = '"+resultSet_allocated_query.getString(2)+"' AND "
                                            + "id = '"+resultSet_allocated_query.getString(6)+"'"; 
                                    PreparedStatement update_ps = objTargetConnection.prepareStatement(update_end);
                                    update_ps.executeUpdate();
                                    objTargetConnection.commit();
                                }catch(Exception e){
                                
                                }
                                
                            }
                            try{
                                String reject_timesheet = "UPDATE timesheet_entries AS te "
                                        + "LEFT JOIN timesheet_entry_projects AS tep ON te.id = tep.timesheet_entry_id "
                                        + "SET te.status = 'r', tep.status = 'r' , tep.approved_date = NOW() "
                                        + "WHERE tep.is_accrued = 'n' AND tep.project_id = '"+resultSet_allocated_query.getString(2)+"' AND "
                                        + "te.employee_id = '"+resultSet_allocated_query.getString(1) +"' "
                                        + "AND te.timesheet_date >'"+sdf.format(dd)+"'";
                                PreparedStatement rj_ts_ps = objTargetConnection.prepareStatement(reject_timesheet);
                                rj_ts_ps.executeUpdate();
                                objTargetConnection.commit();
                            }catch(Exception e){
                            
                            }
                        }
                    }
                }
            }
        }
    }   
}
