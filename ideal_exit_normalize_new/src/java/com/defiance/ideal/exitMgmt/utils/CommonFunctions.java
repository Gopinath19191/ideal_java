/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.utils;

import com.lowagie.text.Cell;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.approval.services.ApprovalService;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;

import com.lowagie.text.BadElementException;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.IOException;
import java.awt.Color;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import org.apache.poi.hssf.util.Region;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class CommonFunctions {

    public static final Font ques_des = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10);
    public static final Font ques = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
     public static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
private static DateFormat dfm = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
private static long unixtime; 
    public static String getStringFromProperty(String filePath,String inputKey) {
        Properties configExitFile = new Properties();
        try {
            configExitFile.load(new FileInputStream(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return configExitFile.getProperty(inputKey);
    }

    public static String getSystemDate(String inputFormat) {
        DateFormat dateFormat1 = new SimpleDateFormat(inputFormat);
        Date date = new Date();
        String currentDate = dateFormat1.format(date);
        System.out.println(dateFormat1.format(date));
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

    public static final void getMenuIdInRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("rmApprovalModId", CommonConfigurations.EXIT_RM_APPROVAL_MODULE_ID);
        request.setAttribute("rmCleranceModId", CommonConfigurations.EXIT_RM_CLERANCE_MODULE_ID);
        request.setAttribute("finApprovalModId", CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID);
        request.setAttribute("adminApprovalModId", CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID);
        request.setAttribute("nsApprovalModId", CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID);
        request.setAttribute("hrApprovalModId", CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID);
        request.setAttribute("rmApprove", CommonConfigurations.RM_APPROVE_STATUS);
        request.setAttribute("rmReject", CommonConfigurations.RM_REJECT_STATUS);
        request.setAttribute("finApprove", CommonConfigurations.FIN_APPROVE_STATUS);
        request.setAttribute("finReject", CommonConfigurations.FIN_REJECT_STATUS);
        request.setAttribute("adminApprove", CommonConfigurations.ADMIN_APPROVE_STATUS);
        request.setAttribute("adminReject", CommonConfigurations.ADMIN_REJECT_STATUS);
        request.setAttribute("nsApprove", CommonConfigurations.NS_APPROVE_STATUS);
        request.setAttribute("nsReject", CommonConfigurations.NS_REJECT_STATUS);
        request.setAttribute("hrApprove", CommonConfigurations.HR_APPROVE_STATUS);
        request.setAttribute("hrReject", CommonConfigurations.HR_REJECT_STATUS);
        request.setAttribute("surveySubmitStatus", CommonConfigurations.SURVEY_SUBMIT_STATUS);
    }

    public static final HashMap getExitType(){
        HashMap exitTypeMap = new HashMap();
            exitTypeMap.put("R", "Resignation");
            exitTypeMap.put("T", "Termination");
            exitTypeMap.put("B", "Absconding");
            return exitTypeMap;
    }
    
    public static final HashMap getMonthList(){
        HashMap monthList = new HashMap();
            monthList.put("1", "January");
            monthList.put("2", "Feburary");
            monthList.put("3", "March");
            monthList.put("4", "April");
            monthList.put("5", "May");
            monthList.put("6", "June");
            monthList.put("7", "July");
            monthList.put("8", "August");
            monthList.put("9", "September");
            monthList.put("10", "October");
            monthList.put("11", "November");
            monthList.put("12", "December");
            return monthList;
    }

    public static final void getSurrenderValueList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("surrenderList", CommonConfigurations.surrenderValueList);
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

    public static void exportToExcel(HttpServletResponse responseObj, String fileName, EmployeeDTO empDetails, ApprovalDTO rmActionData,
            ApprovalDTO finActionData, ApprovalDTO nsActionData, ApprovalDTO adActionData, ApprovalDTO hrActionData,
            List<ApprovalDTO> finMultipleData) throws Exception {
        System.out.println("In exportToExcel Function");
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/ms-excel");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + empDetails.getEmployeeNumber() + ".xls\"");
            fis = (CommonFunctions.class.getResourceAsStream("/Exit_No_Dues_Form.xls"));
            System.out.println("fis = " + fis);

            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet("Exit - No Dues");

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle contentCs = hssfworkbook.createCellStyle();
            HSSFCellStyle contentCs1 = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));

            //Employee Master Data
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 2, 2, empDetails.getEmployeeNumber()); // Emp Number
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 2, 4, empDetails.getEmpBaseLocation()); // Base Location
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 2, empDetails.getEmployeeName()); // Emp Name
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 4, empDetails.getPracticeGroup()); // Pratice Group
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 4, 2, empDetails.getDesignation()); // Designations
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 4, 4, empDetails.getSubBand()); // Sub Band
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 5, 2, empDetails.getRmEmpNumber());// RM Emp Number
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 5, 4, dateFormat.format(empDetails.getEmpDoj())); // Date of Join
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 6, 2, empDetails.getRmName());// RM Name
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 6, 4, dateFormat.format(empDetails.getResignedDate()));// Date of Resignation
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 7, 2, dateFormat.format(rmActionData.getLastWorkingDate())); // Last Working Date
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 7, 4, dateFormat.format(rmActionData.getLastWorkingDate()));// Date of reliving
            //RM Clearance
            for (int i = 0; i < CommonConfigurations.surrenderValueList.length; i++) {
                if (rmActionData.getCompDoc().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 10, 3, CommonConfigurations.surrenderValueList[i]);//Company Manuals / Documents / Training Material
                }
                if (rmActionData.getProjDoc().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 11, 3, CommonConfigurations.surrenderValueList[i]);//Project / function related transition documents
                }
                if (rmActionData.getCustApproval().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 12, 3, CommonConfigurations.surrenderValueList[i]);//Customer informed and approval obtained
                }
                if (rmActionData.getEmpQpd().equals(Integer.toString(i + 1))) {
                    if (rmActionData.getEmpQpd().equals("1")) {
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, 13, 3, CommonConfigurations.surrenderValueList[i] + "  " + rmActionData.getEmpQpdRemarks());//Is the QPD completed. Please specify the rating
                    } else {
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, 13, 3, CommonConfigurations.surrenderValueList[i]);//Is the QPD completed. Please specify the rating
                    }
                }
                if (rmActionData.getRmOthers().equals(Integer.toString(i + 1))) {
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 14, 3, CommonConfigurations.surrenderValueList[i] + " Comments:" + rmClrData.getRmClrOverAllComments());//Any Other (Please specify) and RM Over all comments
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 14, 3, CommonConfigurations.surrenderValueList[i]);//Any Other (Please specify) and RM Over all comments
                }
                if (adActionData.getIdCard().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 16, 3, CommonConfigurations.surrenderValueList[i]);//Admin - ID Card
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 17, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Access Card
                }
                if (adActionData.getDataCard().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 18, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Internet Data Card
                }
                if (adActionData.getBusinessCard().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 19, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Business Cards
                }
                if (adActionData.getCmpResPhone().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 20, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Company Provided Residence Phone
                }
                if (adActionData.getMobilePhone().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 21, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Mobile phone equipments (includes accessories)
                }
                if (adActionData.getSimCard().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 22, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Mobile Phone SIM Card
                }
                if (adActionData.getOutBillClr().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 23, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Outstanding Mobile Bills Cleared
                }
                if (adActionData.getLaptop().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 24, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Laptop
                }
                if (adActionData.getCdPenDrive().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 25, 3, CommonConfigurations.surrenderValueList[i]);//Admin - All external mass storage devices (CD/pen drives)
                }
                if (adActionData.getCabinKey().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 26, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Cabin Keys
                }
                if (adActionData.getDrawerKey().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 27, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Drawer / Storage Keys
                }
                if (adActionData.getAdOther().equals(Integer.toString(i + 1))) {
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 28, 3, CommonConfigurations.surrenderValueList[i] + " Comments:" + adActionData.getAdOverAllComments());//Admin - Any Other (Please specify)
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 28, 3, CommonConfigurations.surrenderValueList[i]);//Admin - Any Other (Please specify)
                }
                if (nsActionData.getDesktopPwd().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 30, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Login Id
                }
                if (nsActionData.getEmailDeactivation().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 31, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Email ID - deactivated
                }
                if (nsActionData.getNsOthers().equals(Integer.toString(i + 1))) {
//                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 32, 3, CommonConfigurations.surrenderValueList[i] + " Comments:" + nsActionData.getNsOverAllComments());// NS -- Others
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 32, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Others
                }
                if (nsActionData.getSalesCrm().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 34, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Sales CRM ID
                }
                if (nsActionData.getFinDax().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 35, 3, CommonConfigurations.surrenderValueList[i]);// NS -- Finance DAX
                }
                ApprovalDTO testData = new ApprovalDTO();
                
                commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 3, (String) CommonFunctions.getMonthList().get(finActionData.getLastPaidSalary()));//Fin --Last Paid Salary
                commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 4, finActionData.getLastPaidSalaryAmt());//Fin --Last Paid Salary Amount
                if (finActionData.getSalaryAdvance().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 38, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Salary Advance
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 38, 4, finActionData.getSalAdvAmt());//Fin --Salary Advance Amount
                }
                if (finActionData.getTravelAdvance().equals(Integer.toString(i + 1))) {
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 39, 3, CommonConfigurations.surrenderValueList[i]);//Fin --Traval Advance
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, 39, 4, finActionData.getTravelAdvAmt());//Fin --Traval Advance Amount
                }
            }
                int y=39;
                int z = ++y;
                if (finMultipleData.size() != 0) {
                for (int increment = 0; increment < finMultipleData.size(); increment++) {
                    if (finMultipleData.get(increment).getOtherCategory().equals("L")) {
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 0, "");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 1, "");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 2, "Others Loans and advances");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 3, getYesNoValue(finMultipleData.get(increment).getOther()));
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 4, finMultipleData.get(increment).getOtherAmt());
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 5, "");
                        z++;
                    }
                }
            }else{
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 0, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 1, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 2, "Others Loans and advances");
                    if(finActionData.getOtherLoans()!=null){
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 3, getYesNoValue(finActionData.getOther()));
                    }else{commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 3, finActionData.getOtherAmt());
                    }commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 4, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, z, 5, "");
                    z++;
            }
                //After Multi add data
                String[] finRecovery = {"3","Recoveries","Relocation benefits",getYesNoValue(finActionData.getRelocation()),finActionData.getRelocationAmt(),""};
                String[] finJoinBouns = {"","","Joining Bonus",getYesNoValue(finActionData.getJoiningBonus()),finActionData.getJoiningBonusAmt(),""};
                String[] finNotReim = {"","","Notice Reimbursement",getYesNoValue(finActionData.getNoticeReimburse()),finActionData.getNoticeReimbAmt(),""};
                String[] finProFee = {"","","Professional Fee",getYesNoValue(finActionData.getProfessionalFee()),finActionData.getProfessionalfeeAmt(),""};
                String[] finLoanAdv = {"","","Loans and Advances",getYesNoValue(finActionData.getLoans()),finActionData.getLoansAmt(),""};
                String[] finOutLoanOff = {"","","Outstanding Loans and official expenses",getYesNoValue(finActionData.getOfficialExpenses()),finActionData.getOfficialExpAmt(),""};
                int thrdColMergeFrm = z;
                commonCode(finRecovery, hssfworkbook, sheet, cs, contentCs, z++);//43
                commonCode(finJoinBouns, hssfworkbook, sheet, cs, contentCs, z++);//44
                commonCode(finNotReim, hssfworkbook, sheet, cs, contentCs, z++);//45
                commonCode(finProFee, hssfworkbook, sheet, cs, contentCs, z++);//46
                commonCode(finLoanAdv, hssfworkbook, sheet, cs, contentCs, z++);//47
                commonCode(finOutLoanOff, hssfworkbook, sheet, cs, contentCs, z++);//48
                int p=z;
                int q = p;
                if (finMultipleData.size() != 0) {
                for (int increment = 0; increment < finMultipleData.size(); increment++) {
                    if (finMultipleData.get(increment).getOtherCategory().equals("R")) {
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 0, "");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 1, "");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 2, "Any Other (Please specify)");
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 3, getYesNoValue(finMultipleData.get(increment).getOther()));
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 4, finMultipleData.get(increment).getOtherAmt());
                        commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 5, "");
                        q++;
                    }
                }
            }else{
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 0, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 1, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 2, "Any Other (Please specify)");
                    if(finActionData.getOther()!=null){
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 3, getYesNoValue(finActionData.getOther()));
                    }else{commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 3, finActionData.getOtherAmt());
                    }commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 4, "");
                    commonInsertCode(hssfworkbook, sheet, cs, contentCs, q, 5, "");
                    q++;
            }
                sheet.addMergedRegion(new Region(38, (short) 0, --thrdColMergeFrm, (short) 0));//Column to merge number 2 in finance
                sheet.addMergedRegion(new Region(38, (short)1, thrdColMergeFrm, (short)1));//Column to merge Advance settlement in finance
                int thrdColMergeTo =q;
                int thrdColMergeFrom = ++thrdColMergeFrm;
                 sheet.addMergedRegion(new Region(thrdColMergeFrom, (short)0, thrdColMergeTo, (short)0));//Column to merge number 3 in finance
                 sheet.addMergedRegion(new Region(thrdColMergeFrom, (short)1, thrdColMergeTo, (short)1));//Column to merge recoveries in finance
                 sheet.addMergedRegion(new Region(38, (short)5, thrdColMergeTo, (short)5));//Column to merge signature in finance
                String[] finTotalAmt = {"4","","Total Amount","",finActionData.getTotalAmount(),""};
                String[] hrTitle = {"HR","","","","",""};
                String exitTypeValue = null;
                for(int h=0;h<CommonConfigurations.exitType.length;h++){
                    if(hrActionData.getExitType().equals(Integer.toString(h+1))){
                        exitTypeValue = CommonConfigurations.exitType[h];
                    }
                }
                HashMap exitTypeMap = CommonFunctions.getExitType();
                String exitType = (String) exitTypeMap.get(hrActionData.getExitType());
