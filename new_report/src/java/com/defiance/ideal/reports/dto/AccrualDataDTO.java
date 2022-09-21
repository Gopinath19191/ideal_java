/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;



import java.io.Serializable;
/**
 *
 * @author 14053
 */
public class AccrualDataDTO implements Serializable {
    private String projectCode;
    private String projectName;
    private String pmName;
    private String accrualStatus;
    private String billingStatus;
    private String invoiceStatus;
    private String sbu;
    private String subSbu;
    private String accrualCount;
    private String totalTeamCount;

    public String getAccrualCount() {
        return accrualCount;
    }

    public void setAccrualCount(String accrualCount) {
        this.accrualCount = accrualCount;
    }

    public String getTotalTeamCount() {
        return totalTeamCount;
    }

    public void setTotalTeamCount(String totalTeamCount) {
        this.totalTeamCount = totalTeamCount;
    }
    
    public String getAccrualStatus() {
        return accrualStatus;
    }

    public void setAccrualStatus(String accrualStatus) {
        this.accrualStatus = accrualStatus;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
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

}
