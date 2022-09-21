/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class EarnedLeaveFilterDTO {

    private int sbuId;
    private String employee_id;
    private String filter_year;
    private String filter_month;
    private String filter_date_applied;
    private String filter_date_applied_pre;
    private String empId;
    private String empName;
    private String empVal;
    private String financialYear;
    private String employee_search;

    public String getFilter_date_applied_pre() {
        return filter_date_applied_pre;
    }

    public void setFilter_date_applied_pre(String filter_date_applied_pre) {
        this.filter_date_applied_pre = filter_date_applied_pre;
    }

    public String getFilter_date_applied() {
        return filter_date_applied;
    }

    public void setFilter_date_applied(String filter_date_applied) {
        this.filter_date_applied = filter_date_applied;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getEmpVal() {
        return empVal;
    }

    public void setEmpVal(String empVal) {
        this.empVal = empVal;
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

    public String getFilter_month() {
        return filter_month;
    }

    public void setFilter_month(String filter_month) {
        this.filter_month = filter_month;
    }

    public String getFilter_year() {
        return filter_year;
    }

    public void setFilter_year(String filter_year) {
        this.filter_year = filter_year;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public int getSbuId() {
        return sbuId;
    }

    public void setSbuId(int sbuId) {
        this.sbuId = sbuId;
    }

    public String getEmployee_search() {
        return employee_search;
    }

    public void setEmployee_search(String employee_search) {
        this.employee_search = employee_search;
    }
    
}
