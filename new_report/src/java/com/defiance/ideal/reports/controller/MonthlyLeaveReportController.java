/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.MonthlyLeaveDataDTO;
import com.defiance.ideal.reports.dto.MonthlyLeaveFilterDTO;
import com.defiance.ideal.reports.service.MonthlyLeaveService;
import com.defiance.ideal.reports.service.MonthlyLeaveServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/** 
 *
 * @author 14517
 */
public class MonthlyLeaveReportController extends MultiActionController {

    public MonthlyLeaveReportController() {
    }

    public ModelAndView monthlyleavereport(HttpServletRequest request, HttpServletResponse response, MonthlyLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/monthlyleavereport");
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        List<MonthlyLeaveDataDTO> leaveRecord = null;
        String selectFromDate = null;
        String selectToDate = null;
        Map<String, String> monthsList = CommonMethods.getMonthsList();
        Map<Integer, Integer> yearsList = CommonMethods.getYearsList(4);
         
        //System.out.println("&&&&&&&&" + monthsList);
        final WebApplicationContext ctxa = getWebApplicationContext();
            MonthlyLeaveService monthlyServicea = (MonthlyLeaveServiceImpl) ctxa.getBean("MonthlyLeaveService");
            //Map<String, String> sbuMap = monthlyService.getSbuList();
            List<MonthlyLeaveFilterDTO> sbuMapa = (List) monthlyServicea.getStructureList();
        Calendar cal = Calendar.getInstance();
        try {
//            if (null == filterData.getMonthFilter()) {
//               filterData.setMonthFilter(months[(cal.get(Calendar.MONTH))]);
//            }
//            if (null == filterData.getYearFilter()) {
//                filterData.setYearFilter(Integer.toString(cal.get(Calendar.YEAR)));
//           }
            
          
//            if(null == filterData.getSbuFilter()){
//                filterData.setSbuFilter("");
//            }
            
//            if(filterData.getFilter_year()==null){
//            filterData.setFilter_year("2016");
//        }
//        if(filterData.getFilter_month()==null){
//            filterData.setFilter_month("01");
//        }
            
            selectFromDate = filterData.getFrom_date();
            selectToDate = filterData.getTo_date();
            if (null == filterData.getFrom_date()) {
                filterData.setLeaveFilter("1");
            }
            if (null == filterData.getTo_date()) {
                String xxx = months[(cal.get(Calendar.MONTH))];
                filterData.setTo_date(Integer.toString(cal.get(Calendar.YEAR))+'-'+xxx+'-'+Integer.toString(cal.get(Calendar.DATE)));
                //System.out.println("--->"+Integer.toString(cal.get(Calendar.YEAR))+'-'+xxx+'-'+Integer.toString(cal.get(Calendar.DATE)));
            }
            
            final WebApplicationContext ctx = getWebApplicationContext();
            MonthlyLeaveService monthlyService = (MonthlyLeaveServiceImpl) ctx.getBean("MonthlyLeaveService");
            //Map<String, String> sbuMap = monthlyService.getSbuList();
            List<MonthlyLeaveFilterDTO> sbuMap = (List) monthlyService.getStructureList();
            leaveRecord = monthlyService.getLeaveRecord(filterData);
            if(filterData != null){
            String employeeName = monthlyService.getEmployeeName(filterData.getEmployee_id());
            mvc.addObject("employeeName", employeeName);
        }
            mvc.addObject("sbuMap", sbuMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mvc.addObject("filterData", filterData);
        mvc.addObject("months", monthsList);
        mvc.addObject("years", yearsList);
         mvc.addObject("monthsList", monthsList);
        mvc.addObject("yearsList", yearsList);
       mvc.addObject("sbuMap", sbuMapa);
        mvc.addObject("leave", leaveRecord);
        mvc.addObject("selectFromDate", selectFromDate);
        mvc.addObject("selectToDate", selectToDate);
        return mvc;
    }

    public ModelAndView getMonthlyLeaveXL(HttpServletRequest request, HttpServletResponse response, MonthlyLeaveFilterDTO filterData) throws Exception {

        if (null == filterData.getFrom_date()) {
            filterData.setLeaveFilter("1");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        MonthlyLeaveService monthlyService = (MonthlyLeaveServiceImpl) ctx.getBean("MonthlyLeaveService");
        List<MonthlyLeaveDataDTO> leaveRecordsList = monthlyService.getLeaveRecord(filterData);
        
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < leaveRecordsList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + leaveRecordsList.get(i).getEmpNumber()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getFirstName()+" "+leaveRecordsList.get(i).getLastName()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getCompStruct()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getCompStructSubgroup()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getLeaveType()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getLeaveReason()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getDateApplied()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getFromDate()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getToDate()));
          //  rowDataList.add(new String("" + leaveRecordsList.get(i).getLength()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getLeaveLength()));
            rowDataList.add(new String("" + leaveRecordsList.get(i).getLeaveStatus()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "monthly_leave_report.xls", "Monthly Leave Report",null);
        return null;
    }
}
