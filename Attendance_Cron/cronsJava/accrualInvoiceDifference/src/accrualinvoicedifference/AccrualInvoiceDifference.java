/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accrualinvoicedifference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class AccrualInvoiceDifference {

    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    static String month = "";
    static String year = "";
    /*
INSERT INTO accrual_invoice_differences 
(SELECT NULL, '06', '2021',inv.invoice_code,iet.employee_id, iet.role_id, inv.project_id, inv.from_date, inv.to_date, iet.amount,"","",
iet.accrual_con_id
FROM invoices AS inv
LEFT JOIN invoice_employee_tms AS iet ON inv.id = iet.invoice_id 
WHERE inv.approved_on BETWEEN '2021-06-01' AND '2021-06-30' AND inv.source='i' AND STATUS = 'a' AND consolidated_id = 0)


INSERT INTO accrual_invoice_differences (
SELECT NULL, '06', '2021',inv.invoice_code,iet.employee_id, iet.role_id, con.project_id, con.from_date, con.to_date,iet.amount,"","",
iet.accrual_con_id 
FROM invoices AS inv
LEFT JOIN invoice_consolidation_details AS icd ON(inv.consolidated_id = icd.invoice_consolidation_id)
LEFT JOIN invoices AS con ON(icd.invoice_id = con.id)
LEFT JOIN invoice_employee_tms AS iet ON con.id = iet.invoice_id 
WHERE inv.approved_on BETWEEN '2021-06-01' AND '2021-06-30' AND inv.source='i' AND inv.STATUS = 'a'
AND inv.consolidated_id <> 0)*/
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ParseException, Exception {
        // TODO code application logic here
        String configFile = new File("jdbc.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        //month = properties.getProperty("MONTH");
        //year = properties.getProperty("YEAR");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        Calendar calendar = Calendar.getInstance();
        //calendar.set(2021, 0, 1);
        calendar.add(calendar.MONTH,-1);
        int mnth = calendar.get(Calendar.MONTH) + 1;
        int yr  = calendar.get(Calendar.YEAR);
        
        if(mnth<10){
            month = Integer.toString(mnth);
            while(month.length()<2)
                month = "0"+month;
        }else{
            month = Integer.toString(mnth);
        }
        year = Integer.toString(yr);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = year + "-" + month + "-01";
        String end_date = year + "-" + month + "-" + Integer.toString(last_date);
        System.out.println("start date ed date "+start_date+" "+end_date);
        String delete_qry = "DELETE FROM accrual_invoice_differences WHERE billing_month = '"+month+"' AND billing_year = '"+year+"'";
        PreparedStatement del_ps = objTargetConnection.prepareStatement(delete_qry);
        del_ps.executeUpdate();
        
        String inv_query_mnth = "SELECT '"+month+"', '"+year+"',inv.invoice_code,iet.employee_id, iet.role_id, "
                + "inv.project_id, inv.from_date, inv.to_date, iet.amount,'','', iet.accrual_con_id "
                + "FROM invoices AS inv "
                + "LEFT JOIN invoice_employee_tms AS iet ON inv.id = iet.invoice_id "
                + "WHERE inv.approved_on BETWEEN '"+start_date+"' AND '"+end_date+"' AND inv.source='i' "
                + "AND STATUS = 'a' AND consolidated_id = 0";
        System.out.println("invoice "+ inv_query_mnth);
        PreparedStatement iad_ps = objTargetConnection.prepareStatement(inv_query_mnth);
        ResultSet iad_rs = iad_ps.executeQuery();
        while(iad_rs.next()){
            String insert_data = "INSERT INTO accrual_invoice_differences(billing_month, billing_year, invoice_code, "
                    + "employee_id, role_id, project_id, from_date, to_date, invoice_amount, accrual_no, accrued_amount, "
                    + "consolidate_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert_data_ps = objTargetConnection.prepareStatement(insert_data);
            insert_data_ps.setString(1, iad_rs.getString(1));
            insert_data_ps.setString(2, iad_rs.getString(2));
            insert_data_ps.setString(3, iad_rs.getString(3));
            insert_data_ps.setString(4, iad_rs.getString(4));
            insert_data_ps.setString(5, iad_rs.getString(5));
            insert_data_ps.setString(6, iad_rs.getString(6));
            insert_data_ps.setString(7, iad_rs.getString(7));
            insert_data_ps.setString(8, iad_rs.getString(8));
            insert_data_ps.setString(9, iad_rs.getString(9));
            insert_data_ps.setString(10, iad_rs.getString(10));
            insert_data_ps.setString(11, iad_rs.getString(11));
            insert_data_ps.setString(12, iad_rs.getString(12));
            insert_data_ps.execute();
        }
        
        String inv_con_mnth = "SELECT '"+month+"', '"+year+"',inv.invoice_code,iet.employee_id, "
                + "iet.role_id, con.project_id, con.from_date, con.to_date,iet.amount, '','', " 
                +"iet.accrual_con_id  FROM invoices AS inv " 
                +"LEFT JOIN invoice_consolidation_details AS icd ON(inv.consolidated_id = icd.invoice_consolidation_id) "
                + "LEFT JOIN invoices AS con ON(icd.invoice_id = con.id) "
                + "LEFT JOIN invoice_employee_tms AS iet ON con.id = iet.invoice_id "
                + "WHERE inv.approved_on BETWEEN '"+start_date+"' AND '"+end_date+"' AND inv.source='i' AND inv.STATUS = 'a'"
                + "AND inv.consolidated_id <> 0";

        PreparedStatement iad_con_ps = objTargetConnection.prepareStatement(inv_con_mnth);
        ResultSet iad_con_rs = iad_con_ps.executeQuery();
        while(iad_con_rs.next()){
            String insert_data = "INSERT INTO accrual_invoice_differences(billing_month, billing_year, invoice_code, "
                    + "employee_id, role_id, project_id, from_date, to_date, invoice_amount, accrual_no, accrued_amount, "
                    + "consolidate_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert_data_ps = objTargetConnection.prepareStatement(insert_data);
            insert_data_ps.setString(1, iad_con_rs.getString(1));
            insert_data_ps.setString(2, iad_con_rs.getString(2));
            insert_data_ps.setString(3, iad_con_rs.getString(3));
            insert_data_ps.setString(4, iad_con_rs.getString(4));
            insert_data_ps.setString(5, iad_con_rs.getString(5));
            insert_data_ps.setString(6, iad_con_rs.getString(6));
            insert_data_ps.setString(7, iad_con_rs.getString(7));
            insert_data_ps.setString(8, iad_con_rs.getString(8));
            insert_data_ps.setString(9, iad_con_rs.getString(9));
            insert_data_ps.setString(10, iad_con_rs.getString(10));
            insert_data_ps.setString(11, iad_con_rs.getString(11));
            insert_data_ps.setString(12, iad_con_rs.getString(12));
            insert_data_ps.execute();
        }

        String inv_details_query = "SELECT invoice_code, employee_id, role_id, project_id, from_date,"
                + "to_date, consolidate_id FROM accrual_invoice_differences "
                + "WHERE employee_id IS NOT NULL AND billing_month = '"+month+"' "
                + "AND billing_year = '"+year+"' AND consolidate_id = '0'";
        System.out.println("accrual_invo "+ inv_details_query);
        PreparedStatement inv_ps = objTargetConnection.prepareStatement(inv_details_query);
        ResultSet inv_rs = inv_ps.executeQuery();
        while(inv_rs.next()){
            String accrual_id_query = "SELECT group_concat(DISTINCT(accrual_no)) FROM accrual_tms "
                    + "WHERE emp_id = "+inv_rs.getString(2) +" AND project_id = "+inv_rs.getString(4) +" "
                    + "AND role_id = "+inv_rs.getString(3) +" AND ts_entry_date BETWEEN '"+inv_rs.getString(5) +"' "
                    + "AND '"+inv_rs.getString(6) +"' AND status = 'm' "
                    + "GROUP BY emp_id, project_id, role_id";
            System.out.println("accrual_details "+ accrual_id_query);
            PreparedStatement accrual_ps = objTargetConnection.prepareStatement(accrual_id_query);
            ResultSet accrual_rs = accrual_ps.executeQuery();
            String accrual_no = "0";
            while(accrual_rs.next()){
                accrual_no = accrual_rs.getString(1);
            }
            System.out.println("acrual NO "+accrual_no+" invoice_code = "+inv_rs.getString(1));
            String accrual_amount_query = "SELECT SUM(billable_amount) "
                    + "FROM accrual_consolidations WHERE emp_id = "+inv_rs.getString(2) +" AND "
                    + "project_id = "+inv_rs.getString(4)+" AND role_id = "+inv_rs.getString(3)+" "
                    + "AND accrual_no IN("+accrual_no+") AND status = 'm'";
            PreparedStatement accrual_amount_ps = objTargetConnection.prepareStatement(accrual_amount_query);
            ResultSet accrual_amount_s = accrual_amount_ps.executeQuery();
            String accrual_amount = "0.00";
            while(accrual_amount_s.next()){
                accrual_amount = accrual_amount_s.getString(1);
            }
            
            String update_resg = "UPDATE accrual_invoice_differences SET accrual_no = '"+accrual_no+"', "
                    + "accrued_amount = '"+accrual_amount+"' WHERE employee_id = "+inv_rs.getString(2)+" "
                    + "AND role_id = "+inv_rs.getString(3)+" AND project_id = "+inv_rs.getString(4)+" AND "
                    + "invoice_code = '"+inv_rs.getString(1)+"'";
            PreparedStatement resig_ps = objTargetConnection.prepareStatement(update_resg);
            resig_ps.executeUpdate();
            
        }
        
        
        String inv_cons_query = "SELECT invoice_code, employee_id, role_id, project_id, from_date,"
                + "to_date, consolidate_id FROM accrual_invoice_differences "
                + "WHERE employee_id IS NOT NULL AND billing_month = '"+month+"' "
                + "AND billing_year = '"+year+"' AND consolidate_id <> '0'";
        System.out.println("accrual_invo "+ inv_details_query);
        PreparedStatement inv_con_ps = objTargetConnection.prepareStatement(inv_cons_query);
        ResultSet inv_con_rs = inv_con_ps.executeQuery();
        while(inv_con_rs.next()){
            String accrual_id_query = "SELECT group_concat(DISTINCT(accrual_no)) FROM accrual_consolidations "
                    + "WHERE id = '"+inv_con_rs.getString(7) +"' AND status = 'm' "
                    + "GROUP BY emp_id, project_id, role_id";
            System.out.println("accrual_details "+ accrual_id_query);
            PreparedStatement accrual_ps = objTargetConnection.prepareStatement(accrual_id_query);
            ResultSet accrual_rs = accrual_ps.executeQuery();
            String accrual_no = "0";
            while(accrual_rs.next()){
                accrual_no = accrual_rs.getString(1);
            }
            //System.out.println("acrual NO "+accrual_no+" invoice_code = "+inv_rs.getString(1));
            String accrual_amount_query = "SELECT SUM(billable_amount) "
                    + "FROM accrual_consolidations WHERE emp_id = "+inv_con_rs.getString(2) +" AND "
                    + "project_id = "+inv_con_rs.getString(4)+" AND role_id = "+inv_con_rs.getString(3)+" "
                    + "AND accrual_no IN("+accrual_no+") AND status = 'm'";
            PreparedStatement accrual_amount_ps = objTargetConnection.prepareStatement(accrual_amount_query);
            ResultSet accrual_amount_s = accrual_amount_ps.executeQuery();
            String accrual_amount = "0.00";
            while(accrual_amount_s.next()){
                accrual_amount = accrual_amount_s.getString(1);
            }
            
            String update_resg = "UPDATE accrual_invoice_differences SET accrual_no = '"+accrual_no+"', "
                    + "accrued_amount = '"+accrual_amount+"' WHERE employee_id = "+inv_con_rs.getString(2)+" "
                    + "AND role_id = "+inv_con_rs.getString(3)+" AND project_id = "+inv_con_rs.getString(4)+" AND "
                    + "invoice_code = '"+inv_con_rs.getString(1)+"'";
            PreparedStatement resig_ps = objTargetConnection.prepareStatement(update_resg);
            resig_ps.executeUpdate();
            
        }
    }
}
