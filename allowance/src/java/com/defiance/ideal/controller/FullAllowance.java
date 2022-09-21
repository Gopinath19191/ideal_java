/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dto.FullAllowanceDto;
import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.ShiftAndTransportDto;
import com.defiance.ideal.service.FullAllowanceService;
import com.defiance.ideal.service.FullAllowanceServiceImpl;
import com.defiance.ideal.util.CommonFunctions;
import com.lowagie.text.Phrase;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
/**
 *
 * @author 16221
 */
public class FullAllowance  extends MultiActionController{
    private ModelAndView mv;
    
    public ModelAndView applyFullAllowance(HttpServletRequest request, HttpServletResponse response, ShiftAndTransportDto filterData) throws Exception {
       HttpSession session = request.getSession();
       String created_by=(String)session.getAttribute("accessId");
       String designation=(String)session.getAttribute("designation");
       if(created_by == null || designation == null){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        
        Map<Integer, Integer> yearsList;
        Date date= new Date();
        DateFormat dat = new SimpleDateFormat("dd");
        String da=dat.format(date);
        int d=Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        if(mn==1){
            if(d>cut_off_date){
                yearsList = CommonFunctions.getYearsList(0);
            } else {
              yearsList = CommonFunctions.getYearsList(1);
            }
        } else {
            yearsList = CommonFunctions.getYearsList(0);
        }
       
        mv = new ModelAndView("/com/defiance/fullAllowance");
        mv.addObject("yearsList", yearsList);
        return mv;
    }
    public ModelAndView getMonthList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> monthsList;
        Date date = new Date();
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        
        if (mn == 1) {
            if (d >cut_off_date) {
                monthsList = CommonFunctions.getMonthsList();
            } else {
                monthsList = CommonFunctions.getMonthsList();
            }
        } else {
            if (d >cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
        }

        response.getWriter().println("<option value=''>" + "--Month--" + "</option>");
        for (Map.Entry<String, String> entry : monthsList.entrySet()) {
            response.getWriter().println("<option value='" + entry.getKey() + "'>" + entry.getValue() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/fullAllowance");
        return null;
    }

    public ModelAndView getCustomerList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        
        int year = Integer.parseInt(objSearch.getYear());
        int month = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year,month-1,1);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year)+"-"+Integer.toString(month)+"-01";
        String end_date = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        response.getWriter().println("<option value=''>" + "--Customer--" + "</option>");
        for (FullAllowanceDto dTO : (service.getCustomerList(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getCustomer_id() + "'>" + dTO.getCustomer_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/fullAllowance");
        return null;
    }

    public ModelAndView getProjectList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        
        int year = Integer.parseInt(objSearch.getYear());
        int month = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year,month-1,1);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year)+"-"+Integer.toString(month)+"-01";
        String end_date = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        objSearch.setCus_id(request.getParameter("cus_id"));
        response.getWriter().println("<option value=''>" + "--Project--" + "</option>");
        for (FullAllowanceDto dTO : (service.getProjectList(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getProject_id() + "'>" + dTO.getProject_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/fullAllowance");
        return null;
    }
            
    public ModelAndView getSubSbu(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {

        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        response.getWriter().println("<option value=''>" + "--Sub SBU--" + "</option>");
        for (FullAllowanceDto dTO : (service.getSubSbu(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getSub_sbu_id() + "'>" + dTO.getSub_sbu_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/fullAllowance");
        return null;
    }
    
    public ModelAndView getEligibleEmployeeList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception{
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        Date date = new Date();
        DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        String customerID = request.getParameter("customer");
        String project = request.getParameter("project");
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        objSearch.setCus_id(customerID);
        objSearch.setProject_id(project);
        objSearch.setMonth(month);
        objSearch.setYear(year);
        
        int year_int = Integer.parseInt(objSearch.getYear());
        int month_int = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year_int,month_int-1,1);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-01";
        String end_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        objSearch.setPrjt_id(Integer.parseInt(project));
        List<FullAllowanceDto> details = service.getEmployeeDetails(objSearch);
        List<FullAllowanceDto> shift = service.getShiftDetails();
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        String editable = "0";
        if (mn == 1) {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
                if(month_int==1){
                    editable = "1";
                }else{
                    editable = "0";
                }
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(0);
                if((month_int==12)){
                    editable = "1";
                }else{
                    editable = "0";
                }
            }
        } else {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
                if((month_int == mn)){
                    editable = "1";
                }else{
                    editable = "0";
                }
            } else {
                monthsList = CommonFunctions.getLastMonth();
                if((month_int==(mn-1))){
                    editable = "1";
                }else{
                    editable = "0";
                }
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        List<FullAllowanceDto> customer = service.getCustomerList(objSearch);
        List<FullAllowanceDto> projectList = service.getProjectList(objSearch);
        mv = new ModelAndView("/com/defiance/fullAllowance");
        mv.addObject("filterData", objSearch);
        mv.addObject("customer", customer);
        mv.addObject("project", projectList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("shift", shift);
        mv.addObject("monthsList", monthsList);
        mv.addObject("details", details);
        mv.addObject("editable", editable);
        return mv;
    }
    
    public ModelAndView submitEmployeeAllowance(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {

        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String da1 = dfm1.format(date);
        long s = CommonFunctions.timeConversion(da1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<FullAllowanceDto> allowanceDetails = Arrays.asList(
                mapper.readValue(selectedRows.toString(), FullAllowanceDto[].class));
        String project = request.getParameter("project");
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        String cus_id = request.getParameter("customer");
        objSearch.setProject_id(project);
        objSearch.setMonth(month);
        objSearch.setYear(year);
        objSearch.setCus_id(cus_id);
        int year_int = Integer.parseInt(objSearch.getYear());
        int month_int = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year_int,month_int-1,1);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-01";
        String end_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        objSearch.setPrjt_id(Integer.parseInt(project));
        
        if (allowanceDetails != null && allowanceDetails.size() > 0) {
            for (int iTSEntry = 0; iTSEntry < allowanceDetails.size(); iTSEntry++) {
                FullAllowanceDto allowanceList = allowanceDetails.get(iTSEntry);
                allowanceList.setStart_date(start_date);
                allowanceList.setEnd_date(end_date);
                allowanceList.setCreated_by(created_by);
                allowanceList.setCreated_date(s);
                allowanceList.setModified_date(s);
                allowanceList.setStatus("1");
                service.insertAllowanceDetails(allowanceList);
            }
        }
        List<FullAllowanceDto> details = service.getEmployeeDetails(objSearch);
        List<FullAllowanceDto> shift = service.getShiftDetails();
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        String editable = "0";
        if (mn == 1) {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
                if(month_int==1){
                    editable = "1";
                }else{
                    editable = "0";
                }
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(0);
                if((month_int==12)){
                    editable = "1";
                }else{
                    editable = "0";
                }
            }
        } else {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
                if((month_int==mn)){
                    editable = "1";
                }else{
                    editable = "0";
                }
            } else {
                monthsList = CommonFunctions.getLastMonth();
                if((month_int==(mn-1))){
                    editable = "1";
                }else{
                    editable = "0";
                }
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        List<FullAllowanceDto> customer = service.getCustomerList(objSearch);
        List<FullAllowanceDto> projectList = service.getProjectList(objSearch);
        
        mv = new ModelAndView("/com/defiance/fullAllowance");
        mv.addObject("filterData", objSearch);
        mv.addObject("customer", customer);
        mv.addObject("project", projectList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("shift", shift);
        mv.addObject("monthsList", monthsList);
        mv.addObject("editable", editable);
        mv.addObject("details", details);
        mv.addObject("submit_status","Data Submitted Successfully");
        return mv;
    }
    
    public ModelAndView getProcessedList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception{
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        Date date = new Date();
        DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        if(designation.equals("13")){
            if(request.getParameter("sbu_id").equals("")){
                objSearch.setSBU_Id(null);
            }else{
                objSearch.setSBU_Id(request.getParameter("sbu_id"));
            }
            if(request.getParameter("sub_sbu_id").equals("")){
                objSearch.setSBU_SUB_Id(null);
            }else{
                objSearch.setSBU_SUB_Id(request.getParameter("sub_sbu_id"));
            }
            objSearch.setProject_id(null);
        }else{
            String customerID = request.getParameter("customer");
            String project = request.getParameter("project");
            objSearch.setProject_id(project);
            objSearch.setCus_id(customerID);
            objSearch.setPrjt_id(Integer.parseInt(project));
            objSearch.setSBU_Id(null);
            objSearch.setSBU_SUB_Id(null);
        }
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        objSearch.setMonth(month);
        objSearch.setYear(year);
        int year_int = Integer.parseInt(objSearch.getYear());
        int month_int = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year_int,month_int-1,1);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-01";
        String end_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        List<FullAllowanceDto> details = service.getProcessedList(objSearch);
        
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        if (mn == 1) {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(0);
            }
        } else {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        List<FullAllowanceDto> customer = service.getCustomerList(objSearch);
        List<FullAllowanceDto> projectList = service.getProjectList(objSearch);
        
        mv = new ModelAndView("/com/defiance/fullAllowanceReport");
        if(designation.equals("13")){
            List<FullAllowanceDto> sbuList = service.getSbuList();
            List<FullAllowanceDto> subSbuList = service.getSubSbu(objSearch);
            mv.addObject("sbu_list", sbuList);
            mv.addObject("sub_sbu_list", subSbuList);
            mv.addObject("hr_login", "1");
        }else{
            mv.addObject("hr_login", "0");
        }
        mv.addObject("filterData", objSearch);
        mv.addObject("customer", customer);
        mv.addObject("project", projectList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("monthsList", monthsList);
        mv.addObject("details", details);
        return mv;
    }
    
    public ModelAndView processedAllowance(HttpServletRequest request, HttpServletResponse response, ShiftAndTransportDto filterData) throws Exception{
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        String designation=(String)session.getAttribute("designation");
        if(created_by == null || designation == null){
             mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        
        Map<Integer, Integer> yearsList;
        Date date= new Date();
        DateFormat dat = new SimpleDateFormat("dd");
        String da=dat.format(date);
        int d=Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);

        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        if(mn==1){
            if(d>cut_off_date){
                yearsList = CommonFunctions.getYearsList(1);
            } else {
              yearsList = CommonFunctions.getYearsList(1);
            }
        } else {
            yearsList = CommonFunctions.getYearsList(0);
        }
        mv = new ModelAndView("/com/defiance/fullAllowanceReport");
        if(designation.equals("13")){
            List<FullAllowanceDto> sbuList = service.getSbuList();
            mv.addObject("sbu_list", sbuList);
            mv.addObject("hr_login", "1");
        }else{
            mv.addObject("hr_login", "0");
        }
        mv.addObject("yearsList", yearsList);
        return mv;
    }
    
    public ModelAndView employeeViewAllowance(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception{
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("accessId");
        System.out.println("employee_id "+employee_id);
        System.out.println("year  "+request.getParameter("selYear"));
        Date date = new Date();
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        
        String year = request.getParameter("selYear");
        objSearch.setYear(year);
        objSearch.setEmployee_id(employee_id);
        List<FullAllowanceDto> details = service.getEmployeeAllowanceReport(objSearch);
        
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        String cutOffDate = service.getCutOffDate();
        int cut_off_date = Integer.parseInt(cutOffDate);
        if (mn == 1) {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(1);
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(1);
            }
        } else {
            if (d >= cut_off_date) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        
        mv = new ModelAndView("/com/defiance/apply");
        
        mv.addObject("filterData", objSearch);
        mv.addObject("yearsList", yearsList);
        mv.addObject("monthsList", monthsList);
        mv.addObject("details", details);
        return mv;
    }
    
    public ModelAndView getExcelReport(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception{
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        String processed = request.getParameter("processed");
        System.out.println("processed "+processed);
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        Date date = new Date();
        DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        final WebApplicationContext ctx = getWebApplicationContext();
        FullAllowanceService service = (FullAllowanceServiceImpl) ctx.getBean("FullAllowanceService");
        
        if(designation.equals("13")){
            if(request.getParameter("sbu_id").equals("")){
                objSearch.setSBU_Id(null);
            }else{
                objSearch.setSBU_Id(request.getParameter("sbu_id"));
            }
            if(request.getParameter("sub_sbu_id").equals("")){
                objSearch.setSBU_SUB_Id(null);
            }else{
                objSearch.setSBU_SUB_Id(request.getParameter("sub_sbu_id"));
            }
            objSearch.setProject_id(null);
        }else{
            String customerID = request.getParameter("customer");
            String project = request.getParameter("project");
            objSearch.setProject_id(project);
            objSearch.setCus_id(customerID);
            objSearch.setPrjt_id(Integer.parseInt(project));
            objSearch.setSBU_Id(null);
            objSearch.setSBU_SUB_Id(null);
        }
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        objSearch.setMonth(month);
        objSearch.setYear(year);
        int year_int = Integer.parseInt(objSearch.getYear());
        int month_int = Integer.parseInt(objSearch.getMonth());
        Calendar calendar = Calendar.getInstance();         
        calendar.set(year_int,month_int-1,1);
        String[] months = new DateFormatSymbols().getMonths();
        System.out.println("month name "+months[Integer.parseInt(month)-1]);
        int last_date = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        String start_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-01";
        String end_date = Integer.toString(year_int)+"-"+Integer.toString(month_int)+"-"+Integer.toString(last_date);
        objSearch.setStartDate(start_date);
        objSearch.setEndDate(end_date);
        List<FullAllowanceDto> details = null;
        String projectName="";
        if(processed.equals("0")){
            details = service.getEmployeeDetails(objSearch);
            projectName = service.getProjectName(objSearch.getPrjt_id());
        }else{
            details = service.getProcessedList(objSearch);
        }
        
        
        ArrayList entireList = new ArrayList();
        try{
            if(details.size()==0){
                ArrayList rowData =  new ArrayList();
                rowData.add(new String(""+"No data found"));
                entireList.add(rowData);
            }else{
                for(int i=0;i<details.size();i++){
                    ArrayList rowData =  new ArrayList();
                    rowData.add(new String(""+details.get(i).getEmployee_name()));
                    if(!processed.equals("0")){
                        rowData.add(new String(""+details.get(i).getCategory()));
                    }
                    rowData.add(new String(""+year));
                    rowData.add(new String(""+months[Integer.parseInt(month)-1]));
                    if(processed.equals("0")){
                        rowData.add(new String(""+projectName));
                    }else{
                        rowData.add(new String(""+details.get(i).getProject_name()));
                    }
                    rowData.add(new String(""+details.get(i).getCreated_by()));
                    rowData.add(new String(""+details.get(i).getCompany_general()));
                    rowData.add(new String(""+details.get(i).getCompany_shift_I()));
                    rowData.add(new String(""+details.get(i).getCompany_shift_II()));
                    rowData.add(new String(""+details.get(i).getCompany_shift_III()));
                    rowData.add(new String(""+details.get(i).getCust_general()));
                    rowData.add(new String(""+details.get(i).getCust_shift_I()));
                    rowData.add(new String(""+details.get(i).getCust_shift_II()));
                    rowData.add(new String(""+details.get(i).getCust_shift_III()));
                    rowData.add(new String(""+details.get(i).getWeekend_holidays_entered()));
                    rowData.add(new String(""+details.get(i).getTwo_way()));
                    rowData.add(new String(""+details.get(i).getOne_way()));
                    rowData.add(new String(""+details.get(i).getNo_cab()));
                    rowData.add(new String(""+details.get(i).getHardship_amount()));
                    rowData.add(new String(""+details.get(i).getShift_amount()));
                    rowData.add(new String(""+details.get(i).getHoliday_amount()));
                    rowData.add(new String(""+details.get(i).getTransport_amount()));
                    rowData.add(new String(""+details.get(i).getTotal_amount()));
                    rowData.add(new String(""+details.get(i).getRemarks()));
                    if(processed.equals("0")){
                        rowData.add(new String(""+"Not Submitted"));
                    }else{
                        rowData.add(new String(""+"Submitted"));
                    }
                    entireList.add(rowData);
                }
            }
        }catch(Exception e){
            System.out.println("exception "+e);
        }
        
        ArrayList Header = new ArrayList();
        ArrayList tableHeader = new ArrayList();
        tableHeader.add(new String("" +"Employee Name"));
        if(!processed.equals("0")){
            tableHeader.add(new String("" +"Employee Category"));
        }
        tableHeader.add(new String("" +"Year"));
        tableHeader.add(new String("" +"Month"));
        tableHeader.add(new String("" +"Project Name"));
        tableHeader.add(new String("" +"Submitted By"));
        tableHeader.add(new String("" +"HTL General Shift"));
        tableHeader.add(new String("" +"HTL Shift I"));
        tableHeader.add(new String("" +"HTL Shift II"));
        tableHeader.add(new String("" +"HTL Shift III"));
        tableHeader.add(new String("" +"Customer General Shift"));
        tableHeader.add(new String("" +"Customer Shift I"));
        tableHeader.add(new String("" +"Customer Shift II"));
        tableHeader.add(new String("" +"Customer Shift III"));
        tableHeader.add(new String("" +"Holiday/Weekend"));
        tableHeader.add(new String("" +"Two Way Cab"));
        tableHeader.add(new String("" +"One Way Cab"));
        tableHeader.add(new String("" +"No cab"));
        tableHeader.add(new String("" +"Hardship Amount"));
        tableHeader.add(new String("" +"Shift Amount"));
        tableHeader.add(new String("" +"Holiday Amount"));
        tableHeader.add(new String("" +"Transport Amount"));
        tableHeader.add(new String("" +"Total"));
        tableHeader.add(new String("" +"Remarks"));
        tableHeader.add(new String("" +"Status"));
        Header.add(tableHeader);
        CommonFunctions.exportToExcel(response, Header , entireList, "allowance_details.xls", "Allowance Details");
        
        return mv;
    }
    
}
