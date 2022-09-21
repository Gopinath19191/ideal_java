package employeesphotozipfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EmployeesPhotoZipFile {

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

        FileInputStream dbfile = new FileInputStream("id_card_jdbc.properties");
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

        FileInputStream mail = new FileInputStream("id_card_mailbody.properties");
        Properties props = new Properties();
        props.load(mail);

        String employees_query = "SELECT emp.employee_number AS Employee_ID,\n"
                + "InitCapital(CONCAT(emp.first_name, ' ',emp.last_name)) AS Employee_Name,\n"
                + "DATE_FORMAT(emp.joined_date, '%d-%b-%Y') AS Date_Of_Joining,\n"
                + "CASE WHEN emp.gender ='m' THEN 'Male' ELSE 'Female' END AS Gender,\n"
                + "cty.city AS Location,\n"
                + "emp.blood_group AS Blood_Group,\n"
                + "COALESCE(eec.mobile_number,'') AS Emergency_Number,\n"
                + "eicp.filename AS ConfigValue, \n"
                + "CASE WHEN eicp.filename IS NOT NULL THEN 'Yes' ELSE 'No' END AS photo_status \n"
                + "FROM employees AS emp \n"
                + "LEFT JOIN company_locations AS cl ON emp.company_location_id = cl.id \n"
                + "LEFT JOIN cities AS cty ON cl.city_id = cty.id \n"
                + "LEFT JOIN employee_emergency_contacts AS eec ON emp.id = eec.employee_id \n"
                + "LEFT JOIN employee_id_card_photos AS eicp ON emp.id = eicp.employee_id \n"
                + "WHERE emp.joined_date = CURDATE() \n"
//                + "WHERE emp.joined_date = '2022-08-16' \n"
                + "GROUP BY emp.id";

        PreparedStatement employees_ps = dbConnection.prepareStatement(employees_query);
        ResultSet employees_rs = employees_ps.executeQuery();

        DateFormat format = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
        String currentDate = format.format(date);
        String OriginalFolder = "C:\\wamp\\www\\app\\webroot\\uploads\\id_card_photo\\";
        String localFolder = "D:\\id_card\\IdCardImage_"+currentDate;
        File inFolder = new File(localFolder);
        inFolder.mkdirs();
        File outFolder;
        int sucess = 0;

//excel file creation:

        String ExcelFileName = "Idcard_details_" + currentDate + ".xls";
        String sheetName = "Idcard_details";//name of sheet       
        File file = new File("D://id_card//" + ExcelFileName);
        file.delete();
        file.createNewFile();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        String headerArray[] = new String[]{"Employee Id", "Employee Name", "Date Of Joining", "Gender", "Location",
            "Blood Group", "Emergency Number", "Photo Available"};

        HSSFRow row = sheet.createRow(0);
        for (short c = 0; c < headerArray.length; c++) {
            HSSFCell cell = row.createCell(c);
            cell.setCellValue(headerArray[c]);
        }

        int j = 0;
        mailMessage = props.getProperty("table");
        int s_no = 1;
        while (employees_rs.next()) {
            String rowArray[] = new String[10];
            rowArray[0] = employees_rs.getString(1);
            rowArray[1] = employees_rs.getString(2);
            rowArray[2] = employees_rs.getString(3);
            rowArray[3] = employees_rs.getString(4);
            rowArray[4] = employees_rs.getString(5);
            rowArray[5] = employees_rs.getString(6);
            rowArray[6] = employees_rs.getString(7);
//            rowArray[6] = employees_rs.getString(9);
            j = j + 1;
            HSSFRow rowElement = sheet.createRow(j);
            for (short c = 0; c < rowArray.length; c++) {
                HSSFCell cell = rowElement.createCell(c);
                cell.setCellValue(rowArray[c]);
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
//mail boby part messages
            mailMessage = mailMessage + props.getProperty("tableRow");
            mailMessage = mailMessage.replace("data[0]", String.valueOf(s_no));
            mailMessage = mailMessage.replace("data[1]", employees_rs.getString(1));
            mailMessage = mailMessage.replace("data[2]", employees_rs.getString(2));
            mailMessage = mailMessage.replace("data[3]", employees_rs.getString(3));
            mailMessage = mailMessage.replace("data[4]", employees_rs.getString(4));
            mailMessage = mailMessage.replace("data[5]", employees_rs.getString(5));
            mailMessage = mailMessage.replace("data[6]", employees_rs.getString(6));
            mailMessage = mailMessage.replace("data[7]", employees_rs.getString(7));
            mailMessage = mailMessage.replace("data[8]", employees_rs.getString(9));
            s_no++;
            String photo = employees_rs.getString(8);
//copying the photo from original file to local file:
            if (photo != null) {
                InputStream is = null;
                OutputStream os = null;
                try {
                    is = new FileInputStream(OriginalFolder + photo);
                    sucess++;
                    os = new FileOutputStream(localFolder + "\\" + photo);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                } catch (Exception ex) {
                    System.out.println("Unable to copy file:" + ex.getMessage());
                } finally {
                    try {
                        is.close();
                        os.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }

        mailSubject = props.getProperty("mailSubject");
        mailTo = props.getProperty("mailTo");
        mailCc = props.getProperty("mailCc");
//Zip file creation:
        if (sucess != 0) {
            outFolder = new File(localFolder + ".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    new FileOutputStream(outFolder)));
            BufferedInputStream in = null;
            byte[] data = new byte[1000];
            String files[] = inFolder.list();
            for (int i = 0; i < files.length; i++) {
                in = new BufferedInputStream(new FileInputStream(
                        inFolder.getPath() + "/" + files[i]), 1000);
                out.putNextEntry(new ZipEntry(files[i]));
                int count;
                while ((count = in.read(data, 0, 1000)) != -1) {
                    out.write(data, 0, count);
                }
                out.closeEntry();
                in.close();
            }
            out.flush();
            out.close();
            in.close();

            String attachedFile = file.getName();            
            String excelFileAttachment = "D:\\id_card\\" + attachedFile;            

            String attachedZipFile = outFolder.getName();
            String zipFileAttachment = "D:\\id_card\\" + attachedZipFile;

            try {
                  mailObj.smtpMailMultipleAttachment(mailTo, mailSubject, mailMessage, mailCc, excelFileAttachment, zipFileAttachment);
            } catch (Exception e) {
            }
            outFolder.delete();              
            FileUtils.deleteDirectory(inFolder);
            file.delete();
            File dir = new File("D:\\id_card");
            dir.delete();
        }

        if (sucess == 0) {
            try {
                     mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCc);
            } catch (Exception e) {
            }
        }
    }
}