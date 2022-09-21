/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remindermail;

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
import java.util.TimeZone;

/**
 *
 * @author 16221
 */
public class ReminderMail {

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
    static String eventMaster = "";
    static String locationId = ""; 
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {
        // TODO code application logic here
        String configFile = new File("ReminderJob.properties").getAbsolutePath();

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
        eventMaster = properties.getProperty("MASTERID");
        locationId = properties.getProperty("WORKLOCATION");
        mailTo = properties.getProperty("MAILTO");
        mailCc = properties.getProperty("MAILCC");
        mailSubject = properties.getProperty("MAILSUBJECT");
        mailMessage = properties.getProperty("MAILMESSAGE");
        Calendar calendar = Calendar.getInstance();
        int current_day = calendar.get(Calendar.DAY_OF_WEEK);
        if(current_day >= Calendar.TUESDAY && current_day <= Calendar.FRIDAY){
            String empNumberQry = "SELECT emp.work_email_address FROM employees AS emp "
                                +"LEFT JOIN event_datas AS ed ON emp.id = ed.employee_id AND ed.master_id = "+ eventMaster+" "
                                +"WHERE emp.employment_status NOT IN ('r','t','b','q','o','y') AND ed.created_on IS NULL "
                                +"AND emp.current_location_id in("+locationId+")";
            System.out.println(empNumberQry);
            PreparedStatement psGetEmpNumbers = objTargetConnection.prepareStatement(empNumberQry);
            ResultSet rsEmpNumbers = psGetEmpNumbers.executeQuery();
            Date curdate = new Date();
            DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String current_date = sdf.format(curdate).toString();
            int mail_count = 0;
            String concat_mailid="";
            if (rsEmpNumbers != null) {
                int employee_count = 0;
                while (rsEmpNumbers.next()) {
                    employee_count = employee_count+1;
                    concat_mailid = concat_mailid+", "+rsEmpNumbers.getString(1);
                    if(employee_count==95){
                        employee_count=0;
                        try{
                           // mailObj.smtpMail(mailTo, mailSubject+" - "+current_date, mailMessage,mailCc,concat_mailid);
                            mail_count=mail_count+1;
                        }catch(Exception e){
                            System.out.println("mail not sent");
                        }
                        concat_mailid="";
                    }

                }
            }
            mailObj.smtpMail(mailTo, mailSubject+" - "+current_date, mailMessage,mailCc,concat_mailid);
            mailObj.smtpMail("gopinath.elangovan@hindujatech.com", mailSubject+" - "+current_date, String.valueOf(mail_count),mailCc,concat_mailid);
        }else{
            mailObj.smtpMail("gopinath.elangovan@hindujatech.com", mailSubject, "No mail sent today","","");
        }
    }
}
