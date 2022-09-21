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
public class BillableStatusDataDTO implements Serializable {

    private String empNumber;
    private String empName;
    private String empBilable;
    private String empStatus;
    private String joinedDate;
    private String releasedDate;
    private String sbuName;
    private String subSbu;
    private String projectCode;
    private String projectName;
    private String billStatus;
    private String custName;
    private String pmName;
    private String eStat;


    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getEmpBilable() {
        return empBilable;
    }

    public void setEmpBilable(String empBilable) {
        this.empBilable = empBilable;
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

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
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

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String geteStat() {
        return eStat;
    }

    public void seteStat(String eStat) {
        this.eStat = eStat;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

}
