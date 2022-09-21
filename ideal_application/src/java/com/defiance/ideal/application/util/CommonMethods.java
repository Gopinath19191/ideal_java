/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.util;


import com.defiance.ideal.application.dto.OJFSurveyDTO;
import com.defiance.ideal.application.service.CustomerService;
import com.defiance.ideal.application.service.CustomerServiceImpl;
import com.lowagie.text.*;

import com.lowagie.text.BadElementException;

import com.lowagie.text.Cell;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;



import com.lowagie.text.pdf.*;
import java.awt.Color;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14053
 */
public final class CommonMethods {

    private static Map<String, String> monthList = new LinkedHashMap<String, String>();
    private static Map<Integer, Integer> yearList = new LinkedHashMap<Integer, Integer>();
    private static Map<String, String> pjctStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> prjtCompStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> empStatsList = new LinkedHashMap<String, String>();
    private static Map<String, String> billableList = new LinkedHashMap<String, String>();
    private static Map<String, String> locationList = new LinkedHashMap<String, String>();
    private static Map<String, String> pricingList = new LinkedHashMap<String, String>();
    private static Map<String, String> conditionList = new LinkedHashMap<String, String>();
    private static String parentHeader = "";

    public static String getSystemDate(String inputFormat) {
        if(inputFormat == null || inputFormat.equals("")) {
           inputFormat = "yyyy-MM-dd";
        }
        DateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        System.out.println(dateFormat.format(date));
        return currentDate;
    }
    public static Long DateDifference (String fromDate, String toDate) {
          Calendar calendar1 = Calendar.getInstance();
          Calendar calendar2 = Calendar.getInstance();
          if(toDate == null || toDate.equals("")) {
              DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
              Date date = new Date();
              toDate = dateFormat.format(date);
          }
          String[] fromDateSplit = fromDate.split("-");
          String[] toDateSplit = toDate.split("-");
          //System.out.println(":FROM DATE:"+Integer.parseInt(fromDateSplit[2])+"-"+Integer.parseInt(fromDateSplit[1])+"-"+Integer.parseInt(fromDateSplit[0]));
          //System.out.println(":TO DATE:"+Integer.parseInt(toDateSplit[0])+"-"+Integer.parseInt(toDateSplit[1])+"-"+Integer.parseInt(toDateSplit[2]));
          calendar1.set(Integer.parseInt(fromDateSplit[2]), Integer.parseInt(fromDateSplit[1]), Integer.parseInt(fromDateSplit[0]));
          calendar2.set(Integer.parseInt(toDateSplit[0]), Integer.parseInt(toDateSplit[1]), Integer.parseInt(toDateSplit[2]));
          long milliseconds1 = calendar1.getTimeInMillis();
          long milliseconds2 = calendar2.getTimeInMillis();
          long diff = milliseconds2 - milliseconds1;
//          long diffSeconds = diff / 1000;
//          long diffMinutes = diff / (60 * 1000);
//          long diffHours = diff / (60 * 60 * 1000);
          long diffDays = diff / (24 * 60 * 60 * 1000);
          //System.out.println(":MILL 1:"+milliseconds1+":MILL 2:"+milliseconds2+":DAYS:"+diffDays);
          return diffDays;
    }

