package headcountjob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HeadCountJob {

    static String url = "";
    static String user = "";
    static String password = "";
    static String fromAddress = "";
    static String mailPassword = "";
    static String mailHost = "";
    static String mailPort = "";
    static String mailTo = "";
    static String mailCc = "";
    static String mailMessage = "";
    static String mailSubject = "";

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ParseException, Exception {

        FileInputStream dbfile = new FileInputStream("hc_jdbc.properties");
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

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        FileInputStream mail = new FileInputStream("hc_mailbody.properties");
        Properties props = new Properties();
        props.load(mail);

        String employees_query = "SELECT emp.employee_number AS Employee_id, CONCAT(emp.first_name,' ',emp.last_name) AS Employee_name,\n"
                + "band.name AS band_name,\n"
                + "designations.designation AS designation,\n"
                + "emp.joined_date AS joined_date,\n"
                + "emp.released_date AS released_date,\n"
                + "cty.city AS Location,\n"
                + "comstrct.name AS unit,\n"
                + "comstrct1.name AS practice,\n"
                + "comstrct2.name AS sub_practice, \n"
                + "gn.configuration_value AS gender, \n"
                + "emp.mobile_number AS mobile_no, \n"
                + "emp.work_email_address AS email_id \n"
                + "FROM employees AS emp \n"
                + "LEFT JOIN bands AS band ON band.id = emp.band_id \n"
                + "LEFT JOIN designations AS designations ON designations.id = emp.designation_id \n"
                + "LEFT JOIN company_locations AS cl ON cl.id = emp.current_location_id \n"
                + "LEFT JOIN cities AS cty ON cty.id = cl.city_id \n"
                + "LEFT JOIN company_structures AS comstrct ON comstrct.id = emp.structure_name \n"
                + "LEFT JOIN company_structures AS comstrct1 ON comstrct1.id = emp.structure_name_subgroup \n"
                + "LEFT JOIN company_structures AS comstrct2 ON comstrct2.id = emp.structure_name_subpractice \n"
                + "LEFT JOIN configuration_values AS gn ON (emp.gender = gn.configuration_key AND gn.parent_id = 39) \n"
                + "WHERE emp.employment_status NOT IN('r','t','b','q','o','y') AND  emp.joined_date < CURDATE() AND (emp.released_date >= CURDATE() OR emp.released_date IS NULL)";

        PreparedStatement employees_ps = dbConnection.prepareStatement(employees_query);
        ResultSet employees_rs = employees_ps.executeQuery();

        Calendar cal = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("dd_MM_yyyy");
        DateFormat format_mail = new SimpleDateFormat("dd-MMM-yyyy");
        cal.add(Calendar.DATE, -1);
        String yesterdayDate = format.format(cal.getTime());

        String ExcelFileName = "HeadCount_" + yesterdayDate + ".xls";
        String sheetName = "HeadCouunt_" + yesterdayDate;//name of sheet
        File file = new File("D://HeadCountJobWorksheet//" + ExcelFileName);
        file.delete();
        file.createNewFile();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        String headerArray[] = new String[]{"Employee Id", "Employee Name", "Band Name", "Designation", "Joined Date",
            "Released Date", "Location", "Unit", "Practice", "Sub Practice", "Gender", "Mobile No", "Mail Id"};

        HSSFRow row = sheet.createRow(0);
        for (short c = 0; c < headerArray.length; c++) {
            HSSFCell cell = row.createCell(c);
            cell.setCellValue(headerArray[c]);
        }

        int i = 0;

        while (employees_rs.next()) {
            String rowArray[] = new String[13];
            rowArray[0] = employees_rs.getString(1);
            rowArray[1] = employees_rs.getString(2);
            rowArray[2] = employees_rs.getString(3);
            rowArray[3] = employees_rs.getString(4);
            rowArray[4] = employees_rs.getString(5);
            rowArray[5] = employees_rs.getString(6);
            rowArray[6] = employees_rs.getString(7);
            rowArray[7] = employees_rs.getString(8);
            rowArray[8] = employees_rs.getString(9);
            rowArray[9] = employees_rs.getString(10);
            rowArray[10] = employees_rs.getString(11);
            rowArray[11] = employees_rs.getString(12);
            rowArray[12] = employees_rs.getString(13);
            i = i + 1;
            HSSFRow rowElement = sheet.createRow(i);
            for (short c = 0; c < rowArray.length; c++) {
                HSSFCell cell = rowElement.createCell(c);
                cell.setCellValue(rowArray[c]);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();

        employees_rs.last();
        int employeeHeadCount = employees_rs.getRow();
        System.out.println("employeeHeadCount:" + employeeHeadCount);

        mailSubject = props.getProperty("mailSubject");
        mailSubject = mailSubject.replace("data[0]", format_mail.format(cal.getTime()));
        mailMessage = props.getProperty("mailMessage");
        mailMessage = mailMessage.replace("data[0]", format_mail.format(cal.getTime()));
        mailMessage = mailMessage.replace("data[1]", String.valueOf(employeeHeadCount));
        mailTo = props.getProperty("mailTo");
        mailCc = props.getProperty("mailCc");

        String attachedFile = file.getName();
        System.out.println("attachedFile: " + attachedFile);

        try {
            mailObj.smtpMailAttachment( "gopinath.elangovan@hindujatech.com" , mailSubject , mailMessage , "gopinath.elangovan@hindujatech.com" , attachedFile);
        } catch (Exception e) {
            System.out.println("mail sent issue " + e);
        }
    }
}