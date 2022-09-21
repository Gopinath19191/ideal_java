package birthdayandanniversaryjob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class BirthdayAndAnniversaryJob {

    static String url = "";
    static String user = "";
    static String password = "";
    static String fromAddress = "";
    static String mailPassword = "";
    static String mailHost = "";
    static String mailPort = "";
    static String mailTo = "";
    static String mailCc = "";
    static String birthdaySubject = "";
    static String birthdayContent = "";
    static String anniversarySubject = "";
    static String anniversaryContent = "";
    static String mailMessage = "";
    static String mailSubject = "";

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ParseException, Exception {

        FileInputStream dbfile = new FileInputStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(dbfile);
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");

        Connection dbConnection = (Connection) DriverManager.getConnection(url, user, password);
        String configQuery = "SELECT configuration_value FROM configuration_values WHERE parent_id = 10";
        PreparedStatement configuration = dbConnection.prepareStatement(configQuery);
        ResultSet rs = configuration.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                if (rs.getRow() == 1) {
                    fromAddress = rs.getString(1);
                }
                if (rs.getRow() == 2) {
                    mailPassword = rs.getString(1);
                }
                if (rs.getRow() == 3) {
                    mailHost = rs.getString(1);
                }
                if (rs.getRow() == 4) {
                    mailPort = rs.getString(1);
                }
            }
        }
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(fromAddress, mailPassword, mailHost, mailPort);
            //mailObj = new SendMail("kalaiarasi.padaikathu@hindujatech.com", "Kalaikalai21@28", mailHost, mailPort);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        FileInputStream wishesfile = new FileInputStream("wishes.properties");
        Properties props = new Properties();
        props.load(wishesfile);
        
        String birthday_query = "SELECT employees.id AS employee_id,\n"
                + "CONCAT(employees.first_name,' ',employees.last_name) AS employee_name,\n"
                + "CONCAT( rm.employee_number, ' - ', rm.first_name, ' ', rm.last_name ) AS rm_name,\n"
                + "employees.work_email_address AS employee_mail_id,\n"
                + "rm.work_email_address AS manager_mail_id\n"
                + "FROM employees\n"
                + "LEFT JOIN employees AS rm ON (employees.manager = rm.id)\n"
                + "WHERE employees.employment_status NOT IN ('r','t','b','q','o','y')\n"
                + "AND MONTH(employees.birth_date) = MONTH(NOW()) AND DAY(employees.birth_date) = DAY(NOW())";

        PreparedStatement birthday_ps = dbConnection.prepareStatement(birthday_query);
        ResultSet birthday_rs = birthday_ps.executeQuery();
       
        while (birthday_rs.next()) {
            birthdaySubject = props.getProperty("BirthdaySubject");
            birthdayContent = props.getProperty("BirthdayContent");
           
            mailTo = birthday_rs.getString(4);
            mailCc = birthday_rs.getString(5) + "," + props.getProperty("MailCC");
        
            final File folder = new File("D:\\wishesimages");
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File folder, String name) {
                    return name.startsWith("Birthday");
                }
            };
            File[] files = folder.listFiles(filter);
            Random rand = new Random();
            File file = files[rand.nextInt(files.length)];
            String attachedFile = file.getName();
           
            Map<String, String> inlineImages = new HashMap<String, String>();
            inlineImages.put("image1", attachedFile);
            
            System.out.println("mailTo "+mailTo);
            System.out.println("mailcc "+mailCc);
            try {           
                mailObj.smtpMailBodyAttachment(mailTo, birthdaySubject, birthdayContent, mailCc, inlineImages);
            } catch (Exception e) {
                System.out.println("mail sent issue " + e);
            }
        }
    }
}
