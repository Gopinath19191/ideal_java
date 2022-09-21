
package com.mycompany.employee.dto;

import java.io.Serializable;

public class TimeSheetDto implements Serializable{
    private String empNumber;
    private String fromDate;
    private String toDate;
    private String selected_date;
    private String availableHrs;
    private String empid;
    private String attendance_hours;
    private String timesheetHrs;
    private String status;
    private String is_holiday;
    private String project_id;
    private String project_name;
    private String timesheet_hours;
    private String shift_id;
    private String role_id;
    private String phase_id;
    private String task_id;
    private String is_regularized;
    private String reasons;
    private String remark;
    private String tokenNo;
    private String regularizationHrs;
    private String totalHrs;
    private String timesheet_id;
    private String reasonCheck;
    private String project_status;

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }
    public String getReasonCheck() {
        return reasonCheck;
    }

    public void setReasonCheck(String reasonCheck) {
        this.reasonCheck = reasonCheck;
    }
    
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
    
    public String getTimesheet_id() {
        if(this.timesheet_id == null) return "";
        return timesheet_id;
    }

    public void setTimesheet_id(String timesheet_id) {
        this.timesheet_id = timesheet_id;
    }
    
    public String getTotalHrs() {
        if(this.totalHrs == null) return "00:00";
        return totalHrs;
    }

    public void setTotalHrs(String totalHrs) {
        this.totalHrs = totalHrs;
    }

    public String getRegularizationHrs() {
        if(this.regularizationHrs == null) return "00:00";
        return regularizationHrs;
    }

    public void setRegularizationHrs(String regularizationHrs) {
        this.regularizationHrs = regularizationHrs;
    }
    
    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getFromDate() {
        if(this.fromDate == null) return "";
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
         if(this.toDate == null) return "";
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getSelected_date() {
        if(this.selected_date == null) return "";
        return selected_date;
    }

    public void setSelected_date(String selected_date) {
        this.selected_date = selected_date;
    }

    public String getAvailableHrs() {
        if(this.availableHrs == null) return "00:00";
        return availableHrs;
    }

    public void setAvailableHrs(String availableHrs) {
        this.availableHrs = availableHrs;
    }

    public String getEmpid() {
        if(this.empid == null) return "";
        return empid; 
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getAttendance_hours() {
        if(this.attendance_hours == null) return "00:00";
        return attendance_hours;
    }

    public void setAttendance_hours(String attendance_hours) {
        this.attendance_hours = attendance_hours;
    }

    public String getTimesheetHrs() {
        if(this.timesheetHrs == null) return "";
        return timesheetHrs;
    }

    public void setTimesheetHrs(String timesheetHrs) {
        this.timesheetHrs = timesheetHrs;
    }

    public String getStatus() {
        if(this.status == null) return "";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_holiday() {
        if(this.is_holiday == null) return "";
        return is_holiday;
    }

    public void setIs_holiday(String is_holiday) {
        this.is_holiday = is_holiday;
    }

    public String getProject_id() {
        if(this.project_id == null) return "";
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getTimesheet_hours() {
        if(this.timesheet_hours == null) return "";
        return timesheet_hours;
    }

    public void setTimesheet_hours(String timesheet_hours) {
        this.timesheet_hours = timesheet_hours;
    }

    public String getShift_id() {
        if(this.shift_id == null) return "";
        return shift_id;
    }

    public void setShift_id(String shift_id) {
        this.shift_id = shift_id;
    }

    public String getRole_id() {
        if(this.role_id == null) return "";
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPhase_id() {
        if(this.phase_id == null) return "";
        return phase_id;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public String getTask_id() {
        if(this.task_id == null) return "";
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getIs_regularized() {
        if(this.is_regularized == null) return "";
        return is_regularized;
    }

    public void setIs_regularized(String is_regularized) {
        this.is_regularized = is_regularized;
    }

    public String getReasons() {
        if(this.reasons == null) return "";
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getRemark() {
        if(this.remark == null) return "";
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
