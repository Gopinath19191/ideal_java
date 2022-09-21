/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.BillingInvoiceReportDao;
import com.defiance.ideal.reports.dto.BillingInvoiceReportDataDTO;
import com.defiance.ideal.reports.dto.BillingInvoiceReportFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16047
 */
public class BillingInvoiceReportServiceImpl implements BillingInvoiceReportService{
    public BillingInvoiceReportDao billing_Invoice_Report_Dao;

    public BillingInvoiceReportDao getBilling_Invoice_Report_Dao() {
        return billing_Invoice_Report_Dao;
    }

    public void setBilling_Invoice_Report_Dao(BillingInvoiceReportDao billing_Invoice_Report_Dao) {
        this.billing_Invoice_Report_Dao = billing_Invoice_Report_Dao;
    }
    public List<BillingInvoiceReportDataDTO> getBillingInvoiceList(BillingInvoiceReportFilterDTO formData){
        List<BillingInvoiceReportDataDTO> billingInvoiceList = null;
        try{
            billingInvoiceList = billing_Invoice_Report_Dao.getBillingInvoiceList(formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return billingInvoiceList;
    }

    public List<BillingInvoiceReportFilterDTO> getSearchList(String empVal) {
        List<BillingInvoiceReportFilterDTO> getSearchList = null;
        try{
            getSearchList = billing_Invoice_Report_Dao.getSearchList(empVal);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getSearchList;
    }

    public List<BillingInvoiceReportFilterDTO> getProjectSearchList(String prjVal) {
        List<BillingInvoiceReportFilterDTO> getProjectSearchList = null;
        try{
            getProjectSearchList = billing_Invoice_Report_Dao.getProjectSearchList(prjVal);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getProjectSearchList;
    }

    public List<BillingInvoiceReportFilterDTO> getCustomerSearchList(String custVal) {
        List<BillingInvoiceReportFilterDTO> getCustomerSearchList = null;
        try{
            getCustomerSearchList = billing_Invoice_Report_Dao.getCustomerSearchList(custVal);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getCustomerSearchList;
    }

    public Map<String, String> getPrjSbuList() {
        return billing_Invoice_Report_Dao.getPrjSbuList();
    }

    public Map<String, String> getEmpSbuList() {
        return billing_Invoice_Report_Dao.getEmpSbuList();
    }

    public List<BillingInvoiceReportDataDTO> getBillingYearList() {
        return billing_Invoice_Report_Dao.getBillingYearList();
    }

    public List<BillingInvoiceReportDataDTO> getBillingMonthList() {
        return billing_Invoice_Report_Dao.getBillingMonthList();
    }

    public List<BillingInvoiceReportDataDTO> getBilingAdviceFilterList() {
        return billing_Invoice_Report_Dao.getBilingAdviceFilterList();
    }

    public List<BillingInvoiceReportDataDTO> getAccrualSnoList() {
        return billing_Invoice_Report_Dao.getAccrualSnoList();
    }

    public String getEmployeeName(String employee_id) {
        return billing_Invoice_Report_Dao.getEmployeeName(employee_id);
    }

    public String getProjectName(String projectId) {
        return billing_Invoice_Report_Dao.getProjectName(projectId);
    }

    public String getCustomerName(String customerId) {
        return billing_Invoice_Report_Dao.getCustomerName(customerId);
    }

    public int getBillingInvoiceListCount(BillingInvoiceReportFilterDTO formData) {
        return billing_Invoice_Report_Dao.getBillingInvoiceListCount(formData);
    }
}
