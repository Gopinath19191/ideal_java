/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dto.AccrualDto;
import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.service.AccrualReversalService;
import com.defiance.ideal.service.AccrualReversalServiceImpl;
import com.defiance.ideal.util.CommonFunctions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class AccrualReversal extends MultiActionController{
    private ModelAndView mv;
    
    public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<SearchDto> customerList = service.getCustomerList(created_by);
        //String customerID = request.getParameter("customer");
        mv = new ModelAndView("/com/defiance/accrualReversal");
        mv.addObject("customerList", customerList);
        return mv;
        
    }
    public ModelAndView projectList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by.equals(null)) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        objSearch.setCus_id(request.getParameter("cus_id"));
        response.getWriter().println("<option value=''>" + "--Project--" + "</option>");
        for (SearchDto dTO : (service.getProjectList(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getPrjt_id() + "'>" + dTO.getProject_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/accrualReversal");
        return null;
    }
    public ModelAndView getAccrualNo(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by.equals(null)) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        String project_id = request.getParameter("project_id");
        response.getWriter().println("<option value=''>" + "--Accrual No--" + "</option>");
        for (String accrual_no : (service.getAccrualNo(project_id))) {
            response.getWriter().println("<option value='" + accrual_no+ "'>" + accrual_no+ "</option>");
        }
        mv = new ModelAndView("/com/defiance/accrualReversal");
        return null;
    }
    public ModelAndView getEmployeeAccrualDetails(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by.equals(null)) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<AccrualDto> accrualList = service.getEmployeeAccrualDetails(objSearch);
        List<SearchDto> customerList = service.getCustomerList(created_by);
        List<SearchDto> projectList = service.getProjectList(objSearch);
        List<String> accrual_no = service.getAccrualNo(objSearch.getProject_id());
        mv = new ModelAndView("/com/defiance/accrualReversal");
        mv.addObject("filterData", objSearch);
        mv.addObject("employeeAccrualList", accrualList);
        mv.addObject("customerList", customerList);
        mv.addObject("projectList", projectList);
        mv.addObject("accrualList", accrual_no);
        return mv;
    }
    public ModelAndView submitAccrualReversal(HttpServletRequest request, HttpServletResponse response, AccrualDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<AccrualDto> accrualReversal = Arrays.asList(
                mapper.readValue(selectedRows.toString(), AccrualDto[].class));
        if(accrualReversal !=null && accrualReversal.size()>0){
            for(int rows = 0; rows < accrualReversal.size(); rows++){
                AccrualDto singleRow = accrualReversal.get(rows);
                singleRow.setCreated_by(created_by);
                service.submitAccrualReversal(singleRow);
            }
        }
        List<SearchDto> customerList = service.getCustomerList(created_by);
        mv = new ModelAndView("/com/defiance/accrualReversal");
        mv.addObject("customerList", customerList);
        return mv;
    }
    
    public ModelAndView timesheetReversal(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<SearchDto> customerList = service.getCustomerList(created_by);
        //String customerID = request.getParameter("customer");
        mv = new ModelAndView("/com/defiance/timesheetReversal");
        mv.addObject("customerList", customerList);
        return mv;
    }
    public ModelAndView getEmployeeTimesheetDetails(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            objSearch.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<AccrualDto> timesheetDetails = service.getEmployeeTimesheetDetails(objSearch);
        Map<String,List> employeeProjetList = new HashMap<String, List>();
        if(timesheetDetails.size()>0){
            for(int i=0;i<timesheetDetails.size();i++){
                objSearch.setEmployee_id(timesheetDetails.get(i).getEmployee_id());
                List<SearchDto> projects = service.getEmployeeProjects(objSearch);
                employeeProjetList.put(timesheetDetails.get(i).getEmployee_id(), projects);
            }
        }
        List<SearchDto> customerList = service.getCustomerList(created_by);
        List<SearchDto> projectList = service.getProjectList(objSearch);
        mv = new ModelAndView("/com/defiance/timesheetReversal");
        mv.addObject("filterData", objSearch);
        mv.addObject("employeeProjetList",employeeProjetList);
        mv.addObject("timesheetDetails", timesheetDetails);
        mv.addObject("customerList", customerList);
        mv.addObject("projectList", projectList);
        return mv;
    }
    
    public ModelAndView getEmployeeRole(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by.equals(null)) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        response.getWriter().println("<option value=''>" + "--Role--" + "</option>");
        for (SearchDto dTO : (service.getEmployeeRole(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getRole_id() + "' role_rate='"+ dTO.getBilling_rate() +"'>" + dTO.getRole_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/timesheetReversal");
        return null;
    }
    
    public ModelAndView updateTimesheet(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<SearchDto> timeshetUpdate = Arrays.asList(
                mapper.readValue(selectedRows.toString(), SearchDto[].class));
        if(timeshetUpdate !=null && timeshetUpdate.size()>0){
            for(int rows = 0; rows < timeshetUpdate.size(); rows++){
                SearchDto singleRow = timeshetUpdate.get(rows);
                singleRow.setCreated_by(created_by);
                service.updateTimesheet(singleRow);
            }
        
        }
        List<SearchDto> customerList = service.getCustomerList(created_by);
        mv = new ModelAndView("/com/defiance/timesheetReversal");
        mv.addObject("customerList", customerList);
        mv.addObject("submit_status","Timesheet moved to new project Successfully");
        return mv;
    }
    
    public ModelAndView timesheetRejection(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<SearchDto> projectList = service.getProjectByManager(created_by);
        mv = new ModelAndView("/com/defiance/timesheetRejection");
        mv.addObject("projectList", projectList);
        return mv;
    }
    public ModelAndView employeeList(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by.equals(null)) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        String project_id = request.getParameter("project_id");
        if(project_id.equals("Non_Project_Activity")){
            response.getWriter().println("<option value=''>" + "--Employee--" + "</option>");
            for (SearchDto dTO : (service.getReporteesList(created_by))) {
                response.getWriter().println("<option value='" + dTO.getEmployee_id() + "'>" + dTO.getEmployee_name() + "</option>");
            }
        }else{
            response.getWriter().println("<option value=''>" + "--Employee--" + "</option>");
            for (SearchDto dTO : (service.getEmployeeList(project_id))) {
                response.getWriter().println("<option value='" + dTO.getEmployee_id() + "'>" + dTO.getEmployee_name() + "</option>");
            }
        }
        
        mv = new ModelAndView("/com/defiance/accrualReversal");
        return null;
    }
    public ModelAndView getTimesheetDetails(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            objSearch.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<AccrualDto> timesheetDetails = service.getTimesheetDetails(objSearch);
        List<SearchDto> projectList = service.getProjectByManager(created_by);
        List<SearchDto> employeeList = null;
        if(objSearch.getProject_id().equals("Non_Project_Activity")){
            employeeList = service.getReporteesList(created_by);
        }else{
            employeeList = service.getEmployeeList(objSearch.getProject_id());
        }
        mv = new ModelAndView("/com/defiance/timesheetRejection");
        System.out.println("prjt "+objSearch.getProject_id());
        mv.addObject("filterData", objSearch);
        mv.addObject("timesheetDetails", timesheetDetails);
        mv.addObject("employeeList", employeeList);
        mv.addObject("projectList", projectList);
        return mv;
    }
    
    public ModelAndView rejectTimesheet(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<SearchDto> timeshetUpdate = Arrays.asList(
                mapper.readValue(selectedRows.toString(), SearchDto[].class));
        if(timeshetUpdate !=null && timeshetUpdate.size()>0){
            for(int rows = 0; rows < timeshetUpdate.size(); rows++){
                SearchDto singleRow = timeshetUpdate.get(rows);
                service.rejectTimesheet(singleRow.getTimesheet_id());
            }
        }
        List<SearchDto> projectList = service.getProjectByManager(created_by);
        List<SearchDto> employeeList = null;
        if(objSearch.getProject_id().equals("Non_Project_Activity")){
            employeeList = service.getReporteesList(created_by);
        }else{
            employeeList = service.getEmployeeList(objSearch.getProject_id());
        }
        List<AccrualDto> timesheetDetails = service.getTimesheetDetails(objSearch);
        mv = new ModelAndView("/com/defiance/timesheetRejection");
        mv.addObject("filterData", objSearch);
        mv.addObject("timesheetDetails", timesheetDetails);
        mv.addObject("employeeList", employeeList);
        mv.addObject("projectList", projectList);
        mv.addObject("submit_status","Timesheet Rejected/Sent back Successfully");
        return mv;
    }
    public ModelAndView getOfficeTiming(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<SearchDto> OfficeTiming = service.getOfficeTimingList();
        List<SearchDto> selectedList = service.getSelectedList(created_by);        
        mv = new ModelAndView("/com/defiance/officeTimingUpdate");
        mv.addObject("OfficeTiming", OfficeTiming);
        mv.addObject("selectedList", selectedList);
        return mv;
    }
    public ModelAndView updateOfficeTiming(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        if(filterData.getRole_id().equals("e")){
            filterData.setStatus("a");
        }else{
            filterData.setStatus("m");
        }
        service.updateOfficeTiming(filterData);
        List<SearchDto> OfficeTiming = service.getOfficeTimingList();
        List<SearchDto> selectedList = service.getSelectedList(created_by); 
        mv = new ModelAndView("/com/defiance/officeTimingUpdate");
        mv.addObject("OfficeTiming", OfficeTiming);
        mv.addObject("selectedList", selectedList);
        mv.addObject("submit_status","Office timing preference submitted Successfully");
        return mv;
    }
    public ModelAndView getMangerList(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        List<SearchDto> employeeList = service.getEmployessList(created_by);
        mv = new ModelAndView("/com/defiance/timingApproval");
        mv.addObject("employeeList", employeeList);
        return mv;
    }
    public ModelAndView approveOfficeTiming(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<SearchDto> timeshetUpdate = Arrays.asList(
                mapper.readValue(selectedRows.toString(), SearchDto[].class));
        if(timeshetUpdate != null && timeshetUpdate.size()>0){
            for(int rows = 0; rows < timeshetUpdate.size(); rows++){
                SearchDto singleRow = timeshetUpdate.get(rows);
                singleRow.setCreated_by(created_by);
                singleRow.setStatus(objSearch.getStatus());
                System.out.println("row id "+singleRow.getTimesheet_id());
                System.out.println("row id "+singleRow.getCreated_by());
                System.out.println("row id "+singleRow.getStatus());
                service.approveOfficeTiming(singleRow);
            }
        }
        String status = "";
        if(objSearch.getStatus().equals("a")){
            status = "Approved";
        }else{
            status = "Rejected/Sent Back";
        }
        List<SearchDto> employeeList = service.getEmployessList(created_by);
        mv = new ModelAndView("/com/defiance/timingApproval");
        mv.addObject("employeeList", employeeList);
        mv.addObject("submit_status","Office Timing "+status+" Successfully");
        return mv;
    }
    
    public ModelAndView accrualAccountingMonth(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        int current_year = CommonFunctions.getCurrentYear();
        String selected_year = "";
        System.out.println("filterData.getYear() "+filterData.getYear());
        if(filterData.getYear() != "" && filterData.getYear() != null){
            selected_year = filterData.getYear();
        }else{
            System.out.println("come here");
            selected_year = Integer.toString(current_year);
        }
        Map<Integer, Integer> yearsList = new LinkedHashMap<Integer, Integer>();;
        for (int i = 2021; i <= current_year; i++) {
            yearsList.put(i,i);
        }
        List<SearchDto> accrual_details = service.getAccrualDetails(selected_year);
        mv = new ModelAndView("/com/defiance/accrualAccountingMonth");
        mv.addObject("yearsList", yearsList);
        mv.addObject("accrual_details", accrual_details);
        mv.addObject("selected_year", selected_year);
        return mv;
        
    }
    
    public ModelAndView updateAccrualClose(HttpServletRequest request, HttpServletResponse response, SearchDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualReversalService service = (AccrualReversalServiceImpl) ctx.getBean("AccrualReversalService");
        String acc_no = request.getParameter("end_no");
        String acc_month = request.getParameter("month");
        String acc_year = request.getParameter("year");
        filterData.setEndDate(acc_no);
        filterData.setStartMonth(acc_month);
        filterData.setStartYear(acc_year);
        filterData.setCreated_by(created_by);
        
        String update_status = service.updateAccrualClose(filterData);
        
        mv = new ModelAndView("redirect:accrualAccountingMonth.htm?&update_status="+update_status);
        
        return mv;
        
    }
}
