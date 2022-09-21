/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 14517
 */
public class CustagingReportFilterDTO {
    private Date historyUpdatedDate;
    private String empId;
    private String legal_entity;
    private String business_leader_id;
    private String bdm_id;
    private String customer_id;
    public String statusFilter;
    public String reportDate;
    public String overDue_aging;
    private String  operators;
    private String bdm_Name_List;
    private String[] invoice_number;
    private String[] date_of_report;
    private String submition_date;
    private String submition_invoice_number;
     private String dynamic_query;

    public String getDynamic_query() {
        return dynamic_query;
    }

    public void setDynamic_query(String dynamic_query) {
        this.dynamic_query = dynamic_query;
    }
     
     

    public String getSubmition_invoice_number() {
        return submition_invoice_number;
    }

    public void setSubmition_invoice_number(String submition_invoice_number) {
        this.submition_invoice_number = submition_invoice_number;
    }
    

    public String getSubmition_date() {
        return submition_date;
    }

    public void setSubmition_date(String submition_date) {
        this.submition_date = submition_date;
    }
    
    
    public String[] getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String[] invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String[] getDate_of_report() {
        return date_of_report;
    }

    public void setDate_of_report(String[] date_of_report) {
        this.date_of_report = date_of_report;
    }
    
    

   
    public String getBdm_Name_List() {
        return bdm_Name_List;
    }

    public void setBdm_Name_List(String bdm_Name_List) {
        this.bdm_Name_List = bdm_Name_List;
    }

   
    
    private String SBU;

    public String getSBU() {
        return SBU;
    }

    public void setSBU(String SBU) {
        this.SBU = SBU;
    }
    
    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }
    private String[] custagingCheckList;


    private String[] custagingId;
    private Date[] invoiceDateSubmitCustomer;
    private Date[] expectedCollectionDate;
    
    private String custagingIdForUpdate;
    private Date invoiceDateSubmitCustomerForUpdate;
    private Date expectedCollectionDateForUpdate;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getBdm_id() {
        return bdm_id;
    }

    public void setBdm_id(String bdm_id) {
        this.bdm_id = bdm_id;
    }

    public String getBusiness_leader_id() {
        return business_leader_id;
    }

    public void setBusiness_leader_id(String business_leader_id) {
        this.business_leader_id = business_leader_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getLegal_entity() {
        return legal_entity;
    }

    public void setLegal_entity(String legal_entity) {
        this.legal_entity = legal_entity;
    }

    public String getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(String statusFilter) {
        this.statusFilter = statusFilter;
    }

    public String[] getCustagingCheckList() {
        return custagingCheckList;
    }

    public void setCustagingCheckList(String[] custagingCheckList) {
        this.custagingCheckList = custagingCheckList;
    }

    public String[] getCustagingId() {
        return custagingId;
    }

    public void setCustagingId(String[] custagingId) {
        this.custagingId = custagingId;
    }

    public Date[] getExpectedCollectionDate() {
        return expectedCollectionDate;
    }

    public void setExpectedCollectionDate(Date[] expectedCollectionDate) {
        this.expectedCollectionDate = expectedCollectionDate;
    }

    public Date[] getInvoiceDateSubmitCustomer() {
        return invoiceDateSubmitCustomer;
    }

    public void setInvoiceDateSubmitCustomer(Date[] invoiceDateSubmitCustomer) {
        this.invoiceDateSubmitCustomer = invoiceDateSubmitCustomer;
    }

    public String getCustagingIdForUpdate() {
        return custagingIdForUpdate;
    }

    public void setCustagingIdForUpdate(String custagingIdForUpdate) {
        this.custagingIdForUpdate = custagingIdForUpdate;
    }

    public Date getExpectedCollectionDateForUpdate() {
        return expectedCollectionDateForUpdate;
    }

    public void setExpectedCollectionDateForUpdate(Date expectedCollectionDateForUpdate) {
        this.expectedCollectionDateForUpdate = expectedCollectionDateForUpdate;
    }

    public Date getInvoiceDateSubmitCustomerForUpdate() {
        return invoiceDateSubmitCustomerForUpdate;
    }

    public void setInvoiceDateSubmitCustomerForUpdate(Date invoiceDateSubmitCustomerForUpdate) {
        this.invoiceDateSubmitCustomerForUpdate = invoiceDateSubmitCustomerForUpdate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getHistoryUpdatedDate() {
        return historyUpdatedDate;
    }

    public void setHistoryUpdatedDate(Date historyUpdatedDate) {
        this.historyUpdatedDate = historyUpdatedDate;
    }

    public String getOverDue_aging() {
        return overDue_aging;
    }

    public void setOverDue_aging(String overDue_aging) {
        this.overDue_aging = overDue_aging;
    }
    
}
