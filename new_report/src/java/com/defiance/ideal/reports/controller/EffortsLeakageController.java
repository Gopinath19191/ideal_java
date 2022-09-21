/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.EffortsLeakageDataDTO;
import com.defiance.ideal.reports.dto.SearchDTO;
import com.defiance.ideal.reports.service.EffortsLeakageService;
import com.defiance.ideal.reports.service.EffortsLeakageServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import com.lowagie.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class EffortsLeakageController extends MultiActionController{
    
    public ModelAndView effortsLeakage(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception{
        ModelAndView mv = null;
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        mv = new ModelAndView("effortsLeakageReport");
        mv.addObject("monthList", monthList);
        mv.addObject("reportType", "unbilled");
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", month);
        mv.addObject("selectedYear", year);
        return mv;
    }
    
    public ModelAndView effortsLeakageSearch(HttpServletRequest request, HttpServletResponse response, EffortsLeakageDataDTO filterData) throws ParseException, Exception{
        ModelAndView mv = null;
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        filterData.setStart_date(sdf.format(startDate));
        filterData.setEnd_date(sdf.format(endDate));
        final WebApplicationContext ctx = getWebApplicationContext();
        EffortsLeakageService service = (EffortsLeakageServiceImpl) ctx.getBean("EffortsLeakageService"); 
        List<EffortsLeakageDataDTO> reportData = service.getData(filterData);
        String excelFileName = "D:/delinquency/Revenue_leakage.xls";//name of excel file
        String sheetName = "Revenue_leakage";//name of sheet
        File file = new File("D://delinquency//Revenue_leakage.xls");
        file.delete();
        file.createNewFile();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName) ;
        String headerArray[]=new String[]{"Employee Id","Employee Name","Reporting Manager","Unit","Sub Unit","Project Id","Project Name",
                                            "UOM","Business Model","Pricing Model","Role","Billing Rate","Currency","Project Manager","Customer Name","Billable",
                                            "Location Name","Timesheet Date","Working Day","Available Hours","Worked Hours","Approved Hours",
                                            "Accrued Hours","Invoiced Hours","Accrued Date","Accrual Number","Available Days"};
        for (int i = 0; i < reportData.size(); i++) {
            String rowArray[]=new String[27];
            rowArray[0]=reportData.get(i).getEmployee_id();
            rowArray[1]=reportData.get(i).getEmployee_name();
            rowArray[2]=reportData.get(i).getReporting_manager_name();
            rowArray[3]=reportData.get(i).getUnit_name();
            rowArray[4]=reportData.get(i).getSub_unit_name();
            rowArray[5]=reportData.get(i).getProject_id();
            rowArray[6]=reportData.get(i).getProject_name();
            rowArray[7]=reportData.get(i).getUom();
            rowArray[8]=reportData.get(i).getBusiness_model();
            rowArray[9]=reportData.get(i).getPricing_model();
            rowArray[10]=reportData.get(i).getRole_name();
            rowArray[11]=reportData.get(i).getBilling_rate();
            rowArray[12]=reportData.get(i).getCurrency();
            rowArray[13]=reportData.get(i).getProject_manager_name();
            rowArray[14]=reportData.get(i).getCustomer_name();
            rowArray[15]=reportData.get(i).getBillable();
            rowArray[16]=reportData.get(i).getLocation_name();
            rowArray[17]=reportData.get(i).getTimesheet_date();
            rowArray[18]=reportData.get(i).getWorking_days();
            rowArray[19]=reportData.get(i).getAvailable_hours();
            rowArray[20]=reportData.get(i).getWorked_hours();
            rowArray[21]=reportData.get(i).getApproved_hours();
            rowArray[22]=reportData.get(i).getAccrued_hours();
            rowArray[23]=reportData.get(i).getInvoiced_hours();
            rowArray[24]=reportData.get(i).getAccrued_date();
            rowArray[25]=reportData.get(i).getAccrual_number();
            rowArray[26]=reportData.get(i).getNo_working_days();
            if(i==0){
                HSSFRow row = sheet.createRow(i);
                for (short c=0;c < rowArray.length; c++ ){
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(headerArray[c]);
                }
            }
            HSSFRow row = sheet.createRow(i+1);
            for (short c=0;c < rowArray.length; c++ ){
                HSSFCell cell = row.createCell(c);
                cell.setCellValue(rowArray[c]);
            }
        }
        
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv = new ModelAndView("effortsLeakageReport");
        mv.addObject("reportType", "unbilled");
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
    
    public ModelAndView projectUnbilledReport(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception{
        ModelAndView mv = null;
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        mv = new ModelAndView("effortsLeakageReport");
        mv.addObject("reportType", "projectUnbilled");
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", month);
        mv.addObject("selectedYear", year);
        return mv;
    }
    
    public ModelAndView projectLeakageSearch(HttpServletRequest request, HttpServletResponse response, EffortsLeakageDataDTO filterData) throws ParseException, Exception{
        ModelAndView mv = null;
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        filterData.setStart_date(sdf.format(startDate));
        filterData.setEnd_date(sdf.format(endDate));
        final WebApplicationContext ctx = getWebApplicationContext();
        EffortsLeakageService service = (EffortsLeakageServiceImpl) ctx.getBean("EffortsLeakageService"); 
        List<EffortsLeakageDataDTO> reportData = service.getProjectUnbilledDetails(filterData);
        String excelFileName = "D:/delinquency/Project_effort_utilization.xls";//name of excel file
        String sheetName = "project_utilization";//name of sheet
        File file = new File("D://delinquency//Project_effort_utilization.xls");
        file.delete();
        file.createNewFile();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName) ;
        String headerArray[]=new String[]{"Project Id","Project Name","Employee Id","Employee Name","Reporting Manager","Project Manager",
                                            "Customer Name","Employee Efforts","Start Date","End Date","Employment Status","Unit","Sub Unit",
                                            "UOM","Pricing Model","Business Model","Role","Billing Rate","Currency","Billable","Timesheet Date","Available Hours",
                                            "Attendance Hours","Worked Hours","Approved Hours","Accrued Hours","Invoiced Hours","Working Days","Leave Taken"};
        for (int i = 0; i < reportData.size(); i++) {
            String rowArray[]=new String[29];
            rowArray[0]=reportData.get(i).getProject_id();
            rowArray[1]=reportData.get(i).getProject_name();
            rowArray[2]=reportData.get(i).getEmployee_id();
            rowArray[3]=reportData.get(i).getEmployee_name();
            rowArray[4]=reportData.get(i).getReporting_manager_name();
            rowArray[5]=reportData.get(i).getProject_manager_name();
            rowArray[6]=reportData.get(i).getCustomer_name();
            rowArray[7]=reportData.get(i).getEmployee_efforts();
            rowArray[8]=reportData.get(i).getPta_start_date();
            rowArray[9]=reportData.get(i).getPta_end_date();
            rowArray[10]=reportData.get(i).getEmployment_status();
            rowArray[11]=reportData.get(i).getUnit_name();
            rowArray[12]=reportData.get(i).getSub_unit_name();
            rowArray[13]=reportData.get(i).getUom();
            rowArray[14]=reportData.get(i).getPricing_model();
            rowArray[15]=reportData.get(i).getBusiness_model();
            rowArray[16]=reportData.get(i).getRole_name();
            rowArray[17]=reportData.get(i).getBilling_rate();
            rowArray[18]=reportData.get(i).getCurrency();
            rowArray[19]=reportData.get(i).getBillable();
            rowArray[20]=reportData.get(i).getTimesheet_date();
            rowArray[21]=reportData.get(i).getAvailable_hours();
            rowArray[22]=reportData.get(i).getAttendance_hours();
            rowArray[23]=reportData.get(i).getWorked_hours();
            rowArray[24]=reportData.get(i).getApproved_hours();
            rowArray[25]=reportData.get(i).getAccrued_hours();
            rowArray[26]=reportData.get(i).getInvoiced_hours();
            rowArray[27]=reportData.get(i).getWorking_days();
            rowArray[28]=reportData.get(i).getLeave_taken();
            if(i==0){
                HSSFRow row = sheet.createRow(i);
                for (short c=0; c < rowArray.length; c++){
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(headerArray[c]);
                }
            }
            HSSFRow row = sheet.createRow(i+1);
            for (short c=0; c < rowArray.length; c++){
                HSSFCell cell = row.createCell(c);
                cell.setCellValue(rowArray[c]);
            }
        }
        
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv = new ModelAndView("effortsLeakageReport");
        mv.addObject("reportType", "projectUnbilled");
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
    
    public  ModelAndView projectLeakageDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
       
        System.out.println("inside userManual");
        String filepath = "D:/delinquency/"; 
        String filename = "Project_effort_utilization.xls";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                myOut = response.getOutputStream(); 
                File myfile = new File(filepath + filename);
                response.setContentType("application/ms-excel");        

                response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
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
        return null;
        
    }
    
    
    public ModelAndView effortsLeakageDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
       
        System.out.println("inside userManual");
        String filepath = "D:/delinquency/"; 
        String filename = "Revenue_leakage.xls";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                myOut = response.getOutputStream(); 
                File myfile = new File(filepath + filename);
                response.setContentType("application/ms-excel");        
                response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
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
        return null;
    }
    
    public ModelAndView effortsLeakageMonthly(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception{
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String usertype = request.getParameter("pm");
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee id   "+employee_id);
        mv = new ModelAndView("revenueLeakageReportMonthly");
        final WebApplicationContext ctx = getWebApplicationContext();
        EffortsLeakageService service = (EffortsLeakageServiceImpl) ctx.getBean("EffortsLeakageService"); 
        if(usertype.equals("1")){
            System.out.println("hereeeeeeeeeeeeee");
        }else{
            List<SearchDTO> CustomerList = service.getCustomerList();
            String parentId = service.getParent_id();
            List<SearchDTO> unitList = service.getUnit();
            List<SearchDTO> subUnitList = service.getSubUnit(parentId);
            mv.addObject("CustomerList",CustomerList);
            mv.addObject("unitList",unitList);
            mv.addObject("subUnitList",subUnitList);
        }
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", month);
        mv.addObject("selectedYear", year);
        mv.addObject("userType", usertype);
        return mv;
    }
    
    public ModelAndView effortsLeakageMonthlySearch(HttpServletRequest request, HttpServletResponse response, SearchDTO filterData) throws ParseException, Exception{
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        String selectedCustomer = request.getParameter("customerId");
        String selectedUnit = request.getParameter("unitId");
        String selectedSubUnit = request.getParameter("subUnitId");
        String userType = request.getParameter("userType");
        String employee_id = (String) session.getAttribute("EMP_ID");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        filterData.setStart_date(sdf.format(startDate));
        filterData.setEnd_date(sdf.format(endDate));
        filterData.setCustomer_id(selectedCustomer);
        filterData.setUnit_id(selectedUnit);
        filterData.setSub_unit_id(selectedSubUnit);
        if(userType.equals("1")){
            filterData.setProject_manager_id(employee_id);
            filterData.setCustomer_id("");
            filterData.setUnit_id("");
            filterData.setSub_unit_id("");
        } 
        final WebApplicationContext ctx = getWebApplicationContext();
        EffortsLeakageService service = (EffortsLeakageServiceImpl) ctx.getBean("EffortsLeakageService"); 
        List<EffortsLeakageDataDTO> reportData = service.getRevenueLeakageReport(filterData);
        String excelFileName = "D:/delinquency/Revenue_leakage_monthly.xls";//name of excel file
        String sheetName = "Revenue_leakage";//name of sheet
        File file = new File("D://delinquency//Revenue_leakage_monthly.xls");
        file.delete();
        file.createNewFile();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName) ;
        String headerArray[]=new String[]{"Employee Id","Employee Name","Band","Designation","Billable","Joined Date", "Resigned Date",
                                            "Released Date","Unit","Practice","Sub Practice","Reporting Manager","Project Id","Project Name",
                                            "Project Manager","UOM","Legal Entity","Pricing Model","Business Model","Role","Role Type",
                                            "Billing Rate","Currency","Project Allocation Start Date","Project Allocation End Date","Customer Id",
                                            "Customer Name","Available Hours","Location Name","Calendar Name","Offshore/Onshore","Calendar Days",
                                            "Weekends","Holidays","Working Days","Leave Taken","Employee Available Days","Employee Month Available Hours",
                                            "Non Project Hours","Non Project Approved Hours","Project Hours","Project Approved Hours","Accrued Hours","Invoiced Hours"};
        for (int i = 0; i < reportData.size(); i++) {
            String rowArray[]=new String[44];
            rowArray[0]=reportData.get(i).getEmployee_id();
            rowArray[1]=reportData.get(i).getEmployee_name();
            rowArray[2]=reportData.get(i).getBand();
            rowArray[3]=reportData.get(i).getDesignation();
            rowArray[4]=reportData.get(i).getBillable();
            rowArray[5]=reportData.get(i).getJoined_date();
            rowArray[6]=reportData.get(i).getResigned_date();
            rowArray[7]=reportData.get(i).getReleased_date();
            rowArray[8]=reportData.get(i).getUnit_name();
            rowArray[9]=reportData.get(i).getSub_unit_name();
            rowArray[10]=reportData.get(i).getSub_practice();
            rowArray[11]=reportData.get(i).getReporting_manager_name();
            rowArray[12]=reportData.get(i).getProject_id();
            rowArray[13]=reportData.get(i).getProject_name();
            rowArray[14]=reportData.get(i).getProject_manager_name();
            rowArray[15]=reportData.get(i).getUom();
            rowArray[16]=reportData.get(i).getLegal_entity();
            rowArray[17]=reportData.get(i).getPricing_model();
            rowArray[18]=reportData.get(i).getBusiness_model();
            rowArray[19]=reportData.get(i).getRole_name();
            rowArray[20]=reportData.get(i).getRole_type();
            rowArray[21]=reportData.get(i).getBilling_rate();
            rowArray[22]=reportData.get(i).getCurrency();
            rowArray[23]=reportData.get(i).getPta_start_date();
            rowArray[24]=reportData.get(i).getPta_end_date();
            rowArray[25]=reportData.get(i).getCustomer_code();
            rowArray[26]=reportData.get(i).getCustomer_name();
            rowArray[27]=reportData.get(i).getAvailable_hours();
            rowArray[28]=reportData.get(i).getLocation_name();
            rowArray[29]=reportData.get(i).getCalendar_name();
            rowArray[30]=reportData.get(i).getOffshore_onshore();
            rowArray[31]=reportData.get(i).getCalendar_days();
            rowArray[32]=reportData.get(i).getNo_weekends();
            rowArray[33]=reportData.get(i).getNo_holidays();
            rowArray[34]=reportData.get(i).getWorking_days();
            rowArray[35]=reportData.get(i).getLeave_taken();
            rowArray[36]=reportData.get(i).getNo_working_days();
            rowArray[37]=reportData.get(i).getEmployee_available_hours();
            rowArray[38]=reportData.get(i).getNon_project_timesheet();
            rowArray[39]=reportData.get(i).getNon_project_timesheet_approved();
            rowArray[40]=reportData.get(i).getWorked_hours();
            rowArray[41]=reportData.get(i).getApproved_hours();
            rowArray[42]=reportData.get(i).getAccrued_hours();
            rowArray[43]=reportData.get(i).getInvoiced_hours();
            if(i==0){
                HSSFRow row = sheet.createRow(i);
                for (short c=0;c < rowArray.length; c++ ){
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(headerArray[c]);
                }
            }
            HSSFRow row = sheet.createRow(i+1);
            for (short c=0;c < rowArray.length; c++ ){
                HSSFCell cell = row.createCell(c);
                cell.setCellValue(rowArray[c]);
            }
        }
        
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv = new ModelAndView("revenueLeakageReportMonthly");
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
    public ModelAndView effortsLeakageMonthlyDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
       
        System.out.println("inside userManual");
        String filepath = "D:/delinquency/"; 
        String filename = "Revenue_leakage_monthly.xls";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                myOut = response.getOutputStream(); 
                File myfile = new File(filepath + filename);
                response.setContentType("application/ms-excel");        
                response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
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
        return null;
    }
    public ModelAndView getSUbUnit(HttpServletRequest request, HttpServletResponse response, SearchDTO objSearch) throws ParseException, Exception{
        ModelAndView mv = null;
        
        mv = new ModelAndView("revenueLeakageReportMonthly");
        final WebApplicationContext ctx = getWebApplicationContext();
        EffortsLeakageService service = (EffortsLeakageServiceImpl) ctx.getBean("EffortsLeakageService"); 
        response.getWriter().println("<option value=''>" + "--Practice--" + "</option>");
        String parent_id = request.getParameter("parent_id");
        for (SearchDTO dTO : (service.getSubUnit(parent_id))) {
            response.getWriter().println("<option value='" + dTO.getSub_unit_id() + "'>" + dTO.getSub_unit_name() + "</option>");
        }        
        return null;
    }
}