//                String[] hrExitTypeRes = {"1","Exit Type","Resignation",exitTypeValue,"",""};
                String[] hrExitTypeRes = {"1","Exit Type","Resignation",(String)CommonFunctions.getExitType().get(hrActionData.getExitType()),"",""};
                String[] hrExitTypeTer = {"","","Termination",exitTypeValue,"",""};
                String[] hrExitTypeAbs = {"","","Absconding",exitTypeValue,"",""};
                String[] hrIdealDeact = {"2","iDeal tool access has been deactivated","",getYesNoValue(hrActionData.getIdealToolDeactivated()),"",""};
                String[] hrLeaveBal = {"3","Leave Balance for Encashment","",getYesNoValue(hrActionData.getLeaveBalance()),"",""};
                String[] hrNtcPayServ = {"4","Notice Pay Served","",rmActionData.getDaysServed(),"",""};
                String[] hrNtcPayBal = {"","Notice Pay Recovery","",rmActionData.getBalNoticePeriod(),"",""};
                String[] hrDaysToRec = {"","No. Days to be recovered (if any)","","","",""};
                String[] hrOverBnd = {"5","Overseas Bond / Training Bond","",getYesNoValue(hrActionData.getOverseasBond()),"",""};
                String[] hrExitInter = {"6","Exit Interview","",getYesNoValue(hrActionData.getExitInterview()),"",""};
                String[] hrExitInsur = {"7","Insurance","",getYesNoValue(hrActionData.getHrInsurance()),"",""};
                String[] hrOthers = {"8","Any Other (Please specify)","",getYesNoValue(hrActionData.getHrOthers()),"",""};
                String[] empSelf = {"Self :","","","","",""};
                String[] empAdd = {"Employee Address for future communication","","Communication Address:"+empDetails.getEmpAddress(),"","",""};
