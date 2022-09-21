/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.TimesheetAttendanceReportDTO;
import com.defiance.ideal.reports.service.TimesheetAttendanceReportService;
import com.defiance.ideal.reports.service.TimesheetAttendanceReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16656
 */
public class TimesheetAttendanceReportController extends MultiActionController {

    public ModelAndView timesheetAttendanceReport(HttpServletRequest request, HttpServletResponse response, TimesheetAttendanceReportDTO dto) {
        int[] paging = null;
        ModelAndView mvc = new ModelAndView("timesheetAttendanceReport");
        System.out.println("Hello Attendance.....");
        HttpSession session = request.getSession();
//        String moduleId = (String) session.getAttribute("MODULE_ID");
        String loggedInempId = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        TimesheetAttendanceReportService service = (TimesheetAttendanceReportServiceImpl) ctx.getBean("TimesheetAttendanceReportService");
//        dto.setModuleId(moduleId);
        Date d = new Date();

        Date dtTo = new Date(d.getTime() - 1 * 24 * 3600 * 1000);
        Date dtFrom = new Date(d.getTime() - 7 * 24 * 3600 * 1000);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fromDate = dateFormat.format(dtFrom);
        String toDate = dateFormat.format(dtTo);

        if (dto.getFromDate() == null || dto.getFromDate().equals("")) {
            dto.setDisplayFromDate(fromDate);
        } else {
            dto.setDisplayFromDate(dto.getFromDate());
        }

        if (dto.getToDate() == null || dto.getToDate().equals("")) {
            dto.setDisplayToDate(toDate);
        } else {
            dto.setDisplayToDate(dto.getToDate());
        }
        if (dto.getDisplayFromDate() != null || !dto.getDisplayFromDate().equals("")) {
            String sFromDate = dto.getDisplayFromDate();
            dto.setFromDate(sFromDate.split("-")[2] + "-" + sFromDate.split("-")[1] + "-" + sFromDate.split("-")[0]);
        }
        if (dto.getDisplayToDate() != null || !dto.getDisplayToDate().equals("")) {
            String sToDate = dto.getDisplayToDate();
            dto.setToDate(sToDate.split("-")[2] + "-" + sToDate.split("-")[1] + "-" + sToDate.split("-")[0]);
        }

        if (dto.getEmployee_no() == null || dto.getEmployee_no().equals("")) {
            dto.setLoggedInempId(loggedInempId);
        } else {
            dto.setLoggedInempId(dto.getEmployee_no());
        }
        TimesheetAttendanceReportDTO employees = service.getEmployeeName(dto);
        List<TimesheetAttendanceReportDTO> tsAttendanceRprtList = service.getTimesheetAttendanceReport(dto);

        int page = 0;
//        int page = Integer.parseInt(request.getParameter("page"));
        String pg = request.getParameter("page");
        if (pg == null || pg.equals("")) {
            page = 1;
        } else {
            page = Integer.parseInt(pg);
        }
        int rows = 15;
        int start;
        int end;
        start = ((page - 1) * rows);
        end = start + rows;
        dto.setStart_page(Integer.toString(start));
        dto.setEnd_page(Integer.toString(rows));
        int recordCount = tsAttendanceRprtList.size();
        if (recordCount < end) {
            end = recordCount;
        }
        List<TimesheetAttendanceReportDTO> tsAttendanceRprtListTemp = null;
        tsAttendanceRprtListTemp = new ArrayList(tsAttendanceRprtList.subList(start, end));
//        for(int i=start;i<=end;i++){
//           tsAttendanceRprtListTemp tsAttendanceRprtList.get(i)
//        }
        boolean a = true;
        List<TimesheetAttendanceReportDTO> empRess = new ArrayList<TimesheetAttendanceReportDTO>();
        
        List<TimesheetAttendanceReportDTO> filter_employees = employeeRecursive(loggedInempId, empRess, a);
        int temp = 0;
        for (int i = 0; i < filter_employees.size(); i++) {
            TimesheetAttendanceReportDTO temp_dto = new TimesheetAttendanceReportDTO();
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(filter_employees.get(i).getEmployee_no()) == Integer.parseInt(filter_employees.get(j).getEmployee_no()) && i!=j) {
                    temp = 1;
                }
            }
            if(temp==0){
                temp_dto.setEmployee_name(filter_employees.get(i).getEmployee_name());
                temp_dto.setEmployee_no(filter_employees.get(i).getEmployee_no());
                empRess.add(temp_dto);
                temp = 0;
            }
        }

        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
        mvc.addObject("timesheetAttendanceReport", tsAttendanceRprtListTemp);
        mvc.addObject("employee", employees);
        mvc.addObject("filterData", dto);
        mvc.addObject("paging", pageNo);
        mvc.addObject("filter_employees", empRess);
        return mvc;
    }

    public List<TimesheetAttendanceReportDTO> employeeRecursive(String employee_id, List<TimesheetAttendanceReportDTO> empRes, boolean a) {
        List<TimesheetAttendanceReportDTO> empRess = null;
        if (a) {
            empRes = null;
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        TimesheetAttendanceReportService service = (TimesheetAttendanceReportServiceImpl) ctx.getBean("TimesheetAttendanceReportService");
        empRess = service.getEmployeesList(employee_id);
        if (empRess == null || empRess.equals("")) {
            empRes = empRes;
        } else if (a) {
            empRes = empRess;
        } else {
            empRes.addAll(empRess);
        }
        for (int i = 0; i < empRess.size(); i++) {
            empRes = employeeRecursive(empRess.get(i).getEmployee_no(), empRes, false);
        }
        return empRes;
    }

    public ModelAndView gettimesheetAttendanceReportXL(HttpServletRequest request, HttpServletResponse response, TimesheetAttendanceReportDTO dto) throws Exception {

        HttpSession session = request.getSession();
        String loggedInempId = (String) session.getAttribute("EMP_ID");

        final WebApplicationContext ctx = getWebApplicationContext();
        TimesheetAttendanceReportService service = (TimesheetAttendanceReportServiceImpl) ctx.getBean("TimesheetAttendanceReportService");

        Date d = new Date();

        Date dtTo = new Date(d.getTime() - 1 * 24 * 3600 * 1000);
        Date dtFrom = new Date(d.getTime() - 7 * 24 * 3600 * 1000);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fromDate = dateFormat.format(dtFrom);
        String toDate = dateFormat.format(dtTo);

        if (dto.getFromDate() == null || dto.getFromDate().equals("")) {
            dto.setDisplayFromDate(fromDate);
        } else {
            dto.setDisplayFromDate(dto.getFromDate());
        }

        if (dto.getToDate() == null || dto.getToDate().equals("")) {
            dto.setDisplayToDate(toDate);
        } else {
            dto.setDisplayToDate(dto.getToDate());
        }
        if (dto.getDisplayFromDate() != null || !dto.getDisplayFromDate().equals("")) {
            String sFromDate = dto.getDisplayFromDate();
            dto.setFromDate(sFromDate.split("-")[2] + "-" + sFromDate.split("-")[1] + "-" + sFromDate.split("-")[0]);
        }
        if (dto.getDisplayToDate() != null || !dto.getDisplayToDate().equals("")) {
            String sToDate = dto.getDisplayToDate();
            dto.setToDate(sToDate.split("-")[2] + "-" + sToDate.split("-")[1] + "-" + sToDate.split("-")[0]);
        }

        if (dto.getEmployee_no() == null || dto.getEmployee_no().equals("")) {
            dto.setLoggedInempId(loggedInempId);
        } else {
            dto.setLoggedInempId(dto.getEmployee_no());
        }

        List<TimesheetAttendanceReportDTO> tsAttendanceRprtList = service.getTimesheetAttendanceReport(dto);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < tsAttendanceRprtList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getEmployee_name()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getManager_name()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getTimesheet_Date()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getDay_Name()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getFirst_In()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getLast_Out()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getAttendance_Hours()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getAttendance_status()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getProject_Name()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getWorked_Hours()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getRegularization_Reason()));
            rowDataList.add(new String("" + tsAttendanceRprtList.get(i).getRemarks()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "Timesheet_Attendance_Report.xls", "Timesheet_Attendance_Report", null);
        return null;
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
}
