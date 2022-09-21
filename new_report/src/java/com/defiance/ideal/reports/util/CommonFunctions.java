/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.util;

import com.defiance.ideal.reports.dto.VendorContractExportDto;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author 16364
 */
public class CommonFunctions {
    public static final String pricingType = "242";
   public static final String shiftType = "201";
   public static final String locationType = "205";
   public static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
   public static void exportToExcelApproval(HttpServletResponse responseObj, ArrayList excelData, String fileName, String sheetName, String rename) throws Exception {
            System.out.println("size   "+excelData.size());
           ArrayList resultList = new ArrayList();
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/ms-excel");
            if (rename == null) {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonFunctions.class.getResourceAsStream("/" + fileName));
            } else {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename + ".xls\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonFunctions.class.getResourceAsStream("/" + fileName + ".xls"));
            }
            int dataRows = excelData.size();
           
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet(sheetName);
           
            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle csInt = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
            csInt.setDataFormat(df.getFormat("0"));
            HSSFRow rowhead = sheet.createRow((short) 0);
            HSSFCellStyle dateFormat1 = hssfworkbook.createCellStyle();
            dateFormat1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            Calendar calendar = Calendar.getInstance();
            HSSFFont font = hssfworkbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);
            HSSFCell cell;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (int i = 0; i < dataRows; i++) {
                rowhead = sheet.createRow((short) (i + 1));
                resultList = (ArrayList) excelData.get(i);
                for (int j = 0; j < resultList.size(); j++) {
                    if (resultList.get(j) != null) {
                        if (resultList.get(j).toString().contains("~~")) {
                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
                        } else if (resultList.get(j).toString().contains("@@@@@@")) {
                            cell = rowhead.createCell((short) j);
                            calendar.setTime(sdf.parse(resultList.get(j).toString().split("@@@@@@")[1]));
                     
                            cell.setCellValue(calendar);
                            cell.setCellStyle(dateFormat1); // style 1

                        } else if (resultList.get(j).toString().contains("#####")) {

                            cell = rowhead.createCell((short) j);
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(new Double(removeNull(resultList.get(j).toString().split("#####")[1])));
                            cell.setCellStyle(csInt);
                        } else {
                            rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
                        }
                    }
                }
            }
            hssfworkbook.write(bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
   
   public static void exportToPdfBulk(HttpServletRequest req, HttpServletResponse responseObj, String filePath,List<VendorContractExportDto> timesheeetDetails, List<VendorContractExportDto> employeeDetails) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
//            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
//            responseObj.setContentType("application/pdf");
//            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employeeDetails.get(0).getEmployee_name()+"_"+employeeDetails.get(0).getMonth().substring(0,3) +"_"+employeeDetails.get(0).getYear()+".pdf\"");
            String file_name = employeeDetails.get(0).getEmployee_number() + "_" + employeeDetails.get(0).getEmployee_name()+"_"+employeeDetails.get(0).getMonth().substring(0,3) +"_"+employeeDetails.get(0).getYear()+".pdf";
            Document document = new Document(PageSize.A4, 30, 30, 50, 30);
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath+"//"+file_name)));
//            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/logo.jpg");
            document.add(headerImage);
                                
            Paragraph para = new Paragraph();
            para.add(new Phrase("Vendor Contract Employees Timesheet Details",new Font(Font.TIMES_ROMAN, 15, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            document.add(para);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(540);
            table.setLockedWidth(true);
            table.setWidths(new float[]{0.75f, 2});
            pfcell = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getEmployee_name(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Employee Number",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getEmployee_number(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Reporting Manager",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getReporting_manager(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Location",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getLocation(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Vendor Name",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getVendor_name(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Vendor Code",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getVendor_code(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Timesheet Month",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getMonth()+" - "+employeeDetails.get(0).getYear(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            document.add(table);
            
            PdfPTable table2 = new PdfPTable(5);
            table2.setSpacingBefore(10f);
            PdfPCell pfcell2;
            table2.setTotalWidth(540);
            table2.setLockedWidth(true);
            table2.setWidths(new float[]{1,1,2,1,1});
            pfcell2 = new PdfPCell(new Phrase("Date",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Day",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Project Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Worked Hours",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Approved Hours",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            for(int i=0;i<timesheeetDetails.size();i++){
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getTimesheet_date(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getDay_name(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getProject_code(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getWorked_hours(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getApproved_hours(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
            }
            document.add(table2);
            
            PdfPTable table3 = new PdfPTable(2);
            table3.setSpacingBefore(10f);
            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pfcell3;
            table3.setTotalWidth(200);
            table3.setLockedWidth(true);
            table3.setWidths(new float[]{2, 1});
            pfcell3 = new PdfPCell(new Phrase("No of Working Days",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getWorking_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            
            pfcell3 = new PdfPCell(new Phrase("No of worked Days",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getWorked_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            
            pfcell3 = new PdfPCell(new Phrase("Leave Count",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getLeave_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            document.add(table3);
            Paragraph para1 = new Paragraph();
            
            Date date = new Date();
            para1.add(new Phrase("Downloaded on :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.add(new Phrase(date.toString(),new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            document.add(para1);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public static void exportToPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName,List<VendorContractExportDto> timesheeetDetails, List<VendorContractExportDto> employeeDetails) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employeeDetails.get(0).getEmployee_name()+"_"+employeeDetails.get(0).getMonth().substring(0,3) +"_"+employeeDetails.get(0).getYear()+".pdf\"");
//            String file_name = employeeDetails.get(0).getEmployee_number() + "_" + employeeDetails.get(0).getEmployee_name()+"_"+employeeDetails.get(0).getMonth().substring(0,3) +"_"+employeeDetails.get(0).getYear()+".pdf";
            Document document = new Document(PageSize.A4, 30, 30, 50, 30);
//            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath+"//"+file_name)));
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/logo.jpg");
            document.add(headerImage);
                                
            Paragraph para = new Paragraph();
            para.add(new Phrase("Vendor Contract Employees Timesheet Details",new Font(Font.TIMES_ROMAN, 15, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            document.add(para);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(540);
            table.setLockedWidth(true);
            table.setWidths(new float[]{0.75f, 2});
            pfcell = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getEmployee_name(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Employee Number",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getEmployee_number(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Reporting Manager",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getReporting_manager(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Location",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getLocation(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Vendor Name",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getVendor_name(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Vendor Code",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getVendor_code(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Timesheet Month",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setGrayFill(0.85f);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employeeDetails.get(0).getMonth()+" - "+employeeDetails.get(0).getYear(),new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(pfcell);
            document.add(table);
            
            PdfPTable table2 = new PdfPTable(5);
            table2.setSpacingBefore(10f);
            PdfPCell pfcell2;
            table2.setTotalWidth(540);
            table2.setLockedWidth(true);
            table2.setWidths(new float[]{1,1,2,1,1});
            pfcell2 = new PdfPCell(new Phrase("Date",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Day",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Project Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Worked Hours",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Approved Hours",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            for(int i=0;i<timesheeetDetails.size();i++){
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getTimesheet_date(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getDay_name(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getProject_code(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getWorked_hours(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(timesheeetDetails.get(i).getApproved_hours(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
            }
            document.add(table2);
            
            PdfPTable table3 = new PdfPTable(2);
            table3.setSpacingBefore(10f);
            table3.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pfcell3;
            table3.setTotalWidth(200);
            table3.setLockedWidth(true);
            table3.setWidths(new float[]{2, 1});
            pfcell3 = new PdfPCell(new Phrase("No of Working Days",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getWorking_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            
            pfcell3 = new PdfPCell(new Phrase("No of worked Days",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getWorked_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            
            pfcell3 = new PdfPCell(new Phrase("Leave Count",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell3.setGrayFill(0.85f);
            table3.addCell(pfcell3);
            pfcell3 = new PdfPCell(new Phrase(employeeDetails.get(0).getLeave_days(),new Font(Font.TIMES_ROMAN, 11)));
            table3.addCell(pfcell3);
            document.add(table3);
            
            Paragraph para1 = new Paragraph();
            Date date = new Date();
            para1.add(new Phrase("Downloaded on :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.add(new Phrase(date.toString(),new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            document.add(para1);
            
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public static void exportConsolidateToPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName,List<VendorContractExportDto> employeeDetails) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"Consolidate_"+employeeDetails.get(0).getMonth()+".pdf\"");
            Document document = new Document(PageSize.A4, 30, 30, 50, 30);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/logo.jpg");
            document.add(headerImage);
                                
            Paragraph para = new Paragraph();
            para.add(new Phrase("Vendor Contract Employees Consolidate Timesheet Details",new Font(Font.TIMES_ROMAN, 15, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            document.add(para);
            
            PdfPTable table2 = new PdfPTable(9);
            table2.setSpacingBefore(10f);
            PdfPCell pfcell2;
            table2.setTotalWidth(540);
            table2.setLockedWidth(true);
            table2.setWidths(new float[]{2,2,3.5f,2,1,1,0.5f,0.5f,0.5f});
            pfcell2 = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("RM Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Location Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Vendor Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Project Name",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Month",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Working Days",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Worked Days",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            pfcell2 = new PdfPCell(new Phrase("Leave Days",new Font(Font.TIMES_ROMAN, 9, Font.BOLD)));
            pfcell2.setGrayFill(0.85f);
            pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell2.setVerticalAlignment(Element.ALIGN_CENTER);
            table2.addCell(pfcell2);
            
            for(int i=0;i<employeeDetails.size();i++){
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getEmployee_number()+"-"+employeeDetails.get(i).getEmployee_name(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getReporting_manager(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getLocation(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getVendor_code()+"-"+employeeDetails.get(i).getVendor_name(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getProject_code(),new Font(Font.TIMES_ROMAN, 9)));
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getMonth(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getWorking_days(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getWorked_days(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
                pfcell2 = new PdfPCell(new Phrase(employeeDetails.get(i).getLeave_days(),new Font(Font.TIMES_ROMAN, 9)));
                pfcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(pfcell2);
            }
            document.add(table2);
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean zipFolder(String zipFile, String sourceDirectory){
        try{
            byte[] buffer = new byte[1024];
            FileOutputStream fout = new FileOutputStream(zipFile);
            ZipOutputStream zout = new ZipOutputStream(fout);
            File dir = new File(sourceDirectory);
            if(!dir.isDirectory()){
                
             }else{
                File[] files = dir.listFiles();
                for(int i=0; i < files.length ; i++){
                    FileInputStream fin = new FileInputStream(files[i]);
                    zout.putNextEntry(new ZipEntry(files[i].getName()));
                    int length;
                    while((length = fin.read(buffer)) > 0){
                       zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fin.close();
                }
            }
            zout.close();
            return true;
        }catch(IOException ioe){
            System.out.println("IOException :" + ioe);
            return false;
        }
    }
    
    public static void downloadZip(HttpServletRequest req, HttpServletResponse response, String filePath, String month, String year) throws Exception {
        String zipFolder = "D:/vendor_contract/VC_Timesheet_"+month+"_"+year+"_"+filePath.substring(19) +".zip";
        if(zipFolder(zipFolder,filePath)){
            String filepath = "D:/vendor_contract/"; 
            String filename = "VC_Timesheet_"+month+"_"+year+"_"+filePath.substring(19) +".zip";  
            BufferedInputStream buf = null;
            ServletOutputStream myOut = null;
            try{
                myOut = response.getOutputStream(); 
                File myfile = new File(filepath + filename);
                response.setContentType("application/zip");        
                response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
                response.setHeader("Content-Type", "application/force-download");
                response.setContentLength((int) myfile.length());
                FileInputStream input = new FileInputStream(myfile);
                buf = new BufferedInputStream(input);
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1) {
                    myOut.write(readBytes);
                }
            
            }catch(Exception e){
                System.out.println("error "+e);
            }
        }
    }
   public static Paragraph insertEmployeeDetails(VendorContractExportDto employeeDetails) {

        Paragraph para = new Paragraph();
        Paragraph emptyLines = new Paragraph();
        para.add(new Phrase("Employee Name:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.getEmployee_name()));
        para.add(emptyLines);
        para.add(new Phrase("Designation:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.getReporting_manager()));
        para.add(emptyLines);
        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        return para;

    }
   private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
}
