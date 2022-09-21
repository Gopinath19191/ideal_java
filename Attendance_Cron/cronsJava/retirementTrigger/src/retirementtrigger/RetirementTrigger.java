/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package retirementtrigger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class RetirementTrigger {

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
    
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ParseException, Exception {
        // TODO code application logic here
        String configFile = new File("retirement_trigger.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        
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
        
        String notify_query = "SELECT emp.id, emp.birth_date, CONCAT(emp.first_name,' ',emp.last_name), "
                + "emp.work_email_address, "
                + "DATE_FORMAT(DATE_ADD(emp.birth_date, INTERVAL 691 MONTH), '%Y-%m-01') AS notification_date, "
                + "DATE_ADD(LAST_DAY(DATE_ADD(emp.birth_date, INTERVAL 58 YEAR)), INTERVAL -89 DAY) AS trigger_date, "
                + "DATE_FORMAT(LAST_DAY(DATE_ADD(emp.birth_date, INTERVAL 58 YEAR)),'%d-%m-%Y') AS last_working_date,"
                + "rm.work_email_address "
                + "FROM employees AS emp "
                + "LEFT JOIN company_locations AS cm ON emp.company_location_id = cm.id "
                + "LEFT JOIN employees AS rm ON emp.manager = rm.id "
                + "WHERE emp.employment_status NOT IN('r','t','b','q','o','y','d','n','v') "
                + "AND cm.country_id = 113 AND DATE_FORMAT(DATE_ADD(emp.birth_date, INTERVAL 691 MONTH),'%Y-%m-01') = CURDATE() ";
        
        PreparedStatement notify_ps = objTargetConnection.prepareStatement(notify_query);
        ResultSet notify_rs = notify_ps.executeQuery();
        System.out.println("sdfsdf "+notify_query);
        while(notify_rs.next()){
            mailMessage = properties.getProperty("NOTIFYMAIL");
            mailMessage = mailMessage.replace("data[0]", notify_rs.getString(3));
            mailMessage = mailMessage.replace("data[1]", notify_rs.getString(7));
            mailSubject = "REG: Retirement Notification";
            mailTo = notify_rs.getString(4);
            mailCc = notify_rs.getString(8) +","+ properties.getProperty("MailCC");
            try{
                mailObj.smtpMail(mailTo, mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
            }catch(Exception e){
            
            }
        }
        String update_resignation = "SELECT emp.id, emp.birth_date, CONCAT(emp.first_name,' ',emp.last_name), emp.work_email_address, "
                + "DATE_FORMAT(DATE_ADD(emp.birth_date, INTERVAL 691 MONTH), '%Y-%m-01') AS notification_date, "
                + "DATE_ADD(LAST_DAY(DATE_ADD(emp.birth_date, INTERVAL 58 YEAR)), INTERVAL -89 DAY) AS trigger_date, "
                + "LAST_DAY(DATE_ADD(emp.birth_date, INTERVAL 58 YEAR)) AS last_working_date, "
                + "emp.manager AS rm_id, CONCAT(rm.first_name,' ',rm.last_name), rm.work_email_address, emp.structure_name "
                + "FROM employees AS emp "
                + "LEFT JOIN company_locations AS cm ON emp.company_location_id = cm.id "
                + "LEFT JOIN employees as rm ON emp.manager = rm.id "
                + "WHERE emp.employment_status NOT IN('r','t','b','q','o','y','d','n','v') "
                + "AND cm.country_id = 113 AND DATE_ADD(LAST_DAY(DATE_ADD(emp.birth_date, INTERVAL 58 YEAR)), INTERVAL -89 DAY) = CURDATE() ";
        System.out.println(""+update_resignation);
        PreparedStatement resignation_ps = objTargetConnection.prepareStatement(update_resignation);
        ResultSet resignation_rs = resignation_ps.executeQuery();
        while(resignation_rs.next()){
            String get_address = "SELECT id FROM employee_addresses WHERE employee_id= "+resignation_rs.getString(1)+" and deleted = 0 LIMIT 1";
            PreparedStatement address_ps = objTargetConnection.prepareStatement(get_address);
            ResultSet address_rs = address_ps.executeQuery();
            address_rs.first();
            String address_id = address_rs.getString(1);
            
            String update_resg = "UPDATE employees set resigned_date = '"+resignation_rs.getString(6)+"', released_date = '"+resignation_rs.getString(7)+"' WHERE id = "+resignation_rs.getString(1);
            PreparedStatement resig_ps = objTargetConnection.prepareStatement(update_resg);
            resig_ps.executeUpdate();
            
            String inst_exit = "INSERT INTO dtl_exit_employee(employee_id,ffs_address_id,leaving_reason,submit_status,deleted) "
                    + "VALUES(?,?,'595',3,'N');";
            PreparedStatement inst_exit_ps = objTargetConnection.prepareStatement(inst_exit, Statement.RETURN_GENERATED_KEYS);
            inst_exit_ps.setInt(1, Integer.parseInt(resignation_rs.getString(1)));
            inst_exit_ps.setInt(2, Integer.parseInt(address_id));
            inst_exit_ps.execute();
            ResultSet rs = inst_exit_ps.getGeneratedKeys();
            rs.first();
            
            String exit_rm_update = "INSERT INTO dtl_exit_rm_approval(exit_emp_id,approver_id,approved_date,exit_trigger_date,notice_wavier_value,survey_empid) "
                    + "values(?,?,?,?,3,?);";
            PreparedStatement exit_rm_ps = objTargetConnection.prepareStatement(exit_rm_update);
            exit_rm_ps.setInt(1, rs.getInt(1));
            exit_rm_ps.setInt(2, Integer.parseInt(resignation_rs.getString(8)));
            exit_rm_ps.setString(3, resignation_rs.getString(6));
            exit_rm_ps.setString(4, resignation_rs.getString(6));
            exit_rm_ps.setString(5, resignation_rs.getString(1));
            exit_rm_ps.execute();
            
            inst_exit_ps.clearParameters();
            inst_exit_ps.close();
            exit_rm_ps.clearParameters();
            exit_rm_ps.close();
            mailTo = resignation_rs.getString(4);
            String to_list = "select GROUP_CONCAT(dl_mail) as rmApprovaldlto from dl_master dm "
                    + "inner join dl_function_master dfm on (dfm.dl_id=dm.id) "
                    + "where dfm.function_id=77 and mail_to_type='to' and structure_id is null";
            PreparedStatement to_list_ps = objTargetConnection.prepareStatement(to_list);
            ResultSet to_list_rs = to_list_ps.executeQuery();
            to_list_rs.first();
            mailTo = mailTo+","+to_list_rs.getString(1);
            String buh_mail_id="";
            if(resignation_rs.getString(11).equals("5") || resignation_rs.getString(11).equals("2")){
                String buh_mail = "SELECT GROUP_CONCAT(dl_mail) AS buhmail FROM dl_master dm "
                        + "INNER JOIN dl_function_master dfm ON (dfm.dl_id=dm.id) "
                        + "WHERE dfm.function_id=76 AND mail_to_type='cc' AND structure_id = "+resignation_rs.getString(11);
                PreparedStatement buh_cc = objTargetConnection.prepareStatement(buh_mail);
                ResultSet buh_rs = buh_cc.executeQuery();
                buh_rs.first();
                buh_mail_id = buh_rs.getString(1);
            }
            String cc_list = "SELECT GROUP_CONCAT(dl_mail) AS rmApprovaldl FROM dl_master dm "
                    + "INNER JOIN dl_function_master dfm ON (dfm.dl_id=dm.id) "
                    + "WHERE dfm.function_id=77 AND mail_to_type='cc' AND structure_id IS NULL";
            PreparedStatement cc_list_ps = objTargetConnection.prepareStatement(cc_list);
            ResultSet cc_list_rs = cc_list_ps.executeQuery();
            cc_list_rs.first();
            mailCc = resignation_rs.getString(10) +","+cc_list_rs.getString(1);
            if(buh_mail_id!=""){
                mailCc = mailCc+","+buh_mail_id;
            }
            DateFormat dmy = new SimpleDateFormat("dd-m-y");
            Date lwd = new SimpleDateFormat("yyyy-mm-dd").parse(resignation_rs.getString(7));
            mailMessage = properties.getProperty("rmApproveToEmp");
            mailMessage = mailMessage.replace("data[0]", resignation_rs.getString(3));
            mailMessage = mailMessage.replace("data[3]", resignation_rs.getString(9));
            mailMessage = mailMessage.replace("data[7]", dmy.format(lwd).toString());
            mailSubject = "Reg: Resignation Form Approved";
            System.out.println("mail to "+mailTo);
            System.out.println("mail cc "+mailCc);
            System.out.println("mail to "+mailMessage);
            try{
                mailObj.smtpMail(mailTo, mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
            }catch(Exception e){
                
            }
        }
        
        
        // LWD update trigger for payroll
        Date curdate = new Date();
        DateFormat dat = new SimpleDateFormat("dd");
        DateFormat mnth = new SimpleDateFormat("MMMM");
        DateFormat year = new SimpleDateFormat("yyyy");
        String da=dat.format(curdate);
        String cut_month=mnth.format(curdate);
        String cut_year=year.format(curdate);
        int current_date = Integer.valueOf(da);
        
        if(current_date > 24 && current_date < 29){
            String get_manager_id = "SELECT DISTINCT(emp.manager), CONCAT(mng.first_name,' ',mng.last_name), "
                    + "mng.work_email_address FROM dtl_exit_employee AS dee "
                    + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                    + "LEFT JOIN employees AS mng ON emp.manager = mng.id "
                    + "WHERE dee.deleted = 'N' AND dee.submit_status = 2 "
                    + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                    + "AND emp.released_date IS NULL";
            PreparedStatement manager_ps = objTargetConnection.prepareStatement(get_manager_id);
            ResultSet manager_rs = manager_ps.executeQuery();
            while(manager_rs.next()){
                String get_employee = "SELECT emp.employee_number, "
                        + "CONCAT(emp.first_name,' ',emp.last_name), date_format(emp.resigned_date,'%d-%m-%Y'), "
                        + "(DATEDIFF(CURDATE(),emp.resigned_date)+1) "
                        + "FROM dtl_exit_employee AS dee "
                        + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                        + "WHERE dee.deleted = 'N' AND dee.submit_status = 2 "
                        + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                        + "AND emp.released_date IS NULL AND emp.manager = "+manager_rs.getString(1)+" "
                        + "ORDER BY emp.resigned_date";
                PreparedStatement employee_ps = objTargetConnection.prepareStatement(get_employee);
                ResultSet employee_rs = employee_ps.executeQuery();
                String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Name</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Resigned Date</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>No of Days Served</th></tr>";
                while(employee_rs.next()){
                    tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(1)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(2)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(3)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(4)+"</td></tr>";
                }
                tabelContent += "</table>";
                mailMessage = properties.getProperty("lwdUpdate");
                mailMessage = mailMessage.replace("data[0]", manager_rs.getString(2));
                mailMessage = mailMessage.replace("data[1]", tabelContent);
                mailSubject = "Payroll Freeze Reminder for "+cut_month+"'"+cut_year;
                mailCc = properties.getProperty("MailCC");
                try{
                    mailObj.smtpMail(manager_rs.getString(3), mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                    //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
                }catch(Exception e){
                }
                
            }
        }
        
        
        // LWD update nearing notice period

        String get_manager_id = "SELECT DISTINCT(emp.manager), CONCAT(mng.first_name,' ',mng.last_name), "
                + "mng.work_email_address FROM dtl_exit_employee AS dee "
                + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                + "LEFT JOIN employees AS mng ON emp.manager = mng.id "
                + "WHERE dee.deleted = 'N' AND dee.submit_status = 2 "
                + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                + "AND emp.released_date IS NULL AND (DATEDIFF(CURDATE(),emp.resigned_date)+1)>80";
        PreparedStatement manager_ps = objTargetConnection.prepareStatement(get_manager_id);
        ResultSet manager_rs = manager_ps.executeQuery();
        while(manager_rs.next()){
            String get_employee = "SELECT emp.employee_number, "
                    + "CONCAT(emp.first_name,' ',emp.last_name), DATE_FORMAT(emp.resigned_date,'%d-%m-%Y'), "
                    + "(DATEDIFF(CURDATE(),emp.resigned_date)+1) "
                    + "FROM dtl_exit_employee AS dee "
                    + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                    + "WHERE dee.deleted = 'N' AND dee.submit_status = 2 "
                    + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                    + "AND emp.released_date IS NULL AND (DATEDIFF(CURDATE(),emp.resigned_date)+1)>80 "
                    + "AND emp.manager = "+manager_rs.getString(1);
            PreparedStatement employee_ps = objTargetConnection.prepareStatement(get_employee);
            ResultSet employee_rs = employee_ps.executeQuery();
            String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Name</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Resigned Date</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>No of Days Served</th></tr>";
            while(employee_rs.next()){
                tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(1)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(2)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(3)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(4)+"</td></tr>";
            }
            tabelContent += "</table>";
            mailMessage = properties.getProperty("lwdUpdateNotice");
            mailMessage = mailMessage.replace("data[0]", manager_rs.getString(2));
            mailMessage = mailMessage.replace("data[1]", tabelContent);
            mailSubject = "Final Reminder for Resignation Approval / Rejection";
            mailCc = properties.getProperty("MailCC");
            try{
                mailObj.smtpMail(manager_rs.getString(3), mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
            }catch(Exception e){
            }

        }
        
        //reprtees change notification
        String resigned_id = "SELECT emp.id, emp.employee_number, CONCAT(emp.first_name,' ',emp.last_name), "
                + "emp.work_email_address, mng.work_email_address "
                + "FROM dtl_exit_employee AS dee "
                + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                + "LEFT JOIN employees AS mng ON emp.manager = mng.id "
                + "WHERE dee.deleted = 'N' AND dee.submit_status = 2 "
                + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                + "AND emp.released_date IS NULL AND ((DATEDIFF(CURDATE(),emp.resigned_date)+1) = 15 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 30 OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 45 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 60 OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 75 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) > 80);";
        PreparedStatement resigned_ps = objTargetConnection.prepareStatement(resigned_id);
        ResultSet resigned_rs = resigned_ps.executeQuery();
        while(resigned_rs.next()){
            int reportee_count=0;
            String get_employee = "SELECT emp.employee_number, CONCAT(emp.first_name,' ',emp.last_name), band.name "
                    + "FROM employees AS emp "
                    + "LEFT JOIN bands AS band ON emp.band_id = band.id "
                    + "WHERE emp.employment_status NOT IN('r','t','b','q','o','y') AND emp.manager = "+resigned_rs.getString(1)+" "
                    + "ORDER BY band.sort_order,emp.employee_number";
            PreparedStatement employee_ps = objTargetConnection.prepareStatement(get_employee);
            ResultSet employee_rs = employee_ps.executeQuery();
            String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Name</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Band</th></tr>";
            while(employee_rs.next()){
                reportee_count++;
                tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(1)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(2)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(3)+"</td></tr>";
            }
            tabelContent += "</table>";
            mailMessage = properties.getProperty("rmChangeUpdate");
            mailMessage = mailMessage.replace("data[0]", resigned_rs.getString(3));
            mailMessage = mailMessage.replace("data[1]", tabelContent);
            mailSubject = "RM Change Needs to be done for your team members "+resigned_rs.getString(2)+" - "+resigned_rs.getString(3);
            mailCc = properties.getProperty("MailCC")+","+resigned_rs.getString(5);
            if(reportee_count>0){
                try{
                    mailObj.smtpMail(resigned_rs.getString(4), mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                    //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
                }catch(Exception e){
                }
            }

        }
        
        //lwd updated cases
        String lwd_id = "SELECT emp.id, emp.employee_number, CONCAT(emp.first_name,' ',emp.last_name), "
                + "emp.work_email_address, mng.work_email_address "
                + "FROM dtl_exit_employee AS dee "
                + "LEFT JOIN employees AS emp ON dee.employee_id = emp.id "
                + "left join employees as mng on emp.manager = mng.id "
                + "WHERE dee.deleted = 'N' AND dee.submit_status = 3 "
                + "AND emp.employment_status NOT IN('r','t','b','q','o','y') "
                + "AND emp.released_date IS NOT NULL AND ((DATEDIFF(emp.released_date,CURDATE())+1) <=10 OR"
                + "(DATEDIFF(CURDATE(),emp.resigned_date)+1) = 15 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 30 OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 45 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 60 OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) = 75 "
                + "OR (DATEDIFF(CURDATE(),emp.resigned_date)+1) > 80)";
        PreparedStatement lwd_ps = objTargetConnection.prepareStatement(lwd_id);
        ResultSet lwd_rs = lwd_ps.executeQuery();
        while(lwd_rs.next()){
            int reportee_count=0;
            String get_employee = "SELECT emp.employee_number, CONCAT(emp.first_name,' ',emp.last_name), band.name "
                    + "FROM employees AS emp "
                    + "LEFT JOIN bands AS band ON emp.band_id = band.id "
                    + "WHERE emp.employment_status NOT IN('r','t','b','q','o','y') AND emp.manager = "+lwd_rs.getString(1)+" "
                    + "ORDER BY band.sort_order,emp.employee_number";
            PreparedStatement employee_ps = objTargetConnection.prepareStatement(get_employee);
            ResultSet employee_rs = employee_ps.executeQuery();
            String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Name</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Band</th></tr>";
            while(employee_rs.next()){
                reportee_count++;
                tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(1)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(2)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+employee_rs.getString(3)+"</td></tr>";
            }
            tabelContent += "</table>";
            mailMessage = properties.getProperty("rmChangeUpdate");
            mailMessage = mailMessage.replace("data[0]", lwd_rs.getString(3));
            mailMessage = mailMessage.replace("data[1]", tabelContent);
            mailSubject = "RM Change Needs to be done for your team members "+lwd_rs.getString(2)+" - "+lwd_rs.getString(3);
            mailCc = properties.getProperty("MailCC")+","+lwd_rs.getString(5);
            if(reportee_count>0){
                try{
                    mailObj.smtpMail(lwd_rs.getString(4), mailSubject, mailMessage,mailCc,"gopinath.elangovan@hindujatech.com");
                    //mailObj.smtpMail("", mailSubject, mailMessage,"","gopinath.elangovan@hindujatech.com");
                }catch(Exception e){
                }
            }
        }
    }
}
