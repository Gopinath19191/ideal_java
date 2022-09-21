/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dto;

/**
 *
 * @author 16221
 */
public class TimesheetDto {
    private String employee_id;
    private String employee_number;
    private String project_code;
    private String project_id;
    private String role_id;
    private String role_type;
    private String timesheet_hours;
    private String reason;
    private String uploaded_by;
    private String date;
    private String p_insert_status;
    private String row_id;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public String getTimesheet_hours() {
        return timesheet_hours;
    }

    public void setTimesheet_hours(String timesheet_hours) {
        this.timesheet_hours = timesheet_hours;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getP_insert_status() {
        return p_insert_status;
    }

    public void setP_insert_status(String p_insert_status) {
        this.p_insert_status = p_insert_status;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }
    
}
