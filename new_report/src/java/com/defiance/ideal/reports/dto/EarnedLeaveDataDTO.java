/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class EarnedLeaveDataDTO {
    private String empNumber;
    private String empName;
    private String rmName;
    private String unit;
    private String earnedLeave;
    private String appliedDate;
    private String daysCount;
    private String financialYear;
    private String fromDate;
    private String toDate;
    private String empId;
    private String cmpStructSubGroup;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }
    

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(String daysCount) {
        this.daysCount = daysCount;
    }

    public String getEarnedLeave() {
        return earnedLeave;
    }

    public void setEarnedLeave(String earnedLeave) {
        this.earnedLeave = earnedLeave;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCmpStructSubGroup() {
        return cmpStructSubGroup;
    }

    public void setCmpStructSubGroup(String cmpStructSubGroup) {
        this.cmpStructSubGroup = cmpStructSubGroup;
    }
   

}
