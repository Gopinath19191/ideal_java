/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employee.dto;

import java.io.Serializable;

/**
 *
 * @author 16363
 */
public class TSNotificationListDto implements Serializable{    
    private String timesheet_date;
    private String projectID;    
    private String project_name;
    private String timesheet_hours;
    private String rejection_remarks;   

    public String getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(String timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
  
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTimesheet_hours() {
        return timesheet_hours;
    }

    public void setTimesheet_hours(String timesheet_hours) {
        this.timesheet_hours = timesheet_hours;
    }

    public String getRejection_remarks() {
        if(this.rejection_remarks == null) return "";
        return rejection_remarks;
    }

    public void setRejection_remarks(String rejection_remarks) {
        this.rejection_remarks = rejection_remarks;
    }
    
}
