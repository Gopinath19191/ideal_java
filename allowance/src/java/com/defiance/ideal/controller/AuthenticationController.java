/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dao.HolidayDaoImpl;
import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.HolidayDto;
import com.defiance.ideal.service.HolidayService;
import com.defiance.ideal.service.HolidayServiceImpl;
import com.defiance.ideal.util.CommonFunctions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14053
 */
public class AuthenticationController extends MultiActionController {

    private ModelAndView mv;

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        HolidayDaoImpl dao = (HolidayDaoImpl) ctx.getBean("HolidayDao");
        HolidayDto data = new HolidayDto();
        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Employee Id Not Present");
        } else {
            data = (HolidayDto) dao.getUserDetails(request.getParameter("empId"));
            session.setAttribute("designation", data.getDesignation());
            System.out.println("Employee Id = " + data.getEmpId());
            System.out.println("Designation Id = " + data.getDesignation());
        }
        session.setAttribute("accessId", data.getEmpId());
        session.setAttribute("accessType", request.getParameter("access"));
        
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } 
        else {
            data.setModuleId(request.getParameter("modId"));
            System.out.println("Module Id = " + data.getModuleId());
        }
        boolean authenticated = dao.authenticate(data);
        //authenticated = true;
        if(authenticated && (request.getParameter("modId").equals("656") || request.getParameter("modId").equals("655"))){
            System.out.println("-------inside apply allowance------");
            if (request.getParameter("access").equals("HR")) {
                mv = new ModelAndView("redirect:processedAllowance.htm");
            }else{
                mv = new ModelAndView("redirect:applyFullAllowance.htm");
            }
            
        }else if(authenticated && (request.getParameter("modId").equals("824"))){
            mv = new ModelAndView("redirect:indexPage.htm");
        }else if(authenticated && (request.getParameter("modId").equals("825"))){
            mv = new ModelAndView("redirect:timesheetReversal.htm");
        }else if(authenticated && (request.getParameter("modId").equals("827"))){
            mv = new ModelAndView("redirect:timesheetRejection.htm");
        }else if(authenticated && (request.getParameter("modId").equals("828"))){
            mv = new ModelAndView("redirect:getOfficeTiming.htm");
        }else if(authenticated && (request.getParameter("modId").equals("829"))){
            mv = new ModelAndView("redirect:getMangerList.htm");
        }else if(authenticated && (request.getParameter("modId").equals("847"))){
            mv = new ModelAndView("redirect:eCardPage.htm");
        }else if(authenticated && (request.getParameter("modId").equals("848"))){
            mv = new ModelAndView("redirect:eCardReport.htm");
        }else if(authenticated && (request.getParameter("modId").equals("849"))){
            mv = new ModelAndView("redirect:employeeEngagement.htm");
        }else if(authenticated && (request.getParameter("modId").equals("851"))){
            mv = new ModelAndView("redirect:accrualAccountingMonth.htm");
        }else if(authenticated && (request.getParameter("modId").equals("846"))){
            mv = new ModelAndView("/com/defiance/timesheetUpload");
        }else if (authenticated) {
            session.setAttribute("empLoginDetails", data);
            session.setAttribute("employeeSearchID", "");
            if (request.getParameter("access").equals("RM")) {
                mv = new ModelAndView("redirect:approve.htm?list=pending&page=1");
            } else if (request.getParameter("access").equals("HR")) {
                mv = new ModelAndView("redirect:processedAllowance.htm");
            }
            else {
                //mv = new ModelAndView("redirect:index.htm?&page=1");
                mv = new ModelAndView("redirect:employeeViewAllowance.htm");
            }
        } 
        
        else {
            session.invalidate();
        }
        return mv;
    }

    public ModelAndView apply(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) throws Exception {
        HttpSession session = request.getSession();
        mv = new ModelAndView("/com/defiance/apply");
        final WebApplicationContext ctx = getWebApplicationContext();
        HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
        formData.setEmployeeId((String) session.getAttribute("accessId"));

        String accessParameter = (String) session.getAttribute("accessType");
        formData.setAccessParameter(accessParameter);

        if (formData.getAllowance_date() == null) {
            String totalHr = "";
            String totalMin = "";
            String allowanceId = request.getParameter("allowanceId");
            if (allowanceId != null) {
                HolidayDto requestData = null;
                requestData = (HolidayDto) service.getRequestData(allowanceId);
                String[] tot = requestData.getTotal_hours().split(":");
                if (tot[0].charAt(0) == '0') {
                    totalHr = Character.toString(tot[0].charAt(1));
                } else {
                    totalHr = tot[0];
                }
                mv.addObject("totalHr", totalHr);
                if (tot[1].charAt(0) == '0') {
                    totalMin = Character.toString(tot[1].charAt(1));
                } else {
                    totalMin = tot[1];
                }
                mv.addObject("totalMin", totalMin);
                mv.addObject("requestData", requestData);
            }
            List<DetailsDto> projectArr = null;
            projectArr = service.getProjectList(formData);
            mv.addObject("projectArr", projectArr);
            List<DetailsDto> holidayArr = null;
            List<DetailsDto> worked_daysArr = null;
            holidayArr = service.getHolidayList(formData);
            String holiday = "";
            for (int k = 0; k < holidayArr.size(); k++) {
                if (k != holidayArr.size() - 1) {
                    holiday = holiday + "'" + holidayArr.get(k).getHoliday() + "',";
                } else {
                    holiday = holiday + "'" + holidayArr.get(k).getHoliday() + "'";
                }
            }
            mv.addObject("holiday", holiday);
            worked_daysArr = service.getWorkedDaysList(formData);
            String worked_day = "";
            for (int k = 0; k < worked_daysArr.size(); k++) {
                if (k != worked_daysArr.size() - 1) {
                    worked_day = worked_day + "'" + worked_daysArr.get(k).getWorkedDay() + "',";
                } else {
                    worked_day = worked_day + "'" + worked_daysArr.get(k).getWorkedDay() + "'";
                }
            }
            mv.addObject("worked_day", worked_day);
        } else {
            service.updateHolidayAllowance(formData);
            mv = new ModelAndView("redirect:index.htm?&page=1");
        }
        return mv;
    }

    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) throws Exception {
        HttpSession session = request.getSession();
        String temp1[];
        String temp2[];
        mv = new ModelAndView("/com/defiance/index");
        final WebApplicationContext ctx = getWebApplicationContext();
        HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
        formData.setEmployeeId((String) session.getAttribute("accessId"));

        String accessParameter = (String) session.getAttribute("accessType");
        formData.setAccessParameter(accessParameter);

        if (formData.getStartFilter() != null) {
            session.setAttribute("sesStartDate", formData.getStartFilter());
        } else {
            formData.setStartFilter((String) session.getAttribute("sesStartDate"));
        }

        if (formData.getEndFilter() != null) {
            session.setAttribute("sesEndDate", formData.getEndFilter());
        } else {
            formData.setEndFilter((String) session.getAttribute("sesEndDate"));
        }

        mv.addObject("stfilter", formData.getStartFilter());
        mv.addObject("edfilter", formData.getEndFilter());
        if (formData.getStartFilter() != null && !formData.getStartFilter().equals("")) {
            temp1 = formData.getStartFilter().split("-");
            formData.setStartFilter(temp1[2] + "-" + temp1[1] + "-" + temp1[0]);
        }
        if (formData.getEndFilter() != null && !formData.getEndFilter().equals("")) {         
            temp2 = formData.getEndFilter().split("-");
            formData.setEndFilter(temp2[2] + "-" + temp2[1] + "-" + temp2[0]);
        }
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);
        String recordCount = service.getRecordCount(formData);
        mv.addObject("paging", paging(rows, recordCount, page));
        List<HolidayDto> result = null;
        result = service.getAllowanceDetails(formData);
        for(int i=0;i<result.size();i++){
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(date.getDate()<=18){
                String[] createdDateFullFormat = result.get(i).getCreated().split("-");
                int createdDate = Integer.parseInt(createdDateFullFormat[2]);
                int createdMonth = Integer.parseInt(createdDateFullFormat[1]);
                int createdYear = Integer.parseInt(createdDateFullFormat[0]);
                if(createdMonth==12&&createdYear==(date.getYear()+1900-1)){
                    if((createdDate>0 && createdDate<19 && createdMonth==date.getMonth()+1) || (createdDate>18 && createdDate<31 && createdMonth==date.getMonth())){
                        result.get(i).setSaveEnable("1");
                    }else{
                        result.get(i).setSaveEnable("0");
                    }
                }else{
                    if((createdDate>0 && createdDate<19 && createdMonth==date.getMonth()+1 && (createdYear==date.getYear()+1900)) || (createdDate>18 && createdDate<31 && createdMonth==date.getMonth() && (createdYear==date.getYear()+1900))){
                        result.get(i).setSaveEnable("1");
                    }else{
                        result.get(i).setSaveEnable("0");
                    }
                }
                
                System.out.println("status "+result.get(i).getSaveEnable());
            }else{
                String[] createdDateFullFormat = result.get(i).getCreated().split("-");
                int createdDate = Integer.parseInt(createdDateFullFormat[2]);
                int createdMonth = Integer.parseInt(createdDateFullFormat[1]);
                int createdYear = Integer.parseInt(createdDateFullFormat[0]);
                
                System.out.println("year "+createdYear);
                System.out.println("yeat current "+date.getYear());
                if(createdMonth==12&&createdYear==(date.getYear()-1)){
                
                }
                
                if(createdDate>18 && createdMonth==date.getMonth()+1){
                    result.get(i).setSaveEnable("1");
                    System.out.println("inside "+(date.getMonth()+1));
                }else{
                    result.get(i).setSaveEnable("0");
                    System.out.println("outside "+(date.getMonth()+1));
                }
                
            }
        }
        mv.addObject("result", result);
        return mv;
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response) {
        mv = new ModelAndView("/com/defiance/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
            String empVal = request.getParameter("q");
            List<DetailsDto> empRes = service.getEmployeeSearchList(empVal);
            System.out.println("size" + empRes.size());
            mv.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView reports(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) throws Exception {
        HttpSession session = request.getSession();
        String employeeSearchID;
        mv = new ModelAndView("/com/defiance/reports");
        final WebApplicationContext ctx = getWebApplicationContext();
        HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
        Calendar cal = Calendar.getInstance();
        List<HolidayDto> configresult = (List<HolidayDto>) service.getConfigValues(CommonFunctions.empDateValue);       
        String accessParameter = (String) session.getAttribute("accessType");
        formData.setAccessParameter(accessParameter);

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Map<String, String> monthsList = CommonFunctions.getMonthsList();
        Map<Integer, Integer> yearsList = CommonFunctions.getYearsList(4);
        mv.addObject("monthsList", monthsList);
        mv.addObject("yearsList", yearsList);
        mv.addObject("employeeSearchID", "");
        mv.addObject("employeeSearchName", "");

        if (formData.getEmployeeId() != null) {
            employeeSearchID = formData.getEmployeeId();
            session.setAttribute("employeeSearchID", formData.getEmployeeId());
        } else {
            employeeSearchID = (String) session.getAttribute("employeeSearchID");
            formData.setEmployeeId(employeeSearchID);
        }

        if (formData.getYear_filter() != null && formData.getMonth_filter() != null) {
            session.setAttribute("sesYearFilter", formData.getYear_filter());
            session.setAttribute("sesMonthFilter", formData.getMonth_filter());
        } else {
            formData.setYear_filter((String) session.getAttribute("sesYearFilter"));
            formData.setMonth_filter((String) session.getAttribute("sesMonthFilter"));
        }
        if (formData.getYear_filter() == null) {
            formData.setYear_filter(Integer.toString(cal.get(Calendar.YEAR)));
            session.setAttribute("sesYearFilter", formData.getYear_filter());
        }
        if (formData.getMonth_filter() == null) {
            formData.setMonth_filter(Integer.toString(cal.get(Calendar.MONTH) + 1));
            session.setAttribute("sesMonthFilter", formData.getMonth_filter());
        }
        if (formData.getYear_filter() != null && formData.getMonth_filter() != null) {
            String mth = "";
            String yr = "";
            String start_date = "";
            String end_date = "";
            int yyy = 0;
            if (Integer.parseInt(formData.getMonth_filter()) - 1 == 0) {
                mth = "12";
                yyy = Integer.parseInt(formData.getYear_filter()) - 1;
                start_date = yyy + "-" + mth + configresult.get(0).getConfigValue();
            } else {
                mth = months[Integer.parseInt(formData.getMonth_filter()) - 2];
                yr = formData.getYear_filter();
                start_date = yr + "-" + mth + configresult.get(0).getConfigValue();
            }

            end_date = formData.getYear_filter() + "-" + months[Integer.parseInt(formData.getMonth_filter()) - 1] + configresult.get(1).getConfigValue();
            System.out.println("start_date" + start_date);
            System.out.println("end_date" + end_date);
            formData.setStart_date(start_date);
            formData.setEnd_date(end_date);
        }

        if (employeeSearchID != null) {
            mv.addObject("employeeSearchID", employeeSearchID);
            String employeeSearchName = service.getEmployeeName(employeeSearchID);
            System.out.println("employeeSearchName" + employeeSearchName);
            mv.addObject("employeeSearchName", employeeSearchName);
        }

        int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);
        String recordCount = service.getTotalRecordCount(formData);
        mv.addObject("paging", paging(rows, recordCount, page));
        List<HolidayDto> result = null;
        result = service.getTotalAllowanceDetails(formData);
        mv.addObject("result", result);
        System.out.println("Month filter" + formData.getMonth_filter());
        mv.addObject("formData", formData);
        if (formData.getExcelbuttonName() != null) {
            String[] methodNames = {"getEmployeeNumber", "getEmployeeName", "getAllowance_date", "getProject_name", "getTotal_hours", "getApproved_hours", "getCreated_date", "getApproved_date", "getReason"};
            String[] tableHeader = {"Employee Id", "Employee Name", "Holiday Date", "Project ", "Total Hours", "Approved Hours", "Created Date", "Approved Date", "Reason"};
            CommonFunctions.drawTable(response, result, tableHeader.length, tableHeader, methodNames, request, true, "holiday_allowance.xls");
        }
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
        System.out.println("totalPage" + totalPage);
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
        System.out.println("recordCount" + recordCount);
        System.out.println("current_page" + current_page);
        System.out.println("start" + start);
        System.out.println("end" + end);
        System.out.println("Totalpage++++" + totalPage);
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }

    //RM Approval Screen---- Malar
    public ModelAndView approve(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) throws Exception {
        HttpSession session = request.getSession();
         //String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
         Map<Integer, Integer> yearsList = CommonFunctions.getYearsList(2);
         Map<String, String> monthsList = CommonFunctions.getMonthsList();
        String employeeSearchID;
        String projectNameID;
        String list = request.getParameter("list");
        formData.setRequestStatus(list);
        String searchEmployeeId = formData.getEmployeeId();
        if (formData.getEmployeeId() == null || formData.getEmployeeId().equals("")) {
            session.setAttribute("employeeSearchID", "");
        }
        HolidayDto empDetails = (HolidayDto) session.getAttribute("empLoginDetails");
        final WebApplicationContext ctx = getWebApplicationContext();
        HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
        formData.setEmpId(empDetails.getEmpId());
        formData.setEmploId((String) session.getAttribute("accessId"));

        mv = new ModelAndView("com/defiance/approve");
        String searchbtn = request.getParameter("rmSubmit");
        if (searchbtn != null || (formData.getEmployeeId() != null && !formData.getEmployeeId().equals("")) || (formData.getProject_name() != null && !formData.getProject_name().equals(""))) {
            //if (searchbtn != null) {
            formData.setRmSubmit("Find");
            if (formData.getEmployeeId() != null) {
                employeeSearchID = formData.getEmployeeId();
                session.setAttribute("employeeSearchID", formData.getEmployeeId());
            } else {
                employeeSearchID = (String) session.getAttribute("employeeSearchID");
                formData.setEmployeeId(employeeSearchID);
            }
            if (employeeSearchID != null) {
                mv.addObject("employeeSearchID", employeeSearchID);
                String employeeSearchName = service.getEmployeeName(employeeSearchID);
                mv.addObject("employeeSearchName", employeeSearchName);
            }
            if (formData.getProject_name() != null || !formData.getProject_name().equals("")) {
                mv.addObject("projectNameIdvalue", formData.getProject_name());
            }
        } else {
            String accessParameter = (String) session.getAttribute("accessType");
            formData.setAccessParameter(accessParameter);
            //--For approve or reject the request begins--
            String status = request.getParameter("status");
            if (status != null) {
                String[] childIds = formData.getChk_box();
                formData.setStatus(status);
                if (childIds != null) {
                    for (int i = 0; i < childIds.length; i++) {
                        System.out.println("");
                        if (childIds[i].split("~~")[0] != null) {
                            formData.setRequestId(childIds[i].split("~~")[0]);
                        }
                        if (childIds[i].split("~~")[1] != null) {
                            formData.setTotal_hours(childIds[i].split("~~")[1]);
                        }
                        service.updateHolidayAllowance(formData);
                    }
                }
            }
        }
        //--For approve or reject the request ends--
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = 20;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);
        String recordCount = null;

        if (searchbtn != null || (formData.getEmployeeId() != null && !formData.getEmployeeId().equals("")) || (formData.getProject_name() != null && !formData.getProject_name().equals(""))) {
            recordCount = service.getPMTotalRecordCount(formData);
        } else {
            recordCount = service.getPmRecordCount(formData);
        }
        mv.addObject("paging", paging(rows, recordCount, page));
        List<HolidayDto> result = null;
        List<HolidayDto> projectsName = null;
        result = service.getRMApprovalDetails(formData);
        
        
        for(int i=0;i<result.size();i++){
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(date.getDate()<=18){
                String[] createdDateFullFormat = result.get(i).getAllowance_date().split("-");
                int createdDate = Integer.parseInt(createdDateFullFormat[0]);
                int createdMonth = Integer.parseInt(createdDateFullFormat[1]);
                int createdYear = Integer.parseInt(createdDateFullFormat[2]);
                
                
                if(createdMonth==12&&createdYear==(date.getYear()+1900-1)){
                    System.out.println("created date"+createdDate+" month"+createdMonth+" year "+createdYear);
                    System.out.println("current year "+(date.getYear()+1900-1));
                    System.out.println("current monthhhhhhhhhhh "+(date.getMonth()+1));
                    if((createdDate>0 && createdDate<32 && createdMonth==date.getMonth()+12)){
                        result.get(i).setSaveEnable("1");
                    }else{
                        result.get(i).setSaveEnable("0");
                    }
                }else{
                    System.out.println("created date"+createdDate+" month"+createdMonth+" year "+createdYear);
                    System.out.println("current year "+(date.getYear()+1900-1));
                    System.out.println("current mont "+(date.getMonth()+1));
                    if((createdDate>0 && createdDate<32 && createdMonth==date.getMonth())){
                        result.get(i).setSaveEnable("1");
                    }else{
                        result.get(i).setSaveEnable("0");
                    }
                }
                
                System.out.println("status "+result.get(i).getSaveEnable());
            }else{
                String[] createdDateFullFormat = result.get(i).getAllowance_date().split("-");
                int createdDate = Integer.parseInt(createdDateFullFormat[0]);
                int createdMonth = Integer.parseInt(createdDateFullFormat[1]);
                int createdYear = Integer.parseInt(createdDateFullFormat[2]);
                
                System.out.println("year "+createdYear+"month "+createdMonth+" date "+createdDate);
                System.out.println("yeat current "+date.getYear());
                if(createdMonth==12&&createdYear==(date.getYear()-1)){
                
                }
                
                if(createdDate>0 && createdDate<32 && createdMonth==date.getMonth()+1){
                    result.get(i).setSaveEnable("1");
                    System.out.println("inside "+(date.getMonth()+1));
                }else{
                    result.get(i).setSaveEnable("0");
                    System.out.println("outside "+(date.getMonth()+1));
                }
                
            }
        }
        
        
        projectsName = service.getProjectName(formData);
        String emploNo = (String) session.getAttribute("accessId");
        System.out.println("contr emplo No::::" + formData.getEmploId());
//      formData.setEmploNo(emploNo);
//      projectsName = service.getProjectList(formData);
        mv.addObject("result", result);
        mv.addObject("formData", formData);

        mv.addObject("projectsName", projectsName);
        mv.addObject("requestList", list);
        if (formData.getExcelbuttonName() != null) {
            String[] methodNames = {"getEmployeeNumber", "getEmployeeName", "getAllowance_date", "getPrajectName", "getTotalHours", "getApprovedHours", "getCreated_date", "getApproved_date", "getReason"};
            String[] tableHeader = {"Employee Id", "Employee Name", "Holiday Date", "Project ", "Total Hours", "Approved Hours", "Created Date", "Approved Date", "Reason"};
            CommonFunctions.drawTable(response, result, tableHeader.length, tableHeader, methodNames, request, true, "holiday_allowance.xls");
        }
        mv.addObject("yearsList", yearsList);
          mv.addObject("monthsList", monthsList);
        return mv;
    }

    public ModelAndView getEmployeeList(HttpServletRequest request, HttpServletResponse response, HolidayDto filterData) {
        mv = new ModelAndView("com/defiance/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
            String empVal = request.getParameter("q");
            List<DetailsDto> empRes = service.getEmployeeSearchList(empVal);
            mv.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView requestdetails(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) {
        mv = new ModelAndView("com/defiance/requestdetails");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
            HolidayDto result = null;
            String primaryId = request.getParameter("primaryId");
            result = (HolidayDto) service.getRequestData(primaryId);
            System.out.println("requestdetails------------" + result.getTotalHours());
            String totHrs = result.getTotal_hours();
            String[] splitHrs = totHrs.split(":");
            System.out.println("Splitted Hours==" + splitHrs[0] + "===Splitted Mins==" + splitHrs[1]);
            mv.addObject("result", result);
            mv.addObject("totalHr", splitHrs[0]);
            mv.addObject("totalMin", splitHrs[1]);
            mv.addObject("primaryId", primaryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView approveRequest(HttpServletRequest request, HttpServletResponse response, HolidayDto formData) {
        HttpSession session = request.getSession();
        mv = new ModelAndView("com/defiance/approve");
                try {
            HolidayDto empDetails = (HolidayDto) session.getAttribute("empLoginDetails");
            final WebApplicationContext ctx = getWebApplicationContext();
            HolidayService service = (HolidayServiceImpl) ctx.getBean("HolidayService");
            formData.setEmpId(empDetails.getEmpId());
            formData.setEmploId((String) session.getAttribute("accessId"));
            String reqId = request.getParameter("requestId");
            String accessParameter = (String) session.getAttribute("accessType");
            formData.setAccessParameter(accessParameter);
            String list = request.getParameter("list");
            formData.setRequestStatus(list);
            formData.setRequestId(reqId);
            String statusType = request.getParameter("statusType");
            String selHr = request.getParameter("selHr");
            String selMin = request.getParameter("selMin");
            System.out.println("SelHrs====" + selHr + "===selMin===" + selMin);
//            System.out.println("list type========" + list + "======statusType====" + statusType + "==AccessParameter===" + accessParameter + "===reqId===" + reqId);
            formData.setStatus(statusType);
            formData.setTotal_hours(selHr + ":" + selMin);
            if (statusType != null) {
                if (!statusType.equalsIgnoreCase("c")) {
                    service.updateHolidayAllowance(formData);
                }
            }
            //For Pagination And Count---
            int page = Integer.parseInt(request.getParameter("page"));
            int rows = 20;
            int start;
            start = ((page - 1) * rows);
            formData.setStart_page(start);
            formData.setEnd_page(rows);
            String recordCount = null;
            recordCount = service.getPmRecordCount(formData);
            mv.addObject("paging", paging(rows, recordCount, page));
            List<HolidayDto> result = null;
            List<HolidayDto> projectsName = null;
            result = service.getRMApprovalDetails(formData);
            projectsName = service.getProjectName(formData);
            mv.addObject("result", result);
            mv.addObject("projectsName", projectsName);
            mv.addObject("requestList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
