/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.controllers;

import com.attendance.employee.attendance.dao.AttendanceDao;
import com.attendance.employee.attendance.dto.AttendanceDto;
import com.attendance.employee.attendance.dto.AttendancePmProjectReport;
import com.attendance.employee.attendance.dto.AttendancePmReportFilterDto;
import com.attendance.employee.attendance.dto.AttendanceReportDto;
import com.attendance.employee.attendance.dto.AttendanceReportFilterDto;
import com.attendance.employee.attendance.dto.AttendanceReportResult;
import com.attendance.employee.attendance.dto.EmpListDTO;
//import com.attendance.employee.attendance.dto.ProjectListDTO;
import com.attendance.employee.attendance.service.AttendanceServiceImpl;
import com.attendance.employee.util.CommonConfigurations;
import com.attendance.employee.util.CommonMethods;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16047
 */
public class AttendanceController extends MultiActionController {

    public ModelAndView getAttendanceDetails(HttpServletRequest request, HttpServletResponse response, AttendanceReportFilterDto filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String moduleId = (String) session.getAttribute("MODULE_ID");
        String loggedInempId = (String) session.getAttribute("EMP_ID");
        int rows = CommonConfigurations.pageCount;

        int[] paging = null;
        List<AttendanceDto> unitList = null;
        List<AttendanceDto> locationList = null;
        List<AttendanceReportResult> attendanceList = null;
        List<AttendanceDto> directReporteeList = null;
        List<AttendancePmReportFilterDto> pmprojectAttReport = null;
        List<EmpListDTO> empListAtt = null;
        //
        String empName = "";

        if (moduleId == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            try {
                final WebApplicationContext ctx = getWebApplicationContext();
                AttendanceServiceImpl attendanceService = (AttendanceServiceImpl) ctx.getBean("AttendanceService");

                filterData.setModuleId(moduleId);

                if (moduleId.equals("662")) {
                    mv = new ModelAndView("/empView");
                    rows = 7;
                    filterData.setLoggedInempId(loggedInempId);
                    filterData.setRole(AttendanceReportFilterDto.Role.EMP);
                } else {
                    unitList = attendanceService.getUnitList();
                    locationList = attendanceService.getLocationList();
                    empName = attendanceService.getEmployeeName(filterData.getEmpId());

                    if (moduleId.equals("663")) {
                        mv = new ModelAndView("/rmView");
                        filterData.setLoggedInempId(loggedInempId);
                        filterData.setRole(AttendanceReportFilterDto.Role.RM);
                        directReporteeList = attendanceService.getDirectReportees(filterData.getLoggedInempId());
                    } else if (moduleId.equals("664")) {
                        mv = new ModelAndView("/adminView");
                        filterData.setRole(AttendanceReportFilterDto.Role.ADMIN);
                    }
                }
                if (filterData.getFromDate() == null || filterData.getFromDate().equals("")) {
                    Date d = new Date();
                    //
                    Date dtTo = new Date(d.getTime() - 1 * 24 * 3600 * 1000);
                    Date dtFrom = new Date(d.getTime() - 7 * 24 * 3600 * 1000);
                    //
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String fromDate = dateFormat.format(dtFrom);
                    String toDate = dateFormat.format(dtTo);

                    filterData.setDisplayFromDate(fromDate);
                    filterData.setDisplayToDate(toDate);
                } else {
                    filterData.setDisplayFromDate(filterData.getFromDate());
                    filterData.setDisplayToDate(filterData.getToDate());
                }
                if (filterData.getDisplayFromDate() != null && !filterData.getDisplayFromDate().equals("")) {
                    String sFromDate = filterData.getDisplayFromDate();
                    filterData.setFromDate(sFromDate.split("-")[2] + "-" + sFromDate.split("-")[1] + "-" + sFromDate.split("-")[0]);
                }
                if (filterData.getDisplayToDate() != null && !filterData.getDisplayToDate().equals("")) {
                    String sToDate = filterData.getDisplayToDate();
                    filterData.setToDate(sToDate.split("-")[2] + "-" + sToDate.split("-")[1] + "-" + sToDate.split("-")[0]);
                }

                if (filterData.getUnit() != null && filterData.getUnit().equals("All")) {
                    filterData.setUnit(null);
                }

                if (filterData.getLocation() != null && filterData.getLocation().equals("All")) {
                    filterData.setLocation(null);
                }
                if (filterData.getEmpId() != null && filterData.getEmpId().equals("")) {
                    filterData.setEmpId(null);
                }
                if (filterData.getRmId() != null && filterData.getRmId().equals("")) {
                    filterData.setRmId(null);
                }

                int page = Integer.parseInt(request.getParameter("page"));

                int iRecordCount = filterData.getTotalRecords();
                if (iRecordCount == 0) {
                    iRecordCount = attendanceService.getAttendanceReportCount(filterData);
                    filterData.setTotalRecords(iRecordCount);
                }

                if (iRecordCount > 0) {
                    int start = 0, end = 0;
                    start = ((page - 1) * rows) + 1;
                    end = (start + rows) - 1;
                    if (end > iRecordCount) {
                        end = iRecordCount;
                    }
                    //
                    filterData.setStartIndex(start);
                    filterData.setEndIndex(end);

                    paging = CommonMethods.paging(iRecordCount, page, rows);

                    List<AttendanceReportDto> attendanceRptList = attendanceService.getAttendanceReport(filterData);
                    if (attendanceRptList != null && attendanceRptList.size() > 0) {
                        attendanceList = ConvertReportToResult(attendanceRptList);
                    }
                }

                if (attendanceList == null || attendanceList.isEmpty()) {
                    mv.addObject("dataDispaly", "0");
                }

                mv.addObject("paging", paging);
                mv.addObject("filterData", filterData);
                mv.addObject("url", "getAttendanceDetails.htm");
                mv.addObject("unitList", unitList);
                mv.addObject("locationList", locationList);
                mv.addObject("directReporteeList", directReporteeList);
                mv.addObject("attendanceList", attendanceList);
                mv.addObject("rmId", filterData.getRmId());
                mv.addObject("empName", empName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response, AttendanceDto filterData) {
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            AttendanceServiceImpl attendanceService = (AttendanceServiceImpl) ctx.getBean("AttendanceService");
            String empVal = request.getParameter("q");
            System.out.println("ajax search----" + empVal);
            String moduleId = (String) session.getAttribute("MODULE_ID");
            List<AttendanceDto> empRes = attendanceService.getSearchList(empVal, moduleId);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView exportAttendanceDetails(HttpServletRequest request, HttpServletResponse response, AttendanceReportFilterDto filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String moduleId = (String) session.getAttribute("MODULE_ID");
        String loggedInempId = (String) session.getAttribute("EMP_ID");

        List<AttendanceReportResult> attendanceList = null;

        if (moduleId == null) {
            mv = new ModelAndView("/unauthorised");
        } else {
            try {
                final WebApplicationContext ctx = getWebApplicationContext();
                AttendanceServiceImpl attendanceService = (AttendanceServiceImpl) ctx.getBean("AttendanceService");

                filterData.setModuleId(moduleId);

                if (moduleId.equals("662")) {
                    mv = new ModelAndView("/empView");
                    filterData.setLoggedInempId(loggedInempId);
                    filterData.setRole(AttendanceReportFilterDto.Role.EMP);
                } else {
                    //
                    if (moduleId.equals("663")) {
                        mv = new ModelAndView("/rmView");
                        filterData.setLoggedInempId(loggedInempId);
                        filterData.setRole(AttendanceReportFilterDto.Role.RM);
                    } else if (moduleId.equals("664")) {
                        mv = new ModelAndView("/adminView");
                        filterData.setRole(AttendanceReportFilterDto.Role.ADMIN);
                    }
                }
                if (filterData.getFromDate() == null || filterData.getFromDate().equals("")) {
                    Date d = new Date();
                    //
                    Date dtTo = new Date(d.getTime() - 1 * 24 * 3600 * 1000);
                    Date dtFrom = new Date(d.getTime() - 7 * 24 * 3600 * 1000);
                    //
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String fromDate = dateFormat.format(dtFrom);
                    String toDate = dateFormat.format(dtTo);

                    filterData.setDisplayFromDate(fromDate);
                    filterData.setDisplayToDate(toDate);
                } else {
                    filterData.setDisplayFromDate(filterData.getFromDate());
                    filterData.setDisplayToDate(filterData.getToDate());
                }
                if (filterData.getDisplayFromDate() != null && !filterData.getDisplayFromDate().equals("")) {
                    String sFromDate = filterData.getDisplayFromDate();
                    filterData.setFromDate(sFromDate.split("-")[2] + "-" + sFromDate.split("-")[1] + "-" + sFromDate.split("-")[0]);
                }
                if (filterData.getDisplayToDate() != null && !filterData.getDisplayToDate().equals("")) {
                    String sToDate = filterData.getDisplayToDate();
                    filterData.setToDate(sToDate.split("-")[2] + "-" + sToDate.split("-")[1] + "-" + sToDate.split("-")[0]);
                }

                if (filterData.getUnit() != null && filterData.getUnit().equals("All")) {
                    filterData.setUnit(null);
                }

                if (filterData.getLocation() != null && filterData.getLocation().equals("All")) {
                    filterData.setLocation(null);
                }
                if (filterData.getEmpId() != null && filterData.getEmpId().equals("")) {
                    filterData.setEmpId(null);
                }
                if (filterData.getRmId() != null && filterData.getRmId().equals("")) {
                    filterData.setRmId(null);
                }

                List<AttendanceReportDto> attendanceRptList = attendanceService.getAttendanceReport(filterData);
                if (attendanceRptList != null && attendanceRptList.size() > 0) {
                    attendanceList = ConvertReportToResult(attendanceRptList);

                    ArrayList entireList = new ArrayList();
                    for (int i = 0; i < attendanceList.size(); i++) {
                        ArrayList rowDataList = new ArrayList();
                        rowDataList.add(new String("" + attendanceList.get(i).getEmployeeNumber()));
                        rowDataList.add(new String("" + attendanceList.get(i).getEmployeeName()));
                        rowDataList.add(new String("" + attendanceList.get(i).getReportingManager()));
                        rowDataList.add(new String("" + attendanceList.get(i).getUnit()));
                        rowDataList.add(new String("" + attendanceList.get(i).getAttendanceDate()));
                        rowDataList.add(new String("" + attendanceList.get(i).getStatus()));
                        rowDataList.add(new String("" + attendanceList.get(i).getInTime()));
                        if (attendanceList.get(i).getCrossOverDate() != null && !attendanceList.get(i).getCrossOverDate().equals("")) {
                            rowDataList.add(new String("" + attendanceList.get(i).getOutTime()) + " [" + attendanceList.get(i).getCrossOverDate() + "]");
                        } else {
                            rowDataList.add(new String("" + attendanceList.get(i).getOutTime()));
                        }
                        rowDataList.add(new String("" + attendanceList.get(i).getTimeDuration()));
                        rowDataList.add(new String("" + attendanceList.get(i).getLocation()));
                        rowDataList.add(new String("" + attendanceList.get(i).getWorklocation()));
                        entireList.add(rowDataList);
                    }
                    CommonMethods.exportToExcel(response, entireList, "attendance_report.xls", "Attendance Report", null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    public ModelAndView success(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/success");
        try {
            mv.addObject("successMsg", request.getParameter("success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    private List<AttendanceReportResult> ConvertReportToResult(List<AttendanceReportDto> lstAttendanceReport) {
        List<AttendanceReportResult> lstAttendanceResult = new ArrayList<AttendanceReportResult>();

        for (int i = 0; i < lstAttendanceReport.size(); i++) {
            AttendanceReportResult oAttendance = new AttendanceReportResult();
            oAttendance.setEmployeeNumber(lstAttendanceReport.get(i).getEmployeeNumber());
            oAttendance.setEmployeeName(lstAttendanceReport.get(i).getEmpName());
            oAttendance.setAttendanceDate(lstAttendanceReport.get(i).getAttendanceDate());
            oAttendance.setReportingManager(lstAttendanceReport.get(i).getRM_Name());
            oAttendance.setStatus(lstAttendanceReport.get(i).getAttendance_status());
            //
            if (lstAttendanceReport.get(i).getFirstIn() != null) {
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                String sInTime = formatter.format(lstAttendanceReport.get(i).getFirstIn());
                oAttendance.setInTime(sInTime);
            }
            //
            if (lstAttendanceReport.get(i).getLastOut() != null) {
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String sOutTime = formatter.format(lstAttendanceReport.get(i).getLastOut());
                String sOutDate = sOutTime.split(" ")[0];
                //
                oAttendance.setOutTime(sOutTime.split(" ")[1]);
                if (!oAttendance.getAttendanceDate().equals(sOutDate)) {
                    oAttendance.setCrossOverDate(sOutDate);
                }
            }
            //
            oAttendance.setTimeDuration(lstAttendanceReport.get(i).getTimeDuration());
            oAttendance.setUnit(lstAttendanceReport.get(i).getUnit());
            oAttendance.setLocation(lstAttendanceReport.get(i).getLocation());
            oAttendance.setWorklocation(lstAttendanceReport.get(i).getWorklocation());
            lstAttendanceResult.add(oAttendance);
        }

        return lstAttendanceResult;
    }
//    Added for PM Project Attendance

    public ModelAndView getPmTeamAttendance(HttpServletRequest request, HttpServletResponse response, AttendancePmReportFilterDto filter) throws Exception {
        String fromDate = null;
        String toDate = null;
        String projectName = null;
        HttpSession session = request.getSession(true);
        ModelAndView mv = new ModelAndView("/pm_attendance");
        String moduleId = (String) session.getAttribute("MODULE_ID");
        String employeeId = (String) session.getAttribute("EMP_ID");
        filter.setEmployeeId(employeeId);
        final WebApplicationContext ctx = getWebApplicationContext();
        AttendanceServiceImpl attendanceService = (AttendanceServiceImpl) ctx.getBean("AttendanceService");
        EmpListDTO empList = new EmpListDTO();
        List<AttendancePmProjectReport> projectList = null;
        List<EmpListDTO> employeeList = null;
        projectName = request.getParameter("projectName");
        fromDate = request.getParameter("fromDate");
        toDate = request.getParameter("toDate");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat newformatter = new SimpleDateFormat("yyyy-MM-dd");
        String aFrom = "";
        String aTo = "";
        try {
            if ((fromDate != null && toDate != null) && (fromDate != "" && toDate != "")) {

                Date dateFrom = (Date) formatter.parse(fromDate);
                Date dateTo = (Date) formatter.parse(toDate);
                aFrom = newformatter.format(dateFrom);
                aTo = newformatter.format(dateTo);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        filter.setFromDate(aFrom);
        filter.setToDate(aTo);

        int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        filter.setStart_page(start);
        filter.setEnd_page(rows);
        projectList = attendanceService.getProjectList(filter);
        empList.setProjectId(filter.getProjectName());
        employeeList = attendanceService.getEmployeeList(empList);
        filter.setProject_id(filter.getProjectName());
        filter.setEmpId(filter.getEmpName());

        List<AttendancePmProjectReport> attPmReport = attendanceService.getPmProjectAttendanceReport(filter);
        String recordCount = attendanceService.recordCount(filter);
        mv.addObject("paging", paging(rows, recordCount, page));
        mv.addObject("attPmReport", attPmReport);
        mv.addObject("projectList", projectList);
        mv.addObject("requestorDTO", filter);
        mv.addObject("employeeList", employeeList);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);

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

    public synchronized ModelAndView getEmployeeList(HttpServletRequest request, HttpServletResponse response, EmpListDTO empListDTO) throws Exception {
        try {

            HttpSession session = request.getSession();
            String projectId = request.getParameter("prjId");
            //  String listId = request.getParameter("list");
            String listId = request.getParameter("selectedList");
            String employeeId = (String) session.getAttribute("accessId");
            empListDTO.setEmployeeId(employeeId);
            empListDTO.setProjectId(projectId);
            empListDTO.setApproveType(listId);
            response.getWriter().println("<option value=''>-   All Employees  -</option>");
            if (projectId != "") {
                for (EmpListDTO dTO : this.getEmployeeList(empListDTO)) {
                    if (dTO.getEmpId() != null) {
                        response.getWriter().println("<option title = '" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "' value='" + dTO.getEmpId() + "'>" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "</option>");
                        System.out.println("test  " + dTO.getEmployeeNumber());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized List<EmpListDTO> getEmployeeList(EmpListDTO empListDTO) {
        List<EmpListDTO> employeeList = ((AttendanceServiceImpl) getWebApplicationContext().getBean("AttendanceService")).getEmployeeList(empListDTO);
        return employeeList;
    }

    public synchronized ModelAndView exportPMAttendanceDetails(HttpServletRequest request, HttpServletResponse response, AttendancePmReportFilterDto filter) throws Exception {
         String fromDate = null;
        String toDate = null;
        String projectName = null; 
        HttpSession session = request.getSession();
          String employeeId = (String) session.getAttribute("EMP_ID");
        filter.setEmployeeId(employeeId);
        ModelAndView mv = new ModelAndView("/pm_attendance"); 
        final WebApplicationContext ctx = getWebApplicationContext();
        AttendanceServiceImpl attendanceService = (AttendanceServiceImpl) ctx.getBean("AttendanceService");
        EmpListDTO empList = new EmpListDTO();
        List<AttendancePmProjectReport> projectList = null;
        List<EmpListDTO> employeeList = null;
        projectName = request.getParameter("projectName");
        fromDate = request.getParameter("fromDate");
        toDate = request.getParameter("toDate");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat newformatter = new SimpleDateFormat("yyyy-MM-dd");
        String aFrom = "";
        String aTo = "";
        try {
            if ((fromDate != null && toDate != null) && (fromDate != "" && toDate != "")) {

                Date dateFrom = (Date) formatter.parse(fromDate);
                Date dateTo = (Date) formatter.parse(toDate);
                aFrom = newformatter.format(dateFrom);
                aTo = newformatter.format(dateTo);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        filter.setFromDate(aFrom);
        filter.setToDate(aTo);
         int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        filter.setStart_page(start);
        filter.setEnd_page(rows);
        projectList = attendanceService.getProjectList(filter);
        empList.setProjectId(filter.getProjectName());
        employeeList = attendanceService.getEmployeeList(empList);
        filter.setProject_id(filter.getProjectName());
        filter.setEmpId(filter.getEmpName());
        String recordCount = attendanceService.recordCount(filter);
        filter.setStart_page(0);
        filter.setEnd_page(Integer.parseInt(recordCount));
        List<AttendancePmProjectReport> attPmReport = attendanceService.getPmProjectAttendanceReport(filter);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < attPmReport.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + attPmReport.get(i).getEmp_id()));
            rowDataList.add(new String("" + attPmReport.get(i).getEmp_name()));
            rowDataList.add(new String("" + attPmReport.get(i).getProjectName()));
            rowDataList.add(new String("" + attPmReport.get(i).getReporting_manager()));
            rowDataList.add(new String("" + attPmReport.get(i).getUnit()));
            rowDataList.add(new String("" + attPmReport.get(i).getAttendance_date()));
            rowDataList.add(new String("" + attPmReport.get(i).getAttendance_status()));
            rowDataList.add(new String("" + attPmReport.get(i).getIn_time()));
            rowDataList.add(new String("" + attPmReport.get(i).getOut_time()));
            rowDataList.add(new String("" + attPmReport.get(i).getTime_duration()));
            rowDataList.add(new String("" + attPmReport.get(i).getSwipe_location()));
            rowDataList.add(new String("" + attPmReport.get(i).getWorkLocation()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "pmAttendance_Report.xls", "Attendance Report", null);
        return mv;
    }

}
