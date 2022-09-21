/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.BenchReportDataDTO;
import com.defiance.ideal.reports.dto.BenchReportFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.BenchReportServiceImpl;
import com.defiance.ideal.reports.service.BenchReportService;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 14619
 */
public class BenchReportController extends MultiActionController {

    public BenchReportController() {
    }

    public ModelAndView benchReport(HttpServletRequest request, HttpServletResponse response,BenchReportFilterDTO filterData) {
        ModelAndView mvc = null;
        try {
           // System.out.println("##############################Inside Controller");
            mvc = new ModelAndView("/benchReport");
            final WebApplicationContext ctx = getWebApplicationContext();
            BenchReportService benchService = (BenchReportServiceImpl) ctx.getBean("BenchReportService");
            AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
            List<AssociateFilterDTO> subSbu = bo.getSubSbu("2,5");
            List<BenchReportDataDTO> benchList = benchService.fetchBenchList(filterData);
            Map<String, String> sbuMap = benchService.getSbuList();
            Map<String, String> bandMap = benchService.getBandList();
            mvc.addObject("sbuMap", sbuMap);
            mvc.addObject("bandMap", bandMap);
            mvc.addObject("filterData", filterData);
            mvc.addObject("bench", benchList);
            mvc.addObject("subSbu", subSbu);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mvc;

    }

    public ModelAndView getBenchReportXL(HttpServletRequest request, HttpServletResponse response, BenchReportFilterDTO filterData) throws Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
         BenchReportService benchService = (BenchReportServiceImpl) ctx.getBean("BenchReportService");
       List<BenchReportDataDTO> benchList = benchService.fetchBenchList(filterData);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < benchList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + benchList.get(i).getEmpNumber()));
            rowDataList.add(new String("" + benchList.get(i).getEmpName()));
            rowDataList.add(new String("" + benchList.get(i).getEmpStatus()));
            rowDataList.add(new String("" + benchList.get(i).getRmName()));
            rowDataList.add(new String("" + benchList.get(i).getJoiningDate()));
            rowDataList.add(new String("" + benchList.get(i).getSbu()));
            rowDataList.add(new String("" + benchList.get(i).getSubSbu()));
            rowDataList.add(new String("" + benchList.get(i).getBand()));
            rowDataList.add(new String("" + benchList.get(i).getDtExp()));
            rowDataList.add(new String("" + benchList.get(i).getLastBilledDate()));
            rowDataList.add(new String("" + benchList.get(i).getLastBilledHrs()));
            rowDataList.add(new String("" + benchList.get(i).getLastProjectId()));
            rowDataList.add(new String("" + benchList.get(i).getBenchDays()));

            entireList.add(rowDataList);
        }
        Calendar cal=Calendar.getInstance();
        String[] months = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};
        String month = null;
        if(cal.get(Calendar.MONTH) == 0) {
           month = months[(cal.get(Calendar.MONTH))];
        } else {
            month = months[(cal.get(Calendar.MONTH))-1];
        }
        String year=Integer.toString(cal.get(Calendar.YEAR));
        CommonMethods.exportToExcel(response, entireList, "Bench_Report_as_on_", "Bench Report",month+"_"+year);
        return null;
    }
}
