/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import com.defiance.ideal.reports.service.EarnedLeaveService;
import com.defiance.ideal.reports.service.EarnedLeaveServiceImpl;
import com.defiance.ideal.reports.service.MonthlyLeaveService;
import com.defiance.ideal.reports.service.MonthlyLeaveServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import java.util.Calendar;

/**
 *
 * @author 14517
 */
public class EarnedLeaveReportController extends MultiActionController {
    public EarnedLeaveReportController() {
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            String empVal = request.getParameter("q");
           // System.out.println("fefefe"+empVal);
//            Map<String, String> empRes = earnedService.getSearchList(empVal);
            List<EarnedLeaveDataDTO> empRes = earnedService.getSearchList(empVal);
           // System.out.println("dgdgdg"+empRes.size());
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView earnedleave(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/earnedleave");
        List<EarnedLeaveDataDTO> leaveRecord = null;
        //int financial_year;
        try {
            int currentYear;
        Calendar calendarInst = Calendar.getInstance();
        currentYear = calendarInst.get(Calendar.YEAR);
            //financial_year = CommonMethods.getFinancialYear();
            filterData.setFinancialYear(Integer.toString(currentYear));
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            leaveRecord = earnedService.getLeaveRecord(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        mvc.addObject("empName", filterData.getEmployee_search());
        mvc.addObject("leave", leaveRecord);
        return mvc;
    }

    public ModelAndView getEarnedLeaveXL(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) throws Exception {
        try {
            int currentYear;
            Calendar calendarInst = Calendar.getInstance();
            currentYear = calendarInst.get(Calendar.YEAR);
            //int financial_year;
            //financial_year = CommonMethods.getFinancialYear();
            filterData.setFinancialYear(Integer.toString(currentYear));
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            List<EarnedLeaveDataDTO> leaveRecordsList = earnedService.getLeaveRecord(filterData);

            ArrayList entireList = new ArrayList();
            for (int i = 0; i < leaveRecordsList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpNumber()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpName()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getRmName()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getUnit()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getCmpStructSubGroup()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEarnedLeave()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "earned_leave_report.xls", "Earned Leave Report", null);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView encashment(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/encashment");
         String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        List<EarnedLeaveDataDTO> leaveRecord = null;
         Map<String, String> monthsList = CommonMethods.getMonthsList();
        Map<Integer, Integer> yearsList = CommonMethods.getYearsList(4);
          Calendar cal = Calendar.getInstance();
        try {
            if (null == filterData.getFilter_month()) {
                filterData.setFilter_month((months[cal.get(Calendar.MONTH)]));
            }
            if (null == filterData.getFilter_year()) {
                filterData.setFilter_year(Integer.toString(cal.get(Calendar.YEAR)));
            }
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            Map<String, String> sbuMap = earnedService.getSbuList();
            leaveRecord = earnedService.getEncashRecord(filterData);
            mvc.addObject("sbuMap", sbuMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        mvc.addObject("empName", filterData.getEmployee_search());
        mvc.addObject("leave", leaveRecord);
        mvc.addObject("monthsList", monthsList);
        mvc.addObject("yearsList", yearsList);
        return mvc;
    }

    public ModelAndView getEncashXL(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) throws Exception {
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            List<EarnedLeaveDataDTO> leaveRecordsList = earnedService.getEncashRecord(filterData);

            ArrayList entireList = new ArrayList();
            for (int i = 0; i < leaveRecordsList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpNumber()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpName()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getUnit()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getCmpStructSubGroup()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getAppliedDate()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getFinancialYear().substring(0,4)));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getDaysCount()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEarnedLeave()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "encashment_report.xls", "Encashment Report", null);
        } catch(Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    public ModelAndView lop(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/lop");
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        List<EarnedLeaveDataDTO> leaveRecord = null;
        Map<String, String> monthsList = CommonMethods.getMonthsList();
        Map<Integer, Integer> yearsList = CommonMethods.getYearsList(4);
        Calendar cal = Calendar.getInstance();
        try {
            if (null == filterData.getFilter_month()) {
                filterData.setFilter_month((months[cal.get(Calendar.MONTH)]));
            }
            if (null == filterData.getFilter_year()) {
                filterData.setFilter_year(Integer.toString(cal.get(Calendar.YEAR)));
            }
            final WebApplicationContext ctx = getWebApplicationContext();
            
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            leaveRecord = earnedService.getLopRecord(filterData);
            
            MonthlyLeaveService monthlyService = (MonthlyLeaveServiceImpl) ctx.getBean("MonthlyLeaveService");
            if(filterData != null){
            String employeeName = monthlyService.getEmployeeName(filterData.getEmployee_id());
            mvc.addObject("employeeName", employeeName);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        mvc.addObject("leave", leaveRecord);
        mvc.addObject("monthsList", monthsList);
        mvc.addObject("yearsList", yearsList);
        return mvc;
    }


    public ModelAndView getLopXL(HttpServletRequest request, HttpServletResponse response, EarnedLeaveFilterDTO filterData) throws Exception {
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            EarnedLeaveService earnedService = (EarnedLeaveServiceImpl) ctx.getBean("EarnedLeaveService");
            List<EarnedLeaveDataDTO> leaveRecordsList = earnedService.getLopRecord(filterData);

            ArrayList entireList = new ArrayList();
            for (int i = 0; i < leaveRecordsList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpNumber()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpName()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getUnit()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getCmpStructSubGroup()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getAppliedDate()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getFromDate()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getToDate()));
                rowDataList.add(new String("" + leaveRecordsList.get(i).getDaysCount()));
                entireList.add(rowDataList);
                //System.out.println("@@@@@@@@@@@@@@@"+leaveRecordsList.get(i).getEmpNumber());
            }

            CommonMethods.exportToExcel(response, entireList, "lop_report.xls", "Loss of Pay Report",null);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
