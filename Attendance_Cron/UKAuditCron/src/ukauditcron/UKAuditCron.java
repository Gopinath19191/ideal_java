/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ukauditcron;

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
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class UKAuditCron {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    static String fromAddress;
    static String mailPassword;
    static String mailHost;
    static String mailPort;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {
        // TODO code application logic here
        String configFile = new File("UKAuditLog.properties").getAbsolutePath();

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
        if (mailResult != null) {
            while (mailResult.next()) {
                if (mailResult.getRow() == 1) {
                    fromAddress = mailResult.getString(1);
                }
                if (mailResult.getRow() == 2) {
                    mailPassword = mailResult.getString(1);
                }
                if (mailResult.getRow() == 3) {
                    mailHost = mailResult.getString(1);
                }
                if (mailResult.getRow() == 4) {
                    mailPort = mailResult.getString(1);
                }
            }
        }
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(fromAddress, mailPassword, mailHost, mailPort);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        String empNumberQry = "SELECT DISTINCT id "
                + "FROM employees "
                + "WHERE current_location_id = 28 "
                + "AND employment_status NOT IN('r','t','b','q','o','y')";
        PreparedStatement psGetEmpNumbers = objTargetConnection.prepareStatement(empNumberQry);
        ResultSet rsEmpNumbers = psGetEmpNumbers.executeQuery();
        if (rsEmpNumbers != null) {
            String mailReceiver = "";
            String mailSubject = "BRP Expiry Mail Notification - Important";
            String mailMessage = "";
            String mailId = "";
            String mailCC = "";
            while (rsEmpNumbers.next()) {
                String visaExpiry = "SELECT CONCAT(emp.first_name,' ',emp.last_name),"
                        + "emp.work_email_address, "
                        + "rm.work_email_address, "
                        + "DATE_FORMAT(egd.brp_valid,'%d/%m/%Y') "
                        + "FROM `onsite_employee_genric_details` AS egd "
                        + "LEFT JOIN employees AS emp ON egd.employee_id = emp.id "
                        + "LEFT JOIN employees AS rm ON emp.manager = rm.id "
                        + "WHERE emp.id = " + rsEmpNumbers.getString(1) + " AND egd.brp_valid = DATE_ADD(CURDATE(), INTERVAL 90 DAY)";
                PreparedStatement vaisaQueryStatement = objTargetConnection.prepareStatement(visaExpiry);
                ResultSet visaList = vaisaQueryStatement.executeQuery();

                String mailCCList = "SELECT configuration_value FROM configuration_values WHERE id = 1007";
                PreparedStatement mailCCStatement = objTargetConnection.prepareStatement(mailCCList);
                ResultSet mailCCHR = mailCCStatement.executeQuery();

                if (visaList != null) {
                    while (visaList.next()) {
                        mailReceiver = "Dear " + visaList.getString(1);
                        mailId = visaList.getString(2);
                        mailCC = visaList.getString(3);
                        mailMessage = mailReceiver + ",<br><br>This is to inform that validity of your BRP status at UK will be expiring on " + visaList.getString(4) + "."
                                + "<br><br>Kindly request your Project Manager to inform the Immigration team and confirm the requirement to extend the BRP status at UK supported with Client letter, PO, SoW."
                                + "<br><br>For Immigration related queries, please write to Rajasekaran.kumar@hindujatech.com "
                                + "<br><br>For HR related queries, please write to Deepika.B@hindujatech.com / Harshavardhan.raju@hindujatech.com"
                                + "<br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd.";
                        if (mailCCHR != null) {
                            while (mailCCHR.next()) {
                                mailCC = mailCC + ", " + mailCCHR.getString(1);
                            }
                        }
                        try {
                             mailObj.smtpMail(mailId, mailSubject, mailMessage,mailCC);
                        } catch (Exception e) {
                            System.out.println("mail not sent");
                        }
                    }
                }
            }
        }

        String leaveList = "SELECT emp.employee_number, "
                            + "CONCAT(emp.first_name,' ',emp.last_name), "
                            + "DATE_FORMAT(elr.date_applied,'%d/%m/%Y'), "
                            + "elr.leave_reason, "
                            + "DATE_FORMAT(elr.from_date,'%d/%m/%Y'), "
                            + "DATE_FORMAT(elr.to_date,'%d/%m/%Y'), "
                            + "elr.length_days "
                            + "FROM employee_leave_requests AS elr "
                            + "LEFT JOIN employees AS emp ON elr.employee_id = emp.id "
                            + "WHERE elr.employee_id IN(SELECT id FROM employees WHERE current_location_id = 28) "
                            + "AND DATE(elr.date_applied) = DATE_SUB(CURDATE(),INTERVAL 1 DAY) "
                            + "AND elr.leave_status IN('a','o','t')";
        PreparedStatement leaveStatement = objTargetConnection.prepareStatement(leaveList);
        ResultSet leaveDetais = leaveStatement.executeQuery();
        if (leaveDetais != null) {
            String mailSubject = "HT UK Associates Leave Applied Status";
            String mailMessage = "";
            String mailId = "";
            String mailCC = "";
            int count = 0;
            mailMessage = "Dear HR Team,<br><br>This is to inform below are the new leave request submitted by HT UK employees  for your reference.<br><br>";
            String tabelContent = "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; "
                                + "padding:5px; background-color: #cccccc;' ><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Employee Id</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>"
                                + "Employee Name</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Leave Applied On</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>"
                                + "Leave From</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Leave To</th><th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Total No of Leaves</th>"
                                + "<th style='border: 1px solid #808080; border-collapse: collapse; background-color: #cccccc;'>Reason For Leave</th></tr>";
            while (leaveDetais.next()) {
                count++;
                tabelContent += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(1) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(2) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(3) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(5) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(6) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(7) + "</td>"
                                + "<td style='border: 1px solid #808080; border-collapse: collapse;'>" + leaveDetais.getString(4) + "</td>"
                                + "</tr>";
            }
            tabelContent += "</table>";
            mailMessage += tabelContent + "<br><br>Regards,<br>Ideal Admin";

            String mailCCList = "SELECT configuration_value FROM configuration_values WHERE id = 1009";
            PreparedStatement mailCCStatement = objTargetConnection.prepareStatement(mailCCList);
            ResultSet mailCCHR = mailCCStatement.executeQuery();
            if (mailCCHR != null) {
                while (mailCCHR.next()) {
                    mailCC = mailCCHR.getString(1);
                }
            }
            String mailToList = "SELECT configuration_value FROM configuration_values WHERE id = 1008";
            PreparedStatement mailToStatement = objTargetConnection.prepareStatement(mailToList);
            ResultSet mailToHR = mailToStatement.executeQuery();
            if (mailToHR != null) {
                while (mailToHR.next()) {
                    mailId = mailToHR.getString(1);
                }
            }
            try {
                if (count > 0) {
                     mailObj.smtpMail(mailId, mailSubject, mailMessage,mailCC);
                }
            } catch (Exception e) {
                System.out.println("mail not sent");
            }
        }
        Date curdate = new Date();
        if (curdate.getMonth() / 2 == 0 && curdate.getDate() == 15) {
            System.out.println("comes here");
            String empId = "SELECT DISTINCT id "
                    + "FROM employees "
                    + "WHERE current_location_id = 28 "
                    + "AND employment_status NOT IN('r','t','b','q','o','y')";
            PreparedStatement empIdQry = objTargetConnection.prepareStatement(empId);
            ResultSet empIdList = empIdQry.executeQuery();
            if (empIdList != null) {
                String mailReceiver = "";
                String mailSubject = "Update Your Mandatory Details in iDEAL For UK - Important";
                String mailMessage = "";
                String mailId = "";
                String mailCC = "";
                while (empIdList.next()) {
                    String empDetails = "SELECT CONCAT(emp.first_name,' ',emp.last_name),"
                                        + "emp.work_email_address, "
                                        + "rm.work_email_address "
                                        + "FROM employees AS emp "
                                        + "LEFT JOIN employees AS rm ON emp.manager = rm.id "
                                        + "WHERE emp.id = " + empIdList.getString(1);
                    PreparedStatement empDetailsStm = objTargetConnection.prepareStatement(empDetails);
                    ResultSet empDetailsList = empDetailsStm.executeQuery();

                    String mailCCList = "SELECT configuration_value FROM configuration_values WHERE id = 1010";
                    PreparedStatement mailCCStatement = objTargetConnection.prepareStatement(mailCCList);
                    ResultSet mailCCHR = mailCCStatement.executeQuery();

                    if (empDetailsList != null) {
                        while (empDetailsList.next()) {
                            mailReceiver = "Dear " + empDetailsList.getString(1);
                            mailId = empDetailsList.getString(2);
                            mailMessage = mailReceiver + ",<br><br>It is mandatory to update your UK contact details on a regular basis."
                                            + "<br><br>To update your information, please login to iDeal."
                                            + "<br><br>Note: This is the system generated mail, please reach out to Deepika.B@hindujatech.com / Harshavardhan.raju@hindujatech.com /hr_operations@hindujatech.com for further queries."
                                            + "<br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd.";
                            if (mailCCHR != null) {
                                while (mailCCHR.next()) {
                                    mailCC = mailCCHR.getString(1);
                                }
                            }
                            try {
                                mailObj.smtpMail(mailId, mailSubject, mailMessage, mailCC);
                            } catch (Exception e) {
                                System.out.println("mail not sent");
                            }
                        }
                    }
                }
            }
        }
        try {
            String mailId = "gopinath.elangovan@hindujatech.com";
            String mailSubject = "UK Cron Status Success";
            String mailMessage = "Hi, <br>Mail status success";
            mailObj.smtpMail(mailId, mailSubject, mailMessage, "");
        } catch (Exception e) {
            System.out.println("mail not sent");
        }
    }
}
