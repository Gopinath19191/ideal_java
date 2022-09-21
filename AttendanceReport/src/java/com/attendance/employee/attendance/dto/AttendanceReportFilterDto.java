/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

import java.util.Date;

/**
 *
 * @author 16365
 */
public class AttendanceReportFilterDto {
    private String fromDate;
    private String toDate;
    private String loggedInempId;
    private String rmId;
    private String unit;
    private String location;
    private String moduleId;
    private int startIndex;
    private int endIndex;
    private int totalRecords;
    private int role;
    private String empId;

    public String getLoggedInempId() {
        return loggedInempId;
    }

    public void setLoggedInempId(String loggedInempId) {
        this.loggedInempId = loggedInempId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }    
    
    public enum Role {
       EMP, RM, ADMIN, PM;
    };

    public int getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.ordinal();
    }
    
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
    
    private String displayFromDate;
    private String displayToDate;
    
    
    public String getDisplayFromDate() {
        return displayFromDate;
    }

    public void setDisplayFromDate(String displayFromDate) {
        this.displayFromDate = displayFromDate;
    }

    public String getDisplayToDate() {
        return displayToDate;
    }

    public void setDisplayToDate(String displayToDate) {
        this.displayToDate = displayToDate;
    }    

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
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

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
}
