/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

/**
 *
 * @author 16365
 */
public class SearchCriteriaDTO {
    private String cityId;
    private String employeeId;
    private String start_date;
    private String end_date;
    private String projectId;
    private String phaseId;
    private String timesheet_date;
    private String status;
    private String global_projects;
    private String projectPhase;
    private String employeeNumber;
    private String taskId;
    
    private String yearStart_date;
    private String yearEnd_date;
    
    private String year;
    private String month;
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getYearStart_date() {
        return yearStart_date;
    }

    public void setYearStart_date(String yearStart_date) {
        this.yearStart_date = yearStart_date;
    }

    public String getYearEnd_date() {
        return yearEnd_date;
    }

    public void setYearEnd_date(String yearEnd_date) {
        this.yearEnd_date = yearEnd_date;
    }
    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    
    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public String getGlobal_projects() {
        return global_projects;
    }

    public void setGlobal_projects(String global_projects) {
        this.global_projects = global_projects;
    }    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    

    public String getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(String timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }   

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
