/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.CollectionReportDataDTO;
import com.defiance.ideal.reports.dto.CollectionReportFilterDTO;
import com.defiance.ideal.reports.dto.ReimbursementsData;
import com.defiance.ideal.reports.service.CollectionReportService;
import com.defiance.ideal.reports.service.CollectionReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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
 * @author 14517
 */
public class CollectionReportController extends MultiActionController {

    public CollectionReportController() {
    }

    public ModelAndView CollectionReport(HttpServletRequest request, HttpServletResponse response, CollectionReportFilterDTO filterData) {
        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        try {
            mvc = new ModelAndView("/CollectionReport");
            HttpSession session = request.getSession();
            access = (String) session.getAttribute("DebtorAccess");
            employee_id = (String) session.getAttribute("EMP_ID");
            final WebApplicationContext ctx = getWebApplicationContext();
            CollectionReportService projectService = (CollectionReportServiceImpl) ctx.getBean("CollectionReportService");
            if (filterData.getStatusFilter() == null) {
                filterData.setStatusFilter("e");
            }
            List<CollectionReportDataDTO> collectionReport = null;
            List<CollectionReportDataDTO> bdmName = null;
            List<CollectionReportDataDTO> customerName = null;
            List<CollectionReportDataDTO> businessLeader = null;
            List<CollectionReportDataDTO> legalEntity = null;
            if (access.equals("BUSINESS_LEADER")) {
                filterData.setBusiness_leader_id(employee_id);
                collectionReport = projectService.fetchCollectionReport(filterData);
                bdmName = projectService.fetchBdmName(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("BDM")) {
                filterData.setBdm_id(employee_id);
                collectionReport = projectService.fetchCollectionReport(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("FULL")) {
                if ((filterData.getCustomer_id() != null) || (filterData.getLegal_entity() != null) || (filterData.getBusiness_leader_id() != null) || (filterData.getBdm_id() != null)) {
                    collectionReport = projectService.fetchCollectionReport(filterData);
                }
                if (filterData.getBusiness_leader_id() == null) {
                    List<CollectionReportDataDTO> bdmList = new ArrayList();
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
                } else {
                    bdmName = projectService.fetchBdmName(filterData);
                }
                customerName = projectService.fetchCustomerName(filterData);
                businessLeader = projectService.fetchBusinessLeader(filterData);
            } else {
                mvc = new ModelAndView("/unauthorised");
            }
            legalEntity = projectService.fetchLegalEntity(filterData);
            mvc.addObject("filterData", filterData);
            mvc.addObject("collectionReport", collectionReport);
            mvc.addObject("legal_entities", legalEntity);
            mvc.addObject("business_leader", businessLeader);
            mvc.addObject("bdm_name", bdmName);
            mvc.addObject("customer_name", customerName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("access", access);
        mvc.addObject("employee_id", employee_id);
        return mvc;

    }

    public ModelAndView CollectionReportXL(HttpServletRequest request, HttpServletResponse response, CollectionReportFilterDTO filterData) throws Exception {
        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        try {
            mvc = new ModelAndView("/CollectionReport");
            HttpSession session = request.getSession();
            access = (String) session.getAttribute("DebtorAccess");
            employee_id = (String) session.getAttribute("EMP_ID");
            final WebApplicationContext ctx = getWebApplicationContext();
            CollectionReportService projectService = (CollectionReportServiceImpl) ctx.getBean("CollectionReportService");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (filterData.getStatusFilter() == null) {
                filterData.setStatusFilter("e");
            }
            List<CollectionReportDataDTO> collectionReport = null;
            List<CollectionReportDataDTO> bdmName = null;
            List<CollectionReportDataDTO> customerName = null;
            List<CollectionReportDataDTO> businessLeader = null;
            List<CollectionReportDataDTO> legalEntity = null;
            if (access.equals("BUSINESS_LEADER")) {

                filterData.setBusiness_leader_id(employee_id);
                collectionReport = projectService.fetchCollectionReport(filterData);
                bdmName = projectService.fetchBdmName(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("BDM")) {

                filterData.setBdm_id(employee_id);

                collectionReport = projectService.fetchCollectionReport(filterData);
                customerName = projectService.fetchCustomerName(filterData);
            } else if (access.equals("FULL")) {
                if ((filterData.getCustomer_id() != null) || (filterData.getLegal_entity() != null) || (filterData.getBusiness_leader_id() != null) || (filterData.getBdm_id() != null)) {
                    collectionReport = projectService.fetchCollectionReport(filterData);
                }
                bdmName = projectService.fetchBdmName(filterData);
                customerName = projectService.fetchCustomerName(filterData);
                businessLeader = projectService.fetchBusinessLeader(filterData);
            } else {
                mvc = new ModelAndView("/unauthorised");
            }
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < collectionReport.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + collectionReport.get(i).getCust_name()));
                rowDataList.add(new String("" + collectionReport.get(i).getLegal_entity()));
//           
//            rowDataList.add(new String("" + projectList.get(i).getRbu()));
//            rowDataList.add(new String("" + projectList.get(i).getSubRBU()));
                rowDataList.add(new String("" + collectionReport.get(i).getBusiness_leader()));
                rowDataList.add(new String("" + collectionReport.get(i).getBdm()));
//            rowDataList.add(new String("" + projectList.get(i).getCustomer_contact_phone()));
                rowDataList.add(new String("" + collectionReport.get(i).getProject_code()));

                rowDataList.add(new String("" + collectionReport.get(i).getInvoice_number()));
                //rowDataList.add(new String("@@@@@@" + collectionReport.get(i).getInvoice_date()));
                rowDataList.add("@@@@@@" + collectionReport.get(i).getInvoice_date());
                //rowDataList.add(new String("" + projectList.get(i).getCredit_period()));
                // rowDataList.add(new String("" + collectionReport.get(i).getCredit_due_date()));
                rowDataList.add("@@@@@@" + collectionReport.get(i).getCredit_due_date());
                /* if(collectionReport.get(i).getInvoice_date_submission_customer()!=null && collectionReport.get(i).getInvoice_date_submission_customer().equals("0000-00-00")){
                 rowDataList.add("@@@@@@" + sdf.format(collectionReport.get(i).getInvoice_date_submission_customer()));
                 }else{
                 rowDataList.add(new String(""));
                 }
                 if(collectionReport.get(i).getExpected_collection_date()!=null && collectionReport.get(i).getExpected_collection_date().equals("0000-00-00")){
                 rowDataList.add("@@@@@@" + sdf.format(collectionReport.get(i).getExpected_collection_date()));
                 }else{
                 rowDataList.add(new String(""));
                 }*/
                rowDataList.add(new String("" + collectionReport.get(i).getInvoicing_currency()));


                rowDataList.add(new Float("" + collectionReport.get(i).getAmount_in_ic()));
//            rowDataList.add(new String("" + projectList.get(i).getLocal_currency_value()));
//            rowDataList.add(new Float("" + projectList.get(i).getAmount_in_lc()));
//            rowDataList.add(new Float("" + projectList.get(i).getAmount_collected()));
                rowDataList.add(new Float("" + collectionReport.get(i).getTds()));
                rowDataList.add(new Float("" + collectionReport.get(i).getOther_charges()));
                rowDataList.add("@@@@@@" + collectionReport.get(i).getCollection_date());
                entireList.add(rowDataList);
            }
            Calendar cal = Calendar.getInstance();
            CommonMethods.exportToExcel(response, entireList, "CollectionReport", "CollectionReport", "");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mvc;
    }
    public ModelAndView invoiceAgingReport(HttpServletRequest request, HttpServletResponse response, CollectionReportFilterDTO filterData) throws Exception {
        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        try{
            mvc = new ModelAndView("/invoice_approval_aginig_report");
            Map<String,String> monthList = CommonMethods.getNewMonthsList();
            Map<Integer,Integer> yearList = CommonMethods.getYearsList(3);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");  
            Date date = new Date();  
            String formated_date = formatter.format(date);
            String datearr[] = formated_date.split("-");
            filterData.setMonth(datearr[1]);
            filterData.setYear(datearr[2]);
            mvc.addObject("monthList", monthList);
            mvc.addObject("yearList", yearList);
            mvc.addObject("filterData", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    public ModelAndView invoiceAgingReportXL(HttpServletRequest request, HttpServletResponse response, CollectionReportFilterDTO filterData) throws Exception {
        ModelAndView mvc = null;
        String access = null;
        String employee_id = null;
        try{
            mvc = new ModelAndView("/invoice_approval_aginig_report");
            final WebApplicationContext ctx = getWebApplicationContext();
            CollectionReportService collectionService = (CollectionReportServiceImpl) ctx.getBean("CollectionReportService");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            System.out.println("her it comes "+month+"  " +year);
            filterData.setMonth(month);
            filterData.setYear(year);
            List<CollectionReportFilterDTO> invoiceList = collectionService.getInvoiceDetails(filterData);
            CollectionReportFilterDTO invoiceAverage = collectionService.getInvoiceAverage(filterData);
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < invoiceList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + invoiceList.get(i).getInvoice_code()));
                rowDataList.add(new String("" + invoiceList.get(i).getInvoice_date()));
                rowDataList.add(new String("" + invoiceList.get(i).getProject_code()));
                rowDataList.add(new String("" + invoiceList.get(i).getProject_name()));
                rowDataList.add(new String("" + invoiceList.get(i).getCustomer_code()));
                rowDataList.add(new String("" + invoiceList.get(i).getCustomer_name()));
                rowDataList.add(new String("" + invoiceList.get(i).getSubmitted_on()));
                rowDataList.add(new String("" + invoiceList.get(i).getApproved_on()));
                rowDataList.add(new String("" + invoiceList.get(i).getAmount()));
                rowDataList.add(new String("" + invoiceList.get(i).getTax_1()));
                rowDataList.add(new String("" + invoiceList.get(i).getTax_2()));
                rowDataList.add(new String("" + invoiceList.get(i).getTax_3()));
                rowDataList.add(new String("" + invoiceList.get(i).getTotal_amount()));
                rowDataList.add(new String("" + invoiceList.get(i).getCurrency()));
                rowDataList.add(new String("" + invoiceList.get(i).getCredit_period()));
                rowDataList.add(new String("" + invoiceList.get(i).getStatus()));
                rowDataList.add(new String("" + invoiceList.get(i).getAging()));
                
                entireList.add(rowDataList);
            }
            ArrayList avgData = new ArrayList();
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String(""));
            avgData.add(new String("Average"));
            avgData.add(new String("" + invoiceAverage.getAging()));

            entireList.add(avgData);
            CommonMethods.exportToExcel(response, entireList, "invoice_aging_report", "InvoiceAgingReport", "");
            return null;
//            Map<String,String> monthList = CommonMethods.getNewMonthsList();
//            Map<Integer,Integer> yearList = CommonMethods.getYearsList(3);
//            mvc.addObject("monthList", monthList);
//            mvc.addObject("yearList", yearList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    
    public ModelAndView reimbursementReport(HttpServletRequest request, HttpServletResponse response, ReimbursementsData filterData) throws Exception {
        ModelAndView mvc = null;
        try{
            mvc = new ModelAndView("/reimbursement_report");
            final WebApplicationContext ctx = getWebApplicationContext();
            CollectionReportService collectionService = (CollectionReportServiceImpl) ctx.getBean("CollectionReportService");
            List<ReimbursementsData> reimbursementList = collectionService.getReimbursementsList();
            Map<String, String> statusList = new LinkedHashMap<String, String>();
            statusList.put("", "All");
            statusList.put("f", "Finance Approved Only");
            mvc.addObject("reimbursementList", reimbursementList);
            mvc.addObject("statusList", statusList);
            mvc.addObject("filterData", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    
    public ModelAndView reimbursementReportXL(HttpServletRequest request, HttpServletResponse response, ReimbursementsData filterData) throws Exception {
        ModelAndView mvc = null;
        try{
            mvc = new ModelAndView("/reimbursement_report");
            String type = request.getParameter("type");
            String status = request.getParameter("status");
            System.out.println("her it comes "+type+"  " +status);
            String from_date = request.getParameter("from_date");
            String to_date = request.getParameter("to_date");
            System.out.println("her it comes "+from_date+"  " +to_date);
            filterData.setReimbursement_type(type);
            filterData.setStatus(status);
            filterData.setFrom_date(from_date);
            filterData.setTo_date(to_date);
            final WebApplicationContext ctx = getWebApplicationContext();
            CollectionReportService collectionService = (CollectionReportServiceImpl) ctx.getBean("CollectionReportService");
            List<ReimbursementsData> reimbursementList = collectionService.getReimbursementReport(filterData);
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < reimbursementList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + reimbursementList.get(i).getReference_number()));
                rowDataList.add(new String("" + reimbursementList.get(i).getEmployee_number()));
                rowDataList.add(new String("" + reimbursementList.get(i).getEmployee_name()));
                rowDataList.add(new String("" + reimbursementList.get(i).getReimbursement_type()));
                rowDataList.add(new String("" + reimbursementList.get(i).getApplied_date()));
                rowDataList.add(new String("" + reimbursementList.get(i).getAmount()));
                rowDataList.add(new String("" + reimbursementList.get(i).getCurrency()));
                rowDataList.add(new String("" + reimbursementList.get(i).getClient_reimbursement()));
                rowDataList.add(new String("" + reimbursementList.get(i).getStatus()));
                rowDataList.add(new String("" + reimbursementList.get(i).getFinance_approved_date()));
                rowDataList.add(new String("" + reimbursementList.get(i).getFinance_name()));
                rowDataList.add(new String("" + reimbursementList.get(i).getDescription()));
                rowDataList.add(new String("" + reimbursementList.get(i).getRm_approved_date()));
                rowDataList.add(new String("" + reimbursementList.get(i).getRm_name()));
                rowDataList.add(new String("" + reimbursementList.get(i).getBuh_approved_date()));
                rowDataList.add(new String("" + reimbursementList.get(i).getBuh_name()));
                rowDataList.add(new String("" + reimbursementList.get(i).getHr_approved_date()));
                rowDataList.add(new String("" + reimbursementList.get(i).getHr_name()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "reimbursement_report", "reimbursement_report", "");
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    
}