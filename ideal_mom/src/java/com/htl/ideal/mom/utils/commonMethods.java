/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.utils;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
 * @author 8000458
 */
public class commonMethods {
    private static short cellnum;
    private static DateFormat dfm = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
    private static long unixtime; 
    
//    public static void exportToExcel(HttpServletResponse responseObj, List excelData, String fileName, String sheetName, String rename) throws Exception {
//            //System.out.println("In exportToExcel Function" +excelData.size()+"---"+fileName+"---"+sheetName+"---"+rename);
//
//        ArrayList resultList = new ArrayList();
//        InputStream fis = null;
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
//            //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + fileName);
//            responseObj.setContentType("application/ms-excel");
//            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//            responseObj.setHeader("Content-Type", "application/force-download");
//                fis = (commonMethods.class.getResourceAsStream("/" + fileName));
////            if (rename == null) {
////                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
////                responseObj.setHeader("Content-Type", "application/force-download");
////                fis = (commonMethods.class.getResourceAsStream("/" + fileName));
////            } else {
////                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename+".xls\"");
////                responseObj.setHeader("Content-Type", "application/force-download");
////                fis = (commonMethods.class.getResourceAsStream("/" + fileName + ".xls"));
////            }
//
//            // new FileInputStream(FILE_LOCATION+"/"+fileName);
////                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
////                HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
//            int dataRows = excelData.size();
//            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
//            final HSSFSheet sheet = hssfworkbook.getSheet(sheetName);
//
//            HSSFCellStyle cs = hssfworkbook.createCellStyle();
//            HSSFCellStyle csInt = hssfworkbook.createCellStyle();
//            HSSFDataFormat df = hssfworkbook.createDataFormat();
//            cs.setDataFormat(df.getFormat("General"));
//            csInt.setDataFormat(df.getFormat("0"));
////              HSSFRow rowhead = sheet.createRow((short) 0);
////              rowhead.createCell((short) 0).setCellValue("Hariharan");
//            HSSFRow rowhead ;//= sheet.createRow((short) 0);
//            
//            HSSFCellStyle dateFormat1 = hssfworkbook.createCellStyle();
//            dateFormat1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
//            Calendar calendar = Calendar.getInstance();
//
//
//            HSSFFont font = hssfworkbook.createFont();
//            font.setFontName(HSSFFont.FONT_ARIAL);
//            //font.setFontHeightInPoints((short) 20);
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//            //font.setColor(HSSFColor.BLUE.index);
//            cs.setFont(font);
//
//            /*
//            ArrayList headerList=new ArrayList();
//            for(int header=0;header<headerData.size();header++){
//            rowhead = sheet.createRow((short) header);
//            headerList = (ArrayList) headerData.get(header);
//            for(int i=0;i<headerList.size();i++){
//            HSSFCell cell = rowhead.createCell((short) i);
//            cell.setCellValue(headerList.get(i).toString());
//            cell.setCellStyle(cs);
//            }
//            //HSSFCell cell = rowhead.createCell((short) header);
//            //cell.setCellValue(headerData.get(header).toString());
//            //cell.setCellStyle(cs);
//            }
//             */
//            String test;
//            HSSFCell cell;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            for (int i = 0; i < dataRows; i++) {
//                rowhead = sheet.createRow((short) (i + 1));
//                resultList = (ArrayList) excelData.get(i);                
//                for (int j = 0; j < resultList.size(); j++) {                    
//                    if (resultList.get(j) != null) {
//                        if (resultList.get(j).toString().contains("~~")) {
//                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
//                        } 
//                        else if (resultList.get(j).toString().contains("@@@@@@")) {
//                            cell = rowhead.createCell((short) j);       
//                            calendar.setTime(sdf.parse(resultList.get(j).toString().split("@@@@@@")[1]));
//                            System.out.println("ccc" +calendar);
//                            cell.setCellValue(calendar);
//                            cell.setCellStyle(dateFormat1); // style 1
////                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
//                        } else if (resultList.get(j).toString().contains("#####")) {
//                       //System.out.println(":::here comes::");
//                            cell = rowhead.createCell((short) j);
//                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
//                            cell.setCellValue(new Double(removeNull(resultList.get(j).toString().split("#####")[1])));
//                            cell.setCellStyle(csInt);
//                        }
//                        else {
//                            rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
//                        }
//                    }
//                }
//            }
//
//            hssfworkbook.write(bos);
//            bos.flush();
//            bos.close();
//        } catch (Exception e) {
//            //System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    public static int[] paging(int recordCount, int current_page, int rows) {
//        int start = 1;
//        int end = 1;
//        int next = current_page + 1;
//        int prev = current_page - 1;
//        int[] pageArr = new int[10];
//        int totalPage = recordCount / rows;
//        if (recordCount % rows != 0) {
//            totalPage = totalPage + 1;
//        }
//        System.out.println("totalPage" + totalPage);
//        if (totalPage > 9) {
//            int minus = current_page - 4;
//            int plus = current_page + 4;
//            if (minus > 0) {
//                start = minus;
//            } else {
//                start = 1;
//            }
//            if ((plus - start) < 8) {
//                plus = start + 8;
//            }
//            if (plus > totalPage) {
//                end = totalPage;
//            } else {
//                end = plus;
//            }
//        } else {
//            start = 1;
//            end = totalPage;
//        }
//        if (prev < 1) {
//            prev = 1;
//        }
//        if (next > totalPage) {
//            next = totalPage;
//        }
//        System.out.println("recordCount" + recordCount);
//        System.out.println("current_page" + current_page);
//        System.out.println("start" + start);
//        System.out.println("end" + end);
//        System.out.println("Totalpage++++" + totalPage);
//        pageArr[0] = totalPage;
//        pageArr[1] = current_page;
//        pageArr[2] = start;
//        pageArr[3] = end;
//        pageArr[4] = prev;
//        pageArr[5] = next;
//        return pageArr;
//    }
//    public static String removeNull(String stringToProcess) {
//        String processedString;
//        if (stringToProcess.equals(null) || stringToProcess.equals("null")) {
//            processedString = "";
//        } else {
//            processedString = stringToProcess;
//        }
//        return processedString;
//    }
//
//public static long timeConversion(String time) 
//            {
//                dfm.setTimeZone(TimeZone.getTimeZone("GMT"));
//            //Specify your timezone
//            try 
//            { 
//                unixtime = dfm.parse(time).getTime(); 
//                unixtime=unixtime/(1000); 
//            } 
//            catch (ParseException e) 
//            { 
//                e.printStackTrace();
//            } 
//            return unixtime; 
//            }
//}
    public static void exportToExcel(HttpServletResponse responseObj, List excelData, String fileName, String sheetName, String rename) throws Exception {
           //System.out.println("In exportToExcel Function" +excelData.size()+"---"+fileName+"---"+sheetName+"---"+rename);
       ArrayList resultList = new ArrayList();
       InputStream fis = null;
       try {
           BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
           //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + fileName);
           responseObj.setContentType("application/ms-excel");
           if (rename == null) {
               responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
               responseObj.setHeader("Content-Type", "application/force-download");
               fis = (commonMethods.class.getResourceAsStream("/" + fileName));
           } else {
               responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename+".xls\"");
               responseObj.setHeader("Content-Type", "application/force-download");
               fis = (commonMethods.class.getResourceAsStream("/" + fileName + ".xls"));
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
           String test;
           HSSFCell cell;
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
           for (int i = 0; i < dataRows; i++) {
               rowhead = sheet.createRow((short) (i + 1));
               resultList = (ArrayList) excelData.get(i);                
               for (int j = 0; j < resultList.size(); j++) {                    
                   if (resultList.get(j) != null) {
                       if (resultList.get(j).toString().contains("~~")) {
                           rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
                       } 
                       else if (resultList.get(j).toString().contains("@@@@@@")) {
                           cell = rowhead.createCell((short) j);       
                           calendar.setTime(sdf.parse(resultList.get(j).toString().split("@@@@@@")[1]));
                           System.out.println("ccc" +calendar);
                           cell.setCellValue(calendar);
                           cell.setCellStyle(dateFormat1);
                       } else if (resultList.get(j).toString().contains("#####")) {                       
                           cell = rowhead.createCell((short) j);
                           cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
                           cell.setCellValue(new Double(removeNull(resultList.get(j).toString().split("#####")[1])));
                           cell.setCellStyle(csInt);
                       }
                       else {
                           rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
                       }
                   }
               }
           }

           hssfworkbook.write(bos);
           bos.flush();
           bos.close();
       } catch (Exception e) {
           //System.out.println(e.getMessage());
           e.printStackTrace();
       }
    }
    public static int[] paging(int recordCount, int current_page, int rows) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = recordCount / rows;
        if (recordCount % rows != 0) {
            totalPage = totalPage + 1;
        }
        System.out.println("totalPage" + totalPage);
        if (totalPage > 9) {
            int minus = current_page - 4;
            int plus = current_page + 4;
            if (minus > 0) {
                start = minus;
            } else {
                start = 1;
            }
            if ((plus - start) < 8) {
                plus = start + 8;
            }
            if (plus > totalPage) {
                end = totalPage;
            } else {
                end = plus;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        if (prev < 1) {
            prev = 1;
        }
        if (next > totalPage) {
            next = totalPage;
        }
        System.out.println("recordCount" + recordCount);
        System.out.println("current_page" + current_page);
        System.out.println("start" + start);
        System.out.println("end" + end);
        System.out.println("Totalpage++++" + totalPage);
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
    public static String removeNull(String stringToProcess) {
        String processedString;
        if (stringToProcess.equals(null) || stringToProcess.equals("null")) {
            processedString = "";
        } else {
            processedString = stringToProcess;
        }
        return processedString;
    }

public static long timeConversion(String time) 
            {
                dfm.setTimeZone(TimeZone.getTimeZone("GMT"));
            //Specify your timezone
            try 
            { 
                unixtime = dfm.parse(time).getTime(); 
                unixtime=unixtime/(1000); 
            } 
            catch (ParseException e) 
            { 
                e.printStackTrace();
            } 
            return unixtime; 
            }
}
