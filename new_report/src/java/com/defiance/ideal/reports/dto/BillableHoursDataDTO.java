/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class BillableHoursDataDTO {

    private String code;
    private String name;
    private String manager;
    private String customer;
    private String location;
    private String empId;
    private String empName;
    private String workedHrs;
    private String approvedHrs;
    private String pricing;
    private String month;
    private String year;
    private String accruedStatus;
    private String invoicedHrs;
    private String uom;
   

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
   


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAccrued() {
        return accrued;
    }

    public void setAccrued(String accrued) {
        this.accrued = accrued;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public String getApprovedHrs() {
        return approvedHrs;
    }

    public void setApprovedHrs(String approvedHrs) {
        this.approvedHrs = approvedHrs;
    }

    public String getWorkedHrs() {
        return workedHrs;
    }

    public void setWorkedHrs(String workedHrs) {
        this.workedHrs = workedHrs;
    }
    private String accrued;
    private String sbu;
    private String subSbu;

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccruedStatus() {
        return accruedStatus;
    }

    public void setAccruedStatus(String accruedStatus) {
        this.accruedStatus = accruedStatus;
    }

    public String getInvoicedHrs() {
        return invoicedHrs;
    }

    public void setInvoicedHrs(String invoicedHrs) {
        this.invoicedHrs = invoicedHrs;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

}
