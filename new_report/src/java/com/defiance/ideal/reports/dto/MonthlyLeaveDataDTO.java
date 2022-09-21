/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class MonthlyLeaveDataDTO {
    private String empNumber;
    private String firstName;
    private String lastName;
    private String compStruct;
    private String leaveType;
    private String leaveReason;
    private String dateApplied;
    private String fromDate;
    private String toDate;
    private String length;
    private String leaveLength;
    private String canceledLeaves;
    private String leaveStatus;
    private String compStructSubgroup;

    public String getLeaveLength() {
        return leaveLength;
    }

    public void setLeaveLength(String leaveLength) {
        this.leaveLength = leaveLength;
    }

    
    public String getCanceledLeaves() {
        return canceledLeaves;
    }

    public void setCanceledLeaves(String canceledLeaves) {
        this.canceledLeaves = canceledLeaves;
    }

    public String getCompStruct() {
        return compStruct;
    }

    public void setCompStruct(String compStruct) {
        this.compStruct = compStruct;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCompStructSubgroup() {
        return compStructSubgroup;
    }

    public void setCompStructSubgroup(String compStructSubgroup) {
        this.compStructSubgroup = compStructSubgroup;
    }

}
