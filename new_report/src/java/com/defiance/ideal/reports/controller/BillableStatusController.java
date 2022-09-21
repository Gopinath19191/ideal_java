/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AccrualDataDTO;
import com.defiance.ideal.reports.dto.AccrualFilterDTO;
import com.defiance.ideal.reports.dto.BillableStatusDataDTO;
import com.defiance.ideal.reports.dto.BillableStatusFilterDTO;
import com.defiance.ideal.reports.service.AccrualServiceImpl;
import com.defiance.ideal.reports.service.BillableStatusService;
import com.defiance.ideal.reports.service.BillableStatusServiceImpl;
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
public class BillableStatusController extends MultiActionController  {

    public BillableStatusController() {
    }


    public ModelAndView billablereport(HttpServletRequest request, HttpServletResponse response,BillableStatusFilterDTO filterData) throws Exception {

        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId=null;
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);
      
        employeeId = (String) session.getAttribute("EMP_ID");
        
//        if(filterData.getMonthFilter()==null){
//           filterData.setMonthFilter(String.format("%02d",(calndr.get(Calendar.MONTH)+1)));
//        }
//        if(filterData.getYearFilter()==null){
//            int yr = calndr.get(Calendar.YEAR);
//            filterData.setYearFilter(Integer.toString(yr));
//        }
        
        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            BillableStatusService billableService=(BillableStatusServiceImpl) ctx.getBean("BillableService");
            
            Map<String,String> joinedMonthsMap = CommonMethods.getMonthsList();
            Map<Integer,Integer> joinedYearsMap = CommonMethods.getYearsList(8);
            Map<String,String> sbuMap = billableService.getSbuList();

          List<BillableStatusDataDTO> billableData = billableService.fetchBillableAssociateData(filterData);
                    
            SOP("Employee ID",employeeId);
            mv = new ModelAndView("/billablestatus");
            
            mv.addObject("joinedMonthsMap",joinedMonthsMap);
            mv.addObject("joinedYearsMap",joinedYearsMap);
            mv.addObject("sbuMap",sbuMap);
            mv.addObject("billableData",billableData);
            mv.addObject("filterData",filterData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView billableexport(HttpServletRequest request, HttpServletResponse response,BillableStatusFilterDTO filterData) throws Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        BillableStatusService billableService=(BillableStatusServiceImpl) ctx.getBean("BillableService");
        List<BillableStatusDataDTO> billableData = billableService.fetchBillableAssociateData(filterData);

        ArrayList entireList = new ArrayList();

        for (int i = 0; i < billableData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + billableData.get(i).getEmpNumber()));
            rowDataList.add(new String("" + billableData.get(i).getEmpName()));
            rowDataList.add(new String("" + billableData.get(i).getEmpStatus()));
            rowDataList.add(new String("" + billableData.get(i).getPmName()));
            rowDataList.add(new String("" + billableData.get(i).getJoinedDate()));
            rowDataList.add(new String("" + billableData.get(i).getReleasedDate()));
            rowDataList.add(new String("" + billableData.get(i).getSbuName()));
            rowDataList.add(new String("" + billableData.get(i).getSubSbu()));
            rowDataList.add(new String("" + billableData.get(i).getProjectCode()));
            rowDataList.add(new String("" + billableData.get(i).getProjectName()));
            rowDataList.add(new String("" + billableData.get(i).getCustName()));
            rowDataList.add(new String("" + billableData.get(i).getBillStatus()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "billable_status_report.xls","Billable Status",null);
         
        return null;
    }

    public void SOP(String keyToDisp,Object strToPrnt){
        //System.out.println(keyToDisp+"  --- --- = --- --- "+strToPrnt);
    }
}
