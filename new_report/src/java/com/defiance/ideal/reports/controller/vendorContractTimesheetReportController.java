/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.VendorContractExportDaoImpl;
import com.defiance.ideal.reports.dto.VendorContractExportDto;
import com.defiance.ideal.reports.service.EffortsLeakageService;
import com.defiance.ideal.reports.service.EffortsLeakageServiceImpl;
import com.defiance.ideal.reports.service.VendorContractExportService;
import com.defiance.ideal.reports.service.VendorContractExportServiceImpl;
import com.defiance.ideal.reports.util.CommonFunctions;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class vendorContractTimesheetReportController extends MultiActionController{
    
    public ModelAndView vendorContractExport(HttpServletRequest request, HttpServletResponse response,VendorContractExportDto dto) throws ParseException, Exception{
        ModelAndView mv = null;
        mv = new ModelAndView("timesheetPDFExport");
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        final WebApplicationContext ctx = getWebApplicationContext();
        VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
        int month=0;
        int year=0;
        if(request.getParameter("monthId") != null && request.getParameter("yearId") != null){
            year = Integer.parseInt(request.getParameter("yearId"));
            month = Integer.parseInt(request.getParameter("monthId"));
            Calendar startfilter = Calendar.getInstance();
            startfilter.set(year,month-1,1);
            Date startDateFilter = startfilter.getTime();
            DateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
            dto.setFrom_date(sdff.format(startDateFilter));
        }else{
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            Calendar startEnd = Calendar.getInstance();
            startEnd.set(year,month,1);
            Date startDate = startEnd.getTime();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dto.setFrom_date(sdf.format(startDate));
        }
        if(month==0){
            month=1;
        }
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        dto.setStart_page(start);
        dto.setEnd_page(rows);
        String empId = request.getParameter("empId");
        String empName = request.getParameter("empName");
        String selectedVendor = dto.getVendor_id();
        dto.setEmpId(empId);
        List<VendorContractExportDto> employeeList = service.getEmployeeList(dto);
        List<VendorContractExportDto> vendorList = service.getVendorList();
        String recordCount = service.getEmployeeListCount(dto);
        
        mv.addObject("empId",empId);
        mv.addObject("empName",empName);
        mv.addObject("paging", paging(rows, recordCount, page));
        mv.addObject("url", "vendorContractExport.htm");
        mv.addObject("employeeList", employeeList);
        mv.addObject("vendorList", vendorList);
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", month);
        mv.addObject("selectedYear", year);
        mv.addObject("selectedVendor", selectedVendor);
        return mv;
    }
    
    public ModelAndView vendorContractTimesheetDetails(HttpServletRequest request, HttpServletResponse response,VendorContractExportDto dto) throws ParseException, Exception{
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
        String employee_id = request.getParameter("employee_id");
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dto.setFrom_date(sdf.format(startDate));
        dto.setTo_date(sdf.format(endDate));
        dto.setEmployee_id(employee_id);
        dto.setYear(selectedYear);
        while(selectedMonth.length()<2) selectedMonth = "0"+selectedMonth;
        dto.setMonth(selectedMonth);
        List<VendorContractExportDto> value = service.getExportEmployeeTimesheet(dto);
        List<VendorContractExportDto> employeeDetails = service.getEmployeeDetails(dto);
        if(value.size()>0){
            CommonFunctions.exportToPdf(request, response,employeeDetails.get(0).getEmployee_number(), value, employeeDetails);
        }
        mv = new ModelAndView("timesheetPDFExport");
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
    
    public ModelAndView exportAllTimesheet(HttpServletRequest request, HttpServletResponse response,VendorContractExportDto dto) throws ParseException, Exception{
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStamp=String.valueOf(timestamp.getTime());
        String filePath = "D:\\vendor_contract\\"+timeStamp;
        new File(filePath).mkdir();
        for(int i=0;i<dto.getEmployeeId().length;i++){
            String employee_id = dto.getEmployeeId()[i];
            Calendar startEnd = Calendar.getInstance();
            startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
            Date startDate = startEnd.getTime();
            startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate = startEnd.getTime();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dto.setFrom_date(sdf.format(startDate));
            dto.setTo_date(sdf.format(endDate));
            dto.setEmployee_id(employee_id);
            dto.setYear(selectedYear);
            while(selectedMonth.length()<2) selectedMonth = "0"+selectedMonth;
            dto.setMonth(selectedMonth);
            List<VendorContractExportDto> value = service.getExportEmployeeTimesheet(dto);
            List<VendorContractExportDto> employeeDetails = service.getEmployeeDetails(dto);
            if(value.size()>0){
                CommonFunctions.exportToPdfBulk(request,response,filePath,value,employeeDetails);
            }
        }
        String[] months = new DateFormatSymbols().getMonths();
        String monthName=months[Integer.parseInt(selectedMonth)-1];
        CommonFunctions.downloadZip(request,response,filePath,monthName,selectedYear);
        mv = new ModelAndView("timesheetPDFExport");
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
    public int[] paging(int rows, String recordCount, int current_page) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
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
        
    public ModelAndView searchEmployee(HttpServletRequest request, HttpServletResponse response, VendorContractExportDto filterData) {
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
            String empVal = request.getParameter("q");
            List<VendorContractExportDto> empRes = service.getSearchEmployeeList(empVal);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }
    
    public ModelAndView vendorConsolidateExport(HttpServletRequest request, HttpServletResponse response,VendorContractExportDto dto) throws ParseException, Exception{
        ModelAndView mv = null;
        mv = new ModelAndView("timesheetConsolidateExport");
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        final WebApplicationContext ctx = getWebApplicationContext();
        VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
        int month=0;
        int year=0;
        if(request.getParameter("monthId") != null && request.getParameter("yearId") != null){
            year = Integer.parseInt(request.getParameter("yearId"));
            month = Integer.parseInt(request.getParameter("monthId"));
            Calendar startfilter = Calendar.getInstance();
            startfilter.set(year,month-1,1);
            Date startDateFilter = startfilter.getTime();
            DateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
            dto.setFrom_date(sdff.format(startDateFilter));
        }else{
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            Calendar startEnd = Calendar.getInstance();
            startEnd.set(year,month,1);
            Date startDate = startEnd.getTime();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dto.setFrom_date(sdf.format(startDate));
        }
        if(month==0){
            month=1;
        }
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(year,month-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dto.setFrom_date(sdf.format(startDate));
        dto.setTo_date(sdf.format(endDate));
        
        String selectedVendor = dto.getVendor_id();
//        if(selectedVendor=="" || selectedVendor.equals("") || selectedVendor.equals(null) ){
//            dto.setVendor_id(null);
//        }
        List<VendorContractExportDto> employeeList = service.getConsolidateEmployeeList(dto);
        List<VendorContractExportDto> vendorList = service.getVendorList();

        mv.addObject("employeeList", employeeList);
        mv.addObject("vendorList", vendorList);
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", month);
        mv.addObject("selectedYear", year);
        mv.addObject("selectedVendor", selectedVendor);
        return mv;
    }
    
    public ModelAndView exportConsolidateTimesheet(HttpServletRequest request, HttpServletResponse response,VendorContractExportDto dto) throws ParseException, Exception{
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        VendorContractExportService service = (VendorContractExportServiceImpl) ctx.getBean("VendorContractExportService");
        String employee_id = request.getParameter("employee_id");
        String selectedMonth = request.getParameter("monthId");
        String selectedYear = request.getParameter("yearId");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dto.setFrom_date(sdf.format(startDate));
        dto.setTo_date(sdf.format(endDate));
        dto.setEmployee_id(employee_id);
        dto.setYear(selectedYear);
        while(selectedMonth.length()<2) selectedMonth = "0"+selectedMonth;
        dto.setMonth(selectedMonth);
        String selectedVendor = dto.getVendor_id();
        if((selectedVendor=="" || selectedVendor.equals(""))&&selectedVendor.length()==0){
            System.out.println("null");
            dto.setVendor_id(null);
        }
        List<VendorContractExportDto> employeeDetails = service.getConsolidateEmployeeList(dto);
//        if(employeeDetails.size()>0){
//            CommonFunctions.exportConsolidateToPdf(request, response,selectedVendor,employeeDetails);
//        }
        ArrayList entireList = new ArrayList();
        for(int i=0;i<employeeDetails.size();i++){
            ArrayList rowData = new ArrayList();
            rowData.add(new String(""+employeeDetails.get(i).getEmployee_number()));
            rowData.add(new String(""+employeeDetails.get(i).getEmployee_name()));
            rowData.add(new String(""+employeeDetails.get(i).getVendor_code()));
            rowData.add(new String(""+employeeDetails.get(i).getVendor_name()));
            rowData.add(new String(""+employeeDetails.get(i).getMonth()));
            rowData.add(new String(""+employeeDetails.get(i).getLegal_entity()));
            rowData.add(new String(""+employeeDetails.get(i).getRate()));
            rowData.add(new String(""+employeeDetails.get(i).getCurrency_code()));
            rowData.add(new String(""+employeeDetails.get(i).getUom_name()));
            rowData.add(new String(""+employeeDetails.get(i).getWorking_days()));
            rowData.add(new String(""+employeeDetails.get(i).getWorked_days()));
            rowData.add(new String(""+employeeDetails.get(i).getLeave_days()));
            rowData.add(new String(""+employeeDetails.get(i).getProject_code()));
            rowData.add(new String(""+employeeDetails.get(i).getLocation()));
            rowData.add(new String(""+employeeDetails.get(i).getReporting_manager()));
            entireList.add(rowData);
        }
        CommonMethods.exportToExcel(response, entireList, "Vendor_Consolidate_Report.xls", "Vendor", null);
        mv = new ModelAndView("timesheetConsolidateExport");
        Map<String,String> monthList = CommonMethods.getNewMonthsList();
        Map<Integer,Integer> yearList = CommonMethods.getYearsList(1);
        mv.addObject("monthList", monthList);
        mv.addObject("yearList", yearList);
        mv.addObject("selectedMonth", selectedMonth);
        mv.addObject("selectedYear", selectedYear);
        return mv;
    }
}
