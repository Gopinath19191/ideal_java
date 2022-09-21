/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsettlementpendingjob;

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
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author 16221
 */
public class TravelSettlementPendingJob {

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
    static String mailSubject = "Reg: Travel Settlement Pending";
    
    public static void main(String[] args)  throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {
        
        String configFile = new File("TravelSettlementConfiguration.properties").getAbsolutePath();

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
        String empNumberQry = "SELECT DISTINCT tp.employee_id "
                              +"FROM tp_master AS tp "
                              +"LEFT JOIN employees AS emp ON tp.employee_id = emp.id "
                              +"WHERE tp.travel_type = 'D' AND tp.travel_requested_date > '2019-04-01' AND tp.status IN('6','8','9','10','11') AND emp.id <> 3650 "
                              +"AND emp.employment_status NOT IN('r','t','b','q','o','y') AND tp.travel_end_date < DATE_SUB(CURDATE(),INTERVAL 3 DAY)";
        PreparedStatement psGetEmpNumbers = objTargetConnection.prepareStatement(empNumberQry);
        ResultSet rsEmpNumbers = psGetEmpNumbers.executeQuery();
        int mail_count = 0;
        String mailReceiver="";
        String mailMessage = "";
        String mailId="";
        if (rsEmpNumbers != null) {
            while (rsEmpNumbers.next()) {
                String travelQuery = "SELECT CONCAT(emp.first_name,' ',emp.last_name), emp.work_email_address, tp.tp_reference_id, tp.travel_requested_date, tp.travel_start_date, tp.travel_end_date, COALESCE(tad.deposited_amount,0)"
                                     +" FROM tp_master AS tp "
                                     +" LEFT JOIN employees AS emp ON tp.employee_id = emp.id"
                                     +" LEFT JOIN tp_advance_details AS tad ON tp.id = tad.master_id"
                                     +" WHERE travel_requested_date > '2019-04-01' AND tp.status IN ('6','8','9','10','11') AND tp.travel_end_date < DATE_SUB(CURDATE(),INTERVAL 3 DAY) AND employee_id = "+rsEmpNumbers.getString(1);
                PreparedStatement travelQueryStatement = objTargetConnection.prepareStatement(travelQuery);
                ResultSet travelList = travelQueryStatement.executeQuery();
                int count = 0;
                if(travelList != null){
                    String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>TP Reference Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Travel Requested Date</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Travel Start Date</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Travel End Date</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Advance Received</th></tr>";
                    while(travelList.next()){
                        count = count + 1;
                        mailReceiver = "Dear "+travelList.getString(1);
                        mailId = travelList.getString(2);
                        tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse;'>"+travelList.getString(3)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+travelList.getString(4)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+travelList.getString(5)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+travelList.getString(6)+"</td><td style='border: 1px solid #808080; border-collapse: collapse;'>"+travelList.getString(7)+"</td></tr>";
                    }
                    if(count>0){
                        tabelContent += "</table>";
                        mailMessage = mailReceiver+",<br><br>"+"As per the Travel Policy, Travel expense report and settlement has to be completed within 3 days from completion of Travel.<br>"
                                     +"It is observed that for the following Travel Plan(s),  Report submission and Settlement are still pending<br><br>" +tabelContent
                                     +"<br>You are requested to submit the report through IDEAL portal and settle the same without any further delay."
                                     +"<br>Any further advance request will be considered only after the settlement of pending Travels.<br><br>"
                                     +"Note: Please dont reply to this mail in case of any queries please reach Treasury@hindujatech.com.<br><br>Regards, <br>Finance Team.";
                        System.out.println("end one employee "+rsEmpNumbers.getString(1));
                        try{
                            mailObj.smtpMail(mailId, mailSubject, mailMessage,"");
                            mail_count=mail_count+1;
                        }catch(Exception e){
                            System.out.println("mail not sent");
                        }
                    }
                }
                
            }
        }
        String mailMessageFinal = "Hi,<br><br>travel settlement mail set count "+mail_count+" Last received mail id "+mailId;
        try{
            mailObj.smtpMail("gopinath.elangovan@hindujatech.com", mailSubject, mailMessageFinal,"");
            mail_count=mail_count+1;
        }catch(Exception e){
            System.out.println("mail not sent");
        }
    }
}
