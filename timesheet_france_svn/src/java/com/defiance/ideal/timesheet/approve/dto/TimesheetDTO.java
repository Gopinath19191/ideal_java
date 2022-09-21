/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 14578
 */
public class TimesheetDTO {


    Map projectMap = new HashMap();
    Map phaseMap = new HashMap();
    Map taskMap = new HashMap();
    Map roleMap = new HashMap();
    Map locationMap = new HashMap();
    
    private String[] projectName;
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
    private String effective_date;
    private String attendance_hours;
    private String attendance_date;
    private String available_minutes;
    
    private int week_day;
    private String project;
    private String phase;
    private String task;
    private String shift;
    private String remarks;
    private String rejectedRemarks;
    private String entry_date;
    private String role_name;
    private String role_id;
    private String location;
    private String role;

    private String out_phase_id;
    private String out_task_id;
    private String out_remarks;
    private String out_rejected_remarks;
    private String out_worked_time;
    private String out_shift;
    private String out_role;
    private String out_location;
    private String out_time_sheet_id;
    private String out_status;
    private String out_timesheet_date;
    private String out_project;
    private String out_available_hours;
    private String out_effective_date;
    private String out_attendance_hours;
    
    private String[] res_check;
    private String[] res_hiddenId;
    private String[] res_project;
    private String[] res_role;
    private String[] res_work_location;
    private String[] res_phase;
    private String[] res_task;
    private String[] res_remarks;
    private String[] res_timesheet_date;
    private String[] res_shift;
    private String[] res_worked_hours;
    private String[] res_worked_minutes;
    private String[] res_autoId;
    private String[] res_available_hours;
    
    private String res_year;
    private String res_month;

    private String db_autoId;
    private String db_project;
    private String db_role;
    private String db_location;
    private String db_phase;
    private String db_task;
    private String db_remarks;
    private String db_timesheet_date;
    private String db_shift;
    private String db_worked_hours;
    private String db_available_hours;
    private String db_timesheet_entry_id;

    private String joined_date;
    private String city_id;
    private String holiday_date;
    private String holiday_description;
    private String last_id;
    private String from_date;
    private String to_date;
    private String length_days;
    private String from_half_day;
    private String to_half_day;
    private String canceled_dates;
    private String leave;
    private String leave_status;
    private String prj_entry_date;
    private String empWork_location;
    
    private String status_key;
    private String status_value;

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
    
    public String getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
    
