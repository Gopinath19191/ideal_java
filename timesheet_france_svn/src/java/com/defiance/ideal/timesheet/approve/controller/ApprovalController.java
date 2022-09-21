/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.controller;

import com.defiance.ideal.timesheet.approve.dao.ApprovalDAOImpl;
import com.defiance.ideal.timesheet.approve.dto.ApprovalDTO;
import com.defiance.ideal.timesheet.approve.dto.CompanyHolidayDTO;
import com.defiance.ideal.timesheet.approve.dto.ConfigurationDTO;
import com.defiance.ideal.timesheet.approve.dto.EmployeeDTO;
import com.defiance.ideal.timesheet.approve.dto.EmployeeLeaveDTO;
import com.defiance.ideal.timesheet.approve.dto.PhaseDTO;
import com.defiance.ideal.timesheet.approve.dto.ProjectDTO;
import com.defiance.ideal.timesheet.approve.dto.RoleDTO;
import com.defiance.ideal.timesheet.approve.dto.RoleWorkLocationDTO;
import com.defiance.ideal.timesheet.approve.dto.SearchCriteriaDTO;
import com.defiance.ideal.timesheet.approve.dto.TaskDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetEntryDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetExportDTO;
import com.defiance.ideal.timesheet.approve.dto.WFHpolicyIntegrationDTO;
import com.defiance.ideal.timesheet.approve.dto.WeekEndDetails;
import com.defiance.ideal.timesheet.approve.services.ApprovalServiceImpl;
import com.defiance.ideal.timesheet.util.CommonFunctions;
import com.lowagie.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.codehaus.jackson.map.ObjectMapper;
import javax.servlet.ServletOutputStream;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class ApprovalController extends MultiActionController {
    
            
        String sLogFile = "D:\\propertyfiles\\TimesheetLogFile\\IDEAL_TS_Log.log";
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;       
        SimpleFormatter formatter = new SimpleFormatter();
        
        public ApprovalController(){         
            try {
                fh = new FileHandler(sLogFile, true);
            } catch (IOException ex) {
                Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            logger.addHandler(fh);
            fh.setFormatter(formatter);
        }

    ModelAndView mv = null;

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
        mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
        ApprovalDTO data = new ApprovalDTO();

        if (request.getParameter("token") == null) {
            mv.addObject("ErrorMessage", "Employee Id Not Present");
        } else {
            data = (ApprovalDTO) dao.getUserDetails(request.getParameter("token"));
        }
        session.setAttribute("accessId", data.getEmpId());

        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            data.setModuleId(request.getParameter("modId"));
        }
        boolean authenticated = dao.authenticate(data);
        authenticated = true;
        if (authenticated) {
            session.setAttribute("employeeSearchID", "");
            int module_id = Integer.parseInt(data.getModuleId());
//          switch (module_id) {
//                case 349:
//                    mv = new ModelAndView("redirect:index.htm");
//                    break;
//                case 350:
//                    mv = new ModelAndView("redirect:pmApproval.htm?list=pending");
//                    break;
//                default:
//                    mv = new ModelAndView("redirect:index.htm");
//                    break;
//            }
            mv = new ModelAndView("redirect:index.htm");
        } else {
            session.invalidate();
        }
        return mv;
    }

    public ModelAndView authenticateApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {

        mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
        ApprovalDTO data = new ApprovalDTO();

        if (request.getParameter("token") == null) {
            mv.addObject("ErrorMessage", "Employee Id Not Present");
        } else {

            data = (ApprovalDTO) dao.getUserDetails(request.getParameter("token"));
        }
        session.setAttribute("accessId", data.getEmpId());

        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            data.setModuleId(request.getParameter("modId"));
        }
        boolean authenticated = dao.authenticate(data);
        //authenticated=true;
        if (authenticated) {

            session.setAttribute("employeeSearchID", "");
            int module_id = Integer.parseInt(data.getModuleId());
           
            mv = new ModelAndView("redirect:pmApproval.htm?list=pending");
        } else {
            session.invalidate();
        }
        return mv;
    }

    public synchronized ModelAndView pmApproval(HttpServletRequest request, HttpServletResponse response, ApprovalDTO appDTO) throws IllegalAccessException, IOException {
        try {
            HttpSession session = request.getSession();
            List<ApprovalDTO> projectList = null;
            List<ApprovalDTO> workTypeList = null;
            String employeeId = (String) session.getAttribute("accessId");
            String status = request.getParameter("status");
            String projectId = request.getParameter("prjId");

            appDTO.setEmployeeId(employeeId);
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
            String approveType = request.getParameter("list");
            appDTO.setApproveType(approveType);
            projectList = serviceImpl.getProjectList(appDTO);
            workTypeList = serviceImpl.getWorkTypeList(appDTO);


            response.getWriter().println("<option value=''>-   Select Employee  -</option>");
            if (projectId != "") {
                for (ApprovalDTO dTO : this.getEmployeeList(appDTO)) {
                    if (dTO.getEmpId() != null) {
                        response.getWriter().println("<option title = '" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "' value='" + dTO.getEmpId() + "'>" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "</option>");
                    }
                }
            }
            mv = new ModelAndView("/approval");
            mv.addObject("employeeList", this.getEmployeeList(appDTO));
            mv.addObject("projectList", projectList);
            mv.addObject("workTypeList", workTypeList);
            mv.addObject("selectedList", approveType);
            mv.addObject("status", status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public synchronized ModelAndView getEmployeeList(HttpServletRequest request, HttpServletResponse response, ApprovalDTO appDTO) throws Exception {
        try {

            HttpSession session = request.getSession();
            String projectId = request.getParameter("prjId");
            String listId = request.getParameter("selectedList");
            String employeeId = (String) session.getAttribute("accessId");
            appDTO.setEmployeeId(employeeId);
            appDTO.setProjectId(projectId);
            appDTO.setApproveType(listId);
            response.getWriter().println("<option value=''>-   Select Employee  -</option>");
            if (projectId != "") {
                for (ApprovalDTO dTO : this.getEmployeeList(appDTO)) {
                    if (dTO.getEmpId() != null) {
                        response.getWriter().println("<option title = '" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "' value='" + dTO.getEmpId() + "'>" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "</option>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized ModelAndView getPrjDetails(HttpServletRequest request, HttpServletResponse response, ApprovalDTO appDTO) throws Exception {
        try {

            HttpSession session = request.getSession();
            String projectId = request.getParameter("prjId");
            String listId = request.getParameter("list");
            String employeeId = (String) session.getAttribute("accessId");
            appDTO.setEmployeeId(employeeId);
            appDTO.setProjectId(projectId);
            appDTO.setApproveType(listId);
            String prjt_code = "";
            String cust_name = "";
            String po_value = "";
            String crncy = "";
           
            if (projectId != "") {
               
                for (ApprovalDTO dTO : this.getPrjDetails(appDTO)) {
                    if (dTO.getProjectCode() != null) {
                        System.out.println("customer name " + dTO.getProjectCustomerName());
                        if (dTO.getProjectCustomerName() == null || dTO.getProjectCustomerName() == " ") {
                            cust_name = "-";
                        } else {
                            cust_name = dTO.getProjectCustomerName();
                        }

                        if (dTO.getProjectPoValue() == null || dTO.getProjectPoValue() == " ") {
                            po_value = "-";
                        } else {
                            po_value = dTO.getProjectPoValue();
                        }

                        if (dTO.getProjectCode() == null || dTO.getProjectCode() == " ") {
                            prjt_code = "-";
                        } else {
                            prjt_code = dTO.getProjectCode();
                        }

                        if (dTO.getProjectCurrency() == null || dTO.getProjectCurrency() == "") {
                            crncy = "-";
                        } else {
                            crncy = dTO.getProjectCurrency();
                        }
                        response.getWriter().println("<tr><td> Customer Name</td><td>" + cust_name + "</td><td> Project Value</td> <td>" + po_value + "</td></tr><tr><td> Project Code</td><td>" + prjt_code + "</td><td> Currency</td><td>" + crncy + "</td></tr>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = ((ApprovalServiceImpl) getWebApplicationContext().getBean("ApprovalService")).getEmployeeList(appDTO);
        return employeeList;
    }

    public synchronized List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO) {
        List<ApprovalDTO> projectList = ((ApprovalServiceImpl) getWebApplicationContext().getBean("ApprovalService")).getPrjDetails(appDTO);
        return projectList;
    }

    public synchronized List<ApprovalDTO> getWorkTypeDetails(ApprovalDTO appDTO) {
        List<ApprovalDTO> workTypeList = ((ApprovalServiceImpl) getWebApplicationContext().getBean("ApprovalService")).getWorkTypeList(appDTO);
        return workTypeList;
    }

    public synchronized ModelAndView filterData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO filterDTO) throws Exception {
        try {

            HttpSession session = request.getSession(true);
            List<ApprovalDTO> projectList = null;
            List<ApprovalDTO> workTypeList = null;

            List<ApprovalDTO> filterDataList = null;
            String employeeId = (String) session.getAttribute("accessId");

            String shiftType = CommonFunctions.shiftType;
            String pricingType = CommonFunctions.pricingType;
            String locationType = CommonFunctions.locationType;
            filterDTO.setShiftKey(shiftType);
            filterDTO.setLocationKey(locationType);
            filterDTO.setPricingKey(pricingType);
            filterDTO.setEmployeeId(employeeId);
            filterDTO.setProjectId(filterDTO.getProjectName());
            filterDTO.setEmpId(filterDTO.getEmpName());
            filterDTO.setApproveType(request.getParameter("approveType"));
            filterDTO.setWorkTypeKey(filterDTO.getRegularName());
            String selectedValue = " ";
            if (filterDTO.getProjectName() != null && !"".equals(filterDTO.getProjectName())) {
                selectedValue = selectedValue + " tep.project_id='" + filterDTO.getProjectId() + "' ";
            }

            if (filterDTO.getEmpName() != null && !"".equals(filterDTO.getEmpName())) {
                selectedValue = selectedValue + "AND te.employee_id=" + filterDTO.getEmpName() + " ";
            }
            if (filterDTO.getFromDate() != null && !"".equals(filterDTO.getFromDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date >=STR_TO_DATE(" + "'" + filterDTO.getFromDate() + "','%d-%m-%Y')" + " ";
            }
            if (filterDTO.getToDate() != null && !"".equals(filterDTO.getToDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date <=STR_TO_DATE(" + "'" + filterDTO.getToDate() + "','%d-%m-%Y')" + " ";
            }
            filterDTO.setValue(selectedValue);
        
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
            projectList = serviceImpl.getProjectList(filterDTO);
            workTypeList=serviceImpl.getWorkTypeList(filterDTO);
            int page = Integer.parseInt(request.getParameter("page"));
            int rows = 60;
            int start;
            start = ((page - 1) * rows);
            filterDTO.setStart_page(start);
            filterDTO.setEnd_page(rows);
            filterDataList = serviceImpl.filterDataList(filterDTO);
            String recordCount = serviceImpl.recordCount(filterDTO);
        
            mv = new ModelAndView("/approval");
            int[] pageNo = paging(rows, recordCount, page);
       
            mv.addObject("paging", paging(rows, recordCount, page));
            mv.addObject("tableHeader", filterDTO.getProjectName());
            mv.addObject("projectList", projectList);
            mv.addObject("workTypeList", workTypeList);
            mv.addObject("requsetorDTO", filterDTO);
            mv.addObject("approverList", filterDataList);
           
            mv.addObject("employeeList", this.getEmployeeList(filterDTO));
            mv.addObject("fromDate", filterDTO.getFromDate());
            mv.addObject("toDate", filterDTO.getToDate());
            mv.addObject("selectedList", filterDTO.getApproveType());
            if (filterDTO.getSuccessMessage() != null) {
                mv.addObject("success_msg", filterDTO.getSuccessMessage());
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public  int[] paging(int rows, String recordCount, int current_page) {
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

    public synchronized ModelAndView approverSubmit(HttpServletRequest request, HttpServletResponse response, ApprovalDTO appDTOSubmit) throws Exception {
        try {
            HttpSession session = request.getSession();
            Date date = new Date();
            DecimalFormat df = new DecimalFormat("###.##");
            String actionValue = request.getParameter("actionValue");
            Integer totMin = CommonFunctions.TOTAL_MIN;
            Integer totOffshoreHrs = CommonFunctions.TOT_OFFSHORE_HRS_IN_DAY;
            Integer totOnsiteHrs = CommonFunctions.TOT_ONSITE_HRS_IN_DAY;
            appDTOSubmit.setActionValue(actionValue);
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");

            if (appDTOSubmit.getChk_data() != null) {
                int chkBoxLength = appDTOSubmit.getChk_data().length;
                if (chkBoxLength > 0) {
                    for (int i = 0; i < appDTOSubmit.getChk_data().length; i++) {
                        int chkedId = Integer.parseInt(appDTOSubmit.getChk_data()[i].split("~~")[1]);
                        int chkedprimaryId = Integer.parseInt(appDTOSubmit.getChk_data()[i].split("~~")[0]);
                         System.out.println("checked iddd "+appDTOSubmit.getChk_data()[i].split("~~")[0]);
                         
                        appDTOSubmit.setCheckedId(appDTOSubmit.getChk_data()[i].split("~~")[0]);
                        appDTOSubmit.setApprovedHrs(appDTOSubmit.getAppHrs()[chkedId] + ":" + appDTOSubmit.getAppMins()[chkedId]);
                        appDTOSubmit.setApprovedMins(appDTOSubmit.getAppMins()[chkedId]);
                        appDTOSubmit.setTimeSheetRemarks(appDTOSubmit.getRemarks()[chkedId]);
                        appDTOSubmit.setEnteredMonth(appDTOSubmit.getSelMonth()[chkedId]);
                        appDTOSubmit.setEnteredYear(appDTOSubmit.getSelYear()[chkedId]);
                        ApprovalDTO prjDetails = serviceImpl.getProjectDetails(appDTOSubmit);
                      
                        if (!"Non_Project_Activity".equals(appDTOSubmit.getProjectName())) {
                            if (actionValue != null) {
                                if ("a".equals(actionValue)) {
                                    if (prjDetails.getProjectModel().equals("c")) {
                                        serviceImpl.approverSubmit(appDTOSubmit);
                                    }
                                        serviceImpl.approverSubmit(appDTOSubmit);
                                } else {
                                    serviceImpl.approverSubmit(appDTOSubmit);
                                }
                            }
                        } else {
                            serviceImpl.approverSubmit(appDTOSubmit);
                        }
                    }
                }
            }
            ApprovalDTO filterTO = new ApprovalDTO();
            filterTO.setProjectName(appDTOSubmit.getProjectName());
            filterTO.setEmpName(appDTOSubmit.getEmpName());
            filterTO.setFromDate(appDTOSubmit.getFromDate());
            filterTO.setToDate(appDTOSubmit.getToDate());
            if (actionValue != null) {
                if (actionValue.equalsIgnoreCase("a")) {
                    filterTO.setSuccessMessage("Timesheet Approved Successfully");
                } else {
                    filterTO.setSuccessMessage("Timesheet Entries are Sent Back");
                }
            }
            filterTO.setRegularName(request.getParameter("regularName"));
            mv = filterData(request, response, filterTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public synchronized ModelAndView  index(HttpServletRequest request, HttpServletResponse response, TimesheetEntryDTO objTimesheet) throws IllegalAccessException, IOException {
        try {
      
            mv = new ModelAndView("/index");
            Map year_list;
            Map<String, String> month_list = new LinkedHashMap<String, String>();
            Calendar cal = Calendar.getInstance();

            HttpSession session = request.getSession();
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
            String accessId = (String) session.getAttribute("accessId");
            objTimesheet.setEmployeeId(accessId);

            //Getting Year List
            year_list = dao.getYearList(objTimesheet);
            mv.addObject("year_list", year_list);
            int currentYear = CommonFunctions.getCurrentYear();

            //Getting Month List
            int currentMonth = cal.get(Calendar.MONTH);
            int currentDate = cal.get(Calendar.DATE);
            int currentDay = cal.get(Calendar.DAY_OF_WEEK);
            String[] months = new DateFormatSymbols().getMonths();
            String month = "";
            String newMnt = "";
            for (int mnt = 0; mnt <= currentMonth; mnt++) {
                month = months[mnt];
                newMnt = Integer.toString(mnt + 1);
                month_list.put(newMnt, month);
            }
            mv.addObject("month_list", month_list);
            
            //Success Message & Redirect
            String yearSession = (String) session.getAttribute("yearSession");
            String monthSession = (String) session.getAttribute("monthSession");
            
//            System.out.println("currentDate **** "+currentDate);
//            System.out.println("currentDay **** "+currentDay);
             
             if(currentDate<=3){
                 if(currentDate==1){
                 currentMonth=currentMonth-1;   
                 }
                 else if(currentDay==Calendar.SATURDAY || currentDay==Calendar.SUNDAY || currentDay==Calendar.MONDAY)
                 {
                 currentMonth=currentMonth-1; 
                 }
                 else{
                     currentMonth=currentMonth;
                 }
            }
            if (yearSession == null && monthSession == null) {
                mv.addObject("currentMonth", currentMonth + 1);
                mv.addObject("currentYear", currentYear);
                mv.addObject("success_msg", "");
            } else {
                if (yearSession.equals("") && monthSession.equals("")) {
                    mv.addObject("currentMonth", currentMonth + 1);
                    mv.addObject("currentYear", currentYear);
                    mv.addObject("success_msg", "");
                } else {
                    mv.addObject("currentMonth", monthSession);
                    mv.addObject("currentYear", yearSession);
                    mv.addObject("success_msg", "Timesheet added successfully");
                }
            }
            
            //Getting Status List 
            List<ConfigurationDTO> status_list = dao.getConfiguration("timesheet_status");
            mv.addObject("status_list", status_list);
            
            mv.addObject("formData", objTimesheet);
            session.setAttribute("yearSession", "");
            session.setAttribute("monthSession", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public synchronized ModelAndView loadMonth(HttpServletRequest request, HttpServletResponse response) {
        try {
         
            String[] months = new DateFormatSymbols().getMonths();
            String month = "";
            String newMnt = "";
            Calendar cal = Calendar.getInstance();
            int currentMonth = cal.get(Calendar.MONTH);
            int currentYear = CommonFunctions.getCurrentYear();
            int from_month = 0;
            int to_month = months.length - 1;

            mv = new ModelAndView("/index");
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String employee_id = request.getParameter("employee_id");
            String year = request.getParameter("year");
            year = year.trim();
            
            String joined_date = dao.getJoinedDate(employee_id);
            String dateArr[] = joined_date.split("-");

            if (year.equals(dateArr[0])) {
                from_month = Integer.parseInt(dateArr[1]) - 1;
            }
            if (year.equals(Integer.toString(currentYear))) {
                to_month = currentMonth + 1;
            }
            if(Integer.parseInt(year.trim()) == 2015){
                from_month = 3;
                if(Integer.parseInt(dateArr[1])> 3){
                    from_month = Integer.parseInt(dateArr[1]) -1;
                }
            }

            for (int mnt = from_month; mnt < to_month; mnt++) {
                month = months[mnt];
                newMnt = Integer.toString(mnt + 1);
                response.getOutputStream().write(newMnt.getBytes());
                response.getOutputStream().write(",".getBytes());
                response.getOutputStream().write(month.getBytes());
                response.getOutputStream().write(":".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized ModelAndView loadProject(HttpServletRequest request, HttpServletResponse response) {
        try {
         
            TimesheetDTO form_data = new TimesheetDTO();
            String start_date = null;
            String end_date = null;
            mv = new ModelAndView("/index");
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String employee_id = request.getParameter("employee_id");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            if (month.length() == 1) {
                month = "0" + month;
            }
            start_date = year + "-" + month + "-01";
            end_date = year + "-" + month + "-31";

            form_data.setEmployeeId(employee_id);
            form_data.setStart_date(start_date);
            form_data.setEnd_date(end_date);
            List<TimesheetDTO> data = dao.getProjects(form_data);
            for (int k = 0; k < data.size(); k++) {
                response.getOutputStream().write(data.get(k).getProject_id().getBytes());
                response.getOutputStream().write(",".getBytes());
                response.getOutputStream().write(data.get(k).getProject_name().getBytes());
                response.getOutputStream().write(":".getBytes());
            }
            response.getOutputStream().write("n".getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write("Non Project Activity".getBytes());
            response.getOutputStream().write(":".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @SuppressWarnings("empty-statement")
    public synchronized  ModelAndView loadTimesheet_New(HttpServletRequest request, HttpServletResponse response){
        List<TimesheetEntryDTO> formulatedDataObject = new ArrayList<TimesheetEntryDTO>();
        List<TimesheetEntryDTO> outputTimeSheetData = new ArrayList<TimesheetEntryDTO>();
        List<ConfigurationDTO> shift_list = new ArrayList<ConfigurationDTO>();
        List<ConfigurationDTO> reg_reason_list = new ArrayList<ConfigurationDTO>();
        List<String> holidayDates = new ArrayList<String>();
        List<WeekEndDetails> weekEndList = new ArrayList<WeekEndDetails>();
        
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        int today = cal.get(Calendar.DAY_OF_MONTH);
        String dayNames[] = new DateFormatSymbols().getShortWeekdays();
        String monthNames[] = new DateFormatSymbols().getMonths();
        
        int minDay = 1;
        int maxDay = 31;
        String halfDayLeaveFlag = "";
        try {
            mv = new ModelAndView("/timesheet");
            
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String employee_id = request.getParameter("employee_id");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String status = request.getParameter("status");
            month = month.trim();
            year = year.trim();
            cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, 1);
            maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            EmployeeDTO employeeInfo = dao.getEmployeeInfo_New(employee_id);
            if(employeeInfo.getBand_id().equals("34") || employeeInfo.getBand_id() == "34"){
                int days = dao.getTimesheetBlockDate();
                if((currentMonth+1 == Integer.parseInt(month)) && currentYear==Integer.parseInt(year)){
                    mv.addObject("editable", "1");
                }else if((currentMonth == Integer.parseInt(month)) && currentYear==Integer.parseInt(year)){
                   if(today>days){
                       mv.addObject("editable", "0");
                   }
               }else{
                   mv.addObject("editable", "0");
               }
               if(currentYear-1 == Integer.parseInt(year)){
                   if((currentMonth+1 == Integer.parseInt(month)) && currentMonth+1 == 1){
                       if(today>days){
                           mv.addObject("editable", "0");
                       }
                   }else{
                       mv.addObject("editable", "0");
                   }
               }
            }else{
                mv.addObject("editable", "1");
            }
            int dateCount = 0;
            int completed_count = 0;
            boolean bTSDataNotExistsflag = false;
            boolean projectFlag = false;

            if (Integer.parseInt(year.trim()) == currentYear && Integer.parseInt(month.trim()) == currentMonth + 1) {
                maxDay = today;
            }
            //Header Starts
            mv.addObject("Year", year);
            mv.addObject("Month", monthNames[Integer.parseInt(month.trim()) - 1]);
            mv.addObject("Month_integer",month);
            mv.addObject("employee_id", employee_id);
            mv.addObject("next_month", "");
            mv.addObject("next_year", "");
            mv.addObject("previous_month", "");
            mv.addObject("previous_year", "");
            mv.addObject("status", status);
            int previous_month = Integer.parseInt(month.trim()) - 1;
            int previous_year = Integer.parseInt(year.trim());
            if (previous_month == 0) {
                previous_month = 12;
                previous_year = previous_year - 1;
            }

            int next_month = Integer.parseInt(month.trim()) + 1;
            int next_year = Integer.parseInt(year.trim());
            if (next_month == 13) {
                next_month = 1;
                next_year = next_year + 1;
            }
            
            if ((next_year < currentYear) || (next_year == currentYear && next_month <= (currentMonth + 1))) {
                mv.addObject("next_month", next_month);
                mv.addObject("next_year", next_year);
            }
            
            String joined_date = employeeInfo.getJoined_date();
            
            if (joined_date != null || !joined_date.isEmpty()) {
                String[] joinedDateArr = joined_date.split("-");
                if (previous_year > Integer.parseInt(joinedDateArr[0]) || (previous_year == Integer.parseInt(joinedDateArr[0]) && previous_month >= Integer.parseInt(joinedDateArr[1]))) {
                    mv.addObject("previous_year", previous_year);
                    mv.addObject("previous_month", previous_month);
                }
                if (Integer.parseInt(year.trim()) == Integer.parseInt(joinedDateArr[0]) && Integer.parseInt(month.trim()) == Integer.parseInt(joinedDateArr[1])) {
                    minDay = Integer.parseInt(joinedDateArr[2]);
                }
            }
                                            
            String mth = Integer.toString(Integer.parseInt(month));  
            
            String min_day = Integer.toString(minDay);
            String max_day = Integer.toString(maxDay);
            
            if (minDay <= 9) {
                min_day = "0" + minDay;
            }
            if (maxDay <= 9) {
                max_day = "0" + maxDay;
            }
            if (Integer.parseInt(month) <= 9) {
                mth = "0" + month;
            }
                        
            String start_date = year + "-" + mth + "-" + min_day;
            String end_date = year + "-" + mth + "-" + max_day;
            String yearStart_date = year + "-" + "01" + "-" + "01";
            String yearEnd_date = year + "-" + "12" + "-" + "31";
            
            List<ConfigurationDTO> statusConfigValues = dao.getConfiguration("timesheet_status");
            String[] statusList = new String[statusConfigValues.size() + 1];
            for (int i = 0; i < statusConfigValues.size(); i++) {
                statusList[i] = statusConfigValues.get(i).getConfiguration_key();
            }
            
            SearchCriteriaDTO oSearchTimeSheet = new SearchCriteriaDTO();
            oSearchTimeSheet.setStart_date(start_date);
            oSearchTimeSheet.setEnd_date(end_date);
            oSearchTimeSheet.setEmployeeId(employee_id);
            oSearchTimeSheet.setStatus(status);
            outputTimeSheetData = dao.getTimesheetEntries_New(oSearchTimeSheet);
            //Get WeekEndList
            SearchCriteriaDTO objWeekSearch = new SearchCriteriaDTO();
            objWeekSearch.setEmployeeId(employee_id);
            objWeekSearch.setStart_date(start_date);
            objWeekSearch.setEnd_date(end_date);
            weekEndList = dao.getWeekEndDetails(objWeekSearch);

            // Retrieve projects that constitute for the whole month
            oSearchTimeSheet.setGlobal_projects("1");
            List<ProjectDTO> PTAEntireMonth = dao.getProjects_New1(oSearchTimeSheet);
            
            // Retrieve projects that constitute part of the month
            oSearchTimeSheet.setGlobal_projects("2");
            List<ProjectDTO> PTAPartialMonth = dao.getProjects_New1(oSearchTimeSheet);
            
            Map mapObjGlobal = new HashMap();
            if (PTAPartialMonth.isEmpty()) {
                if (PTAEntireMonth.isEmpty()) {
                    projectFlag = true;
                } else if (PTAEntireMonth.size() == 1) {
                    oSearchTimeSheet.setProjectId(PTAEntireMonth.get(0).getProject_id());
                    ProjectDTO pDetail = dao.getProjectDetail_New(oSearchTimeSheet);
                    if (pDetail != null) {
                        mapObjGlobal.put(PTAEntireMonth.get(0).getProject_id(), PTAEntireMonth.get(0).getProject_name());
                        projectFlag = true;
                    }
                }
            }
            
            Map holidayData = new HashMap();
            SearchCriteriaDTO objSearch = new SearchCriteriaDTO();
           
            objSearch.setEmployeeId(employee_id);
            objSearch.setStart_date(start_date);
            objSearch.setEnd_date(end_date);
            List<CompanyHolidayDTO> holidayList = dao.getCompanyHolidays(objSearch);
            if (holidayList.size() > 0) {
                for (int h = 0; h < holidayList.size(); h++) {
                    holidayData.put(holidayList.get(h).getHoliday_date(), holidayList.get(h).getHoliday_description());
                    holidayDates.add(holidayList.get(h).getHoliday_date());
                }
            }
            
            Map exceptionalWeekend = new HashMap();
            List<CompanyHolidayDTO> exceptionalWorkingDays = dao.getCompanyExceptionalWorkingDays(objSearch);
            if(exceptionalWorkingDays.size()>0){
                for(int k=0; k<exceptionalWorkingDays.size();k++){
                    exceptionalWeekend.put(exceptionalWorkingDays.get(k).getHoliday_date(),exceptionalWorkingDays.get(k).getHoliday_description());
                }
            }
            
            objSearch.setEmployeeId(employee_id);
            objSearch.setYearEnd_date(yearEnd_date);
            objSearch.setYearStart_date(yearStart_date);
            
            List<WFHpolicyIntegrationDTO> countOfWFH = dao.getCountOfRegularizationWFH(objSearch);

            mv.addObject("year_count", countOfWFH.get(0).getYear_count());
            mv.addObject("month_count", countOfWFH.get(0).getMonth_count());

            
            List<WFHpolicyIntegrationDTO> policyValue = dao.getPolicyConfigurationValue();

            mv.addObject("WFH_Month", policyValue.get(0).getWHF_Per_Month());
            mv.addObject("WFH_Year", policyValue.get(0).getWHF_Per_Year());

            
            List<WFHpolicyIntegrationDTO> exceptionList = dao.getExceptionList();

            Iterator<WFHpolicyIntegrationDTO> WFHIterator = exceptionList.iterator();
            while (WFHIterator.hasNext()) {
                WFHpolicyIntegrationDTO c = WFHIterator.next();
                if (c.getEmp_id().contains(employeeInfo.getEmployee_number())) {
                    mv.addObject("inExceptionList", "Y");
                    break;
                } else {
                    mv.addObject("inExceptionList", "N");
                }
            }
            
            Map leaveData = new HashMap();
            Map cancelledLeaveData = new HashMap();
            
            objSearch.setEmployeeId(employee_id);
            
            List<EmployeeLeaveDTO> leaveDetails = dao.getEmployeeLeaveDetails(objSearch);
            if (leaveDetails.size() > 0) {
                for (EmployeeLeaveDTO leave_details : leaveDetails) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    
                    Date fromDate = (Date) sdf.parse(leave_details.getFrom_date());
                    Date toDate = (Date) sdf.parse(leave_details.getTo_date());
                    Date monthStartDate = (Date) sdf.parse(start_date);
                    Date monthEndDate = (Date) sdf.parse(end_date);
                    
                    long fromHalfDay = 0;
                    long toHalfDay = 0;

                    
                    if (leave_details.getFrom_half_day().equals("1")) {
                        fromHalfDay = fromDate.getTime();
                    }
                    
                    if (leave_details.getTo_half_day().equals("1")) {
                        toHalfDay = toDate.getTime();
                    }
                   
                    long interval = 24 * 1000 * 60 * 60;
                    long curTime = fromDate.getTime();
                    long endTime = toDate.getTime();
                    String[] cancelledDatesArr = null;
                    if (leave_details.getCanceled_dates() != null && !leave_details.getCanceled_dates().equals("")) {
                        cancelledDatesArr = leave_details.getCanceled_dates().split(",");
                        for (String cancelled_arr : cancelledDatesArr) {
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                            Date cancelledDate = (Date) sdf1.parse(cancelled_arr);
                            cancelledLeaveData.put(cancelledDate.getTime(), cancelledDate.getTime());
                        }
                    }
                    while (curTime <= endTime) {
                        boolean half_day_flag = true;
                        if (fromHalfDay != 0) {
                            if (fromHalfDay == curTime) {
                                half_day_flag = false;
                            }
                        }
                        if (toHalfDay != 0) {
                            if (toHalfDay == curTime) {
                                half_day_flag = false;
                            }
                        }
                        if (leave_details.getCanceled_dates() != null && !leave_details.getCanceled_dates().equals("")) {
                            if (!cancelledLeaveData.containsKey(curTime)) {
                                leaveData.put(sdf.format(new Date(curTime)), half_day_flag);
                            }
                        } else {
                            leaveData.put(sdf.format(new Date(curTime)), half_day_flag);
                        }
                        curTime += interval;
                    }
                }
            }
           
            List<String> lstExcludedStatus = new ArrayList<String>();

            if (status == null || status.equals("")) {
                lstExcludedStatus.add("a");
                lstExcludedStatus.add("m");
            } else {
                if (!status.equalsIgnoreCase("all")) {
                    Iterator<TimesheetEntryDTO> TSIterator = outputTimeSheetData.iterator();
                    while (TSIterator.hasNext()) {
                        TimesheetEntryDTO c = TSIterator.next();
                        if (!c.getStatus().equalsIgnoreCase(status)) {
                            TSIterator.remove();
                        }
                    }
                }
            }

            if (outputTimeSheetData.size() > 0) {
                for (int j = 0; j < outputTimeSheetData.size(); j++) {
                    String[] timesheet_date_arr = outputTimeSheetData.get(j).getTimesheet_date().split("-");
                    reg_reason_list = dao.getConfiguration("emp_reason_list");
                    // Process for the date that doesn't have entry in timesheet table
                    if (Integer.parseInt(timesheet_date_arr[2]) != dateCount + 1) {
                        if (status == null || status.equalsIgnoreCase("all") || status.equals("")) {
                            for (int k = dateCount + 1; k < Integer.parseInt(timesheet_date_arr[2]); k++) {
                                String entry_date = year.trim();
                                if (Integer.parseInt(month.trim()) <= 9) {
                                    entry_date += "-" + "0" + Integer.parseInt(month);
                                } else {
                                    entry_date += "-" + Integer.parseInt(month);
                                }
                                if (k <= 9) {
                                    entry_date += "-" + "0" + k;
                                } else {
                                    entry_date += "-" + k;
                                }
                                TimesheetEntryDTO obj = new TimesheetEntryDTO();

                                oSearchTimeSheet.setTimesheet_date(entry_date);

                                DateFormat dateFormatTemp = new SimpleDateFormat("yyyy-MM-dd");
                                Date framedDate = (Date) dateFormatTemp.parse(entry_date);
                                Date joineDate = (Date) dateFormatTemp.parse(employeeInfo.getJoined_date());
                                long framedDateTemp = (framedDate.getTime() + 1 * 24 * 3600 * 1000);
                                long joineDateTemp = (joineDate.getTime() + 1 * 24 * 3600 * 1000);
                                if (framedDateTemp >= joineDateTemp) {
                                    Map mapObj = new HashMap();
                                    if (!projectFlag) {
                                        // Retrieve projects whose duration falls on the timesheet date
                                        oSearchTimeSheet.setGlobal_projects("0");
                                        List<ProjectDTO> project = dao.getProjects_New1(oSearchTimeSheet);
                                        for (int prj = 0; prj < project.size(); prj++) {
                                            mapObj.put(project.get(prj).getProject_id(), project.get(prj).getProject_name());
                                        }
                                    }
                                    Map phaseObj = new HashMap();
                                    Map taskObj = new HashMap();
                                    Map roleObj = new HashMap();
                                    Map locationObj = new HashMap();

                                
                                cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, k);
                                obj.setPhase_name("");
                                obj.setTask_name("");
                                obj.setProject_name("");
                                obj.setShift("");
                                obj.setRemarks("");
                                obj.setRejected_remarks("");
                                obj.setHoliday_description("");
                                obj.setRole_name("");
                                obj.setLocation("");
                                obj.setTimesheet_date(k + "");
                                obj.setTimesheet_day(dayNames[cal.get(Calendar.DAY_OF_WEEK)]);
                                obj.setTimesheet_datex(year + "-" + Integer.parseInt(month) + "-" + k);
                                obj.setTimesheet_id("");
                                obj.setStatus("");
                                obj.setWorked_hours("");
                                obj.setWorked_minutes("");
                                
                                SearchCriteriaDTO oSearchHours = new SearchCriteriaDTO();
                                oSearchHours.setEmployeeNumber(employeeInfo.getEmployee_number());
                                oSearchHours.setEmployeeId(employee_id);
                                oSearchHours.setTimesheet_date(entry_date);
                                 

                                obj.setWeek_day(cal.get(Calendar.DAY_OF_WEEK));

                                obj.setLeave("0");
                                if (leaveData.containsKey(entry_date)) {
                                    obj.setLeave("1");
                                    if (leaveData.get(entry_date).equals(true)) {
                                        obj.setLeave_status("0");
                                    } else {
                                        obj.setLeave_status("1");
                                    }
                                }
                                String sAvailableHours = dao.getAvailableHours_New(oSearchHours);
                                if (sAvailableHours != null && !sAvailableHours.equals("")) {
                                    obj.setAvailable_hours(sAvailableHours);
                                } else {
                                    obj.setAvailable_hours("---");
                                }
                                
                                String sOfficeHours = dao.getOfficeHours_New(oSearchHours);
                                if (sOfficeHours != null && !sOfficeHours.equals("")) {
                                    obj.setOffice_hours(sOfficeHours);
                                } else {
                                    obj.setOffice_hours("---");
                                }

                                    String sWorkLocation = dao.getWorkLocationOfEmployee(oSearchHours);
                                    String sAttendanceHours = dao.getAttendanceHours_New(oSearchHours);
                                    String sGet_WFH_Eligible = dao.get_WFH_Eligible(oSearchHours);
                                    reg_reason_list = dao.getConfiguration("emp_reason_list");

                                    if (sGet_WFH_Eligible.equals("0")) {
                                        Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                        while (reasonList.hasNext()) {
                                            ConfigurationDTO c = reasonList.next();
                                            if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                reasonList.remove();
                                            }
                                        }
                                        obj.setReg_reason_list(reg_reason_list);
                                    } else {
                                        obj.setReg_reason_list(reg_reason_list);
                                    }

                                    if (!projectFlag) {
                                        obj.setProjectMap(mapObj);
                                    } else {
                                        obj.setProjectMap(mapObjGlobal);
                                    }
                                    obj.setPhaseMap(phaseObj);
                                    obj.setTaskMap(taskObj);
                                    obj.setRoleMap(roleObj);
                                    obj.setLocationMap(locationObj);
                                    if (halfDayLeaveFlag.equals(entry_date) && (obj.getStatus().equals("m") || obj.getStatus().equals("a"))) {
                                        obj.setIsSubmitted("Y");
                                    } else {
                                        obj.setIsSubmitted("N");
                                    }

                                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date entry_date_formatted = (Date) dateFormat.parse(entry_date);
                                    long entryTemp = (entry_date_formatted.getTime() + 1 * 24 * 3600 * 1000);
                                    for (int i = 0; i < weekEndList.size(); i++) {
                                        String sDate = weekEndList.get(i).getStart_date();
                                        String eDate = weekEndList.get(i).getEnd_date();
                                        String weekEnd = weekEndList.get(i).getLeaveday();
                                        String weekEndTemp[] = weekEnd.split(",");
                                        int tempWeekEnds[] = new int[weekEndTemp.length];
                                        for (int l = 0; l < weekEndTemp.length; l++) {
                                            tempWeekEnds[l] = Integer.parseInt(weekEndTemp[l]);
                                        }
                                        Date sFormattedDate = (Date) dateFormat.parse(sDate);
                                        Date eFormattedDate = (Date) dateFormat.parse(eDate);
                                        long sTemp = (sFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
                                        long eTemp = (eFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
//analysis stats
                                        if ((sTemp >= entryTemp) || (entryTemp <= eTemp)) {
                                            int flag = 0;
                                            for (int weekEndIndex = 0; weekEndIndex < tempWeekEnds.length; weekEndIndex++) {
                                                if (flag == 0) {
                                                    if (tempWeekEnds[weekEndIndex] == cal.get(Calendar.DAY_OF_WEEK)) {
                                                        if(exceptionalWeekend.containsKey(entry_date)){
                                                            if (obj.getLeave().equals("1")) { //full day la
                                                                if (obj.getLeave_status().equals("0")) {
                                                                    obj.setAttendance_hours("---");
                                                                    obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Leave Applied</font></html>");
                                                                    i = weekEndList.size();
                                                                } else {
                                                                    if (sWorkLocation.equals("1")) {
                                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                            obj.setAttendance_hours(sAttendanceHours);
                                                                        } else {
                                                                            obj.setAttendance_hours("---");
                                                                        }
                                                                    } else {
                                                                        String[] valFromDB = obj.getAvailable_hours().split(":");
                                                                        int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                                        int hrs = hrsValue / 2;
                                                                        int h = hrs / 60;
                                                                        int mins = hrs % 60;
                                                                        String hr = String.valueOf(h);
                                                                        String mn = String.valueOf(mins);
                                                                        String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                                        fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                                        obj.setAttendance_hours(fV);
                                                                    }
                                                                    obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Halfday Leave</font></html>");
                                                                    i = weekEndList.size();
                                                                }
                                                            }else{
                                                                obj.setIsWeekEnd("N");
                                                                obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;' >" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                                if (sWorkLocation.equals("1")) {
                                                                    if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                        obj.setAttendance_hours(sAttendanceHours);
                                                                    } else {
                                                                        obj.setAttendance_hours("---");
                                                                    }
                                                                } else {
                                                                    obj.setAttendance_hours(obj.getAvailable_hours());
                                                                }
                                                                obj.setStatus_text("<html><font color='red' ; style = 'font-weight: bold;'>To be submitted</font></html>");
                                                                i = weekEndList.size();
                                                            }
                                                            
                                                        }else{
                                                            Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                            while (reasonList.hasNext()) {
                                                                ConfigurationDTO c = reasonList.next();
                                                                if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                                    reasonList.remove();
                                                                }
                                                            }
                                                            obj.setReg_reason_list(reg_reason_list);
                                                            obj.setLeave(null);
                                                            obj.setIsWeekEnd("Y");
                                                            obj.setAvailable_hours("---");
                                                            obj.setOffice_hours("---");
                                                            obj.setDate_display("<html><font color='red'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                            obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Week End</font></html>");
                                                            if (sWorkLocation.equals("1")) {
                                                                if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                    obj.setAttendance_hours(sAttendanceHours);
                                                                } else {
                                                                    obj.setAttendance_hours("---");
                                                                }
                                                            } else {
                                                                obj.setAttendance_hours("---");
                                                            }
                                                        }
//                                                        Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                                        while (reasonList.hasNext()) {
//                                                            ConfigurationDTO c = reasonList.next();
//                                                            if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                                reasonList.remove();
//                                                            }
//                                                        }
//                                                        System.out.println("weekend "+entry_date);
//                                                        obj.setReg_reason_list(reg_reason_list);
//                                                        obj.setLeave(null);
//                                                        obj.setIsWeekEnd("Y");
//                                                        obj.setAvailable_hours("---");
//                                                        obj.setOffice_hours("---");
//                                                        obj.setDate_display("<html><font color='red'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
//                                                        obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Week End</font></html>");
//                                                        if (sWorkLocation.equals("1")) {
//                                                            if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
//                                                                obj.setAttendance_hours(sAttendanceHours);
//                                                            } else {
//                                                                obj.setAttendance_hours("---");
//                                                            }
//                                                        } else {
//                                                            obj.setAttendance_hours("---");
//                                                        }
                                                        i = weekEndList.size();
                                                        flag++;
                                                    } else {
                                                        obj.setIsWeekEnd("N");
                                                        obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;' >" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                        if (obj.getLeave().equals("1")) { //full day la
                                                            if (obj.getLeave_status().equals("0")) {
                                                                obj.setAttendance_hours("---");
                                                                obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Leave Applied</font></html>");
                                                                i = weekEndList.size();
                                                            } else {
                                                                if (sWorkLocation.equals("1")) {
                                                                    if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                        obj.setAttendance_hours(sAttendanceHours);
                                                                    } else {
                                                                        obj.setAttendance_hours("---");
                                                                    }
                                                                } else {
                                                                    String[] valFromDB = obj.getAvailable_hours().split(":");
                                                                    int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                                    int hrs = hrsValue / 2;
                                                                    int h = hrs / 60;
                                                                    int mins = hrs % 60;
                                                                    String hr = String.valueOf(h);
                                                                    String mn = String.valueOf(mins);
                                                                    String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                                    fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                                    obj.setAttendance_hours(fV);
                                                                }

                                                          
                                                                obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Halfday Leave</font></html>");
                                                                i = weekEndList.size();
                                                            }
                                                        } else {
                                                            if (holidayData.containsKey(entry_date)) {
                                                                obj.setHoliday_description((String) holidayData.get(entry_date));
                                                            }
                                                            if (obj.getHoliday_description().equals("")) {
                                                                if (sWorkLocation.equals("1")) {
                                                                    if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                        obj.setAttendance_hours(sAttendanceHours);
                                                                    } else {
                                                                        obj.setAttendance_hours("---");
                                                                    }
                                                                } else {
                                                                    obj.setAttendance_hours(obj.getAvailable_hours());
                                                                }
                                                                obj.setStatus_text("<html><font color='red' ; style = 'font-weight: bold;'>To be submitted</font></html>");
                                                                i = weekEndList.size();
                                                            } else {
                                                                if(exceptionalWeekend.containsKey(entry_date)){
                                                                    obj.setHoliday_description("");
                                                                    if (sWorkLocation.equals("1")) {
                                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                            obj.setAttendance_hours(sAttendanceHours);
                                                                        } else {
                                                                            obj.setAttendance_hours("---");
                                                                        }
                                                                    } else {
                                                                        obj.setAttendance_hours(obj.getAvailable_hours());
                                                                    }
                                                                    obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                                    obj.setStatus_text("<html><font color='red' ; style = 'font-weight: bold;'>To be submitted</font></html>");
                                                                }else{
                                                                    Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                                    while (reasonList.hasNext()) {
                                                                        ConfigurationDTO c = reasonList.next();
                                                                        if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                                            reasonList.remove();
                                                                        }
                                                                    }

                                                                    obj.setReg_reason_list(reg_reason_list);
                                                                    if (sWorkLocation.equals("1")) {
                                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                            obj.setAttendance_hours(sAttendanceHours);
                                                                        } else {
                                                                            obj.setAttendance_hours("---");
                                                                        }
                                                                    } else {
                                                                        obj.setAttendance_hours("---");
                                                                    }
                                                                    obj.setAvailable_hours("---");
                                                                    obj.setOffice_hours("---");
                                                                    obj.setDate_display("<html><font color='red'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                                    obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Holiday</font></html>");
                                                                }
                                                                
//                                                                Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                                                while (reasonList.hasNext()) {
//                                                                    ConfigurationDTO c = reasonList.next();
//                                                                    if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                                        reasonList.remove();
//                                                                    }
//                                                                }
//
//                                                                obj.setReg_reason_list(reg_reason_list);
//                                                                if (sWorkLocation.equals("1")) {
//                                                                    if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
//                                                                        obj.setAttendance_hours(sAttendanceHours);
//                                                                    } else {
//                                                                        obj.setAttendance_hours("---");
//                                                                    }
//                                                                } else {
//                                                                    obj.setAttendance_hours("---");
//                                                                }
//                                                                obj.setAvailable_hours("---");
//                                                                obj.setOffice_hours("---");
//                                                                obj.setDate_display("<html><font color='red'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
//                                                                obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Holiday</font></html>");
                                                                i = weekEndList.size();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }//analysis ends
                                    }
                                    formulatedDataObject.add(obj);
                                }
                            }
                        }
                        dateCount = Integer.parseInt(timesheet_date_arr[2]) - 1;
                    }
                    // Process for the date that has entry in timesheet table
                    if (Integer.parseInt(timesheet_date_arr[2]) == dateCount + 1) {
                        if (!lstExcludedStatus.contains(outputTimeSheetData.get(j).getStatus())) {
                            String entry_date = outputTimeSheetData.get(j).getTimesheet_date();
                            oSearchTimeSheet.setTimesheet_date(entry_date);
                            String dummy_date = entry_date;
                            Map mapObj = new HashMap();
                            Map phaseObj = new HashMap();
                            Map taskObj = new HashMap();
                            Map roleObj = new HashMap();
                            Map locationObj = new HashMap();
                            TimesheetEntryDTO obj = new TimesheetEntryDTO();
                            TimesheetEntryDTO leaveObj = new TimesheetEntryDTO();

                            if (leaveData.containsKey(entry_date)) {

                                if (leaveData.get(entry_date).equals(false) && !(halfDayLeaveFlag.equals(entry_date))) {
                                    halfDayLeaveFlag = entry_date;
                                    oSearchTimeSheet.setTimesheet_date(entry_date);
                                    cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, dateCount + 1);
                                    leaveObj.setPhase_name("");
                                    leaveObj.setTask_name("");
                                    leaveObj.setProject_name("");
                                    leaveObj.setShift("");
                                    leaveObj.setRemarks("");
                                    leaveObj.setRejected_remarks("");
                                    leaveObj.setHoliday_description("");
                                    leaveObj.setRole_name("");
                                    leaveObj.setLocation("");
                                    leaveObj.setTimesheet_date(dateCount + 1 + "");
                                    leaveObj.setTimesheet_day(dayNames[cal.get(Calendar.DAY_OF_WEEK)]);
                                    leaveObj.setTimesheet_datex(entry_date);
                                    leaveObj.setTimesheet_id("");
                                    leaveObj.setStatus("");
                                    leaveObj.setWorked_hours("");
                                    leaveObj.setWorked_minutes("");
                                    SearchCriteriaDTO oSearchHours = new SearchCriteriaDTO();
                                    oSearchHours.setEmployeeNumber(employeeInfo.getEmployee_number());
                                    oSearchHours.setEmployeeId(employee_id);
                                    oSearchHours.setTimesheet_date(entry_date);
                                      
                                    String sAvailableHours = dao.getAvailableHours_New(oSearchHours);
                                    if (sAvailableHours != null && !sAvailableHours.equals("")) {
                                        leaveObj.setAvailable_hours(sAvailableHours);
                                    } else {
                                        leaveObj.setAvailable_hours("---");
                                    }
                                    
                                    String sOfficeHours = dao.getOfficeHours_New(oSearchHours);
                                    if (sOfficeHours != null && !sOfficeHours.equals("")) {
                                        obj.setOffice_hours(sOfficeHours);
                                    } else {
                                        obj.setOffice_hours("---");
                                    }

                                    leaveObj.setLeave("0");
                                    if (leaveData.containsKey(entry_date)) {
                                        leaveObj.setLeave("1");
                                        if (leaveData.get(entry_date).equals(true)) {
                                            leaveObj.setLeave_status("0");
                                        } else {
                                            leaveObj.setLeave_status("1");
                                        }
                                    }
                                    String sWorkLocation = dao.getWorkLocationOfEmployee(oSearchHours);
                                    String sAttendanceHours = dao.getAttendanceHours_New(oSearchHours);

                                    if (sWorkLocation.equals("1")) {
                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                            leaveObj.setAttendance_hours(sAttendanceHours);
                                        } else {
                                            leaveObj.setAttendance_hours("---");
                                        }
                                    } else {
                                        if (!leaveObj.getLeave_status().equals("1")) {
                                            leaveObj.setAttendance_hours("---");
                                        } else {
                                            String[] valFromDB = leaveObj.getAvailable_hours().split(":");
                                            int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                            int hrs = hrsValue / 2;
                                            int h = hrs / 60;
                                            int mins = hrs % 60;
                                            String hr = String.valueOf(h);
                                            String mn = String.valueOf(mins);
                                            String fV = (hr.length() == 1 ? "0" + hr : hr);
                                            fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                            leaveObj.setAttendance_hours(fV);
                                        }
                                    }
                                    leaveObj.setReg_reason_list(reg_reason_list);
                                    leaveObj.setPhaseMap(phaseObj);
                                    leaveObj.setTaskMap(taskObj);
                                    leaveObj.setRoleMap(roleObj);
                                    leaveObj.setLocationMap(locationObj);
                                    leaveObj.setDate_display("<html><font color='green' ; style = 'font-weight: bold;'>" + leaveObj.getTimesheet_date() + "-" + leaveObj.getTimesheet_day() + "</font></html>");
                                    leaveObj.setStatus_text("<html><font color='red' ; style = 'font-weight: bold;'>Halfday Leave</font></html>");
                                    formulatedDataObject.add(leaveObj);
                                }
                            }

                            if (!projectFlag) {
                                // Retrieve projects whose duration falls on the timesheet date
                                oSearchTimeSheet.setGlobal_projects("0");
                                List<ProjectDTO> projectx = dao.getProjects_New1(oSearchTimeSheet);

                                for (int prj = 0; prj < projectx.size(); prj++) {
                                    mapObj.put(projectx.get(prj).getProject_id(), projectx.get(prj).getProject_name());
                                }
                            }
                            
                            List<PhaseDTO> phaseData = null;
                            List<TaskDTO> taskData = null;
                            List<RoleDTO> roleData = null;

                            SearchCriteriaDTO objSearchTaskAndPhase = new SearchCriteriaDTO();
                            if (outputTimeSheetData.get(j).getProject_id().equals("Non_Project_Activity")) {
                                phaseData = dao.getPhases_New(objSearchTaskAndPhase);
                                objSearchTaskAndPhase.setProjectId("Non_Project_Activity");
                                objSearchTaskAndPhase.setPhaseId(outputTimeSheetData.get(j).getPhase_id());
                                taskData = dao.getTasks_New(objSearchTaskAndPhase);

                                locationObj.put("o", "Onsite");
                                locationObj.put("s", "Offshore");
                            } else {
                                objSearchTaskAndPhase.setProjectId(outputTimeSheetData.get(j).getProject_id());
                                objSearchTaskAndPhase.setTimesheet_date(entry_date);
                                objSearchTaskAndPhase.setProjectPhase(outputTimeSheetData.get(j).getPhase_id());
                                phaseData = dao.getPhases_New(objSearchTaskAndPhase);

                                if (outputTimeSheetData.get(j).getPhase_id() != null && !outputTimeSheetData.get(j).getPhase_id().equals("Non billable activity")) {

                                    objSearchTaskAndPhase.setPhaseId(outputTimeSheetData.get(j).getPhase_id());
                                    if (!outputTimeSheetData.get(j).getPhase_id().equals("Others")) {
                                        PhaseDTO phaseValue = dao.getPhaseDetail(objSearchTaskAndPhase);
                                        objSearchTaskAndPhase.setPhaseId(phaseValue.getPhase_id());
                                    }

                                    taskData = dao.getTasks_New(objSearchTaskAndPhase);
                                }

                                objSearchTaskAndPhase.setEmployeeId(employee_id);
                                roleData = dao.getRoles_New(objSearchTaskAndPhase);
                                for (int lc = 0; lc < roleData.size(); lc++) {
                                    roleObj.put(roleData.get(lc).getRole_id(), roleData.get(lc).getRole_name());
                                    if (roleData.get(lc).getLocation().equals("o")) {
                                        locationObj.put(roleData.get(lc).getLocation(), "Onsite");
                                    } else {
                                        locationObj.put(roleData.get(lc).getLocation(), "Offshore");
                                    }
                                }
                            }
                            if (phaseData != null) {
                                for (int ph = 0; ph < phaseData.size(); ph++) {
                                    phaseObj.put(phaseData.get(ph).getPhase_id(), phaseData.get(ph).getPhase_name());
                                }
                            }

                            if (taskData != null) {
                                for (int tk = 0; tk < taskData.size(); tk++) {
                                    taskObj.put(taskData.get(tk).getTask_id(), taskData.get(tk).getTask_name());
                                }
                            }
                            if (outputTimeSheetData.get(j).getPhase_id() != null && outputTimeSheetData.get(j).getPhase_id().equals("Non billable activity")) {
                                taskObj.put("Non billable activity", "Non billable activity");
                            }
                            if (holidayDates.contains(entry_date)) {
                                obj.setHoliday_description((String) holidayData.get(entry_date));
                            } else {
                                obj.setHoliday_description("");
                            }
                            if (Integer.parseInt(month) <= 9) {
                                if (!leaveData.containsKey(entry_date)) {
                                    entry_date += "-" + "0" + Integer.parseInt(month);
                                }
                            } else {
                                if (!leaveData.containsKey(entry_date)) {
                                    entry_date += "-" + Integer.parseInt(month);
                                }
                            }
                            if (dateCount + 1 <= 9) {
                                if (!leaveData.containsKey(entry_date)) {
                                    entry_date += "-" + "0" + (dateCount + 1);
                                }
                            } else {
                                if (!leaveData.containsKey(entry_date)) {
                                    entry_date += "-" + (dateCount + 1);
                                }
                            }
                            cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, dateCount + 1);
                            obj.setTimesheet_date(dateCount + 1 + "");
                            obj.setTimesheet_day(dayNames[cal.get(Calendar.DAY_OF_WEEK)]);
                            obj.setTimesheet_id(outputTimeSheetData.get(j).getTime_sheet_id());
                            obj.setTimesheet_datex(entry_date);

                            obj.setStatus(outputTimeSheetData.get(j).getStatus());

                            obj.setEffective_date(outputTimeSheetData.get(j).getEffective_date());

                            if (outputTimeSheetData.get(j).getAvailable_hours() == null) {
                                obj.setAvailable_hours("---");
                            } else {
                                obj.setAvailable_hours(outputTimeSheetData.get(j).getAvailable_hours());
                            }
                            if (outputTimeSheetData.get(j).getAvailable_hours() == null) {
                                obj.setOffice_hours("---");
                            } else {
                                obj.setOffice_hours(outputTimeSheetData.get(j).getOffice_hours());
                            }
                            String[] worked_time_arr = outputTimeSheetData.get(j).getWorked_hours().split(":");
                            obj.setWorked_hours(worked_time_arr[0]);
                            try {
                                obj.setWorked_minutes(worked_time_arr[1]);
                            } catch (Exception e) {
                                obj.setWorked_minutes("00");
                            }
                            obj.setWeek_day(cal.get(Calendar.DAY_OF_WEEK));
                            obj.setRole_name(outputTimeSheetData.get(j).getRole_name());
                            obj.setLocation(outputTimeSheetData.get(j).getLocation());
                            
                            obj.setProject_id(outputTimeSheetData.get(j).getProject_id());
                            
                            obj.setPhase_id(outputTimeSheetData.get(j).getPhase_id());
                            obj.setTask_id(outputTimeSheetData.get(j).getTask_id());
                            obj.setRole_id(outputTimeSheetData.get(j).getRole_id());
                            obj.setProject_name(outputTimeSheetData.get(j).getProject_name());
                            obj.setRemarks(outputTimeSheetData.get(j).getRemarks());
                            obj.setRejected_remarks(outputTimeSheetData.get(j).getRejected_remarks());
                            
                            obj.setRegularization_reason(outputTimeSheetData.get(j).getRegularization_reason());
                            
                            obj.setDate_display(obj.getTimesheet_date() + "-" + obj.getTimesheet_day());

                            obj.setLeave("0");
                            if (leaveData.containsKey(entry_date)) {
                                obj.setLeave("1");
                                if (leaveData.get(entry_date).equals(true)) {
                                    obj.setLeave_status("0");
                                } else {
                                    obj.setLeave_status("1");
                                }
                            }
                            SearchCriteriaDTO oSearchHours = new SearchCriteriaDTO();
                            oSearchHours.setEmployeeNumber(employeeInfo.getEmployee_number());
                            oSearchHours.setEmployeeId(employee_id);
                            oSearchHours.setTimesheet_date(entry_date);

                            String sWorkLocation = dao.getWorkLocationOfEmployee(oSearchHours);
                            String sGet_WFH_Eligible = dao.get_WFH_Eligible(oSearchHours);
                            reg_reason_list = dao.getConfiguration("emp_reason_list");
                            if (sGet_WFH_Eligible.equals("0")) {
                                Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                while (reasonList.hasNext()) {
                                    ConfigurationDTO c = reasonList.next();
                                    if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                        reasonList.remove();
                                    }
                                }
                                obj.setReg_reason_list(reg_reason_list);
                            } else {
                                obj.setReg_reason_list(reg_reason_list);
                            }
                            if (!projectFlag) {
                                obj.setProjectMap(mapObj);
                            } else {
                                obj.setProjectMap(mapObjGlobal);
                            }
                            obj.setPhaseMap(phaseObj);
                            obj.setTaskMap(taskObj);
                            obj.setRoleMap(roleObj);
                            obj.setLocationMap(locationObj);
                            obj.setShift(outputTimeSheetData.get(j).getShift());
                            if (halfDayLeaveFlag.equals(entry_date) && (obj.getStatus().equals("m") || obj.getStatus().equals("a"))) {
                                obj.setIsSubmitted("Y");
                            } else {
                                obj.setIsSubmitted("N");
                            }
                            for (int statusIndex = 0; statusIndex < statusList.length; statusIndex++) {
                                if (outputTimeSheetData.get(j).getStatus().equals(statusList[statusIndex])) {
                                    if (outputTimeSheetData.get(j).getStatus().equals("a")) {
                                        obj.setStatus_text("<html><font color='green'; style = 'font-weight: bold;'>" + statusConfigValues.get(statusIndex).getConfiguration_value() + "</font></html>");
                                    }
                                    if (outputTimeSheetData.get(j).getStatus().equals("m")) {
                                        obj.setStatus_text("<html><font color='#FF00FF'; style = 'font-weight: bold;'>" + statusConfigValues.get(statusIndex).getConfiguration_value() + "</font></html>");
                                    }
                                    if (outputTimeSheetData.get(j).getStatus().equals("s")) {
                                        obj.setStatus_text("<html><font color='orange'; style = 'font-weight: bold;'>" + statusConfigValues.get(statusIndex).getConfiguration_value() + "</font></html>");
                                    }
                                    if (outputTimeSheetData.get(j).getStatus().equals("r")) {
                                        obj.setStatus_text("<html><font color='blue' ; style = 'font-weight: bold;'>" + statusConfigValues.get(statusIndex).getConfiguration_value() + "</font></html>");
                                    }
                                }
                            }
                         
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date entry_date_formatted = (Date) dateFormat.parse(entry_date);
                            long entryTemp = (entry_date_formatted.getTime() + 1 * 24 * 3600 * 1000);
                            for (int i = 0; i < weekEndList.size(); i++) {
                                String sDate = weekEndList.get(i).getStart_date();
                                String eDate = weekEndList.get(i).getEnd_date();
                                String weekEnd = weekEndList.get(i).getLeaveday();
                                String weekEndTemp[] = weekEnd.split(",");
                                int tempWeekEnds[] = new int[weekEndTemp.length];
                                for (int l = 0; l < weekEndTemp.length; l++) {
                                    tempWeekEnds[l] = Integer.parseInt(weekEndTemp[l]);
                                }
                                Date sFormattedDate = (Date) dateFormat.parse(sDate);
                                Date eFormattedDate = (Date) dateFormat.parse(eDate);
                                long sTemp = (sFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
                                long eTemp = (eFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
                                if ((sTemp >= entryTemp) || (entryTemp <= eTemp)) {
                                    int flag = 0;
                                    for (int weekEndIndex = 0; weekEndIndex < tempWeekEnds.length; weekEndIndex++) {
                                        if (flag == 0) {
                                            if (tempWeekEnds[weekEndIndex] == cal.get(Calendar.DAY_OF_WEEK)) {
                                                if(exceptionalWeekend.containsKey(dummy_date)){
                                                    obj.setIsWeekEnd("N");
                                                    if (sWorkLocation.equals("1")) {
                                                        if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                            obj.setAttendance_hours("---");
                                                        } else {
                                                            obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                        }
                                                    } else {
                                                        obj.setAttendance_hours("---");
                                                    }
                                                    obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;' >" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                }else{
                                                    obj.setIsWeekEnd("Y");
                                                    obj.setAvailable_hours("---");
                                                    obj.setOffice_hours("---");
                                                    Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                    while (reasonList.hasNext()) {
                                                        ConfigurationDTO c = reasonList.next();
                                                        if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                            reasonList.remove();
                                                        }
                                                    }
                                                    obj.setReg_reason_list(reg_reason_list);
                                                    obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                    if (sWorkLocation.equals("1")) {
                                                        if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                            obj.setAttendance_hours("---");
                                                        } else {
                                                            obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                        }
                                                    } else {
                                                        obj.setAttendance_hours("---");
                                                    }
                                                }
//                                                obj.setIsWeekEnd("Y");
//                                                obj.setAvailable_hours("---");
//                                                obj.setOffice_hours("---");
//                                                Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                                while (reasonList.hasNext()) {
//                                                    ConfigurationDTO c = reasonList.next();
//                                                    if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                        reasonList.remove();
//                                                    }
//                                                }
////                                                reg_reason_list = (List<ConfigurationDTO>) reasonList;
//                                                obj.setReg_reason_list(reg_reason_list);
//                                                obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
//                                                if (sWorkLocation.equals("1")) {
//                                                    if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
//                                                        obj.setAttendance_hours("---");
//                                                    } else {
//                                                        obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
//                                                    }
//                                                } else {
//                                                    obj.setAttendance_hours("---");
//                                                }
                                                i = weekEndList.size();
                                                flag++;
                                            } else {
                                                obj.setIsWeekEnd("N");
                                                obj.setDate_display("<html><font color='green' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                if (obj.getLeave().equals("1")) {
                                                    if (obj.getLeave_status().equals("0")) {
                                                        obj.setAttendance_hours("---");
                                                        i = weekEndList.size();
                                                    } else {
                                                        if (sWorkLocation.equals("1")) {
                                                            if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                                obj.setAttendance_hours("---");
                                                            } else {
                                                                obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                            }
                                                        } else {
                                                            String[] valFromDB = obj.getAvailable_hours().split(":");
                                                            if (!valFromDB[0].equals("---")) {
                                                                int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                                int hrs = hrsValue / 2;
                                                                int h = hrs / 60;
                                                                int mins = hrs % 60;
                                                                String hr = String.valueOf(h);
                                                                String mn = String.valueOf(mins);
                                                                String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                                fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                                obj.setAttendance_hours(fV);
                                                            } else {
                                                                obj.setAttendance_hours("---");
                                                            }
                                                        }
                                                        i = weekEndList.size();
                                                    }
                                                } else {
                                                    if (holidayData.containsKey(entry_date)) {
                                                        obj.setHoliday_description((String) holidayData.get(entry_date));
                                                    }
                                                    if (obj.getHoliday_description().equals("")) {
                                                        obj.setReg_reason_list(reg_reason_list);
                                                        if (sWorkLocation.equals("1")) {
                                                            if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                                obj.setAttendance_hours("---");
                                                            } else {
                                                                obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                            }
                                                        } else {
                                                            obj.setAttendance_hours(obj.getAvailable_hours());
                                                        }
                                                        i = weekEndList.size();
                                                    } else {
                                                        if(exceptionalWeekend.containsKey(dummy_date)){
                                                            obj.setHoliday_description("");
                                                            obj.setReg_reason_list(reg_reason_list);
                                                            if (sWorkLocation.equals("1")) {
                                                                if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                                    obj.setAttendance_hours("---");
                                                                } else {
                                                                    obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                                }
                                                            } else {
                                                                obj.setAttendance_hours(obj.getAvailable_hours());
                                                            }
                                                        }else{
                                                            obj.setAvailable_hours("---");
                                                            obj.setOffice_hours("---");
                                                            Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                            while (reasonList.hasNext()) {
                                                                ConfigurationDTO c = reasonList.next();
                                                                if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                                    reasonList.remove();
                                                                }
                                                            }
                                                            obj.setReg_reason_list(reg_reason_list);
                                                            if (sWorkLocation.equals("1")) {
                                                                if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
                                                                    obj.setAttendance_hours("---");
                                                                } else {
                                                                    obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
                                                                }
                                                            } else {
                                                                String[] valFromDB = obj.getAvailable_hours().split(":");
                                                                if (!valFromDB[0].equals("---")) {
                                                                    int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                                    int hrs = hrsValue / 2;
                                                                    int h = hrs / 60;
                                                                    int mins = hrs % 60;
                                                                    String hr = String.valueOf(h);
                                                                    String mn = String.valueOf(mins);
                                                                    String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                                    fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                                    obj.setAttendance_hours(fV);
                                                                } else {
                                                                    obj.setAttendance_hours("---");
                                                                }
                                                            }
                                                            obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                        }
                                                        
//                                                        obj.setAvailable_hours("---");
//                                                        obj.setOffice_hours("---");
//                                                        Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                                        while (reasonList.hasNext()) {
//                                                            ConfigurationDTO c = reasonList.next();
//                                                            if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                                reasonList.remove();
//                                                            }
//                                                        }
//                                                        obj.setReg_reason_list(reg_reason_list);
//                                                        if (sWorkLocation.equals("1")) {
//                                                            if (outputTimeSheetData.get(j).getAttendance_hours() == null) {
//                                                                obj.setAttendance_hours("---");
//                                                            } else {
//                                                                obj.setAttendance_hours(outputTimeSheetData.get(j).getAttendance_hours());
//                                                            }
//                                                        } else {
//                                                            String[] valFromDB = obj.getAvailable_hours().split(":");
//                                                            if (!valFromDB[0].equals("---")) {
//                                                                int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
//                                                                int hrs = hrsValue / 2;
//                                                                int h = hrs / 60;
//                                                                int mins = hrs % 60;
//                                                                String hr = String.valueOf(h);
//                                                                String mn = String.valueOf(mins);
//                                                                String fV = (hr.length() == 1 ? "0" + hr : hr);
//                                                                fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
//                                                                obj.setAttendance_hours(fV);
//                                                            } else {
//                                                                obj.setAttendance_hours("---");
//                                                            }
//                                                        }
//                                                        obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                        i = weekEndList.size();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            obj.setLeave("0");
                            obj.setLeave_status(null);
                          
                            formulatedDataObject.add(obj);
                        }
                    }
                    dateCount = Integer.parseInt(timesheet_date_arr[2]);
                    completed_count = Integer.parseInt(timesheet_date_arr[2]);
                }
            } else {
                bTSDataNotExistsflag = true;
            }
            //Framing initially
            if (status == null || status.equalsIgnoreCase("all") || status.equals("")) {
                if (bTSDataNotExistsflag || completed_count != 0) {
                    if (completed_count != 0) {
                        minDay = completed_count + 1;
                    }
                    for (int j = minDay; j <= maxDay; j++) {
                        String entry_date = year;
                        if (Integer.parseInt(month) <= 9) {
                            entry_date += "-" + "0" + Integer.parseInt(month);
                        } else {
                            entry_date += "-" + Integer.parseInt(month);
                        }
                        if (j <= 9) {
                            entry_date += "-" + "0" + j;
                        } else {
                            entry_date += "-" + j;
                        }
                        oSearchTimeSheet.setTimesheet_date(entry_date);

                        Map mapObj = new HashMap();
                        Map phaseObj = new HashMap();
                        Map taskObj = new HashMap();
                        Map roleObj = new HashMap();
                        Map locationObj = new HashMap();

                        if (!projectFlag) {
                            // Retrieve projects whose duration falls on the timesheet date
                            oSearchTimeSheet.setGlobal_projects("0");
                            List<ProjectDTO> project = dao.getProjects_New1(oSearchTimeSheet);
                            for (int prj = 0; prj < project.size(); prj++) {
                                mapObj.put(project.get(prj).getProject_id(), project.get(prj).getProject_name());
                            }
                        }

                        TimesheetEntryDTO obj = new TimesheetEntryDTO();
                        obj.setPhase_name("");
                        obj.setTask_name("");
                        obj.setProject_name("");
                        obj.setShift("");
                        obj.setRemarks("");
                        obj.setRejected_remarks("");
                        obj.setHoliday_description("");
                        cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, j);
                        obj.setTimesheet_date(j + "");
                        obj.setTimesheet_day(dayNames[cal.get(Calendar.DAY_OF_WEEK)]);
                        obj.setTimesheet_datex(year + "-" + Integer.parseInt(month) + "-" + j);
                        obj.setTimesheet_id("");
                        obj.setStatus("");
                        obj.setWorked_hours("");
                        obj.setWorked_minutes("");
                        
                        SearchCriteriaDTO oSearchHours = new SearchCriteriaDTO();
                        oSearchHours.setEmployeeNumber(employeeInfo.getEmployee_number());
                        oSearchHours.setEmployeeId(employee_id);
                        oSearchHours.setTimesheet_date(entry_date);
                                                  
                        obj.setDate_display(obj.getTimesheet_date() + "-" + obj.getTimesheet_day());
                        obj.setWeek_day(cal.get(Calendar.DAY_OF_WEEK));

                        obj.setLeave("0");
                        if (leaveData.containsKey(entry_date)) {
                            obj.setLeave("1");
                            if (leaveData.get(entry_date).equals(true)) {
                                obj.setLeave_status("0");
                            } else {
                                obj.setLeave_status("1");
                            }
                        }
                        String sAvailableHours = dao.getAvailableHours_New(oSearchHours);
                        if (sAvailableHours != null && !sAvailableHours.equals("")) {
                            obj.setAvailable_hours(sAvailableHours);
                        } else {
                            obj.setAvailable_hours("---");
                        }
                        String sOfficeHours = dao.getOfficeHours_New(oSearchHours);
                        if (sOfficeHours != null && !sOfficeHours.equals("")) {
                            obj.setOffice_hours(sOfficeHours);
                        } else {
                            obj.setOffice_hours("---");
                        }
                        
                        String sWorkLocation = dao.getWorkLocationOfEmployee(oSearchHours);
                        String sAttendanceHours = dao.getAttendanceHours_New(oSearchHours);
                        String sGet_WFH_Eligible = dao.get_WFH_Eligible(oSearchHours);
                        reg_reason_list = dao.getConfiguration("emp_reason_list");
                        if (sGet_WFH_Eligible.equals("0")) {
                            Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                            while (reasonList.hasNext()) {
                                ConfigurationDTO c = reasonList.next();
                                if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                    reasonList.remove();
                                }
                            }
                            obj.setReg_reason_list(reg_reason_list);
                        } else {
                            obj.setReg_reason_list(reg_reason_list);
                        }
                        if (!projectFlag) {
                            obj.setProjectMap(mapObj);
                        } else {
                            obj.setProjectMap(mapObjGlobal);
                        }
                        obj.setPhaseMap(phaseObj);
                        obj.setTaskMap(taskObj);
                        obj.setRoleMap(roleObj);
                        obj.setLocationMap(locationObj);
                        if (halfDayLeaveFlag.equals(entry_date) && (obj.getStatus().equals("m") || obj.getStatus().equals("a"))) {
                            obj.setIsSubmitted("Y");
                        } else {
                            obj.setIsSubmitted("N");
                        }

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date entry_date_formatted = (Date) dateFormat.parse(entry_date);
                        long entryTemp = (entry_date_formatted.getTime() + 1 * 24 * 3600 * 1000);
                        for (int i = 0; i < weekEndList.size(); i++) {
                            String sDate = weekEndList.get(i).getStart_date();
                            String eDate = weekEndList.get(i).getEnd_date();
                            String weekEnd = weekEndList.get(i).getLeaveday();
                            String weekEndTemp[] = weekEnd.split(",");
                            int tempWeekEnds[] = new int[weekEndTemp.length];
                            for (int l = 0; l < weekEndTemp.length; l++) {
                                tempWeekEnds[l] = Integer.parseInt(weekEndTemp[l]);
                            }
                            Date sFormattedDate = (Date) dateFormat.parse(sDate);
                            Date eFormattedDate = (Date) dateFormat.parse(eDate);
                            long sTemp = (sFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
                            long eTemp = (eFormattedDate.getTime() + 1 * 24 * 3600 * 1000);
                            if ((sTemp >= entryTemp) || (entryTemp <= eTemp)) {
                                int flag = 0;
                                for (int weekEndIndex = 0; weekEndIndex < tempWeekEnds.length; weekEndIndex++) {

                                    if (flag == 0) {
                                        if (tempWeekEnds[weekEndIndex] == cal.get(Calendar.DAY_OF_WEEK)) {
                                            if(exceptionalWeekend.containsKey(entry_date)){
                                                if (obj.getLeave().equals("1")) {
                                                    if (obj.getLeave_status().equals("0")) {
                                                        obj.setAttendance_hours("---");
                                                        obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Leave Applied</font></html>");
                                                        i = weekEndList.size();
                                                    } else {
                                                        obj.setReg_reason_list(reg_reason_list);
                                                        if (sWorkLocation.equals("1")) {
                                                            if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                obj.setAttendance_hours(sAttendanceHours);
                                                            } else {
                                                                obj.setAttendance_hours("---");
                                                            }
                                                        } else {
                                                            String[] valFromDB = obj.getAvailable_hours().split(":");
                                                            int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                            int hrs = hrsValue / 2;
                                                            int h = hrs / 60;
                                                            int mins = hrs % 60;
                                                            String hr = String.valueOf(h);
                                                            String mn = String.valueOf(mins);
                                                            String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                            fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                            obj.setAttendance_hours(fV);
                                                        }
                                                        obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Halfday Leave</font></html>");
                                                        i = weekEndList.size();
                                                    }
                                                }else{
                                                    obj.setLeave(null);
                                                    obj.setIsWeekEnd("N");
                                                    if (sWorkLocation.equals("1")) {
                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                            obj.setAttendance_hours(sAttendanceHours);
                                                        } else {
                                                            obj.setAttendance_hours("---");
                                                        }
                                                    } else {
                                                        obj.setAttendance_hours("---");
                                                    }
                                                    obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;' >" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                }
                                                
                                                
                                            }else{
                                                obj.setLeave(null);
                                                obj.setIsWeekEnd("Y");
                                                obj.setAvailable_hours("---");
                                                obj.setOffice_hours("---");
                                                Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                while (reasonList.hasNext()) {
                                                    ConfigurationDTO c = reasonList.next();
                                                    if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                        reasonList.remove();
                                                    }
                                                }
                                                obj.setReg_reason_list(reg_reason_list);
                                                obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Week End</font></html>");
                                                if (sWorkLocation.equals("1")) {
                                                    if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                        obj.setAttendance_hours(sAttendanceHours);
                                                    } else {
                                                        obj.setAttendance_hours("---");
                                                    }
                                                } else {
                                                    obj.setAttendance_hours("---");
                                                }
                                            }
                                            
//                                            obj.setLeave(null);
//                                            obj.setIsWeekEnd("Y");
//                                            obj.setAvailable_hours("---");
//                                            obj.setOffice_hours("---");
//                                            Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                            while (reasonList.hasNext()) {
//                                                ConfigurationDTO c = reasonList.next();
//                                                if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                    reasonList.remove();
//                                                }
//                                            }
////                                            reg_reason_list = (List<ConfigurationDTO>) reasonList;
//                                            obj.setReg_reason_list(reg_reason_list);
//                                            obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
//                                            obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Week End</font></html>");
//                                            if (sWorkLocation.equals("1")) {
//                                                if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
//                                                    obj.setAttendance_hours(sAttendanceHours);
//                                                } else {
//                                                    obj.setAttendance_hours("---");
//                                                }
//                                            } else {
//                                                obj.setAttendance_hours("---");
//                                            }
                                            i = weekEndList.size();
                                            flag++;
                                        } else {
                                            obj.setIsWeekEnd("N");
                                            obj.setDate_display("<html><font color='green'; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                            if (obj.getLeave().equals("1")) {
                                                if (obj.getLeave_status().equals("0")) {
                                                    obj.setAttendance_hours("---");
                                                    obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Leave Applied</font></html>");
                                                    i = weekEndList.size();
                                                } else {
                                                    obj.setReg_reason_list(reg_reason_list);
                                                    if (sWorkLocation.equals("1")) {
                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                            obj.setAttendance_hours(sAttendanceHours);
                                                        } else {
                                                            obj.setAttendance_hours("---");
                                                        }
                                                    } else {
                                                        String[] valFromDB = obj.getAvailable_hours().split(":");
                                                        int hrsValue = (Integer.parseInt(valFromDB[0]) * 60) + Integer.parseInt(valFromDB[1]);
                                                        int hrs = hrsValue / 2;
                                                        int h = hrs / 60;
                                                        int mins = hrs % 60;
                                                        String hr = String.valueOf(h);
                                                        String mn = String.valueOf(mins);
                                                        String fV = (hr.length() == 1 ? "0" + hr : hr);
                                                        fV = fV + ":" + (mn.length() == 1 ? "0" + mn : mn);
                                                        obj.setAttendance_hours(fV);
                                                    }
                                                    obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>Halfday Leave</font></html>");
                                                    i = weekEndList.size();
                                                }
                                            } else {
                                                if (holidayData.containsKey(entry_date)) {
                                                    obj.setHoliday_description((String) holidayData.get(entry_date));
                                                }
                                                if (obj.getHoliday_description().equals("")) {
                                                    obj.setReg_reason_list(reg_reason_list);
                                                    if (sWorkLocation.equals("1")) {
                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                            obj.setAttendance_hours(sAttendanceHours);
                                                        } else {
                                                            obj.setAttendance_hours("---");
                                                        }
                                                    } else {
                                                        obj.setAttendance_hours(obj.getAvailable_hours());
                                                    }
                                                    obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>To be submitted</font></html>");
                                                    i = weekEndList.size();
                                                } else {
                                                    if(exceptionalWeekend.containsKey(entry_date)){
                                                        obj.setHoliday_description("");
                                                        obj.setReg_reason_list(reg_reason_list);
                                                        if (sWorkLocation.equals("1")) {
                                                            if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                obj.setAttendance_hours(sAttendanceHours);
                                                            } else {
                                                                obj.setAttendance_hours("---");
                                                            }
                                                        } else {
                                                            obj.setAttendance_hours("---");
                                                        }
                                                        obj.setDate_display("<html><font color='green' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                        obj.setStatus_text("<html><font color='red'; style = 'font-weight: bold;'>To be submitted</font></html>");
                                                    }else{
                                                        if (sWorkLocation.equals("1")) {
                                                            if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
                                                                obj.setAttendance_hours(sAttendanceHours);
                                                            } else {
                                                                obj.setAttendance_hours("---");
                                                            }
                                                        } else {
                                                            obj.setAttendance_hours("---");
                                                        }
                                                        Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
                                                        while (reasonList.hasNext()) {
                                                            ConfigurationDTO c = reasonList.next();
                                                            if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
                                                                reasonList.remove();
                                                            }
                                                        }
                                                        obj.setReg_reason_list(reg_reason_list);
                                                        obj.setAvailable_hours("---");
                                                        obj.setOffice_hours("---");
                                                        obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
                                                        obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Holiday</font></html>");
                                                    }
                                                    
//                                                    if (sWorkLocation.equals("1")) {
//                                                        if (sAttendanceHours != null && !sAttendanceHours.equals("")) {
//                                                            obj.setAttendance_hours(sAttendanceHours);
//                                                        } else {
//                                                            obj.setAttendance_hours("---");
//                                                        }
//                                                    } else {
//                                                        obj.setAttendance_hours("---");
//                                                    }
//                                                    Iterator<ConfigurationDTO> reasonList = reg_reason_list.iterator();
//                                                    while (reasonList.hasNext()) {
//                                                        ConfigurationDTO c = reasonList.next();
//                                                        if (c.getConfiguration_value().equalsIgnoreCase("Work From Home")) {
//                                                            reasonList.remove();
//                                                        }
//                                                    }
////                                                    reg_reason_list = (List<ConfigurationDTO>) reasonList;
//                                                    obj.setReg_reason_list(reg_reason_list);
//                                                    obj.setAvailable_hours("---");
//                                                    obj.setOffice_hours("---");
//                                                    obj.setDate_display("<html><font color='red' ; style = 'font-weight: bold;'>" + obj.getTimesheet_date() + "-" + obj.getTimesheet_day() + "</font></html>");
//                                                    obj.setStatus_text("<html><font color='#666666'; style = 'font-weight: bold;'>Holiday</font></html>");
                                                    i = weekEndList.size();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        formulatedDataObject.add(obj);
                    }
                }
            }
            shift_list = dao.getConfiguration("shift_list");

        } catch (Exception e) {
            System.out.print("exception occurred : " + e.getMessage());
            e.printStackTrace();
        }

        mv.addObject("timeSheetObj", formulatedDataObject);
        mv.addObject("shift_list", shift_list);

        return mv;
    }

    public synchronized ModelAndView loadTask(HttpServletRequest request, HttpServletResponse response) {
        try {
          
            TimesheetDTO form_data = new TimesheetDTO();
            mv = new ModelAndView("/index");
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String phase = request.getParameter("phase");
            String project = request.getParameter("project");

            form_data.setPhase(phase);
            form_data.setProject(project);
            List<TimesheetDTO> data = null;
            if (project.equals("Non_Project_Activity")) {
                data = dao.getNonTasks(form_data);
            } else {
                String phaseValue = dao.getPhaseId(form_data);
                form_data.setPhase(phaseValue);
                data = dao.getTasks(form_data);
            }
            for (int k = 0; k < data.size(); k++) {
                response.getOutputStream().write(data.get(k).getTask_id().getBytes());
                response.getOutputStream().write(",".getBytes());
                response.getOutputStream().write(data.get(k).getTask_name().getBytes());
                response.getOutputStream().write(":".getBytes());
            }
            if (phase.equals("Non billable activity")) {
                response.getOutputStream().write("Non billable activity".getBytes());
                response.getOutputStream().write(",".getBytes());
                response.getOutputStream().write("Non Billable Activity".getBytes());
                response.getOutputStream().write(":".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized ModelAndView loadAllActivities(HttpServletRequest request, HttpServletResponse response) {
        try {
          
            TimesheetDTO form_data = new TimesheetDTO();
            mv = new ModelAndView("/index");
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String project = request.getParameter("project");
            String employee_id = request.getParameter("employee");
            String entry_date = request.getParameter("entry_date");
            form_data.setProject(project);
            form_data.setEmployeeId(employee_id);
            form_data.setEntry_date(entry_date);
            List<TimesheetDTO> data = null;
            if (!project.equals("Non_Project_Activity")) {
                data = dao.getRoles(form_data);
                for (int k = 0; k < data.size(); k++) {
                    response.getOutputStream().write(data.get(k).getRole_id().getBytes());
                    response.getOutputStream().write(",".getBytes());
                    response.getOutputStream().write(data.get(k).getRole_name().getBytes());
                    response.getOutputStream().write(":".getBytes());
                }
                response.getOutputStream().write("@#@".getBytes());
                for (int k = 0; k < data.size(); k++) {
                    response.getOutputStream().write(data.get(k).getLocation().getBytes());
                    response.getOutputStream().write(",".getBytes());
                    if (data.get(k).getLocation().equals("o")) {
                        response.getOutputStream().write("Onsite".getBytes());
                    } else {
                        response.getOutputStream().write("Offshore".getBytes());
                    }
                    response.getOutputStream().write(":".getBytes());
                }
                response.getOutputStream().write("@#@".getBytes());
                data = dao.getPhases(form_data);
                for (int k = 0; k < data.size(); k++) {
                    response.getOutputStream().write(data.get(k).getPhase_id().getBytes());
                    response.getOutputStream().write(",".getBytes());
                    response.getOutputStream().write(data.get(k).getPhase_name().getBytes());
                    response.getOutputStream().write(":".getBytes());
                }
            } else {
                data = dao.getNonPhases(form_data);
                for (int k = 0; k < data.size(); k++) {
                    response.getOutputStream().write(data.get(k).getPhase_id().getBytes());
                    response.getOutputStream().write(",".getBytes());
                    response.getOutputStream().write(data.get(k).getPhase_name().getBytes());
                    response.getOutputStream().write(":".getBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized ModelAndView timesheetSave_New(HttpServletRequest request, HttpServletResponse response, TimesheetEntryDTO oInputData) throws IOException, IllegalAccessException {
        try {
            mv = new ModelAndView("/timesheet");
            final WebApplicationContext ctx = getWebApplicationContext();
            HttpSession session = request.getSession();
           
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
            String stausFromFilter = request.getParameter("res_status");
            String selectedRows = (String) request.getParameter("selectedRows");
            String employee_id = oInputData.getEmployeeId();
            
            logger.info(employee_id + " : " + selectedRows);
            logger.info(employee_id + " : " + request.getParameter("saveStatus"));
            ObjectMapper mapper = new ObjectMapper();
           
            List<TimesheetEntryDTO> oTimeSheetEntries = Arrays.asList(
                    mapper.readValue(selectedRows.toString(), TimesheetEntryDTO[].class));
            
            if (oTimeSheetEntries != null && oTimeSheetEntries.size() > 0) {
                for (int iTSEntry = 0; iTSEntry < oTimeSheetEntries.size(); iTSEntry++) {
                    TimesheetEntryDTO oTimeSheetEntry = oTimeSheetEntries.get(iTSEntry);
                    oTimeSheetEntry.setEmployeeId(employee_id);
                    oTimeSheetEntry.setStatus(request.getParameter("saveStatus"));

                    String hrs = "";
                    String mins = "";
                    String[] worked_time = oTimeSheetEntry.getWorked_hours().split(":");
                    if (Integer.parseInt(worked_time[0]) <= 9) {
                        if (worked_time[0].length() < 2) {
                            hrs = "0" + worked_time[0];
                        } else {
                            hrs = worked_time[0];
                        }
                    } else {
                        hrs = worked_time[0];
                    }
                    if (Integer.parseInt(worked_time[1]) <= 9) {
                        if (worked_time[1].length() < 2) {
                            mins = "0" + worked_time[1];
                        } else {
                            mins = worked_time[1];
                        }
                    } else {
                        mins = worked_time[1];
                    }
                    oTimeSheetEntry.setWorked_hours(hrs + ":" + mins);
                    
                    String month = "";
                    String date = "";
                    String[] timesheet_date_arr = oTimeSheetEntry.getTimesheet_date().split("-");
                    if (Integer.parseInt(timesheet_date_arr[1]) <= 9) {
                        if (timesheet_date_arr[1].length() < 2) {
                            month = "0" + timesheet_date_arr[1];
                        } else {
                            month = timesheet_date_arr[1];
                        }
                    } else {
                        month = timesheet_date_arr[1];
                    }
                    if (Integer.parseInt(timesheet_date_arr[2]) <= 9) {
                        if (timesheet_date_arr[2].length() < 2) {
                            date = "0" + timesheet_date_arr[2];
                        } else {
                            date = timesheet_date_arr[2];
                        }
                    } else {
                        date = timesheet_date_arr[2];
                    }
                    oTimeSheetEntry.setTimesheet_date(timesheet_date_arr[0] + "-" + month + "-" + date);
                    oTimeSheetEntry.setDeleted("0");
//                    logger.info(employee_id + " : " + oTimeSheetEntry.getStatus());
                    dao.saveTimesheet(oTimeSheetEntry);

                    session.setAttribute("yearSession", timesheet_date_arr[0]);
                    session.setAttribute("monthSession", month);
                }
            }
            oInputData.setStatus(stausFromFilter);
        } catch (Exception e) {
            System.out.print("exception occurred : " + e.getMessage());
            e.printStackTrace();
        }

        mv = index(request, response, oInputData);
        return mv;
    }

    public synchronized ModelAndView exportTimesheetReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("coming to export Timesheet");
        mv = new ModelAndView("redirect:index.htm");
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int minDay = 1;
        int maxDay = 31;
        final WebApplicationContext ctx = getWebApplicationContext();
        ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
        List<TimesheetEntryDTO> outData = new ArrayList<TimesheetEntryDTO>();

        String employee_id = request.getParameter("employee_id");
        String year = request.getParameter("year");
        String month = request.getParameter("month");

        String monthNames[] = new DateFormatSymbols().getMonths();
        cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, 1);
        maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String min_day = Integer.toString(minDay);
        String max_day = Integer.toString(maxDay);
        String mth = month;
        EmployeeDTO empInfo = dao.getEmployeeInfo_New(employee_id);
        String emp_joined_date[] = empInfo.getJoined_date().split("-");

        if (minDay <= 9) {
            min_day = "0" + minDay;
        }
        if (maxDay <= 9) {
            max_day = "0" + maxDay;
        }
        if (Integer.parseInt(month) <= 9) {
            mth = "0" + month;
        }
        
        String start_date = year + "-" + mth + "-" + min_day;
        String end_date = year + "-" + mth + "-" + max_day;
        
        SearchCriteriaDTO searchObj = new SearchCriteriaDTO();
        searchObj.setStart_date(start_date);
        searchObj.setEnd_date(end_date);
        searchObj.setEmployeeId(employee_id);
        
        outData = dao.getTimesheetEntries_New(searchObj);
        
        //Header Data
        ArrayList entireList = new ArrayList();
        ArrayList headerData = new ArrayList();
        ArrayList headerList = new ArrayList();
        headerList.add("Date");
        headerList.add("Project");
        headerList.add("Shift");
        headerList.add("Role");
        headerList.add("Work Location");
        headerList.add("Regularization Reason");
        headerList.add("Phase");
        headerList.add("Task");
        headerList.add("Office Hours");
        headerList.add("Available Hours");
        headerList.add("Attendance Hours");
        headerList.add("Worked Hours");
        headerList.add("Remarks");
        headerList.add("Status");
        headerData.add(headerList);
        
        
        // Details
       
        List<ConfigurationDTO> statusConfigValues = dao.getConfiguration("timesheet_status");
        List<ConfigurationDTO> shiftConfigValues = dao.getConfiguration("shift_list");
        List<ConfigurationDTO> locationConfigValues = dao.getConfiguration("work_location_list");

        String[] statusList = new String[statusConfigValues.size() + 1];
        String[] shiftList = new String[shiftConfigValues.size() + 1];
        String[] locationList = new String[locationConfigValues.size() + 1];

        for (int i = 0; i < statusConfigValues.size(); i++) {
            statusList[i] = statusConfigValues.get(i).getConfiguration_key();
        }
        for (int i = 0; i < shiftConfigValues.size(); i++) {
            shiftList[i] = shiftConfigValues.get(i).getConfiguration_key();
        }
        for (int i = 0; i < locationConfigValues.size(); i++) {
            locationList[i] = locationConfigValues.get(i).getConfiguration_key();
        }

        List<ProjectDTO> projectList = new ArrayList<ProjectDTO>();
        //Data List
        for (int i = 0; i < outData.size(); i++) {
            ArrayList rowDataList = new ArrayList();

            //Timesheet date
            rowDataList.add(outData.get(i).getTimesheet_date());

            //To get project details
            if (!outData.get(i).getProject_id().equals("Non_Project_Activity")) {
                rowDataList.add(outData.get(i).getProject_name());
            } else {
                rowDataList.add(outData.get(i).getProject_id());
            }

            //Shift details
            for (int shiftIndex = 0; shiftIndex < shiftList.length; shiftIndex++) {
                if (outData.get(i).getShift().equals(shiftList[shiftIndex])) {
                    rowDataList.add(shiftConfigValues.get(shiftIndex).getConfiguration_value());
                }
            }

            //Role details
            rowDataList.add(outData.get(i).getRole_name());

            //Location details
            
            rowDataList.add(outData.get(i).getLocation());
            
//            for (int locationIndex = 0; locationIndex < locationList.length; locationIndex++) {
//                if (outData.get(i).getLocation().equals(locationList[locationIndex])) {
//                    rowDataList.add(locationConfigValues.get(locationIndex).getConfiguration_value());
//                }
//            }

            //Regularization reason

            rowDataList.add(outData.get(i).getRegularization_reason());

            //Phase details
            
            if (outData.get(i).getPhase_id().equalsIgnoreCase("Others") || outData.get(i).getPhase_id().equalsIgnoreCase("Non billable activity")) {
                rowDataList.add(outData.get(i).getPhase_id());
            } else {
                
                searchObj.setProjectId(outData.get(i).getProject_id());
                searchObj.setProjectPhase(outData.get(i).getPhase_id());
                PhaseDTO dto = dao.getPhaseDetail(searchObj);
                rowDataList.add(dto.getPhase_name());
               
            }

            //Task details
            if (outData.get(i).getTask_id().equalsIgnoreCase("Others")|| outData.get(i).getTask_id().equalsIgnoreCase("Non billable activity")) {
                rowDataList.add(outData.get(i).getTask_id());
            } else {
                searchObj.setTaskId(outData.get(i).getTask_id());
                TaskDTO dto = dao.getTask(searchObj);
                rowDataList.add(dto.getTask_name());
            }
            //Office hrs
            if (outData.get(i).getOffice_hours() != null) {
                rowDataList.add(outData.get(i).getOffice_hours());
            } else {
                rowDataList.add("00:00");
            }
            //Available hrs
            if (outData.get(i).getAvailable_hours() != null) {
                rowDataList.add(outData.get(i).getAvailable_hours());
            } else {
                rowDataList.add("00:00");
            }

            //Attendance hrs
            if (outData.get(i).getAttendance_hours() != null) {
                rowDataList.add(outData.get(i).getAttendance_hours());
            } else {
                rowDataList.add("00:00");
            }

            //Worked hrs
            if (outData.get(i).getWorked_hours() != null) {
                rowDataList.add(outData.get(i).getWorked_hours());
            } else {
                rowDataList.add("00:00");
            }

            //Remarks
            rowDataList.add(outData.get(i).getRemarks());

            //Status details
            for (int statusIndex = 0; statusIndex < statusList.length; statusIndex++) {
                if (outData.get(i).getStatus().equals(statusList[statusIndex])) {
                    rowDataList.add(statusConfigValues.get(statusIndex).getConfiguration_value());
                }
            }

            //Add entire list
            entireList.add(rowDataList);
        }
        CommonFunctions.exportToExcel(response, headerData, entireList, "TimeSheet Report For " + monthNames[Integer.parseInt(month) - 1] + " " + year + ".xls");
        return null;
    }
    
    public synchronized ModelAndView exportTimesheetPdfReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");
        String employee_id = request.getParameter("employee_id");
        String selectedMonth = request.getParameter("month");
        String selectedYear = request.getParameter("year");
        Calendar startEnd = Calendar.getInstance();
        startEnd.set(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth)-1,1);
        Date startDate = startEnd.getTime();
        startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = startEnd.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        SearchCriteriaDTO searchObj = new SearchCriteriaDTO();
        searchObj.setStart_date(sdf.format(startDate).toString());
        searchObj.setEnd_date(sdf.format(endDate).toString());
        searchObj.setEmployeeId(employee_id);
        searchObj.setYear(selectedYear);
//        dto.setFrom_date(sdf.format(startDate));
//        dto.setTo_date(sdf.format(endDate));
//        dto.setEmployee_id(employee_id);
//        dto.setYear(selectedYear);
        while(selectedMonth.length()<2) selectedMonth = "0"+selectedMonth;
        searchObj.setMonth(selectedMonth);
        List<TimesheetExportDTO> value = dao.getExportEmployeeTimesheet(searchObj);
        List<TimesheetExportDTO> employeeDetails = dao.getEmployeeDetails(searchObj);
        if(value.size()>0){
            CommonFunctions.exportToPdf(request, response,employeeDetails.get(0).getEmployee_number(), value, employeeDetails);
            
        }
        return null;
    }

    public  Object getBean(String beanName) {
        Object o = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public synchronized ModelAndView loadEmployeeRoleAndLocation(HttpServletRequest request, HttpServletResponse response) {
        List<RoleWorkLocationDTO> lstRoleWorkLocation = null;
        
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        int today = cal.get(Calendar.DAY_OF_MONTH);
        
        int minDay = 1;
        int maxDay = 31;
        try {
            mv = new ModelAndView("/timesheet");
            
            final WebApplicationContext ctx = getWebApplicationContext();
            ApprovalDAOImpl dao = (ApprovalDAOImpl) ctx.getBean("ApprovalDAO");

            String employee_id = request.getParameter("employee_id");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            year = year.trim();
            month = month.trim();
            
            cal.set(Integer.parseInt(year.trim()), Integer.parseInt(month.trim()) - 1, 1);
            maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (Integer.parseInt(year.trim()) == currentYear && Integer.parseInt(month.trim()) == currentMonth + 1) {
                maxDay = today;
            }
            
            String min_day = Integer.toString(minDay);
            String max_day = Integer.toString(maxDay);
            
            String mth = Integer.toString(Integer.parseInt(month)); 
            
            if (minDay <= 9) {
                min_day = "0" + minDay;
            }
            if (maxDay <= 9) {
                max_day = "0" + maxDay;
            }
            if (Integer.parseInt(month) <= 9) {
                mth = "0" + month;
            }
                        
            String start_date = year + "-" + mth + "-" + min_day;
            String end_date = year + "-" + mth + "-" + max_day;
            
            SearchCriteriaDTO oSearch = new SearchCriteriaDTO();
            oSearch.setStart_date(start_date);
            oSearch.setEnd_date(end_date);
            oSearch.setEmployeeId(employee_id);
            lstRoleWorkLocation = dao.getRoleAndLocation(oSearch);
            
            if (lstRoleWorkLocation != null && lstRoleWorkLocation.size() > 0) {
                
                String[] start_date_array = start_date.split("-");
                String[] end_date_array = end_date.split("-");
               
                for (int nItem = 0; nItem < lstRoleWorkLocation.size(); nItem++) {
                    String sEffectiveStartDate = "";
                    String sEffectiveEndDate = "";
                  
                    String[] role_start_date_array = lstRoleWorkLocation.get(nItem).getStart_date().split("-");
                    String[] role_end_date_array = lstRoleWorkLocation.get(nItem).getEnd_date().split("-");
                  
                    response.getOutputStream().write(lstRoleWorkLocation.get(nItem).getRole_description().getBytes());
                    response.getOutputStream().write(":".getBytes());
                    response.getOutputStream().write((lstRoleWorkLocation.get(nItem).getProject_code() + " - " + lstRoleWorkLocation.get(nItem).getProject_name()).getBytes());
                    response.getOutputStream().write(":".getBytes());
                    response.getOutputStream().write(lstRoleWorkLocation.get(nItem).getStart_date().getBytes());
                    response.getOutputStream().write(":".getBytes());
                    response.getOutputStream().write(lstRoleWorkLocation.get(nItem).getEnd_date().getBytes());
                    response.getOutputStream().write(":".getBytes());
                    response.getOutputStream().write(lstRoleWorkLocation.get(nItem).getWorking_place().getBytes());
                    response.getOutputStream().write(";".getBytes());
                }
            }
        } catch (Exception e) {
            System.out.print("exception occurred : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public synchronized ModelAndView approvalSave(HttpServletRequest request, HttpServletResponse response, ApprovalDTO oInputData) throws IOException, Exception {

        String selectedRows = (String) request.getParameter("selectedRows");
        ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
        ApprovalDTO filterTO = new ApprovalDTO();
        String workTypeKey = request.getParameter("regularName");
        ObjectMapper mapper = new ObjectMapper();
    
        List<ApprovalDTO> oApprovalSave = Arrays.asList(
                mapper.readValue(selectedRows.toString(), ApprovalDTO[].class));
        if (oApprovalSave != null && oApprovalSave.size() > 0) {
            for (int iTSEntry = 0; iTSEntry < oApprovalSave.size(); iTSEntry++) {
                ApprovalDTO oApprvalDTO = oApprovalSave.get(iTSEntry);
                serviceImpl.approverSubmit(oApprvalDTO);
            }
            filterTO.setProjectName(oInputData.getProjectName());
            filterTO.setEmpName(oInputData.getEmpName());
            filterTO.setFromDate(oInputData.getFromDate());
            filterTO.setToDate(oInputData.getToDate());
            if (oApprovalSave.get(0).getActionValue() != null) {
                filterTO.setSuccessMessage("Timesheet Saved Successfully");
            }
        }
        filterTO.setRegularName(workTypeKey);
        mv = filterData(request, response, filterTO);
        return mv;
    }
    
    public synchronized ModelAndView removeSavedItems(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException{
        
        mv = new ModelAndView("/index");
        ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
        int i = serviceImpl.savedDataRemove(request.getParameter("value"));
        HttpSession session = request.getSession();
        TimesheetEntryDTO oInputData = new TimesheetEntryDTO();
        try {
            session.setAttribute("yearSession",request.getParameter("year"));
            session.setAttribute("monthSession",request.getParameter("month"));
            mv = index(request, response, oInputData);
            response.getWriter().println(request.getParameter("month")+"@@"+request.getParameter("year"));
        } catch (IOException ex) {
            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized ModelAndView clearSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("page to refresh  = = = = "+request.getParameter("pageToRefresh"));
        if (request.getParameter("pageToRefresh").equals("Index")) {
            TimesheetEntryDTO oInputData = new TimesheetEntryDTO();
            HttpSession session = request.getSession();
            try {
                session.setAttribute("yearSession" , request.getParameter("year"));
                session.setAttribute("monthSession" , request.getParameter("month"));
                mv = index(request, response, oInputData);
                response.getWriter().println(request.getParameter("month") + "@@" + request.getParameter("year"));
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    public  ModelAndView userManual(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
       
        System.out.println("inside userManual");
        String filepath = "D:/propertyfiles/"; 
        String filename = "entry_usermanual.pdf";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                 myOut = response.getOutputStream(); 
                 File myfile = new File(filepath + filename);
                
            response.setContentType("application/pdf");        
          
            response.setHeader("Content-Disposition", "inline; filename=" + filename); // for downloading the pdf format important
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            
           
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }
            
            }catch(Exception e){
                
            }
        return null;
        
    }
    public  ModelAndView userManualApproval(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
      
        System.out.println("inside userManual");
        String filepath = "D:/propertyfiles/"; 
        String filename = "approval_usermanual.pdf";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                 myOut = response.getOutputStream(); 
                 File myfile = new File(filepath + filename);
                
            response.setContentType("application/pdf");        
          
		     response.setHeader("Content-Disposition", "inline; filename=" + filename); 
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            
           
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }
            
            }catch(Exception e){
                
            }
        return null;
        
    }
    
    
  }


