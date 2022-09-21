/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14619
 */
public class BenchReportDataDTO {
    private String empNumber;
    private String empName;
    private String empStatus;
    private String rmName;
    private String joiningDate;
    private String sbu;
    private String band;
    private String dtExp;
    private String lastAllocatedDate;
    private String lastBilledDate;
    private String lastBilledHrs;
    private String lastProjectId;
    private String benchDays;
    private String subSbu;

    public String getLastAllocatedDate() {
        return lastAllocatedDate;
    }

    public void setLastAllocatedDate(String lastAllocatedDate) {
        this.lastAllocatedDate = lastAllocatedDate;
    }

    /**
     * @return the empNumber
     */
    public String getEmpNumber() {
        return empNumber;
    }

    /**
     * @param empNumber the empNumber to set
     */
    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus the empStatus to set
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    /**
     * @return the rmName
     */
    public String getRmName() {
        return rmName;
    }

    /**
     * @param rmName the rmName to set
     */
    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    /**
     * @return the joiningDate
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     * @param joiningDate the joiningDate to set
     */
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    /**
     * @return the sbu
     */
    public String getSbu() {
        return sbu;
    }

    /**
     * @param sbu the sbu to set
     */
    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    /**
     * @return the band
     */
    public String getBand() {
        return band;
    }

    /**
     * @param band the band to set
     */
    public void setBand(String band) {
        this.band = band;
    }

    /**
     * @return the dtExp
     */
    public String getDtExp() {
        return dtExp;
    }

    /**
     * @param dtExp the dtExp to set
     */
    public void setDtExp(String dtExp) {
        this.dtExp = dtExp;
    }

    /**
     * @return the lastBilledDate
     */
    public String getLastBilledDate() {
        return lastBilledDate;
    }

    /**
     * @param lastBilledDate the lastBilledDate to set
     */
    public void setLastBilledDate(String lastBilledDate) {
        this.lastBilledDate = lastBilledDate;
    }

    /**
     * @return the lastBilledHrs
     */
    public String getLastBilledHrs() {
        return lastBilledHrs;
    }

    /**
     * @param lastBilledHrs the lastBilledHrs to set
     */
    public void setLastBilledHrs(String lastBilledHrs) {
        this.lastBilledHrs = lastBilledHrs;
    }

    /**
     * @return the lastProjectId
     */
    public String getLastProjectId() {
        return lastProjectId;
    }

    /**
     * @param lastProjectId the lastProjectId to set
     */
    public void setLastProjectId(String lastProjectId) {
        this.lastProjectId = lastProjectId;
    }

    /**
     * @return the benchDays
     */
    public String getBenchDays() {
        return benchDays;
    }

    /**
     * @param benchDays the benchDays to set
     */
    public void setBenchDays(String benchDays) {
        this.benchDays = benchDays;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }
}