//                String[] empAdd = {"Employee Address for future communication","","Permanent Address:"+empDetails.getContactAddr(),"","Communication Address:"+empDetails.getContactAddr(),""};
                String[] empEmail = {"Contact e-mail ID","",empDetails.getPersonalEmail(),"","Contact Phone No:",empDetails.getContactNo()};
                String[] empInfo = {"Note : FFS process will be communicated within 4 weeks from the last working date."+
                "I hereby accept to get the necessary clearance from the respective departments on this form and acknowledge acceptance on the details mentioned here-in.","","","","",""};
                String[] empSign = {"Employee Signature","",empDetails.getEmployeeName(),"","Date",CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT)};
                commonCode(finTotalAmt, hssfworkbook, sheet, cs, contentCs, q++);//52
                commonCode(hrTitle, hssfworkbook, sheet, cs, contentCs, q++);//53
                commonCode(hrExitTypeRes, hssfworkbook, sheet, cs, contentCs, q++);//54
                commonCode(hrExitTypeTer, hssfworkbook, sheet, cs, contentCs, q++);//55
                commonCode(hrExitTypeAbs, hssfworkbook, sheet, cs, contentCs, q++);//56
                commonCode(hrIdealDeact, hssfworkbook, sheet, cs, contentCs, q++);//57
                commonCode(hrLeaveBal, hssfworkbook, sheet, cs, contentCs, q++);//58
                int hrNumForth = q++;
                commonCode(hrNtcPayServ, hssfworkbook, sheet, cs, contentCs, hrNumForth);//59
                commonCode(hrNtcPayBal, hssfworkbook, sheet, cs, contentCs, q++);//60
                commonCode(hrDaysToRec, hssfworkbook, sheet, cs, contentCs, q++);//61
                commonCode(hrOverBnd, hssfworkbook, sheet, cs, contentCs, q++);//62
                commonCode(hrExitInter, hssfworkbook, sheet, cs, contentCs, q++);//63
                commonCode(hrExitInsur, hssfworkbook, sheet, cs, contentCs, q++);//64
                int hrLastCol = q++;
                commonCode(hrOthers, hssfworkbook, sheet, cs, contentCs, hrLastCol);//65
                commonCode(empSelf, hssfworkbook, sheet, cs, contentCs, q++);//66
                commonCode(empAdd, hssfworkbook, sheet, cs, contentCs, q++);//67
                commonCode(empEmail, hssfworkbook, sheet, cs, contentCs, q++);//68
                commonCode(empInfo, hssfworkbook, sheet, cs, contentCs, q++);//69
                commonCode(empSign, hssfworkbook, sheet, cs, contentCs, q++);//70

                int hrTitleMrg = ++thrdColMergeTo;
                 sheet.addMergedRegion(new Region(hrTitleMrg, (short)0, hrTitleMrg, (short)5));// Column to merge the hr title
                 int hrFrstCol = ++hrTitleMrg;
                 commonInsertCode(hssfworkbook, sheet, cs, contentCs, hrFrstCol, 5, hrActionData.getName());//HR - Signature of approving authority
                 sheet.addMergedRegion(new Region(hrFrstCol, (short)0, hrFrstCol+2, (short)0));// Column to merge the hr serial number 1
                 int hrScndCol = hrTitleMrg;
                 sheet.addMergedRegion(new Region(hrScndCol, (short)1, hrScndCol+2, (short)1));// Column to merge the hr exit type column
                 int hrForthCol = hrTitleMrg;
                 sheet.addMergedRegion(new Region(hrForthCol, (short)3, hrForthCol+2, (short)3));// Column to merge the hr exit type answer
                 sheet.addMergedRegion(new Region(hrNumForth, (short)0, hrNumForth+2, (short)0));// Column to merge the hr serial number 4
                 sheet.addMergedRegion(new Region(hrFrstCol, (short)5, hrLastCol, (short)5));// Column to merge the hr signature
                 
                 int self= ++hrLastCol;
                 sheet.addMergedRegion(new Region(self, (short)0, self, (short)5));// Column to merge the self
                 int empAddress = ++self;
                 sheet.addMergedRegion(new Region(empAddress, (short)0, empAddress, (short)1));// Column to merge the empAddress
                 sheet.addMergedRegion(new Region(empAddress, (short)2, empAddress, (short)3));// Column to merge the emp permanent Address
                 sheet.addMergedRegion(new Region(empAddress, (short)4, empAddress, (short)5));// Column to merge the emp permanent Address
                 int empContactEmail = ++empAddress;
                 sheet.addMergedRegion(new Region(empContactEmail, (short)0, empContactEmail, (short)1));// Column to merge the emp email
                 sheet.addMergedRegion(new Region(empContactEmail, (short)2, empContactEmail, (short)3));// Column to merge the emp contact phone
                 int note = ++empContactEmail;
                 sheet.addMergedRegion(new Region(note, (short)0, note, (short)5));// Column to merge the note
                 int empSignature = ++note;
                 sheet.addMergedRegion(new Region(empSignature, (short)0, empSignature, (short)1));// Column to merge the emp signature
                 sheet.addMergedRegion(new Region(empSignature, (short)2, empSignature, (short)3));// Column to merge the emp signature
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 10, 5, empDetails.getRmName());//RM - Signature of approving authority
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 16, 5, adActionData.getName());//Admin - Signature of approving authority
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 30, 5, nsActionData.getName());//NS - Signature of approving authority
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 37, 5, finActionData.getName());//Finance - Signature of approving authority
            
            hssfworkbook.write(bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void exportFfsToExcel(HttpServletResponse responseObj, String fileName, EmployeeDTO empDetails , 
            ApprovalDTO rmActionData,ApprovalDTO finActionData,ApprovalDTO hrActionData) throws Exception {
        System.out.println("In exportToExcel Function");
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/ms-excel");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + empDetails.getEmployeeNumber() + ".xls\"");
            fis = (CommonFunctions.class.getResourceAsStream("/Exit_FFS.xls"));
            System.out.println("fis = " + fis);

            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet("FFS");

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle contentCs = hssfworkbook.createCellStyle();
            HSSFCellStyle contentCs1 = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));

            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 2, 1, CommonFunctions.getSystemDate(CommonConfigurations.PDF_DATE_SELECT)); // Date Of Input
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 1, empDetails.getEmployeeNumber()); // Emp Number
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 3, 2, finActionData.getFinOverAllComments()); // Instructions given by finance team
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 4, 1, empDetails.getEmployeeName()); // Emp Name
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 5, 1, empDetails.getDesignation()); // Designations
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 6, 1, empDetails.getEmpBaseLocation()); // Base Location
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 7, 1, dateFormat.format(empDetails.getEmpDoj())); // Date of Join
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 8, 1, dateFormat.format(empDetails.getResignedDate()));// Date of Resignation
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 9, 1, dateFormat.format(rmActionData.getLastWorkingDate()));// Date of Relieving
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 10, 1, empDetails.getExitType());// Exit type of the employee
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 12, 1, finActionData.getLastPaidSalaryRemarks());// Last Paid Salary Month
            if(empDetails.getNoticeWavierType().equals("0")){
                commonInsertCode(hssfworkbook, sheet, cs, contentCs, 14, 1, "0");// Notice period balance days
            }else{
                commonInsertCode(hssfworkbook, sheet, cs, contentCs, 14, 1, empDetails.getNoticePeriod());// Notice period balance days
            }
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, 15, 3, empDetails.getLeaveBalance());// Leave Balance
            
            hssfworkbook.write(bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

//    public static void exportToRtf(HttpServletRequest req, HttpServletResponse responseObj, String fileName, EmployeeDTO employeeDetails, List<ApprovalDTO> surveyQuestions, List<ApprovalDTO> surveyAnswers) throws Exception {
//        InputStream fis = null;
//        int count = 0;
//
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
//            responseObj.setContentType("application/rtf");
//            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + employeeDetails.getEmployeeNumber() + ".rtf\"");
//            fis = (CommonFunctions.class.getResourceAsStream("/Exit_Interview_Questionnaire.rtf"));
//            System.out.println("fis = " + fis);
//
//            Document document = new Document(PageSize.A4, 30, 30, 10, 10);
//
//            RtfWriter2 writer2 = RtfWriter2.getInstance(document, bos);
//            document.open();
//            writer2.importRtfDocument((FileInputStream) fis);
//
//            writer2.setAutogenerateTOCEntries(true);
//            Table headerTable = new Table(3);
//            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/images/logo2.jpg");
//            System.out.println("the path is http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath());
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
//            cell=new Cell(new Phrase("Exit Survey Questionnaire",new Font(Font.TIMES_ROMAN, 16, Font.BOLD)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//
//            cell.setBorder(Rectangle.NO_BORDER);
//            headerTable.addCell(cell);
//            headerTable.setBorder(Rectangle.NO_BORDER);
//            HeaderFooter header = new RtfHeaderFooter(headerTable);
//
//            RtfHeaderFooterGroup footer = new RtfHeaderFooterGroup();
//            footer.setHeaderFooter(new RtfHeaderFooter(new Phrase("D-FO-HR-009/Version 2.0/wef:Oct 1,2010")), com.lowagie.text.rtf.headerfooter.RtfHeaderFooter.DISPLAY_ALL_PAGES);
//
//            document.setHeader(header);
//            document.setFooter(footer);
//
////            document.open();
//
////            document.add(insertEmployeeDetails_name(employeeDetails));
//            document.add(insertEmployeeDetails(employeeDetails));
//
//            //        int questionNumber
//            insertParagraph(document, surveyQuestions, surveyAnswers);
//            document.close();
//            bos.flush();
//            bos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public static void exportToPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName, EmployeeDTO employeeDetails, List<ApprovalDTO> surveyQuestions, List<ApprovalDTO> surveyAnswers) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "-" + employeeDetails.getEmployeeNumber() + ".pdf\"");
            fis = (CommonFunctions.class.getResourceAsStream("/Exit_Interview_Questionnaire.pdf"));
            System.out.println("fis = " + fis);

            Document document = new Document(PageSize.A4, 30, 30, 10, 10);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();

            Table headerTable = new Table(3);
//            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/images/logo2.jpg");
//            System.out.println("the path is http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath());
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
//            cell=new Cell(new Phrase("Exit Survey Questionnaire",new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
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

            //document.add(p);
            //document.setFooter(footer);

            document.open();

            
            document.add(insertEmployeeDetails(employeeDetails));
            insertParagraph(document, surveyQuestions, surveyAnswers);
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

    public static Paragraph insertEmployeeDetails(EmployeeDTO employeeDetails) {

        Paragraph para = new Paragraph();
        Paragraph emptyLines = new Paragraph();
        para.add(new Phrase("Employee Name:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.getEmployeeName()));
//        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        para.add(new Phrase("Designation:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.getDesignation()));
//        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        para.add(new Phrase("RM Name:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((employeeDetails.getRmName()));
//        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);

        para.add(new Phrase("Last Working Date:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
        para.add((dateFormat.format(employeeDetails.getLastWorkingDate())));
        addEmptyLine(emptyLines, 1);
        para.add(emptyLines);
        return para;

    }

    public static void insertParagraph(Document document, List<ApprovalDTO> surveyQuestion, List<ApprovalDTO> surveyAnswers) throws BadElementException, DocumentException {
        int questionNumber = 0;
        Paragraph para = null;
        Paragraph emptyLines = new Paragraph();
        for (ApprovalDTO surveyQuestionData : surveyQuestion) {
            if (surveyQuestionData.getParentId().equals("0")) {
                questionNumber++;

                if (surveyQuestionData.getAnswerType().equals(CommonConfigurations.SURVEY_ANSWER_RADIO)) {
                    Table table = new Table(1);
                    para = new Paragraph();
                    para.add(questionNumber + "." + surveyQuestionData.getName());
//                    addEmptyLine(emptyLines, 1);
                    para.add(emptyLines);
                    para.add(surveyQuestionData.getQuestionDescription());
//                    para.add(new RtfTableOfContents(surveyQuestionData.getQuestionDescription()));
                   
                    for (ApprovalDTO surveryAnswerData : surveyAnswers) {
                        if (surveryAnswerData.getQuestionId().equals(surveyQuestionData.getQuestionId())) {
                            if (surveryAnswerData.getAnswerKey().equals(surveyQuestionData.getEmpAnswer())) {
                                //styling need to be added for this selected answer
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
                    
                    for (ApprovalDTO surveryAnswerData : surveyAnswers) {
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
                    para.add(questionNumber + "." + surveyQuestionData.getName());
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
                        for (ApprovalDTO surveyQuestionMultiple : surveyQuestion) {
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

    public static void commonCode(String[] arrayList,HSSFWorkbook hssfworkbook, HSSFSheet sheet, HSSFCellStyle cs, HSSFCellStyle contentCs,int rowNum){
        for (int k = 0; k < arrayList.length; k++) {
            commonInsertCode(hssfworkbook, sheet, cs, contentCs, rowNum, k, arrayList[k]);
        }
    }

    public static String getYesNoValue(String dbValue){
        String outputValue = null;
         for (int i = 0; i < CommonConfigurations.surrenderValueList.length; i++) {
                if (dbValue.equals(Integer.toString(i + 1))) {
                   outputValue = CommonConfigurations.surrenderValueList[i];
                }
         }
        return outputValue;
    }

    public static String getExitTypeValue(String dbValue){
        String outputValue = null;
         for (int i = 0; i < CommonConfigurations.exitType.length; i++) {
                if (dbValue.equals(Integer.toString(i))) {
                   outputValue = CommonConfigurations.exitType[i];
                }
         }
        return outputValue;
    }
    
    public static void commonInsertCodeForMerge(HSSFSheet sheet,int rowFrm,int rowTo,short colFrm,short colTo) {
        try {
//            HSSFRow rowhead = sheet.createRow((short) 0);
//
//            HSSFFont font = hssfworkbook.createFont();
//            font.setFontName(HSSFFont.FONT_ARIAL);
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//            cs.setFont(font);
//            contentCs.setFont(font);
//            contentCs = getCellStyle(hssfworkbook);
//
//            HSSFCell cell;
//            rowhead = sheet.createRow((short) (rowNum));
//            sheet.addMergedRegion(new Region(38, (short)0, 42, (short)0));
//            sheet.addMergedRegion(new Region(38, (short)1, 42, (short)1));
//            sheet.addMergedRegion(new Region(38, (short)5, 42, (short)5));


            sheet.addMergedRegion(new Region(rowFrm, (short)colFrm, rowTo, (short)colTo));
//            cell = rowhead.createCell((short) cellNum);
//            cell.setCellStyle(contentCs);
//            cell.setCellValue(content);

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

    public static String replaceString(String string, String[] oldValue, String[] newValue) {
        for (int i = 0; i < oldValue.length; i++) {
            System.out.println("========= the old Value is ======= " + oldValue[i]);
            System.out.println("========= the new Value is ======= " + newValue[i]);
            System.out.println("=======The replace value is ======= " + string.replace(oldValue[i], newValue[i]));
            string = string.replace(oldValue[i], newValue[i]);
        }
        return string;
    }
    
    public static void fileUpload(MultipartFile fileprop,int refId,String referenceName,String moduleName, ApprovalService approvalService) throws  IOException{
        try {
            System.out.println(fileprop.getName());
            System.out.println(fileprop.getOriginalFilename());
            if(!fileprop.getOriginalFilename().equals(""))
            {
            String contentType = fileprop.getContentType();
            String fileName =refId+"~~"+referenceName+"~~"+fileprop.getOriginalFilename();
//            byte[] fileData = fileprop.getFileData();
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
            approvalService.addFileDb(fileName,contentType,referenceName,refId,moduleName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try{
            response.setContentType(fileType);
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
    public static void fileUpdate(MultipartFile fileprop,int refId,String referenceName,String moduleName, ApprovalService approvalService) throws  IOException{
        System.out.println("in file update---------------");
        try {
            System.out.println(fileprop.getName());
            System.out.println(fileprop.getOriginalFilename());
            if(!fileprop.getOriginalFilename().equals(""))
            {
            String contentType = fileprop.getContentType();
            String fileName =refId+"~~"+referenceName+"~~"+fileprop.getOriginalFilename();
//            byte[] fileData = fileprop.getFileData();
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
            approvalService.updateFileDb(fileName,contentType,referenceName,refId,moduleName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
