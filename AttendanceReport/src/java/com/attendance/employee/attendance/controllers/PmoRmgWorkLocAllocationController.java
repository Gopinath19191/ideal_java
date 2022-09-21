/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.controllers;

import com.attendance.employee.attendance.dto.CalendarDTO;
import com.attendance.employee.attendance.dto.PmoRmgWrkLocAllocationDto;
import com.attendance.employee.attendance.dto.ReportingListDTO;
import com.attendance.employee.attendance.dto.SearchDTO;
import com.attendance.employee.attendance.service.PmoRmgWrkLocAllocationServiceImpl;
import com.attendance.employee.util.CommonConfigurations;
import com.attendance.employee.util.CommonMethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16365
 */
public class PmoRmgWorkLocAllocationController extends MultiActionController {

    private ModelAndView mv;
    Properties configFile = new Properties();
        
    //To display employee list
    public ModelAndView employeeList(HttpServletRequest request, HttpServletResponse response, PmoRmgWrkLocAllocationDto dto) throws Exception {
        configFile.load(new FileInputStream(CommonConfigurations.ExternalModuleId));
        final WebApplicationContext ctx = getWebApplicationContext();
        PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
        HttpSession session = request.getSession();
        SearchDTO searchObj = new SearchDTO();
        List<ReportingListDTO> reportingList = new ArrayList();
        String empTemp = request.getParameter("employeeId");
        if (session.getAttribute("MODULE_ID") == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            if (session.getAttribute("MODULE_ID").equals(configFile.getProperty("AttendanceReport_RM"))) {
                //dto.setFlag("RM");
                //dto.setManagerId((String) session.getAttribute("EMP_ID"));
                searchObj.setLoginEmpId((String) session.getAttribute("EMP_ID"));
                reportingList = service.getReportingList(searchObj);
                String temp = "";
                for (int i = 0; i < reportingList.size(); i++) {
                    temp = temp + reportingList.get(i).getEid();
                    if (i < reportingList.size() - 1) {
                        temp = temp + ",";
                    }
                }
                if (empTemp == null || empTemp == "") {
                    dto.setEmployeeId(temp);
                } else {
                    dto.setEmployeeId(empTemp);
                }
            } else {
                // dto.setFlag("Admin");
            }
        }

        List<PmoRmgWrkLocAllocationDto> empList = service.getEmployeeList(dto);
        List<PmoRmgWrkLocAllocationDto> baseLocationList = service.getBaseLocationList();
        List<PmoRmgWrkLocAllocationDto> workLocationList = service.getWorkLocationList();
        List<PmoRmgWrkLocAllocationDto> cusWorkLocationList = service.getCustomerLocationList(dto);
        List<PmoRmgWrkLocAllocationDto> cusList = service.getCustomerList();
        List<PmoRmgWrkLocAllocationDto> HtlBaseLocationList = service.getHTLBaseLocationList();
        mv = new ModelAndView("/employeeList");
        mv.addObject("empList", empList);
        mv.addObject("filterData", dto);
        mv.addObject("baseLocationList", baseLocationList);
        mv.addObject("workLocationList", workLocationList);
        mv.addObject("cusWorkLocationList", cusWorkLocationList);
        mv.addObject("HtlBaseLocationList", HtlBaseLocationList);
        mv.addObject("cusList", cusList);
        return mv;
    }

    //Search employee
    public ModelAndView getCusAddress(HttpServletRequest request, HttpServletResponse response, PmoRmgWrkLocAllocationDto filterData) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
        response.getWriter().println("<p>Customer Address :</p>");
        response.getWriter().println("<ul>");

