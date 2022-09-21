/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.CustagingReportDataDTO;
import com.defiance.ideal.reports.dto.CustagingReportFilterDTO;
import com.defiance.ideal.reports.service.CustagingReportService;
import com.defiance.ideal.reports.service.CustagingReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14517
 */
public class CustagingReportController extends MultiActionController {

    public CustagingReportController() {
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    public ModelAndView CustagingReport(HttpServletRequest request, HttpServletResponse response, CustagingReportFilterDTO filterData) {
        System.out.println("Custaging report");
        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        String PES = null;
        String TS = null;
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            mvc = new ModelAndView("/CustagingReport");
            HttpSession session = request.getSession();
            access = (String) session.getAttribute("DebtorAccess");
            System.out.println("access---" + access);
            employee_id = (String) session.getAttribute("EMP_ID");
            final WebApplicationContext ctx = getWebApplicationContext();
            CustagingReportService projectService = (CustagingReportServiceImpl) ctx.getBean("CustagingReportService");
            if (filterData.getStatusFilter() == null) {
                filterData.setStatusFilter("e");
            }
            List<CustagingReportDataDTO> custagingReport = null;
            List<CustagingReportDataDTO> bdmName = null;
            List<CustagingReportDataDTO> bdm_Name_List = null;
            List<CustagingReportDataDTO> customerName = null;
            List<CustagingReportDataDTO> businessLeader = null;
            List<CustagingReportDataDTO> legalEntity = null;
            List<CustagingReportDataDTO> ReportDates = null;
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String maxDateOfReport = projectService.maxDateOfReport();
            HashMap<String, String> test = projectService.fetchExchangeRate(filterData);
            System.out.println("Access" + access);
            if (access.equals("BUSINESS_LEADER")) {
                System.out.println("inside BUSINESS_LEADER ");
                filterData.setBusiness_leader_id(employee_id);
                custagingReport = projectService.fetchCustagingReport(filterData);
                //bdmName = projectService.fetchBdmName(filterData);
                List<CustagingReportDataDTO> bdmList = new ArrayList();
                bdmList = projectService.fetchBdmId(filterData);

                String bdmNameList = "";
                for (int k = 0; k < 1; k++) {
                    bdmNameList = bdmList.get(k).getBdm_name();
                }
                String bdm = "";
                if (!bdmNameList.isEmpty()) {
                    String nameList[];
                    nameList = bdmNameList.split(",");

                    for (int j = 0; j < nameList.length; j++) {

                        bdm = bdm.concat(nameList[j]);
                        if (j < nameList.length - 1) {
                            bdm = bdm + ",";
                        }
                    }
                    filterData.setBdm_Name_List(bdm);
                    bdmName = projectService.fetchBdmNameList(filterData);
                }
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("BDM")) {
                System.out.println("inside BDM");
                filterData.setBdm_id(employee_id);
                custagingReport = projectService.fetchCustagingReport(filterData);
                customerName = projectService.fetchCustomerName(filterData);
               

            } else if (access.equals("FULL")) {
                if ((filterData.getCustomer_id() != null) || (filterData.getLegal_entity() != null) || (filterData.getBusiness_leader_id() != null) || (filterData.getBdm_id() != null) || (filterData.getReportDate() != null)) {
                    custagingReport = projectService.fetchCustagingReport(filterData);
                } else {
                    custagingReport = projectService.fetchCustagingReport(filterData);
                }

                List<CustagingReportDataDTO> bdmList = new ArrayList();
                bdmList = projectService.fetchBdmId(filterData);

                String bdmNameList = "";
                for (int k = 0; k < 1; k++) {
                    bdmNameList = bdmList.get(k).getBdm_name();
                }
                String bdm = "";
                if (!bdmNameList.isEmpty()) {
                    String nameList[];
                    nameList = bdmNameList.split(",");

                    for (int j = 0; j < nameList.length; j++) {

                        bdm = bdm.concat(nameList[j]);
                        if (j < nameList.length - 1) {
                            bdm = bdm + ",";
                        }
                    }
                    filterData.setBdm_Name_List(bdm);
                    bdmName = projectService.fetchBdmNameList(filterData);
                }


                customerName = projectService.fetchCustomerName(filterData);
                businessLeader = projectService.fetchBusinessLeader(filterData);
            } else {
                mvc = new ModelAndView("/unauthorised");
            }
            ReportDates = projectService.fetchReportDates(filterData);
            legalEntity = projectService.fetchLegalEntity(filterData);

            mvc.addObject("filterData", filterData);
            mvc.addObject("custagingReport", custagingReport);
            mvc.addObject("legal_entities", legalEntity);
            mvc.addObject("business_leader", businessLeader);
            mvc.addObject("bdm_name", bdmName);
            mvc.addObject("customer_name", customerName);
            mvc.addObject("test", test);
            mvc.addObject("selectOperator", request.getParameter("operators"));
            mvc.addObject("overDue_aging", request.getParameter("overDue_aging"));
            mvc.addObject("ReportDates", ReportDates);
            mvc.addObject("maxDateOfReport", inputFormat.format(outputFormat.parse(maxDateOfReport)));
            if (request.getParameter("reportDate") != null) {
                mvc.addObject("systemDate", request.getParameter("reportDate"));
            } else {
                mvc.addObject("systemDate", inputFormat.format(outputFormat.parse(maxDateOfReport)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("access", access);
        mvc.addObject("employee_id", employee_id);
        mvc.addObject("PES", PES);
        mvc.addObject("TS", TS);
        return mvc;

    }

    public ModelAndView CustagingReportXL(HttpServletRequest request, HttpServletResponse response, CustagingReportFilterDTO filterData) throws Exception {

        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {           
            mvc = new ModelAndView("/CustagingReport");
            HttpSession session = request.getSession();
            access = (String) session.getAttribute("DebtorAccess");
            employee_id = (String) session.getAttribute("EMP_ID");
            final WebApplicationContext ctx = getWebApplicationContext();
            CustagingReportService projectService = (CustagingReportServiceImpl) ctx.getBean("CustagingReportService");
            if (filterData.getStatusFilter() == null) {
                filterData.setStatusFilter("e");
            }
            List<CustagingReportDataDTO> custagingReport = null;
            List<CustagingReportDataDTO> bdmName = null;
            List<CustagingReportDataDTO> customerName = null;
            List<CustagingReportDataDTO> businessLeader = null;
            List<CustagingReportDataDTO> legalEntity = null;
            HashMap<String, String> test = projectService.fetchExchangeRate(filterData);
            if (access.equals("BUSINESS_LEADER")) {
                filterData.setBusiness_leader_id(employee_id);
                custagingReport = projectService.fetchCustagingReport(filterData);
                bdmName = projectService.fetchBdmName(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("BDM")) {
                filterData.setBdm_id(employee_id);
                custagingReport = projectService.fetchCustagingReport(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("FULL")) {
                if ((filterData.getCustomer_id() != null) || (filterData.getLegal_entity() != null) || (filterData.getBusiness_leader_id() != null) || (filterData.getBdm_id() != null) || (filterData.getReportDate() != null)) {
                    custagingReport = projectService.fetchCustagingReport(filterData);
                }
                bdmName = projectService.fetchBdmName(filterData);
                customerName = projectService.fetchCustomerName(filterData);
                businessLeader = projectService.fetchBusinessLeader(filterData);
            } else {
                mvc = new ModelAndView("/unauthorised");
            }

            ArrayList entireList = new ArrayList();
            Float finalresult = null;


            for (int i = 0; i < custagingReport.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                if (access.equals("BUSINESS_LEADER")) {
                    rowDataList.add(new String("" + custagingReport.get(i).getCust_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getBdm()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_code()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getClient_manager()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_email_id()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_contact_number()));
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoice_number()));
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date());
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getCredit_due_date());
                    rowDataList.add(new String("#####" + custagingReport.get(i).getCredit_period()));

                    if (custagingReport.get(i).getInvoice_date_submission_customer() != null && !custagingReport.get(i).getInvoice_date_submission_customer().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date_submission_customer());
                    } else {
                        rowDataList.add(new String(""));
                    }

                    if (custagingReport.get(i).getExpected_collection_date() != null && !custagingReport.get(i).getExpected_collection_date().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getExpected_collection_date());
                    } else {
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoicing_currency()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getAmount_in_ic()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_IC()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    rowDataList.add("#####" + custagingReport.get(i).getDays_past_due());
                    Integer getPastDue = Integer.parseInt(custagingReport.get(i).getDays_past_due());
                    if (getPastDue == 0) {
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 0 && getPastDue < 31) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 30 && getPastDue < 61) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 60 && getPastDue < 91) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 90 && getPastDue < 181) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 180) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    } else {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getLegal_entity()));

                } else if (access.equals("BDM")) {
                    rowDataList.add(new String("" + custagingReport.get(i).getCust_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_code()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getClient_manager()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_email_id()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_contact_number()));
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoice_number()));
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date());
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getCredit_due_date());
                    rowDataList.add(new String("#####" + custagingReport.get(i).getCredit_period()));
                    if (custagingReport.get(i).getInvoice_date_submission_customer() != null && !custagingReport.get(i).getInvoice_date_submission_customer().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date_submission_customer());
                    } else {
                        rowDataList.add(new String(""));
                    }
                    if (custagingReport.get(i).getExpected_collection_date() != null && !custagingReport.get(i).getExpected_collection_date().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getExpected_collection_date());
                    } else {
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoicing_currency()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getAmount_in_ic()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_IC()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    rowDataList.add("#####" + custagingReport.get(i).getDays_past_due());
                    Integer getPastDue = Integer.parseInt(custagingReport.get(i).getDays_past_due());
                    if (getPastDue == 0) {
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 0 && getPastDue < 31) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 30 && getPastDue < 61) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 60 && getPastDue < 91) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 90 && getPastDue < 181) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 180) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    } else {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getBusiness_leader()));
                    rowDataList.add(new String("" + custagingReport.get(i).getLegal_entity()));

                } else if (access.equals("FULL")) {
                    rowDataList.add(new String("" + custagingReport.get(i).getCust_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getBusiness_leader()));
                    rowDataList.add(new String("" + custagingReport.get(i).getBdm()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_code()));
                    rowDataList.add(new String("" + custagingReport.get(i).getProject_name()));
                    rowDataList.add(new String("" + custagingReport.get(i).getClient_manager()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_email_id()));
                    rowDataList.add(new String("" + custagingReport.get(i).getCustomer_contact_number()));
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoice_number()));
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date());
                    rowDataList.add("@@@@@@" + custagingReport.get(i).getCredit_due_date());
                    rowDataList.add(new String("#####" + custagingReport.get(i).getCredit_period()));

                    if (custagingReport.get(i).getInvoice_date_submission_customer() != null && !custagingReport.get(i).getInvoice_date_submission_customer().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getInvoice_date_submission_customer());
                    } else {
                        rowDataList.add(new String(""));
                    }

                    if (custagingReport.get(i).getExpected_collection_date() != null && !custagingReport.get(i).getExpected_collection_date().equals("0000-00-00")) {
                        rowDataList.add("@@@@@@" + custagingReport.get(i).getExpected_collection_date());
                    } else {
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getInvoicing_currency()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getAmount_in_ic()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_IC()));
                    rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    rowDataList.add("#####" + custagingReport.get(i).getDays_past_due());
                    Integer getPastDue = Integer.parseInt(custagingReport.get(i).getDays_past_due());
                    if (getPastDue == 0) {
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 0 && getPastDue < 31) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 30 && getPastDue < 61) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 60 && getPastDue < 91) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 90 && getPastDue < 181) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                        rowDataList.add(new String(""));
                    } else if (getPastDue > 180) {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String("~~" + custagingReport.get(i).getBalance_in_INR()));
                    } else {
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                        rowDataList.add(new String(""));
                    }
                    rowDataList.add(new String("" + custagingReport.get(i).getLegal_entity()));

                }
                entireList.add(rowDataList);

            }
            if (access.equals("BUSINESS_LEADER")) {
                CommonMethods.exportToExcel(response, entireList, "CustagingReport_BL", "CustagingReport_BL", "");
            } else if (access.equals("BDM")) {
                CommonMethods.exportToExcel(response, entireList, "CustagingReport_BDM", "CustagingReport_BDM", "");
            } else if (access.equals("FULL")) {
                CommonMethods.exportToExcel(response, entireList, "CustagingReport_Finance", "CustagingReport_Finance", "");
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mvc;
    }

    public ModelAndView getCustomerList(HttpServletRequest request, HttpServletResponse response, CustagingReportFilterDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustagingReportService projectService = (CustagingReportServiceImpl) ctx.getBean("CustagingReportService");
        if (request.getParameter("business_leader_id") != null && !request.getParameter("business_leader_id").equals("")) {
            filterData.setBusiness_leader_id(request.getParameter("business_leader_id"));
        }
        if (request.getParameter("bdm_id") != null && !request.getParameter("bdm_id").equals("")) {
            filterData.setBdm_id(request.getParameter("bdm_id"));
        }else{
            String access = null;
            HttpSession session = request.getSession();
            access = (String) session.getAttribute("DebtorAccess");
            if (access.equals("BDM")) {
                filterData.setBdm_id((String)session.getAttribute("EMP_ID"));
            }
            
        }
        if (request.getParameter("reportDate") != null && !request.getParameter("reportDate").equals("")) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            filterData.setReportDate(outputFormat.format(inputFormat.parse(request.getParameter("reportDate"))));
        }
        List<CustagingReportDataDTO> customerName = projectService.fetchCustomerName(filterData);
        ModelAndView mv = new ModelAndView("/getCustomerList");
        mv.addObject("customerList", customerName);
        return mv;
    }

    public ModelAndView getBdmList(HttpServletRequest request, HttpServletResponse response, CustagingReportFilterDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustagingReportService projectService = (CustagingReportServiceImpl) ctx.getBean("CustagingReportService");
        if (request.getParameter("business_leader_id") != null && !request.getParameter("business_leader_id").equals("")) {
            filterData.setBusiness_leader_id(request.getParameter("business_leader_id"));
        }
       if (request.getParameter("reportDate") != null && !request.getParameter("reportDate").equals("")) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            filterData.setReportDate(outputFormat.format(inputFormat.parse(request.getParameter("reportDate"))));
        }
        List<CustagingReportDataDTO> bdmName = null;
