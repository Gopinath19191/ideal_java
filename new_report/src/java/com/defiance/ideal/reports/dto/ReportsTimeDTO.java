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
public class ReportsTimeDTO implements Serializable {

    private String workedHrs;
    private String workedHrsSec;
    private String approvedHrs;
    private String approvedHrsSec;
    private String leaveId;
    private String ptaId;
    private String approvType;

    private int tsDate;
    private String leaveDate;
    private String allocDate;



    public String getWorkedHrs() {
        return workedHrs;
    }

    public void setWorkedHrs(String workedHrs) {
        this.workedHrs = workedHrs;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getPtaId() {
        return ptaId;
    }

    public void setPtaId(String ptaId) {
        this.ptaId = ptaId;
    }

    public String getApprovedHrs() {
        return approvedHrs;
    }

    public void setApprovedHrs(String approvedHrs) {
        this.approvedHrs = approvedHrs;
    }

    public String getApprovedHrsSec() {
        return approvedHrsSec;
    }

    public void setApprovedHrsSec(String approvedHrsSec) {
        this.approvedHrsSec = approvedHrsSec;
    }

    public String getWorkedHrsSec() {
        return workedHrsSec;
    }

    public void setWorkedHrsSec(String workedHrsSec) {
        this.workedHrsSec = workedHrsSec;
    }

    public String getApprovType() {
        return approvType;
    }

    public void setApprovType(String approvType) {
        this.approvType = approvType;
    }

    public int getTsDate() {
        return tsDate;
    }

    public void setTsDate(int tsDate) {
        this.tsDate = tsDate;
    }

    public String getAllocDate() {
        return allocDate;
    }

    public void setAllocDate(String allocDate) {
        this.allocDate = allocDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public ReportsTimeDTO(int tsDate) {
        this.tsDate = tsDate;
    }

    public ReportsTimeDTO() {
    }

}