    @Override
    public String toString() {
        return "TimesheetDTO{" + "available_hours=" + available_hours + '}';
    }
    
    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }

    public String getAvailable_minutes() {
        return available_minutes;
    }

    public void setAvailable_minutes(String available_minutes) {
        this.available_minutes = available_minutes;
    }
    

    public String getOut_attendance_hours() {
        return out_attendance_hours;
    }

    public void setOut_attendance_hours(String out_attendance_hours) {
        this.out_attendance_hours = out_attendance_hours;
    }
    

    public String getOut_effective_date() {
        return out_effective_date;
    }

    public void setOut_effective_date(String out_effective_date) {
        this.out_effective_date = out_effective_date;
    }

    public String getOut_available_hours() {
        return out_available_hours;
    }

    public void setOut_available_hours(String out_available_hours) {
        this.out_available_hours = out_available_hours;
    }

    public String[] getRes_available_hours() {
        return res_available_hours;
    }

    public void setRes_available_hours(String[] res_available_hours) {
        this.res_available_hours = res_available_hours;
    }
    
    public String getRejectedRemarks() {
        return rejectedRemarks;
    }

    public void setRejectedRemarks(String rejectedRemarks) {
        this.rejectedRemarks = rejectedRemarks;
    }

    public String getOut_rejected_remarks() {
        return out_rejected_remarks;
    }

    public void setOut_rejected_remarks(String out_rejected_remarks) {
        this.out_rejected_remarks = out_rejected_remarks;
    }

    public Map getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(Map locationMap) {
        this.locationMap = locationMap;
    }

    public Map getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map roleMap) {
        this.roleMap = roleMap;
    }

    public Map getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map taskMap) {
        this.taskMap = taskMap;
    }

    public String getOut_project() {
        return out_project;
    }

    public void setOut_project(String out_project) {
        this.out_project = out_project;
    }

    public Map getPhaseMap() {
        return phaseMap;
    }

    public void setPhaseMap(Map phaseMap) {
        this.phaseMap = phaseMap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTimesheet_datex() {
        return timesheet_datex;
    }

    public void setTimesheet_datex(String timesheet_datex) {
        this.timesheet_datex = timesheet_datex;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Map getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map projectMap) {
        this.projectMap = projectMap;
    }

    public String getOut_timesheet_date() {
        return out_timesheet_date;
    }

    public void setOut_timesheet_date(String out_timesheet_date) {
        this.out_timesheet_date = out_timesheet_date;
    }

    public String getOut_status() {
        return out_status;
    }

    public void setOut_status(String out_status) {
        this.out_status = out_status;
    }

    public String getOut_location() {
        return out_location;
    }

    public void setOut_location(String out_location) {
        this.out_location = out_location;
    }

    public String getOut_phase_id() {
        return out_phase_id;
    }

    public void setOut_phase_id(String out_phase_id) {
        this.out_phase_id = out_phase_id;
    }

    public String getOut_remarks() {
        return out_remarks;
    }

    public void setOut_remarks(String out_remarks) {
        this.out_remarks = out_remarks;
    }

    public String getOut_role() {
        return out_role;
    }

    public void setOut_role(String out_role) {
        this.out_role = out_role;
    }

    public String getOut_shift() {
        return out_shift;
    }

    public void setOut_shift(String out_shift) {
        this.out_shift = out_shift;
    }

    public String getOut_task_id() {
        return out_task_id;
    }

    public void setOut_task_id(String out_task_id) {
        this.out_task_id = out_task_id;
    }

    public String getOut_time_sheet_id() {
        return out_time_sheet_id;
    }

    public void setOut_time_sheet_id(String out_time_sheet_id) {
        this.out_time_sheet_id = out_time_sheet_id;
    }

    public String getOut_worked_time() {
        return out_worked_time;
    }

    public void setOut_worked_time(String out_worked_time) {
        this.out_worked_time = out_worked_time;
    }
    
    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getWeek_day() {
        return week_day;
    }

    public void setWeek_day(int week_day) {
        this.week_day = week_day;
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

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public String getPhase_name() {
        return phase_name;
    }

    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
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

    public String getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(String timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public String getTimesheet_day() {
        return timesheet_day;
    }

    public void setTimesheet_day(String timesheet_day) {
        this.timesheet_day = timesheet_day;
    }

    public String getTimesheet_id() {
        return timesheet_id;
    }

    public void setTimesheet_id(String timesheet_id) {
        this.timesheet_id = timesheet_id;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
        this.projectName = projectName;
    }

    public String[] getRes_check() {
        return res_check;
    }

    public void setRes_check(String[] res_check) {
        this.res_check = res_check;
    }

    public String[] getRes_hiddenId() {
        return res_hiddenId;
    }

    public void setRes_hiddenId(String[] res_hiddenId) {
        this.res_hiddenId = res_hiddenId;
    }

    public String[] getRes_phase() {
        return res_phase;
    }

    public void setRes_phase(String[] res_phase) {
        this.res_phase = res_phase;
    }

    public String[] getRes_project() {
        return res_project;
    }

    public void setRes_project(String[] res_project) {
        this.res_project = res_project;
    }

    public String[] getRes_remarks() {
        return res_remarks;
    }

    public void setRes_remarks(String[] res_remarks) {
        this.res_remarks = res_remarks;
    }

    public String[] getRes_role() {
        return res_role;
    }

    public void setRes_role(String[] res_role) {
        this.res_role = res_role;
    }

    public String[] getRes_shift() {
        return res_shift;
    }

    public void setRes_shift(String[] res_shift) {
        this.res_shift = res_shift;
    }

    public String[] getRes_task() {
        return res_task;
    }

    public void setRes_task(String[] res_task) {
        this.res_task = res_task;
    }

    public String[] getRes_timesheet_date() {
        return res_timesheet_date;
    }

    public void setRes_timesheet_date(String[] res_timesheet_date) {
        this.res_timesheet_date = res_timesheet_date;
    }

    public String[] getRes_work_location() {
        return res_work_location;
    }

    public void setRes_work_location(String[] res_work_location) {
        this.res_work_location = res_work_location;
    }

    public String[] getRes_worked_hours() {
        return res_worked_hours;
    }

    public void setRes_worked_hours(String[] res_worked_hours) {
        this.res_worked_hours = res_worked_hours;
    }

    public String[] getRes_worked_minutes() {
        return res_worked_minutes;
    }

    public void setRes_worked_minutes(String[] res_worked_minutes) {
        this.res_worked_minutes = res_worked_minutes;
    }

    public String getDb_location() {
        return db_location;
    }

    public void setDb_location(String db_location) {
        this.db_location = db_location;
    }

    public String getDb_phase() {
        return db_phase;
    }

    public void setDb_phase(String db_phase) {
        this.db_phase = db_phase;
    }

    public String getDb_project() {
        return db_project;
    }

    public void setDb_project(String db_project) {
        this.db_project = db_project;
    }

    public String getDb_remarks() {
        return db_remarks;
    }

    public void setDb_remarks(String db_remarks) {
        this.db_remarks = db_remarks;
    }

    public String getDb_role() {
        return db_role;
    }

    public void setDb_role(String db_role) {
        this.db_role = db_role;
    }

    public String getDb_shift() {
        return db_shift;
    }

    public void setDb_shift(String db_shift) {
        this.db_shift = db_shift;
    }

    public String getDb_task() {
        return db_task;
    }

    public void setDb_task(String db_task) {
        this.db_task = db_task;
    }

    public String getDb_timesheet_date() {
        return db_timesheet_date;
    }

    public void setDb_timesheet_date(String db_timesheet_date) {
        this.db_timesheet_date = db_timesheet_date;
    }

    public String getDb_worked_hours() {
        return db_worked_hours;
    }

    public void setDb_worked_hours(String db_worked_hours) {
        this.db_worked_hours = db_worked_hours;
    }

    public String getDb_available_hours() {
        return db_available_hours;
    }

    public void setDb_available_hours(String db_available_hours) {
        this.db_available_hours = db_available_hours;
    }

    public String getRes_month() {
        return res_month;
    }

    public void setRes_month(String res_month) {
        this.res_month = res_month;
    }

    public String getRes_year() {
        return res_year;
    }

    public void setRes_year(String res_year) {
        this.res_year = res_year;
    }

    public String getDb_autoId() {
        return db_autoId;
    }

    public void setDb_autoId(String db_autoId) {
        this.db_autoId = db_autoId;
    }

    public String[] getRes_autoId() {
        return res_autoId;
    }

    public void setRes_autoId(String[] res_autoId) {
        this.res_autoId = res_autoId;
    }

    public String getDb_timesheet_entry_id() {
        return db_timesheet_entry_id;
    }

    public void setDb_timesheet_entry_id(String db_timesheet_entry_id) {
        this.db_timesheet_entry_id = db_timesheet_entry_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }

    public String getHoliday_date() {
        return holiday_date;
    }

    public void setHoliday_date(String holiday_date) {
        this.holiday_date = holiday_date;
    }

    public String getHoliday_description() {
        return holiday_description;
    }

    public void setHoliday_description(String holiday_description) {
        this.holiday_description = holiday_description;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCanceled_dates() {
        return canceled_dates;
    }

    public void setCanceled_dates(String canceled_dates) {
        this.canceled_dates = canceled_dates;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getFrom_half_day() {
        return from_half_day;
    }

    public void setFrom_half_day(String from_half_day) {
        this.from_half_day = from_half_day;
    }

    public String getLength_days() {
        return length_days;
    }

    public void setLength_days(String length_days) {
        this.length_days = length_days;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTo_half_day() {
        return to_half_day;
    }

    public void setTo_half_day(String to_half_day) {
        this.to_half_day = to_half_day;
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

    public String getPrj_entry_date() {
        return prj_entry_date;
    }

    public void setPrj_entry_date(String prj_entry_date) {
        this.prj_entry_date = prj_entry_date;
    }

    public String getEmpWork_location() {
        return empWork_location;
    }

    public void setEmpWork_location(String empWork_location) {
        this.empWork_location = empWork_location;
    }
    
}
