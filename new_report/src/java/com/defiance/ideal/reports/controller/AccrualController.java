/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AccrualDataDTO;
import com.defiance.ideal.reports.dto.AccrualFilterDTO;
import com.defiance.ideal.reports.service.AccrualServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
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
public class AccrualController extends MultiActionController  {

    public AccrualController() {
    }


    public ModelAndView accrualreport(HttpServletRequest request, HttpServletResponse response,AccrualFilterDTO filterData) throws Exception {
        
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId=null;
        //String selectedMonth,selectedYear,;
          
          //create a calendar
          Calendar calndr = Calendar.getInstance();
          calndr.get(Calendar.MONTH);
          calndr.get(Calendar.YEAR);
        
           if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }

        if(filterData.getMonthFilter()==null){
           filterData.setMonthFilter(String.format("%02d",(calndr.get(Calendar.MONTH)+1)));
        }

        if(filterData.getYearFilter()==null){
            int yr = calndr.get(Calendar.YEAR);
            filterData.setYearFilter(Integer.toString(yr));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(filterData.getYearFilter()),(Integer.parseInt(filterData.getMonthFilter())-1),1);
        int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            AccrualServiceImpl accrualService=(AccrualServiceImpl) ctx.getBean("AccrualService");
            
            Map<String,String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer,Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String,String> sbuMap = accrualService.getSbuList();
            Map<String,String> pjctStatsMap = CommonMethods.getPjtStatusList();


            List<AccrualDataDTO> accrualData = accrualService.fetchAccrualData(filterData);
                    
            SOP("Employee ID",employeeId);

            mv = new ModelAndView("/accrual");

            mv.addObject("monthsMap",monthsMap);
            mv.addObject("yearsMap",yearsMap);
            mv.addObject("sbuMap",sbuMap);
            mv.addObject("pjctStatsMap",pjctStatsMap);
            
            mv.addObject("accrualData",accrualData);
            
            mv.addObject("filterData",filterData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }


    public ModelAndView accrualexport(HttpServletRequest request, HttpServletResponse response,AccrualFilterDTO filterData) throws Exception {

        
        final WebApplicationContext ctx = getWebApplicationContext();
        AccrualServiceImpl accrualService = (AccrualServiceImpl) ctx.getBean("AccrualService");
        List<AccrualDataDTO> accrualData = accrualService.fetchAccrualData(filterData);

        ArrayList entireList = new ArrayList();

        for (int i = 0; i < accrualData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + accrualData.get(i).getProjectCode()));
            rowDataList.add(new String("" + accrualData.get(i).getProjectName()));
            rowDataList.add(new String("" + accrualData.get(i).getPmName()));
            rowDataList.add(new String("" + accrualData.get(i).getSbu()));
            rowDataList.add(new String("" + accrualData.get(i).getSubSbu()));
            //rowDataList.add(new String("" + accrualData.get(i).getAccrualStatus()));
            if(accrualData.get(i).getAccrualCount().equals(accrualData.get(i).getTotalTeamCount())) {
                if(accrualData.get(i).getAccrualCount().equals("0") && accrualData.get(i).getTotalTeamCount().equals("0")){
                    accrualData.get(i).setAccrualStatus("Not Submitted");
                } else {
                    accrualData.get(i).setAccrualStatus("Submitted");
                }
            } else {
                accrualData.get(i).setAccrualStatus("Not Submitted");
            }
            rowDataList.add(new String("" + accrualData.get(i).getAccrualCount()+" out of "+accrualData.get(i).getTotalTeamCount() +" Accured - "+accrualData.get(i).getAccrualStatus()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "accrual_report.xls","accrual",null);
        return null;
    }

    public void SOP(String keyToDisp,Object strToPrnt){
//        System.out.println(keyToDisp+"  --- --- = --- --- "+strToPrnt);
    }
}
