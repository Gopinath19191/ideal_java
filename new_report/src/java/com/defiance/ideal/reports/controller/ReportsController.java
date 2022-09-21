/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import com.defiance.ideal.reports.dto.FilterDTO;
import com.defiance.ideal.reports.dto.FullReportDTO;
import com.defiance.ideal.reports.dto.PhaseTaskDTO;
import com.defiance.ideal.reports.dto.ReportEmployeeExperienceDTO;
import com.defiance.ideal.reports.dto.ReportsDTO;
import com.defiance.ideal.reports.dto.ReportsDataDTO;
import com.defiance.ideal.reports.dto.ReportsFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.BillableHoursService;
import com.defiance.ideal.reports.service.BillableHoursServiceImpl;
import com.defiance.ideal.reports.service.ReportsService;
import com.defiance.ideal.reports.service.ReportsServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author 14053
 */
public class ReportsController extends MultiActionController {

    public ReportsController() {
//        setCommandClass(ReportsFilterDTO.class);
//        setCommandName("reportForm");
    }

    public ModelAndView timesheetreport(HttpServletRequest request, HttpServletResponse response, ReportsFilterDTO filterData) throws Exception {

        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

//        if(session.getAttribute("EMP_ID")==null){
//            employeeId = request.getParameter("empid");
//            session.setAttribute("EMP_ID", employeeId);
//        }else{
        employeeId = (String) session.getAttribute("EMP_ID");
//        }

        if (filterData.getMonthFilter() == null) {
            filterData.setMonthFilter(String.format("%02d", (calndr.get(Calendar.MONTH) + 1)));
        }

        if (filterData.getYearFilter() == null) {
            int yr = calndr.get(Calendar.YEAR);
            filterData.setYearFilter(Integer.toString(yr));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(filterData.getYearFilter()), (Integer.parseInt(filterData.getMonthFilter()) - 1), 1);
        int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        filterData.setLastDate(lstDate);

       // System.out.println("========== employeeId ==========" + session.getAttribute("EMP_ID"));

        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
            //List<ReportsDTO> employeeData=reportService.getEmployeeList();
            Map<String, String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> yearsMap = CommonMethods.getYearsList(3);

            SOP("Employee ID", employeeId);

            Map<Integer, String> projectList = reportService.getProjects(employeeId);

            List<ReportsDataDTO> associateData = reportService.fetchReport(filterData);
//            List<ReportsDataDTO> associateData = reportService.fetchReport2(filterData);

            SOP("Report Data Size", associateData.size());

//          for(int assoc=0; assoc < associateData.size(); assoc++){
//            System.out.println("assoc = " + assoc);
//            System.out.println("associateData.get(assoc).getWorkDetails().get(1).getWorkedHrs()"+associateData.get(assoc).getWorkDetails().get(1).getWorkedHrs());
//          }

//          if(request.getParameter("excel")!=null){
//            excelexport(response, associateData, "attendance.xls");
//          }

            mv = new ModelAndView("/home");
            //mv.addObject("employeeList",employeeData);
            mv.addObject("monthsMap", monthsMap);
            mv.addObject("yearsMap", yearsMap);
            mv.addObject("projectList", projectList);
            mv.addObject("filterData", filterData);
            mv.addObject("associateData", associateData);

        } catch (Exception e) {
            mv = new ModelAndView("/error");
            StackTraceElement[] errorMsg = e.getStackTrace();

          //  System.out.println("errorMsg = " + errorMsg[0].getMethodName());
          //  System.out.println("errorMsg = " + errorMsg[0].getLineNumber());
          //  System.out.println("errorMsg = " + errorMsg[0].getClassName());

            mv.addObject("errorMessage", errorMsg);
            mv.addObject("testing", "TESTING MESSAGE");
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView excelexport(HttpServletRequest request, HttpServletResponse response, ReportsFilterDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
        List<ReportsDataDTO> associateData = reportService.fetchReport(filterData);

        ArrayList entireList = new ArrayList();

        for (int i = 0; i < associateData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + associateData.get(i).getEmployeeNumber()));
            rowDataList.add(new String("" + associateData.get(i).getEmployeeName()));
            int daysList = associateData.get(i).getWorkDetails().size();
            for (int j = 0; j < daysList; j++) {
                rowDataList.add(new String("" + associateData.get(i).getWorkDetails().get(j).getWorkedHrs()));
            }
            int remRows = 31 - daysList;
            for (int nb = 0; nb < remRows; nb++) {
                rowDataList.add(new String(""));
            }
            rowDataList.add(new String("" + associateData.get(i).getTotalWorkedHrs()));
            rowDataList.add(new String("" + associateData.get(i).getTotalApprovedHrs()));
            rowDataList.add(new String("" + associateData.get(i).getAccrEffort()));
            rowDataList.add(new String("" + associateData.get(i).getInvEffort()));
            entireList.add(rowDataList);
        }

