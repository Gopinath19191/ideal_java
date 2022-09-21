/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.defiance.ideal.reports.dto.AssociateTimesheetDataDTO;
import com.defiance.ideal.reports.service.AssociateTimesheetServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;

/**
 *
 * @author 14053
 */
public class AssociateTimesheetController extends MultiActionController {

    public AssociateTimesheetController() {
    }

    public ModelAndView associatetimesheetreport(HttpServletRequest request, HttpServletResponse response, AssociateTimesheetDataDTO filterData) throws Exception {

        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String empNUm = "14312";
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

        if (session.getAttribute("EMP_ID") == null) {
//        if (empNUm == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
            //  employeeId = empNUm;
        }

        if (filterData.getMonthFilter() == null) {
            filterData.setMonthFilter(String.format("%02d", (calndr.get(Calendar.MONTH) + 1)));
        }

        if (filterData.getYearFilter() == null) {
            int yr = calndr.get(Calendar.YEAR);
            filterData.setYearFilter(Integer.toString(yr));
        }
        String reportConfigId = CommonConfigurations.REPORTNAME_CONFIG_VALUE;
        filterData.setReportConfigValue(reportConfigId);
        filterData.setSbuId(request.getParameter("sbuFilter"));
        filterData.setReportFilter(request.getParameter("reportFilter"));
        filterData.setReportFilter("cwh");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(filterData.getYearFilter()), (Integer.parseInt(filterData.getMonthFilter().toString()) - 1), 1);
        int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        System.out.println("Employee id -------" + employeeId);
        filterData.setEmployeeId(employeeId);
        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            AssociateTimesheetServiceImpl accrualService = (AssociateTimesheetServiceImpl) ctx.getBean("AssociateTimesheetService");

            Map<String, String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String, String> sbuMap = accrualService.getSbuList();
            List<AssociateTimesheetDataDTO> reportNameMap = accrualService.getReportList(filterData);
       //     Map<String, String> pjctStatsMap = CommonMethods.getPjtStatusList();
//          System.out.println("Filter Data and Form Data----"+filterData.getMonthFilter()+"::::Year Filter----"+filterData.getYearFilter()+":::get SBU filter---"+filterData.getSbuFilter()+"::::get Report Nale filter----"+filterData.getReportFilter());
            String reportType = filterData.getReportFilter();
           // System.out.println("Report Type========" + filterData.getReportFilter() + ":::::::::" + reportType);

