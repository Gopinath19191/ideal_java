/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 14053
 */
public class ReportsDataDTO implements Serializable {
    private int employeeId;
    private String employeeNumber;
    private String employeeName;

    private String totalWorkedHrs;
    private String totalApprovedHrs;
    
    private String assocStDate;
    private String assocEdDate;

    private String accrEffort;
    private String invEffort;
    private String accrStatus;
    private String approvType;
    private String sbu;
    private String subSbu;
    private String startDate;
    private String endDate;
    private String totalWorkedHours;
    private String totalApprovedHours;
    private String totalAccruedHours;

    private List<ReportsTimeDTO> workDetails;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAssocEdDate() {
        return assocEdDate;
    }

    public void setAssocEdDate(String assocEdDate) {
        this.assocEdDate = assocEdDate;
    }

    public String getAssocStDate() {
        return assocStDate;
    }

    public void setAssocStDate(String assocStDate) {
        this.assocStDate = assocStDate;
    }

    public List<ReportsTimeDTO> getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(List<ReportsTimeDTO> workDetails) {
        this.workDetails = workDetails;
    }

    public String getTotalApprovedHrs() {
        return totalApprovedHrs;
    }

    public void setTotalApprovedHrs(String totalApprovedHrs) {
        this.totalApprovedHrs = totalApprovedHrs;
    }

    public String getTotalWorkedHrs() {
        return totalWorkedHrs;
    }

    public void setTotalWorkedHrs(String totalWorkedHrs) {
        this.totalWorkedHrs = totalWorkedHrs;
    }

    public String getAccrEffort() {
        return accrEffort;
    }

    public void setAccrEffort(String accrEffort) {
        this.accrEffort = accrEffort;
    }

    public String getAccrStatus() {
        return accrStatus;
    }

    public void setAccrStatus(String accrStatus) {
        this.accrStatus = accrStatus;
    }

    public String getInvEffort() {
        return invEffort;
    }

    public void setInvEffort(String invEffort) {
        this.invEffort = invEffort;
    }

    public String getApprovType() {
        return approvType;
    }

    public void setApprovType(String approvType) {
        this.approvType = approvType;
    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(String totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }

    public String getTotalApprovedHours() {
        return totalApprovedHours;
    }

    public void setTotalApprovedHours(String totalApprovedHours) {
        this.totalApprovedHours = totalApprovedHours;
    }

    public String getTotalAccruedHours() {
        return totalAccruedHours;
    }

    public void setTotalAccruedHours(String totalAccruedHours) {
        this.totalAccruedHours = totalAccruedHours;
    }

}
