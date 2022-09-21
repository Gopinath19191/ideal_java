/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14578
 */
public class TimesheetEntryDTO {


    Map projectMap = new HashMap();
    Map phaseMap = new HashMap();
    Map taskMap = new HashMap();
    Map roleMap = new HashMap();
    Map locationMap = new HashMap();
    
    private String employeeId;
    private String start_date;
    private String end_date;
    private String project_id;
    private String project_name;
    private String timesheet_id;
    private String timesheet_date;
    private String timesheet_datex;
    private String timesheet_day;
    private String configuration_key;
    private String configuration_value;
    private String phase_name;
    private String phase_id;
    private String task_id;
    private String task_name;
    private String status;
    private String worked_hours;
    private String worked_minutes;
    private String available_hours;
    private String office_hours;
    private String effective_date;    
    
    private String attendance_hours;
    private String attendance_date;
    
    private String status_key;
    private String status_value;
    
    private int week_day;
    private String shift;
    private String remarks;
    private String rejected_remarks;
    private String role_name;
    private String role_id;
    private String location;

    private String time_sheet_id;  
    private String leave;
    private String leave_status;
    private String holiday_description;
    
    private String regularization_reason;
    private String deleted;
    private String month;
    private String year;
            
    private String isSubmitted;
    private String status_text;
    private String date_display;
    private String isWeekEnd;

    List<ConfigurationDTO> reg_reason_list = new ArrayList<ConfigurationDTO>();

    public List<ConfigurationDTO> getReg_reason_list() {
        return reg_reason_list;
    }

    public void setReg_reason_list(List<ConfigurationDTO> reg_reason_list) {
        this.reg_reason_list = reg_reason_list;
    }
    
    public String getIsWeekEnd() {
        return isWeekEnd;
    }

    public void setIsWeekEnd(String isWeekEnd) {
        this.isWeekEnd = isWeekEnd;
    }   

    public String getDate_display() {
        return date_display;
    }

    public void setDate_display(String date_display) {
        this.date_display = date_display;
    }
        
    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }
    
    public String getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(String isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getRegularization_reason() {
        return regularization_reason;
    }

    public void setRegularization_reason(String regularization_reason) {
        this.regularization_reason = regularization_reason;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }

    public String getHoliday_description() {
        return holiday_description;
    }

    public void setHoliday_description(String holiday_description) {
        this.holiday_description = holiday_description;
    }    
    
    public Map getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map projectMap) {
        this.projectMap = projectMap;
    }

    public Map getPhaseMap() {
        return phaseMap;
    }

    public void setPhaseMap(Map phaseMap) {
        this.phaseMap = phaseMap;
    }

    public Map getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map taskMap) {
        this.taskMap = taskMap;
    }

    public Map getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map roleMap) {
        this.roleMap = roleMap;
    }

    public Map getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(Map locationMap) {
        this.locationMap = locationMap;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTimesheet_id() {
        return timesheet_id;
    }

    public void setTimesheet_id(String timesheet_id) {
        this.timesheet_id = timesheet_id;
    }

    public String getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(String timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public String getTimesheet_datex() {
        return timesheet_datex;
    }

    public void setTimesheet_datex(String timesheet_datex) {
        this.timesheet_datex = timesheet_datex;
    }

    public String getTimesheet_day() {
        return timesheet_day;
    }

    public void setTimesheet_day(String timesheet_day) {
        this.timesheet_day = timesheet_day;
    }

    public String getConfiguration_key() {
        return configuration_key;
    }

    public void setConfiguration_key(String configuration_key) {
        this.configuration_key = configuration_key;
    }

    public String getConfiguration_value() {
        return configuration_value;
    }

    public void setConfiguration_value(String configuration_value) {
        this.configuration_value = configuration_value;
    }

    public String getPhase_name() {
        return phase_name;
    }

    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorked_hours() {
        return worked_hours;
    }

    public void setWorked_hours(String worked_hours) {
        this.worked_hours = worked_hours;
    }

    public String getWorked_minutes() {
        return worked_minutes;
    }

    public void setWorked_minutes(String worked_minutes) {
        this.worked_minutes = worked_minutes;
    }

    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }

    public String getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(String office_hours) {
        this.office_hours = office_hours;
    }
        
    public String getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }

    public String getAttendance_hours() {
        return attendance_hours;
    }

    public void setAttendance_hours(String attendance_hours) {
        this.attendance_hours = attendance_hours;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getStatus_key() {
        return status_key;
    }

    public void setStatus_key(String status_key) {
        this.status_key = status_key;
    }

    public String getStatus_value() {
        return status_value;
    }

    public void setStatus_value(String status_value) {
        this.status_value = status_value;
    }

    public int getWeek_day() {
        return week_day;
    }

    public void setWeek_day(int week_day) {
        this.week_day = week_day;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRejected_remarks() {
        return rejected_remarks;
    }

    public void setRejected_remarks(String rejected_remarks) {
        this.rejected_remarks = rejected_remarks;
    }
    

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime_sheet_id() {
        return time_sheet_id;
    }

    public void setTime_sheet_id(String time_sheet_id) {
        this.time_sheet_id = time_sheet_id;
    }   
    
}
