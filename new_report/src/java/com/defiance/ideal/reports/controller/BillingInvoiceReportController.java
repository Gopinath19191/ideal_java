/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.BillingInvoiceReportDataDTO;
import com.defiance.ideal.reports.dto.BillingInvoiceReportFilterDTO;
import com.defiance.ideal.reports.service.BillingInvoiceReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16047
 */
public class BillingInvoiceReportController extends MultiActionController {

    public BillingInvoiceReportController() {
    }

    public ModelAndView billingInvoiceReport(HttpServletRequest request, HttpServletResponse response, BillingInvoiceReportFilterDTO formData) throws Exception {
        ModelAndView mvc = new ModelAndView("/billing_invoice_report");
        String selectedValue = "";
        if (formData.getEmployee_id() != null && !"".equals(formData.getEmployee_id())) {
            selectedValue = selectedValue + " AND ba.employee_id = " + formData.getEmployee_id();
        }
        if (formData.getProjectId() != null && !"".equals(formData.getProjectId())) {
            selectedValue = selectedValue + " AND ba.project_id = " + formData.getProjectId();
        }
        if (formData.getCustomerId() != null && !"".equals(formData.getCustomerId())) {
            selectedValue = selectedValue + " AND proj.customer_id = " + formData.getCustomerId();
        }
        if (formData.getProjectSbu() != null && !"".equals(formData.getProjectSbu())) {
            selectedValue = selectedValue + " AND proj.project_sbu = " + formData.getProjectSbu();
        }
        if (formData.getEmployeeSbu() != null && !"".equals(formData.getEmployeeSbu())) {
            selectedValue = selectedValue + " AND ((ba.sbu = " + formData.getEmployeeSbu() + ") OR "
                    + " (ba.sbu IS NULL AND emp.structure_name = " + formData.getEmployeeSbu() + ") OR"
                    + " (ba.sbu IS NULL AND emp.structure_name_subgroup = " + CommonConfigurations.CRMG + " AND empinfo.changed_from IN (select id from company_structures where parent_id = " + formData.getEmployeeSbu() + ")))";
        }
        if (formData.getBillingYear() != null && !"".equals(formData.getBillingYear())) {
            selectedValue = selectedValue + " AND ba.billing_year = " + formData.getBillingYear();
        }
        formData.setBillingMonth(request.getParameterValues("billingMonth"));
        String[] billingMonths = request.getParameterValues("billingMonth");
        String allBillingMonths = "";
        if (billingMonths != null) {
            for (int i = 0; i < billingMonths.length; i++) {
                if (i == 0) {
                    allBillingMonths = allBillingMonths + billingMonths[i];
                } else {
                    allBillingMonths = allBillingMonths + "," + billingMonths[i];
                }

            }
        }
        if (allBillingMonths != null && !"".equals(allBillingMonths)) {
            selectedValue = selectedValue + " AND ba.billing_month IN (" + allBillingMonths + ")";
        }
        if ((formData.getOperator() != null && !"".equals(formData.getOperator())) && (formData.getAccrualSno() != null && !"".equals(formData.getAccrualSno()))) {
            selectedValue = selectedValue + " AND ba.accrual_sno " + formData.getOperator() + " " + formData.getAccrualSno();
        }
//        if("0000-00-00".equals(formData.getAccruedFrom())){
//            formData.setAccruedFrom(null);
//        }
//        if("0000-00-00".equals(formData.getAccruedTo())){
//            formData.setAccruedTo(null);
//        }
        if (formData.getAccruedFrom() != null && !"".equals(formData.getAccruedFrom())) {
            String[] fromDateArr = formData.getAccruedFrom().split("-");
            String fromDate = fromDateArr[2] + "-" + fromDateArr[1] + "-" + fromDateArr[0];
            selectedValue = selectedValue + " AND ba.submitted_on >= '" + fromDate + "'";
        }
        if (formData.getAccruedTo() != null && !"".equals(formData.getAccruedTo())) {
            String[] toDateArr = formData.getAccruedTo().split("-");
            String toDate = toDateArr[2] + "-" + toDateArr[1] + "-" + toDateArr[0];
            selectedValue = selectedValue + " AND ba.submitted_on <= '" + toDate + "'";
        }
        formData.setSelectedValue(selectedValue);
        int page;
        int india_count = 0;
        if (request.getParameter("go") != null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int rows = CommonConfigurations.pageCount;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);
        int recordCount = 0;
        final WebApplicationContext ctx = getWebApplicationContext();
        BillingInvoiceReportServiceImpl billingInvoiceReportServiceImpl = (BillingInvoiceReportServiceImpl) ctx.getBean("Billing_Invoice_Report_Service");
        Map<String, String> prjSbuMap = billingInvoiceReportServiceImpl.getPrjSbuList();
        Map<String, String> empSbuMap = billingInvoiceReportServiceImpl.getEmpSbuList();
        List<BillingInvoiceReportDataDTO> bilingYearList = billingInvoiceReportServiceImpl.getBillingYearList();
        List<BillingInvoiceReportDataDTO> bilingMonthList = billingInvoiceReportServiceImpl.getBillingMonthList();
        List<BillingInvoiceReportDataDTO> bilingAdviceFilterList = billingInvoiceReportServiceImpl.getBilingAdviceFilterList();
        List<BillingInvoiceReportDataDTO> accrualSnoList = billingInvoiceReportServiceImpl.getAccrualSnoList();
        List<BillingInvoiceReportDataDTO> billingInvoiceList = null;
        System.out.println("Selected Value : " + selectedValue);
        String button = request.getParameter("go");
        String flag = null;
        flag = request.getParameter("flag");
        if (!"1".equals(flag)) {
                 
        billingInvoiceList = (List<BillingInvoiceReportDataDTO>) billingInvoiceReportServiceImpl.getBillingInvoiceList(formData);
      
        india_count = billingInvoiceReportServiceImpl.getBillingInvoiceListCount(formData);
        }
        System.out.println("india_count" + india_count);
        recordCount = india_count;
        int end = (start + rows) - 1;
        int[] paging = CommonMethods.paging(recordCount, page, rows);
        String empName = billingInvoiceReportServiceImpl.getEmployeeName(formData.getEmployee_id());
        String projectName = billingInvoiceReportServiceImpl.getProjectName(formData.getProjectId());
        String customerName = billingInvoiceReportServiceImpl.getCustomerName(formData.getCustomerId());
        mvc.addObject("paging", paging);
        mvc.addObject("empName", empName);
        mvc.addObject("projectName", projectName);
        mvc.addObject("customerName", customerName);
        mvc.addObject("billingInvoiceList", billingInvoiceList);
        mvc.addObject("prjSbu", prjSbuMap);
        mvc.addObject("empSbu", empSbuMap);
        mvc.addObject("yearList", bilingYearList);
        mvc.addObject("monthList", bilingMonthList);
        mvc.addObject("operatorsList", bilingAdviceFilterList);
        mvc.addObject("accrualSnoList", accrualSnoList);
        mvc.addObject("filterData", formData);
        return mvc;
    }

