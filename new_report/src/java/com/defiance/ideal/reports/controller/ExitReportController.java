/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ExitReportDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.ExitReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14517
 */
public class ExitReportController extends MultiActionController {

    public ExitReportController() {
    }

    public ModelAndView exitReport(HttpServletRequest request, HttpServletResponse response, ExitReportDTO dto) {
        ModelAndView mvc = new ModelAndView("exitReport");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String exit_trigger_date = request.getParameter("exit_triggerDate");
        if (exit_trigger_date == null) {
            exit_trigger_date = sdf.format(new Date());
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        ExitReportServiceImpl bo = (ExitReportServiceImpl) ctx.getBean("ExitReportService");
        List<ExitReportDTO> getExitReport = bo.getExitreport(exit_trigger_date);
        mvc.addObject("exitReport", getExitReport);
        if (exit_trigger_date == null) {
            mvc.addObject("exitDate", sdf.format(new Date()));
        } else {
            mvc.addObject("exitDate", exit_trigger_date);
        }
        return mvc;
    }

    public ModelAndView exportExitReport(HttpServletRequest request, HttpServletResponse response, ExitReportDTO fomDto) throws Exception {
        ModelAndView mvc = new ModelAndView("exitReport");
        try {
            ArrayList entireList = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String exit_trigger_date = request.getParameter("exit_triggerDate");
            if (exit_trigger_date == null) {
                exit_trigger_date = sdf.format(new Date());
            }
            final WebApplicationContext ctx = getWebApplicationContext();
            ExitReportServiceImpl bo = (ExitReportServiceImpl) ctx.getBean("ExitReportService");
            List<ExitReportDTO> getExitReport = bo.getExitreport(exit_trigger_date);
            for (int i = 0; i < getExitReport.size(); i++) {
                ArrayList rowData = new ArrayList();
                rowData.add(new String("" + getExitReport.get(i).getEmployeeNumber() + " - " + getExitReport.get(i).getEmployeeName()));
                rowData.add(new String("" + getExitReport.get(i).getContactNumber()));
                rowData.add(new String("" + getExitReport.get(i).getEmailId()));
                rowData.add(new String("" + getExitReport.get(i).getRmNumber() + " - " + getExitReport.get(i).getRmName()));
                rowData.add(new String("" + getExitReport.get(i).getLastBilledDate()));
                rowData.add(new String("" + getExitReport.get(i).getCustomerName()));
                rowData.add(new String("" + getExitReport.get(i).getResignedDate()));
                rowData.add(new String("" + getExitReport.get(i).getLastWorkingDate()));
                rowData.add(new String("" + getExitReport.get(i).getPracticeGroup()));
                rowData.add(new String("" + getExitReport.get(i).getBandName()));
                rowData.add(new String("" + getExitReport.get(i).getSubBandName()));
                rowData.add(new String("" + getExitReport.get(i).getExitStatus()));
                rowData.add(new String("" + getExitReport.get(i).getLeavingReason()));
                rowData.add(new String("" + getExitReport.get(i).getLeaveBalance()));
                rowData.add(new String("" + getExitReport.get(i).getBalanceNoticePeriod()));
                rowData.add(new String("" + getExitReport.get(i).getNoticeWaiver()));
                rowData.add(new String("" + getExitReport.get(i).getRmCleared()));
                rowData.add(new String("" + getExitReport.get(i).getAdminCleared()));
                rowData.add(new String("" + getExitReport.get(i).getNsCleared()));
                rowData.add(new String("" + getExitReport.get(i).getFinanceCleared()));
                rowData.add(new String("" + getExitReport.get(i).getExitSurveyClearance()));
                
                
                entireList.add(rowData);
            }
            CommonMethods.exportToExcel(response, entireList, "ExitReport", "ExitReport", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
}
