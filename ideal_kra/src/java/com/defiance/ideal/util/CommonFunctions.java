/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class CommonFunctions {
public static final String fileUploadPath="D:/AttachedFiles";
 public static final String KRA_Description= "KRA_Description";
    public static final String KRA_UOM = "KRA_UOM";
    public static final String KRA_TARGET = "KRA_TARGET";
    public static final String KRA_WEIGHTAGE = "KRA_WEIGHTAGE(%)";
public static final String fileUploadPathKra="D:/kraUpload/";
public static final String cmpStructParentId = "0";
    public static String getSystemDate(String inputFormat) {
        DateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        System.out.println(dateFormat.format(date));
        return currentDate;
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
public static String commonCodeForFileUpload(File dir, MultipartFile file) throws IOException {
             Random random = new Random();
            int ran = random.nextInt();
        if(ran<0){
            ran = -(ran);
        }   
        String temp =  file.getOriginalFilename().trim();
        String splitFile[] = temp.split("\\.");
        String tempfile = splitFile[0] + ran +"."+splitFile[1];
        File file1 = new File(dir.getAbsolutePath() + "/" + tempfile);
        file1.createNewFile();
        file.transferTo(file1);
        return tempfile;
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
                if (CommonFunctions.isStringInArray(method[i].getName().replace("get", ""), fieldNames, true)) {
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

    public static HSSFCellStyle getCellStyle(final HSSFWorkbook workBook) {
        final HSSFCellStyle cellstyleTblLeft = workBook.createCellStyle();
        cellstyleTblLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyleTblLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return cellstyleTblLeft;
    }

//    public static void exportToExcel(HttpServletResponse responseObj, String fileName,EmployeeDTO empDetails,ApprovalDTO rmActionData,
//            ApprovalDTO finActionData,ApprovalDTO nsActionData,ApprovalDTO adActionData,ApprovalDTO hrActionData,ApprovalDTO rmClrData) throws Exception {
//        System.out.println("In exportToExcel Function");
//        InputStream fis = null;
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
//            responseObj.setContentType("application/ms-excel");
//            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + empDetails.getEmployeeNumber() + ".xls\"");
//            fis = (CommonFunctions.class.getResourceAsStream("/Exit_No_Dues_Form.xls"));
//            System.out.println("fis = " + fis);
//
//            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
//            final HSSFSheet sheet = hssfworkbook.getSheet("Exit - No Dues");
//
//            HSSFCellStyle cs = hssfworkbook.createCellStyle();
//            HSSFCellStyle contentCs = hssfworkbook.createCellStyle();
//            HSSFDataFormat df = hssfworkbook.createDataFormat();
//            cs.setDataFormat(df.getFormat("General"));
//
//            //Employee Master Data
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 2, 2, empDetails.getEmployeeNumber()); // Emp Number
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 2, 4, empDetails.getEmpBaseLocation()); // Base Location
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 2, empDetails.getEmployeeName()); // Emp Name
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 4, empDetails.getPracticeGroup()); // Pratice Group
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 4, 2, empDetails.getDesignation()); // Designations
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 4, 4, empDetails.getSubBand()); // Sub Band
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 5, 2, empDetails.getRmEmpNumber());// RM Emp Number
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 5, 4, empDetails.getEmpDoj()); // Date of Join
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 6, 2, empDetails.getRmName());// RM Name
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 6, 4, empDetails.getResignedDate());// Date of Resignation
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 7, 2, rmActionData.getLastWorkingDate()); // Last Working Date
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 7, 4, rmActionData.getLastWorkingDate());// Date of reliving
//            //RM Clearance
//            for(int i=0;i<CommonConfigurations.surrenderValueList.length;i++){
//                if(rmClrData.getCompDoc().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 10, 3, CommonConfigurations.surrenderValueList[i]);//Company Manuals / Documents / Training Material
//                }
//                if(rmClrData.getProjDoc().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 11, 3, CommonConfigurations.surrenderValueList[i]);//Project / function related transition documents
//                }
//                if(rmClrData.getCustApproval().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 12, 3, CommonConfigurations.surrenderValueList[i]);//Customer informed and approval obtained
//                }
//                if(rmClrData.getEmpQpd().equals(Integer.toString(i+1))){
//                    if(rmClrData.getEmpQpd().equals("1")){
//                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, 13, 3, CommonConfigurations.surrenderValueList[i]+"  "+rmClrData.getEmpQpdRemarks());//Is the QPD completed. Please specify the rating
//                    }else{
//                       commonInsertCode(hssfworkbook, sheet, cs, contentCs, 13, 3, CommonConfigurations.surrenderValueList[i]);//Is the QPD completed. Please specify the rating
//                    }
//                }
//                if(rmClrData.getRmOthers().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 14, 3, CommonConfigurations.surrenderValueList[i]+" Comments:"+rmClrData.getRmClrOverAllComments());//Any Other (Please specify) and RM Over all comments
//                }
//                if(adActionData.getIdCard().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 16, 3, CommonConfigurations.surrenderValueList[i]);//Admin - ID Card
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 17, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Access Card
//                }
//                if(adActionData.getDataCard().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 18, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Internet Data Card
//                }
//                if(adActionData.getBusinessCard().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 19, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Business Cards
//                }
//                if(adActionData.getCmpResPhone().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 20, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Company Provided Residence Phone
//                }
//                if(adActionData.getMobilePhone().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 21, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Mobile phone equipments (includes accessories)
//                }
//                if(adActionData.getSimCard().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 22, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Mobile Phone SIM Card
//                }
//                if(adActionData.getOutBillClr().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 23, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Outstanding Mobile Bills Cleared
//                }
//                if(adActionData.getLaptop().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 24, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Laptop
//                }
//                if(adActionData.getCdPenDrive().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 25, 3, CommonConfigurations.surrenderValueList[i]);//Admin - All external mass storage devices (CD/pen drives)
//                }
//                if(adActionData.getCabinKey().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 26, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Cabin Keys
//                }
//                if(adActionData.getDrawerKey().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 27, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Drawer / Storage Keys
//                }
//                if(adActionData.getAdOther().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 28, 3, CommonConfigurations.surrenderValueList[i]+" Comments:"+adActionData.getAdOverAllComments());//Admin - Any Other (Please specify)
//                }
//                if(nsActionData.getDesktopPwd().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 30, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Login Id
//                }
//                if(nsActionData.getEmailDeactivation().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 31, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Email ID - deactiviated
//                }
//                if(nsActionData.getNsOthers().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 32, 3, CommonConfigurations.surrenderValueList[i]+" Comments:"+nsActionData.getNsOverAllComments());// NS -- Others
//                }
//                if(nsActionData.getSalesCrm().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 34, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Sales CRM ID
//                }
//                if(nsActionData.getFinDax().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 35, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Finance DAX
//                }
//                if(finActionData.getLastPaidSalary().equals(Integer.toString(i+1))){
//                    if(finActionData.getLastPaidSalary().equals("1")){
//                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 3, CommonConfigurations.surrenderValueList[i]+" "+finActionData.getLastPaidSalaryRemarks());//Fin --Last Paid Salary
//                    }else{
//                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Last Paid Salary
//                    }
//                }
//                if(finActionData.getAdvanceSettlement().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 38, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Advance Settlement
//                }
//                if(finActionData.getSalaryAdvance().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 39, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Salary Advance
//                }
//                if(finActionData.getTravelAdvance().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 40, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Traval Advance
//                }
//                if(finActionData.getRelocation().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 41, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Relocation benefits
//                }
//                if(finActionData.getJoiningBonus().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 42, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Joining Bonus
//                }
//                if(finActionData.getNoticeReimburse().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 43, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Notice Reimbursement
//                }
//                if(finActionData.getProfessionalFee().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 44, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Professional Fee
//                }
//                if(finActionData.getLoans().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 45, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Loans and Advances
//                }
//                if(finActionData.getOfficialExpenses().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 46, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Outstanding Loans and official expenses cleared
//                }
//                if(finActionData.getOther().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 47, 3, CommonConfigurations.surrenderValueList[i]+" Comments:"+finActionData.getFinOverAllComments());//Fin --Others
//                }
//                if(hrActionData.getIdealToolDeactivated().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 52, 3, CommonConfigurations.surrenderValueList[i]);//HR --iDeal Deactivated
//                }
//                if(hrActionData.getLeaveBalance().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 53, 3, CommonConfigurations.surrenderValueList[i]);//HR --Leave Balance for Encashment
//                }
//                if(hrActionData.getOverseasBond().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 57, 3, CommonConfigurations.surrenderValueList[i]);//HR --Overseas Bond / Training Bond
//                }
//                if(hrActionData.getExitInterview().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 58, 3, CommonConfigurations.surrenderValueList[i]);//HR --Exit Interview
//                }
//                if(hrActionData.getHrInsurance().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 59, 3, CommonConfigurations.surrenderValueList[i]);//HR --Insurance
//                }
//                if(hrActionData.getHrOthers().equals(Integer.toString(i+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 60, 3, CommonConfigurations.surrenderValueList[i]+" Comments:"+hrActionData.getHrOverAllComments());//HR --Others
//                }
//            }
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 10, 4, empDetails.getRmName());//RM - Signature of approving authority
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 16, 4, adActionData.getName());//Admin - Signature of approving authority
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 30, 4, nsActionData.getName());//NS - Signature of approving authority
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 4, finActionData.getName());//Finance - Signature of approving authority
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 49, 4, hrActionData.getName());//HR - Signature of approving authority
//            //HR Clearance
//            for(int j=0;j<CommonConfigurations.exitType.length;j++){
//                if(hrActionData.getExitType().equals(Integer.toString(j+1))){
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 49, 3, CommonConfigurations.exitType[j]);//HR --Exit Type
//                }
//            }
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 54, 3, rmActionData.getDaysServed());// HR --Notice Pay Served
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 55, 3, rmActionData.getBalNoticePeriod());// HR --Notice Pay Recovery
//            // Needs Clarification what to display in No. Days to be recovered (if any)
//            //commonInsertCode(hssfworkbook, sheet, cs, contentCs, 56, 3, "No. Days to be recovered (if any) --NA");// HR --No. Days to be recovered (if any)
//            //Self Data
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 62, 2, "Permanent Address:" + empDetails.getContactAddr());
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 62, 3, "Comm Address:" + empDetails.getContactAddr());
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 63, 2, empDetails.getPersonalEmail());//Contact e-mail ID
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 63, 4, empDetails.getContactNo());//Contact Phone No:
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 65, 2, empDetails.getEmployeeName());//Employee Signature
//            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 65, 4, CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT));//Current Data
//
//            hssfworkbook.write(bos);
//            bos.flush();
//            bos.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    public static void exportToExcel(HttpServletResponse responseObj, ArrayList headerData, ArrayList excelData, String fileName) throws Exception {
////            System.out.println("In exportToExcel Function");
//
//        ArrayList resultList = new ArrayList();
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
//
//            responseObj.setContentType("application/ms-excel");
//            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//            responseObj.setHeader("Content-Type", "application/force-download");
//
//            int dataRows = excelData.size();
//
//            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
//            HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
//            HSSFCellStyle cs = hssfworkbook.createCellStyle();
//            HSSFDataFormat df = hssfworkbook.createDataFormat();
//            cs.setDataFormat(df.getFormat("General"));
////              HSSFRow rowhead = sheet.createRow((short) 0);
////              rowhead.createCell((short) 0).setCellValue("Hariharan");
//            HSSFRow rowhead = sheet.createRow((short) 0);
//
//            HSSFFont font = hssfworkbook.createFont();
//            font.setFontName(HSSFFont.FONT_ARIAL);
//            //font.setFontHeightInPoints((short) 20);
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//            //font.setColor(HSSFColor.BLUE.index);
//            cs.setFont(font);
//            ArrayList headerList = new ArrayList();
//
//            for (int header = 0; header < headerData.size(); header++) {
//                rowhead = sheet.createRow((short) header);
//                headerList = (ArrayList) headerData.get(header);
//                for (int i = 0; i < headerList.size(); i++) {
//                    HSSFCell cell = rowhead.createCell((short) i);
//                    cell.setCellValue(headerList.get(i).toString());
//                    cell.setCellStyle(cs);
//
//                }
//                //HSSFCell cell = rowhead.createCell((short) header);
//                //cell.setCellValue(headerData.get(header).toString());
//                //cell.setCellStyle(cs);
//            }
//
//            for (int i = 0; i < dataRows; i++) {
//                rowhead = sheet.createRow((short) (i + 3));
//                resultList = (ArrayList) excelData.get(i);
//                for (int j = 0; j < resultList.size(); j++) {
//                    if (resultList.get(j) != null) {
//                        rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
//                    }
//                }
//            }
//
//            hssfworkbook.write(bos);
//            bos.flush();
//            bos.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
    
       public static void exportRMKraExcel(HttpServletResponse responseObj, ArrayList excelData, String fileName, String sheetName, String rename) throws Exception {
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
            
            HSSFFont boldFont = hssfworkbook.createFont();
            boldFont.setFontHeightInPoints((short)10);
            boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //Setting Bold font
            boldFont.setFontName(HSSFFont.FONT_ARIAL);
            HSSFCellStyle boldStyle = hssfworkbook.createCellStyle();
            boldStyle.setBorderTop((short) 1);
            boldStyle.setBorderBottom((short) 1);
            boldStyle.setBorderLeft((short) 1);
            boldStyle.setBorderRight((short) 1);
            boldStyle.setFont(boldFont);
            
            HSSFFont normalFont = hssfworkbook.createFont();
            normalFont.setFontHeightInPoints((short)10);
            normalFont.setFontName(HSSFFont.FONT_ARIAL);
            HSSFCellStyle normalStyle = hssfworkbook.createCellStyle();
            normalStyle.setBorderTop((short) 1);
            normalStyle.setBorderBottom((short) 1);
            normalStyle.setBorderLeft((short) 1);
            normalStyle.setBorderRight((short) 1);
            normalStyle.setFont(normalFont);
           
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
                            cell = rowhead.createCell((short) j);
                            cell.setCellValue(removeNull(resultList.get(j).toString()));
                            if(i==0 ){                                
                                    cell.setCellStyle(boldStyle);
                                
                            }else{
                                cell.setCellStyle(normalStyle);
                            }
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
            
            
            
    public static void exportToExcelApproval(HttpServletResponse responseObj, ArrayList excelData, String fileName, String sheetName, String rename) throws Exception {
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
            
            HSSFFont boldFont = hssfworkbook.createFont();
            boldFont.setFontHeightInPoints((short)10);
            boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //Setting Bold font
            boldFont.setFontName(HSSFFont.FONT_ARIAL);
            HSSFCellStyle boldStyle = hssfworkbook.createCellStyle();
            boldStyle.setBorderTop((short) 1);
            boldStyle.setBorderBottom((short) 1);
            boldStyle.setBorderLeft((short) 1);
            boldStyle.setBorderRight((short) 1);
            boldStyle.setFont(boldFont);
            
            HSSFFont normalFont = hssfworkbook.createFont();
            normalFont.setFontHeightInPoints((short)10);
            normalFont.setFontName(HSSFFont.FONT_ARIAL);
            HSSFCellStyle normalStyle = hssfworkbook.createCellStyle();
            normalStyle.setBorderTop((short) 1);
            normalStyle.setBorderBottom((short) 1);
            normalStyle.setBorderLeft((short) 1);
            normalStyle.setBorderRight((short) 1);
            normalStyle.setFont(normalFont);
           
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
                            cell = rowhead.createCell((short) j);
                            cell.setCellValue(removeNull(resultList.get(j).toString()));
                            if(i==0 || i==1){
                                if(j==0 || j==2 || j==4 || j==6){
                                    cell.setCellStyle(boldStyle);
                                }else{
                                    cell.setCellStyle(normalStyle);
                                }
                            }else if(i==3 || i == (dataRows-1)){
                                cell.setCellStyle(boldStyle);
                            }else{
                                cell.setCellStyle(normalStyle);
                            }
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

    public static String removeNull(String stringToProcess) {
        String processedString;
        if (stringToProcess.equals(null) || stringToProcess.equals("null")) {
            processedString = "";
        } else {
            processedString = (stringToProcess);
        }
        return processedString;
    }

    public static String convertDate(String inputDate, String fromFormat, String toFormat) throws ParseException {
        System.out.println("convertDate ");
        SimpleDateFormat fromdateFormat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat todateFormat = new SimpleDateFormat(toFormat);
        System.out.println("======== The date is ========= " + fromdateFormat.parse(inputDate));
        Date convertedDate = fromdateFormat.parse(inputDate);
        String currentDate = todateFormat.format(convertedDate);
        return currentDate;
    }

    /**
     * 
     * @param documentName
     * @param tableHeaderName
     * @param data
     * @param nc
     * @param width
     * @param tableHeader
     * @param textFont
     * @param getterMethodName
     * @param req
     * @param res
     * @return
     * @throws DocumentException
     * @throws Exception
     */
//    public static PdfPTable drawTable(String documentName, String tableHeaderName, List data, int nc, int[] width, String[] tableHeader, Font textFont, String[] getterMethodName, HttpServletRequest req, HttpServletResponse res,boolean  chartFlag) throws DocumentException, Exception {
    public static void drawTable(HttpServletResponse responseObj, List data, int nc, String[] tableHeader, String[] getterMethodName, HttpServletRequest req, boolean chartFlag, String fileName) throws DocumentException, Exception {
        try {
            ArrayList resultList = new ArrayList();

            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());

            responseObj.setContentType("application/ms-excel");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            responseObj.setHeader("Content-Type", "application/force-download");
            HSSFWorkbook hssfworkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
            HSSFRow rowhead = sheet.createRow((short) 0);
            HSSFFont font = hssfworkbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);
            if (tableHeader != null) {
                rowhead = sheet.createRow((short) 0);
                for (int header = 0; header < nc; header++) {
                    HSSFCell cell = rowhead.createCell((short) header);
                    cell.setCellValue(tableHeader[header]);
                    cell.setCellStyle(cs);
                }
            }
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    rowhead = sheet.createRow((short) i + 1);
                    int count = 0;
                    for (String methodName : getterMethodName) {
                        HSSFCell cell = rowhead.createCell((short) count);
                        Method method = data.get(i).getClass().getMethod(methodName);
                        Object dataObj = method.invoke(data.get(i));
                        if (methodName.equalsIgnoreCase("getPassport_statusX")) {
                            if (dataObj != null) {
                                if (dataObj.equals("a")) {
                                    cell.setCellValue("Active");
                                } else if (dataObj.equals("i")) {
                                    cell.setCellValue("Inactive");
                                } else {
                                    cell.setCellValue("---");
                                }
                            } else {
                                cell.setCellValue("---");
                            }
                        } else if (methodName.equalsIgnoreCase("getVisa_visitX")) {
                            if (dataObj != null) {
                                if (dataObj.equals("1")) {
                                    cell.setCellValue("Multiple");
                                } else if (dataObj.equals("0")) {
                                    cell.setCellValue("Single");
                                } else {
                                    cell.setCellValue("---");
                                }
                            } else {
                                cell.setCellValue("---");
                            }
                        } else if (methodName.equalsIgnoreCase("getSkill_categoryX")) {
                            if (dataObj != null) {
                                if (dataObj.equals("F")) {
                                    cell.setCellValue("Functional");
                                } else if (dataObj.equals("T")) {
                                    cell.setCellValue("Technical");
                                } else if (dataObj.equals("N")) {
                                    cell.setCellValue("Non Technical");
                                } else {
                                    cell.setCellValue("---");
                                }
                            } else {
                                cell.setCellValue("---");
                            }
                        } else if (methodName.equalsIgnoreCase("getSkill_typeX")) {
                            if (dataObj != null) {
                                if (dataObj.equals("P")) {
                                    cell.setCellValue("Primary");
                                } else if (dataObj.equals("S")) {
                                    cell.setCellValue("Secondary");
                                } else {
                                    cell.setCellValue("---");
                                }
                            } else {
                                cell.setCellValue("---");
                            }
                        } else if (methodName.equalsIgnoreCase("getAddress_typeX")) {
                            if (dataObj != null) {
                                if (dataObj.equals("p")) {
                                    cell.setCellValue("Permanent");
                                } else if (dataObj.equals("c")) {
                                    cell.setCellValue("Communication");
                                } else if (dataObj.equals("T")) {
                                    cell.setCellValue("Temporary");
                                } else {
                                    cell.setCellValue("---");
                                }
                            } else {
                                cell.setCellValue("---");
                            }
                        } else {
                            if (dataObj != null && !"".equals(dataObj)) {
                                cell.setCellValue(dataObj.toString());
                            }
                        }
                        count++;
                    }
                }
            }
            hssfworkbook.write(bos);
            bos.flush();
            bos.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
