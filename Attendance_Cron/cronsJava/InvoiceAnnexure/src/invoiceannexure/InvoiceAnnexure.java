/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invoiceannexure;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class InvoiceAnnexure {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, Exception {
        // TODO code application logic here (.xlsx)
        String configFile = new File("invoice_annexure.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
        
        Connection objTargetConnection = (Connection) DriverManager.getConnection(strDatabase, strUserName, strPassword);
        //objTargetConnection.setAutoCommit(false);
        
        String invoice_list = "SELECT id, invoice_code "
                + "FROM invoices "
                + "WHERE approved_on = date_format(curdate()-1,'%Y-%m-%d') AND status = 'a'";
        
        PreparedStatement invoice_list_ps = objTargetConnection.prepareStatement(invoice_list);
        ResultSet invoice_list_rs = invoice_list_ps.executeQuery();
        
        while (invoice_list_rs.next()) {
            String attachment_list = "SELECT id, attachment_name "
                    + "FROM invoice_attachments "
                    + "WHERE invoice_id = "+invoice_list_rs.getString(1) +" AND invoice_status = 'm';";
            PreparedStatement attachment_ps = objTargetConnection.prepareStatement(attachment_list);
            ResultSet attachment_rs = attachment_ps.executeQuery();
            int count = 1;
            while (attachment_rs.next()) {
                
                File file = new File("C:/wamp/www/app/webroot/uploads/invoice_uploads/"+attachment_rs.getString(2));
                File file_transfer = new File("D:/invoice_uploads/"+attachment_rs.getString(2));
                File file2 = new File("C:/wamp/www/app/webroot/uploads/invoice_uploads/Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2)+".pdf");
                System.out.println("Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2));
                if (!file2.exists() && file.exists()) {
                    
                    String fileName = attachment_rs.getString(2);
                    String fe = "";
                    if (fileName.contains(".")){
                        fe = fileName.substring(fileName.lastIndexOf(".")+1);
                    }
                    if(fe.equals("pdf")){
                        //System.out.println(attachment_rs.getString(2)+" --> file replaced "+"Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2)+".pdf");
                        int page_count = 0;
                        PdfReader reader = new PdfReader("C:/wamp/www/app/webroot/uploads/invoice_uploads/"+attachment_rs.getString(2));
                        String pdf_file_name = "Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2)+".pdf";
                        String dest_file = "C:\\wamp\\www\\app\\webroot\\uploads\\invoice_uploads\\" + pdf_file_name;
                        page_count = reader.getNumberOfPages();
                        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest_file));
                        PdfContentByte pagecontent;
                        for (int i = 0; i < page_count;) {
                            pagecontent = stamper.getOverContent(++i);
                            float width_length = reader.getPageSize(i).getRight();
                            ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,
                                    new Phrase("Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2), new Font(Font.FontFamily.TIMES_ROMAN, 8)), 45, 30, 0);
                            ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
                                    new Phrase(String.format("Page %s of %s", i, page_count), new Font(Font.FontFamily.TIMES_ROMAN, 8)), width_length-45, 30, 0);
                        }
                        stamper.close();
                        reader.close();
                        count++;
                        if(file.renameTo(file_transfer)){
                            file.delete();
                            String update_inv_attachments = "UPDATE invoice_attachments SET old_attachment_name = ?, attachment_name = ? WHERE id = ?";
                            PreparedStatement attach_ps = objTargetConnection.prepareStatement(update_inv_attachments);

                            attach_ps.setString(1, attachment_rs.getString(2));
                            attach_ps.setString(2, pdf_file_name);
                            attach_ps.setInt(3, Integer.parseInt(attachment_rs.getString(1)));
                            attach_ps.executeUpdate();
                        }
                    }else{
                        System.out.println("not a pdf file "+fe);
                    }
                    
                }else{
                    // System.out.println("Annexure_"+Integer.toString(count)+"_"+invoice_list_rs.getString(2)+".pdf preasent already");
                }
            }
        }
    }
}