    public ModelAndView billingInvoiceReportExport(HttpServletRequest request, HttpServletResponse response, BillingInvoiceReportFilterDTO formData) throws Exception {
        ModelAndView mvc = null;
        try {
            String selectedValue = "";
            if (formData.getEmployee_id() != null && !"".equals(formData.getEmployee_id())) {
                selectedValue = selectedValue + " AND ba.employee_id = " + formData.getEmployee_id();
            }
            if (formData.getProjectId() != null && !"".equals(formData.getProjectId())) {
                selectedValue = selectedValue + " AND ba.project_id = " + formData.getProjectId();
            }
            if (formData.getCustomerId() != null && !"".equals(formData.getCustomerId())) {
                selectedValue = selectedValue + " AND proj.customer_id = " + formData.getCustomerId();
            }
            if (formData.getProjectSbu() != null && !"".equals(formData.getProjectSbu())) {
                selectedValue = selectedValue + " AND proj.project_sbu = " + formData.getProjectSbu();
            }
            if (formData.getEmployeeSbu() != null && !"".equals(formData.getEmployeeSbu())) {
                selectedValue = selectedValue + " AND ((ba.sbu = " + formData.getEmployeeSbu() + ") OR "
                        + " (ba.sbu IS NULL AND emp.structure_name = " + formData.getEmployeeSbu() + ") OR"
                        + " (ba.sbu IS NULL AND emp.structure_name_subgroup = " + CommonConfigurations.CRMG + " AND empinfo.changed_from IN (select id from company_structures where parent_id = " + formData.getEmployeeSbu() + ")))";
            }
            if (formData.getBillingYear() != null && !"".equals(formData.getBillingYear())) {
                selectedValue = selectedValue + " AND ba.billing_year = " + formData.getBillingYear();
            }
            formData.setBillingMonth(request.getParameterValues("billingMonth"));
            String[] billingMonths = request.getParameterValues("billingMonth");
            String allBillingMonths = "";
            if (billingMonths != null) {
                for (int i = 0; i < billingMonths.length; i++) {
                    if (i == 0) {
                        allBillingMonths = allBillingMonths + billingMonths[i];
                    } else {
                        allBillingMonths = allBillingMonths + "," + billingMonths[i];
                    }

                }
            }
            if (allBillingMonths != null && !"".equals(allBillingMonths)) {
                selectedValue = selectedValue + " AND ba.billing_month IN (" + allBillingMonths + ")";
            }
            if ((formData.getOperator() != null && !"".equals(formData.getOperator())) && (formData.getAccrualSno() != null && !"".equals(formData.getAccrualSno()))) {
                selectedValue = selectedValue + " AND ba.accrual_sno " + formData.getOperator() + " " + formData.getAccrualSno();
            }
//        if("0000-00-00".equals(formData.getAccruedFrom())){
//            formData.setAccruedFrom(null);
//        }
//        if("0000-00-00".equals(formData.getAccruedTo())){
//            formData.setAccruedTo(null);
//        }
            if (formData.getAccruedFrom() != null && !"".equals(formData.getAccruedFrom())) {
                String[] fromDateArr = formData.getAccruedFrom().split("-");
                String fromDate = fromDateArr[2] + "-" + fromDateArr[1] + "-" + fromDateArr[0];
                selectedValue = selectedValue + " AND ba.submitted_on >= '" + fromDate + "'";
            }
            if (formData.getAccruedTo() != null && !"".equals(formData.getAccruedTo())) {
                String[] toDateArr = formData.getAccruedTo().split("-");
                String toDate = toDateArr[2] + "-" + toDateArr[1] + "-" + toDateArr[0];
                selectedValue = selectedValue + " AND ba.submitted_on <= '" + toDate + "'";
            }
            formData.setSelectedValue(selectedValue);
            final WebApplicationContext ctx = getWebApplicationContext();
            BillingInvoiceReportServiceImpl billingInvoiceReportServiceImpl = (BillingInvoiceReportServiceImpl) ctx.getBean("Billing_Invoice_Report_Service");

            List<BillingInvoiceReportDataDTO> billingInvoiceList = null;
            System.out.println("Selected Value : " + selectedValue);

            billingInvoiceList = (List<BillingInvoiceReportDataDTO>) billingInvoiceReportServiceImpl.getBillingInvoiceList(formData);

            ArrayList entireList = new ArrayList();
            for (int i = 0; i < billingInvoiceList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("~~"  + billingInvoiceList.get(i).getAccrualSno()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getSubmittedOn()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getInvoiceRefNo()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getSubmittedOn()));
                rowDataList.add(new String("~~" + billingInvoiceList.get(i).getProjectCode()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getProjectName()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getPricing()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getCustomerName()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getRbu()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getProjectSbu()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getProjectSubSbu()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getEmployeeNumber()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getEmployeeName()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getEmployeeSbu()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getEmployeeSubSbu()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getRole()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getWorkingPlace()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getProjectManager()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getSalesPerson()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getBillingMonth()));
                rowDataList.add(new String("~~" + billingInvoiceList.get(i).getBillingYear()));
                rowDataList.add(new String("~~" + billingInvoiceList.get(i).getBillableEffort()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getEffortsUom()));
                rowDataList.add(new String("~~" + billingInvoiceList.get(i).getBillingRate()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getCurrency()));
                rowDataList.add(new String("~~" + billingInvoiceList.get(i).getBillableAmount()));
                rowDataList.add(new String("" + billingInvoiceList.get(i).getInvoiceDescription()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "billing_invoice_report.xls", "Billing Invoice", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView baEmpAjaxsearch(HttpServletRequest request, HttpServletResponse response, BillingInvoiceReportFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/baempajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            BillingInvoiceReportServiceImpl billingInvoiceReportService = (BillingInvoiceReportServiceImpl) ctx.getBean("Billing_Invoice_Report_Service");
            String empVal = request.getParameter("q");
            List<BillingInvoiceReportFilterDTO> empRes = billingInvoiceReportService.getSearchList(empVal);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView projectAjaxsearch(HttpServletRequest request, HttpServletResponse response, BillingInvoiceReportFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            BillingInvoiceReportServiceImpl billingInvoiceReportService = (BillingInvoiceReportServiceImpl) ctx.getBean("Billing_Invoice_Report_Service");
            String prjVal = request.getParameter("q");
            List<BillingInvoiceReportFilterDTO> prjRes = billingInvoiceReportService.getProjectSearchList(prjVal);
            mvc.addObject("prjRes", prjRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView customerAjaxsearch(HttpServletRequest request, HttpServletResponse response, BillingInvoiceReportFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/customerajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            BillingInvoiceReportServiceImpl billingInvoiceReportService = (BillingInvoiceReportServiceImpl) ctx.getBean("Billing_Invoice_Report_Service");
            String custVal = request.getParameter("q");
            List<BillingInvoiceReportFilterDTO> custRes = billingInvoiceReportService.getCustomerSearchList(custVal);
            mvc.addObject("custRes", custRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }
}