        for (PmoRmgWrkLocAllocationDto dTO : (service.getCustomerLocationList(filterData))) {
            response.getWriter().println("<li><input name = 'add' class = 'addr' onclick = 'getCustomerCalendarName(this.value)' type= 'radio' value='" + dTO.getCusWrkLocId() + "'/>" + dTO.getCusWrkLocAddress() + "</li>");
        }
        response.getWriter().println("</ul>");
        // mv = new ModelAndView("com/employeeList");
        return null;
    }

    public ModelAndView employeeSearch(HttpServletRequest request, HttpServletResponse response, PmoRmgWrkLocAllocationDto filterData) throws IOException {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch");
        HttpSession session = request.getSession();        
        configFile.load(new FileInputStream(CommonConfigurations.ExternalModuleId));
        List<ReportingListDTO> reportingList = new ArrayList();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
            String empVal = request.getParameter("q");
//            String modId = (String) session.getAttribute("MODULE_ID");            
            SearchDTO searchObj = new SearchDTO();
            searchObj.setEmployeeSearch(empVal);
            if (session.getAttribute("MODULE_ID") == null) {
                mv = new ModelAndView("/unauthorised");
            } else {
                if (session.getAttribute("MODULE_ID").equals(configFile.getProperty("AttendanceReport_RM"))) {
                    //dto.setManagerId((String) session.getAttribute("EMP_ID"));
                    searchObj.setLoginEmpId((String) session.getAttribute("EMP_ID"));
                    reportingList = service.getReportingList(searchObj);
                    String temp = "";
                    for (int i = 0; i < reportingList.size(); i++) {
                        temp = temp + reportingList.get(i).getEid();
                        if (i < reportingList.size() - 1) {
                            temp = temp + ",";
                        }
                    }
                    searchObj.setManagerId(temp);
                }
            }

            List<PmoRmgWrkLocAllocationDto> empRes = service.getSearchEmployeeList(searchObj);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    //To insert in Location & maintain history
    public ModelAndView insertLocation(HttpServletRequest request, HttpServletResponse response, PmoRmgWrkLocAllocationDto filterData) throws ParseException, FileNotFoundException, IOException {

        String succcessMsg = "";        
        configFile.load(new FileInputStream(CommonConfigurations.ExternalModuleId));
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
        PmoRmgWrkLocAllocationDto singleEmpDetails = null;
        CalendarDTO dto = new CalendarDTO();
        int flag = 0;
        if (request.getParameter("calendarName") == null) {
            succcessMsg = "Please check the Data";
            request.setAttribute("succcessMsg", succcessMsg);
        } else {
            for (int i = 0; i < filterData.getEmpLocChangeList().length; i++) {
                filterData.setEmployeeId(filterData.getEmpLocChangeList()[i]);
                singleEmpDetails = service.getSingleEmpDetails(filterData);
                if (request.getParameter("locationType").equals("base location")) {
                    filterData.setLocationTable("company_locations");
                    filterData.setEmployeeWrkLocId(request.getParameter("htlBaseLocationId"));
                    filterData.setCalendar_id(request.getParameter("calendarName"));
                    dto.setCustomer_id(request.getParameter("calendarName"));
                    String availableHrs = (String) service.getAvailableHrs(dto);
                    filterData.setAvailable_hours(availableHrs);
                } else if (request.getParameter("locationType").equals("customer location")) {
                    filterData.setLocationTable("customer_work_locations");
                    filterData.setEmployeeWrkLocId(request.getParameter("add"));
                    filterData.setCalendar_id(request.getParameter("calendarName"));
                    dto.setCustomer_id(request.getParameter("calendarName"));
                    String availableHrs = (String) service.getAvailableHrs(dto);
                    filterData.setAvailable_hours(availableHrs);
                }
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat1.parse(request.getParameter("effectiveFrom"));
                String dateModified = dateFormat.format(date);
                filterData.setEffectiveFrom(dateModified);
                if (request.getParameter("effectiveFrom").equals("")) {
                    Date d = new Date();
                    filterData.setEffectiveFrom(dateFormat.format(d));
                }
                if (session.getAttribute("EMP_ID") == null) {
                    mv = new ModelAndView("/unauthorised");
                } else {
                    filterData.setCreated_by((String) session.getAttribute("EMP_ID"));
                }
                service.insertLocation(filterData);

                if (singleEmpDetails != null) {
                    Date d = new Date(date.getTime() - 1 * 24 * 3600 * 1000);
                    filterData.setEmployeeOldWrkLocId(singleEmpDetails.getEmployeeOldWrkLocId());
                    filterData.setLocationTable(singleEmpDetails.getLocationTable());
                    filterData.setEmpId((String) session.getAttribute("EMP_ID"));
                    filterData.setFromDate(singleEmpDetails.getOldEffectiveFrom());
                    filterData.setAvailable_hours(singleEmpDetails.getOldAvailableHours());
                    filterData.setCalendar_id(singleEmpDetails.getOldCalendar_id());
                    filterData.setToDate(dateFormat.format(d));
                    filterData.setCreated_by((String) session.getAttribute("EMP_ID"));
                    service.insertHistoryLocation(filterData);
                } else {
                }
                flag++;
            }

            if (flag == filterData.getEmpLocChangeList().length) {
                filterData = new PmoRmgWrkLocAllocationDto();
            }
            succcessMsg = "Data updated successfully";
            request.setAttribute("succcessMsg", succcessMsg);
        }
        SearchDTO searchObj = new SearchDTO();
        List<ReportingListDTO> reportingList = new ArrayList();
        if (session.getAttribute("MODULE_ID") == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            if (session.getAttribute("MODULE_ID").equals(configFile.getProperty("AttendanceReport_RM"))) {
                //filterData.setFlag("RM");
                //dto.setManagerId((String) session.getAttribute("EMP_ID"));
                searchObj.setLoginEmpId((String) session.getAttribute("EMP_ID"));
                reportingList = service.getReportingList(searchObj);
            } else {
                // filterData.setFlag("Admin");
            }
        }
        String temp = "";
        for (int i = 0; i < reportingList.size(); i++) {
            temp = temp + reportingList.get(i).getEid();
            if (i < reportingList.size() - 1) {
                temp = temp + ",";
            }
        }
        filterData.setEmployeeId(temp);
        List<PmoRmgWrkLocAllocationDto> empList = service.getEmployeeList(filterData);
        List<PmoRmgWrkLocAllocationDto> baseLocationList = service.getBaseLocationList();
        List<PmoRmgWrkLocAllocationDto> workLocationList = service.getWorkLocationList();
        List<PmoRmgWrkLocAllocationDto> cusWorkLocationList = service.getCustomerLocationList(filterData);
        List<PmoRmgWrkLocAllocationDto> cusList = service.getCustomerList();
        List<PmoRmgWrkLocAllocationDto> HtlBaseLocationList = service.getHTLBaseLocationList();
        mv = new ModelAndView("/employeeList");
        mv.addObject("empList", empList);
        mv.addObject("baseLocationList", baseLocationList);
        mv.addObject("workLocationList", workLocationList);
        mv.addObject("cusWorkLocationList", cusWorkLocationList);
        mv.addObject("HtlBaseLocationList", HtlBaseLocationList);
        mv.addObject("cusList", cusList);
        return mv;
    }
    //Export

    public ModelAndView exportExcel(HttpServletRequest request, HttpServletResponse response, PmoRmgWrkLocAllocationDto filterData) throws Exception {
        List<PmoRmgWrkLocAllocationDto> empDetails = new ArrayList();
        final WebApplicationContext ctx = getWebApplicationContext();
        PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
        
        SearchDTO searchObj = new SearchDTO();
        HttpSession session = request.getSession();
        
        configFile.load(new FileInputStream(CommonConfigurations.ExternalModuleId));
        List<ReportingListDTO> reportingList = new ArrayList();
        String empTemp = request.getParameter("employeeId");
        if (session.getAttribute("MODULE_ID") == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            if (session.getAttribute("MODULE_ID").equals(configFile.getProperty("AttendanceReport_RM"))) {
                //dto.setFlag("RM");
                //dto.setManagerId((String) session.getAttribute("EMP_ID"));
                searchObj.setLoginEmpId((String) session.getAttribute("EMP_ID"));
                reportingList = service.getReportingList(searchObj);
                String temp = "";
                for (int i = 0; i < reportingList.size(); i++) {
                    temp = temp + reportingList.get(i).getEid();
                    if (i < reportingList.size() - 1) {
                        temp = temp + ",";
                    }
                }
                if (empTemp == null || empTemp == "") {
                    filterData.setEmployeeId(temp);
                } else {
                    filterData.setEmployeeId(empTemp);
                }
            } else {
                // dto.setFlag("Admin");
            }
        }
        List<PmoRmgWrkLocAllocationDto> empList = service.getEmployeeList(filterData);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < empList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + empList.get(i).getEmpName()));
            rowDataList.add(new String("" + empList.get(i).getReportingManager()));
            rowDataList.add(new String("" + empList.get(i).getBaseLocation()));
            rowDataList.add(new String("" + empList.get(i).getWorkLocation()));
            rowDataList.add(new String("" + empList.get(i).getEffectiveFrom()));
            rowDataList.add(new String("" + empList.get(i).getAvailable_hours()));
            rowDataList.add(new String("" + empList.get(i).getCalendarName()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "workAllocation_report.xls", "Work Allocation Report", null);
        return mv;
    }

    public ModelAndView getCalendarNameList(HttpServletRequest request, HttpServletResponse response, CalendarDTO searchObj) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        PmoRmgWrkLocAllocationServiceImpl service = (PmoRmgWrkLocAllocationServiceImpl) ctx.getBean("PmoRmgWrkLocAllocationService");
        if (request.getParameter("is_customer").equals("base location")) {
            searchObj.setIs_customer("0");            
        } else {            
            searchObj.setIs_customer("1");
        }
        List<CalendarDTO> dTo = service.getCalendarNameList(searchObj);
        if (dTo.size()>0) {
//            response.getWriter().println("<option value='" + dTO.getCalendar_id() + "'>" + dTO.getCalendar_name() + "</option>");
            response.getWriter().println(dTo.get(0).getCalendar_name());
            response.getWriter().println("<input type='hidden' value = '" + dTo.get(0).getCalendar_name() + "' id = 'calName'/>");
            response.getWriter().println("<input type='hidden' value = '" + dTo.get(0).getCalendar_id() + "' name = 'calendarName' id = 'calId'/>");
        } else {
            response.getWriter().println("<input type='hidden' value = '' id = 'calName'/>");
        }

        return null;
    }
}