    public static final String changeDateFormatToDB(String providedDate) throws ParseException {
        if (!("").equals(providedDate) && providedDate != null) {
            System.out.println("input date:::" + providedDate);
            String[] proDateArray = providedDate.split("-");
            String formattedDate = null;
            if ((proDateArray[2] == null ? "0000" != null : !proDateArray[2].equals("0000")) && (proDateArray[1] == null ? "00" != null : !proDateArray[1].equals("00")) && (proDateArray[0] == null ? "00" != null : !proDateArray[0].equals("00"))) {
                formattedDate = proDateArray[2] + "-" + proDateArray[1] + "-" + proDateArray[0];
                System.out.println("formatted date::" + formattedDate);
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

    public static int getCurrentYear() {
        int currentYear;
        Calendar calendarInst = Calendar.getInstance();
        currentYear = calendarInst.get(Calendar.YEAR);
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
        pjctStatsList.put("e", "Active");
        pjctStatsList.put("y", "Yet-to-Start");
        pjctStatsList.put("h", "On-Hold");
        pjctStatsList.put("d", "Project-Delivered");
        pjctStatsList.put("c", "Closed");
        return pjctStatsList;
    }

    public static Map<String, String> getBillableList() {
        billableList.put("0", "Equal to 0");
        billableList.put("168", "<=168");
        billableList.put("189", "<=189");
        return billableList;
    }

    public static Map<String, String> getLocationList() {
        locationList.put("o", "On Site");
        locationList.put("s", "Off Shore");
        return locationList;
    }

    public static Map<String, String> getPricingList() {
        pricingList.put("tm", "Time & Material");
        pricingList.put("fb", "Fixed Bid");
        return pricingList;
    }

    public static Map<String, String> getConditionList() {
        conditionList.put("1", "=");
        conditionList.put("2", ">");
        conditionList.put("3", "<");
        conditionList.put("4", ">=");
        conditionList.put("5", "<=");
        return conditionList;
    }

    public static Map<String, String> getEmpStatusList() {
        empStatsList.put("d", "Direct Contract");
        empStatsList.put("v", "Vendor Contract");
        empStatsList.put("p", "Permanent");
        empStatsList.put("g", "Permanent GmbH");
        empStatsList.put("c", "Contract GmbH");
        empStatsList.put("a", "AL Employees");
        empStatsList.put("c", "Closed");

        //$employmentStatus = array('d' => '', 'v' => '', 'p' => '', 'g' => '', 'c' => '', 'a' => '', 't' => 'Terminated',  'r' => 'Resigned','e' => 'Transferred','l' => 'Long Leave', 'b' => 'Absconding', 'q' => 'Asked to resign');
        return empStatsList;
    }

    public static Map<String, String> getPrjtCompStatusList() {
        prjtCompStatsList.put("e", "Active");
        prjtCompStatsList.put("d", "Project-Delivered");
        prjtCompStatsList.put("c", "Closed");
        return prjtCompStatsList;
    }

    public static void exportToExcel(HttpServletResponse responseObj, List excelData, String fileName, String sheetName, String rename) throws Exception {
        ArrayList resultList = new ArrayList();
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + fileName);
            responseObj.setContentType("application/ms-excel");
            if (rename == null) {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonMethods.class.getResourceAsStream("/" + fileName));
                System.out.println("::::"+fileName+"::::"+fis);
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
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
//              HSSFRow rowhead = sheet.createRow((short) 0);
//              rowhead.createCell((short) 0).setCellValue("Hariharan");
            HSSFRow rowhead = sheet.createRow((short) 0);

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

            for (int i = 0; i < dataRows; i++) {
                rowhead = sheet.createRow((short) (i + 1));
                resultList = (ArrayList) excelData.get(i);
                for (int j = 0; j < resultList.size(); j++) {
                    if (resultList.get(j) != null) {
                        //System.out.println("::::"+resultList.get(j).toString());
                        rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
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
            processedString = (stringToProcess);
        }
        return processedString;
    }

    public static Object ifNull(Object value, Object substituteWhenNull) {
        return (value != null) ? value : substituteWhenNull;
    }

    /**
     * <tt>ifNull</tt> method lets you substitute a value when a null value is
     * encountered.
     *
     * This method is the same as the <tt>nvl</tt> method.
     *
     * @param value                 the value to test
     * @param substituteWhenNull    substitute value when value is null
     * @return substitute when value is null
     */
    public static String ifNull(String value, String substituteWhenNull) {
        return (value != null) ? value : substituteWhenNull;
    }

    /**
     * <tt>ifNull</tt> method lets you substitute a value when a null value is
     * encountered as well as when a non-null value is encountered.
     *
     * This method is the same as the <tt>nvl</tt> method.
     *
     * @param value                 the value to test
     * @param substituteWhenNotNull substitute value when value is not null
     * @param substituteWhenNull    substitute value when value is null
     * @return substitute when value is null
     */
    public static Object ifNull(Object value, Object substituteWhenNotNull, Object substituteWhenNull) {
        return (value != null) ? substituteWhenNotNull : substituteWhenNull;
    }

    /**
     * <tt>ifNull</tt> method lets you substitute a value when a null value is
     * encountered as well as when a non-null value is encountered.
     *
     * This method is the same as the <tt>nvl</tt> method.
     *
     * @param value                 the value to test
     * @param substituteWhenNotNull substitute value when value is not null
     * @param substituteWhenNull    substitute value when value is null
     * @return substitute when value is null
     */
    public static String ifNull(String value, String substituteWhenNotNull, String substituteWhenNull) {
        return (value != null) ? substituteWhenNotNull : substituteWhenNull;
    }

    /**
     * Check if the string array contains the item. String case is ignored
     * when doing the check.
     *
     * @param item
     * @param array
     * @return true if the string array contains the item.
     */
    public static boolean isStringInArray(String item, String[] array) {
        return isStringInArray(item, array, false);
    }

    /**
     * Check if the string array contains the item.
     *
     * @param item
     * @param array
     * @param ignoreCase true if case is ignored when doing comparison.
     * @return true if the string array contains the item.
     */
    public static boolean isStringInArray(String item, String[] array, boolean ignoreCase) {
        if (array == null) {
            return false;
        }

        boolean result = false;

        int size = array.length;
        for (int i = 0; i < size; i++) {
            String tmp = array[i];
            if (tmp != null) {
                if (ignoreCase) {
                    if (tmp.equalsIgnoreCase(item)) {
                        result = true;
                        break;
                    }
                } else {
                    if (tmp.equals(item)) {
                        result = true;
                        break;
                    }
                }
            } else {
                if (item == null) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Check whether the given value is present in getter of the given field names,if its present it return true.Even if a single value is not equal it return false.
     * @param pageClass -DTO object
     * @param value - Value to checked
     * @param fieldNames - String array of Field names whose getter value is checked
     * @return
     * @throws Exception
     */
    public static boolean checkData(final Object pageClass, String noValue, String[] fieldNames) throws Exception {
        boolean flag = true;
        final Class cls = Class.forName(pageClass.getClass().getCanonicalName());
        final Method[] method = cls.getDeclaredMethods();
        for (int i = 0; i < method.length; i++) {
            if (method[i].getName().startsWith("g")) {
                if (CommonMethods.isStringInArray(method[i].getName().replace("get", ""), fieldNames, true)) {
                    final Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if (obj != null) {
                        //final Class[] types = new Class[]{String.class};
                        final Method setterMethod = cls.getMethod("get" + methodName);
                        Object setterValue = setterMethod.invoke(pageClass);
//                            if(!setterValue.equals(value)){
//                                flag=false;
//                                break;
//                            }
                        System.out.println("setterMethod......." + setterMethod.getName() + "----" + setterValue);
                        if (!setterMethod.getName().equals("getEmpQpd")) {
                            if (setterValue.equals(noValue)) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public static void exportToPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName, List<OJFSurveyDTO> employeeDetails, List<OJFSurveyDTO> surveyQuestionsParent, List<OJFSurveyDTO> surveyQuestions, List<OJFSurveyDTO> surveyAnswers) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + employeeDetails.get(0).getEmployee_code() + ".pdf\"");
            fis = (CommonMethods.class.getResourceAsStream("/OJF_Survey_Details.pdf"));
            System.out.println("fis = " + fis);

            Document document = new Document(PageSize.A4, 30, 30, 10, 10);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();

//            Table headerTable = new Table(3);
//            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/Defiance.gif");
//            System.out.println("the path is http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()+ "/css/images/Defiance.gif");
//            System.out.println("image header:::"+headerImage);
//
//            if (headerImage != null) {
//                headerImage.scalePercent(100);
//                headerImage.setAlignment(Image.ALIGN_LEFT);
//                Cell c1 = new Cell();
//                c1.setBorderWidth(0);
//                c1.addElement(headerImage);
//                headerTable.addCell(c1);
//            }
//            Cell cell=new Cell();
//            cell.setBorder(Rectangle.NO_BORDER);
//            headerTable.addCell(cell);
//            headerTable.setWidths(new int[]{40,0,60});
//            Phrase p = new Phrase();
//            p.add(headerTable);
//            cell=new Cell(new Phrase("OJF Survey Details",new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//
//            cell.setBorder(Rectangle.NO_BORDER);
//            headerTable.addCell(cell);
//            headerTable.setBorder(Rectangle.NO_BORDER);
            //HeaderFooter header = new HeaderFooter(headerTable);


            //HeaderFooter footer = new HeaderFooter( new Phrase("D-FO-HR-009/Version 2.0/wef:Oct 1,2010") );




//            HeaderFooter footer = new HeaderFooter();
//            footer.setHeaderFooter(new HeaderFooter(new Phrase("D-FO-HR-009/Version 2.0/wef:Oct 1,2010")), com.lowagie.text.pdf.headerfooter.PdfHeaderFooter.DISPLAY_ALL_PAGES);

            //document.setHeader(header);
            //document.setFooter(footer);

            document.open();


            document.add(insertEmployeeDetails(employeeDetails));
            insertParagraph(document, employeeDetails, surveyQuestionsParent,surveyQuestions, surveyAnswers);
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static Paragraph insertEmployeeDetails_name(EmployeeDTO employeeDetails) {
//
//        Paragraph para = new Paragraph();
//        Paragraph emptyLines = new Paragraph();
//        addEmptyLine(emptyLines, 3);
//        para.add(emptyLines);
//        para.add("Dear ");
//        para.add((employeeDetails.getEmployeeName()));
//        para.add(", ");
//        addEmptyLine(emptyLines, 1);
//        para.add(emptyLines);
//        para.add(CommonFunctions.getStringFromProperty(CommonConfigurations.ExternalExitFile,"exitSurveyHome").replace("<br>", "\n"));
//        addEmptyLine(emptyLines, 22);
//        para.add(emptyLines);
//
//        return para;
//
//    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static Paragraph insertEmployeeDetails(List<OJFSurveyDTO> employeeDetails) {

        Paragraph para = new Paragraph();
        Paragraph emptyLines = new Paragraph();
        para.add(new Phrase("Employee Name:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.get(0).getEmployee_name()));
//        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        para.add(new Phrase("RM Name:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.get(0).getPm_name()));
//        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        para.add(new Phrase("Joined Date:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.get(0).getJoined_date()));
        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        return para;

    }

    public static void insertParagraph(Document document, List<OJFSurveyDTO> employeeDetails, List<OJFSurveyDTO> surveyQuestionsParent, List<OJFSurveyDTO> surveyQuestion, List<OJFSurveyDTO> surveyAnswers) throws BadElementException, DocumentException {
        int questionNumber = 0;
        Paragraph para = null;
        Paragraph emptyLines = new Paragraph();

        for (OJFSurveyDTO surveyQuestionData : surveyQuestion) {
            if (!surveyQuestionData.getParentId().equals("0")) {
                
                for (OJFSurveyDTO surveyQuestionParentData : surveyQuestionsParent) {
                    //System.out.println(":PARENT ID :"+parentHeader+":PARENT:"+surveyQuestionData.parentId+":PAR QN:"+surveyQuestionParentData.questionId);
                    if(parentHeader != null && !parentHeader.equals(surveyQuestionData.parentId)) {
                        if(surveyQuestionParentData.questionId.equals(surveyQuestionData.parentId)) {
                            parentHeader = surveyQuestionData.parentId;
                            System.out.println(":PARENT ANSWER : "+surveyQuestionParentData.getName());
                            //para.setAlignment(#99BBE8);
                            para = new Paragraph();
                            Table table = new Table(1);
                            Cell cell = new Cell(surveyQuestionParentData.getName());
                            cell.setBackgroundColor(Color.decode("#99BBE8"));
                            table.setAlignment(1);
                            //Font font = new Font(Font.COURIER,8);
                            //para.setFont(font);
                            table.setWidth(100);
                            table.addCell(cell);
                            para.add(table);
                            para.add(emptyLines);
                            document.add(para);
                        }
                    }
                }
                //System.out.println(":RADIOO:"+surveyQuestionData.getAnswerType());
                if (surveyQuestionData.getAnswerType().equals(CommonConfigurations.SURVEY_ANSWER_RADIO)) {
                    questionNumber++;
                    Table table = new Table(1);
                    para = new Paragraph();
                    para.add(questionNumber + "." + surveyQuestionData.getName());
                    //para.setFont(11);
//                    addEmptyLine(emptyLines, 1);
                    para.add(emptyLines);
                    para.add(surveyQuestionData.getQuestionDescription());
//                    para.add(new RtfTableOfContents(surveyQuestionData.getQuestionDescription()));

                    for (OJFSurveyDTO surveryAnswerData : surveyAnswers) {
                        if (surveryAnswerData.getQuestionId().equals(surveyQuestionData.getQuestionId())) {
                            if (surveryAnswerData.getAnswerKey().equals(surveyQuestionData.getEmpAnswer())) {
                                //styling need to be added for this selected answer
                                System.out.println(":ANSWRERRRR:"+surveryAnswerData.getAnswerValue());
                                Cell cell = new Cell(surveryAnswerData.getAnswerValue());
                                cell.setBackgroundColor(Color.LIGHT_GRAY);
                                table.addCell(cell);
                            } else {
                                table.addCell(surveryAnswerData.getAnswerValue());
                            }
                        }
                    }
                    para.add(table);
                } else if (surveyQuestionData.getAnswerType().equals(CommonConfigurations.SURVEY_ANSWER_SLIDER) && surveyQuestionData.getParentId().equals("0")) {
                    para = new Paragraph();
                    Table table = new Table(5);
                    para.add(questionNumber + "." +surveyQuestionData.getName());
//                    addEmptyLine(emptyLines, 1);
                    para.add(emptyLines);
                    //para.add(new RtfTableOfContents(surveyQuestionData.getQuestionDescription()));

                    for (OJFSurveyDTO surveryAnswerData : surveyAnswers) {
                        if (surveryAnswerData.getQuestionId().equals(surveyQuestionData.getQuestionId())) {
                            if (surveryAnswerData.getAnswerKey().equals(surveyQuestionData.getEmpAnswer())) {
                                Cell cell = new Cell(surveryAnswerData.getAnswerValue());
                                cell.setBackgroundColor(Color.LIGHT_GRAY);
                                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);
                            } else {
                                Cell cell = new Cell(surveryAnswerData.getAnswerValue());
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);
                            }
                        }
                    }
                    para.add(table);
                } else if (surveyQuestionData.getAnswerType().equals(CommonConfigurations.SURVEY_ANSWER_FREE_TEXT)) {
                    para = new Paragraph();
//    ---------------   Phrase phrase=new Phrase(surveyQuestionData.getName(),new Font(Font.TIMES_ROMAN, 11, Font.BOLD));
                    para.add(surveyQuestionData.getName());
                    //addEmptyLine(emptyLines, 1);
                    para.add(emptyLines);
                    //para.add(new RtfTableOfContents(surveyQuestionData.getQuestionDescription()));

                    para.add(surveyQuestionData.getEmpAnswer());
                } else {
                    if (surveyQuestionData.getParentId().equals("0")) {
                        para = new Paragraph();
//                       para.add(questionNumber + "." +new Phrase( surveyQuestionData.getName(),new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
                        para.add(questionNumber + "." + surveyQuestionData.getName());
                       // addEmptyLine(emptyLines, 1);
                        para.add(emptyLines);
                        //para.add(new RtfTableOfContents(surveyQuestionData.getQuestionDescription()));

                        Table multipleTable = new Table(2);
                        for (OJFSurveyDTO surveyQuestionMultiple : surveyQuestion) {
                            if (surveyQuestionData.getQuestionId().equals(surveyQuestionMultiple.getParentId())) {
                                multipleTable.addCell(surveyQuestionMultiple.getName());
                                multipleTable.addCell(surveyQuestionMultiple.getEmpAnswer());
                            }
                        }
                        para.add(multipleTable);
                    }
                }
                document.add(para);
            }
        }
        if(employeeDetails.get(0).getOjf_survey_rating() != null ){
            para = new Paragraph();
            Table table = new Table(1);
            Cell cell = new Cell("Over All Rating");
            cell.setBackgroundColor(Color.decode("#99BBE8"));
            table.setAlignment(1);
            table.setWidth(100);
            table.addCell(cell);
            para.add(table);
            para.add(emptyLines);
            document.add(para);
            para = new Paragraph();
            para.add(employeeDetails.get(0).getOjf_survey_rating());
            para.add(emptyLines);
            document.add(para);
        }
    }

    public static void commonInsertCode(HSSFWorkbook hssfworkbook, HSSFSheet sheet, HSSFCellStyle cs, HSSFCellStyle contentCs, int rowNum, int cellNum, String content) {
        try {
            HSSFRow rowhead = sheet.createRow((short) 0);

            HSSFFont font = hssfworkbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);
            contentCs.setFont(font);
            contentCs = getCellStyle(hssfworkbook);

            HSSFCell cell;
            rowhead = sheet.createRow((short) (rowNum));
            cell = rowhead.createCell((short) cellNum);
            cell.setCellStyle(contentCs);
            cell.setCellValue(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static HSSFCellStyle getCellStyle(final HSSFWorkbook workBook) {
        final HSSFCellStyle cellstyleTblLeft = workBook.createCellStyle();
        cellstyleTblLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return cellstyleTblLeft;
    }
    
     public static void exportToExcelQuality(HttpServletResponse responseObj,ArrayList headerData,ArrayList excelData,String fileName) throws Exception{
//            System.out.println("In exportToExcel Function");

        ArrayList resultList =  new ArrayList();
         try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());

            responseObj.setContentType("application/ms-excel");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            responseObj.setHeader("Content-Type","application/force-download");

                int dataRows = excelData.size();

                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
                HSSFCellStyle cs = hssfworkbook.createCellStyle();
                HSSFDataFormat df = hssfworkbook.createDataFormat();
                cs.setDataFormat(df.getFormat("General"));
//              HSSFRow rowhead = sheet.createRow((short) 0);
//              rowhead.createCell((short) 0).setCellValue("Hariharan");
                HSSFRow rowhead = sheet.createRow((short) 0);

                HSSFFont font = hssfworkbook.createFont();
                font.setFontName(HSSFFont.FONT_ARIAL);
              //font.setFontHeightInPoints((short) 20);
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
              //font.setColor(HSSFColor.BLUE.index);
                cs.setFont(font);
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

                for(int i=0;i<dataRows;i++){
                     rowhead = sheet.createRow((short) (i+3));
                     resultList = (ArrayList) excelData.get(i);
                        for(int j=0;j<resultList.size();j++){
                            if(resultList.get(j)!=null){
                            rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
                            }
                        }
                }

                hssfworkbook.write(bos);
                bos.flush();
                bos.close();
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
     }
     
      public static String fileUpload(MultipartFile fileprop,int refId,String referenceName,String moduleName, CustomerService customerService) throws  IOException{
          String lastFileInsertId = null;
          System.out.println("mmmmmmmmmmmmm---------------------------------"+fileprop.getName());
          try {
            System.out.println("mmmmmmmmmmmmm---------------------------------"+fileprop.getName());
            System.out.println("-------file"+fileprop.getOriginalFilename());
            if(!fileprop.getOriginalFilename().equals(""))
            {
            String contentType = fileprop.getContentType();
            System.out.println("file content type >>>>>>>>>>>"+contentType);
            String fileName =refId+"~"+referenceName+"~"+fileprop.getOriginalFilename();
//             byte[] fileData = fileprop.getFileData();
            String uploadDir=CommonConfigurations.fileUploadPath;

                    new File(uploadDir).mkdir();
                    
            if (!fileName.equals("")) {
                File fileToCreate = new File(uploadDir, fileName);
                if (!fileToCreate.exists()){
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    byte[] fileData = fileprop.getBytes();
                    fileOutStream.write(fileData);
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
             lastFileInsertId = (String) customerService.addFileDb(fileName,contentType,referenceName,refId,moduleName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lastFileInsertId;
    }
	
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
//    public static void fileUpdate(MultipartFile fileprop,int refId,String referenceName,String moduleName, CustomerServiceImpl customerService) throws  IOException{
//        System.out.println("in file update---------------");
//        try {
//            System.out.println(fileprop.getName());
//            System.out.println(fileprop.getOriginalFilename());
//            if(!fileprop.getOriginalFilename().equals(""))
//            {
//            String contentType = fileprop.getContentType();
//            String fileName =refId+"~~"+referenceName+"~~"+fileprop.getOriginalFilename();
////            byte[] fileData = fileprop.getFileData();
//            String uploadDir=CommonConfigurations.fileUploadPath;
//
//                    new File(uploadDir).mkdir();
//                    
//            if (!fileName.equals("")) {
//                File fileToCreate = new File(uploadDir, fileName);
//                if (!fileToCreate.exists()){
//                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
//                    byte[] fileData = fileprop.getBytes();
//                    fileOutStream.write(fileData);
//                    fileOutStream.flush();
//                    fileOutStream.close();
//                }
//            }
//            customerService.updateFileDb(fileName,contentType,referenceName,refId,moduleName);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}
