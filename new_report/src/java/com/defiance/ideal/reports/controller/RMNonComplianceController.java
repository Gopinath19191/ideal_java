/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.RMNonComplianceDao;
import com.defiance.ideal.reports.dao.RMNoncomplianceDAOImpl;
import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import com.defiance.ideal.reports.dto.RMNonComplianceDataDTO;
import com.defiance.ideal.reports.service.RMNoncomplianceServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import com.lowagie.text.Phrase;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class RMNonComplianceController extends MultiActionController{
    
    static Properties configFile = new Properties();
    static String filePath;
    public ModelAndView employeeTimesheetReport(HttpServletRequest request, HttpServletResponse response, RMNonComplianceDataDTO filterData) throws ParseException, Exception{
        ModelAndView mv = null;      
        String employeeId = null;
        String empNum = (String)request.getSession().getAttribute("EMP_ID");               
        String maxDate;
        String minDate;
        Calendar calendar = Calendar.getInstance();         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //maxDate = sdf.format(calendar.getTime());
        String prjdata = request.getParameter("project_id");
        Date MaximumDate = sdf.parse(request.getParameter("toDate"));
        long miliSecondForMaxDate = MaximumDate.getTime();
        calendar.setTime(MaximumDate);
        maxDate = sdf.format(calendar.getTime());
        Date MinimumDate = sdf.parse(request.getParameter("fromDate"));
        long miliSecondForMinDate = MinimumDate.getTime();
        calendar.setTime(MinimumDate);
        minDate = sdf.format(calendar.getTime());
        long diffDate = (miliSecondForMaxDate-miliSecondForMinDate)/ (24 * 60 * 60 * 1000);
        filterData.setStart_date(minDate);
        filterData.setEnd_date(maxDate);
        
        System.out.println("min date "+minDate);
        System.out.println("max date "+maxDate);
        
        final WebApplicationContext ctx = getWebApplicationContext();
        RMNoncomplianceServiceImpl timesheetReportService = (RMNoncomplianceServiceImpl) ctx.getBean("RMNonComplianceService"); 
        if(filterData.getCompany_structure_id()==null || filterData.getCompany_structure_id()==""){
            filterData.setEmployee_id(request.getParameter("employee_id"));
            filterData.setReporting_manager_id(request.getParameter("reporting_manager_id"));
        }else{
            filterData.setEmployee_id("");
            filterData.setReporting_manager_id("");
        }
        String buttonValue = request.getParameter("button_value");
        if(buttonValue.equals("searchreportingData")){
            filterData.setCompany_structure_id(null);
            filterData.setProject_id(null);
            filterData.setEmployee_id(request.getParameter("employee_id"));
            filterData.setReporting_manager_id(request.getParameter("reporting_manager_id"));
        }else if(buttonValue.equals("searchData")){
            filterData.setEmployee_id(null);
            filterData.setReporting_manager_id(null);
            filterData.setProject_id(null);
        }else if(buttonValue.equals("searchProjectData")){
            if(prjdata == null || "".equals(prjdata)){
            filterData.setReporting_manager_id(empNum);            
            }else{
                filterData.setReporting_manager_id(null);
            }            
            filterData.setCompany_structure_id(null);
            filterData.setEmployee_id(null);
        }
        List<RMNonComplianceDataDTO> timesheetReport = null;
        if(buttonValue.equals("searchData")){
            filterData.setUnit_name(request.getParameter("company_structure_id"));
            try{
                configFile.load(new FileInputStream("D:\\propertyfiles\\DelinquencyReport.properties"));
                filePath = configFile.getProperty("FILE_PATH");
            }catch(Exception ex){
            
            }
            try{
//                    configFile.load(new FileInputStream("D:\\propertyfiles\\DelinquencyReport.properties"));
//                    filePath = configFile.getProperty("FILE_PATH");
                System.out.println("file path in deletion "+filePath);
                    File file = new File(filePath+"delinquency_report.csv");
                    if(file.delete()){
                            System.out.println(file.getName() + " is deleted!");
                    }else{
                            System.out.println("Delete operation is failed.");
                    }
            }catch(Exception e){
                    e.printStackTrace();
            }
            
            timesheetReport = timesheetReportService.timesheetReportUnitWise(filterData);
            String fileName = timesheetReportService.getFileName(filterData);
            System.out.println("file path "+fileName);
            attachmentDownload(request, response,fileName);
        }else{
            List<RMNonComplianceDataDTO> getEmployeeList = null;
            StringBuffer sb = new StringBuffer();
            if(buttonValue.equals("searchProjectData")){
                 if(prjdata == null || "".equals(prjdata)){                     
                     List<RMNonComplianceDataDTO> getProjectList = timesheetReportService.getProjectList(filterData);
                     if(getProjectList != null && getProjectList.size() > 0 ){
                            Iterator itr = getProjectList.iterator();
                            while(itr.hasNext()){
                                 RMNonComplianceDataDTO dto = (RMNonComplianceDataDTO)itr.next();
                                sb.append(dto.getProject_id());
                                sb.append(",");
                            }                
                     }
                       System.out.println("sub string "+sb.toString().substring(0,sb.toString().length()-1));
                       filterData.setProject_id(sb.toString().substring(0,sb.toString().length()-1));
                     getEmployeeList =  timesheetReportService.getMultiProjectEmployeeList(filterData);
                 }
                 else{
                     getEmployeeList =  timesheetReportService.getProjectEmployeeList(filterData);
                 }                
            }else{
                getEmployeeList =  timesheetReportService.getEmployeeList(filterData);
            }
            ArrayList entireList = new ArrayList();
            ArrayList DateList = new ArrayList();
            DateList.add(0,"Employee Number");
            DateList.add(1,"Employee Name");
            DateList.add(2,"Reporting Manager");
            DateList.add(3,"Unit");
            DateList.add(4,"Billable/DNB");
            for(int lop = 0;lop<=diffDate;lop++){
                SimpleDateFormat dmy = new SimpleDateFormat("dd-MMM-yyyy");
                String output = dmy.format(calendar.getTime());
                    DateList.add(output);
                calendar.add(Calendar.DATE, 1);
            }
            entireList.add(DateList);
            ArrayList DayList = new ArrayList();
            calendar.setTime(MinimumDate);
            DayList.add("");DayList.add("");DayList.add("");DayList.add("");DayList.add("");
            for(int lop = 0;lop<=diffDate;lop++){
                SimpleDateFormat da = new SimpleDateFormat("E");
                String output = da.format(calendar.getTime());
                DayList.add(output);
                calendar.add(Calendar.DATE, 1);
            }
            entireList.add(DayList);
            for(int lop = 0;lop<getEmployeeList.size();lop++){
                calendar.setTime(MinimumDate);
                ArrayList rowDataList = new ArrayList();
                employeeId = getEmployeeList.get(lop).getEmployee_id();
                filterData.setEmployee_id(employeeId);
                List<RMNonComplianceDataDTO> getEmployeeDetails =  timesheetReportService.getEmployeeDetails(filterData);
                rowDataList.add(0,getEmployeeDetails.get(0).getEmployee_number());
                rowDataList.add(1,getEmployeeDetails.get(0).getEmployee_name());
                rowDataList.add(2,getEmployeeDetails.get(0).getReporting_manager());
                rowDataList.add(3,getEmployeeDetails.get(0).getUnit_name());
                rowDataList.add(4,getEmployeeDetails.get(0).getBillable_non_billable());
                timesheetReport = timesheetReportService.timesheetReport(filterData);
                for(int i=0;i<=diffDate;i++){
                    rowDataList.add(timesheetReport.get(i).getWorked_hours());
                }
                entireList.add(rowDataList);
            }
            downloadExcel(request,response, entireList);
        }
        
            
//            int notApplicable=0,holiday=0,week=0,nonCompliance=0,entered=0,leaveApplied=0;
//            for(int i=0;i<=diffDate;i++){
//                String output = sdf.format(calendar.getTime());
//                try{
//                    int leaveDetails =  0, calendar_id = 0, weekEndOne = 0, weekEndTwo = 0;
//                    String holidayDate = null;
//                    String weekEndDetails = null;
//                    filterData.setTimesheet_date(output);
//                    filterData.setEmployee_id(employeeId);
//                    List<RMNonComplianceDataDTO> timesheetReport = null;
//                    timesheetReport = timesheetReportService.timesheetReport(filterData);
//                    if(timesheetReport.size()==0){
//                        leaveDetails = timesheetReportService.leaveDetails(filterData);
//                        calendar_id = timesheetReportService.getCalendarId(filterData);
//                        filterData.setCalendar_id(Integer.toString(calendar_id));
//                        holidayDate = timesheetReportService.getHolidayDetails(filterData);
//                        weekEndDetails = timesheetReportService.getWeekEndDetails(Integer.toString(calendar_id));
//                        String weekEnds[] = weekEndDetails.split(",");
//                        weekEndOne = Integer.parseInt(weekEnds[0]);
//                        if(weekEnds.length>1){
//                            weekEndTwo = Integer.parseInt(weekEnds[1]);
//                        }
//                    }
//                    
//                    int weekEnd = calendar.get(calendar.DAY_OF_WEEK);
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                    if(getEmployeeDetails.get(0).getReleased_date()!=null && (formatter.parse(getEmployeeDetails.get(0).getReleased_date()).getTime() < formatter.parse(output).getTime())){
//                        filterData.setWorked_hours("NA");
//                        notApplicable++;
//                    }else if(formatter.parse(getEmployeeDetails.get(0).getJoined_date()).getTime() > formatter.parse(output).getTime()){
//                        filterData.setWorked_hours("NA");
//                        notApplicable++;
//                    }else if(holidayDate != null){
//                        filterData.setWorked_hours("H");
//                        holiday++;
//                    }else if(weekEnd == weekEndOne || weekEnd == weekEndTwo){
//                        if(timesheetReport.size()>0){
//                            filterData.setWorked_hours(timesheetReport.get(0).getWorked_hours());
//                            entered++;
//                        }else{
//                            filterData.setWorked_hours("WE");
//                            week++;
//                        }
//                    }else if(leaveDetails>0){
//                        filterData.setWorked_hours("LA");
//                        leaveApplied++;
//                    }else if(timesheetReport.size()>0){
//                        filterData.setWorked_hours(timesheetReport.get(0).getWorked_hours());
//                        entered++;
//                    }else{
//                        filterData.setWorked_hours("NC");
//                        nonCompliance++;
//                    }
//                    
////                    if(buttonValue.equals("searchProjectData")){
////                        rowDataList.add(i+8, filterData.getWorked_hours());
////                    }else{
//                        rowDataList.add(i+5, filterData.getWorked_hours());
////                    }
//                    
//                    calendar.add(Calendar.DATE, 1);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//                
//            }
            
//            int columnIndexData = 0;
//            columnIndexData = (int)diffDate;
//            if(buttonValue.equals("searchProjectData")){
//                columnIndexData = columnIndexData+3;
//            }
//            else{
//                columnIndexData = columnIndexData;
//            }
////        
//            rowDataList.add(columnIndexData+6,entered);
//            rowDataList.add(columnIndexData+7,nonCompliance);
//            rowDataList.add(columnIndexData+8,leaveApplied);
//            rowDataList.add(columnIndexData+9,week);
//            rowDataList.add(columnIndexData+10,holiday);
//            rowDataList.add(columnIndexData+11,notApplicable);
//            entireList.add(lop+1,rowDataList);
//            entireList.add(rowDataList);
//        }
       
//        downloadExcel(request,response, entireList);
        mv = new ModelAndView("redirect:delinquencyReportList.htm");
        System.out.println("here it comes");
        return mv;
    }
    
    public ModelAndView downloadExcel(HttpServletRequest request, HttpServletResponse response, ArrayList entireList)throws Exception{
        ModelAndView mvc = new ModelAndView("delinquencyReportList");
        try{
            CommonMethods.exportToExcel(response, entireList, "delinquency_report", "timesheet_report", "");

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public ModelAndView delinquencyReportList(HttpServletRequest request, HttpServletResponse response, RMNonComplianceDataDTO filterData) throws Exception{
        ModelAndView mv = null;
        String employeeId = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("employee_no") == null) {
            employeeId = request.getParameter("empId");
            System.out.println("employeeID+++"+employeeId);
            session.setAttribute("employee_no", employeeId);
        } else {
            employeeId = (String) session.getAttribute("employee_no");
            System.out.println("employeeID known+++"+employeeId);
        }
        
        final WebApplicationContext ctx = getWebApplicationContext();
        RMNoncomplianceServiceImpl timesheetReportService = (RMNoncomplianceServiceImpl) ctx.getBean("RMNonComplianceService"); 
        
        String getRMId = timesheetReportService.getRMId(employeeId);
        filterData.setReporting_manager_id(getRMId);
        Date currentDate = new Date();
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        filterData.setStart_date(sfd.format(currentDate));
        List<RMNonComplianceDataDTO> getCompanyStructure = null;
        List<RMNonComplianceDataDTO> getEmployeeList =  timesheetReportService.getEmployeeList(filterData);
        List<RMNonComplianceDataDTO> getProjectList = timesheetReportService.getProjectList(filterData);
        String delinquencyUnitWise = timesheetReportService.getUnitWiseAccess(getRMId);
        if(delinquencyUnitWise != null){
            getCompanyStructure =  timesheetReportService.getCompanyStructure(filterData);
        }else{
            getCompanyStructure = null;
        }
        mv = new ModelAndView("/timesheet_report");
        mv.addObject("structure", getCompanyStructure);
        mv.addObject("employeeList", getEmployeeList);
        mv.addObject("reportingManagerId", getRMId);
        mv.addObject("projectList", getProjectList);
        mv.addObject("unitReport", delinquencyUnitWise);
        return mv;
    }
    
    public ModelAndView attachmentDownload(HttpServletRequest request, HttpServletResponse response, String fileNameDelinquency) throws Exception {
        System.out.println("---" + fileNameDelinquency);
        String filePath=null;
        try{
            configFile.load(new FileInputStream("D:\\propertyfiles\\DelinquencyReport.properties"));
            filePath = configFile.getProperty("FILE_PATH");
        }catch(Exception ex){
        
        }
        configFile.load(new FileInputStream("D:\\propertyfiles\\DelinquencyReport.properties"));
//            filePath = configFile.getProperty("FILE_PATH");
//            File file = new File(filePath+"delinquency_report.csv");
        String fileName = "delinquency_report.csv";
//        String filePath = configFile.getProperty("FILE_PATH");
//        String filePath = "C:\\wamp\\www\\idealbeta\\app\\tmp\\";
        String fileType = "csv";
        System.out.println("file name " + fileName + " file path " + filePath + " file type " + fileType);
        CommonMethods.fileDownload(fileName, filePath, fileType, response);
        return null;
    }
}
