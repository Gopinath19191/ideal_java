/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

import java.io.Serializable;

/**
 *
 * @author 16047
 */
public class BillingInvoiceReportFilterDTO implements Serializable{
    private String employee_id;
    private String employee_search;
    private String employeeName;
    private String projectName;
    private String customer;
    private String projectSbu;
    private String employeeSbu;
    private String billingYear;
    private String[] billingMonth;
    private String accrualSno;
    private String accruedFrom;
    private String accruedTo;
    private String project_search;
    private String customer_search;
    private String projectId;
    private String customerId;
    private String customerName;
    private String operator;
    private String selectedValue;
    private int start_page;
    private int end_page;

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

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getOperator(){
        return operator;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProject_search() {
        return project_search;
    }

    public void setProject_search(String project_search) {
        this.project_search = project_search;
    }

    public String getCustomer_search() {
        return customer_search;
    }

    public void setCustomer_search(String customer_search) {
        this.customer_search = customer_search;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_search() {
        return employee_search;
    }

    public void setEmployee_search(String employee_search) {
        this.employee_search = employee_search;
    }

    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProjectSbu() {
        return projectSbu;
    }

    public void setProjectSbu(String projectSbu) {
        this.projectSbu = projectSbu;
    }

    public String getEmployeeSbu() {
        return employeeSbu;
    }

    public void setEmployeeSbu(String employeeSbu) {
        this.employeeSbu = employeeSbu;
    }

    public String getBillingYear() {
        return billingYear;
    }

    public void setBillingYear(String billingYear) {
        this.billingYear = billingYear;
    }

    public String[] getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String[] billingMonth) {
        this.billingMonth = billingMonth;
    }

    public String getAccrualSno() {
        return accrualSno;
    }

    public void setAccrualSno(String accrualSno) {
        this.accrualSno = accrualSno;
    }

    public String getAccruedFrom() {
        return accruedFrom;
    }

    public void setAccruedFrom(String accruedFrom) {
        this.accruedFrom = accruedFrom;
    }

    public String getAccruedTo() {
        return accruedTo;
    }

    public void setAccruedTo(String accruedTo) {
        this.accruedTo = accruedTo;
    }
    
    
}