        CommonMethods.exportToExcel(response, entireList, "attendance_report.xls", "attendance", null);
        return null;
    }

    public void SOP(String keyToDisp, Object strToPrnt) {
       // System.out.println(keyToDisp + "  --- --- = --- --- " + strToPrnt);
    }

    public ModelAndView filter(HttpServletRequest request, HttpServletResponse response, final FullReportDTO dataTo) {
        ModelAndView mvc = new ModelAndView("projectlist");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
     //   List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String parentId = "";
        if("".equals(dataTo.getSbu())||dataTo.getSbu()==null||"All".equals(dataTo.getSbu())){   
            parentId = PES + ',' + TS;
        }else{
           parentId =  dataTo.getSbu().equals("PES")?"2":"5";
//           dataTo.setSbu(parentId);
        }
        List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
        List<AssociateFilterDTO> Sbu = bo.getSbu("1");
        List<FilterDTO> list = null;
        System.out.println("sbu "+parentId);
        list = dao.getSortedList(dataTo);
      //  System.out.println("sorted list length" + list.size());
        mvc.addObject("list", list);
        mvc.addObject("result", dataTo);
        mvc.addObject("subSbu", subSbu);
        mvc.addObject("sbu", Sbu);
        return mvc;
    }

    public ModelAndView projectlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mvc = new ModelAndView("projectlist");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
        List<FilterDTO> list = dao.getList();
      //  List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String parentId = PES + ',' + TS;
        List<AssociateFilterDTO> Sbu = bo.getSbu("1");
        List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
        FullReportDTO dto = new FullReportDTO();
        String[] st = {"e"};
        dto.setStatus(st);
        String[] own = {"df", "cr"};
        dto.setOwnership(own);
        mvc.addObject("result", dto);
        mvc.addObject("list", list);
        mvc.addObject("subSbu", subSbu);
        mvc.addObject("sbu", Sbu);
        return mvc;
    }

    public ModelAndView getDataXL(HttpServletRequest request, HttpServletResponse response, final FullReportDTO dataTo) throws IOException, Exception {
       // System.out.println("Inside Excel sheet");
        ModelAndView mvc = new ModelAndView("report");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        List<FilterDTO> associateData = null;
//         if(dataTo.getStatus()!=null){
//            list = dao.getSortedList(dataTo.getProjectcode(), null,dataTo.getSbu());
//        }else{
//        list = dao.getSortedList(dataTo.getProjectcode(), dataTo.getStatus(),dataTo.getSbu());
//        }
        associateData = dao.getSortedList(dataTo);
//        System.out.println("sorted list length" + list.size());
//        mvc.addObject("list", list);
//        mvc.addObject("result", dataTo);
        ArrayList entireList = new ArrayList();

        for (int i = 0; i < associateData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + associateData.get(i).getCode()));
            rowDataList.add(new String("" + associateData.get(i).getName()));
            rowDataList.add(new String("" + associateData.get(i).getStart_date()));
            rowDataList.add(new String("" + associateData.get(i).getEnd_date()));
            //rowDataList.add(new String("" + associateData.get(i).getActual_start_date()));
            //rowDataList.add(new String("" + associateData.get(i).getActual_end_date()));
            rowDataList.add(new String("" + associateData.get(i).getCustomer_name()));
            rowDataList.add(new String("" + associateData.get(i).getSalesPerson()));
            rowDataList.add(new String("" + associateData.get(i).getSales_order_type()));
            rowDataList.add(new String("" + associateData.get(i).getManager()));
            rowDataList.add(new String("" + associateData.get(i).getSbu()));
            rowDataList.add(new String("" + associateData.get(i).getSubSbu()));
            rowDataList.add(new String("" + associateData.get(i).getBillable_efforts()));
            rowDataList.add(new String("" + associateData.get(i).getBilling_uom()));
            //rowDataList.add(new String("" + associateData.get(i).getActual_efforts()));
            //rowDataList.add(new String("" + "Hours"));
            rowDataList.add(new String("" + associateData.get(i).getBillable()));
            rowDataList.add(new String("" + associateData.get(i).getProject_type()));
            rowDataList.add(new String("" + associateData.get(i).getOwnership()));
            rowDataList.add(new String("" + associateData.get(i).getModel()));
            rowDataList.add(new String("" + associateData.get(i).getBusiness_model()));
            rowDataList.add(new String("" + associateData.get(i).getStatus()));
            rowDataList.add(new String("" + associateData.get(i).getPricing()));
            rowDataList.add(new String("" + associateData.get(i).getCreated()));
            rowDataList.add(new String("" + associateData.get(i).getClosed()));
            rowDataList.add(new String("" + associateData.get(i).getPhase()));
            rowDataList.add(new String("" + associateData.get(i).getTasks()));
            rowDataList.add(new String("" + associateData.get(i).getTeam_count()));
            rowDataList.add(new String("" + associateData.get(i).getLast_modified_date()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "project_report.xls", "project report", null);
        return null;
    }

    public ModelAndView phasetasklist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mvc = new ModelAndView("phasetasklist");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
       // List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String parentId = PES + ',' + TS;
        List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
        List<AssociateFilterDTO> sbu = bo.getSbu("1");
        List<PhaseTaskDTO> list = dao.getPhaseTaskList();
        FullReportDTO dto = new FullReportDTO();
        String[] st = {"e"};
        dto.setStatus(st);
        String[] own = {"df", "cr"};
        dto.setOwnership(own);
        mvc.addObject("result", dto);
        mvc.addObject("list", list);
        mvc.addObject("subSbu", subSbu);
        mvc.addObject("sbu", sbu);
        return mvc;
    }

    public ModelAndView filterPhaseTask(HttpServletRequest request, HttpServletResponse response, final FullReportDTO dataTo) {
        ModelAndView mvc = new ModelAndView("phasetasklist");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
        // List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String parentId = PES + ',' + TS;
        List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
        List<AssociateFilterDTO> sbu = bo.getSbu("1");
        List<PhaseTaskDTO> list = null;
       // System.out.println("dataTo" + dataTo.getStatus());
        list = dao.getPhaseTaskSortedList(dataTo);
        System.out.println("sorted list length" + list.size());
        System.out.println("sorted list::" + list);
        mvc.addObject("list", list);
        mvc.addObject("result", dataTo);
        mvc.addObject("subSbu", subSbu);
        mvc.addObject("sbu", sbu);
        return mvc;
    }

    public ModelAndView getPhaseTaskDataXL(HttpServletRequest request, HttpServletResponse response, final FullReportDTO dataTo) throws IOException, Exception {
    //    System.out.println("Inside Excel sheet");
        ModelAndView mvc = new ModelAndView("report");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl dao = (ReportsServiceImpl) ctx.getBean("ReportsService");
        List<PhaseTaskDTO> associateData = null;
//         if(dataTo.getStatus()!=null){
//            list = dao.getSortedList(dataTo.getProjectcode(), null,dataTo.getSbu());
//        }else{
//        list = dao.getSortedList(dataTo.getProjectcode(), dataTo.getStatus(),dataTo.getSbu());
//        }

        associateData = dao.getPhaseTaskSortedList(dataTo);
//        System.out.println("sorted list length" + list.size());
//        mvc.addObject("list", list);
//        mvc.addObject("result", dataTo);
        ArrayList entireList = new ArrayList();

        for (int i = 0; i < associateData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + associateData.get(i).getCode()));
            rowDataList.add(new String("" + associateData.get(i).getName()));
            rowDataList.add(new String("" + associateData.get(i).getPhase()));
            rowDataList.add(new String("" + associateData.get(i).getTask()));
            rowDataList.add(new String("" + associateData.get(i).getSbu()));
            rowDataList.add(new String("" + associateData.get(i).getSubSbu()));
            rowDataList.add(new String("" + associateData.get(i).getProjectBillable()));
            rowDataList.add(new String("" + associateData.get(i).getStatus()));
            rowDataList.add(new String("" + associateData.get(i).getOwnership()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "PhaseTask_report.xls", "Phase Task", null);
        return null;
    }

    public ModelAndView getSubPracticeGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
        System.out.println("test>>>>>>>>>>>>>>>>>>");
        response.getWriter().println("<option value='All'>--Select--</option>");

        for (AssociateFilterDTO dTO : ((bo).getSubSbu((request.getParameter("id"))))) {
            System.out.println("----" + dTO);
            response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
        }
        return null;
    }
    public ModelAndView timesheethoursreport(HttpServletRequest request, HttpServletResponse response, ReportsFilterDTO filterData) throws IOException{
        System.out.println("TIMESHEET HOURS REPORT");
        ModelAndView mv = new ModelAndView("timesheetHours");
        HttpSession session = request.getSession();
        try{
            final WebApplicationContext ctx = getWebApplicationContext();
            ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
            String projectDept = "";
            String empId = (String)session.getAttribute("EMP_ID");
            filterData.setProjectManager(empId);
            if(Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_TS){
                projectDept = "5";
            }else if(Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_PES[0] || Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_PES[1]){
                projectDept = "2";
            }
            Map<String, String> timesheetMonthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> timesheetYearsMap = CommonMethods.getYearsList(6);
            Map<String, String> sbuMap = reportService.getSbuList();
            List<ReportsFilterDTO> projectTypeList = reportService.getPojectTypeList();
            List<ReportsDTO> pro = null;
            List<ReportsDTO> empList = null;
            AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
            List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
//            if (filterData.getSbuFilter() == null || filterData.getSbuFilter().equals("")) {
//
//                pro = reportService.getProjectList("","");
//            } else {
//
//                pro = reportService.getProjectList(filterData.getSbuFilter(),filterData.getSubSbu());
//            }
            pro = reportService.getProjectList(empId, projectDept, filterData.getProjectType());
            if(filterData.getNameFilter() == null || filterData.getNameFilter().equals("")){
                empList = reportService.getEmployeeList("");
            }else{
                System.out.println("projectId "+filterData.getNameFilter());
                System.out.println("employeeFilter "+filterData.getEmployeeNameFilter());
                empList = reportService.getEmployeeList(filterData.getNameFilter());
            }
            filterData.setYearAndMonth("");
            if((filterData.getYearFilter() != null || !"".equals(filterData.getYearFilter())) && (filterData.getMonthFilter() != null || !"".equals(filterData.getMonthFilter()))){
                filterData.setYearAndMonth(filterData.getYearFilter()+filterData.getMonthFilter());
                filterData.setMonthStartDate(filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-01");
                filterData.setMonthEndDate(filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-31");
            }else if(filterData.getYearFilter() == null || "".equals(filterData.getYearFilter())){
                filterData.setYearAndMonth(filterData.getMonthFilter());
                Calendar now = Calendar.getInstance(); 
                int year = now.get(Calendar.YEAR);
                filterData.setMonthStartDate(year+"-"+filterData.getMonthFilter()+"-01");
                filterData.setMonthEndDate(year+"-"+filterData.getMonthFilter()+"-31");
            }else if(filterData.getMonthFilter() == null || "".equals(filterData.getMonthFilter())){
                filterData.setYearAndMonth(filterData.getYearFilter());
                filterData.setMonthStartDate(filterData.getYearFilter()+"01-01");
                filterData.setMonthEndDate(filterData.getYearFilter()+"12-31");
            }
            System.out.println("startDate "+filterData.getMonthStartDate());
            System.out.println("endDate "+filterData.getMonthEndDate());
            int page;
            int timesheetHoursDataCount = 0;
            if(filterData.getSubmitButton() != null)
                page = 1;
            else
                page = Integer.parseInt(request.getParameter("page"));
            int rows = CommonConfigurations.pageCount;
            int start;
            start = ((page - 1) * rows);
            filterData.setStart_page(start);
            filterData.setEnd_page(rows);
            System.out.println("startPage "+filterData.getStart_page());
            System.out.println("endPage "+filterData.getEnd_page());
            int recordCount = 0;
            List<ReportsDataDTO> timesheetHoursData = reportService.getTimesheetHoursData(filterData);
            timesheetHoursDataCount = reportService.getTimesheetHoursDataCount(filterData);
            recordCount = timesheetHoursDataCount;
            int end = (start+rows)-1;
            int[] paging = CommonMethods.paging(recordCount, page, rows);
            mv.addObject("paging", paging);
            mv.addObject("sbuMap", sbuMap);
            mv.addObject("subSbu", subSbu);
            mv.addObject("projects", pro);
            mv.addObject("employeeList", empList);
            mv.addObject("timesheetMonthsMap", timesheetMonthsMap);
            mv.addObject("timesheetYearsMap", timesheetYearsMap);
            mv.addObject("projectTypes", projectTypeList);
            mv.addObject("filterData", filterData);
            mv.addObject("EMPID", empId);
            mv.addObject("projectDept", projectDept);
            mv.addObject("timesheetHoursData", timesheetHoursData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView timesheethoursexport(HttpServletRequest request, HttpServletResponse response, ReportsFilterDTO filterData) throws IOException{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        try{
            final WebApplicationContext ctx = getWebApplicationContext();
            ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
            String projectDept = "";
            String empId = (String)session.getAttribute("EMP_ID");
            filterData.setProjectManager(empId);
            if(Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_TS){
                projectDept = "5";
            }else if(Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_PES[0] || Integer.parseInt(empId) == CommonConfigurations.PMO_FOR_PES[1]){
                projectDept = "2";
            }
            filterData.setYearAndMonth("");
            if(filterData.getYearFilter() != null && filterData.getMonthFilter() != null){
                filterData.setYearAndMonth(filterData.getYearFilter()+filterData.getMonthFilter());
                filterData.setMonthStartDate(filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-01");
                filterData.setMonthEndDate(filterData.getYearFilter()+"-"+filterData.getMonthFilter()+"-31");
            }else if(filterData.getYearFilter() == null){
                filterData.setYearAndMonth(filterData.getMonthFilter());
                Calendar now = Calendar.getInstance(); 
                int year = now.get(Calendar.YEAR);
                filterData.setMonthStartDate(year+"-"+filterData.getMonthFilter()+"-01");
                filterData.setMonthEndDate(year+"-"+filterData.getMonthFilter()+"-31");
            }else if(filterData.getMonthFilter() == null){
                filterData.setYearAndMonth(filterData.getYearFilter());
                filterData.setMonthStartDate(filterData.getYearFilter()+"01-01");
                filterData.setMonthEndDate(filterData.getYearFilter()+"12-31");
            }
            System.out.println("startDate "+filterData.getMonthStartDate());
            System.out.println("endDate "+filterData.getMonthEndDate());
            List<ReportsDataDTO> timesheetHoursData = reportService.getTimesheetHoursData(filterData);
            ArrayList entireList = new ArrayList();
            if(timesheetHoursData != null){
                for (int i = 0; i < timesheetHoursData.size(); i++) {
                    ArrayList rowDataList = new ArrayList();
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getEmployeeNumber()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getEmployeeName()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getSbu()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getSubSbu()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getStartDate()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getEndDate()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getTotalWorkedHours()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getTotalApprovedHours()));
                    rowDataList.add(new String("" + timesheetHoursData.get(i).getTotalAccruedHours()));
                    entireList.add(rowDataList);
                }
            }
            CommonMethods.exportToExcel(response, entireList, "total_timesheet_hours.xls", "Timesheet Hours Report", null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView getempajax(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mvc = new ModelAndView("/ajax");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
        String id = request.getParameter("id");
        List<ReportsDTO> empList = reportService.getEmployeeList(id);
        mvc.addObject("employeeList", empList);
        return mvc;
    }
    
    public ModelAndView getprjajax(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mvc = new ModelAndView("/ajax");
        final WebApplicationContext ctx = getWebApplicationContext();
        ReportsServiceImpl reportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
        String empId = request.getParameter("empId");
        String projectDept = request.getParameter("projectDept");
        String projectType = request.getParameter("projectType");
        List<ReportsDTO> pro = reportService.getProjectList(empId,projectDept,projectType);
        mvc.addObject("projectList", pro);
        return mvc;
    }
    
    public ModelAndView getEmployeeExperienceReportXL(HttpServletRequest request, HttpServletResponse response, ReportEmployeeExperienceDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/employee_experience_report");
            final WebApplicationContext ctx = getWebApplicationContext();

            ReportsService employeeReportService = (ReportsServiceImpl) ctx.getBean("ReportsService");
            List<ReportEmployeeExperienceDTO> employeeReport;
            employeeReport = employeeReportService.getEmployeeExperienceReport(filterData);

            mvc.addObject("employeeReport", employeeReport);


            String excelFileName = "D:/delinquency/Employee_Experience_Report.xls";//name of excel file
            String sheetName = "employee_experience_report";//name of sheet
            File file = new File("D://delinquency//Employee_Experience_Report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employee Name", "Joined Date", "Org Unit", "Business Unit",
                "Practice", "Sub Practice", "Band", "Designation", "Career Start Date","Company Experience(Years)","company Experience(months)",
                "Total Experience(Years)","Total Experience(Months)","RM_Id", "RM_Name", "Category", "Employment Status", "Employee_MailID", "Resigned Date",
                "Last Working Date"};
            for (int i = 0; i < employeeReport.size(); i++) {
                String rowArray[] = new String[21];
                rowArray[0] = employeeReport.get(i).getEmployee_id();
                rowArray[1] = employeeReport.get(i).getEmployee_name();
                rowArray[2] = employeeReport.get(i).getJoined_date();
                rowArray[3] = employeeReport.get(i).getOrg_unit();
                rowArray[4] = employeeReport.get(i).getBusiness_unit();
                rowArray[5] = employeeReport.get(i).getPractice();
                rowArray[6] = employeeReport.get(i).getSub_practice();
                rowArray[7] = employeeReport.get(i).getBand();
                rowArray[8] = employeeReport.get(i).getDesignation();
                rowArray[9] = employeeReport.get(i).getCareer_start_date();
                rowArray[10] = employeeReport.get(i).getCompany_experience_years();
                rowArray[11] = employeeReport.get(i).getCompany_experience_month();
                rowArray[12] = employeeReport.get(i).getTotal_experience_years();
                rowArray[13] = employeeReport.get(i).getTotal_experience_month();
                rowArray[14] = employeeReport.get(i).getRm_Id();
                rowArray[15] = employeeReport.get(i).getRm_Name();
                rowArray[16] = employeeReport.get(i).getCategory();
                rowArray[17] = employeeReport.get(i).getEmployment_status();
                rowArray[18] = employeeReport.get(i).getEmployee_mailId();
                rowArray[19] = employeeReport.get(i).getResigned_date();
                rowArray[20] = employeeReport.get(i).getLast_working_date();
                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }


            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getEmployeeExperienceReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "Employee_Experience_Report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            // File myfile = new File(filepath + filename);
            File myfile = new File("D://delinquency//employee_experience_report.xls");
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

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }
}
