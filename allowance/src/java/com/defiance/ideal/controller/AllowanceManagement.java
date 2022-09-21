/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dto.SearchDto;
import com.defiance.ideal.dto.ShiftAndTransportDto;
import com.defiance.ideal.service.ShiftAndTransportService;
import com.defiance.ideal.service.ShiftAndTransportServiceImpl;
import com.defiance.ideal.util.CommonFunctions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author 8000247
 */
public class AllowanceManagement extends MultiActionController {

   private ModelAndView mv;

   public ModelAndView applyAllowance(HttpServletRequest request, HttpServletResponse response,ShiftAndTransportDto filterData) throws Exception {
       HttpSession session = request.getSession();
       String created_by=(String)session.getAttribute("accessId");
       String designation=(String)session.getAttribute("designation");
       if(created_by == null || designation == null){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        Date date= new Date();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da=dat.format(date);
        int d=Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);
        System.out.println("month "+mn);
        if(mn==1){
            if(d>=18){
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
            } else {
                System.out.println("inside");
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(1);
                System.out.println("monthlist "+monthsList);
                System.out.println("yearlist "+yearsList);
            }
        } else {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
       
        mv = new ModelAndView("/com/defiance/applyallowance");
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        
        List<ShiftAndTransportDto> shift =service.getShiftDetails();
        
        if(designation.equals("13")){
            List<ShiftAndTransportDto> SBU_List = service.getSBU_List();
            mv.addObject("SBU_List", SBU_List);
        }
        
        mv.addObject("yearsList", yearsList);
        mv.addObject("shift",shift);
        //mv.addObject("monthsList", monthsList);
        return mv;
    }

    public ModelAndView getMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> monthsList;
        Date date = new Date();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        if (mn == 1) {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getMonthsList();
            }
        } else {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
        }

