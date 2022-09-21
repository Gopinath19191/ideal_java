/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 15332
 */
public class PurchaseOrderReportDetails {
     String customer;
     String pid_Num;
     String pid_Description;
     String sbu;
     String bdm;
     String projectManager;
     String po_Date;
     String currencies;
     String po_Value;
     String startDate;
     String endDate;
     String projectName;
     String customerName;
     String po_Reference_Number;
     String fromDate;
     String toDate;
     int start_page;
     int end_page;

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }
     
     

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBdm() {
        return bdm;
    }

    public void setBdm(String bdm) {
        this.bdm = bdm;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPid_Description() {
        return pid_Description;
    }

    public void setPid_Description(String pid_Description) {
        this.pid_Description = pid_Description;
    }

    public String getPid_Num() {
        return pid_Num;
    }

    public void setPid_Num(String pid_Num) {
        this.pid_Num = pid_Num;
    }

    public String getPo_Date() {
        return po_Date;
    }

    public void setPo_Date(String po_Date) {
        this.po_Date = po_Date;
    }

     
    
    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }
    
    public String getPo_Value() {
        return po_Value;
    }

    public void setPo_Value(String po_Value) {
        this.po_Value = po_Value;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    
public String getPo_Reference_Number() {
        return po_Reference_Number;
    }

    public void setPo_Reference_Number(String po_Reference_Number) {
        this.po_Reference_Number = po_Reference_Number;
    }
    
}