//        if (filterData.getBusiness_leader_id() == null || "".equals(filterData.getBusiness_leader_id())) {
        List<CustagingReportDataDTO> bdmList = new ArrayList();
        bdmList = projectService.fetchBdmId(filterData);

        String bdmNameList = "";
        for (int k = 0; k < 1; k++) {
            bdmNameList = bdmList.get(k).getBdm_name();
        }
        String bdm = "";
        if (!bdmNameList.isEmpty()) {
            String nameList[];
            nameList = bdmNameList.split(",");

            for (int j = 0; j < nameList.length; j++) {

                bdm = bdm.concat(nameList[j]);
                if (j < nameList.length - 1) {
                    bdm = bdm + ",";
                }
            }
            filterData.setBdm_Name_List(bdm);
            bdmName = projectService.fetchBdmNameList(filterData);
            System.out.println("new----------->");
        }

        bdmName = projectService.fetchBdmNameList(filterData);
        System.out.println("old----------->");


        ModelAndView mv = new ModelAndView("/getBdmList");
        mv.addObject("bdmList", bdmName);
        return mv;
    }

    public ModelAndView updateInvoiceDates(HttpServletRequest request, HttpServletResponse response, CustagingReportFilterDTO filterData) throws Exception {

        System.out.println("filterData=====>" + filterData.getInvoice_number());
        System.out.println("filterData=====>" + filterData.getDate_of_report());


        String[] custagingCheckList = request.getParameterValues("custagingCheckList");
        filterData.setCustagingCheckList(custagingCheckList);
        System.out.println("filterData      =====>" + custagingCheckList);
        for (int i = 0; i < filterData.getCustagingCheckList().length; i++) {
            System.out.println("check------------------->" + filterData.getCustagingCheckList()[i]);
        }
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustagingReportService custageReportService = (CustagingReportServiceImpl) ctx.getBean("CustagingReportService");
        filterData.setEmpId(employee_id);
        filterData.setHistoryUpdatedDate(new Date());
        custageReportService.updateInvoiceDates(filterData);

        ModelAndView mv = CustagingReport(request, response, filterData);

        return mv;
    }
}
