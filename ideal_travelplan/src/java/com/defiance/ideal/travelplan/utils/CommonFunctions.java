/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.utils;

import com.defiance.ideal.travelplan.dto.CommonDto;
import com.lowagie.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14583
 */
public class CommonFunctions extends MultiActionController {
    
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
    
    public static int checkEmpDbAccess(String empStatus,String[] statusArray){
        int accessCnt =0;
        if(empStatus!=null){
            for (int i = 0; i < statusArray.length; i++) {
                if (empStatus.equals(statusArray[i])) {
                    accessCnt++;
                }
            }
        }
        return accessCnt;
    }
    
    public static String getCurrentEmpStatus(String empStatus,String transferredCntyId){
        String currEmpStatus = null;
        String[] indianArray = {"d", "v", "p", "e"};
        //String[] germArray = {"g", "c", "e"};
        try {
            //if(empStatus!=null && (empStatus.equals("d") || empStatus.equals("v") || empStatus.equals("p") || (empStatus.equals("e") && !transferredCntyId.equals(CommonConfigurations.germanyCntryId)))){
            if(empStatus!=null && (empStatus.equals("d") || empStatus.equals("v") || empStatus.equals("p") || (empStatus.equals("e")))){
            currEmpStatus = CommonConfigurations.indiaEmpStatus;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(":::"+currEmpStatus);
        return currEmpStatus;
    }
    
    public static CommonDto commonManipulationCode(CommonDto empDetails,String moduleId,String employeeId,String actionName){
        if(employeeId!=null & employeeId.equals(CommonConfigurations.vetriEmpId)){
            empDetails.setVetri("Y");
        } else {
            empDetails.setVetri("N");
        }
        if(moduleId!=null && moduleId.equals(CommonConfigurations.internationalModId)) {
            empDetails.setTravelType(CommonConfigurations.internationalCode);
            empDetails.setDeviation("N");
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.domesticModId)) {
            empDetails.setTravelType(CommonConfigurations.domesticCode);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.localConvModId)) {
            empDetails.setTravelType(CommonConfigurations.localConvCode);
            empDetails.setDeviation("N");
            empDetails.setBillable("N");
            empDetails.setVetri("N");
            empDetails.setSales("N");
        }
        empDetails.setActionName(actionName);
        return empDetails;
    }

   // public static String getRequestId(String india_last_id,String german_last_id,String type,Date req_date){
       public static String getRequestId(String india_last_id,String type,Date req_date){

        String val = null;
        String ref_no = null;
        String data = "";
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(req_date);
        String req_year = String.valueOf(calendar.get(Calendar.YEAR)).substring(2);
        
        if(!india_last_id.equals(""))
            ref_no = india_last_id;
       
        int result = Integer.parseInt(ref_no)+1;
        String out = String.valueOf(result);
        int len = out.length();
        if(len < 5) {
            for(int k=1;k<= 5-len;k++) {
                data += "0";
            }
        }
        data += out;
        val = type+req_year+data;
        return val;
    }
    
    public static CommonDto commonCodeForApproval(String moduleId,String employeeId,CommonDto interFormData)throws Exception {
        CommonDto commonFormData = new CommonDto();
        commonFormData.setTpId(interFormData.getTpId());
        if(moduleId!=null && moduleId.equals(CommonConfigurations.rmModId)) {
            commonFormData.setActionName(CommonConfigurations.rmActionCode);
            commonFormData.setColumnName(CommonConfigurations.rmColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.buhModId)) {
            commonFormData.setActionName(CommonConfigurations.buhActionCode);
            commonFormData.setColumnName(CommonConfigurations.buhColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.mdModId)) {
            commonFormData.setActionName(CommonConfigurations.mdActionCode);
            commonFormData.setColumnName(CommonConfigurations.mdColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.finModId)) {
            commonFormData.setActionName(CommonConfigurations.financeActionCode);
            commonFormData.setColumnName(CommonConfigurations.financeColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.cfoModId)) {
            commonFormData.setActionName(CommonConfigurations.financeActionCode);
            commonFormData.setColumnName(CommonConfigurations.cfoColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.adminModId)) {
            commonFormData.setActionName(CommonConfigurations.adminActionCode);
            commonFormData.setColumnName(CommonConfigurations.adminColumnName);
        } else if(moduleId!=null && moduleId.equals(CommonConfigurations.treausryModId)) {
            commonFormData.setActionName(CommonConfigurations.adminActionCode);
            commonFormData.setColumnName(CommonConfigurations.treasuryColumnName);
        }
        commonFormData.setRemarks(interFormData.getRemarks());
        commonFormData.setApprovedBy(employeeId);
        commonFormData.setActionDate(new Date());
        commonFormData.setTpId(interFormData.getTpId());
        commonFormData.setApproved_status("a");
        
        return commonFormData;
    }

    public static void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try {
            System.out.println("FileTyep:::" + fileType + "Name:::" + fileName + "Path::::" + filePath);
            response.setContentType(fileType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

            File file = new File(filePath + "\\" + fileName);
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
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRole(String role_id)throws Exception {
        String role = null;
        if(role_id != null && role_id.equals(CommonConfigurations.rmActionCode)) {
            role = "Reporting/Project Manager";
        } else if(role_id != null && role_id.equals(CommonConfigurations.buhActionCode)) {
            role = "BUH";
        } else if(role_id != null && role_id.equals(CommonConfigurations.mdActionCode)) {
            role = "MD";
        } else if(role_id != null && role_id.equals(CommonConfigurations.financeActionCode)) {
            role = "Business Operations";
        } else if(role_id != null && role_id.equals(CommonConfigurations.cfoActionCode)) {
            role = "CFO";
        } else if(role_id != null && role_id.equals(CommonConfigurations.adminActionCode)) {
            role = "Admin";
        } else if(role_id != null && role_id.equals(CommonConfigurations.treasuryActionCode)) {
            role = "Treasury";
        }
        return role;
    }

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
                        if (dataObj != null && !"".equals(dataObj)) {
                            cell.setCellValue(dataObj.toString());
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
