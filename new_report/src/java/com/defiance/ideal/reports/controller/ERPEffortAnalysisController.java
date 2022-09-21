package com.defiance.ideal.reports.controller;

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

public class ERPEffortAnalysisController extends MultiActionController {

	public ModelAndView showEffortAnalysisFilter(HttpServletRequest request,
			HttpServletResponse response, AssociateTimesheetDataDTO filterData) {
		ModelAndView mv = null;
		mv = new ModelAndView("/effortAnalysisReport");
		HttpSession session = request.getSession();
		String employeeId = null;
		// String empNUm = "14312";
		// String selectedMonth,selectedYear,;

		Calendar calndr = Calendar.getInstance();
		calndr.get(Calendar.MONTH);
		calndr.get(Calendar.YEAR);

		if (session.getAttribute("EMP_ID") == null) {
			// if (empNUm == null) {
			employeeId = request.getParameter("empid");
			session.setAttribute("EMP_ID", employeeId);
		} else {
			employeeId = (String) session.getAttribute("EMP_ID");
			// employeeId = empNUm;
		}

		if (filterData.getMonthFilter() == null) {
			filterData.setMonthFilter(String.format("%02d",
					(calndr.get(Calendar.MONTH) + 1)));
		}

		if (filterData.getYearFilter() == null) {
			int yr = calndr.get(Calendar.YEAR);
			filterData.setYearFilter(Integer.toString(yr));
		}
		String reportConfigId = CommonConfigurations.REPORTNAME_CONFIG_VALUE;
		filterData.setReportConfigValue(reportConfigId);
		filterData.setSbuId(request.getParameter("sbuFilter"));
		//filterData.setReportFilter(request.getParameter("reportFilter"));
		filterData.setReportFilter("cwh");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(filterData.getYearFilter()),
				(Integer.parseInt(filterData.getMonthFilter().toString()) - 1),
				1);
		int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println("Employee id -------" + employeeId);
		filterData.setEmployeeId(employeeId);
		try {

			final WebApplicationContext ctx = getWebApplicationContext();
			AssociateTimesheetServiceImpl accrualService = (AssociateTimesheetServiceImpl) ctx
					.getBean("AssociateTimesheetService");

			Map<String, String> monthsMap = CommonMethods.getMonthsList();
			Map<Integer, Integer> yearsMap = CommonMethods.getYearsList(2);
			Map<String, String> sbuMap = accrualService.getSbuList();
			List<AssociateTimesheetDataDTO> reportNameMap = accrualService
					.getReportList(filterData);
			// Map<String, String> pjctStatsMap =
			// CommonMethods.getPjtStatusList();
			// System.out.println("Filter Data and Form Data----"+filterData.getMonthFilter()+"::::Year Filter----"+filterData.getYearFilter()+":::get SBU filter---"+filterData.getSbuFilter()+"::::get Report Nale filter----"+filterData.getReportFilter());
			String reportType = filterData.getReportFilter();
			// System.out.println("Report Type========" +
			// filterData.getReportFilter() + ":::::::::" + reportType);

			List<AssociateTimesheetDataDTO> accrualData = null;
			accrualData = accrualService.fetchAccrualData(filterData);
			//mv = new ModelAndView("/effortAnalysisReport");
			// SOP("Employee ID", employeeId);
			mv.addObject("monthsMap", monthsMap);
			mv.addObject("yearsMap", yearsMap);
			mv.addObject("sbuMap", sbuMap);
			mv.addObject("reportNameMap", reportNameMap);
			// mv.addObject("pjctStatsMap", pjctStatsMap);
			mv.addObject("accrualData", filterData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	public ModelAndView effortAnalysisChart(HttpServletRequest request,
			HttpServletResponse response, AssociateTimesheetDataDTO filterData)
			throws Exception {

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
        //filterData.setReportFilter(request.getParameter("reportFilter"));
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
            accrualData = accrualService.fetchAccrualData(filterData);
            mv = new ModelAndView("/effortAnalysisReport");
           // SOP("Employee ID", employeeId);
            mv.addObject("monthsMap", monthsMap);
            mv.addObject("yearsMap", yearsMap);
            mv.addObject("sbuMap", sbuMap);
            mv.addObject("reportNameMap", reportNameMap);
           // mv.addObject("pjctStatsMap", pjctStatsMap);
            mv.addObject("accrualData", filterData);
            
            
            //To retrieve other hours.
            filterData.setSbuId(request.getParameter("sbuFilter"));
            filterData.setYearValue(filterData.getYearFilter());
            filterData.setMonthValue(filterData.getMonthFilter());
            
            filterData.setPhaseId(CommonConfigurations.TrainingPhaseValue);
            AssociateTimesheetDataDTO dto = accrualService.getSummation(filterData);
            String trainingHrs = dto.getTotal();
            if(trainingHrs==null){
            	trainingHrs="0";
            }
            
            filterData.setPhaseId(CommonConfigurations.ProjectManagementPhaseValue);
            dto = accrualService.getSummation(filterData);
            String prjMgmtHrs = dto.getTotal();
            if(prjMgmtHrs==null){
            	prjMgmtHrs="0";
            }
            
            filterData.setPhaseId(CommonConfigurations.PreSalesPhaseValue);
            dto = accrualService.getSummation(filterData);
            String preSalesHrs = dto.getTotal();
            if(preSalesHrs==null){
            	preSalesHrs="0";
            }
            
            
            //Populate data for chart series.
            String totalAvailableHrs=""; double totalAvailHrsDbl = 0.0;
            String billedHours = ""; double billedHrsDbl = 0.0;
            String competency =""; double compentencyDbl = 0.0;
            String leaveHours = ""; double leaveHoursDbl=0.0;
            double trainingHrsDbl = 0.0, prjMgmtHrsDbl = 0.0, preSalesHrsDbl = 0.0;
            
            Iterator<AssociateTimesheetDataDTO> itr = accrualData.iterator();
            dto = null;
            while(itr.hasNext()){
            	dto = itr.next();
            	if(dto.getTotalAvailableHour()!=null){
            		totalAvailHrsDbl = totalAvailHrsDbl + Double.parseDouble(dto.getTotalAvailableHour());            	
            	}            	
            	if(dto.getAccrued_hours()!=null){
            		billedHrsDbl = billedHrsDbl + Double.parseDouble(dto.getAccrued_hours());
            	}            	            
            	if(dto.getCompetency()!=null){
            		compentencyDbl = compentencyDbl + Double.parseDouble(dto.getCompetency());
            	}            	
            	if(dto.getTotalLeaveHours()!=null){
            		leaveHoursDbl = leaveHoursDbl + Double.parseDouble(dto.getTotalLeaveHours());
            	}
            }

            Map chartData = new HashMap();
            chartData.put("totalAvailHrs", round(totalAvailHrsDbl,2));
            chartData.put("billedHrsTotal", round(billedHrsDbl,2));
            chartData.put("compentencyTotal", round(compentencyDbl,2));            
            chartData.put("leaveHoursTotal", round(leaveHoursDbl,2));            
            chartData.put("trainingHrsTotal", round(Double.valueOf(trainingHrs),2));
            chartData.put("prjMgmtHrsTotal", round(Double.valueOf(prjMgmtHrs),2));
            chartData.put("preSalesHrsTotal", round(Double.valueOf(preSalesHrs),2));

            
            
                        
            //Calculate percentages.
            billedHrsDbl = round((billedHrsDbl/totalAvailHrsDbl) * 100,2);
            compentencyDbl = round((compentencyDbl/totalAvailHrsDbl) * 100,2);
            leaveHoursDbl = round((leaveHoursDbl/totalAvailHrsDbl) * 100,2);
            trainingHrsDbl = round((Double.parseDouble(trainingHrs)/totalAvailHrsDbl)*100,2);
            prjMgmtHrsDbl = round((Double.parseDouble(prjMgmtHrs)/totalAvailHrsDbl)*100,2);
            preSalesHrsDbl =  round((Double.parseDouble(preSalesHrs)/totalAvailHrsDbl)*100,2);
            
                                   
            billedHours = String.valueOf(billedHrsDbl);
            competency = String.valueOf(compentencyDbl);
            leaveHours = String.valueOf(leaveHoursDbl);
            trainingHrs = String.valueOf(trainingHrsDbl);
            prjMgmtHrs = String.valueOf(prjMgmtHrsDbl);
            preSalesHrs = String.valueOf(preSalesHrsDbl);
            
            
            
            Map percentage = new HashMap();          
            percentage.put("competency", competency);
            percentage.put("leaveHours", leaveHours);            
            percentage.put("billedHours", billedHours);            
            percentage.put("trainingHrs", trainingHrs);
            percentage.put("prjMgmtHrs", prjMgmtHrs);
            percentage.put("preSalesHrs", preSalesHrs);
            
            
            //String Category = "'Total Available Hours','Competency', 'Accured Hours','Training Hours','Business Development','Project Management' , 'Leave Hours'";
            
            //chartData.put("category", Category);            
            mv.addObject("chartData",chartData);    
            mv.addObject("percentageValues", percentage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
