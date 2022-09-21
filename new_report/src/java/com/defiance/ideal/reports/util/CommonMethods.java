/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
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
 * @author 14053
 */
public final class CommonMethods {

    private static Map<String, String> monthList = new LinkedHashMap<String, String>();
    private static Map<String, String> monthNewList = new LinkedHashMap<String, String>();
    private static Map<Integer, Integer> yearList = new LinkedHashMap<Integer, Integer>();
    private static Map<String, String> pjctStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> prjtCompStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> empStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> billableList = new LinkedHashMap<String, String>();
    private static Map<String, String> locationList = new LinkedHashMap<String, String>();
    private static Map<String, String> pricingList = new LinkedHashMap<String, String>();
    private static Map<String, String> conditionList = new LinkedHashMap<String, String>();
    private static short cellnum;

    public static String getSystemDate(String inputFormat) {
        DateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        //System.out.println(dateFormat.format(date));
        return currentDate;
    }

    public static final String changeDateFormatToDB(String providedDate) throws ParseException {
        if (!("").equals(providedDate) && providedDate != null) {
           // System.out.println("input date:::" + providedDate);
            String[] proDateArray = providedDate.split("-");
            String formattedDate = null;
            if ((proDateArray[2] == null ? "0000" != null : !proDateArray[2].equals("0000")) && (proDateArray[1] == null ? "00" != null : !proDateArray[1].equals("00")) && (proDateArray[0] == null ? "00" != null : !proDateArray[0].equals("00"))) {
                formattedDate = proDateArray[2] + "-" + proDateArray[1] + "-" + proDateArray[0];
               // System.out.println("formatted date::" + formattedDate);
            }
            return formattedDate;
        } else {
            return providedDate = null;
        }
    }

    public static Map<String, String> getMonthsList() {
        String[] months = new DateFormatSymbols().getShortMonths();
//        To get Full Month name uncomment the below line
//        String[] months = new DateFormatSymbols().getMonths();
        String month = "";
        String newMnt = "";
        for (int mnt = 0; mnt < (months.length - 1); mnt++) {
            month = months[mnt];
            newMnt = String.format("%02d", (mnt + 1));
            monthList.put(newMnt, month);
        }
        return monthList;
    }

    public static Map<String, String> getNewMonthsList() {
        //String[] months = new DateFormatSymbols().getShortMonths();
        //To get Full Month name uncomment the below line
        String[] months = new DateFormatSymbols().getMonths();
        String month = "";
        String newMnt = "";
        for (int mnt = 0; mnt < (months.length-1); mnt++) {
            month = months[mnt];
            newMnt = Integer.toString(mnt + 1);
            monthNewList.put(newMnt, month);
        }
        return monthNewList;
    }
    
    public static int getCurrentYear() {
        int currentYear;
        Calendar calendarInst = Calendar.getInstance();
        currentYear = calendarInst.get(Calendar.YEAR);
        return currentYear;
    }

    public static int getFinancialYear() {
        int currentYear;
        Calendar calendarInst = Calendar.getInstance();
        currentYear = calendarInst.get(Calendar.YEAR);
        if(calendarInst.get(Calendar.MONTH) < 3) {
            currentYear -= 1;
        }
        return currentYear;
    }

    public static Map<Integer, Integer> getYearsList(int range) {
        int crrntYr = getCurrentYear();
        for (int i = 0; i <= range; i++) {
            yearList.put((crrntYr - i), (crrntYr - i));
        }
        //yearList.put((crrntYr-2),(crrntYr-2));
        return yearList;
    }

