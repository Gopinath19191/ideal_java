/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dunningreport;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
 * @author 16221
 */

public class DunningReport {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    static String mailTo = "";
    static String mailCC = "";
    static String mailSubject = "";
    static String mailMessage = "";
    static String fromAddress ;
    static String mailPassword;
    static String mailHost;
    static String mailPort;
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException, Exception {
        // TODO code application logic here
        String configFile = new File("dunning_report.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        mailSubject = properties.getProperty("MailSubject");
        mailMessage = properties.getProperty("MailMessage");
        mailCC = properties.getProperty("MailCC");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        objTargetConnection.setAutoCommit(false);

        String legal_entity_id = "SELECT id, legal_entity FROM legal_entities";
        PreparedStatement le_ps = objTargetConnection.prepareStatement(legal_entity_id);
        ResultSet le_rs = le_ps.executeQuery();
        int count = 0;
        Date curdate = new Date();
        DateFormat fold = new SimpleDateFormat("ddMMyyyy");
        DateFormat datetime = new SimpleDateFormat("ddMMyyyyhhmmss");
        String dunn_fol = fold.format(curdate).toString();
        String parent_folder = "D:\\dunning_report\\";
        try {
            new File(parent_folder).mkdir();
            new File(parent_folder + "\\" + dunn_fol).mkdir();
        } catch (Exception e) {
            System.out.println(" " + e);
        }

        while (le_rs.next()) {
            count++;
            String customer_id = "SELECT distinct(so.customer_id), cus.customer_code "
                    + "FROM invoices AS inv "
                    + "LEFT JOIN project_so_masters AS pso ON inv.project_id = pso.project_id "
                    + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                    + "LEFT JOIN customers AS cus ON so.customer_id = cus.id "
                    + "LEFT JOIN customer_dunning_mapping AS cdm ON cus.id = cdm.customer_id "
                    + "WHERE inv.balance_amount>0 AND inv.status = 'a' AND cdm.dunning_status='y' AND "
                    + "so.hinduja_entity_id = " + le_rs.getString(1);

            PreparedStatement customer_ps = objTargetConnection.prepareStatement(customer_id);
            ResultSet customer_rs = customer_ps.executeQuery();

            while (customer_rs.next()) {
                curdate = new Date();
                String inv_fol = datetime.format(curdate).toString();
                String invoices_folder = parent_folder + "\\" + dunn_fol + "\\" + customer_rs.getString(2) + "_" + inv_fol;
                try {
                    new File(invoices_folder).mkdir();
                } catch (Exception e) {
                    System.out.println(" " + e);
                }
                String FILE = "dunning_report_" + customer_rs.getString(2) + ".pdf";
                Document doc = new Document(PageSize.A4, 40, 40, 50, 60);
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                doc.open();
                Image headerImage = Image.getInstance("images/logo.jpg");
                headerImage.scaleAbsoluteHeight(60);
                headerImage.scaleAbsoluteWidth(150);
                //////
                PdfPTable table = new PdfPTable(2);
                PdfPCell pfcell;
                table.setTotalWidth(540);
                table.setLockedWidth(true);
                table.setWidths(new float[]{2.5f, 1.5f});
                pfcell = new PdfPCell(headerImage);
                pfcell.setBorder(0);
                table.addCell(pfcell);
                /////
                Paragraph para = new Paragraph();
                para.add(new Phrase("Address For Communication\n", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));

                String address = "SELECT ic.*,tm.legal_entity_id FROM invoice_configurations AS ic "
                        + "LEFT JOIN tax_masters AS tm ON ic.tax_master_id = tm.id "
                        + "WHERE tm.legal_entity_id = " + le_rs.getString(1) + " "
                        + "ORDER BY id DESC LIMIT 1;";
                //
                PreparedStatement adddress_ps = objTargetConnection.prepareStatement(address);
                ResultSet address_rs = adddress_ps.executeQuery();
                address_rs.first();
                para.add(new Phrase(address_rs.getString(6) + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                para.add(new Phrase(address_rs.getString(7) + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                para.add(new Phrase(address_rs.getString(32) + " ", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                para.add(new Phrase(address_rs.getString(33), new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);

                para.setAlignment(Element.ALIGN_RIGHT);
                pfcell = new PdfPCell(para);
                pfcell.setBorder(0);
                pfcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(pfcell);
                doc.add(table);
                String customer_details = "select cus.customer_name,cba.address_line,cty.city,"
                        + "reg.region,cnty.country,cba.zip_code, cus.customer_code "
                        + "from customer_billing_addresses as cba "
                        + "LEFT JOIN customers as cus on cba.customer_id = cus.id "
                        + "LEFT JOIN countries AS cnty ON cba.country_id = cnty.id "
                        + "LEFT JOIN regions AS reg ON cba.region_id = reg.id "
                        + "LEFT JOIN cities AS cty ON cba.city_id = cty.id "
                        + "where cba.deleted=0 and cba.customer_id= " + customer_rs.getString(1) + " "
                        + "limit 1;";
                PreparedStatement customer_detail_ps = objTargetConnection.prepareStatement(customer_details);
                ResultSet customer_detail_rs = customer_detail_ps.executeQuery();
                customer_detail_rs.first();
                PdfPTable table_to = new PdfPTable(2);
                PdfPCell pfcell_to;
                table_to.setTotalWidth(510);
                table_to.setLockedWidth(true);
                table_to.setWidths(new float[]{2.5f, 1.5f});

                Paragraph toAddress = new Paragraph();
                toAddress.add(new Phrase("\n\nTo,\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                toAddress.add(new Phrase(customer_detail_rs.getString(1) + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                toAddress.add(new Phrase(customer_detail_rs.getString(2) + ",\n", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                toAddress.add(new Phrase(customer_detail_rs.getString(3) + ", " + customer_detail_rs.getString(4) + ",\n", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                toAddress.add(new Phrase(customer_detail_rs.getString(5) + " - " + customer_detail_rs.getString(6) + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                pfcell_to = new PdfPCell(toAddress);
                pfcell_to.setBorder(0);
                table_to.addCell(pfcell_to);
                DateFormat dmy = new SimpleDateFormat("dd-MMM-yyyy");
                String dun_date = dmy.format(curdate).toString();
                Paragraph ht_details = new Paragraph();
                ht_details.add(new Phrase("\n\n\n  Dunning Report", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
                ht_details.add(new Phrase("\n  Date:" + dun_date, new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                ht_details.add(new Phrase("\n  Email: AR@hindujatech.com", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                ht_details.add(new Phrase("\n  Telephone: +91 44 6669 1144", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                ht_details.add(new Phrase("\n  Fax: +91 44 6669 1143", new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                ht_details.add(new Phrase("\n  Your Account Reference With us: " + customer_rs.getString(2), new Font(Font.FontFamily.TIMES_ROMAN, 10)));
                pfcell_to = new PdfPCell(ht_details);
                pfcell_to.setBorder(0);
                table_to.addCell(pfcell_to);
                doc.add(table_to);

                Paragraph content = new Paragraph();
                content.add(new Phrase("\nDear Sir/Madam,\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
                content.add(new Phrase("Upon review of your account with us, we have determined that payment is past due / falling due against our following Invoices. We request you to arrange for payment of the past due amount immediately and the remaining by due date.\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
                content.add(new Phrase("If there is any reason why payment cannot be made, please contact us on the email / phone as mentioned above. If the payment have been made, kindly share the details of payment for us to trace the same and confirm. \n\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
                content.add(new Phrase("Sincerely,\n"+ address_rs.getString(6) +"\n\n\n\n", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
                doc.add(content);
                // adding bank details here
                doc.add(bank_details_formation(address_rs));
                
                doc.newPage();
                String past_due_query = past_days_query(le_rs.getString(1), customer_rs.getString(1));
                PreparedStatement past_due_ps = objTargetConnection.prepareStatement(past_due_query);
                ResultSet past_due_rs = past_due_ps.executeQuery();
                String currency_name = "";
                PdfPTable due_table = new PdfPTable(8);
                PdfPCell duecell;
                due_table.setTotalWidth(510);
                due_table.setLockedWidth(true);
                due_table.setWidths(new float[]{1, 3, 2, 2, 1.5f, 1.5f, 2, 2.5f});
                duecell = new PdfPCell(new Phrase("S.No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Invoice Number", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Invoice Date", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Due Date", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Past Due Dates", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Currency", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Invoice Amount", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                duecell = new PdfPCell(new Phrase("Balance Amount", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                duecell.setGrayFill(0.85f);
                duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                duecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                due_table.addCell(duecell);
                due_table.setHeaderRows(1);
                double due_total = 0.00;
                int due_count = 0;
                ArrayList entireList = new ArrayList();
                ArrayList header = new ArrayList();
                header.add(new String("Invoice Number"));
                header.add(new String("Invoice Date"));
                header.add(new String("Due Date"));
                header.add(new String("Past Due Dates"));
                header.add(new String("Currency"));
                header.add(new String("Invoice Amount"));
                header.add(new String("Balance Amount"));
                entireList.add(header);
                int invoice_due_count=0;
                while (past_due_rs.next()) {
                    invoice_due_count++;
                    ArrayList rowData = new ArrayList();
                    duecell = new PdfPCell(new Phrase(Integer.toString(invoice_due_count), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(past_due_rs.getString(1), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(past_due_rs.getString(2), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(past_due_rs.getString(3), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(past_due_rs.getString(4), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(past_due_rs.getString(5), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    due_table.addCell(duecell);
                    currency_name = past_due_rs.getString(5);
                    duecell = new PdfPCell(new Phrase(fmt(past_due_rs.getString(6), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    duecell.setVerticalAlignment(Element.ALIGN_RIGHT);
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(fmt(past_due_rs.getString(7), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    duecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    duecell.setVerticalAlignment(Element.ALIGN_RIGHT);
                    due_table.addCell(duecell);
                    due_total += Double.parseDouble(past_due_rs.getString(7));
                    due_count++;
                    rowData.add(new String("" + past_due_rs.getString(1)));
                    rowData.add(new String("" + past_due_rs.getString(2)));
                    rowData.add(new String("" + past_due_rs.getString(3)));
                    rowData.add(new String("" + past_due_rs.getString(4)));
                    rowData.add(new String("" + past_due_rs.getString(5)));
                    rowData.add(new String("" + past_due_rs.getString(6)));
                    rowData.add(new String("" + past_due_rs.getString(7)));
                    entireList.add(rowData);
                    copy_invoices(past_due_rs.getString(1), invoices_folder);
                }
                if (due_count > 0) {
                    duecell = new PdfPCell(new Phrase(" Total Invoice Amount Past Due - "+currency_name, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                    duecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    duecell.setColspan(7);
                    due_table.addCell(duecell);
                    duecell = new PdfPCell(new Phrase(fmt(String.format("%.2f", due_total), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                    duecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    due_table.addCell(duecell);
                    ArrayList past_footer = new ArrayList();
                    past_footer.add(new String("Total Invoice Amount Past Due " + String.format("%.2f", due_total) + " " + currency_name) + "\n");
                    entireList.add(past_footer);
                    ArrayList empty_row1 = new ArrayList();
                    empty_row1.add(new String(""));
                    entireList.add(empty_row1);
                    ArrayList empty_row2 = new ArrayList();
                    empty_row2.add(new String(""));
                    entireList.add(empty_row2);
                }
                doc.add(due_table);
                Paragraph past_total = new Paragraph();
                past_total.add(new Phrase("\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                doc.add(past_total);
                String no_due_query = no_due_query(le_rs.getString(1), customer_rs.getString(1));
                PreparedStatement no_due_ps = objTargetConnection.prepareStatement(no_due_query);
                ResultSet no_due_rs = no_due_ps.executeQuery();
                PdfPTable no_due_table = new PdfPTable(8);
                PdfPCell noduecell;
                no_due_table.setTotalWidth(510);
                no_due_table.setLockedWidth(true);
                no_due_table.setWidths(new float[]{1, 3, 2, 2, 1.5f, 1.5f, 2, 2.5f});
                noduecell = new PdfPCell(new Phrase("S.No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Invoice Number", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Invoice Date", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Due Date", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Days to Due Dates", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Currency", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Invoice Amount", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                noduecell = new PdfPCell(new Phrase("Balance Amount", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                noduecell.setGrayFill(0.85f);
                noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noduecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                no_due_table.addCell(noduecell);
                no_due_table.setHeaderRows(1);
                double no_due_total = 0.00;
                int no_due_count = 0;
                ArrayList header_no_due = new ArrayList();
                header_no_due.add(new String("Invoice Number"));
                header_no_due.add(new String("Invoice Date"));
                header_no_due.add(new String("Due Date"));
                header_no_due.add(new String("Days to Due Dates"));
                header_no_due.add(new String("Currency"));
                header_no_due.add(new String("Invoice Amount"));
                header_no_due.add(new String("Balance Amount"));
                int invoice_not_due_count=0;
                while (no_due_rs.next()) {
                    invoice_not_due_count++;
                    ArrayList rowData = new ArrayList();
                    noduecell = new PdfPCell(new Phrase(Integer.toString(invoice_not_due_count), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(no_due_rs.getString(1), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(no_due_rs.getString(2), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(no_due_rs.getString(3), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(no_due_rs.getString(4), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(no_due_rs.getString(5), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    no_due_table.addCell(noduecell);
                    currency_name = no_due_rs.getString(5);
                    noduecell = new PdfPCell(new Phrase(fmt(no_due_rs.getString(6), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(fmt(no_due_rs.getString(7), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    no_due_table.addCell(noduecell);
                    no_due_total += Double.parseDouble(no_due_rs.getString(7));
                    no_due_count++;
                    copy_invoices(no_due_rs.getString(1), invoices_folder);
                    rowData.add(new String("" + no_due_rs.getString(1)));
                    rowData.add(new String("" + no_due_rs.getString(2)));
                    rowData.add(new String("" + no_due_rs.getString(3)));
                    rowData.add(new String("" + no_due_rs.getString(4)));
                    rowData.add(new String("" + no_due_rs.getString(5)));
                    rowData.add(new String("" + no_due_rs.getString(6)));
                    rowData.add(new String("" + no_due_rs.getString(7)));
                    entireList.add(rowData);
                }
                
                Paragraph no_total = new Paragraph();
                if (no_due_count > 0) {
                    noduecell = new PdfPCell(new Phrase(" Total Invoice Amount Not Yet Due - "+currency_name, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    noduecell.setColspan(7);
                    no_due_table.addCell(noduecell);
                    noduecell = new PdfPCell(new Phrase(fmt(String.format("%.2f", no_due_total), currency_name), new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
                    noduecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    no_due_table.addCell(noduecell);
                    //no_total.add(new Phrase(" Total Invoice Amount Not Yet Due " + fmt(String.format("%.2f", no_due_total), currency_name) + " " + currency_name + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                    ArrayList nodue_footer = new ArrayList();
                    nodue_footer.add(new String(" Total Invoice Amount Not Yet Due " + String.format("%.2f", no_due_total) + " " + currency_name) + "\n");
                    entireList.add(nodue_footer);
                    ArrayList empty_row3 = new ArrayList();
                    empty_row3.add(new String(""));
                    entireList.add(empty_row3);
                }
                doc.add(no_due_table);
                no_total.add(new Phrase("\n\n Total Outstanding as on " + dun_date + " is " + fmt(String.format("%.2f", no_due_total + due_total), currency_name) + " " + currency_name + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
                doc.add(no_total);
                ArrayList empty_row4 = new ArrayList();
                empty_row4.add(new String(""));
                entireList.add(empty_row4);
                ArrayList total_footer = new ArrayList();
                total_footer.add(new String("Total Outstanding as on " + dun_date + " is " + String.format("%.2f", no_due_total + due_total) + " " + currency_name));
                entireList.add(total_footer);
                doc.close();

                int page_count = 0;
                PdfReader reader = new PdfReader(FILE);
                DateFormat sdf = new SimpleDateFormat("yyyyMddhhmmss");
                String current_date = sdf.format(curdate).toString();
                String pdf_file_name = "dunning_report_" + le_rs.getString(2) + "_" + customer_rs.getString(2) + "_" + current_date + ".pdf";
                String dest_file = parent_folder + "\\" + dunn_fol + "\\" + pdf_file_name;
                page_count = reader.getNumberOfPages();
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest_file));
                PdfContentByte pagecontent;
                for (int i = 0; i < page_count;) {
                    pagecontent = stamper.getOverContent(++i);
                    ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,
                            new Phrase(address_rs.getString(6) + " - Dunning Report " + dun_date, new Font(Font.FontFamily.TIMES_ROMAN, 8)), 45, 45, 0);
                    ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
                            new Phrase(String.format("Page %s of %s", i, page_count), new Font(Font.FontFamily.TIMES_ROMAN, 8)), 550, 45, 0);
                }
                stamper.close();
                reader.close();
                File f = new File(FILE);
                f.delete();
                String excel_file = parent_folder + "\\" + dunn_fol + "\\dunning_report_" + le_rs.getString(2) + "_" + customer_rs.getString(2) + "_" + current_date + ".xlsx";
                createExcel(entireList, entireList, excel_file, "");
                make_zip_folder(invoices_folder, invoices_folder);
                insertFileDetails(customer_rs.getString(1), le_rs.getString(1), pdf_file_name, "dunning_report_" + le_rs.getString(2) + "_" + customer_rs.getString(2) + "_" + current_date + ".xlsx", customer_rs.getString(2) + "_" + inv_fol + ".zip", objTargetConnection);
                String download_link="http://ideal.hindujatech.com:8080/ideal_application/authenticate.htm?empId=16221&modId=833&fileName="+ customer_rs.getString(2) + "_" + inv_fol + ".zip"+"&fileType=zip&dunningFolder="+dunn_fol;
                System.out.println("file nameeee "+download_link);
                String message = mailMessage.replace("data[0]", download_link);
                message = message.replace("data[1]", address_rs.getString(6));
                sendDunningMail(customer_rs.getString(1), objTargetConnection, mailSubject+ le_rs.getString(2) + "_" + customer_rs.getString(2) + "_"+dun_date, message, mailCC, dest_file);
            }

        }

    }

    public static String fmt(String value, String currency) {
        String[] curr_value = value.split("-");
        String s = "";
        if (curr_value.length == 2) {
            s = curr_value[1];
        } else {
            s = curr_value[0];
        }
        String formatted = "";
        String[] spl = s.split("\\.");
        s = spl[0];
        if (!currency.equals("INR")) {
            if (s.length() < 4) {
                formatted = s;
            } else if (s.length() == 4) {
                formatted = s.substring(0, 1) + "," + s.substring(1);
            } else if (s.length() == 5) {
                formatted = s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 6) {
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 7) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 8) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 9) {
                formatted = s.substring(0, 3) + ",";
                s = s.substring(3);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 10) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 3) + ",";
                s = s.substring(3);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else {
                formatted = s;
            }
        } else {
            if (s.length() < 4) {
                formatted = s;
            } else if (s.length() == 4) {
                formatted = s.substring(0, 1) + "," + s.substring(1);
            } else if (s.length() == 5) {
                formatted = s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 6) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 7) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 8) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 9) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 10) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else {
                formatted = s;
            }
        }
        if (curr_value.length == 2) {
            if (spl.length == 2) {
                return "-" + formatted + "." + spl[1];
            } else {
                return "-" + formatted + ".00";
            }
        } else {
            if (spl.length == 2) {
                return formatted + "." + spl[1];
            } else {
                return formatted + ".00";
            }
        }
    }

    public static String past_days_query(String entity_id, String Customer_id) {
        String query = "SELECT inv.invoice_code, date_format(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "date_format(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY),'%d-%b-%Y') AS due_date,"
                + "DATEDIFF(CURDATE(), DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY)) AS days_past_due,"
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM invoices AS inv "
                + "LEFT JOIN project_so_masters AS pso ON inv.project_id = pso.project_id "
                + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                + "LEFT JOIN currencies AS curr ON so.currency_id = curr.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY)<CURDATE() AND "
                + "inv.status = 'a' AND so.hinduja_entity_id = " + entity_id + " AND so.customer_id = " + Customer_id + " "
                + "UNION ALL "
                + "SELECT inv.invoice_code, DATE_FORMAT(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY),'%d-%b-%Y') AS due_date, "
                + "DATEDIFF(CURDATE(), DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY)) AS days_past_due, "
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM non_ideal_invoices AS inv "
                + "LEFT JOIN currencies AS curr ON inv.invoicing_currency_id = curr.id "
                + "LEFT JOIN tax_masters AS tm ON inv.tax_code = tm.short_code "
                + "LEFT JOIN customers AS cus ON inv.customer_id = cus.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY)<CURDATE() "
                + "AND tm.legal_entity_id = " + entity_id + " AND inv.customer_id = " + Customer_id + " "
                + "UNION ALL "
                + "SELECT inv.invoice_code, DATE_FORMAT(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY),'%d-%b-%Y') AS due_date, "
                + "DATEDIFF(CURDATE(),DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY)) AS days_past_due, "
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM invoices AS inv "
                + "LEFT JOIN invoice_consolidations AS inc ON inv.consolidated_id = inc.id "
                + "LEFT JOIN invoice_consolidation_details AS incd ON inc.id = incd.invoice_consolidation_id "
                + "LEFT JOIN invoices AS invli ON incd.invoice_id = invli.id "
                + "LEFT JOIN project_so_masters AS pso ON invli.project_id = pso.project_id "
                + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                + "LEFT JOIN currencies AS curr ON so.currency_id = curr.id "
                + "LEFT JOIN customers AS cus ON so.customer_id = cus.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY)<CURDATE() AND "
                + "inv.status = 'a' AND so.hinduja_entity_id = " + entity_id + " AND so.customer_id = " + Customer_id + " "
                + "GROUP BY inv.id "
                + "ORDER BY days_past_due DESC";
        return query;
    }

    public static String no_due_query(String entity_id, String customer_id) {
        String query = "SELECT inv.invoice_code, date_format(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY), '%d-%b-%Y') AS due_date,"
                + "DATEDIFF(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY), CURDATE()) AS days_to_due,"
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM invoices AS inv "
                + "LEFT JOIN project_so_masters AS pso ON inv.project_id = pso.project_id "
                + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                + "LEFT JOIN currencies AS curr ON so.currency_id = curr.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY)>=CURDATE() AND "
                + "inv.status = 'a' AND so.hinduja_entity_id = " + entity_id + " AND so.customer_id = " + customer_id + " "
                + "UNION ALL "
                + "SELECT inv.invoice_code, DATE_FORMAT(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY),'%d-%b-%Y') AS due_date, "
                + "DATEDIFF(DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY), CURDATE()) AS days_to_due, "
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM invoices AS inv "
                + "LEFT JOIN invoice_consolidations AS inc ON inv.consolidated_id = inc.id "
                + "LEFT JOIN invoice_consolidation_details AS incd ON inc.id = incd.invoice_consolidation_id "
                + "LEFT JOIN invoices AS invli ON incd.invoice_id = invli.id "
                + "LEFT JOIN project_so_masters AS pso ON invli.project_id = pso.project_id "
                + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                + "LEFT JOIN currencies AS curr ON so.currency_id = curr.id "
                + "LEFT JOIN customers AS cus ON so.customer_id = cus.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL so.credit_period DAY) >= CURDATE() AND "
                + "inv.status = 'a' AND so.hinduja_entity_id = " + entity_id + " AND so.customer_id = " + customer_id + " "
                + "GROUP BY inv.id "
                + "UNION ALL "
                + "SELECT inv.invoice_code, DATE_FORMAT(inv.invoice_date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY), '%d-%b-%Y') AS due_date, "
                + "DATEDIFF(DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY), CURDATE()) AS days_to_due, "
                + "curr.currency_code, inv.total_amount, inv.balance_amount "
                + "FROM non_ideal_invoices AS inv "
                + "LEFT JOIN currencies AS curr ON inv.invoicing_currency_id = curr.id "
                + "LEFT JOIN tax_masters AS tm ON inv.tax_code = tm.short_code "
                + "LEFT JOIN customers AS cus ON inv.customer_id = cus.id "
                + "WHERE inv.balance_amount > 0 AND DATE_ADD(inv.invoice_date, INTERVAL inv.credit_period DAY) >= CURDATE() "
                + "AND tm.legal_entity_id = " + entity_id + " AND inv.customer_id = " + customer_id + " "
                + "UNION ALL "
                + "SELECT cn.mode_no, DATE_FORMAT(cn.date,'%d-%b-%Y') AS invoice_date, "
                + "DATE_FORMAT(cn.date,'%d-%b-%Y') AS due_date, "
                + "0 AS days_to_due, "
                + "cwnr.currency,-cn.amount,-cn.balance_amount "
                + "FROM credit_notes AS cn "
                + "LEFT JOIN credit_with_no_references AS cwnr ON cn.id = cwnr.credite_note_id "
                + "LEFT JOIN customers AS cus ON cwnr.customer_no = cus.id "
                + "LEFT JOIN project_so_masters AS pso ON cwnr.project_id = pso.project_id "
                + "LEFT JOIN sales_orders AS so ON pso.sales_order_id = so.id "
                + "LEFT JOIN legal_entities as le on cwnr.entity = le.legal_entity "
                + "WHERE cn.balance_amount > 0 AND cn.deleted = 0 "
                + "AND cwnr.customer_no = " + customer_id + " AND le.id = " + entity_id+" "
                + "ORDER BY days_to_due DESC";
        return query;
    }

    public static PdfPTable bank_details_formation(ResultSet address_rs) throws SQLException, Exception {
        address_rs.first();
        PdfPTable bank_details = new PdfPTable(4);
        if (address_rs.getString(36).equals("2")) {
            bank_details = new PdfPTable(4);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 2.3f, 1, 1.7f});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(4);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("GSTIN/UID", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(13), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("HSN Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(15), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("PAN", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(16), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("CIN No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(17), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("IFSC Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(12), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("LUT No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(34), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else if (address_rs.getString(36).equals("3")) {
            //germany
            bank_details = new PdfPTable(2);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 3});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(2);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("IBAN", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(18), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else if (address_rs.getString(36).equals("4")) {
            System.out.println("usa");
            bank_details = new PdfPTable(4);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 2.3f, 1, 1.7f});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(4);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Routing\n(Wire Transfer)", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(20), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Routing\n(ACH Payments)", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(21), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("EIN No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(22), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

        } else if (address_rs.getString(36).equals("6")) {
            System.out.println("france");
            bank_details = new PdfPTable(4);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 2, 1, 2.5f});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(4);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("SIRET No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(23), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("VAT No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(25), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("IBAN", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(18), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else if (address_rs.getString(36).equals("7")) {
            System.out.println("uk");
            bank_details = new PdfPTable(4);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 2, 1, 2.5f});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(4);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Sort Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(23), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("VAT No", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(25), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("IBAN", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(18), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else if (address_rs.getString(36).equals("8")) {
            System.out.println("japan");
            bank_details = new PdfPTable(4);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 2, 1, 2});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(4);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Type of Account", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(26), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Bank Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(27), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Branch Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(28), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("Branch Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(29), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else if (address_rs.getString(36).equals("9")) {
            System.out.println("mexico");
            bank_details = new PdfPTable(2);
            bank_details.setSpacingAfter(50f);
            PdfPCell bank_cell;
            bank_details.setTotalWidth(510);
            bank_details.setLockedWidth(true);
            bank_details.setWidths(new float[]{1, 3});
            bank_cell = new PdfPCell(new Phrase("Please Wire Transfer Payments To:", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setColspan(2);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Name", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(8), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank Address", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(9), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("Bank A/c #", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(10), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);

            bank_cell = new PdfPCell(new Phrase("SWIFT Code", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(11), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase("RFC NO", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD)));
            bank_cell.setGrayFill(0.85f);
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
            bank_cell = new PdfPCell(new Phrase(address_rs.getString(17), new Font(Font.FontFamily.TIMES_ROMAN, 11)));
            bank_cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            bank_details.addCell(bank_cell);
        } else {
            System.out.println("other " + address_rs.getString(36));
        }
        return bank_details;

    }

    public static void copy_invoices(String file_name, String file_path) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream("C:\\wamp\\www\\app\\webroot\\uploads\\invoice_uploads\\" + file_name + ".pdf");
            os = new FileOutputStream(file_path + "\\" + file_name + ".pdf");
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

    public static void make_zip_folder(String source_folder, String destination_zip_folder) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(destination_zip_folder + ".zip");
        ZipOutputStream zout = new ZipOutputStream(fout);
        File dir = new File(source_folder);
        if (!dir.isDirectory()) {
        } else {
            System.out.println("zip folder forming");
            byte[] buffer = new byte[1024];
            File[] files = dir.listFiles();
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    FileInputStream fin = new FileInputStream(files[i]);
                    zout.putNextEntry(new ZipEntry(files[i].getName()));
                    int length;
                    while ((length = fin.read(buffer)) > 0) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fin.close();
                }
                zout.close();
            }

        }

        //zout.flush();
    }

    public static void createExcel(ArrayList headerData, ArrayList excelData, String fileName, String sheetName) throws Exception {
        sheetName = "Dunning Report";//name of sheet
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        ArrayList resultList = new ArrayList();
        int dataRows = excelData.size();
        for (int r = 0; r < dataRows; r++) {
            XSSFRow row = sheet.createRow(r);
            //iterating c number of columns
            resultList = (ArrayList) excelData.get(r);
            for (int c = 0; c < resultList.size(); c++) {
                XSSFCell cell = row.createCell(c);
                cell.setCellValue(removeNull(resultList.get(c).toString()));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public static String removeNull(String stringToProcess) {
        String processedString;
        if (stringToProcess.equals(null) || stringToProcess.equals("null")) {
            processedString = "";
        } else {
            processedString = (stringToProcess);
        }
        return processedString;
    }

    public static void insertFileDetails(String customer_id, String entity_id, String pdf_file_name,
            String excel_name, String zip_name, Connection objTargetConnection) throws SQLException {
        String inser_status = "INSERT INTO dunning_report_status(date, customer_id, entity_id, file_name, excel_name, zip_name, created_on) "
                + "values(CURDATE(),?,?,?,?,?, NOW());";
        PreparedStatement insert_ps = objTargetConnection.prepareStatement(inser_status);
        insert_ps.setString(1, customer_id);
        insert_ps.setString(2, entity_id);
        insert_ps.setString(3, pdf_file_name);
        insert_ps.setString(4, excel_name);
        insert_ps.setString(5, zip_name);
        insert_ps.execute();
        insert_ps.clearParameters();
        insert_ps.close();

    }
    
    public static void sendDunningMail(String customer_id, Connection objTargetConnection, String mailSubject,
        String mailMessage, String mailCC, String pdf_file_name) throws Exception{
        
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
        
        String customer_mail = "SELECT GROUP_CONCAT(cc.contact_person_email), "
                + "bdm.work_email_address "
                + "FROM customers AS cus "
                + "LEFT JOIN customer_contacts AS cc ON cus.id = cc.customer_id AND cc.customer_contact_type = 'd' "
                + "LEFT JOIN customer_dunning_mapping AS cdm ON cus.id = cdm.customer_id "
                + "LEFT JOIN employees AS bdm ON cdm.bdm_id = bdm.id "
                + "WHERE cus.deleted = 0 AND cus.id = "+customer_id+" "
                + "GROUP BY cus.id;";
        PreparedStatement customer_ps = objTargetConnection.prepareStatement(customer_mail);
        ResultSet customer_rs = customer_ps.executeQuery();

        while (customer_rs.next()) {
            String mailTo=customer_rs.getString(1);
            mailCC = mailCC+","+customer_rs.getString(2);
            try {
                mailObj = new SendMail(fromAddress, mailPassword, mailHost, mailPort);
                mailObj.smtpMailAttachment("prasanna.venkatasubramanian@hindujatech.com", mailSubject, mailMessage,"",pdf_file_name);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }
        }
    }
}