            List<AssociateTimesheetDataDTO> accrualData = null;
            if (reportType != null) {
                accrualData = accrualService.fetchAccrualData(filterData);
                if (reportType.equals("cwh")) {
                    mv = new ModelAndView("/associateTimesheet");
                } else if (reportType.equals("ptwh")) {
                    //System.out.println("Second report---");
                    mv = new ModelAndView("/associateTimesheet");
                } else if (reportType.equals("pmwh")) {
                   // System.out.println("Third report----");
                    mv = new ModelAndView("/monthlyWorkedHrs");
                } else if (reportType.equals("npwh")) {
                    //System.out.println("Fourth report-----");
                    mv = new ModelAndView("/nonProjectWorkedHrs");
                } else {
                    mv = new ModelAndView("/associateTimesheet");
                }
            } else {
                mv = new ModelAndView("/associateTimesheet");
            }
           // SOP("Employee ID", employeeId);
            mv.addObject("monthsMap", monthsMap);
            mv.addObject("yearsMap", yearsMap);
            mv.addObject("sbuMap", sbuMap);
            mv.addObject("reportNameMap", reportNameMap);
           // mv.addObject("pjctStatsMap", pjctStatsMap);
            mv.addObject("accrualData", filterData);
            mv.addObject("filterData", accrualData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView associatetimesheetreportexport(HttpServletRequest request, HttpServletResponse response, AssociateTimesheetDataDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        System.out.println("INSIDE EXPPORTT ###");
        AssociateTimesheetServiceImpl accrualService = (AssociateTimesheetServiceImpl) ctx.getBean("AssociateTimesheetService");
        List<AssociateTimesheetDataDTO> accrualData = accrualService.fetchAccrualData(filterData);
        String reportConfigId = CommonConfigurations.REPORTNAME_CONFIG_VALUE;
        filterData.setReportConfigValue(reportConfigId);
        String reportType = filterData.getReportFilter();
reportType="cwh";
        ArrayList entireList = new ArrayList();
        if (accrualData != null) {
            for (int i = 0; i < accrualData.size(); i++) {
                System.out.println("accrualData.get(i).getCustomer_name():"+accrualData.get(i).getCustomerName());
                 System.out.println("Project code"+accrualData.get(i).getProjectCode());
                 
                ArrayList rowDataList = new ArrayList();
                if(!"Non_Project_Activity".equals(accrualData.get(i).getProjectCode())){
                    rowDataList.add(new String("" + accrualData.get(i).getCustomerName()));
                }
                else{
                    rowDataList.add(new String(""));
                }
                    
                rowDataList.add(new String("" + accrualData.get(i).getProjectCode()));
                rowDataList.add(new String("" + accrualData.get(i).getAboutProject()));
                rowDataList.add(new String("" + accrualData.get(i).getEmployeeNumber()));
                rowDataList.add(new String("" + accrualData.get(i).getEmployeeName()));
                rowDataList.add(new String("" + accrualData.get(i).getRm()));
                rowDataList.add(new String("" + accrualData.get(i).getEmploymentStatus()));
               
                if (reportType.equals("cwh")) {
                    rowDataList.add(new String("" + accrualData.get(i).getSbu()));
                    rowDataList.add(new String("" + accrualData.get(i).getSubSbu()));
                    rowDataList.add(new String("" + accrualData.get(i).getJoinedDate()));
                    rowDataList.add(new String("" + accrualData.get(i).getReleasedDate()));
                }
               
//                rowDataList.add(new String("" + accrualData.get(i).getDay1()));
                
                rowDataList.add(new String("~~" + accrualData.get(i).getDay1()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay2()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay3()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay4()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay5()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay6()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay7()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay8()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay9()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay10()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay11()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay12()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay13()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay14()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay15()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay16()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay17()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay18()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay19()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay20()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay21()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay22()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay23()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay24()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay25()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay26()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay27()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay28()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay29()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay30()));
                rowDataList.add(new String("~~" + accrualData.get(i).getDay31()));
               if (accrualData.get(i).getAvailableHrs() != null) {
                    rowDataList.add(new String("~~" + accrualData.get(i).getAvailableHrs()));
                } else {
                    rowDataList.add(new String("~~"+"0.00"));
                }
                if (accrualData.get(i).getWorked_hours() != null) {
                    rowDataList.add(new String("~~" + accrualData.get(i).getWorked_hours()));
                } else {
                    rowDataList.add(new String("~~"+"0.00"));
                }
                 
               
                
              
                              
                if (accrualData.get(i).getApproved_hours() != null) {
                    rowDataList.add(new String("~~" + accrualData.get(i).getApproved_hours()));
                } else {
                    rowDataList.add(new String("~~"+"0.00"));
                }
                 
                if (reportType.equals("cwh") || reportType.equals("pmwh")) {
                    if(accrualData.get(i).getAccrued_hours() != null){
                    rowDataList.add(new String("~~" + accrualData.get(i).getAccrued_hours()));
                    }
                    else{
                        rowDataList.add(new String("~~"+"0.00"));
                    }
                }
                if (reportType.equals("cwh") || reportType.equals("pmwh")) {
                    if(accrualData.get(i).getAccrued_hours() != null){
                    rowDataList.add(new String("~~" + accrualData.get(i).getWorking_days()));
                    }
                    else{
                        rowDataList.add(new String("~~"+"0.00"));
                    }
                }
                if (reportType.equals("cwh") || reportType.equals("pmwh")) {
                    if(accrualData.get(i).getAccrued_hours() != null){
                    rowDataList.add(new String("~~" + accrualData.get(i).getNoofLeaveTakenDays()));
                    }
                    else{
                        rowDataList.add(new String("~~"+"0.00"));
                    }
                }

                //rowDataList.add(new String("" + accrualData.get(i).getAccrualStatus()));
//            if (accrualData.get(i).getAccrualCount().equals(accrualData.get(i).getTotalTeamCount())) {
//                if (accrualData.get(i).getAccrualCount().equals("0") && accrualData.get(i).getTotalTeamCount().equals("0")) {
//                    accrualData.get(i).setAccrualStatus("Not Submitted");
//                } else {
//                    accrualData.get(i).setAccrualStatus("Submitted");
//                }
//            } else {
//                accrualData.get(i).setAccrualStatus("Not Submitted");
//            }
                //rowDataList.add(new String("" + accrualData.get(i).getAccrualCount() + " out of " + accrualData.get(i).getTotalTeamCount() + " Accured - " + accrualData.get(i).getAccrualStatus()));
                entireList.add(rowDataList);
            }
        }
        String sheetName = "";
        if (reportType != null) {
            if (reportType.equals("cwh")) {
                sheetName = "Associate_Consolidated_Worked_Hours.xls";
            } else if (reportType.equals("ptwh")) {
                sheetName = "Competency_Total_Worked_Hours.xls";
            } else if (reportType.equals("pmwh")) {
                sheetName = "Associate_Competency_Load_Factor.xls";
            } else {
                sheetName = "Associate_NonProject_Activity_With_Phase_Load_Factor.xls";
            }
        } else {
            sheetName = "Associate_consolidated_worked_hours.xls";
        }
        CommonMethods.exportToExcel(response, entireList, sheetName, "accrual", null);
        return null;
    }

    public void SOP(String keyToDisp, Object strToPrnt) {
//        System.out.println(keyToDisp + "  --- --- = --- --- " + strToPrnt);
    }
    
    public ModelAndView associateTimeSheetChart(HttpServletRequest request, HttpServletResponse response, AssociateTimesheetDataDTO filterData) throws Exception {

    	//System.out.println("inside chart controller");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String empNUm = "14312";
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

        if (session.getAttribute("EMP_ID") == null) {
//        if (empNUm == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
            //  employeeId = empNUm;
        }

        if (filterData.getMonthFilter() == null) {
            filterData.setMonthFilter(String.format("%02d", (calndr.get(Calendar.MONTH) + 1)));
        }

        if (filterData.getYearFilter() == null) {
            int yr = calndr.get(Calendar.YEAR);
            filterData.setYearFilter(Integer.toString(yr));
        }
        String reportConfigId = CommonConfigurations.REPORTNAME_CONFIG_VALUE;
        filterData.setReportConfigValue(reportConfigId);
        filterData.setSbuId(request.getParameter("sbuFilter"));
        filterData.setReportFilter(request.getParameter("reportFilter"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(filterData.getYearFilter()), (Integer.parseInt(filterData.getMonthFilter().toString()) - 1), 1);
        int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        System.out.println("Employee id -------" + employeeId);
        filterData.setEmployeeId(employeeId);
        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            AssociateTimesheetServiceImpl accrualService = (AssociateTimesheetServiceImpl) ctx.getBean("AssociateTimesheetService");

            Map<String, String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String, String> sbuMap = accrualService.getSbuList();
            List<AssociateTimesheetDataDTO> reportNameMap = accrualService.getReportList(filterData);
       //     Map<String, String> pjctStatsMap = CommonMethods.getPjtStatusList();
//          System.out.println("Filter Data and Form Data----"+filterData.getMonthFilter()+"::::Year Filter----"+filterData.getYearFilter()+":::get SBU filter---"+filterData.getSbuFilter()+"::::get Report Nale filter----"+filterData.getReportFilter());
            String reportType = filterData.getReportFilter();
           // System.out.println("Report Type========" + filterData.getReportFilter() + ":::::::::" + reportType);

            List<AssociateTimesheetDataDTO> accrualData = null;
            accrualData = accrualService.fetchAccrualData(filterData);
            mv = new ModelAndView("/associateTimesheetChart");
           // SOP("Employee ID", employeeId);
            mv.addObject("monthsMap", monthsMap);
            mv.addObject("yearsMap", yearsMap);
            mv.addObject("sbuMap", sbuMap);
            mv.addObject("reportNameMap", reportNameMap);
           // mv.addObject("pjctStatsMap", pjctStatsMap);
            mv.addObject("accrualData", filterData);
            
            //Populate data for chart series.
            String totalAvailableHrs=""; int totalAvailHrsInt = 0;
            String workedHours=""; double workedHrsInt = 0.0;
            String appHours = ""; double appHrsDbl = 0.0;
            String billedHours = ""; double billedHrsDbl = 0.0;
            String projectWPO =""; double projectWPODbl = 0.0;
            String competency =""; double compentencyDbl = 0.0;
            String noPOProject =""; double noPOProjectDbl = 0.0;
            String businessWait =""; double businessWaitDbl = 0.0;
            String projectWait=""; double projectWaitDbl = 0.0;
            String nonProjectActivity=""; double nonProjectActivityDbl=0.0;
            String notEnteredHours = ""; double notEnteredHoursDbl=0.0;            
            String leaveHours = ""; double leaveHoursDbl=0.0;
            
            Iterator<AssociateTimesheetDataDTO> itr = accrualData.iterator();
            AssociateTimesheetDataDTO dto = null;
            while(itr.hasNext()){
            	dto = itr.next();
            	if(dto.getTotalAvailableHour()!=null){
            		totalAvailHrsInt = totalAvailHrsInt + Integer.parseInt(dto.getTotalAvailableHour());
            		
            	}
            	if(dto.getWorked_hours()!=null){
            		workedHrsInt = workedHrsInt + Double.parseDouble(dto.getWorked_hours());
            	}
            	if(dto.getApproved_hours()!=null){
            		appHrsDbl = appHrsDbl + Double.parseDouble(dto.getApproved_hours());
            	}
            	if(dto.getAccrued_hours()!=null){
            		billedHrsDbl = billedHrsDbl + Double.parseDouble(dto.getAccrued_hours());
            	}
            	
            	if(dto.getProjectWPO()!=null){
            		projectWPODbl = projectWPODbl + Double.parseDouble(dto.getProjectWPO());
            	}
            	if(dto.getCompetency()!=null){
            		compentencyDbl = compentencyDbl + Double.parseDouble(dto.getCompetency());
            	}
            	if(dto.getNoPOProject()!=null){
            		noPOProjectDbl = noPOProjectDbl + Double.parseDouble(dto.getNoPOProject());
            	}
            	if(dto.getBusinessWait()!=null){
            		businessWaitDbl = businessWaitDbl + Double.parseDouble(dto.getBusinessWait());
            	}
            	if(dto.getProjectwait()!=null){
            		projectWaitDbl = projectWaitDbl + Double.parseDouble(dto.getProjectwait());
            	}
            	if(dto.getNonProjectActivity()!=null){
            		nonProjectActivityDbl = nonProjectActivityDbl + Double.parseDouble(dto.getNonProjectActivity());
            	}
            	if(dto.getNotenteredHours()!=null){
            		notEnteredHoursDbl = notEnteredHoursDbl + Double.parseDouble(dto.getNotenteredHours());
            	}
            	if(dto.getTotalLeaveHours()!=null){
            		leaveHoursDbl = leaveHoursDbl + Double.parseDouble(dto.getTotalLeaveHours());
            	}
            }
             
            totalAvailableHrs = String.valueOf(Math.round(totalAvailHrsInt));
            workedHours=String.valueOf(Math.round(workedHrsInt));
            appHours = String.valueOf(Math.round(appHrsDbl));
            billedHours = String.valueOf(Math.round(billedHrsDbl));
            
            projectWPO = String.valueOf(Math.round(projectWPODbl));
            competency = String.valueOf(Math.round(compentencyDbl));
            noPOProject = String.valueOf(noPOProjectDbl);
            businessWait = String.valueOf(Math.round(businessWaitDbl));
            projectWait = String.valueOf(Math.round(projectWaitDbl));
            nonProjectActivity = String.valueOf(Math.round(nonProjectActivityDbl));
            notEnteredHours = String.valueOf(Math.round(notEnteredHoursDbl));
            leaveHours = String.valueOf(Math.round(leaveHoursDbl));
            
            String finalList = projectWPO + ", "+ competency +  "," + noPOProject + "," +  businessWait + "," + projectWait + "," + nonProjectActivity + "," + notEnteredHours + "," + leaveHours;            
            finalList = finalList + "," + totalAvailableHrs + "," + workedHours + "," + appHours + "," + billedHours;
            
            Map result = new HashMap();
            result.put("finalList", finalList);
            
            result.put("projectWPO", projectWPO);
            result.put("competency", competency);
            result.put("noPOProject", noPOProject);
            result.put("businessWait", businessWait);
            result.put("projectWait", projectWait);
            result.put("nonProjectActivity", nonProjectActivity);
            result.put("notEnteredHours", notEnteredHours);
            result.put("leaveHours", leaveHours);
            result.put("totalAvailableHrs", totalAvailableHrs);
            result.put("workedHours", workedHours);
            result.put("appHours", appHours);
            result.put("billedHours", billedHours);
            
            String Category = "'Project WPO','Competency', 'No PO Project','Business Wait','Project Wait','Non Project Activity' , 'Not Entered Hours','Leave Hours','Total Available Hours','Worked Hours', 'Approved Hours', 'Billed Hours'";
            result.put("category", Category);
            
            mv.addObject("result",result);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    
    
    
}