    public static Map<String, String> getPjtStatusList() {
        HashMap projectStatus = Config.getChildElements(Config.getParentId("project_active_list"));
        Set set = projectStatus.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            pjctStatsList.put(me.getKey().toString(), me.getValue().toString());
        }
        return pjctStatsList;
    }

    public static Map<String, String> getBillableList() {
        System.out.println("here it comer");
        billableList.put("0".toString(), "Equal to 0".toString());
        billableList.put("168".toString(), "<=168".toString());
        billableList.put("189".toString(), "<=189".toString());
        return billableList;
    }

    public static Map<String, String> getLocationList() {
        System.out.println("here it location");
        System.out.println("location id "+(Config.getParentId("work_location_list")));
        HashMap locationDet = Config.getChildElements(Config.getParentId("work_location_list"));
        Set set = locationDet.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            locationList.put(me.getKey().toString(), me.getValue().toString());
        }
        return locationList;
    }

    public static Map<String, String> getPricingList() {
        System.out.println("here it pricing");
        HashMap priceList = Config.getChildElements(Config.getParentId("pricing_type"));
        Set set = priceList.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            pricingList.put(me.getKey().toString(), me.getValue().toString());
        }
        return pricingList;
    }

    public static Map<String, String> getConditionList() {
        System.out.println("here it condition list");
        HashMap cndnList = Config.getChildElements(Config.getParentId("billable_hours_filter"));
        Set set = cndnList.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            //System.out.println(":KEY:"+me.getKey().toString()+":VAL:"+me.getValue().toString());
            conditionList.put(me.getKey().toString(), me.getValue().toString());
        }
        return conditionList;
    }

    public static Map<String, String> getEmpStatusList() {
        HashMap empStatus = Config.getChildElements(Config.getParentId("employment_status_add"));
        Set set = empStatus.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            empStatsList.put(me.getKey().toString(), me.getValue().toString());
        }
        return empStatsList;
    }

    public static Map<String, String> getPrjtCompStatusList() {
        prjtCompStatsList.put("e", "Active");
        prjtCompStatsList.put("d", "Project-Delivered");
        prjtCompStatsList.put("c", "Closed");
        return prjtCompStatsList;
    }

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
                fis = (CommonMethods.class.getResourceAsStream("/" + fileName));
            } else {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename+".xls\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonMethods.class.getResourceAsStream("/" + fileName + ".xls"));
            }

            // new FileInputStream(FILE_LOCATION+"/"+fileName);
//                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
//                HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
            int dataRows = excelData.size();
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet(sheetName);

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle csInt = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
            csInt.setDataFormat(df.getFormat("0"));
//              HSSFRow rowhead = sheet.createRow((short) 0);
//              rowhead.createCell((short) 0).setCellValue("Hariharan");
            HSSFRow rowhead = sheet.createRow((short) 0);
            
            HSSFCellStyle dateFormat1 = hssfworkbook.createCellStyle();
            dateFormat1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            Calendar calendar = Calendar.getInstance();


            HSSFFont font = hssfworkbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            //font.setFontHeightInPoints((short) 20);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //font.setColor(HSSFColor.BLUE.index);
            cs.setFont(font);

            /*
            ArrayList headerList=new ArrayList();
            for(int header=0;header<headerData.size();header++){
            rowhead = sheet.createRow((short) header);
            headerList = (ArrayList) headerData.get(header);
            for(int i=0;i<headerList.size();i++){
            HSSFCell cell = rowhead.createCell((short) i);
            cell.setCellValue(headerList.get(i).toString());
            cell.setCellStyle(cs);
            }
            //HSSFCell cell = rowhead.createCell((short) header);
            //cell.setCellValue(headerData.get(header).toString());
            //cell.setCellStyle(cs);
            }
             */
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
                            cell.setCellStyle(dateFormat1); // style 1
//                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
                        } else if (resultList.get(j).toString().contains("#####")) {
                       //System.out.println(":::here comes::");
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
    
   public static String formatIntoHHMMSS(int secsIn) {

        int hours = secsIn / 3600,
                remainder = secsIn % 3600,
                minutes = remainder / 60,
                seconds = remainder % 60;

//return ( (hours < 10 ? "0" : "") + hours
//+ ":" + (minutes < 10 ? "0" : "") + minutes
//+ ":" + (seconds< 10 ? "0" : "") + seconds );
        return ((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes);

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
    
	/*public static int[] paging(int recordCount, int current_page, int rows) {
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
    }*/
    public static void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try{
            response.setContentType(fileType);
            System.out.println("file name>>>>>"+fileName);
            response.setHeader("Content-disposition","attachment; filename=\""+fileName+"\"");
            File file = new File(filePath+"\\"+fileName);
            //prepare input stream
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //prepare output stream
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            //start reading and writing data
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        }
    }

 }