        response.getWriter().println("<option value=''>" + "--Month--" + "</option>");
        for (Map.Entry<String, String> entry : monthsList.entrySet()) {
            response.getWriter().println("<option value='" + entry.getKey() + "'>" + entry.getValue() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/applyallowance");
        return null;
    }

    public ModelAndView getCustomer(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        response.getWriter().println("<option value=''>" + "--All--" + "</option>");
        for (ShiftAndTransportDto dTO : (service.getCustomerList(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getCus_id() + "'>" + dTO.getCustomer_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/applyallowance");
        return null;
    }

    public ModelAndView getProject(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();

        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        objSearch.setCus_id(request.getParameter("cus_id"));
        response.getWriter().println("<option value=''>" + "--All--" + "</option>");
        for (ShiftAndTransportDto dTO : (service.getProjectList(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getProject_id() + "'>" + dTO.getProject_name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/applyallowance");
        return null;
    }

    public ModelAndView getSUB_SBU(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {

        HttpSession session = request.getSession();
        String created_by = (String) session.getAttribute("accessId");
        String designation = (String) session.getAttribute("designation");
        if (created_by == null || designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        response.getWriter().println("<option value=''>" + "--SUB SBU--" + "</option>");
        for (ShiftAndTransportDto dTO : (service.getSBU_SUB_List(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getSBU_SUB_Id() + "'>" + dTO.getSBU_SUB_Name() + "</option>");
        }
        mv = new ModelAndView("/com/defiance/applyallowance");
        return null;
    }

    public ModelAndView getEmployeeDetails(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {

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
        String da1 = dfm1.format(date);
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        String customerID = request.getParameter("customer");
        String project = request.getParameter("project");
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        session.setAttribute("month", month);
        objSearch.setCus_id(customerID);
        objSearch.setProject_id(project);
        objSearch.setMonth(month);
        objSearch.setYear(year);
        if (designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else if (!designation.equals("13")) {
            if (project.equals("")) {
                objSearch.setProject_id(null);
            } else {
                objSearch.setProject_id(project);
            }
            if (customerID.equals("")) {
                objSearch.setCus_id(null);
            } else {
                objSearch.setCus_id(customerID);
            }
        } else {
            String SBU_Id = request.getParameter("SBU_Id");
            String SBU_SUB_Id = request.getParameter("SBU_SUB_Id");
            objSearch.setProject_id(null);
            objSearch.setCus_id(null);
            objSearch.setCreated_by(null);
            if (SBU_Id.equals("")) {
                objSearch.setSBU_Id(null);
            } else {
                objSearch.setSBU_Id(SBU_Id);
            }
            if (SBU_SUB_Id.equals("")) {
                objSearch.setSBU_SUB_Id(null);
            } else {
                objSearch.setSBU_SUB_Id(SBU_SUB_Id);
            }
            objSearch.setStatus("1");
        }


        List<ShiftAndTransportDto> details = service.getEmployeeDetails(objSearch);
        List<ShiftAndTransportDto> shift = service.getShiftDetails();
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        if (mn == 1) {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(0);
            }
        } else {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        ShiftAndTransportDto dtoSubmittedCount = service.submittedCount(objSearch);
        ShiftAndTransportDto dtoDetailsCount = service.detailsCount(objSearch);
        List<ShiftAndTransportDto> customer = service.getCustomerList(objSearch);
        List<ShiftAndTransportDto> projectList = service.getProjectList(objSearch);
        if (dtoSubmittedCount != null) {
            request.setAttribute("submittedCount", dtoSubmittedCount.getSubmittedcount());
        } else {
            request.setAttribute("submittedCount", "0");
        }
        if (dtoDetailsCount != null) {
            request.setAttribute("detailsCount", dtoDetailsCount.getDetailsCount());
        } else {
            request.setAttribute("detailsCount", "0");
        }
        mv = new ModelAndView("/com/defiance/applyallowance");
        if (designation == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            if (designation.equals("13")) {
                List<ShiftAndTransportDto> SBU_List = null;
                List<ShiftAndTransportDto> SBU_SUB_List = null;
                String SBU_Id = request.getParameter("SBU_Id");
                String SBU_SUB_Id = request.getParameter("SBU_SUB_Id");
                if (SBU_Id.equals("")) {
                    SBU_List = service.getSBU_List();
                    objSearch.setSBU_Id(SBU_Id);
                    mv.addObject("SBU_List", SBU_List);
                } else {
                    SBU_List = service.getSBU_List();
                    objSearch.setSBU_Id(SBU_Id);
                    SBU_SUB_List = service.getSBU_SUB_List(objSearch);
                    objSearch.setSBU_SUB_Id(SBU_SUB_Id);
                    mv.addObject("SBU_List", SBU_List);
                    mv.addObject("SBU_SUB_List", SBU_SUB_List);
                }
            }
        }

        mv.addObject("filterData", objSearch);
        mv.addObject("customer", customer);
        mv.addObject("project", projectList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("shift", shift);
        mv.addObject("monthsList", monthsList);
        mv.addObject("details", details);
        return mv;
    }

    public ModelAndView submit(HttpServletRequest request, HttpServletResponse response, SearchDto objSearch) throws Exception {

        HttpSession session = request.getSession();
        int count = 0;
        String succcessMsg = "";
        String prj = null;
        String created_by = (String) session.getAttribute("accessId");
        if (created_by == null) {
            mv = new ModelAndView("/unauthorised");
        }
        String save = request.getParameter("save");
        String submit = request.getParameter("ok");
        String project_id = request.getParameter("project");
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        String cus_id = request.getParameter("customer");
        String selectedRows = (String) request.getParameter("selectedRows");
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");
        DateFormat dfm1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String da1 = dfm1.format(date);
        long s = CommonFunctions.timeConversion(da1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<ShiftAndTransportDto> allowanceDetails = Arrays.asList(
                mapper.readValue(selectedRows.toString(), ShiftAndTransportDto[].class));
        if (allowanceDetails != null && allowanceDetails.size() > 0) {
            for (int iTSEntry = 0; iTSEntry < allowanceDetails.size(); iTSEntry++) {
                ShiftAndTransportDto allowanceList = allowanceDetails.get(iTSEntry);
                allowanceList.setProject(project_id);
                allowanceList.setYear(year);
                allowanceList.setMonth(month);
                allowanceList.setCustomer(cus_id);

                allowanceList.setCreated_by(created_by);
                int fromFilter = Integer.parseInt(allowanceDetails.get(0).getProject_id());
                int fromResultSet = Integer.parseInt(allowanceList.getProject_id());
                if (fromFilter != fromResultSet) {
                    count++;
                }
                if (save != null) {
                        if (allowanceList.getStatus()== null || !allowanceList.getStatus().equals("0")) {
                        allowanceList.setCreated_date(s);
                        allowanceList.setModified_date(s);
                        allowanceList.setStatus("0");
                        service.insertDetails(allowanceList);
                    } else if (allowanceList.getStatus().equals("0")) {
                        allowanceList.setModified_date(s);
                        allowanceList.setStatus("0");
                        service.updateDetails(allowanceList);
                    }
                    } 
                if (submit != null){
                        if (allowanceList.getStatus() == null || !allowanceList.getStatus().equals("0")) {
                        allowanceList.setCreated_date(s);
                        allowanceList.setModified_date(s);
                        allowanceList.setStatus("1");
                        service.insertDetails(allowanceList);
                    } else if (allowanceList.getStatus().equals("0")) {
                        allowanceList.setModified_date(s);
                        allowanceList.setStatus("1");
                        service.updateDetails(allowanceList);
                    }
                    }
            }
            if (allowanceDetails.get(0).getStatus().equals("0")) {
                succcessMsg = "Data saved successfully";
            } else {
                succcessMsg = "Data submitted successfully";
            }
        }
        if (count == 0) {
            objSearch.setProject_id(project_id);
        } else {
            objSearch.setProject_id(null);
        }
        objSearch.setStatus(null);
        objSearch.setYear(year);
        objSearch.setMonth(month);
        objSearch.setCreated_by(created_by);
        objSearch.setCus_id(cus_id);
        List<ShiftAndTransportDto> details = service.getEmployeeDetails(objSearch);
        List<ShiftAndTransportDto> shift = service.getShiftDetails();
        Map<String, String> monthsList;
        Map<Integer, Integer> yearsList;
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dat = new SimpleDateFormat("dd");
        String da = dat.format(date);
        int d = Integer.valueOf(da);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont = mon.format(date);
        int mn = Integer.valueOf(mont);
        if (mn == 1) {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
                yearsList = CommonFunctions.getYearsList(0);
            } else {
                monthsList = CommonFunctions.getMonthsList();
                yearsList = CommonFunctions.getYearsList(0);
            }
        } else {
            if (d >= 18) {
                monthsList = CommonFunctions.getCurrentMonth();
            } else {
                monthsList = CommonFunctions.getLastMonth();
            }
            yearsList = CommonFunctions.getYearsList(0);
        }
        List<ShiftAndTransportDto> customer = service.getCustomerList(objSearch);
        List<ShiftAndTransportDto> projectList = service.getProjectList(objSearch);
        mv = new ModelAndView("/com/defiance/applyallowance");
        mv.addObject("filterData", objSearch);
        mv.addObject("customer", customer);
        mv.addObject("project", projectList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("shift", shift);
        mv.addObject("monthsList", monthsList);
        request.setAttribute("succcessMsg", succcessMsg);
        mv.addObject("details", details);
        return mv;
    }
    public ModelAndView getExcel(HttpServletRequest request, HttpServletResponse response,SearchDto objSearch) throws Exception {
        HttpSession session = request.getSession();
        String created_by=(String)session.getAttribute("accessId");
        String designation=(String)session.getAttribute("designation");
        if(created_by == null){
            mv = new ModelAndView("/unauthorised");
        }else{
            objSearch.setCreated_by(created_by);
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        ShiftAndTransportService service = (ShiftAndTransportServiceImpl) ctx.getBean("ShiftAndTransportService");      
        String customerID = request.getParameter("customer");
        String project = request.getParameter("project");
        String year = request.getParameter("selYear");
        String month = request.getParameter("selMon");
        objSearch.setCus_id(customerID);
        objSearch.setProject_id(project);
        objSearch.setMonth(month);
        objSearch.setYear(year);
        if(designation == null){
            mv = new ModelAndView("/unauthorised");
        }else if(!designation.equals("13")){
            if( project.equals("")){
            objSearch.setProject_id(null);
        }
        else{
            objSearch.setProject_id(project);
        }
        if( customerID.equals("") ){
            objSearch.setCus_id(null);
        }
        else{
            objSearch.setCus_id(customerID);
        }
        }else{
            String SBU_Id = request.getParameter("SBU_Id");
            String SBU_SUB_Id = request.getParameter("SBU_SUB_Id");
            objSearch.setProject_id(null);
            objSearch.setCus_id(null);
            objSearch.setCreated_by(null);
            if(SBU_Id.equals("")){
                objSearch.setSBU_Id(null);
            }else{
                objSearch.setSBU_Id(SBU_Id);
            }if(SBU_SUB_Id.equals("")){
                objSearch.setSBU_SUB_Id(null);
            }else{
                objSearch.setSBU_SUB_Id(SBU_SUB_Id);
            }
        }
        
        objSearch.setYear(year);
        objSearch.setMonth(month);
        objSearch.setStatus("1");
        List<ShiftAndTransportDto> saveddetails = service.getEmployeeDetails(objSearch);
        ArrayList entireList = new ArrayList();
        try{
        for (int i = 0; i < saveddetails.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + saveddetails.get(i).getEmp_id()));
            rowDataList.add(new String("" + saveddetails.get(i).getEmp_name()));
            rowDataList.add(new String("" + saveddetails.get(i).getDOJ()));
            rowDataList.add(new String("" + saveddetails.get(i).getSBU_Name()));
            rowDataList.add(new String("" + saveddetails.get(i).getCity()));
            rowDataList.add(new String("" + saveddetails.get(i).getProjectStartDate()));
            rowDataList.add(new String("" + saveddetails.get(i).getPrj_code()));
            rowDataList.add(new String("" + saveddetails.get(i).getPrj_name()));
            rowDataList.add(new String("" + saveddetails.get(i).getCus_code()));
            rowDataList.add(new String("" + saveddetails.get(i).getCus_name()));
            rowDataList.add(new String("" + saveddetails.get(i).getMonthInText()));
            rowDataList.add(new String("" + saveddetails.get(i).getYear()));
            rowDataList.add(new String("" + saveddetails.get(i).getLocation()));
            rowDataList.add(new String("" + saveddetails.get(i).getGeneral()));
            rowDataList.add(new String("" + saveddetails.get(i).getShift1_days()));
            rowDataList.add(new String("" + saveddetails.get(i).getShift2_days()));
            rowDataList.add(new String("" + saveddetails.get(i).getShift3_days()));
            rowDataList.add(new String("" + saveddetails.get(i).getShift_amount()));
            rowDataList.add(new String("" + saveddetails.get(i).getHardship_amount()));
            rowDataList.add(new String("" + saveddetails.get(i).getHs_amount()));
            rowDataList.add(new String("" + saveddetails.get(i).getWay()));
            rowDataList.add(new String("" + saveddetails.get(i).getTransport_amount()));
            rowDataList.add(new String("" + saveddetails.get(i).getTotal_amount()));
            rowDataList.add(new String("" + saveddetails.get(i).getRemarks()));
            entireList.add(rowDataList);
        }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        
        ArrayList Header = new ArrayList();
        ArrayList tableHeader = new ArrayList();
        tableHeader.add(new String("" +"Employee Id"));
        tableHeader.add(new String("" +"Employee Name"));
        tableHeader.add(new String("" +"DOJ"));
        tableHeader.add(new String("" +"SBU"));
        tableHeader.add(new String("" +"Location"));
        tableHeader.add(new String("" +"Project Start Date"));
        tableHeader.add(new String("" +"Project Code"));
        tableHeader.add(new String("" +"Project Name"));
        tableHeader.add(new String("" +"Customer Code"));
        tableHeader.add(new String("" +"Customer Name"));
        tableHeader.add(new String("" +"Month"));
        tableHeader.add(new String("" +"Year"));
        tableHeader.add(new String("" +"Project Work location > 30 kms from HTI location"));
        tableHeader.add(new String("" +"General Shift Days"));
        tableHeader.add(new String("" +"First Shift Days"));
        tableHeader.add(new String("" +"Second Shift Days"));
        tableHeader.add(new String("" +"Third Shift Days"));
        tableHeader.add(new String("" +"Shift Allowance"));
        tableHeader.add(new String("" +"Hardship Allowance"));
        tableHeader.add(new String("" +"Shift/Hardship Allowance"));
        tableHeader.add(new String("" +"Cab Facility Availed"));
        tableHeader.add(new String("" +"Transport Allowance")); 
        tableHeader.add(new String("" +"Total Allowance Amount")); 
        tableHeader.add(new String("" +"Remarks")); 
        Header.add(tableHeader);
        CommonFunctions.exportToExcel(response, Header , entireList, "allowance_details.xls", "Allowance Details");
        return mv;
    }
    
}
