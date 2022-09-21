/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.admin.ticket.util;

import com.ideal.admin.ticket.dto.TicAdminDataDTO;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CommonMethods {

    public static String getSystemDate(String inputFormat) {
        DateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
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
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }

    public static void exportToExcel(HttpServletResponse responseObj, List excelData, String fileName, String sheetName, String rename) throws Exception {
        ArrayList resultList = new ArrayList();
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());

            responseObj.setContentType("application/ms-excel");
            if (rename == null) {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonMethods.class.getResourceAsStream("/" + fileName));
            } else {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename + ".xls\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonMethods.class.getResourceAsStream("/" + fileName + ".xls"));
            }
            int dataRows = excelData.size();

            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet(sheetName);

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle csInt = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
            csInt.setDataFormat(df.getFormat("0"));
            HSSFRow rowhead;
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
                        } else if (resultList.get(j).toString().contains("@@@@@@")) {
                            cell = rowhead.createCell((short) j);
                            calendar.setTime(sdf.parse(resultList.get(j).toString().split("@@@@@@")[1]));

                            cell.setCellValue(calendar);
                            cell.setCellStyle(dateFormat1);
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
            processedString = stringToProcess;
        }
        return processedString;
    }
    
    public static List<TicAdminDataDTO> getNewTimeFormat(List<TicAdminDataDTO> details){
        if (details != null) {            
            String rdate = null;
            String cdate = null;
            for(TicAdminDataDTO detail : details) {
            String createdDate = detail.getCreated_date();
            String repliedDate = detail.getReplied_date();               
            cdate = formatedDate(createdDate);
            detail.setCreated_date(cdate);
            if(repliedDate != null ){
            rdate = formatedDate(repliedDate);
            detail.setReplied_date(rdate);                        
            
            }          
           }
            
        }
        return details;    
    }
    public static String formatedDate(String date) {
        String DATE_FORMAT_OLD = "yyyy-MM-dd HH:mm:ss";
        String DATE_FORMAT_NEW = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_OLD);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(DATE_FORMAT_NEW);
        Date newDate = null;
        try {
            newDate = dateFormat.parse(date);
        } catch (ParseException ex) {
             ex.printStackTrace();
        }
        String formattedDate = dateFormat2.format(newDate);
        return formattedDate;
        
    }
}